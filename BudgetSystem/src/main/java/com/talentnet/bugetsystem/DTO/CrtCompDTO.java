package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.Company;
import com.talentnet.bugetsystem.Entity.Criteria;

public class CrtCompDTO {
	private Criteria criteria;
	private List<CriteriaDetailDTO> criterialist;
	
	public List<CriteriaDetailDTO> getCriterialist() {
		return criterialist;
	}
	public void setCriterialist(List<CriteriaDetailDTO> criterialist) {
		this.criterialist = criterialist;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
}
