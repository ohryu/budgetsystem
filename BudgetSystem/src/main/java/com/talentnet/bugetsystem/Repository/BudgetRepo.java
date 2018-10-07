package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.Dept;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Integer>{
	Budget findByDept(Dept dept);
}
