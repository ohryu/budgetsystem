package com.talentnet.bugetsystem.Service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Utils.Report_controldept;
import com.talentnet.bugetsystem.Utils.Report_sponsordept;

public class ExportExcelService {
	public ModelAndView ReportByControl(List<BudgetDetail> bds) {
	    return new ModelAndView(new Report_controldept(), "summary", bds);
	}
	
	public ModelAndView ReportBySponsor(List<BudgetDetail> bds) {
	    return new ModelAndView(new Report_sponsordept(), "summary", bds);
	}
}
