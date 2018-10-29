package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.SystemRole;

@Repository
public interface SysroleRepo extends JpaRepository<SystemRole, Integer>{
	public SystemRole findByRoleid(Integer idrole);
}
