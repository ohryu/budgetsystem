package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.MoreDetail;
import com.talentnet.bugetsystem.Entity.BudgetDetail;

public class BudgetDetailDTO {
	private BudgetDetail bd;
	List<MoreDetail> detailList;
	
	public BudgetDetail getBd() {
		return bd;
	}
	public void setBd(BudgetDetail bd) {
		this.bd = bd;
	}
	public List<MoreDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<MoreDetail> detailList) {
		this.detailList = detailList;
	}
	
	public BudgetDetailDTO(BudgetDetail bd, List<MoreDetail> detailList) {
		this.bd = bd;
		this.detailList = detailList;
	}
	
}
