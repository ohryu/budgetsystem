package com.talentnet.bugetsystem.Utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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
	    
	    List<blinequeryDTO> rev_blines = blines.stream().filter(temp -> temp.getBltype().equals("Revenue")).collect(Collectors.toList());
	    List<blinequeryDTO> tec_blines = blines.stream().filter(temp -> temp.getBltype().equals("TEC")).collect(Collectors.toList());
	    List<blinequeryDTO> nonetec_blines = blines.stream().filter(temp -> temp.getBltype().equals("None-TEC")).collect(Collectors.toList());
	    
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
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("Account");
	    sheet.autoSizeColumn(0);
	    header.getCell(0).setCellStyle(headerstyle);
	    header.createCell(1).setCellValue("Account code");
	    sheet.autoSizeColumn(1);
	    header.getCell(1).setCellStyle(headerstyle);
	    header.createCell(2).setCellValue("Account type");
	    sheet.autoSizeColumn(2);
	    header.getCell(2).setCellStyle(headerstyle);
	    int cellcount = 3;
	    int sponsordept = 0;
	    for(deptqueryDTO dept : depts) {
	    	header.createCell(cellcount).setCellValue(dept.getDeptcode());
	    	header.getCell(cellcount).setCellStyle(headerstyle);
	    	cellcount++;
	    	sponsordept++;
	    }
	    header.createCell(sponsordept+3).setCellValue("Total");
	    header.getCell(sponsordept+3).setCellStyle(headerstyle);
	    int rowcount = 1;
	    
	    long[] revtotal = new long[depts.size()+1];
	    long[] tectotal = new long[depts.size()+1];
	    long[] nonetectotal = new long[depts.size()+1];
	    
	    if(!rev_blines.isEmpty()) {
	    	 for(blinequeryDTO bline : rev_blines) {
	 	    	long total = 0;
	 	    	Row blinerow = sheet.createRow(rowcount++);
	 	    	blinerow.createCell(0).setCellValue(bline.getBlname());
	 	    	blinerow.getCell(0).setCellStyle(style);
	 	    	sheet.autoSizeColumn(0);
	 	    	blinerow.createCell(1).setCellValue(bline.getBlcode());
	 	    	blinerow.getCell(1).setCellStyle(style);
	 	    	sheet.autoSizeColumn(1);
	 	    	blinerow.createCell(2).setCellValue(bline.getBltype());
	 	    	blinerow.getCell(2).setCellStyle(style);
	 	    	sheet.autoSizeColumn(2);
	 	    	for(int i = 0; i<sponsordept; i++) {
	 	    		int a = i;
	 	    		List<bdsqueryDTO> bd =  bds.stream().filter(sr -> (sr.getBlname().equals(bline.getBlname()) && sr.getDeptcode().equals(depts.get(a).getDeptcode()))).collect(Collectors.toList());
	 	    		
	 	    		if(bd.isEmpty()) {
	 	    			blinerow.createCell(i+3).setCellValue(0);
	 	    		}
	 	    		else {
	 	    			blinerow.createCell(i+3).setCellValue(bd.get(0).getAmount());
	 	    			total+= bd.get(0).getAmount();
	 	    			revtotal[i] += bd.get(0).getAmount();
	 	    		}
	 	    		blinerow.getCell(i+3).setCellStyle(style);
	 	    		sheet.autoSizeColumn(i+3);
	 	    	}
	 	    	blinerow.createCell(sponsordept+3);
	 	    	blinerow.createCell(sponsordept+3).setCellValue(total);
	 	    	sheet.autoSizeColumn(sponsordept+3);
	 	    	blinerow.getCell(sponsordept+3).setCellStyle(style);
	 	    	revtotal[depts.size()]+=total;
	 	    }
	    	Row blinerow = sheet.createRow(rowcount++);
	    	blinerow.createCell(0).setCellValue("Total Revenue");
	    	CellRangeAddress mergedCell = new CellRangeAddress(rowcount-1, rowcount-1, 0, 2);
	    	sheet.addMergedRegion(mergedCell);
	    	RegionUtil.setBorderBottom(1, mergedCell, sheet);
	    	blinerow.getCell(0).setCellStyle(totalstyle);
	    	for(int i=0; i<revtotal.length; i++) {
	    		blinerow.createCell(i+3).setCellValue(revtotal[i]);
	    		blinerow.getCell(i+3).setCellStyle(totalstyle);
 	    		sheet.autoSizeColumn(i+3);
	    	}
	    	sheet.setRowGroupCollapsed(rowcount-1-rev_blines.size(), false);
	    	sheet.groupRow(rowcount-1-rev_blines.size(), rowcount-2);
	    }
	    
	    if(!tec_blines.isEmpty()) {
	    	 for(blinequeryDTO bline : tec_blines) {
	    		 long total = 0;
		 	    	Row blinerow = sheet.createRow(rowcount++);
		 	    	blinerow.createCell(0).setCellValue(bline.getBlname());
		 	    	blinerow.getCell(0).setCellStyle(style);
		 	    	sheet.autoSizeColumn(0);
		 	    	blinerow.createCell(1).setCellValue(bline.getBlcode());
		 	    	blinerow.getCell(1).setCellStyle(style);
		 	    	sheet.autoSizeColumn(1);
		 	    	blinerow.createCell(2).setCellValue(bline.getBltype());
		 	    	blinerow.getCell(2).setCellStyle(style);
		 	    	sheet.autoSizeColumn(2);
		 	    	for(int i = 0; i<sponsordept; i++) {
		 	    		int a = i;
		 	    		List<bdsqueryDTO> bd =  bds.stream().filter(sr -> (sr.getBlname().equals(bline.getBlname()) && sr.getDeptcode().equals(depts.get(a).getDeptcode()))).collect(Collectors.toList());
		 	    		
		 	    		if(bd.isEmpty()) {
		 	    			blinerow.createCell(i+3).setCellValue(0);
		 	    		}
		 	    		else {
		 	    			blinerow.createCell(i+3).setCellValue(bd.get(0).getAmount());
		 	    			total+= bd.get(0).getAmount();
		 	    			tectotal[i] += bd.get(0).getAmount();
		 	    		}
		 	    		blinerow.getCell(i+3).setCellStyle(style);
		 	    		sheet.autoSizeColumn(i+3);
		 	    	}
		 	    	blinerow.createCell(sponsordept+3);
		 	    	blinerow.createCell(sponsordept+3).setCellValue(total);
		 	    	sheet.autoSizeColumn(sponsordept+3);
		 	    	blinerow.getCell(sponsordept+3).setCellStyle(style);
		 	    	tectotal[depts.size()]+=total;
	 	    }
	    	Row blinerow = sheet.createRow(rowcount++);
	    	blinerow.createCell(0).setCellValue("Total TEC");
	    	CellRangeAddress mergedCell = new CellRangeAddress(rowcount-1, rowcount-1, 0, 2);
	    	sheet.addMergedRegion(mergedCell);
	    	RegionUtil.setBorderBottom(1, mergedCell, sheet);
	    	blinerow.getCell(0).setCellStyle(totalstyle);
	    	for(int i=0; i<tectotal.length; i++) {
	    		blinerow.createCell(i+3).setCellValue(tectotal[i]);
	    		blinerow.getCell(i+3).setCellStyle(totalstyle);
 	    		sheet.autoSizeColumn(i+3);
	    	}
	    	sheet.setRowGroupCollapsed(rowcount-1-tec_blines.size(), false);
	    	sheet.groupRow(rowcount-1-tec_blines.size(), rowcount-2);
	    }
	    
	    if(!nonetec_blines.isEmpty()) {
	    	 for(blinequeryDTO bline : nonetec_blines) {
	    		 long total = 0;
		 	    	Row blinerow = sheet.createRow(rowcount++);
		 	    	blinerow.createCell(0).setCellValue(bline.getBlname());
		 	    	blinerow.getCell(0).setCellStyle(style);
		 	    	sheet.autoSizeColumn(0);
		 	    	blinerow.createCell(1).setCellValue(bline.getBlcode());
		 	    	blinerow.getCell(1).setCellStyle(style);
		 	    	sheet.autoSizeColumn(1);
		 	    	blinerow.createCell(2).setCellValue(bline.getBltype());
		 	    	blinerow.getCell(2).setCellStyle(style);
		 	    	sheet.autoSizeColumn(2);
		 	    	for(int i = 0; i<sponsordept; i++) {
		 	    		int a = i;
		 	    		List<bdsqueryDTO> bd =  bds.stream().filter(sr -> (sr.getBlname().equals(bline.getBlname()) && sr.getDeptcode().equals(depts.get(a).getDeptcode()))).collect(Collectors.toList());
		 	    		
		 	    		if(bd.isEmpty()) {
		 	    			blinerow.createCell(i+3).setCellValue(0);
		 	    		}
		 	    		else {
		 	    			blinerow.createCell(i+3).setCellValue(bd.get(0).getAmount());
		 	    			total+= bd.get(0).getAmount();
		 	    			nonetectotal[i] += bd.get(0).getAmount();
		 	    		}
		 	    		blinerow.getCell(i+3).setCellStyle(style);
		 	    		sheet.autoSizeColumn(i+3);
		 	    	}
		 	    	blinerow.createCell(sponsordept+3);
		 	    	blinerow.createCell(sponsordept+3).setCellValue(total);
		 	    	sheet.autoSizeColumn(sponsordept+3);
		 	    	blinerow.getCell(sponsordept+3).setCellStyle(style);
		 	    	nonetectotal[depts.size()]+=total;
	 	    }
	    	Row blinerow = sheet.createRow(rowcount++);
	    	blinerow.createCell(0).setCellValue("Total None-TEC");
	    	CellRangeAddress mergedCell = new CellRangeAddress(rowcount-1, rowcount-1, 0, 2);
	    	sheet.addMergedRegion(mergedCell);
	    	RegionUtil.setBorderBottom(1, mergedCell, sheet);
	    	blinerow.getCell(0).setCellStyle(totalstyle);
	    	for(int i=0; i<nonetectotal.length; i++) {
	    		blinerow.createCell(i+3).setCellValue(nonetectotal[i]);
	    		blinerow.getCell(i+3).setCellStyle(totalstyle);
 	    		sheet.autoSizeColumn(i+3);
	    	}
	    	sheet.setRowGroupCollapsed(rowcount-1-nonetec_blines.size(), false);
	    	sheet.groupRow(rowcount-1-nonetec_blines.size(), rowcount-2);
	    }
	    Row blinerow = sheet.createRow(rowcount++);
    	blinerow.createCell(0).setCellValue("Profit");
    	CellRangeAddress mergedCell = new CellRangeAddress(rowcount-1, rowcount-1, 0, 2);
    	sheet.addMergedRegion(mergedCell);
    	RegionUtil.setBorderBottom(1, mergedCell, sheet);
    	blinerow.getCell(0).setCellStyle(totalstyle);
    	for(int i=0; i<nonetectotal.length; i++) {
    		blinerow.createCell(i+3).setCellValue(revtotal[i]-tectotal[i]-nonetectotal[i]);
    		blinerow.getCell(i+3).setCellStyle(totalstyle);
	    		sheet.autoSizeColumn(i+3);
    	}
	}
}
