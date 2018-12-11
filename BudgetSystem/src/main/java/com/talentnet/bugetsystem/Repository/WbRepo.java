package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Wb;

@Repository
public interface WbRepo extends JpaRepository<Wb, Integer>{
	List<Wb> findByBline(BudgetLine bline);
	Wb findByWbid(Integer id);
	Wb findByWbcode(String wbcode);
	Wb findByWbname(String wbname);
	
	@Transactional
	void removeByWbid(Integer wbid);
	@Transactional
	void removeByBline(BudgetLine bline);
}
