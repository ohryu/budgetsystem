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
import com.talentnet.bugetsystem.DAO.RoleDAO;
import com.talentnet.bugetsystem.DAO.UserDAO;
import com.talentnet.bugetsystem.DAOImplement.RoleDAOImpl;
import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Entity.Role;

@Controller
public class MainController {
	@Autowired UserDAO userDAO;
	@Autowired RoleDAO roleDAO;
	@Autowired PasswordEncoder passwordEncoder;

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String loginPage(Model model) {
		/*BUser user = new BUser();
		user.setUsername("luu@gmail.com");
		user.setPassword(passwordEncoder.encode("123"));
		user.setRole(roleDAO.findByRoleid(2));
		user.setFullname("Luu Dao");
		user.setActive(true);
		userDAO.save(user);*/
		return "login";
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String userHome(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userDAO.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userDAO.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
		return "home";
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String adminHome(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userDAO.findByUsername(principal.getName()).getFullname());
		userInfo.add(this.userDAO.findByUsername(principal.getName()).getRole().getRolename());
		model.addAttribute("userInfo", userInfo);
		return "admin";
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
