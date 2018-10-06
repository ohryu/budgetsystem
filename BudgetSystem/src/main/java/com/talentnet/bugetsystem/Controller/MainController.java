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
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Role;
import com.talentnet.bugetsystem.Entity.UserRole;
import com.talentnet.bugetsystem.Entity.Wb;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
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
	

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String loginPage(Model model) {
		/*BUser user = new BUser();
		user.setUsername("luu@gmail.com");
		user.setPassword(passwordEncoder.encode("123"));
		user.setRole(roleRepo.findByRoleid(2));
		user.setFullname("Luu Repo");
		user.setActive(true);
		userRepo.save(user);*/
		return "login";
	}
	
	/*@RequestMapping(value = "/user/dept", method = RequestMethod.GET)
	public String userHome(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
		List<UserRole> userrole = userroleRepo.findByUser(userRepo.findByUsername(principal.getName()));
		List<List<String>> reviewer = new ArrayList<>();
		List<List<String>> reporter = new ArrayList<>();
		for(UserRole urole : userrole) {
			List<String> ur = new ArrayList<>();
			if(urole.getGroup()!=null) {
				ur.add(urole.getGroup().getCompany().getCompany_name());
				ur.add(urole.getGroup().getCompany().getCompany_id().toString());
				reviewer.add(ur);
			}else {
				ur.add(urole.getDept().getGroup().getCompany().getCompany_name());
				ur.add(urole.getDept().getDept_name());
				ur.add(urole.getDept().getDept_id().toString());
				reporter.add(ur);
			}
		}
		model.addAttribute("reviewer", reviewer);
		model.addAttribute("reporter", reporter);
		return "user_dept";
	}*/
	
	@RequestMapping(value = "/admin/summary", method = RequestMethod.GET)
	public String adminHome(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userRepo.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
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
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(2));
		model.addAttribute("roles", userrole);
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
		List<Dept> depts = new ArrayList<>();
		List<List<Dept>> groups = new ArrayList<>();
		List<UserRole> userrole = userroleRepo.findByUserAndSysrole(userRepo.findByUsername(principal.getName()), sysroleRepo.findByRoleid(1));
		for(UserRole ur : userrole) {
			depts = deptRepo.findByGroupAndControl(ur.getGroup(), true);
			groups.add(depts);
		}
		model.addAttribute("roles", groups);
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
