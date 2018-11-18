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
	Dept findByDeptname(String name);
	Dept findByDeptcode(String code);
	List<Dept> findByGroup(Group group);
	List<Dept> findByDeptidIn(List<Integer> id);
	@Transactional
	void removeByDeptid(Integer id);
}
