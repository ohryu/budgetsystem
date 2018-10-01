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
@Table(name = "BDEPT")
public class Dept implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DEPT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dept_id;
	
	@Column(name = "DEPT_NAME")
	private String dept_name;
	
	@Column(name = "DEPT_CODE")
	private String dept_code;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	
	@Column(name = "SPONSOR")
	private boolean sponsor;
	
	@Column(name = "CONTROL")
	private boolean control;

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isSponsor() {
		return sponsor;
	}

	public void setSponsor(boolean sponsor) {
		this.sponsor = sponsor;
	}

	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	
}
