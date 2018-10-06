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
	private Integer criteriaid;
	
	@Column(name = "CRITERIA_NAME")
	private String criterianame;

	public Integer getCriteriaid() {
		return criteriaid;
	}

	public void setCriteriaid(Integer criteriaid) {
		this.criteriaid = criteriaid;
	}

	public String getCriterianame() {
		return criterianame;
	}

	public void setCriterianame(String criterianame) {
		this.criterianame = criterianame;
	}
	
}
