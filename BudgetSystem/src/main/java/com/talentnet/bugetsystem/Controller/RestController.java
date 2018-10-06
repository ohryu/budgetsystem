package com.talentnet.bugetsystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Wb;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
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
	@Autowired BudgetlineRepo blRep;
	
	@RequestMapping(value = "/admin/getwbbybl/{bline}", method = RequestMethod.GET)
	public List<Wb> getWbByBl(@PathVariable("bline") int bline) {
		BudgetLine bl = blRep.findByBlid(bline);
		return wbRep.findByBline(bl);
	}
}
