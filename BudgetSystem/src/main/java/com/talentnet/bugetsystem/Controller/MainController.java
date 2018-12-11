package com.talentnet.bugetsystem.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.Utils.WebUtils;
import com.talentnet.bugetsystem.DTO.CompanyDTO;
import com.talentnet.bugetsystem.DTO.GroupDTO;
import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.Criteria;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.UserRole;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.CompanyRepo;
import com.talentnet.bugetsystem.Repository.CriteriaRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.GroupRepo;
import com.talentnet.bugetsystem.Repository.RoleRepo;
import com.talentnet.bugetsystem.Repository.SystemroleRepo;
import com.talentnet.bugetsystem.Repository.UserRepo;
import com.talentnet.bugetsystem.Repository.UserroleRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;
import com.talentnet.bugetsystem.Repository.YearRepo;

@Controller
public class MainController {
	@Autowired UserRepo userRepo;
	@Autowired RoleRepo roleRepo;
	@Autowired UserroleRepo userroleRepo;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired WbRepo wbRep;
	@Autowired BudgetlineRepo blRep;
	@Autowired SystemroleRepo sysroleRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired GroupRepo groupRepo;
	@Autowired CompanyRepo companyRepo;
	@Autowired CriteriaRepo criteriaRepo;
	@Autowired YearRepo yearRepo;

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/user/summary", method = RequestMethod.GET)
	public String usersummary(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<UserRole> userrole = userroleRepo.findByUser(userRepo.findByUsername(principal.getName()));
		boolean isReviewer = false;
		boolean isReporter = false;
		for(UserRole urole : userrole) {
			if(urole.getDept()!=null) isReporter = true;
			if(urole.getGroup()!=null) isReviewer = true;
			if(isReporter && isReviewer) break;
		}
		if(!isReporter) return "redirect:/user/summary/review";
		else if(!isReviewer) return "redirect:/user/summary/report";
		else return "select_role";
	}
	
	@RequestMapping(value = "/admin/summary", method = RequestMethod.GET)
	public String adminHome(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		model.addAttribute("userInfo", userInfo);
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<CompanyDTO> companydtos = new ArrayList<>();
		List<Company> companies = companyRepo.findAll();
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
		
		model.addAttribute("companydepts", companydtos);
		model.addAttribute("date", yearRepo.findAll().get(0));
		List<Criteria> criterias = criteriaRepo.findAll();
		model.addAttribute("criterias", criterias);
		return "summary";
	}
	
	@RequestMapping(value = "/admin/histoticalamount", method = RequestMethod.GET)
	public String adminHistory(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<Company> companies = companyRepo.findAll();
		model.addAttribute("companies", companies);
		model.addAttribute("userInfo", userInfo);
		List<Criteria> criterias = criteriaRepo.findAll();
		model.addAttribute("criterias", criterias);
		return "admin_historical_amount";
	}
	
	@RequestMapping(value = "/user/histoticalamount", method = RequestMethod.GET)
	public String userHistory(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<Company> companies = companyRepo.findAll();
		model.addAttribute("companies", companies);
		model.addAttribute("userInfo", userInfo);
		List<Criteria> criterias = criteriaRepo.findAll();
		model.addAttribute("criterias", criterias);
		return "admin_historical_amount";
	}
	
	@RequestMapping(value = "/user/summary/report", method= RequestMethod.GET)
	public String report_summary(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("REPORTER");
		model.addAttribute("userInfo", userInfo);
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<CompanyDTO> companydtos = new ArrayList<>();
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(2));
		List<Company> companies = new ArrayList<>();
		List<Group> groups = new ArrayList<>();
		for(UserRole ur : userrole) {
			if(!companies.contains(ur.getDept().getGroup().getCompany())) companies.add(ur.getDept().getGroup().getCompany());
			if(!groups.contains(ur.getDept().getGroup())) groups.add(ur.getDept().getGroup());
		}
		for(Company company : companies) {
			CompanyDTO companydto = new CompanyDTO();
			List<GroupDTO> groupdtos = new ArrayList<>();
			List<Group> groups1 = new ArrayList<>();
			for(Group group : groups) {
				if(group.getCompany().getCompanyid()==company.getCompanyid()) {
					groups1.add(group);
				}
			}
			for(Group group : groups1) {
				GroupDTO groupdto = new GroupDTO(); 
				List<Dept> depts = new ArrayList<>();
				for(UserRole ur : userrole) {
					if(ur.getDept().getGroup()==group) {
						depts.add(ur.getDept());
					}
				}
				groupdto.setGroup(group);
				groupdto.setDeptlist(depts);
				groupdtos.add(groupdto);
			}
			companydto.setCompany(company);
			companydto.setGrouplist(groupdtos);
			companydtos.add(companydto);
		}
		model.addAttribute("companydepts", companydtos);
		List<Criteria> criterias = criteriaRepo.findAll();
		model.addAttribute("criterias", criterias);
		model.addAttribute("date", yearRepo.findAll().get(0));
		return "summary";
	}
	
	@RequestMapping(value = "/user/summary/review", method= RequestMethod.GET)
	public String review_summary(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("REVIEWER");
		model.addAttribute("userInfo", userInfo);
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<CompanyDTO> companydtos = new ArrayList<>();
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(1));
		List<Company> companies = new ArrayList<>();	
		List<Group> groups = new ArrayList<>();
		for(UserRole ur : userrole) {
			if(!companies.contains(ur.getGroup().getCompany())) companies.add(ur.getGroup().getCompany());
			groups.add(ur.getGroup());
		}
		for(Company company : companies) {
			CompanyDTO companydto = new CompanyDTO();
			List<GroupDTO> groupdtos = new ArrayList<>();
			List<Group> groups1 = new ArrayList<>();
			for(Group group : groups) {
				if(group.getCompany().getCompanyid()==company.getCompanyid()) {
					groups1.add(group);
				}
			}
			for(Group group : groups1) {
				GroupDTO groupdto = new GroupDTO(); 
				List<Dept> depts = deptRepo.findByGroupAndControl(group, true);
				groupdto.setGroup(group);
				groupdto.setDeptlist(depts);
				groupdtos.add(groupdto);
			}
			companydto.setCompany(company);
			companydto.setGrouplist(groupdtos);
			companydtos.add(companydto);
		}
		model.addAttribute("companydepts", companydtos);
		List<Criteria> criterias = criteriaRepo.findAll();
		model.addAttribute("criterias", criterias);
		model.addAttribute("date", yearRepo.findAll().get(0));
		return "summary";
	}
	@RequestMapping(value = "/admin/account", method = RequestMethod.GET)
	public String accountManage(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<UserRole> accounts = userroleRepo.findAll();
		model.addAttribute("accounts", accounts);
		return "account";
		
	}
	
	@RequestMapping(value = {"/user/password","/admin/password"}, method = RequestMethod.GET)
	public String resetPasswordPage(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("");
		model.addAttribute("userInfo", userInfo);
		return "password";
	}
	
	@RequestMapping(value = "/admin/dept", method = RequestMethod.GET)
	public String manageDept(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		model.addAttribute("userInfo", userInfo);
		return "admin_dept";
	}
	
	@RequestMapping(value = "/admin/budgetline-dept", method = RequestMethod.GET)
	public String manageBline_dept(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		model.addAttribute("userInfo", userInfo);
		return "admin_bline_dept";
	}
	
	@RequestMapping(value = "/admin/budgetline", method = RequestMethod.GET)
	public String manageBudgetline(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		model.addAttribute("userInfo", userInfo);
		return "admin_bline";
	}
	@RequestMapping(value = "/admin/criteria", method = RequestMethod.GET)
	public String manageCriteria(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		List<Company> companies = companyRepo.findAll();
		model.addAttribute("companies", companies);
		model.addAttribute("userInfo", userInfo);
		return "admin_criteria";
	}
	
	@RequestMapping(value = "/admin/report", method = RequestMethod.GET)
	public String adminReport(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");	
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		model.addAttribute("userInfo", userInfo);
		return "report";
	}
	
	@RequestMapping(value = "/user/report", method = RequestMethod.GET)
	public String userReport(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		userInfo.add("NOT");
		if(!userRepo.findByUsername(userName).isActive()) {
			return "password";
		}
		model.addAttribute("userInfo", userInfo);
		return "report";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}
	
}
