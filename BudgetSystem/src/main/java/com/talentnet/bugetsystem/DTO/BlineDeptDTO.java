package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Dept;

public class BlineDeptDTO {
	private List<BudgetLine> blinelist;
	private Dept dept;
	
	public List<BudgetLine> getBlinelist() {
		return blinelist;
	}
	public void setBlinelist(List<BudgetLine> blinelist) {
		this.blinelist = blinelist;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}	
}
