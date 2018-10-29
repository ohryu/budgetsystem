package com.talentnet.bugetsystem.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;

@Repository
public interface BudgetlineRepo extends JpaRepository<BudgetLine, Integer>{
	BudgetLine findByBlid(Integer blid);
	BudgetLine findByBlcode(String blcode);
	BudgetLine findByBlname(String blname);
	
	@Transactional
	void removeByBlid(Integer blid);
}
