package com.talentnet.bugetsystem.DTO;

public class bdsqueryDTO {
	private Long amount;
	private String deptcode;
	private String blname;
	private String blcode;
	private String bltype;
	
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
	
	public String getBltype() {
		return bltype;
	}
	public void setBltype(String bltype) {
		this.bltype = bltype;
	}
	public bdsqueryDTO(Long amount, String deptcode, String blname, String blcode, String bltype) {
		this.amount = amount;
		this.deptcode = deptcode;
		this.blname = blname;
		this.blcode = blcode;
		this.bltype = bltype;
	}
	
	
}
