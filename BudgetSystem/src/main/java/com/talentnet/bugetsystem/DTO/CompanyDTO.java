package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.Company;

public class CompanyDTO {
	private Company company;
	private List<GroupDTO> grouplist;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<GroupDTO> getGrouplist() {
		return grouplist;
	}
	public void setGrouplist(List<GroupDTO> grouplist) {
		this.grouplist = grouplist;
	}

}
