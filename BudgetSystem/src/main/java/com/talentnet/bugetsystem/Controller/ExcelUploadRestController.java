package com.talentnet.bugetsystem.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.talentnet.bugetsystem.DTO.BudgetError;
import com.talentnet.bugetsystem.Entity.Bg;
import com.talentnet.bugetsystem.Entity.Budget;
import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.BudgetLine;
import com.talentnet.bugetsystem.Entity.Criteria;
import com.talentnet.bugetsystem.Entity.CriteriaDetail;
import com.talentnet.bugetsystem.Entity.Dept;
import com.talentnet.bugetsystem.Entity.HistoricalAmount;
import com.talentnet.bugetsystem.Repository.BgRepo;
import com.talentnet.bugetsystem.Repository.BudgetDetailRepo;
import com.talentnet.bugetsystem.Repository.BudgetRepo;
import com.talentnet.bugetsystem.Repository.BudgetlineRepo;
import com.talentnet.bugetsystem.Repository.CriteriaDetailRepo;
import com.talentnet.bugetsystem.Repository.CriteriaRepo;
import com.talentnet.bugetsystem.Repository.DeptRepo;
import com.talentnet.bugetsystem.Repository.HistoricalAmountRepo;
import com.talentnet.bugetsystem.Repository.WbRepo;

@Controller
public class ExcelUploadRestController {
	@Autowired
	HistoricalAmountRepo haRepo;
	@Autowired
	DeptRepo deptRepo;
	@Autowired
	BudgetlineRepo blRepo;
	@Autowired
	BudgetRepo budgetRepo;
	@Autowired
	BgRepo bgRepo;
	@Autowired
	WbRepo wbRepo;
	@Autowired
	BudgetDetailRepo bdRepo;
	@Autowired
	CriteriaRepo criteriaRepo;
	@Autowired
	CriteriaDetailRepo cdRepo;

	@PostMapping("/admin/uploadfile") // upload histotical amount
	public String uploadFile(Model model, MultipartFile file, Integer company) throws IOException {
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		List<String> sponsor = new ArrayList<>();
		List<HistoricalAmount> haList = new ArrayList<>();
		String blname;
		String blcode;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			blname = row.getCell(0).toString();
			blcode = row.getCell(1).toString();
			for (int j = 2; j < row.getLastCellNum(); j++) {
				if (i == 0) {
					sponsor.add(row.getCell(j).toString());
				} else {
					HistoricalAmount ha = new HistoricalAmount();
					ha.setBlname(blname);
					ha.setBlcode(blcode);
					ha.setSponsor(sponsor.get(j - 2));
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

	@PostMapping("/service/uploadsummary") // upload budget
	@Transactional
	public String uploadSummary(Model model, MultipartFile file, String sysrole, String deptcode,
			RedirectAttributes redirectattr) throws IOException {
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;

		List<BudgetDetail> bdList = new ArrayList<>();
		String control_dept;
		String sponsor_dept;
		String bline;
		String group;
		String bg;
		Long amount;
		int allocation_time;
		Date start_time;
		Long expense;

		String errors = "";

		Budget budget = budgetRepo.findByDept(deptRepo.findByDeptcode(deptcode));
		if (budget == null) {
			budget = new Budget();
			budget.setDept(deptRepo.findByDeptcode(deptcode));
			if (sysrole.equals("NOT"))
				budget.setStatus(2);
			else if (sysrole.equals("REPORTER"))
				budget.setStatus(0);
			else
				budget.setStatus(1);
			budgetRepo.save(budget);
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String note = "";
			row = (Row) sheet.getRow(i);

			control_dept = row.getCell(0).toString();
			sponsor_dept = row.getCell(1).toString();
			bline = row.getCell(2).toString();
			group = row.getCell(3).toString();
			bg = row.getCell(4).toString();
			amount = Math.round(row.getCell(6).getNumericCellValue());
			allocation_time = Math.round(Float.parseFloat(row.getCell(7).toString()));
			start_time = row.getCell(8).getDateCellValue();
			expense = Math.round(row.getCell(9).getNumericCellValue());

			Dept sdept = deptRepo.findByDeptcode(sponsor_dept);
			Bg detail = bgRepo.findByWbAndBgcode(wbRepo.findByWbcode(group), bg);
			BudgetLine bl = blRepo.findByBlcode(bline);

			if (!control_dept.equals(deptcode))
				note += "Wrong control dept. ";
			if (sdept == null)
				note += "Sponsor dept not correct. ";
			if (bl == null)
				note += "-Budget line not correct. ";
			if (!group.trim().toUpperCase().equals("NEW") && detail == null)
				note += "-Detail not correct. ";

			if (note.length() == 0) {
				BudgetDetail bd = new BudgetDetail();
				bd.setBudget(budget);
				bd.setDept(sdept);
				bd.setBline(bl);
				if (group.trim().toUpperCase().equals("NEW")) {
					bd.setNewdetail(bg);
				} else {
					bd.setBg(detail);
				}
				bd.setAllocationtime(allocation_time);
				bd.setStarttime(start_time);
				bd.setAmount(amount);
				bd.setExpense(expense);
				bdList.add(bd);
			}
			else {
				errors+=("- Line "+(i+1)+": "+note+"<br>");
			}
		}
		bdRepo.saveAll(bdList);
		redirectattr.addFlashAttribute("errors", errors);
		redirectattr.addFlashAttribute("dept", deptcode);
		if (sysrole.equals("NOT"))
			return "redirect:/admin/summary";
		else if (sysrole.equals("REPORTER"))
			return "redirect:/user/summary/reporter";
		else
			return "redirect:/user/summary/reviewer";
	}
	
	@PostMapping("/service/uploadcriteria") // upload criteria
	@Transactional
	public String uploadCriteria(Model model, MultipartFile file,
			RedirectAttributes redirectattr) throws IOException {
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		Row row0 = sheet.getRow(0);
		List<CriteriaDetail> cdList = new ArrayList<>();
		String errors = "";
		try {
			for(int i=1; i < row0.getLastCellNum(); i++) {
				String criterianame = row0.getCell(i).toString();
				Criteria criteria = criteriaRepo.findByCriterianame(criterianame);
				if(criteria==null) {
					errors+= "-Criteria '"+criterianame+"' not existed \n";
					continue;
				}else {
					for(int y=1; y<= sheet.getLastRowNum(); y++) {
						row = sheet.getRow(y);
						String deptcode = row.getCell(0).toString();
						Dept dept = deptRepo.findByDeptcode(deptcode);
						if(dept==null) {
							errors+="-Line "+y+": Dept code not correct.";
							continue;
						}else {
							CriteriaDetail cd = cdRepo.findByDeptAndCriteria(dept, criteria);
							cd.setAmount(((Double) row.getCell(i).getNumericCellValue()).intValue());
							cdList.add(cd);
						}
					}
				}
			}
			cdRepo.saveAll(cdList);
		}catch (RuntimeException e){
			errors = "File format error. Please check!";
		}
		redirectattr.addFlashAttribute("errors", errors);
		return "redirect:/admin/criteria";
	}
}
