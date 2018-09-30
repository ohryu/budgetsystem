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
public class bbudget implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BUDGET_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer budget_id;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private bdept dept;
	
	@Column(name = "STATUS")
	private boolean status;

	public Integer getBudget_id() {
		return budget_id;
	}

	public void setBudget_id(Integer budget_id) {
		this.budget_id = budget_id;
	}

	public bdept getDept() {
		return dept;
	}

	public void setDept(bdept dept) {
		this.dept = dept;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
