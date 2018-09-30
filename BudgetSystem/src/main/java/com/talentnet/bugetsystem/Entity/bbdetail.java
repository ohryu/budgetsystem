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
public class bbdetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bd_id;
	
	@ManyToOne
	@JoinColumn(name = "BUDGET_ID")
	private bbudget budget;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private bdept dept;
	
	@ManyToOne
	@JoinColumn(name = "BG_ID")
	private bbg bg;
	
	@Column(name = "AMOUNT")
	private Integer amount;
	
	@Column(name = "ALLOCATION_TIME")
	@Temporal(TemporalType.DATE)
	private Date allocation_time;
	
	@Column(name = "START_TIME")
	@Temporal(TemporalType.DATE)
	private Date start_time;
	
	@Column(name = "EXPENSE")
	private Integer expense;

	public Integer getBd_id() {
		return bd_id;
	}

	public void setBd_id(Integer bd_id) {
		this.bd_id = bd_id;
	}

	public bbudget getBudget() {
		return budget;
	}

	public void setBudget(bbudget budget) {
		this.budget = budget;
	}

	public bdept getDept() {
		return dept;
	}

	public void setDept(bdept dept) {
		this.dept = dept;
	}

	public bbg getBg() {
		return bg;
	}

	public void setBg(bbg bg) {
		this.bg = bg;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getAllocation_time() {
		return allocation_time;
	}

	public void setAllocation_time(Date allocation_time) {
		this.allocation_time = allocation_time;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Integer getExpense() {
		return expense;
	}

	public void setExpense(Integer expense) {
		this.expense = expense;
	}
	
}
