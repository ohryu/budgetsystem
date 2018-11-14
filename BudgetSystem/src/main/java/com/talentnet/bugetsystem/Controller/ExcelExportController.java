package com.talentnet.bugetsystem.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Utils.Report_controldept;
import com.talentnet.bugetsystem.Utils.Report_sponsordept;

@Controller
public class ExcelExportController {
	@Autowired BudgetDetailRepo bdRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired BudgetRepo budgetRepo;
	
	@RequestMapping(value = "/service/report", method = RequestMethod.POST)
	public ModelAndView download(Model model, @RequestParam("dept") String dept) {
		List<Integer> report_dept = Arrays.asList(dept.split("\\s*,\\s*")).stream().map(Integer::parseInt).collect(Collectors.toList());
		if(report_dept.get(0)==0) {  //sponsor
			report_dept.remove(0);
			List<Budget> budget = budgetRepo.findByDeptIn(deptRepo.findByDeptidIn(report_dept));
			List<BudgetDetail> bds = bdRepo.getdatagroupby(budget);
			return new ModelAndView(new Report_sponsordept(), "summary", bds);
		}else {
			report_dept.remove(0);
			List<Budget> budget = budgetRepo.findByDeptIn(deptRepo.findByDeptidIn(report_dept));
			List<BudgetDetail> bds = bdRepo.findByBudgetInOrderByBgAsc(budget);
			return new ModelAndView(new Report_controldept(), "summary", bds);
		}
	}
}
