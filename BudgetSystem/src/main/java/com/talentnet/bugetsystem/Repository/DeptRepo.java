package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.Group;

@Repository
public interface DeptRepo extends JpaRepository<Dept, Integer>{
	List<Dept> findByGroupAndControl(Group group, boolean control);
	List<Dept> findByGroupAndSponsor(Group group, boolean sponsor);
	List<Dept> findByControl(boolean control);
	Dept findByDeptid(Integer deptid);
	Dept findByDeptnameAndGroup(String name, Group group);
	Dept findByDeptcodeAndGroup(String code, Group group);
	List<Dept> findByGroup(Group group);
	@Transactional
	void removeByDeptid(Integer id);
}
