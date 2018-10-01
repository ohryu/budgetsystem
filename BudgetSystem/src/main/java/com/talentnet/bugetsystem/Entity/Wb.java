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
@Table(name = "BWB")
public class Wb implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "WB_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wb_id;
	
	@Column(name = "WB_NAME")
	private String wb_name;
	
	@Column(name = "WB_CODE")
	private String wb_code;
	
	@ManyToOne
	@JoinColumn(name = "BL_ID")
	private BudgetLine bline;

	public Integer getWb_id() {
		return wb_id;
	}

	public void setWb_id(Integer wb_id) {
		this.wb_id = wb_id;
	}

	public String getWb_name() {
		return wb_name;
	}

	public void setWb_name(String wb_name) {
		this.wb_name = wb_name;
	}

	public String getWb_code() {
		return wb_code;
	}

	public void setWb_code(String wb_code) {
		this.wb_code = wb_code;
	}

	public BudgetLine getBline() {
		return bline;
	}

	public void setBline(BudgetLine bline) {
		this.bline = bline;
	}
	
}
