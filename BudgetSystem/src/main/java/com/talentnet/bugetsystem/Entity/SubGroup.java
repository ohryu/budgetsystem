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
@Table(name = "BSUBGROUP")
public class SubGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sg_id;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;

	@Column(name = "SG_NAME")
	private String sg_name;

	public Integer getSg_id() {
		return sg_id;
	}

	public void setSg_id(Integer sg_id) {
		this.sg_id = sg_id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getSg_name() {
		return sg_name;
	}

	public void setSg_name(String sg_name) {
		this.sg_name = sg_name;
	}

}
