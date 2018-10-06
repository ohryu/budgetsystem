package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BCOMPANY")
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "COMPANY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyid;

	@Column(name = "COMPANY_NAME")
	private String companyname;

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	
}
