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
public class bgroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GROUP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer group_id;
	
	@Column(name = "GROUP_CODE")
	private String group_code;
	
	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private bcompany company;

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public bcompany getCompany() {
		return company;
	}

	public void setCompany(bcompany company) {
		this.company = company;
	}

}
