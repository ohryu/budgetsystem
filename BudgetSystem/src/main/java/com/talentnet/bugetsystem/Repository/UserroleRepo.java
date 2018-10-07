package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Entity.SystemRole;
import com.talentnet.bugetsystem.Entity.UserRole;

@Repository
public interface UserroleRepo extends JpaRepository<UserRole, Integer>{
	List<UserRole> findByUserAndSysrole(BUser user, SystemRole sysrole);
	List<UserRole> findByUser(BUser user);
}
