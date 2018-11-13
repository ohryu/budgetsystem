package com.talentnet.bugetsystem.Utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.talentnet.bugetsystem.Entity.BudgetDetail;
import com.talentnet.bugetsystem.Entity.BudgetLine;

public class Report_controldept extends AbstractXlsView{

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
	    Row header0 = sheet.createRow(0);
	    header0.createCell(12).setCellValue("Expense");
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 12, 23));
	    
	    Row header = sheet.createRow(1);
	    header.createCell(0).setCellValue("Control Dept");
	    header.createCell(1).setCellValue("Sponsor Dept");
	    header.createCell(2).setCellValue("Account code");
	    header.createCell(3).setCellValue("Account name");
	    header.createCell(4).setCellValue("Group code");
	    header.createCell(5).setCellValue("Group name");
	    header.createCell(6).setCellValue("Detail code");
	    header.createCell(7).setCellValue("Detail name");
	    header.createCell(8).setCellValue("Budget code");
	    header.createCell(9).setCellValue("Budget amount");
	    header.createCell(10).setCellValue("Allocation time");
	    header.createCell(11).setCellValue("From");
	    header.createCell(12).setCellValue("Jan");
	    header.createCell(13).setCellValue("Feb");
	    header.createCell(14).setCellValue("Mar");
	    header.createCell(15).setCellValue("Apr");
	    header.createCell(16).setCellValue("May");
	    header.createCell(17).setCellValue("Jun");
	    header.createCell(18).setCellValue("Jul");
	    header.createCell(19).setCellValue("Aug");
	    header.createCell(20).setCellValue("Sep");
	    header.createCell(21).setCellValue("Oct");
	    header.createCell(22).setCellValue("Nov");
	    header.createCell(23).setCellValue("Dec");
	    header.createCell(24).setCellValue("Total");
	    for (int i=0; i<25; i++){
	    	   header.getCell(i).setCellStyle(style);
	    }
	    
	    int rowCount = 2;
	    if(bds==null) return;
	    List<BudgetLine> printedbd = new ArrayList<>();
	    for(BudgetDetail bd : bds){
	    	if(printedbd.contains(bd.getBline())) continue;
	    	printedbd.add(bd.getBline());
	    	List<BudgetDetail> temp = bds.stream().filter(b -> b.getBline().equals(bd.getBline())).collect(Collectors.toList());  	
	    	Row group =  sheet.createRow(rowCount);
	    	group.createCell(0).setCellValue(bd.getBline().getBlname());
	    	sheet.addMergedRegion(new CellRangeAddress(rowCount, rowCount, 0, 8));
	    	rowCount++;
	    	long groupamount = 0;
    		long[] group_amount_bymonth = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    		long groupexpense = 0;
	    	for(BudgetDetail bdtemp : temp) { 	
		        Row userRow =  sheet.createRow(rowCount);
		        userRow.createCell(0).setCellValue(bdtemp.getBudget().getDept().getDeptname());
		        userRow.createCell(1).setCellValue(bdtemp.getDept().getDeptname());
		        userRow.createCell(2).setCellValue(bdtemp.getBline().getBlcode());
		        userRow.createCell(3).setCellValue(bdtemp.getBline().getBlname());
		        if(bdtemp.getBg() == null) {
		        	userRow.createCell(4).setCellValue("NEW");
		        	userRow.createCell(5).setCellValue("NEW");
		        	userRow.createCell(6).setCellValue("NEW");
		        	userRow.createCell(7).setCellValue(bdtemp.getNewdetail());
		        	userRow.createCell(8).setCellValue("NEW");
		        }else {	
		        	userRow.createCell(4).setCellValue(bdtemp.getBg().getWb().getWbcode());
		        	userRow.createCell(5).setCellValue(bdtemp.getBg().getWb().getWbname());
		        	userRow.createCell(6).setCellValue(bdtemp.getBg().getBgcode());
		        	userRow.createCell(7).setCellValue(bdtemp.getBg().getBgname());
		        	userRow.createCell(8).setCellValue(bdtemp.getDept().getDeptcode()+"-"+bdtemp.getBudget().getDept().getDeptcode()+"-"+
		        			bdtemp.getBg().getWb().getWbcode()+"-"+bdtemp.getBg().getBgcode());
		        }
		        userRow.createCell(9).setCellValue(bdtemp.getAmount());
		        groupamount += bdtemp.getAmount();
		        userRow.createCell(10).setCellValue(bdtemp.getAllocationtime());
		        userRow.createCell(11).setCellValue(formatter.format(bdtemp.getStarttime()));
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(bdtemp.getStarttime());
		        int month = cal.get(Calendar.MONTH)+1;
		        float firstmonth = (float)(30+1-cal.get(Calendar.DAY_OF_MONTH))/(float)30;
		        long expense_per_month = (long)(bdtemp.getExpense()/(firstmonth + 12 - month));
		        userRow.createCell(11+month).setCellValue((long)(expense_per_month*firstmonth));
		        for(int i = 12; i<=23; i++) {
		        	if(i-11 >= month) {
		        		if(i-11 == month) {
		        			userRow.createCell(i).setCellValue((long)(expense_per_month*firstmonth));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month*firstmonth);
		        		}
		        		else {
		        			userRow.createCell(i).setCellValue((long)(expense_per_month));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month);
		        		}
		        	}else {
		        		userRow.createCell(i);
		        	}
		        }
		        userRow.createCell(24).setCellValue(bdtemp.getExpense());
		        groupexpense += bdtemp.getExpense();
		        for (int i=0; i<25; i++){
		        	sheet.autoSizeColumn(i);
			    	userRow.getCell(i).setCellStyle(style);
			    }
		        rowCount++;
	    	}
	    	group.createCell(9).setCellValue(groupamount);
		    group.getCell(9).setCellStyle(style);
		    group.createCell(24).setCellValue(groupexpense);
		    group.getCell(24).setCellStyle(style);
		    for(int i=12; i<24; i++) {
		     	group.createCell(i).setCellValue(group_amount_bymonth[i-12]);
		       	group.getCell(i).setCellStyle(style);
		    }
		    
	    	sheet.setRowGroupCollapsed(rowCount-temp.size()-1, false);
	    	sheet.groupRow(rowCount-temp.size(),rowCount-1);
	    	sheet.setRowSumsBelow(false);
	    }

	}
}
