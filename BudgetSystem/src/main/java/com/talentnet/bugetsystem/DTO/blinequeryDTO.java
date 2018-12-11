package com.talentnet.bugetsystem.DTO;

public class blinequeryDTO {
	private String blcode;
	private String blname;
	private String bltype;
	public String getBlcode() {
		return blcode;
	}
	public void setBlcode(String blcode) {
		this.blcode = blcode;
	}
	public String getBlname() {
		return blname;
	}
	public void setBlname(String blname) {
		this.blname = blname;
	}
	
	public String getBltype() {
		return bltype;
	}
	public void setBltype(String bltype) {
		this.bltype = bltype;
	}
	
	public blinequeryDTO(String blcode, String blname, String bltype) {
		this.blcode = blcode;
		this.blname = blname;
		this.bltype = bltype;
	}
	
	
}
