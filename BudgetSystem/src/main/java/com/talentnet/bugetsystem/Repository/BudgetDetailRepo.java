package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;

@Repository
public interface BudgetDetailRepo extends JpaRepository<BudgetDetail, Integer>{
	List<BudgetDetail> findByBudget(Budget budget);
}
