package com.talentnet.bugetsystem.DTO;

import java.util.Date;

public class BudgetError {
	private String controldept;
	private String sponsordept;
	private String budgetline;
	private String group;
	private String detail;
	private String code;
	private Long amount;
	private int allocation;
	private Date starttime;
	private Long expense;
	private String note;
	public String getControldept() {
		return controldept;
	}
	public void setControldept(String controldept) {
		this.controldept = controldept;
	}
	public String getSponsordept() {
		return sponsordept;
	}
	public void setSponsordept(String sponsordept) {
		this.sponsordept = sponsordept;
	}
	public String getBudgetline() {
		return budgetline;
	}
	public void setBudgetline(String budgetline) {
		this.budgetline = budgetline;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public int getAllocation() {
		return allocation;
	}
	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Long getExpense() {
		return expense;
	}
	public void setExpense(Long expense) {
		this.expense = expense;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
