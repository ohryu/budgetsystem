package com.talentnet.bugetsystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.Wb;
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
	@Autowired WbRepo wbRep;
	@Autowired BudgetlineRepo blRepo;
	@Autowired BudgetRepo budgetRepo;
	@Autowired BudgetDetailRepo bdRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired GroupRepo groupRepo;
	
	@RequestMapping(value = "/service/getwbbybl/{bline}", method = RequestMethod.GET)
	public List<Wb> getWbByBl(@PathVariable("bline") int bline) {
		BudgetLine bl = blRepo.findByBlid(bline);
		return wbRep.findByBline(bl);
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
}
