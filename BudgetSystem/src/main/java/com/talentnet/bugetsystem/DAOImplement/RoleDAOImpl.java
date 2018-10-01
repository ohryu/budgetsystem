package com.talentnet.bugetsystem.DAOImplement;

import org.springframework.beans.factory.annotation.Autowired;

import com.talentnet.bugetsystem.DAO.RoleDAO;
import com.talentnet.bugetsystem.Entity.Role;

public class RoleDAOImpl {
	@Autowired RoleDAO roleDAO;
	public Role getRole(int roleid) {
		return roleDAO.findByRoleid(roleid);
	}
}
