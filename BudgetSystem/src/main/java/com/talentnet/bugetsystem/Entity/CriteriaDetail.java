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
@Table(name = "BCRITERIADETAIL")
public class CriteriaDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cd_id;
	
	@ManyToOne
	@JoinColumn(name = "CRITERIA_ID")
	private Criteria criteria;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Dept dept;
	
	@Column(name = "AMOUNT")
	private Integer amount;

	public Integer getCd_id() {
		return cd_id;
	}

	public void setCd_id(Integer cd_id) {
		this.cd_id = cd_id;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
