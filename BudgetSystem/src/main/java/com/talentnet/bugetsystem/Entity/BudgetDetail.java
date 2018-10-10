package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "BBDETAIL")
public class BudgetDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bdid;
	
	@ManyToOne
	@JoinColumn(name = "BUDGET_ID")
	private Budget budget;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Dept dept;
	
	@ManyToOne
	@JoinColumn(name = "BG_ID")
	private Bg bg;
	
	@ManyToOne
	@JoinColumn(name = "BL_ID")
	private BudgetLine bline;
	
	public BudgetLine getBline() {
		return bline;
	}

	public void setBline(BudgetLine bline) {
		this.bline = bline;
	}

	@Column(name = "AMOUNT")
	private Integer amount;
	
	@Column(name = "ALLOCATION_TIME")
	@Temporal(TemporalType.DATE)
	private Date allocationtime;
	
	@Column(name = "START_TIME")
	@Temporal(TemporalType.DATE)
	private Date starttime;
	
	@Column(name = "EXPENSE")
	private Integer expense;

	@Column(name = "NEW")
	private String newdetail;
	
	public String getNewdetail() {
		return newdetail;
	}

	public void setNewdetail(String newdetail) {
		this.newdetail = newdetail;
	}

	public Integer getBdid() {
		return bdid;
	}

	public void setBdid(Integer bdid) {
		this.bdid = bdid;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Bg getBg() {
		return bg;
	}

	public void setBg(Bg bg) {
		this.bg = bg;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getAllocationtime() {
		return allocationtime;
	}

	public void setAllocationtime(Date allocationtime) {
		this.allocationtime = allocationtime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Integer getExpense() {
		return expense;
	}

	public void setExpense(Integer expense) {
		this.expense = expense;
	}
	
}
