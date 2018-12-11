package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BBLINE")
public class BudgetLine implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blid;

	@Column(name = "BL_CODE")
	private String blcode;
	
	@Column(name = "BL_NAME")
	private String blname;

	@Column(name = "BL_TYPE")
	private String bltype;

	public Integer getBlid() {
		return blid;
	}

	public void setBlid(Integer blid) {
		this.blid = blid;
	}

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
}
