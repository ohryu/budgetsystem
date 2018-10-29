package com.talentnet.bugetsystem.DTO;

import java.util.List;

public class BlineDeptForm {
	private Integer dept;
	private List<Integer> blinelist;
	
	public Integer getDept() {
		return dept;
	}
	public void setDept(Integer dept) {
		this.dept = dept;
	}
	public List<Integer> getBlinelist() {
		return blinelist;
	}
	public void setBlinelist(List<Integer> blinelist) {
		this.blinelist = blinelist;
	}
	
	
}
