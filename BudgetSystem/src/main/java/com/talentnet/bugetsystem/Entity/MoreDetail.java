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
@Table(name = "BMOREDETAIL")
public class MoreDetail implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "MDT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mdtid;
	
	@ManyToOne
	@JoinColumn(name = "BD_ID")
	private BudgetDetail budgetdetail;
	
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "AMOUNT")
	private Long amount;

	public Integer getMdtid() {
		return mdtid;
	}

	public void setMdtid(Integer mdtid) {
		this.mdtid = mdtid;
	}

	public BudgetDetail getBudgetdetail() {
		return budgetdetail;
	}

	public void setBudgetdetail(BudgetDetail budgetdetail) {
		this.budgetdetail = budgetdetail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}

