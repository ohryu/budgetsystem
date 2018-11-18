package com.talentnet.bugetsystem.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.talentnet.bugetsystem.DTO.bdsqueryDTO;
import com.talentnet.bugetsystem.DTO.blinequeryDTO;
import com.talentnet.bugetsystem.DTO.deptqueryDTO;
import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.SponsorReportRepo;
import com.talentnet.bugetsystem.Utils.Report_controldept;
import com.talentnet.bugetsystem.Utils.Report_sponsordept;

@Controller
public class ExcelExportController {
	@Autowired BudgetDetailRepo bdRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired BudgetRepo budgetRepo;
	@Autowired SponsorReportRepo srRepo;
	
	@RequestMapping(value = "/service/report", method = RequestMethod.POST)
	public ModelAndView download(Model model, @RequestParam("dept") String dept) {
		List<Integer> report_dept = Arrays.asList(dept.split("\\s*,\\s*")).stream().map(Integer::parseInt).collect(Collectors.toList());
		if(report_dept.get(0)==0) {  //sponsor
			report_dept.remove(0);
			List<Integer> budgetid = budgetRepo.getBudgedIdByDept(deptRepo.findByDeptidIn(report_dept));
			List<bdsqueryDTO> bds = srRepo.getdatagroupby(budgetid);
			List<blinequeryDTO> blines = srRepo.getDistinctBline(budgetid);
		    List<deptqueryDTO> depts = srRepo.getDistinctDept(budgetid);
		    Map<String, Object> data = new HashMap<String, Object>();
		    data.put("bds", bds);
		    data.put("blines", blines);
		    data.put("depts", depts);
			return new ModelAndView(new Report_sponsordept(), "data", data);
		}else {
			report_dept.remove(0);
			List<Budget> budget = budgetRepo.findByDeptIn(deptRepo.findByDeptidIn(report_dept));
			List<BudgetDetail> bds = bdRepo.findByBudgetInOrderByBgAsc(budget);
			return new ModelAndView(new Report_controldept(), "summary", bds);
		}
	}
}
