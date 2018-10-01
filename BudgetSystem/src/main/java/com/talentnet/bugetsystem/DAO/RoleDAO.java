package com.talentnet.bugetsystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentnet.bugetsystem.Entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer>{
	Role findByRoleid(int roleid); 
}
