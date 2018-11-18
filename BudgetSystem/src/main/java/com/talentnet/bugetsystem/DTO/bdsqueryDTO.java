package com.talentnet.bugetsystem.DTO;

public class bdsqueryDTO {
	private Long amount;
	private String deptcode;
	private String blname;
	private String blcode;
	
	
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getBlname() {
		return blname;
	}
	public void setBlname(String blname) {
		this.blname = blname;
	}
	public String getBlcode() {
		return blcode;
	}
	public void setBlcode(String blcode) {
		this.blcode = blcode;
	}
	
	public bdsqueryDTO(Long amount, String deptcode, String blname, String blcode) {
		this.amount = amount;
		this.deptcode = deptcode;
		this.blname = blname;
		this.blcode = blcode;
	}
	
	
}
