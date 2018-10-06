package com.talentnet.bugetsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;

@Repository
public interface BudgetlineRepo extends JpaRepository<BudgetLine, Integer>{
	BudgetLine findByBlid(Integer blid);
}
