package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentnet.bugetsystem.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	Role findByRoleid(int roleid); 
}
