package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.BudgetLine;


public class BlineDTO {
	private BudgetLine bline;
	private List<WbDTO> wblist;
	public BudgetLine getBline() {
		return bline;
	}
	public void setBline(BudgetLine bline) {
		this.bline = bline;
	}
	public List<WbDTO> getWblist() {
		return wblist;
	}
	public void setWblist(List<WbDTO> wblist) {
		this.wblist = wblist;
	}
	
	
}
