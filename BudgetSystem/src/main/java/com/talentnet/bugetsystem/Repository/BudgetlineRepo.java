package com.talentnet.bugetsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Dept;

@Repository
public interface BudgetlineRepo extends JpaRepository<BudgetLine, Integer>{
	BudgetLine findByBlid(Integer blid);
	List<BudgetLine> findByDept(Dept dept);
}
