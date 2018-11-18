package com.talentnet.bugetsystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPONSOR_REPORT")
public class SponsorReport {
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "AMOUNT")
	private Long amount;
	
	@Column(name = "DEPT_ID")
	private Integer deptid;
	
	@Column(name = "DEPT_CODE")
	private String deptcode;
	
	@Column(name = "BL_ID")
	private Integer blid;
	
	@Column(name = "BL_NAME")
	private String blname;
	
	@Column(name = "BL_CODE")
	private String blcode;
	
	@Column(name = "BUDGET_ID")
	private Integer budget;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public Integer getBlid() {
		return blid;
	}

	public void setBlid(Integer blid) {
		this.blid = blid;
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

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	
	
}
