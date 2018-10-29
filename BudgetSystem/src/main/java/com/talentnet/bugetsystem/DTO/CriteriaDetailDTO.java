package com.talentnet.bugetsystem.DTO;


import java.util.List;

import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.CriteriaDetail;

public class CriteriaDetailDTO {
	private Company company;
	private List<CriteriaDetail> cdList;
	
	public List<CriteriaDetail> getCdList() {
		return cdList;
	}
	public void setCdList(List<CriteriaDetail> cdList) {
		this.cdList = cdList;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
