package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.MoreDetail;

@Repository
public interface MoreDetailRepo extends JpaRepository<MoreDetail, Integer>{
	List<MoreDetail> findByBudgetdetail(BudgetDetail bd);
	@Transactional
	void removeByBudgetdetail(BudgetDetail bd);
	
}
