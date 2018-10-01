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
@Table(name = "BBLINE")
public class BudgetLine implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bl_id;

	@Column(name = "BL_CODE")
	private String bl_code;
	
	@Column(name = "BL_NAME")
	private String bl_name;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_id")
	private Dept dept;

	public Integer getBl_id() {
		return bl_id;
	}

	public void setBl_id(Integer bl_id) {
		this.bl_id = bl_id;
	}

	public String getBl_code() {
		return bl_code;
	}

	public void setBl_code(String bl_code) {
		this.bl_code = bl_code;
	}

	public String getBl_name() {
		return bl_name;
	}

	public void setBl_name(String bl_name) {
		this.bl_name = bl_name;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
}
