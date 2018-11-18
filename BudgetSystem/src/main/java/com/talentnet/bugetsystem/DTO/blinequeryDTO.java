package com.talentnet.bugetsystem.DTO;

public class blinequeryDTO {
	private String blcode;
	private String blname;
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
	public blinequeryDTO(String blcode, String blname) {
		this.blcode = blcode;
		this.blname = blname;
	}
	
	
}
