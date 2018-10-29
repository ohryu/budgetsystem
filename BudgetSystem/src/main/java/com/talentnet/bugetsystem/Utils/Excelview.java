package com.talentnet.bugetsystem.Utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.talentnet.bugetsystem.Entity.BudgetDetail;

public class Excelview extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 // change the file name
	    response.setHeader("Content-Disposition", "attachment; filename=\"summary.xls\"");
	    Format formatter = new SimpleDateFormat("dd-MM-yyyy");
	    @SuppressWarnings("unchecked")
	    List<BudgetDetail> bds = (List<BudgetDetail>) model.get("summary");

	    // create excel xls sheet
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


	    // create header row
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("Budget control Dept");
	    header.createCell(1).setCellValue("Sponsor Dept");
	    header.createCell(2).setCellValue("Budget line");
	    header.createCell(3).setCellValue("Group");
	    header.createCell(4).setCellValue("Detail");
	    header.createCell(5).setCellValue("Code");
	    header.createCell(6).setCellValue("Budget amount");
	    header.createCell(7).setCellValue("Time allocation");
	    header.createCell(8).setCellValue("Start time");
	    header.createCell(9).setCellValue("Expense");
	    
	    for (int i=0; i<10; i++){
	    	   header.getCell(i).setCellStyle(style);
	    }
	    
	    int rowCount = 1;
	    if(bds==null) return;
	    for(BudgetDetail bd : bds){
	        Row userRow =  sheet.createRow(rowCount++);
	        userRow.createCell(0).setCellValue(bd.getBudget().getDept().getDeptname());
	        userRow.createCell(1).setCellValue(bd.getDept().getDeptname());
	        userRow.createCell(2).setCellValue(bd.getBg().getWb().getBline().getBlcode());
	        if(bd.getBg() == null) {
	        	userRow.createCell(3).setCellValue("NEW");
	        	userRow.createCell(4).setCellValue(bd.getNewdetail());
	        	userRow.createCell(5).setCellValue("NEW");
	        }else {	
	        	userRow.createCell(3).setCellValue(bd.getBg().getWb().getWbname());
	        	userRow.createCell(4).setCellValue(bd.getBg().getBgname());
	        	userRow.createCell(5).setCellValue(bd.getDept().getDeptcode()+"-"+bd.getBudget().getDept().getDeptcode()+"-"+
	        									    bd.getBg().getWb().getWbcode()+"-"+bd.getBg().getBgcode());
	        }
	        userRow.createCell(6).setCellValue(bd.getAmount());
	        userRow.createCell(7).setCellValue(formatter.format(bd.getAllocationtime()));
	        userRow.createCell(8).setCellValue(formatter.format(bd.getStarttime()));
	        userRow.createCell(9).setCellValue(bd.getExpense());
	        for (int i=0; i<10; i++){
	        	sheet.autoSizeColumn(i);
		    	userRow.getCell(i).setCellStyle(style);
		    }
	    }

	}
}
