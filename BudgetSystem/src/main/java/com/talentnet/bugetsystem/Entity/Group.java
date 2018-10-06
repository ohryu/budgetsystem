package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BGROUP")
public class Group implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GROUP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupid;
	
	@Column(name = "GROUP_CODE")
	private String groupcode;
	
	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
