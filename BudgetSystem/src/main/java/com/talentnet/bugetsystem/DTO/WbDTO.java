package com.talentnet.bugetsystem.DTO;

import java.util.List;

import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Wb;

public class WbDTO {
	private Wb wb;
	private List<Bg> bglist;
	public Wb getWb() {
		return wb;
	}
	public void setWb(Wb wb) {
		this.wb = wb;
	}
	public List<Bg> getBglist() {
		return bglist;
	}
	public void setBglist(List<Bg> bglist) {
		this.bglist = bglist;
	}
	
	
}
