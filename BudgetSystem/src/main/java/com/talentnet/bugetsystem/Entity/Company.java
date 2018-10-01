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
	private Integer company_id;

	@Column(name = "COMPANY_NAME")
	private String company_name;

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
}
