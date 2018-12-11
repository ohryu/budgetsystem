package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BHISAMOUNT")
public class HistoricalAmount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer haid;
	
	@Column(name = "BL_NAME")
	private String blname;

	@Column(name = "BL_CODE")
	private String blcode;
	
	@Column(name = "SPONSOR")
	private String sponsor;
	
	@Column(name = "AMOUNT")
	private Long amount;
	
	@Column(name = "COMPANYID")
	private int companyid;

	public Integer getHaid() {
		return haid;
	}

	public void setHaid(Integer haid) {
		this.haid = haid;
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

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	
	
}
