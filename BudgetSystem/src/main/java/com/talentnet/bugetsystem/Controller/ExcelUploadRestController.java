package com.talentnet.bugetsystem.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.HistoricalAmount;
import com.talentnet.bugetsystem.Repository.BgRepo;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.HistoricalAmountRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;

@Controller
public class ExcelUploadRestController {
	@Autowired HistoricalAmountRepo haRepo;
	@Autowired DeptRepo deptRepo;
	@Autowired BudgetlineRepo blRepo;
	@Autowired BudgetRepo budgetRepo;
	@Autowired BgRepo bgRepo;
	@Autowired WbRepo wbRepo;
	@Autowired BudgetDetailRepo bdRepo;
	
	@PostMapping("/admin/uploadfile")
	public String uploadFile(Model model, MultipartFile file, Integer company) throws IOException{
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		List<String> sponsor= new ArrayList<>();
		List<HistoricalAmount> haList = new ArrayList<>();
		String blname;
		String blcode;
		for(int i=0; i<=sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			blname = row.getCell(0).toString();
			blcode = row.getCell(1).toString();
			for(int j=2; j<row.getLastCellNum(); j++){
				if(i==0) {
					sponsor.add(row.getCell(j).toString());
				}else {
					HistoricalAmount ha = new HistoricalAmount();
					ha.setBlname(blname);
					ha.setBlcode(blcode);
					ha.setSponsor(sponsor.get(j-2));
					ha.setAmount(Math.round(Double.parseDouble(row.getCell(j).toString())));
					ha.setCompanyid(company);
					haList.add(ha);
				}
			}
		}
		haRepo.removeByCompanyid(company);
		haRepo.saveAll(haList);
		return "redirect:/admin/histoticalamount";
	}
	
	@PostMapping("/service/uploadsummary")
	@Transactional
	public String uploadSummary(Model model, MultipartFile file, String sysrole) throws IOException{
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;

		List<BudgetDetail> bdList = new ArrayList<>();
		String control_dept = "";
		String sponsor_dept;
		String bline;
		String group;
		String bg;
		Long amount;
		int allocation_time;
		Date start_time;
		Long expense;
		
		Budget budget = new Budget();
		for(int i=1; i<=sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			if(control_dept=="") {
				control_dept = row.getCell(0).toString();
				budget = budgetRepo.findByDept(deptRepo.findByDeptcode(control_dept));
				if(budget==null) {
					budget = new Budget();
					budget.setDept(deptRepo.findByDeptcode(control_dept));
					if(sysrole.equals("NOT")) 
						budget.setStatus(2);
					else if(sysrole.equals("REPORTER")) budget.setStatus(0);
					else budget.setStatus(1);
					budgetRepo.save(budget);
				}
			}
			sponsor_dept = row.getCell(1).toString();
			bline = row.getCell(2).toString();
			group = row.getCell(3).toString();
			bg = row.getCell(4).toString();
			amount = Math.round(row.getCell(6).getNumericCellValue());
			allocation_time = Math.round(Float.parseFloat(row.getCell(7).toString()));
			start_time = row.getCell(8).getDateCellValue();
			expense = Math.round(row.getCell(9).getNumericCellValue());
			
			BudgetDetail bd = new BudgetDetail();
			bd.setBudget(budget);
			bd.setDept(deptRepo.findByDeptcode(sponsor_dept));
			bd.setBline(blRepo.findByBlcode(bline));
			if(group.trim().toUpperCase().equals("NEW")) {
				bd.setNewdetail(bg);
			}else {
				bd.setBg(bgRepo.findByWbAndBgcode(wbRepo.findByWbcode(group), bg));
			}
			bd.setAllocationtime(allocation_time);
			bd.setStarttime(start_time);
			bd.setAmount(amount);
			bd.setExpense(expense);
			bdList.add(bd);
		}
		bdRepo.saveAll(bdList);
		if(sysrole.equals("NOT")) return "redirect:/admin/summary";
		else if(sysrole.equals("REPORTER")) return "redirect:/user/summary/reporter";
		else return "redirect:/user/summary/reviewer";
	}
}
