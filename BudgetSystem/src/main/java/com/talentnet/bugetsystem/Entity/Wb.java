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
	private Integer wbid;
	
	@Column(name = "WB_NAME")
	private String wbname;
	
	@Column(name = "WB_CODE")
	private String wbcode;
	
	@ManyToOne
	@JoinColumn(name = "BL_ID")
	private BudgetLine bline;

	public Integer getWbid() {
		return wbid;
	}

	public void setWbid(Integer wbid) {
		this.wbid = wbid;
	}

	public String getWbname() {
		return wbname;
	}

	public void setWbname(String wbname) {
		this.wbname = wbname;
	}

	public String getWbcode() {
		return wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}

	public BudgetLine getBline() {
		return bline;
	}

	public void setBline(BudgetLine bline) {
		this.bline = bline;
	}
	
}
