package com.talentnet.bugetsystem.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.talentnet.bugetsystem.Entity.HistoricalAmount;

@Controller
public class ExcelRestController {
	
	@PostMapping("/admin/uploadfile")
	public String uploadFile(Model model, MultipartFile file) throws IOException{
		InputStream in = file.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row;
		List<String> sponsor= new ArrayList();
		List<HistoricalAmount> haList = new ArrayList();
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
					ha.setWbname(blname);
					ha.setWbcode(blcode);
					ha.setSponsor(sponsor.get(j-2));
					ha.setAmount((int)Double.parseDouble(row.getCell(j).toString()));
					haList.add(ha);
				}
			}
		}
		return "redirect:/admin/histoticalamount";
	}
}
