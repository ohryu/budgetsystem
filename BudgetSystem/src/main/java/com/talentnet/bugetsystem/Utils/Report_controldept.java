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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
	    
	    List<BudgetDetail> revbds = bds.stream().filter(temp -> temp.getBline().getBltype().equals("Revenue")).collect(Collectors.toList());
	    List<BudgetDetail> tecbds = bds.stream().filter(temp -> temp.getBline().getBltype().equals("TEC")).collect(Collectors.toList());
	    List<BudgetDetail> nonetecbds = bds.stream().filter(temp -> temp.getBline().getBltype().equals("None-TEC")).collect(Collectors.toList());

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

	    CellStyle headerstyle = workbook.createCellStyle();
	    headerstyle.setAlignment(HorizontalAlignment.CENTER);
	    headerstyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
	    headerstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerstyle.setFont(font);
	    headerstyle.setBorderBottom(BorderStyle.THIN);
	    headerstyle.setBorderTop(BorderStyle.THIN);
	    headerstyle.setBorderRight(BorderStyle.THIN);
	    headerstyle.setBorderLeft(BorderStyle.THIN);
	    
	    CellStyle totalstyle = workbook.createCellStyle();
	    totalstyle.setDataFormat(format.getFormat("#,##0"));
	    totalstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
	    totalstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    totalstyle.setFont(font);
	    totalstyle.setBorderBottom(BorderStyle.THIN);
	    totalstyle.setBorderTop(BorderStyle.THIN);
	    totalstyle.setBorderRight(BorderStyle.THIN);
	    totalstyle.setBorderLeft(BorderStyle.THIN);

	    // create header row
	    Row header0 = sheet.createRow(0);
	    header0.createCell(13).setCellValue("Expense");
	    header0.getCell(13).setCellStyle(headerstyle);
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 13, 24));
	    
	    Row header = sheet.createRow(1);
	    header.createCell(0).setCellValue("Control Dept");
	    header.createCell(1).setCellValue("Sponsor Dept");
	    header.createCell(2).setCellValue("Account code");
	    header.createCell(3).setCellValue("Account name");
	    header.createCell(4).setCellValue("Account type");
	    header.createCell(5).setCellValue("Group code");
	    header.createCell(6).setCellValue("Group name");
	    header.createCell(7).setCellValue("Detail code");
	    header.createCell(8).setCellValue("Detail name");
	    header.createCell(9).setCellValue("Budget code");
	    header.createCell(10).setCellValue("Budget amount");
	    header.createCell(11).setCellValue("Allocation time");
	    header.createCell(12).setCellValue("From");
	    header.createCell(13).setCellValue("Jan");
	    header.createCell(14).setCellValue("Feb");
	    header.createCell(15).setCellValue("Mar");
	    header.createCell(16).setCellValue("Apr");
	    header.createCell(17).setCellValue("May");
	    header.createCell(18).setCellValue("Jun");
	    header.createCell(19).setCellValue("Jul");
	    header.createCell(20).setCellValue("Aug");
	    header.createCell(21).setCellValue("Sep");
	    header.createCell(22).setCellValue("Oct");
	    header.createCell(23).setCellValue("Nov");
	    header.createCell(24).setCellValue("Dec");
	    header.createCell(25).setCellValue("Total");
	    for (int i=0; i<26; i++){
	    	   header.getCell(i).setCellStyle(headerstyle);
	    }
	    
	    int rowCount = 2;
	    if(bds==null) return;
	    List<BudgetLine> printedbd = new ArrayList<>();
	    
	    long[] revtotal = new long[14];
	    long[] tec_nonetectotal = new long[14];
	    
	    for(BudgetDetail bd : revbds){
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
		        userRow.createCell(4).setCellValue(bdtemp.getBline().getBltype());
		        if(bdtemp.getBg() == null) {
		        	userRow.createCell(5).setCellValue("NEW");
		        	userRow.createCell(6).setCellValue("NEW");
		        	userRow.createCell(7).setCellValue("NEW");
		        	userRow.createCell(8).setCellValue(bdtemp.getNewdetail());
		        	userRow.createCell(9).setCellValue("NEW");
		        }else {	
		        	userRow.createCell(5).setCellValue(bdtemp.getBg().getWb().getWbcode());
		        	userRow.createCell(6).setCellValue(bdtemp.getBg().getWb().getWbname());
		        	userRow.createCell(7).setCellValue(bdtemp.getBg().getBgcode());
		        	userRow.createCell(8).setCellValue(bdtemp.getBg().getBgname());
		        	userRow.createCell(9).setCellValue(bdtemp.getDept().getDeptcode()+"-"+bdtemp.getBudget().getDept().getDeptcode()+"-"+
		        			bdtemp.getBg().getWb().getWbcode()+"-"+bdtemp.getBg().getBgcode());
		        }
		        userRow.createCell(10).setCellValue(bdtemp.getAmount());
		        groupamount += bdtemp.getAmount();
		        userRow.createCell(11).setCellValue(bdtemp.getAllocationtime());
		        userRow.createCell(12).setCellValue(formatter.format(bdtemp.getStarttime()));
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(bdtemp.getStarttime());
		        int month = cal.get(Calendar.MONTH)+1;
		        float firstmonth = (float)(30+1-cal.get(Calendar.DAY_OF_MONTH))/(float)30;
		        long expense_per_month = (long)(bdtemp.getExpense()/(firstmonth + 12 - month));
		        userRow.createCell(12+month).setCellValue((long)(expense_per_month*firstmonth));
		        for(int i = 12; i<24; i++) {
		        	if(i-11 >= month) {
		        		if(i-11 == month) {
		        			userRow.createCell(i+2).setCellValue((long)(expense_per_month*firstmonth));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month*firstmonth);
		        		}
		        		else {
		        			userRow.createCell(i+2).setCellValue((long)(expense_per_month));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month);
		        		}
		        	}else {
		        		userRow.createCell(i+2);
		        	}
		        }
		        userRow.createCell(25).setCellValue(bdtemp.getExpense());
		        groupexpense += bdtemp.getExpense();
		        for (int i=0; i<26; i++){
		        	sheet.autoSizeColumn(i);
			    	userRow.getCell(i).setCellStyle(style);
			    }
		        rowCount++;
	    	}
	    	revtotal[0]+=groupamount;
	    	group.createCell(10).setCellValue(groupamount);
		    group.getCell(10).setCellStyle(style);
		    revtotal[13]+=groupexpense;
		    group.createCell(25).setCellValue(groupexpense);
		    group.getCell(25).setCellStyle(style);
		    for(int i=12; i<24; i++) {
		    	revtotal[i-11]+=group_amount_bymonth[i-12];
		     	group.createCell(i+1).setCellValue(group_amount_bymonth[i-12]);
		       	group.getCell(i+1).setCellStyle(style);
		    }
		    
	    	sheet.setRowGroupCollapsed(rowCount-temp.size()-1, false);
	    	sheet.groupRow(rowCount-temp.size(),rowCount-1);
	    	sheet.setRowSumsBelow(false);
	    }
	    for(BudgetDetail bd : tecbds){
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
		        userRow.createCell(4).setCellValue(bdtemp.getBline().getBltype());
		        if(bdtemp.getBg() == null) {
		        	userRow.createCell(5).setCellValue("NEW");
		        	userRow.createCell(6).setCellValue("NEW");
		        	userRow.createCell(7).setCellValue("NEW");
		        	userRow.createCell(8).setCellValue(bdtemp.getNewdetail());
		        	userRow.createCell(9).setCellValue("NEW");
		        }else {	
		        	userRow.createCell(5).setCellValue(bdtemp.getBg().getWb().getWbcode());
		        	userRow.createCell(6).setCellValue(bdtemp.getBg().getWb().getWbname());
		        	userRow.createCell(7).setCellValue(bdtemp.getBg().getBgcode());
		        	userRow.createCell(8).setCellValue(bdtemp.getBg().getBgname());
		        	userRow.createCell(9).setCellValue(bdtemp.getDept().getDeptcode()+"-"+bdtemp.getBudget().getDept().getDeptcode()+"-"+
		        			bdtemp.getBg().getWb().getWbcode()+"-"+bdtemp.getBg().getBgcode());
		        }
		        userRow.createCell(10).setCellValue(bdtemp.getAmount());
		        groupamount += bdtemp.getAmount();
		        userRow.createCell(11).setCellValue(bdtemp.getAllocationtime());
		        userRow.createCell(12).setCellValue(formatter.format(bdtemp.getStarttime()));
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(bdtemp.getStarttime());
		        int month = cal.get(Calendar.MONTH)+1;
		        float firstmonth = (float)(30+1-cal.get(Calendar.DAY_OF_MONTH))/(float)30;
		        long expense_per_month = (long)(bdtemp.getExpense()/(firstmonth + 12 - month));
		        userRow.createCell(12+month).setCellValue((long)(expense_per_month*firstmonth));
		        for(int i = 12; i<24; i++) {
		        	if(i-11 >= month) {
		        		if(i-11 == month) {
		        			userRow.createCell(i+2).setCellValue((long)(expense_per_month*firstmonth));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month*firstmonth);
		        		}
		        		else {
		        			userRow.createCell(i+2).setCellValue((long)(expense_per_month));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month);
		        		}
		        	}else {
		        		userRow.createCell(i+2);
		        	}
		        }
		        userRow.createCell(25).setCellValue(bdtemp.getExpense());
		        groupexpense += bdtemp.getExpense();
		        for (int i=0; i<26; i++){
		        	sheet.autoSizeColumn(i);
			    	userRow.getCell(i).setCellStyle(style);
			    }
		        rowCount++;
	    	}
	    	tec_nonetectotal[0]+=groupamount;
	    	group.createCell(10).setCellValue(groupamount);
		    group.getCell(10).setCellStyle(style);
		    tec_nonetectotal[13]+=groupexpense;
		    group.createCell(25).setCellValue(groupexpense);
		    group.getCell(25).setCellStyle(style);
		    for(int i=12; i<24; i++) {
		    	tec_nonetectotal[i-11]+=group_amount_bymonth[i-12];
		     	group.createCell(i+1).setCellValue(group_amount_bymonth[i-12]);
		       	group.getCell(i+1).setCellStyle(style);
		    }
		    
	    	sheet.setRowGroupCollapsed(rowCount-temp.size()-1, false);
	    	sheet.groupRow(rowCount-temp.size(),rowCount-1);
	    	sheet.setRowSumsBelow(false);
	    }
	    for(BudgetDetail bd : nonetecbds){
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
		        userRow.createCell(4).setCellValue(bdtemp.getBline().getBltype());
		        if(bdtemp.getBg() == null) {
		        	userRow.createCell(5).setCellValue("NEW");
		        	userRow.createCell(6).setCellValue("NEW");
		        	userRow.createCell(7).setCellValue("NEW");
		        	userRow.createCell(8).setCellValue(bdtemp.getNewdetail());
		        	userRow.createCell(9).setCellValue("NEW");
		        }else {	
		        	userRow.createCell(5).setCellValue(bdtemp.getBg().getWb().getWbcode());
		        	userRow.createCell(6).setCellValue(bdtemp.getBg().getWb().getWbname());
		        	userRow.createCell(7).setCellValue(bdtemp.getBg().getBgcode());
		        	userRow.createCell(8).setCellValue(bdtemp.getBg().getBgname());
		        	userRow.createCell(9).setCellValue(bdtemp.getDept().getDeptcode()+"-"+bdtemp.getBudget().getDept().getDeptcode()+"-"+
		        			bdtemp.getBg().getWb().getWbcode()+"-"+bdtemp.getBg().getBgcode());
		        }
		        userRow.createCell(10).setCellValue(bdtemp.getAmount());
		        groupamount += bdtemp.getAmount();
		        userRow.createCell(11).setCellValue(bdtemp.getAllocationtime());
		        userRow.createCell(12).setCellValue(formatter.format(bdtemp.getStarttime()));
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(bdtemp.getStarttime());
		        int month = cal.get(Calendar.MONTH)+1;
		        float firstmonth = (float)(30+1-cal.get(Calendar.DAY_OF_MONTH))/(float)30;
		        long expense_per_month = (long)(bdtemp.getExpense()/(firstmonth + 12 - month));
		        for(int i = 12; i<24; i++) {
		        	if(i-11 >= month) {
		        		if(i-11 == month) {
		        			userRow.createCell(i+1).setCellValue((long)(expense_per_month*firstmonth));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month*firstmonth);
		        		}
		        		else {
		        			userRow.createCell(i+1).setCellValue((long)(expense_per_month));
		        			group_amount_bymonth[i-12] += (long)(expense_per_month);
		        		}
		        	}else {
		        		userRow.createCell(i+1);
		        	}
		        }
		        userRow.createCell(25).setCellValue(bdtemp.getExpense());
		        groupexpense += bdtemp.getExpense();
		        for (int i=0; i<26; i++){
		        	sheet.autoSizeColumn(i);
			    	userRow.getCell(i).setCellStyle(style);
			    }
		        rowCount++;
	    	}
	    	tec_nonetectotal[0]+=groupamount;
	    	group.createCell(10).setCellValue(groupamount);
		    group.getCell(10).setCellStyle(style);
		    tec_nonetectotal[13]+=groupexpense;
		    group.createCell(25).setCellValue(groupexpense);
		    group.getCell(25).setCellStyle(style);
		    for(int i=12; i<24; i++) {
		    	tec_nonetectotal[i-11]+=group_amount_bymonth[i-12];
		     	group.createCell(i+1).setCellValue(group_amount_bymonth[i-12]);
		       	group.getCell(i+1).setCellStyle(style);
		    }
		    
	    	sheet.setRowGroupCollapsed(rowCount-temp.size()-1, false);
	    	sheet.groupRow(rowCount-temp.size(),rowCount-1);
	    	sheet.setRowSumsBelow(false);
	    }
	    Row profit = sheet.createRow(rowCount);
	    profit.createCell(0).setCellValue("Profit");
    	CellRangeAddress mergedCell = new CellRangeAddress(rowCount, rowCount, 0, 9);
    	sheet.addMergedRegion(mergedCell);
    	RegionUtil.setBorderBottom(1, mergedCell, sheet);
    	profit.getCell(0).setCellStyle(totalstyle);
    	profit.createCell(10).setCellValue(revtotal[0]-tec_nonetectotal[0]);
    	profit.getCell(10).setCellStyle(totalstyle);
    	sheet.autoSizeColumn(10);
    	profit.createCell(11);
    	profit.getCell(11).setCellStyle(totalstyle);
    	profit.createCell(12);
    	profit.getCell(12).setCellStyle(totalstyle);
    	for(int i=1; i<14; i++) {
    		profit.createCell(i+12).setCellValue(revtotal[i]-tec_nonetectotal[i]);
    		profit.getCell(i+12).setCellStyle(totalstyle);
    		sheet.autoSizeColumn(i+12);
    	}
    	
	}
}
