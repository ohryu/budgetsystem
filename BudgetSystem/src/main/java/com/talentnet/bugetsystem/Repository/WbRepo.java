package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Wb;

@Repository
public interface WbRepo extends JpaRepository<Wb, Integer>{
	List<Wb> findByBline(BudgetLine bline);
}
