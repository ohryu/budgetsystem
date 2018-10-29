package com.talentnet.bugetsystem.DTO;

import java.util.List;

public class UserDTO {
	private String username;
	private String fullname;
	private List<SysRoleDTO> role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public List<SysRoleDTO> getRole() {
		return role;
	}
	public void setRole(List<SysRoleDTO> role) {
		this.role = role;
	}
	
	
}
