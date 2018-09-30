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
@Table(name = "BUSERROLE")
public class buserrole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private buser user;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private bsysrole sysrole;
	
	@ManyToOne
	@JoinColumn(name = "BCD_ID")
	private bdept dept;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private bgroup group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public buser getUser() {
		return user;
	}

	public void setUser(buser user) {
		this.user = user;
	}

	public bsysrole getSysrole() {
		return sysrole;
	}

	public void setSysrole(bsysrole sysrole) {
		this.sysrole = sysrole;
	}

	public bdept getDept() {
		return dept;
	}

	public void setDept(bdept dept) {
		this.dept = dept;
	}

	public bgroup getGroup() {
		return group;
	}

	public void setGroup(bgroup group) {
		this.group = group;
	}
	
}
