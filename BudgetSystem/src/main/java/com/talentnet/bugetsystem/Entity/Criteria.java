package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BCRITERIA")
public class Criteria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CRITERIA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer criteria_id;
	
	@Column(name = "CRITERIA_NAME")
	private String criteria_name;

	public Integer getCriteria_id() {
		return criteria_id;
	}

	public void setCriteria_id(Integer criteria_id) {
		this.criteria_id = criteria_id;
	}

	public String getCriteria_name() {
		return criteria_name;
	}

	public void setCriteria_name(String criteria_name) {
		this.criteria_name = criteria_name;
	}
	
}
