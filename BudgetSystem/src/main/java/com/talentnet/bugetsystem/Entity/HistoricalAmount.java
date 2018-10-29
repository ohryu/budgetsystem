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
	
	@Column(name = "WB_NAME")
	private String wbname;

	@Column(name = "WB_CODE")
	private String wbcode;
	
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

	public String getWbname() {
		return wbname;
	}

	public void setWbname(String wbname) {
		this.wbname = wbname;
	}

	public String getWbcode() {
		return wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
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
