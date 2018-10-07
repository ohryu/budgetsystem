package com.talentnet.bugetsystem.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.Utils.WebUtils;
import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.Role;
import com.talentnet.bugetsystem.Entity.UserRole;
import com.talentnet.bugetsystem.Entity.Wb;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.CompanyRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.GroupRepo;
import com.talentnet.bugetsystem.Repository.RoleRepo;
import com.talentnet.bugetsystem.Repository.SystemroleRepo;
import com.talentnet.bugetsystem.Repository.UserRepo;
import com.talentnet.bugetsystem.Repository.UserroleRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;

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
		List<List<List<Dept>>> companydept = new ArrayList<>();
		List<List<Dept>> groupdept = new ArrayList<>();
		List<Company> companies = companyRepo.findAll();
		for(Company company : companies) {
			List<Group> groups = groupRepo.findByCompany(company);
			for(Group group : groups) {
				List<Dept> depts = deptRepo.findByGroupAndControl(group, true);
				groupdept.add(depts);
			}
			companydept.add(groupdept);
		}
		
		model.addAttribute("companydepts", companydept);
		return "summary";
	}
	
	@RequestMapping(value = "/admin/histoticalamount", method = RequestMethod.GET)
	public String history(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
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
		List<List<List<Dept>>> companydept = new ArrayList<>();
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(2));
		List<Company> companies = new ArrayList<>();
		List<Group> groups = new ArrayList<>();
		for(UserRole ur : userrole) {
			if(!companies.contains(ur.getDept().getGroup().getCompany())) companies.add(ur.getDept().getGroup().getCompany());
			if(!groups.contains(ur.getDept().getGroup())) groups.add(ur.getDept().getGroup());
		}
		for(Company company : companies) {
			List<List<Dept>> groupdept = new ArrayList<>();
			for(Group group : groups) {
				List<Dept> dept = new ArrayList<>();
				for(UserRole ur : userrole) {
					if(ur.getDept().getGroup()==group) {
						dept.add(ur.getDept());
					}
				}
				groupdept.add(dept);
			}
			companydept.add(groupdept);
		}
		model.addAttribute("companydepts", companydept);
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
		List<List<List<Dept>>> companydept = new ArrayList<>();
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(1));
		List<Company> companies = new ArrayList<>();	
		for(UserRole ur : userrole) {
			if(!companies.contains(ur.getGroup().getCompany())) companies.add(ur.getGroup().getCompany());
		}
		for(Company company : companies) {
			List<List<Dept>> groupdept = new ArrayList<>();
			for(UserRole ur : userrole) {
				if(ur.getGroup().getCompany()==company) {
					groupdept.add(deptRepo.findByGroupAndControl(ur.getGroup(), true));
				}
			}
			companydept.add(groupdept);
		}
		model.addAttribute("companydepts", companydept);
		return "summary";
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
