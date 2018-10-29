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
public class Bg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bgid;
	
	@Column(name = "BG_NAME")
	private String bgname;
	
	@Column(name = "BG_CODE")
	private String bgcode;
	
	@ManyToOne
	@JoinColumn(name = "WB_ID")
	private Wb wb;

	public Integer getBgid() {
		return bgid;
	}

	public void setBgid(Integer bgid) {
		this.bgid = bgid;
	}

	public String getBgname() {
		return bgname;
	}

	public void setBgname(String bgname) {
		this.bgname = bgname;
	}

	public String getBgcode() {
		return bgcode;
	}

	public void setBgcode(String bgcode) {
		this.bgcode = bgcode;
	}

	public Wb getWb() {
		return wb;
	}

	public void setWb(Wb wb) {
		this.wb = wb;
	}
	
}
