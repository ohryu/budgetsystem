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
@Table(name = "BSGDETAIL")
public class SubGroupDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SGD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sgd_id;
	
	@ManyToOne
	@JoinColumn(name = "SG_ID")
	private SubGroup subgroup;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Dept dept;

	public Integer getSgd_id() {
		return sgd_id;
	}

	public void setSgd_id(Integer sgd_id) {
		this.sgd_id = sgd_id;
	}

	public SubGroup getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(SubGroup subgroup) {
		this.subgroup = subgroup;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	
}
