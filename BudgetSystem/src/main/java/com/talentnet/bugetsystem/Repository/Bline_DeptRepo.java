package com.talentnet.bugetsystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.MapBline_Dept;

@Repository
public interface Bline_DeptRepo extends JpaRepository<MapBline_Dept, Integer>{
	List<MapBline_Dept> findByDept(Dept dept); 
	List<MapBline_Dept> findByBline(BudgetLine bline);
	@Transactional
	void removeByBlineAndDept(BudgetLine bline, Dept dept);
	@Transactional
	void removeByBline(BudgetLine bline);
}
