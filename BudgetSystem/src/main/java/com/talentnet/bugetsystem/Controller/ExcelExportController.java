package com.talentnet.bugetsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Utils.Report_controldept;

@Controller
public class ExcelExportController {
	@Autowired BudgetDetailRepo bdRepo;
	
	@RequestMapping(value = "/service/download", method = RequestMethod.GET)
	public ModelAndView download(Model model) {
	    return new ModelAndView(new Report_controldept(), "summary", bdRepo.findAllByOrderByBudgetDesc());
	}
	
	/*@RequestMapping(value = "/service/download1", method = RequestMethod.GET)
	public ModelAndView download1(Model model) {
	    return new ModelAndView(new Report_controldept(), "summary", bdRepo. ());
	}*/
}
