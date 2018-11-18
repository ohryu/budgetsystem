package com.talentnet.bugetsystem.Utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.talentnet.bugetsystem.DTO.bdsqueryDTO;
import com.talentnet.bugetsystem.DTO.blinequeryDTO;
import com.talentnet.bugetsystem.DTO.deptqueryDTO;

public class Report_sponsordept extends AbstractXlsView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 // change the file name
	    response.setHeader("Content-Disposition", "attachment; filename=\"summary.xls\"");
	    Format formatter = new SimpleDateFormat("dd-MM-yyyy");
	    Map<String, Object> data = (Map<String, Object>) model.get("data");
	    List<bdsqueryDTO> bds = (List<bdsqueryDTO>) data.get("bds");
	    List<blinequeryDTO> blines = (List<blinequeryDTO>)data.get("blines");
	    List<deptqueryDTO> depts = (List<deptqueryDTO>)data.get("depts");
	    
	    Sheet sheet = workbook.createSheet("Summary");

	    // create style for header cells
	    CellStyle style = workbook.createCellStyle();
	    DataFormat format = workbook.createDataFormat();
	    style.setDataFormat(format.getFormat("#,##0"));
	    Font font = workbook.createFont();
	    font.setFontHeight((short) 220);
	    font.setFontName("Calibri");
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderTop(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setFont(font);
	    
	    CellStyle headerstyle = workbook.createCellStyle();
	    headerstyle.setAlignment(HorizontalAlignment.CENTER);
	    headerstyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
	    headerstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerstyle.setFont(font);
	    headerstyle.setBorderBottom(BorderStyle.THIN);
	    headerstyle.setBorderTop(BorderStyle.THIN);
	    headerstyle.setBorderRight(BorderStyle.THIN);
	    headerstyle.setBorderLeft(BorderStyle.THIN);

	    // create header row
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("Account");
	    sheet.autoSizeColumn(0);
	    header.getCell(0).setCellStyle(headerstyle);
	    header.createCell(1).setCellValue("Account code");
	    sheet.autoSizeColumn(1);
	    header.getCell(1).setCellStyle(headerstyle);
	    int cellcount = 2;
	    int sponsordept = 0;
	    for(deptqueryDTO dept : depts) {
	    	header.createCell(cellcount).setCellValue(dept.getDeptcode());
	    	header.getCell(cellcount).setCellStyle(headerstyle);
	    	cellcount++;
	    	sponsordept++;
	    }
	    header.createCell(sponsordept+2).setCellValue("Total");
	    header.getCell(sponsordept+2).setCellStyle(headerstyle);
	    int rowcount = 1;
	    for(blinequeryDTO bline : blines) {
	    	long total = 0;
	    	Row blinerow = sheet.createRow(rowcount++);
	    	blinerow.createCell(0).setCellValue(bline.getBlname());
	    	blinerow.getCell(0).setCellStyle(style);
	    	sheet.autoSizeColumn(0);
	    	blinerow.createCell(1).setCellValue(bline.getBlcode());
	    	blinerow.getCell(1).setCellStyle(style);
	    	sheet.autoSizeColumn(1);
	    	for(int i = 0; i<sponsordept; i++) {
	    		int a = i;
	    		List<bdsqueryDTO> bd =  bds.stream().filter(sr -> (sr.getBlname().equals(bline.getBlname()) && sr.getDeptcode().equals(depts.get(a).getDeptcode()))).collect(Collectors.toList());
	    		
	    		if(bd.isEmpty()) {
	    			blinerow.createCell(i+2).setCellValue(0);
	    		}
	    		else {
	    			blinerow.createCell(i+2).setCellValue(bd.get(0).getAmount());
	    			total+= bd.get(0).getAmount();
	    		}
	    		blinerow.getCell(i+2).setCellStyle(style);
	    		sheet.autoSizeColumn(i+2);
	    	}
	    	blinerow.createCell(sponsordept+2);
	    	blinerow.createCell(sponsordept+2).setCellValue(total);
	    	sheet.autoSizeColumn(sponsordept+2);
	    	blinerow.getCell(sponsordept+2).setCellStyle(style);
	    }
	}
}
