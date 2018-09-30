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
@Table(name = "BBG")
public class bbg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bg_id;
	
	@Column(name = "BG_NAME")
	private String bg_name;
	
	@Column(name = "BG_CODE")
	private Integer bg_code;
	
	@ManyToOne
	@JoinColumn(name = "WB_ID")
	private bwb wb;

	public Integer getBg_id() {
		return bg_id;
	}

	public void setBg_id(Integer bg_id) {
		this.bg_id = bg_id;
	}

	public String getBg_name() {
		return bg_name;
	}

	public void setBg_name(String bg_name) {
		this.bg_name = bg_name;
	}

	public Integer getBg_code() {
		return bg_code;
	}

	public void setBg_code(Integer bg_code) {
		this.bg_code = bg_code;
	}

	public bwb getWb() {
		return wb;
	}

	public void setWb(bwb wb) {
		this.wb = wb;
	}
	
}
