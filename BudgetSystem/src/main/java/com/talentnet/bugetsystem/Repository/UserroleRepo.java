package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;
import com.talentnet.bugetsystem.Entity.SystemRole;
import com.talentnet.bugetsystem.Entity.UserRole;

@Repository
public interface UserroleRepo extends JpaRepository<UserRole, Integer>{
	List<UserRole> findByUserAndSysrole(BUser user, SystemRole sysrole);
	List<UserRole> findByUser(BUser user);
	List<UserRole> findByDept(Dept dept);
	List<UserRole> findByGroup(Group group);
	
	
	@Transactional
	void removeByUser(BUser user);
}
