package com.talentnet.bugetsystem.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.DTO.BudgetDTO;
import com.talentnet.bugetsystem.DTO.BudgetDetailDTO;
import com.talentnet.bugetsystem.DTO.CrtCompDTO;
import com.talentnet.bugetsystem.DTO.PasswordDTO;
import com.talentnet.bugetsystem.DTO.BlineDTO;
import com.talentnet.bugetsystem.DTO.BlineDeptDTO;
import com.talentnet.bugetsystem.DTO.SysRoleDTO;
import com.talentnet.bugetsystem.DTO.UserDTO;
import com.talentnet.bugetsystem.DTO.WbDTO;
import com.talentnet.bugetsystem.DTO.BlineDeptForm;
import com.talentnet.bugetsystem.DTO.CompanyDTO;
import com.talentnet.bugetsystem.DTO.CriteriaDetailDTO;
import com.talentnet.bugetsystem.DTO.GroupDTO;
import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.Criteria;
import com.talentnet.bugetsystem.Entity.CriteriaDetail;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.HistoricalAmount;
import com.talentnet.bugetsystem.Entity.MapBline_Dept;
import com.talentnet.bugetsystem.Entity.MoreDetail;
import com.talentnet.bugetsystem.Entity.UserRole;
import com.talentnet.bugetsystem.Entity.Wb;
import com.talentnet.bugetsystem.Entity.Year;
import com.talentnet.bugetsystem.Repository.BgRepo;
import com.talentnet.bugetsystem.Repository.Bline_DeptRepo;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.CompanyRepo;
import com.talentnet.bugetsystem.Repository.CriteriaDetailRepo;
import com.talentnet.bugetsystem.Repository.CriteriaRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.GroupRepo;
import com.talentnet.bugetsystem.Repository.HistoricalAmountRepo;
import com.talentnet.bugetsystem.Repository.MoreDetailRepo;
import com.talentnet.bugetsystem.Repository.RoleRepo;
import com.talentnet.bugetsystem.Repository.SystemroleRepo;
import com.talentnet.bugetsystem.Repository.UserRepo;
import com.talentnet.bugetsystem.Repository.UserroleRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;
import com.talentnet.bugetsystem.Repository.YearRepo;
import com.talentnet.bugetsystem.Service.ExportExcelService;

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
	@Autowired CompanyRepo compRepo;
	@Autowired SystemroleRepo sysroleRepo;
	@Autowired Bline_DeptRepo bldeptRepo;
	@Autowired CriteriaDetailRepo cdRepo;
	@Autowired CriteriaRepo criteriaRepo;
	@Autowired HistoricalAmountRepo haRepo;
	@Autowired MoreDetailRepo mdtRepo;
	@Autowired YearRepo yearRepo;
	
	@RequestMapping(value = "/service/getwbbybl/{bline}", method = RequestMethod.GET)
	public List<Wb> getWbByBl(@PathVariable("bline") int bline) {
		BudgetLine bl = blRepo.findByBlid(bline);
		return wbRepo.findByBline(bl);
	}
	
	@RequestMapping(value = "/service/summarybydept/{dept}", method = RequestMethod.GET)
	public List<BudgetDetailDTO> getBudgetByDept(@PathVariable("dept") int deptid){
		Dept dept = deptRepo.findByDeptid(deptid);
		Budget budget = budgetRepo.findByDept(dept);
		List<BudgetDetail> bdList = bdRepo.findByBudget(budget);
		List<BudgetDetailDTO> bdDTO = new ArrayList<>();
		for(BudgetDetail bd : bdList) {
			bdDTO.add(new BudgetDetailDTO(bd, mdtRepo.findByBudgetdetail(bd)));
		}
		return bdDTO;
	}
	
	@RequestMapping(value = "service/budgetlinebydept/{dept}", method = RequestMethod.GET)
	public List<MapBline_Dept> getBLByDept(@PathVariable("dept") int deptid){
		Dept dept = deptRepo.findByDeptid(deptid);
		return bldeptRepo.findByDept(dept);
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
	
	@RequestMapping(value = "/service/getallbg", method = RequestMethod.GET)
	public List<Bg> getAllBg(){
		return bgRepo.findAll();
	}
	
	@RequestMapping(value="/service/savebudget", method = RequestMethod.POST)
	public String postbudget(@RequestBody List<BudgetDTO> budgets){
		if(budgetRepo.findByDept(deptRepo.findByDeptid(budgets.get(0).getCdept()))==null){
			Budget bg = new Budget();
			bg.setDept(deptRepo.findByDeptid(budgets.get(0).getCdept()));
			if(budgets.get(0).getRole().equals("REPORTER")) bg.setStatus(0);
			else if(budgets.get(0).getRole().equals("REVIEWER")) bg.setStatus(1);
			else bg.setStatus(2);
			budgetRepo.save(bg);
		}else {
			List<BudgetDetail> bdList= bdRepo.findByBudget(budgetRepo.findByDept(deptRepo.findByDeptid(budgets.get(0).getCdept())));
			for(BudgetDetail bd : bdList) {
				int del = 1;
				for(BudgetDTO budget : budgets) {
					if(budget.getId()!=null) {
						if(bd.getBdid() == Integer.parseInt(budget.getId()))  {
							del = 0;
							break;
						}
					}
					if(del ==1) {
						mdtRepo.removeByBudgetdetail(bd);
						bdRepo.delete(bd);
					}
				}		
			}
			for(BudgetDTO budget : budgets) {
				if(budget.getId()!=null) {
					List<MoreDetail> mdtList = mdtRepo.findByBudgetdetail(bdRepo.findByBdid(Integer.parseInt(budget.getId())));
					for(MoreDetail mdt : mdtList) {
						int delmdt = 1;
						for(List<String> mdtDTO : budget.getMoredetail()) {
							if(mdtDTO.get(0)!="0") {
								if(mdt.getMdtid() == Integer.parseInt(mdtDTO.get(0))) {
									delmdt = 0;
									break;
								}
							}
						}
						if(delmdt ==1) {
							mdtRepo.delete(mdt);
						}
					}			
				}
			}
		}
		List<BudgetDetail> bdsaveList = new ArrayList<>();
		List<MoreDetail> mdtsaveList = new ArrayList<>();
		for(BudgetDTO budget : budgets) {

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
			bdsaveList.add(bgd);
			for(List<String> mdtDTO : budget.getMoredetail()) {
				MoreDetail mdt = new MoreDetail();
				if(mdtDTO.get(0)!="0") {
					mdt.setMdtid(Integer.parseInt(mdtDTO.get(0)));
				}
				mdt.setBudgetdetail(bgd);
				mdt.setAmount(Long.parseLong(mdtDTO.get(2)));
				mdt.setDetail(mdtDTO.get(1));
				mdtsaveList.add(mdt);
			}
		}
		bdRepo.saveAll(bdsaveList);
		mdtRepo.saveAll(mdtsaveList);
		return "Saved!";
	}
	
	@RequestMapping(value="/service/submitbudget", method = RequestMethod.POST)
	public String submitbudget(@RequestBody BudgetDTO budget){
		if(budget.getRole().equals("REPORTER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getCdept()));
			bud.setStatus(1);
			budgetRepo.save(bud);
		}else if(budget.getRole().equals("REVIEWER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getCdept()));
			bud.setStatus(2);
			budgetRepo.save(bud);
		}
		return "Submitted!";
	}
	
	@RequestMapping(value="/service/rejectbudget", method = RequestMethod.POST)
	public String rejectbudget(@RequestBody BudgetDTO budget){
		if(budget.getRole().equals("NOT")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getCdept()));
			bud.setStatus(1);
			budgetRepo.save(bud);
		}else if(budget.getRole().equals("REVIEWER")) {
			Budget bud = budgetRepo.findByDept(deptRepo.findByDeptid(budget.getCdept()));
			bud.setStatus(0);
			budgetRepo.save(bud);
		}
		return "Rejected!";
	}
	
	@RequestMapping(value = "/service/getallaccount", method = RequestMethod.GET)
	public List<BUser> getallaccount(){
		return userRepo.findAll();
	}
	
	@RequestMapping(value = "/service/resetpass/{id}", method = RequestMethod.GET)
	public String resetpass(@PathVariable("id") int id){
		BUser user = userRepo.findByUserid(id);
		user.setPassword(passwordEncoder.encode("P@ssword123"));
		user.setActive(false);
		userRepo.save(user);
		return "Password Reseted Successfully!";
	}
	
	@RequestMapping(value = "/service/deleteaccount/{id}", method = RequestMethod.GET)
	public String deleteAccount(@PathVariable("id") int id){
		BUser user = userRepo.findByUserid(id);
		userroleRepo.deleteAll(userroleRepo.findByUser(user));
		userRepo.removeByUserid(id);
		return "Account Deleted Successfully!";
	}
	
	@RequestMapping(value="/service/changepassword", method = RequestMethod.POST)
	public String changePassword(@RequestBody PasswordDTO password, Principal principal){
		BUser user = userRepo.findByUsername(principal.getName());
		if(!passwordEncoder.matches(password.getOldpass(), user.getPassword())) {
			return "Current password does not match!";
		}else {
			user.setPassword(passwordEncoder.encode(password.getNewpass()));
			user.setActive(true);
			userRepo.save(user);
		}
		return "Password Changed Successful!";
	}
	
	@RequestMapping(value = "/service/getallcompany", method = RequestMethod.GET)
	public List<Company> getAllCompany(){
		return compRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getallgroup", method = RequestMethod.GET)
	public List<Group> getAllGroupByComp(){
		return groupRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getalldept", method = RequestMethod.GET)
	public List<Dept> getAllDeptByGroup(){
		return deptRepo.findByControl(true);
	}
	
	@RequestMapping(value="/service/saveuser", method = RequestMethod.POST)
	public String saveuser(@RequestBody UserDTO userform){
		if(userRepo.findByUsername(userform.getUsername())!=null) return "Username existed!";
		if(userform.getRole().isEmpty()) return "User must have aleast one role";
		BUser user = new BUser();
		user.setUsername(userform.getUsername());
		user.setFullname(userform.getFullname());
		user.setPassword(passwordEncoder.encode("P@ssword123"));
		user.setRole(roleRepo.findByRoleid(2));
		user.setActive(false);	
		
		List<UserRole> uroleList = new ArrayList<>();
		for(SysRoleDTO roles : userform.getRole()) {
			if(roles.getRole()==1) {
				UserRole urole = new UserRole();
				if(!userroleRepo.findByGroup(groupRepo.findByGroupid(roles.getGroup())).isEmpty()) {
					return "Reviewer For Group "+ groupRepo.findByGroupid(roles.getGroup()).getGroupcode()+" Existed!";
				};
				userRepo.save(user);
				urole.setUser(userRepo.findByUsername(userform.getUsername()));
				urole.setGroup(groupRepo.findByGroupid(roles.getGroup()));
				urole.setSysrole(sysroleRepo.findByRoleid(1));
				uroleList.add(urole);
			}else {
				UserRole urole = new UserRole();
				if(!userroleRepo.findByDept(deptRepo.findByDeptid(roles.getDept())).isEmpty()) {
					return "Reporter For Dept "+ deptRepo.findByDeptid(roles.getDept()).getDeptname()+" Existed!";
				};
				userRepo.save(user);
				urole.setUser(userRepo.findByUsername(userform.getUsername()));
				urole.setDept(deptRepo.findByDeptid(roles.getDept()));
				urole.setSysrole(sysroleRepo.findByRoleid(2));
				uroleList.add(urole);
			}
		}
		userroleRepo.saveAll(uroleList);
		return "Account Created Successfully!";

	}
	
	@RequestMapping(value="/service/saveadmin", method = RequestMethod.POST)
	public String saveadmin(@RequestBody UserDTO userform){
		if(userRepo.findByUsername(userform.getUsername())!=null) return "Username existed!";
		BUser user = new BUser();
		user.setUsername(userform.getUsername());
		user.setFullname(userform.getFullname());
		user.setPassword(passwordEncoder.encode("P@ssword123"));
		user.setRole(roleRepo.findByRoleid(1));
		user.setActive(false);
		userRepo.save(user);
		return "Account Created Successfully!";
	}
	
	@RequestMapping(value = "/service/getuser/{userid}", method = RequestMethod.GET)
	public List<UserRole> getUser(@PathVariable("userid") int id){
		return userroleRepo.findByUser(userRepo.findByUserid(id));
	}
	
	@RequestMapping(value = "/service/getadmin/{userid}", method = RequestMethod.GET)
	public BUser getAdmin(@PathVariable("userid") int id){
		return userRepo.findByUserid(id);
	}
	
	@RequestMapping(value="/service/edituser", method = RequestMethod.POST)
	public String editUser(@RequestBody UserDTO userform){
		if(userform.getRole().isEmpty()) return "User must have aleast one role";
		BUser user = userRepo.findByUsername(userform.getUsername());
		if(!user.getFullname().equals(userform.getFullname())) {
			user.setFullname(userform.getFullname());
			userRepo.save(user);
		}
		userroleRepo.removeByUser(user);
		List<UserRole> uroleList = new ArrayList<>();
		for(SysRoleDTO roles : userform.getRole()) {
			if(roles.getRole()==1) {
				UserRole urole = new UserRole();
				if(userroleRepo.findByGroup(groupRepo.findByGroupid(roles.getGroup())).size()>1) {
					return "Reviewer For Group "+ groupRepo.findByGroupid(roles.getGroup()).getGroupcode()+" Existed!";
				};
				urole.setUser(userRepo.findByUsername(userform.getUsername()));
				urole.setGroup(groupRepo.findByGroupid(roles.getGroup()));
				urole.setSysrole(sysroleRepo.findByRoleid(1));
				uroleList.add(urole);
			}else {
				UserRole urole = new UserRole();
				if(userroleRepo.findByDept(deptRepo.findByDeptid(roles.getDept())).size()>1) {
					return "Reporter For Dept "+ deptRepo.findByDeptid(roles.getDept()).getDeptname()+" Existed!";
				};
				urole.setUser(userRepo.findByUsername(userform.getUsername()));
				urole.setDept(deptRepo.findByDeptid(roles.getDept()));
				urole.setSysrole(sysroleRepo.findByRoleid(2));
				uroleList.add(urole);
			}
		}
		userroleRepo.saveAll(uroleList);
		return "Account Edited Successfully!";

	}
	
	@RequestMapping(value="/service/editadmin", method = RequestMethod.POST)
	public String editAdmin(@RequestBody UserDTO userform){
		BUser user = userRepo.findByUsername(userform.getUsername());
		if(!user.getFullname().equals(userform.getFullname())) {
			user.setFullname(userform.getFullname());
			userRepo.save(user);
		}
		return "Account Edited Successfully!";
	}
	
	@RequestMapping(value="/service/getdepts", method = RequestMethod.GET)
	public List<CompanyDTO> getAlldepts(Principal principal){
		List<CompanyDTO> companydtos = new ArrayList<>();
		if(userRepo.findByUsername(principal.getName()).getRole().equals(roleRepo.findByRoleid(1))) {
			List<Company> companies = compRepo.findAll();
			for(Company company : companies) {
				CompanyDTO companydto = new CompanyDTO();
				List<GroupDTO> groupdtos = new ArrayList<>();
				List<Group> groups = groupRepo.findByCompany(company);
				for(Group group : groups) {
					GroupDTO groupdto = new GroupDTO(); 
					List<Dept> depts = deptRepo.findByGroup(group);
					groupdto.setGroup(group);
					groupdto.setDeptlist(depts);
					groupdtos.add(groupdto);
				}
				companydto.setCompany(company);
				companydto.setGrouplist(groupdtos);
				companydtos.add(companydto);
			}
		}else {
			List<UserRole> userrole = userroleRepo.findByUser(userRepo.findByUsername(principal.getName()));
			List<Company> comps = new ArrayList<>();
			List<Group> groups = new ArrayList<>();
			List<Dept> depts = new ArrayList<>();
			for(UserRole ur : userrole) {
				if(ur.getSysrole().equals(sysroleRepo.findByRoleid(1))) {
					if(!comps.contains(ur.getGroup().getCompany()))
						comps.add(ur.getGroup().getCompany());
					if(!groups.contains(ur.getGroup())) {
						groups.add(ur.getGroup());
						for(Dept dept : deptRepo.findByGroupAndControl(ur.getGroup(), true)) {
							if(!depts.contains(dept))
								depts.add(dept);
						}
					}
				}else {
					if(!comps.contains(ur.getDept().getGroup().getCompany()))
						comps.add(ur.getDept().getGroup().getCompany());
					if(!groups.contains(ur.getDept().getGroup()))
						groups.add(ur.getDept().getGroup());
					if(!depts.contains(ur.getDept()))
						depts.add(ur.getDept());
				}
			}
			for(Company comp: comps) {
				CompanyDTO companydto = new CompanyDTO();
				List<GroupDTO> tempgroups = new ArrayList<>();
				for(Group group : groups) {
					if(group.getCompany().equals(comp)) {
						GroupDTO tempgroup = new GroupDTO();
						tempgroup.setGroup(group);
						List<Dept> tempdept = new ArrayList<>();
						for (Dept dept : depts) {
							if(dept.getGroup().equals(group))
								tempdept.add(dept);
						}
						tempgroup.setDeptlist(tempdept);
						tempgroups.add(tempgroup);
					}
				}
				companydto.setCompany(comp);
				companydto.setGrouplist(tempgroups);
				companydtos.add(companydto);
			}
		}
		return companydtos;
	}
	
	@RequestMapping(value = "/service/getdept/{id}", method = RequestMethod.GET)
	public Dept getDept(@PathVariable("id") int id){
		return deptRepo.findByDeptid(id);
	}
	
	@RequestMapping(value = "/service/getcomp/{id}", method = RequestMethod.GET)
	public Company getCompany(@PathVariable("id") int id){
		return compRepo.findByCompanyid(id);
	}
	
	@RequestMapping(value = "/service/getgroup/{id}", method = RequestMethod.GET)
	public Group getGroup(@PathVariable("id") int id){
		return groupRepo.findByGroupid(id);
	}
	
	@RequestMapping(value="/service/editcomp", method = RequestMethod.POST)
	public String editComp(@RequestBody List<String> comp){
		if(comp.get(0)=="") return "Company name can not be blank";
		if(compRepo.findByCompanyname(comp.get(0))!=null) {
			return "Company Existed!";
		}
		Company company = compRepo.findByCompanyid(Integer.parseInt(comp.get(1)));
		company.setCompanyname(comp.get(0));
		compRepo.save(company);
		return "Company Edited Successfully!";
	}
	
	@RequestMapping(value="/service/editgroup", method = RequestMethod.POST)
	public String editGroup(@RequestBody List<String> group){
		if(group.get(0)=="") return "Group code can not be blank";
		if(groupRepo.findByGroupcodeAndCompany(group.get(0), groupRepo.findByGroupid(Integer.parseInt(group.get(1))).getCompany())!=null) {
			return "Group Existed!";
		}
		Group grp = groupRepo.findByGroupid(Integer.parseInt(group.get(1)));
		grp.setGroupcode(group.get(0));
		groupRepo.save(grp);
		return "Group Edited Successfully!";
	}
	
	@RequestMapping(value="/service/editdept", method = RequestMethod.POST)
	public String editDept(@RequestBody List<String> dept){
		if(dept.get(1)=="") return "Dept name can not be blank";
		if(dept.get(0)=="") return "Dept code can not be blank";
		/*if(deptRepo.findByDeptcodeAndGroup(dept.get(0), deptRepo.findByDeptid(Integer.parseInt(dept.get(2))).getGroup())!=null
				|| deptRepo.findByDeptnameAndGroup(dept.get(1), deptRepo.findByDeptid(Integer.parseInt(dept.get(2))).getGroup()) !=null){
			return "Dept Existed!";
		}*/
		Dept dpt = deptRepo.findByDeptid(Integer.parseInt(dept.get(2)));
		dpt.setDeptcode(dept.get(0));
		dpt.setDeptname(dept.get(1));
		dpt.setControl(Boolean.valueOf(dept.get(3)));
		dpt.setSponsor(Boolean.valueOf(dept.get(4)));
		deptRepo.save(dpt);
		return "Dept Edited Successfully!";
	}
	
	@RequestMapping(value="/service/addcomp", method = RequestMethod.POST)
	public String addComp(@RequestBody List<String> comp){
		if(compRepo.findByCompanyname(comp.get(0))!=null) {
			return "Company Existed!";
		}
		Company company = new Company();
		company.setCompanyname(comp.get(0));
		compRepo.save(company);
		return "Company Created Successfully!";
	}
	
	@RequestMapping(value="/service/addgroup", method = RequestMethod.POST)
	public String addGroup(@RequestBody List<String> group){
		if(groupRepo.findByGroupcodeAndCompany(group.get(1), compRepo.findByCompanyid(Integer.parseInt(group.get(0))))!=null) {
			return "Group Existed!";
		}
		Group grp = new Group();
		grp.setCompany(compRepo.findByCompanyid(Integer.parseInt(group.get(0))));
		grp.setGroupcode(group.get(1));
		groupRepo.save(grp);
		return "Group Created Successfully!";
	}
	
	@RequestMapping(value="/service/adddept", method = RequestMethod.POST)
	public String addDept(@RequestBody List<String> dept){
		if(deptRepo.findByDeptcodeAndGroup(dept.get(1), groupRepo.findByGroupid(Integer.parseInt(dept.get(0))))!=null
				|| deptRepo.findByDeptnameAndGroup(dept.get(2), groupRepo.findByGroupid(Integer.parseInt(dept.get(0)))) !=null){
			return "Dept Existed!";
		}
		Dept dpt = new Dept();
		dpt.setGroup(groupRepo.findByGroupid(Integer.parseInt(dept.get(0))));
		dpt.setDeptcode(dept.get(1));
		dpt.setDeptname(dept.get(2));
		dpt.setControl(Boolean.valueOf(dept.get(3)));
		dpt.setSponsor(Boolean.valueOf(dept.get(4)));
		dpt = deptRepo.save(dpt);
		for(Criteria crt : criteriaRepo.findAll()) {
			CriteriaDetail cd = new CriteriaDetail();
			cd.setDept(dpt);
			cd.setAmount(0);
			cd.setCriteria(crt);
			cdRepo.save(cd);
		}
		return "Dept Created Successfully!";
	}
	
	@RequestMapping(value = "/service/delcompany/{id}", method = RequestMethod.GET)
	public String delCompany(@PathVariable("id") int id){
		List<Group> groups =  groupRepo.findByCompany(compRepo.findByCompanyid(id));
		for(Group group : groups) {
			List<Dept> depts = deptRepo.findByGroup(group);
			for(Dept dept : depts) {
				List<UserRole> uroles = userroleRepo.findByDept(dept);
				for(UserRole urole : uroles) {
					urole.setDept(null);
					userroleRepo.save(urole);
				}
				List<BudgetDetail> bd = bdRepo.findByDept(dept);
				bdRepo.deleteAll(bd);
				Budget budget = budgetRepo.findByDept(dept);
				bd = bdRepo.findByBudget(budget);
				bdRepo.deleteAll(bd);
				budgetRepo.removeByDept(dept);
				List<MapBline_Dept> mapbd = bldeptRepo.findByDept(dept);
				bldeptRepo.deleteAll(mapbd);
				List<CriteriaDetail> cdList = cdRepo.findByDept(dept);
				cdRepo.deleteAll(cdList);
			}
			deptRepo.deleteAll(depts);
		}
		groupRepo.deleteAll(groups);
		compRepo.removeByCompanyid(id);
		return "Company Deleted Successfully!";
	}
	
	@RequestMapping(value = "/service/delgroup/{id}", method = RequestMethod.GET)
	public String delGroup(@PathVariable("id") int id){
		Group group = groupRepo.findByGroupid(id);
		List<Dept> depts = deptRepo.findByGroup(group);
		for(Dept dept : depts) {
			List<UserRole> uroles = userroleRepo.findByDept(dept);
			for(UserRole urole : uroles) {
				urole.setDept(null);
				userroleRepo.save(urole);
			}
			List<BudgetDetail> bd = bdRepo.findByDept(dept);
			bdRepo.deleteAll(bd);
			Budget budget = budgetRepo.findByDept(dept);
			bd = bdRepo.findByBudget(budget);
			bdRepo.deleteAll(bd);
			budgetRepo.removeByDept(dept);
			List<MapBline_Dept> mapbd = bldeptRepo.findByDept(dept);
			bldeptRepo.deleteAll(mapbd);
			List<CriteriaDetail> cdList = cdRepo.findByDept(dept);
			cdRepo.deleteAll(cdList);
		}
		deptRepo.deleteAll(depts);
		groupRepo.removeByGroupid(id);
		return "Group Deleted Successfully!";
	}
	
	@RequestMapping(value = "/service/deldept/{id}", method = RequestMethod.GET)
	public String delDept(@PathVariable("id") int id){
		Dept dept = deptRepo.findByDeptid(id);
		List<UserRole> uroles = userroleRepo.findByDept(dept);
		for(UserRole urole : uroles) {
			urole.setDept(null);
			userroleRepo.save(urole);
		}
		List<BudgetDetail> bd = bdRepo.findByDept(dept);
		bdRepo.deleteAll(bd);
		Budget budget = budgetRepo.findByDept(dept);
		bd = bdRepo.findByBudget(budget);
		bdRepo.deleteAll(bd);
		budgetRepo.removeByDept(dept);
		List<MapBline_Dept> mapbd = bldeptRepo.findByDept(dept);
		bldeptRepo.deleteAll(mapbd);
		List<CriteriaDetail> cdList = cdRepo.findByDept(dept);
		cdRepo.deleteAll(cdList);
		deptRepo.removeByDeptid(id);
		return "Dept Deleted Successfully";
	}
	
	@RequestMapping(value = "/service/getallbline", method = RequestMethod.GET)
	public List<BudgetLine> getAllBline(){
		return blRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getallwb", method = RequestMethod.GET)
	public List<Wb> getAllWb(){
		return wbRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getbgs", method = RequestMethod.GET)
	public List<BlineDTO> getAllbg(){
		List<BlineDTO> blinedtos = new ArrayList<>();
		List<BudgetLine> blines = blRepo.findAll();
		for(BudgetLine bline : blines) {
			BlineDTO blinedto = new BlineDTO();
			List<WbDTO> wbdtos = new ArrayList<>();
			List<Wb> wbs = wbRepo.findByBline(bline);
			for(Wb wb : wbs) {
				WbDTO wbdto = new WbDTO(); 
				List<Bg> bgs = bgRepo.findByWb(wb);
				wbdto.setWb(wb);
				wbdto.setBglist(bgs);
				wbdtos.add(wbdto);
			}
			blinedto.setBline(bline);
			blinedto.setWblist(wbdtos);
			blinedtos.add(blinedto);
		}
		return blinedtos;
	}
	
	@RequestMapping(value = "/service/getbudgetline", method = RequestMethod.GET)
	public List<BlineDeptDTO> getBudgetline(){
		List<BlineDeptDTO> blinedepts = new ArrayList<>();
		List<Dept> depts = new ArrayList<>();
		List<MapBline_Dept> bline_depts = bldeptRepo.findAll();
		for(MapBline_Dept bline_dept : bline_depts) {
			if(!depts.contains(bline_dept.getDept())) {
				depts.add(bline_dept.getDept());
			}
		}
		for(Dept dept : depts) {
			BlineDeptDTO bldto = new BlineDeptDTO();
			List<MapBline_Dept> blinedept=  bldeptRepo.findByDept(dept);
			List<BudgetLine> bline = new ArrayList<>();
			for(MapBline_Dept bline_dept : blinedept) {
				bline.add(bline_dept.getBline());
			}
			bldto.setBlinelist(bline);
			bldto.setDept(dept);
			blinedepts.add(bldto);
		}
		return blinedepts;
	}
	
	@RequestMapping(value="/service/addblinetodept", method = RequestMethod.POST)
	public String addBlinetoDept(@RequestBody BlineDeptForm blinedeptform){
		List<MapBline_Dept> blines_dept = bldeptRepo.findByDept(deptRepo.findByDeptid(blinedeptform.getDept()));
		List<Integer> blineid = new ArrayList<>();
		
		for(MapBline_Dept bline_dept : blines_dept) {
			blineid.add(bline_dept.getBline().getBlid());
		}
		blines_dept = new ArrayList<>();
		for(Integer i : blinedeptform.getBlinelist()) {
			if(!blineid.contains(i)) {
				MapBline_Dept blinedept = new MapBline_Dept();
				blinedept.setDept(deptRepo.findByDeptid(blinedeptform.getDept()));
				blinedept.setBline(blRepo.findByBlid(i));
				blines_dept.add(blinedept);
			}
		}
		bldeptRepo.saveAll(blines_dept);
		return "Successful!";
	}
	

	@RequestMapping(value="/service/delblineofdept", method = RequestMethod.POST)
	public String delBlineofDept(@RequestBody List<Integer> blinedept){
		bldeptRepo.removeByBlineAndDept(blRepo.findByBlid(blinedept.get(1)), deptRepo.findByDeptid(blinedept.get(0)));
		return "Successful!";
	}
	
	@RequestMapping(value="/service/addbline", method = RequestMethod.POST)
	public String addBline(@RequestBody List<String> bline) {		
		if(blRepo.findByBlcode(bline.get(0))!=null) return "Budget Line Code existed!";
		if(blRepo.findByBlname(bline.get(1))!=null) return "Budget Line Name existed!";
		BudgetLine budgetline = new BudgetLine();
		budgetline.setBlcode(bline.get(0));
		budgetline.setBlname(bline.get(1));
		blRepo.save(budgetline);
		return "Successful!";
	}
	
	@RequestMapping(value="/service/addwb", method = RequestMethod.POST)
	public String addWb(@RequestBody List<String> wb) {		
		if(wbRepo.findByBlineAndWbcode(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(1))!=null) return "WB Code existed!";
		if(wbRepo.findByBlineAndWbname(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(2))!=null) return "WB Name existed!";
		Wb wb1 = new Wb();
		wb1.setBline(blRepo.findByBlid(Integer.parseInt(wb.get(0))));
		wb1.setWbcode(wb.get(1));
		wb1.setWbname(wb.get(2));
		wbRepo.save(wb1);
		return "Successful!";
	}
	
	@RequestMapping(value="/service/addbg", method = RequestMethod.POST)
	public String addBg(@RequestBody List<String> bg) {		
		if(bgRepo.findByWbAndBgcode(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(1))!=null) return "BG Code existed!";
		if(bgRepo.findByWbAndBgname(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(2))!=null) return "BG Name existed!";
		Bg bg1 = new Bg();
		bg1.setWb(wbRepo.findByWbid(Integer.parseInt(bg.get(0))));
		bg1.setBgcode(bg.get(1));
		bg1.setBgname(bg.get(2));
		bgRepo.save(bg1);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/getbg/{id}", method = RequestMethod.GET)
	public Bg getBg(@PathVariable("id") int id) {
		return bgRepo.findByBgid(id);
	}
	
	@RequestMapping(value="/service/editbline", method = RequestMethod.POST)
	public String editBline(@RequestBody List<String> bline) {		
		BudgetLine budgetline = blRepo.findByBlid(Integer.parseInt(bline.get(0)));
		if(budgetline.getBlcode().equals(bline.get(1)) && !budgetline.getBlname().equals(bline.get(2))) {
			if(blRepo.findByBlname(bline.get(1))!=null) return "Budget Line Name existed!";
		}else if(budgetline.getBlname().equals(bline.get(2)) && !budgetline.getBlcode().equals(bline.get(1))){
			if(blRepo.findByBlname(bline.get(1))!=null) return "Budget Line Name existed!";
		}else if(!budgetline.getBlname().equals(bline.get(2)) && !budgetline.getBlcode().equals(bline.get(1))){
			if(blRepo.findByBlcode(bline.get(0))!=null) return "Budget Line Code existed!";
			if(blRepo.findByBlname(bline.get(1))!=null) return "Budget Line Name existed!";
		}
		budgetline.setBlcode(bline.get(1));
		budgetline.setBlname(bline.get(2));
		blRepo.save(budgetline);
		return "Successful!";
	}
	
	@RequestMapping(value="/service/editwb", method = RequestMethod.POST)
	public String editWb(@RequestBody List<String> wb) {		
		Wb wb1 = wbRepo.findByWbid(Integer.parseInt(wb.get(0)));
		if(wb1.getWbcode().equals(wb.get(1)) && !wb1.getWbname().equals(wb.get(2))) {
			if(wbRepo.findByBlineAndWbname(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(2))!=null) return "WB Name existed!";
		}else if(!wb1.getWbcode().equals(wb.get(1)) && wb1.getWbname().equals(wb.get(2))) {
			if(wbRepo.findByBlineAndWbcode(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(1))!=null) return "WB Code existed!";
		}else if(!wb1.getWbcode().equals(wb.get(1)) && !wb1.getWbname().equals(wb.get(2))) {
			if(wbRepo.findByBlineAndWbcode(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(1))!=null) return "WB Code existed!";
			if(wbRepo.findByBlineAndWbname(blRepo.findByBlid(Integer.parseInt(wb.get(0))), wb.get(2))!=null) return "WB Name existed!";
		}
		wb1.setWbcode(wb.get(1));
		wb1.setWbname(wb.get(2));
		wbRepo.save(wb1);
		return "Successful!";
	}
	
	@RequestMapping(value="/service/editbg", method = RequestMethod.POST)
	public String editBg(@RequestBody List<String> bg) {		
		Bg bg1 = bgRepo.findByBgid(Integer.parseInt(bg.get(0)));
		if(bg1.getBgcode().equals(bg.get(1)) && !bg1.getBgname().equals(bg.get(2))){
			if(bgRepo.findByWbAndBgname(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(2))!=null) return "BG Name existed!";
		}else if(!bg1.getBgcode().equals(bg.get(1)) && bg1.getBgname().equals(bg.get(2))){
			if(bgRepo.findByWbAndBgcode(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(1))!=null) return "BG Code existed!";
		}else if(!bg1.getBgcode().equals(bg.get(1)) && !bg1.getBgname().equals(bg.get(2))){
			if(bgRepo.findByWbAndBgcode(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(1))!=null) return "BG Code existed!";
			if(bgRepo.findByWbAndBgname(wbRepo.findByWbid(Integer.parseInt(bg.get(0))), bg.get(2))!=null) return "BG Name existed!";
		}
		bg1.setBgcode(bg.get(1));
		bg1.setBgname(bg.get(2));
		bgRepo.save(bg1);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/delbl/{id}", method = RequestMethod.GET)
	@Transactional
	public String delBline(@PathVariable("id") int blid) {
		bdRepo.removeByBline(blRepo.findByBlid(blid));
		bldeptRepo.removeByBline(blRepo.findByBlid(blid));
		for(Wb wb : wbRepo.findByBline(blRepo.findByBlid(blid))) {
			bgRepo.removeByWb(wb);
		}
		wbRepo.removeByBline(blRepo.findByBlid(blid));
		blRepo.removeByBlid(blid);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/delwb/{id}", method = RequestMethod.GET)
	@Transactional
	public String delWb(@PathVariable("id") int wbid) {
		for(Bg bg : bgRepo.findByWb(wbRepo.findByWbid(wbid))) {
			bdRepo.removeByBg(bg);
		}
		bgRepo.removeByWb(wbRepo.findByWbid(wbid));
		wbRepo.removeByWbid(wbid);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/delbg/{id}", method = RequestMethod.GET)
	@Transactional
	public String delBg(@PathVariable("id") int bgid) {
		bdRepo.removeByBg(bgRepo.findByBgid(bgid));
		bgRepo.removeByBgid(bgid);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/getallcriteria", method = RequestMethod.GET)
	public List<com.talentnet.bugetsystem.Entity.Criteria> getAllCriteria(){
		return criteriaRepo.findAll();
	}
	
	@RequestMapping(value = "/service/getallcriteriadetail", method = RequestMethod.GET)
	public List<CrtCompDTO> getAllCriteriaDetail(){
		List<Company> companies = compRepo.findAll();
		List<CriteriaDetail> cds = cdRepo.findAll();
		List<Criteria> criterias = criteriaRepo.findAll();
		List<CrtCompDTO> crtcomp = new ArrayList<>();
		for(Criteria criteria : criterias) {
			CrtCompDTO crtcompDTO = new CrtCompDTO();
			List<CriteriaDetailDTO> cdDTOList = new ArrayList<>();
			for(Company company : companies) {
				CriteriaDetailDTO cdDTO = new CriteriaDetailDTO();
				List<CriteriaDetail> cdList = new ArrayList<>();
				for(CriteriaDetail cd : cds) {
					if(cd.getDept().getGroup().getCompany().getCompanyid()==company.getCompanyid() && cd.getCriteria().getCriteriaid()==criteria.getCriteriaid()) {
						cdList.add(cd);
					}
				}
				cdDTO.setCompany(company);
				cdDTO.setCdList(cdList);
				cdDTOList.add(cdDTO);
			}
			crtcompDTO.setCriteria(criteria);
			crtcompDTO.setCriterialist(cdDTOList);
			crtcomp.add(crtcompDTO);
		}
		return crtcomp;
	}
	
	@RequestMapping(value="/service/addcriteria", method = RequestMethod.POST)
	public String addCriteria(@RequestBody List<String> criteria) {		
		Criteria crt = new Criteria();
		crt.setCriterianame(criteria.get(0));
		crt = criteriaRepo.save(crt);
		List<Dept> deptList = deptRepo.findAll();
		List<CriteriaDetail> cdList = new ArrayList<>();
		for(Dept dept : deptList) {
			CriteriaDetail cd = new CriteriaDetail();
			cd.setCriteria(crt);
			cd.setDept(dept);
			cd.setAmount(0);
			cdList.add(cd);
		}
		cdRepo.saveAll(cdList);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/savecriteria", method = RequestMethod.POST)
	public String saveCriteria(@RequestBody List<List<Integer>> cds) {
		List<CriteriaDetail> criteriadetails = new ArrayList<>();
		for(List<Integer> cd : cds) {
			CriteriaDetail criteriadetail = cdRepo.findByCdid(cd.get(0));
			criteriadetail.setAmount(cd.get(1));
			criteriadetails.add(criteriadetail);
		}
		cdRepo.saveAll(criteriadetails);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/delcriteria/{id}", method = RequestMethod.GET)
	public String delCriteria(@PathVariable("id") int id) {
		cdRepo.removeByCriteria(criteriaRepo.findByCriteriaid(id));
		criteriaRepo.removeByCriteriaid(id);
		return "Successful!";
	}
	
	@RequestMapping(value = "/service/gethisamount/{id}", method = RequestMethod.GET)
	public List<HistoricalAmount> getHisAmount(@PathVariable("id") int id){
		return haRepo.findByCompanyid(id);
	}
	
	@RequestMapping(value = "/service/gethisbl/{id}", method = RequestMethod.GET)
	public List<String> getHisBL(@PathVariable("id") int id){
		return haRepo.findWB(id);
	}
	
	@RequestMapping(value = "/service/hatool", method = RequestMethod.POST)
	public List<Long> historicalamounttool(@RequestBody List<List<String>> datas) {
		List<Long> result = new ArrayList<>();
		if(datas.size()==0) return result;
		if(datas.get(0).size()==3) {
			for(List<String> data : datas) {
				Dept dept = deptRepo.findByDeptid(Integer.parseInt(data.get(0)));
				BudgetLine bline = blRepo.findByBlid(Integer.parseInt(data.get(1)));
				Integer percent = Integer.parseInt(data.get(2));
				HistoricalAmount ha = haRepo.findByWbcodeAndSponsor(bline.getBlcode(), dept.getDeptcode());
				if(ha == null) result.add((long) 0);
				else result.add(ha.getAmount()*(percent/100 + 1));
			}
		}else {
			if(datas.get(0).get(2).equals("0")) {
				for(List<String> data : datas) {
					Dept dept = deptRepo.findByDeptid(Integer.parseInt(data.get(0)));
					BudgetLine bline = blRepo.findByBlid(Integer.parseInt(data.get(1)));
					Long cost = Long.parseLong(data.get(3));
					HistoricalAmount ha = haRepo.findByWbcodeAndSponsor(bline.getBlcode(), dept.getDeptcode());
					HistoricalAmount total = haRepo.findByWbcodeAndSponsor(bline.getBlcode(), "TOTAL");
					if(ha == null) result.add((long) 0);
					else if(total.getAmount()==0) result.add((long) 0);
					else result.add(cost*ha.getAmount()/total.getAmount());
				}
			}else {
				for(List<String> data : datas) {
					Dept dept = deptRepo.findByDeptid(Integer.parseInt(data.get(0)));
					//BudgetLine  bline = blRepo.findByBlid(Integer.parseInt(data.get(1)));
					Criteria crt = criteriaRepo.findByCriteriaid(Integer.parseInt(data.get(2)));
					Long cost = Long.parseLong(data.get(3));
					Integer total = 0;
					CriteriaDetail cd1 = cdRepo.findByDeptAndCriteria(dept, crt);
					List<CriteriaDetail> cds = cdRepo.findAll();
					for(CriteriaDetail cd : cds) {
						if(cd.getDept().getGroup().getCompany().getCompanyid()==dept.getGroup().getCompany().getCompanyid()) {
							total+= cd.getAmount();
						}
					}
					if(cd1 == null) {
						result.add((long) 0);
					}
					else {
						result.add((long)(cost*cd1.getAmount()/total));
					}
				}
			}
			
		}
		return result;
	}
	
	@RequestMapping(value = "/service/report", method = RequestMethod.POST)
	public String exportReport(@RequestBody List<Integer> report_dept) {
		ExportExcelService export_sv = new ExportExcelService();
		if(report_dept.get(0)==0) {  //sponsor
			report_dept.remove(0);
			List<Budget> budget = budgetRepo.findByDeptIn(deptRepo.findByDeptidIn(report_dept));
			List<BudgetDetail> bds = bdRepo.getdatagroupby(budget);
			export_sv.ReportBySponsor(bds);
		}else { 					  //control
			report_dept.remove(0);
			List<Budget> budget = budgetRepo.findByDeptIn(deptRepo.findByDeptidIn(report_dept));		
			export_sv.ReportByControl(bdRepo.findByBudgetInOrderByBgAsc(budget));
		}
		return "Successful!";
	}
	
	@RequestMapping(value = "/admin/refresh", method = RequestMethod.GET)
	@Transactional
	public String refresh() {
		mdtRepo.deleteAll();
		bdRepo.deleteAll();
		budgetRepo.deleteAll();
		haRepo.deleteAll();
		yearRepo.deleteAll();
		Calendar now = Calendar.getInstance();
		Date date = Date.valueOf((now.get(Calendar.YEAR)+1)+"-12-31");
		Year year = new Year();
		year.setYear(date);
		yearRepo.save(year);
		
		return "Refresh Successful!" + date.toString();
	}

}
