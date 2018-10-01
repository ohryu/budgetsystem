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
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private BUser user;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private SystemRole sysrole;
	
	@ManyToOne
	@JoinColumn(name = "BCD_ID")
	private Dept dept;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BUser getUser() {
		return user;
	}

	public void setUser(BUser user) {
		this.user = user;
	}

	public SystemRole getSysrole() {
		return sysrole;
	}

	public void setSysrole(SystemRole sysrole) {
		this.sysrole = sysrole;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
