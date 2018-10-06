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
@Table(name = "BBUDGET")
public class Budget implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BUDGET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer budgetid;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Dept dept;
	
	@Column(name = "STATUS")
	private boolean status;

	public Integer getBudgetid() {
		return budgetid;
	}

	public void setBudgetid(Integer budgetid) {
		this.budgetid = budgetid;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
