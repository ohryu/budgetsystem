package com.talentnet.bugetsystem.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BHISAMOUNT")
public class bhisamount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "WB_NAME")
	private String wb_name;
	
	@Column(name = "WB_CODE")
	private String wb_code;
	
	@Column(name = "SPONSOR")
	private String sponsor;
	
	@Column(name = "AMOUNT")
	private Integer amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
