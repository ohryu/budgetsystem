package com.talentnet.bugetsystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.BudgetForm;
import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.Wb;
import com.talentnet.bugetsystem.Repository.BgRepo;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.GroupRepo;
import com.talentnet.bugetsystem.Repository.RoleRepo;
import com.talentnet.bugetsystem.Repository.UserRepo;
import com.talentnet.bugetsystem.Repository.UserroleRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired UserRepo userRepo;
	@Autowired RoleRepo roleRepo;
	@Autowired UserroleRepo userroleRepo;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired WbRepo wbRepo;
	@Autowired BudgetlineRepo blRepo;
	@Autowired BudgetRepo budgetRepo;
	@Autowired BudgetDetailRepo bdRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired GroupRepo groupRepo;
	@Autowired BgRepo bgRepo;
	
	@RequestMapping(value = "/service/getwbbybl/{bline}", method = RequestMethod.GET)
	public List<Wb> getWbByBl(@PathVariable("bline") int bline) {
		BudgetLine bl = blRepo.findByBlid(bline);
		return wbRepo.findByBline(bl);
	}
	
	@RequestMapping(value = "/service/summarybydept/{dept}", method = RequestMethod.GET)
	public List<BudgetDetail> getBudgetByDept(@PathVariable("dept") int deptid){
		Dept dept = deptRepo.findByDeptid(deptid);
		Budget budget = budgetRepo.findByDept(dept);
		return bdRepo.findByBudget(budget);
	}
	
	@RequestMapping(value = "service/budgetlinebydept/{dept}", method = RequestMethod.GET)
	public List<BudgetLine> getBLByDept(@PathVariable("dept") int deptid){
		Dept dept = deptRepo.findByDeptid(deptid);
		return blRepo.findByDept(dept);
	}
	
	@RequestMapping(value = "/service/sponsorbydept/{dept}", method = RequestMethod.GET)
	public List<List<Dept>> getSponsorByDept(@PathVariable("dept") int deptid){
		Dept dept = deptRepo.findByDeptid(deptid);
		List<List<Dept>> sponsor = new ArrayList<>();
		List<Group> groups= groupRepo.findByCompany(dept.getGroup().getCompany());
		for(Group group : groups) {
			sponsor.add(deptRepo.findByGroupAndSponsor(group, true));
		}
		return sponsor;
	}
	
	@RequestMapping(value = "/service/bgbywb/{wb}", method = RequestMethod.GET)
	public List<Bg> getBgByWb(@PathVariable("wb") int wbid){
		return bgRepo.findByWb(wbRepo.findByWbid(wbid));
	}
	
	@RequestMapping(value = "/service/getallwb", method = RequestMethod.GET)
	public List<Wb> getAllWb(){
		return wbRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getallbg", method = RequestMethod.GET)
	public List<Bg> getAllBg(){
		return bgRepo.findAll();
	}
	
	@RequestMapping(value="/service/savebudget", method = RequestMethod.POST)
	public String postbudget(@RequestBody List<BudgetForm> budgets){
		if(budgetRepo.findByDept(deptRepo.findByDeptid(budgets.get(0).getCdept()))==null){
			Budget bg = new Budget();
			bg.setDept(deptRepo.findByDeptid(budgets.get(0).getCdept()));
			if(budgets.get(0).getRole().equals("REPORTER")) bg.setStatus(0);
			if(budgets.get(0).getRole().equals("REVIEWER")) bg.setStatus(1);
			else bg.setStatus(2);
			budgetRepo.save(bg);
		}else {
			List<BudgetDetail> bdList= bdRepo.findByBudget(budgetRepo.findByDept(deptRepo.findByDeptid(budgets.get(0).getCdept())));
			for(BudgetDetail bd : bdList) {
				int del = 1;
				for(BudgetForm budget : budgets) {
					if(budget.getId()!=null) {
						if(bd.getBdid() == Integer.parseInt(budget.getId()))  {
							del = 0;
							break;
						}
					}	
				}
				if(del ==1) {
					bdRepo.delete(bd);
				}
			}
		}
		List<BudgetDetail> saveList = new ArrayList<>(); 
		for(BudgetForm budget : budgets) {
			BudgetDetail bgd = new BudgetDetail();
			if(budget.getId()!=null) {
				bgd.setBdid(Integer.parseInt(budget.getId()));
			}
			bgd.setBudget(budgetRepo.findByDept(deptRepo.findByDeptid(budget.getCdept())));
			bgd.setDept(deptRepo.findByDeptid(budget.getSdept()));
			bgd.setBline(blRepo.findByBlid(budget.getBline()));
			bgd.setAmount(budget.getAmount());
			bgd.setExpense(budget.getExpense());
			bgd.setAllocationtime(budget.getAllocate());
			bgd.setStarttime(budget.getStart());
			if(budget.getWb().equals("NEW")) {
				bgd.setNewdetail(budget.getBg());
			}else {
				bgd.setBg(bgRepo.findByBgid(Integer.parseInt(budget.getBg())));
			}
			saveList.add(bgd);
		}
		bdRepo.saveAll(saveList);
		return "Saved!";
	}
	
	@RequestMapping(value="/service/submitbudget", method = RequestMethod.POST)
	public String submitbudget(@RequestBody BudgetForm budget){
		if(budget.getRole().equals("REPORTER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getSdept()));
			bud.setStatus(1);
			budgetRepo.save(bud);
		}else if(budget.getRole().equals("REVIEWER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getSdept()));
			bud.setStatus(2);
			budgetRepo.save(bud);
		}
		return "Submitted!";
	}
	
	@RequestMapping(value="/service/rejectbudget", method = RequestMethod.POST)
	public String rejectbudget(@RequestBody BudgetForm budget){
		if(budget.getRole().equals("NOT")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getSdept()));
			bud.setStatus(1);
			budgetRepo.save(bud);
		}else if(budget.getRole().equals("REVIEWER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getSdept()));
			bud.setStatus(0);
			budgetRepo.save(bud);
		}
		return "Rejected!";
	}
}
