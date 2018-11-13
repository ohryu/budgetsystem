package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.Dept;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Integer>{
	Budget findByDept(Dept dept);
	List<Budget> findByDeptIn(List<Dept> depts);
	
	@Transactional
	void removeByDept(Dept dept);
}
