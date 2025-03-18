package com.rightprice.auth.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.Math.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ProviderCreatingFactoryBean;
import org.springframework.http.ResponseEntity;

import com.rightprice.auth.model.AppCode;
import com.rightprice.auth.model.BasicAllowance;
import com.rightprice.auth.model.BasicSalary;
import com.rightprice.auth.model.CommonCost;
import com.rightprice.auth.model.CostBreakup;
import com.rightprice.auth.model.Country;
import com.rightprice.auth.model.DealDetailsView;
import com.rightprice.auth.model.DealFixLocation;
import com.rightprice.auth.model.DealIndirectCostInputs;
import com.rightprice.auth.model.DealPricingTemplate;
import com.rightprice.auth.model.Deduction;
import com.rightprice.auth.model.Designation;
import com.rightprice.auth.model.EmpDetails;
import com.rightprice.auth.model.FPCalculationDetails;
import com.rightprice.auth.model.Lob;
import com.rightprice.auth.model.MasterAssumptions;
import com.rightprice.auth.model.MasterRate;
import com.rightprice.auth.model.MasterRole;
import com.rightprice.auth.model.MasterRoles;
import com.rightprice.auth.model.MasterTaxType;
import com.rightprice.auth.model.OffshoreMiscellaneousCost;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.RateCardLocation;
import com.rightprice.auth.model.RateCardRoleUtilization;
import com.rightprice.auth.model.RateCardYOYIncrement;
import com.rightprice.auth.model.RoleUtilizationYear;
import com.rightprice.auth.model.SDealReport;
import com.rightprice.auth.model.SearchCity;
import com.rightprice.auth.model.StaffingDetails;
import com.rightprice.auth.model.StaffingDetailsHeader;
import com.rightprice.auth.model.SubPractice;
import com.rightprice.auth.model.Vertical;
import com.rightprice.auth.model.City;
import com.rightprice.auth.model.CityFP;
import com.rightprice.auth.model.Currency;
import com.rightprice.auth.model.Deal;
import com.rightprice.auth.model.DealContraactorRole;
import com.rightprice.auth.model.FPDealRoleAndContractor;
import com.rightprice.auth.model.FPDealTower;
import com.rightprice.auth.model.FpDeal;
import com.rightprice.auth.model.FpDealRole;
import com.rightprice.auth.model.FpDealRoleExcel;
import com.rightprice.auth.model.MasterXOSkillElement;
import com.rightprice.auth.model.MstRpMasterRolesExcel;
import com.rightprice.auth.model.MyDashboardDeal;
import com.rightprice.auth.model.MyDashboardRC;
import com.rightprice.auth.model.PersistedFpDealRoles;
import com.rightprice.auth.model.Practice;
import com.rightprice.auth.model.X0KnowledgeMaster;
import com.rightprice.auth.model.XOSkillMaster;
import com.rightprice.auth.repository.RightPriceRepositoryImpl;

import sun.util.resources.cldr.aa.CalendarData_aa_ER;


public class ExcelView {
	
	public static byte[] getLobDetailsReport(List<Lob> lobDetails) {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Lob Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master LOB Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$B$1"));
		
     // Table Header
		final String[] list = {"LOB Code","LOB Description"};
		Row headerRow1 = sheet.createRow(1);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < list.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(list[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 2;
        for(int i=0;i<lobDetails.size();i++)
        {
    	  String[] lobList ={lobDetails.get(i).getLobCode(),lobDetails.get(i).getLobDescription()};
    	  Row header1 = sheet.createRow(rowCount);
    	  createDataRow(lobList,sheet,styles,header1);
    	  
    	  Cell cellI = header1.getCell(0);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  cellI = header1.getCell(1);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	

	public static byte[] getDesignationReport(List<Designation> list2) {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Designation");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Designation Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$E$1"));
		
     // Table Header
		final String[] list = {"Designation","Band","Grade","Min_Exp_Years","Max_Exp_Years"};
		Row headerRow1 = sheet.createRow(1);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < list.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(list[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 2;
        
        List<Designation> Designation = new ArrayList<Designation>();
        for(int i=0; i<list2.size(); i++)
        {
        	AppLoger.APPLOGGER.info("Designation Description............  "+ list2.get(i).getEmpDesignationDescription());
        	String strMinExp="";
        	String strMaxExp="";
        	
        	if(list2.get(i).getMinimumExperienceYears()!=null)
        	{
        	Double minExp = Double.parseDouble(new Double(list2.get(i).getMinimumExperienceYears()).toString());
        	strMinExp =  String.format ("%,.2f", minExp);
        	}

        	if(list2.get(i).getMaximumExperienceYears()!=null)
        	{
        	Double maxExp = Double.parseDouble(new Double(list2.get(i).getMaximumExperienceYears()).toString());
    	  	strMaxExp =  String.format ("%,.2f", maxExp);
        	}
        	
    		String[] designationList ={ list2.get(i).getEmpDesignationDescription(), list2.get(i).getBand().getDescription(),list2.get(i).getGrade().getDescription(),
    				strMinExp,strMaxExp};
    		Row header1 = sheet.createRow(rowCount);
    		createDataRow(designationList,sheet,styles,header1);
    	  
    		Cell cellI = header1.getCell(0);
    		cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    		cellI = header1.getCell(1);
    		cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    		cellI = header1.getCell(2);
    		cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    		cellI = header1.getCell(3);
    		cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    		cellI = header1.getCell(4);
    		cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	public static byte[] getVerticalReport(List<Vertical> list) {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Vertical");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Vertical Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
		
     // Table Header
		final String[] headerList = {"Vertical","Delivery Head","BU Head","RiskManagers Employee Head"};
		Row headerRow1 = sheet.createRow(1);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 2;
        
        for(Vertical vertical : list){
    	  String[] verticalList ={ vertical.getVerticalName(), vertical.getDUH(),vertical.getBUH()};//vertical.getRiskManagers()};
    	  Row header1 = sheet.createRow(rowCount);
    	  createDataRow(verticalList,sheet,styles,header1);
    	  
    	  Cell cellI = header1.getCell(0);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  cellI = header1.getCell(1);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  cellI = header1.getCell(2);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  cellI = header1.getCell(3);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	 
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	public static byte[] getCityReport(List<SearchCity> cityList, int countryId, int cityId, String countryName, String cityName) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		
		AppLoger.APPLOGGER.info("The Country Id is........... "+countryId);
		System.err.println("The City Id is.................  "+cityId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master City Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(1);
		titleCell.setCellValue(" Master City Details ");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$B$2:$E$2"));
		
		
		final String[] headerList1 = {"Country","City"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i+1);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =3;
		
		String [] verticalList1 = {countryName,cityName};
		Row header = sheet.createRow(rowCount1);
		createHeaderDataRow(verticalList1,sheet,styles,header);
    	rowCount1++;
		
		
     // Table Header
		final String[] headerList = {"Country","City","City Categorization","Cola Value"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 6;
        
        for(SearchCity cityDetails : cityList){
    	  String[] verticalList ={cityDetails.getCountryName(),cityDetails.getCityName(),cityDetails.getDescription(),new Integer(cityDetails.getColaValue()).toString()};
    	  Row header1 = sheet.createRow(rowCount);
    	  createDataRow(verticalList,sheet,styles,header1);
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	
	public static byte[] getVerticalMemberExcelReport(List<EmpDetails> verticalData,int verticalId) {
		
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The Vertical Id of the Vertical Member is....... "+ verticalId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Vertical Member Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Vertical Member Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
		
		
		final String[] headerList1 = {"Employee Id","Employee LAN Id","Name","Designation"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =3;
		if(verticalId !=0) {
		for(EmpDetails empdata : verticalData){
			String[] verticalList1 ={empdata.getEmployeeId(),empdata.getEmployeeLanId(),empdata.getName(),empdata.getDesignation()
	    	  };
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;
	        }
		}
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	}
	
	public static byte[] getBasicAllowanceExcelReport(List<BasicAllowance> allowanceList, int countryId, int visaId,
			int year, String countryName, String visaName) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The Country Id is....... "+ countryId);
		AppLoger.APPLOGGER.info("The Visa Id is............. "+visaId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Basic Allowance Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(2);
		titleCell.setCellValue("Master Basic Allowance Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
		
		
		final String[] headerList1 = {"Country","Visa","Year"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i+1);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =3;
		
			String[] verticalList1 ={countryName,visaName,new Integer(year).toString()};
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;
	      

		
     // Table Header
		final String[] headerList = {"Designation","Band/Grade","Low","Medium","High","Very High"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 6;
        
        for(int i=0; i<allowanceList.size(); i++)
        {
    	  Double lowAll = Double.parseDouble(allowanceList.get(i).getLowallowance());
    	  Double medll = Double.parseDouble(allowanceList.get(i).getMediumallowance());
    	  Double highAll = Double.parseDouble(allowanceList.get(i).getHighallowance());
    	  Double vHighAll = Double.parseDouble(allowanceList.get(i).getVeryhighallowance());
    	  
    	  String[] verticalList ={allowanceList.get(i).getEmpDesgmap().getDesgDesc(),
    			  allowanceList.get(i).getEmpDesgmap().getBandmap().getDescription()+"-"+allowanceList.get(i).getEmpDesgmap().getGrademap().getDescription()};
    	  
    	  Double[] onsiteVerticalDataList =
				{
					//Utilization Onsite
					new BigDecimal(lowAll).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(medll).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(highAll).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(vHighAll).setScale(2, RoundingMode.HALF_UP).doubleValue()
				};

    	  Row header = sheet.createRow(rowCount);
    	  createDataRowExcel(verticalList,onsiteVerticalDataList,sheet,styles,header);
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);			     
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	
	public static byte[] getCommonCostParametersReport(List<Deduction> commonCostList, ArrayList<CommonCost> commonCostDescData, int countryId, int year) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The Country Id is....... "+ countryId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Common Cost Parameters Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Common Cost Parameters Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$E$1"));
		
		final String[] headerList1 = {"Country","Year"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i+1);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =3;
		
		
			String[] verticalList1 ={getCountryName(countryId),new Integer(year).toString()};
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;
	      

		
     // Table Header
		final String[] headerList = {"Parameter Name","Domestic","Deputed","Short Term","Off Shore"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 6;
        
        AppLoger.APPLOGGER.info("CommonCostDesc Data...     "+commonCostDescData.size());
        AppLoger.APPLOGGER.info("Common Cost List .......... "+commonCostList.size());
        Double domestic = 0.0;
        Double deputed = 0.0;
        Double shortTerm = 0.0;
        Double offShore = 0.0;
        for(CommonCost commonCostDeduction : commonCostDescData) {
        	
        	 for(Deduction deduction : commonCostList){
    		 
        	if(deduction.getVisaTypeId() == 1 &&  commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
    			  domestic = deduction.getParDeduction();
    		  }
    		  else if(deduction.getVisaTypeId() ==2 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
    			  deputed = deduction.getParDeduction();
    		  }
    		  else if(deduction.getVisaTypeId() == 3 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
    			 shortTerm = deduction.getParDeduction();
    		  }
    		  else if(deduction.getVisaTypeId() == 4 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
    			 offShore = deduction.getParDeduction();
    		  }
    		  
    	 	}
        	 for(Deduction deduction1 : commonCostList) {
        	 String[] costParameterList ={deduction1.getDeductionTypeMap().getParamName(),new Double(domestic).toString(),new Double(deputed).toString(),
        			 new Double(shortTerm).toString(),new Double(offShore).toString()};
        	 Row header = sheet.createRow(rowCount);
        	 createDataRow(costParameterList,sheet,styles,header);
        	 rowCount++;
        	 }
     }
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	public static byte[] getTaxParametersReport(List<Deduction> commonCostList, ArrayList<MasterTaxType> commonCostDescData, int countryId, int year) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The Country Id is....... "+ countryId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
			Sheet sheet = wb.createSheet("Master Common Cost Parameters Details");
			sheet.setFitToPage(true);
			
			// create style for header cells
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
			font.setBold(true);
			font.setColor(HSSFColor.WHITE.index);
			style.setFont(font);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Master Common Cost Parameters Details");
			titleCell.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$E$1"));
			
			final String[] headerList1 = {"Country","Year"};
			Row headerRow = sheet.createRow(2);
			headerRow.setHeightInPoints(16);
			Cell headerCell;
			for (int i = 0; i < headerList1.length; i++) {
				headerCell = headerRow.createCell(i+1);
				headerCell.setCellValue(headerList1[i]);
				headerCell.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount1 =3;
			
			
			String[] verticalList1 ={getCountryName(countryId),new Integer(year).toString()};
			Row header1 = sheet.createRow(rowCount1);
			createHeaderDataRow(verticalList1,sheet,styles,header1);
			rowCount1++;
			
			
			
			// Table Header
			final String[] headerList = {"Parameter Name","Domestic","Deputed","Short Term","Off Shore"};
			Row headerRow1 = sheet.createRow(5);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList[i]);
				headerCell1.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount = 6;
			
			AppLoger.APPLOGGER.info("CommonCostDesc Data...     "+commonCostDescData.size());
			AppLoger.APPLOGGER.info("Common Cost List .......... "+commonCostList.size());
			Double domestic = 0.0;
			Double deputed = 0.0;
			Double shortTerm = 0.0;
			Double offShore = 0.0;
			for(MasterTaxType commonCostDeduction : commonCostDescData) {
				
				for(Deduction deduction : commonCostList){
					
					if(deduction.getVisaTypeId() == 1 &&  commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						domestic = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() ==2 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						deputed = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() == 3 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						shortTerm = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() == 4 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						offShore = deduction.getParDeduction();
					}
					
				}
				for(Deduction deduction1 : commonCostList) {
					String[] costParameterList ={deduction1.getTaxMap().getDescription(),new Double(domestic).toString(),new Double(deputed).toString(),
							new Double(shortTerm).toString(),new Double(offShore).toString()};
					Row header = sheet.createRow(rowCount);
					createDataRow(costParameterList,sheet,styles,header);
					rowCount++;
				}
			}
			for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
				sheet.autoSizeColumn(columnIndex);
			}
			wb.write(out);
			wb.close();
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getAssumptionsParametersReport(List<Deduction> commonCostList, ArrayList<MasterAssumptions> commonCostDescData, int countryId, int year) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The Country Id is....... "+ countryId);
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
			Sheet sheet = wb.createSheet("Master Common Cost Parameters Details");
			sheet.setFitToPage(true);
			
			// create style for header cells
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
			font.setBold(true);
			font.setColor(HSSFColor.WHITE.index);
			style.setFont(font);
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Master Common Cost Parameters Details");
			titleCell.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$E$1"));
			
			final String[] headerList1 = {"Country","Year"};
			Row headerRow = sheet.createRow(2);
			headerRow.setHeightInPoints(16);
			Cell headerCell;
			for (int i = 0; i < headerList1.length; i++) {
				headerCell = headerRow.createCell(i+1);
				headerCell.setCellValue(headerList1[i]);
				headerCell.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount1 =3;
			
			
			String[] verticalList1 ={getCountryName(countryId),new Integer(year).toString()};
			Row header1 = sheet.createRow(rowCount1);
			createHeaderDataRow(verticalList1,sheet,styles,header1);
			rowCount1++;
			
			
			
			// Table Header
			final String[] headerList = {"Parameter Name","Domestic","Deputed","Short Term","Off Shore"};
			Row headerRow1 = sheet.createRow(5);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList[i]);
				headerCell1.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount = 6;
			
			AppLoger.APPLOGGER.info("CommonCostDesc Data...     "+commonCostDescData.size());
			AppLoger.APPLOGGER.info("Common Cost List .......... "+commonCostList.size());
			Double domestic = 0.0;
			Double deputed = 0.0;
			Double shortTerm = 0.0;
			Double offShore = 0.0;
			for(MasterAssumptions commonCostDeduction : commonCostDescData) {
				
				for(Deduction deduction : commonCostList){
					
					if(deduction.getVisaTypeId() == 1 &&  commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						domestic = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() ==2 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						deputed = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() == 3 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						shortTerm = deduction.getParDeduction();
					}
					else if(deduction.getVisaTypeId() == 4 && commonCostDeduction.getCodeName() == deduction.getDeductionTypeMap().getCodeName()) {
						offShore = deduction.getParDeduction();
					}
					
				}
				for(Deduction deduction1 : commonCostList) {
					String[] costParameterList ={deduction1.getAssuMap().getDescription(),new Double(domestic).toString(),new Double(deputed).toString(),
							new Double(shortTerm).toString(),new Double(offShore).toString()};
					Row header = sheet.createRow(rowCount);
					createDataRow(costParameterList,sheet,styles,header);
					rowCount++;
				}
			}
			for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
				sheet.autoSizeColumn(columnIndex);
			}
			wb.write(out);
			wb.close();
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static byte[] getCountryForexExcelReport(List<Country> countryDetails) {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Forex Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master_Country_Forex_Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$C$1"));
		
     // Table Header
		final String[] list = {"Country","Currency_Code","Exchange_Rate (INR)","Premium"};
		Row headerRow1 = sheet.createRow(1);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < list.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(list[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
		
		
        int rowCount = 2;
        for(int i=0; i<countryDetails.size();i++)
        {
        	Double strExchangeRate = Double.parseDouble(new Double(countryDetails.get(i).getExchangeRate()).toString());
        	Double strPremium = Double.parseDouble(new Double(countryDetails.get(i).getSyntelFacilityCost()).toString());
        	String[] cntryList ={ countryDetails.get(i).getCountryName(),countryDetails.get(i).getCurrencyCode()};
      	  	Double[] cntryListData =
  				{
  					//Utilization Onsite
  					new BigDecimal(strExchangeRate).setScale(2, RoundingMode.HALF_UP).doubleValue(),
  					new BigDecimal(strPremium).setScale(2, RoundingMode.HALF_UP).doubleValue(),
  				};
      	  Row header = sheet.createRow(rowCount);
      	  createDataRowExcel(cntryList,cntryListData,sheet,styles,header);
        	rowCount++;
        }
        
	    for(int columnIndex = 0; columnIndex < 11; columnIndex++) 
	    {
	    	sheet.autoSizeColumn(columnIndex);
	    }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
		}

	public static byte[] getSubPracticeExcelReport(List<SubPractice> subPracticeData, int practiceId, String practiceName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Sub Practice Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master_Sub_Practice_Details");
		titleCell.setCellStyle(styles.get("Header"));
		//sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1"));
		
		final String[] list = {"Practice"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < list.length; i++) {
			headerCell1 = headerRow.createCell(i);
			headerCell1.setCellValue(list[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 = 3;
		String[] subPracticeHeader ={practiceName};
		Row header = sheet.createRow(rowCount1);
		createDataRow(subPracticeHeader,sheet,styles,header);
		rowCount1++;

		// Table Header
		final String[] headerList = {"Sub Practice Name"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList.length; i++) {
			headerCell = headerRow1.createCell(i);
			headerCell.setCellValue(headerList[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		// Table Header
        int rowCount = 6;
        for(SubPractice subPractice : subPracticeData){
    	  String[] subPracticeList ={ subPractice.getSubpracticeName()
    	  };
    	  Row header11 = sheet.createRow(rowCount);
    	  createDataRow(subPracticeList,sheet,styles,header11);
    	  Cell cellI = header11.getCell(0);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	public static byte[] getMiscelleneousExcelReport(List<OffshoreMiscellaneousCost> miscellenoeusData, int city) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The City Id is....... "+ city);
		
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Miscelleneous Data");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Shift Cost Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A1:$D1"));
		
		final String[] headerList1 = {"Country","City"};
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i+1);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =2;
		for(OffshoreMiscellaneousCost costHeaderDetails : miscellenoeusData){
			String[] verticalList1 = {costHeaderDetails.getMiscellaneous().getCountry().getCountryName(),costHeaderDetails.getMiscellaneous().getCityName()};
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;
		 }

     // Table Header
		final String[] headerList = {"City","Nigh Shift Cost","Night Shift Transport Cost","Second Shift Cost","Second Shift Transport Cost"};
		Row headerRow1 = sheet.createRow(4);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
 			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 5;
        
      
      for(int i=0; i<miscellenoeusData.size(); i++)
      {
    	  Double num = Double.parseDouble(new Double(miscellenoeusData.get(0).getNightShiftCost()).toString());
    	  String strNightShiftCost =  String.format ("%,.2f", num);
    	  
    	  num = Double.parseDouble(new Double(miscellenoeusData.get(0).getNightShiftTransportCost()).toString());
    	  String strNightShiftTransportCost =  String.format ("%,.2f", num);
    	  
    	  num = Double.parseDouble(new Double(miscellenoeusData.get(0).getSecondShiftCost()).toString());
    	  String strSecondShiftCost =  String.format ("%,.2f", num);
    	  
    	  num = Double.parseDouble(new Double(miscellenoeusData.get(0).getSecondShiftTransportCost()).toString());
    	  String strSecondShiftTransportCost =  String.format ("%,.2f", num);
    	  
    	  
    	  String[] costDetailList ={miscellenoeusData.get(0).getMiscellaneous().getCityName(),strNightShiftCost,strNightShiftTransportCost,strSecondShiftCost,strSecondShiftTransportCost};
    	  Row header = sheet.createRow(rowCount);
    	  createDataRow(costDetailList,sheet,styles,header);
    	  
    	  Cell cellI = header.getCell(0);
    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
    	  
    	  cellI = header.getCell(1);
    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    	  cellI = header.getCell(2);
    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    	  cellI = header.getCell(3);
    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    	  cellI = header.getCell(4);
    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
    	  
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	


	public static byte[] getTaxAssumptionExcelReport(List<Deduction> taxAssumptionData, int country,
			int deductionId) {
		
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		AppLoger.APPLOGGER.info("The City Id is....... "+ country);
		
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Tax Assumption Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("Master Tax Assumption Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
		
		final String[] headerList1 = {"Country","Tax/Assumptions"};
		Row headerRow = sheet.createRow(2);
		headerRow.setHeightInPoints(16);
		Cell headerCell;
		for (int i = 0; i < headerList1.length; i++) {
			headerCell = headerRow.createCell(i+1);
			headerCell.setCellValue(headerList1[i]);
			headerCell.setCellStyle(styles.get("Row3"));
		}
		
		int rowCount1 =3;
			String[] verticalList1 = {getCountryName(country),getDeductionType(deductionId)};
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;

     // Table Header
		final String[] headerList = {"Name","Visa","Fixed Value","Percentage Value"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
 			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 6;
        
      for(Deduction taxDeductionDetails : taxAssumptionData){
    	  String[] costDetailList ={taxDeductionDetails.getTaxMap().getDescription(),taxDeductionDetails.getVisaMap().getVisaName(),new Double(taxDeductionDetails.getCodeName()).toString(),new Double(taxDeductionDetails.getParDeduction()).toString()};
    	  Row header = sheet.createRow(rowCount);
    	  createDataRow(costDetailList,sheet,styles,header);
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	
	//Formatting Cell Style
		private static Map<String, CellStyle> createStyles(Workbook wb) {
			Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
			CellStyle style;
			Font noBold = wb.createFont();
			noBold.setBoldweight((short) 400);
			Font headerFont = wb.createFont();
			headerFont.setBoldweight((short) 700);
			
			Font headerFontRC = wb.createFont();
			headerFontRC.setBoldweight((short) 700);
			headerFontRC.setColor(IndexedColors.WHITE.getIndex());
			
			Font headerFontRC1 = wb.createFont();
			headerFontRC1.setBoldweight((short) 700);
			headerFontRC1.setColor(IndexedColors.BLACK.getIndex());
						
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("Header", style);
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFontRC);
			styles.put("rcHeader", style);
			
			//style = createUnBorderedStyle(wb);
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFontRC);
			styles.put("rcHeaderOnsiteUtil", style);
			
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillPattern((short) 1);
			style.setFont(headerFontRC1);
			styles.put("rcHeaderOnsiteUtil12", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("rcHeaderOnsiteUtil1", style);
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFontRC);
			styles.put("rcHeaderOffshoreUtil", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setWrapText(true);
			style.setFont(headerFont);
			styles.put("Row1", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("Row2", style);

			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("Row3", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);			
			styles.put("RowOffshoreRCHeader", style);
			
			style = createBorderedStyle(wb);
			/*style.setAlignment((short) 2);*/
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("RowRCHeader", style);
			
			style = createBorderedStyle(wb);
			/*style.setAlignment((short) 2);*/
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("RowRCHeaderCenterAlign", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("Row4", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("DataRowColumnLeftAlign", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("0.00"));
			styles.put("DataRowColumnLeftAlignNumber", style);
			
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("rcHeadersubTotal", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("DataRowColumnRightAlign", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("0.00"));
			styles.put("DataRowColumnRightAlignNumber", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0.00"));
			styles.put("DataRowColumnRightAlignNumberComma", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("0.00"));
			styles.put("DataRowColumnRightAlignBold", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("DataRowColumnCenterAlign", style);
	
			Font headerFontRightSub = wb.createFont();
			headerFontRightSub.setBoldweight((short) 700);
			headerFontRightSub.setColor(IndexedColors.BLACK.getIndex());
						
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFontRightSub);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("0.00"));
			styles.put("rcSubTotal", style);
			
			style = createBorderedStyle(wb);
			/*style.setAlignment((short) 2);*/
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
			HSSFColor myColor = palette.findSimilarColor(204, 255, 255);
			short palIndex = myColor.getIndex();
			style.setFillForegroundColor(palIndex);
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("RowRateCardHeader", style);
			
			style = createBorderedStyle(wb);
			/*style.setAlignment((short) 2);*/
			
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFillForegroundColor(palIndex);
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("RowRateCardHeader2", style);
			
			//Right alligned Number with bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0.00"));
			styles.put("DataRowColumnRightAlignNumberCommaBold", style);
		
			//Right alligned Number without bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat("#,##0.00"));
			styles.put("DataRowColumnRightAlignNumberComma", style);
			
			
			//Left alligned Number without bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("DataRowColumnLeftAlignBold", style);
			
			//Right alligned Number without bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat(""));
			styles.put("DataRowColumnRightAlignNum", style);
			

			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			//style.setDataFormat((short)BuiltinFormats.getBuiltinFormat(""));
			styles.put("costBreakupRGreyBold", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("costBreakupLGreyBold", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("RowColumnRightAlignNumber", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			styles.put("ColumnRightAlignNum", style);
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFont(noBold);
			styles.put("ColumnAlignLeft", style);
			
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRight", style);
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRight2Decimal", style);
			
			style = createUnBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFont(noBold);
			styles.put("ColumnAlignCenter", style);
			
			style = createTopBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopBorder", style);
			
			style = createTopBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRightTopBorder2Decimal", style);
			
			
			style = createTopThinRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopThinRightMedBorder", style);
			
			style = createTopThinRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRightTopThinRightMedBorder2Decimal", style);
			
			style = createTopHairBorderStyle(wb);						
			style.setFont(noBold);
			styles.put("TopHairBorder", style);
			
			style = createTopRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightRightTopBorder", style);
			
			style = createTopRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRightRightTopBorder2Decimal", style);
			
			
			style = createTopRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			
			style.setFont(headerFont);
			styles.put("ColumnAlignLeftTopMedRightMedLeftMedBorder", style);
			
			style = createRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightRightBorder", style);
			
			style = createRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRightRightBorder2Decimal", style);
			
			style = createLeftTopMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFont(noBold);
			styles.put("ColumnAlignLeftLeftTopBorder", style);
			
			style = createTopMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopMedBorder", style);
			
			style = createTopMedRightHairBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("TopMedRightHairBorder", style);
			
			style = createLeftMedTopHairBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			
			style.setFont(noBold);
			styles.put("ColumnAlignLeftLeftMedTopHairBorder", style);
			
			style = createTopHairRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopHairRightMedrBorder", style);
			
			style = createTopHairRightMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);			
			style.setFont(noBold);
			styles.put("ColumnAlignCenterTopHairRightMedrBorder", style);
			
			style = createTopHairRightMedLeftMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopHairRightMedLeftMedBorder", style);
			
			style = createTopHairRightMedLeftMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			
			style.setFont(noBold);
			style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			styles.put("ColumnAlignRightTopHairRightMedLeftMedBorder2Decimal", style);
			
			style = createTopHairRightMedLeftMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			
			style.setFont(noBold);
			styles.put("ColumnAlignLeftTopHairRightMedLeftMedBorder", style);
			
			style = createTopHairRightMedLeftMedBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			
			style.setFont(headerFont);
			styles.put("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder", style);
			
			style = createTopMedRightMedBorderStyle(wb);						
			style.setFont(noBold);
			styles.put("TopMedRightMedBorder", style);
			
			style = createTopMedRightMedBorderStyle(wb);	
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);		
			style.setFont(noBold);
			styles.put("ColumnAlignRightTopMedRightMedBorder", style);
			
			style = createTopHairRightHairBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);			
			style.setFont(noBold);
			styles.put("ColumnAlignCenterTopHairRightHairBorder", style);
			
			
			style = createTopMedLeftMedFontBoldBorderStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);			
			style.setFont(headerFont);
			styles.put("ColumnAlignLeftTopMedLeftMedFontBoldBorder", style);
			
			style = createTopHairRightMedBottomHairLeftMedBorderStyle(wb);
			styles.put("createTopHairRightMedBottomHairLeftMedBorderStyle",style);
					
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("DataRowColumnLeftAlignBold", style);
			
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("ColumnRightAlignNumBold", style);
			
			//Right alligned Number without bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat(""));
			styles.put("DataRowColumnRightAlignNum", style);
			
			//Right alligned Number without bold
			style = createBorderedStyle(wb);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(noBold);
			style.setDataFormat((short)BuiltinFormats.getBuiltinFormat(""));
			styles.put("DataRowColumnRightAlignNumSDReport", style);

			//Right alligned Number with bold
			
			style = createBorderedStyle(wb);
			style.setAlignment((short) 2);
			style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			style.setFillPattern((short) 1);
			style.setFont(headerFont);
			styles.put("DataRowColumnRightAlignNumSDReportBOLD", style);
			
			return styles;
			
		}
		
		//Formatting Border Style
		private static CellStyle createBorderedStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();
			style.setBorderRight((short) 1);
			style.setRightBorderColor(black);
			style.setBorderBottom((short) 1);
			style.setBottomBorderColor(black);
			style.setBorderLeft((short) 1);
			style.setLeftBorderColor(black);
			style.setBorderTop((short) 1);
			style.setTopBorderColor(black);
			return style;
		}
		
		//Formatting Border Style
		private static CellStyle createUnBorderedStyle(Workbook wb) 
		{
			//short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();
			/*style.setBorderRight((short) 1);
			style.setRightBorderColor(black);
			style.setBorderBottom((short) 1);
			style.setBottomBorderColor(black);
			style.setBorderLeft((short) 1);
			style.setLeftBorderColor(black);
			style.setBorderTop((short) 1);
			style.setTopBorderColor(black);*/
			return style;
		}
		
		private static void createDataRow(String[] list1,Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) {
			for (int i = 0; i < list1.length; i++) {
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i);
				headerCell2.setCellValue(list1[i]);
				headerCell2.setCellStyle(styles.get("Row4"));			
			}
			
		}
		
		/*private static void createRCDataRow(String[] list1,Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) {
			for (int i = 0; i < list1.length; i++) {
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i);
				headerCell2.setCellValue(list1[i]);
				if(i<11)
					headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				else
					headerCell2.setCellStyle(styles.get("DataRowColumnRightAlign"));
					//headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumber"));
				headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
				
			}
			
		}*/
		
		private static void createDataRowExcel(String[] headerlist1, Double[] dataList, Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) 
		{	
			ArrayList arrlstCommonData = new ArrayList();
			
			for (int i = 0; i < headerlist1.length; i++)
			{
				arrlstCommonData.add(headerlist1[i]);
			}
			for (int i = 0; i < dataList.length; i++)
			{
				arrlstCommonData.add(dataList[i]);
			}
			
			
			for (int i = 0; i < arrlstCommonData.size(); i++) 
			{
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i);
				
				if(i<headerlist1.length)
				{
					headerCell2.setCellValue(arrlstCommonData.get(i).toString());
					headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				}
				else
				{
					headerCell2.setCellValue((double) arrlstCommonData.get(i));
					headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumberComma"));
				}
				
			}
		}
		
		/*private static void createDataRowExcelForRPRate(String[] headerlist1, Double[] dataList, Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) 
		{	
			ArrayList arrlstCommonData = new ArrayList();
			
			for (int i = 0; i < headerlist1.length; i++)
			{
				arrlstCommonData.add(headerlist1[i]);
			}
			for (int i = 0; i < dataList.length; i++)
			{
				arrlstCommonData.add(dataList[i]);
			}
			
			
			for (int i = 0; i < arrlstCommonData.size(); i++) 
			{
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i+1);
				
				if(i<headerlist1.length)
				{
					headerCell2.setCellValue(arrlstCommonData.get(i).toString());
					headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				}
				else
				{
					headerCell2.setCellValue((double) arrlstCommonData.get(i));
					headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumberComma"));
				}
				
			}
		}*/
		
		private static void createRCDataRow(String[] headerlist1, Double[] dataList, Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) 
		{	
			ArrayList arrlstCommonData = new ArrayList();
			
			for (int i = 0; i < headerlist1.length; i++)
			{
				arrlstCommonData.add(headerlist1[i]);
			}
			for (int i = 0; i < dataList.length; i++)
			{
				arrlstCommonData.add(dataList[i]);
			}
			
			
			for (int i = 0; i < arrlstCommonData.size(); i++) 
			{
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i);
				
				if(i<13)
				{
					headerCell2.setCellValue((arrlstCommonData.get(i) == null )? "": arrlstCommonData.get(i).toString());
					headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				}
				else
				{
					if (arrlstCommonData.get(i).toString().equals("Infinity") || arrlstCommonData.get(i).toString().equals("NaN") || arrlstCommonData.get(i).toString().equals("-Infinity") )
					{
						headerCell2.setCellValue("");
					}
					else
					{
					headerCell2.setCellValue((double) arrlstCommonData.get(i));
					}
					headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumber"));
				}
				
			}
		}
		
		private static void createHeaderDataRow(String[] list1,Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) {
			for (int i = 0; i < list1.length; i++) {
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i+1);
				headerCell2.setCellValue(list1[i]);
				headerCell2.setCellStyle(styles.get("Row4"));			
			}
			
		}
		// get Country name using Country Id
		public static String getCountryName (int countryId) {
			
			String countryName = null;
			if(countryId == 1) {
				countryName = "USA";
			}else if(countryId == 2) {
				countryName = "India";
			}
			else if(countryId == 3) {
				countryName = "Canada";
			}
			else if(countryId == 4) {
				countryName = "Germany";
			}
			else if(countryId == 5) {
				countryName = "UK";
			}
			else if(countryId == 6) {
				countryName = "Australia";
			}
			else if(countryId == 7) {
				countryName = "Others";
			}
			else if(countryId == 8) {
				countryName = "Common";
			}
			return countryName;
		}
		
		public static String getDeductionType(int deductionId) {
			String deductionType = null;
			if(deductionId == 2) {
				deductionType = "Tax";
			}
			else if(deductionId == 3) {
				deductionType = "Assumption";
			}
			return deductionType;
		}


		public static byte[] getSalaryExcelReport(List<BasicSalary> salaryList, int countryId, int practiceId, int year, String countryName, String practiceName) 
		{	
			AppLoger.APPLOGGER.info("Inside the Excel Download function of Master Salary screen");
			AppLoger.APPLOGGER.info("The Country Id is....... "+ countryId);
			AppLoger.APPLOGGER.info("The Practice Id is............. "+ practiceId);
			AppLoger.APPLOGGER.info("The year is............. "+ year);
			// TODO Auto-generated method stub
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
	        Sheet sheet = wb.createSheet("Master Salary Details");
	        sheet.setFitToPage(true);
	        
	        // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	        Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(1);
			titleCell.setCellValue("Master Salary Details");
			titleCell.setCellStyle(styles.get("Header"));
			//sheet.addMergedRegion(CellRangeAddress.valueOf("$B$1:$C$1"));
			
			
			final String[] headerList1 = {"Country","Practice","Year"};
			Row headerRow = sheet.createRow(2);
			headerRow.setHeightInPoints(16);
			Cell headerCell;
			for (int i = 0; i < headerList1.length; i++) {
				headerCell = headerRow.createCell(i+1);
				headerCell.setCellValue(headerList1[i]);
				headerCell.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount1 =3;
			
				String[] verticalList1 ={countryName,practiceName,new Integer(year).toString()};
		    	  Row header1 = sheet.createRow(rowCount1);
		    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
		    	  rowCount1++;
		      

			
	     // Table Header
			final String[] headerList = {"Designation","Annual Salary"};
			Row headerRow1 = sheet.createRow(5);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) {
				headerCell1 = headerRow1.createCell(i+1);
				headerCell1.setCellValue(headerList[i]);
				headerCell1.setCellStyle(styles.get("Row3"));
			}
	 
	        int rowCount = 6;
	        
	      for(int i=0; i<salaryList.size(); i++)
	      {
	    	  
	    	  Double annulaSal = Double.parseDouble(new Double(salaryList.get(i).getAnnualSalary()).toString());
	    	  String strannulaSal =  String.format ("%,.2f", annulaSal);
	    	  
	    	  String[] verticalList ={salaryList.get(i).getEmpDesg().getDesgDesc(), strannulaSal};
	    	  Row header = sheet.createRow(rowCount);
	    	  createHeaderDataRow(verticalList,sheet,styles,header);
	    	  
	    	  Cell cellI = header.getCell(1);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(2);
	    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
	    	  
	    	  rowCount++;
	        }
	        
		     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
				     sheet.autoSizeColumn(columnIndex);
		      }
			wb.write(out);
			wb.close();
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}


		public static byte[] getRPRateExcelReport(List<MasterRate> rPRateList, int countryId, int year, String countryName) {
			
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try 
			{
				
				//	create excel xls sheet
				Map<String, CellStyle> styles = createStyles(wb);
		    Sheet sheet = wb.createSheet("Master Right Price Rate Details");
		        sheet.setFitToPage(true);
		        
		        // create style for header cells
		        CellStyle style = wb.createCellStyle();
		        Font font = wb.createFont();
		        font.setFontName("Arial");
		        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		        font.setBold(true);
		        font.setColor(HSSFColor.WHITE.index);
		        style.setFont(font);
		        Row titleRow = sheet.createRow(0);
				titleRow.setHeightInPoints(16);
				Cell titleCell = titleRow.createCell(2);
				titleCell.setCellValue("Master Right Price Rate Details");
				titleCell.setCellStyle(styles.get("Header"));
				//sheet.addMergedRegion(CellRangeAddress.valueOf("$B$1:$C$1"));
			
			
				final String[] headerList1 = {"Country","Year"};
				Row headerRow = sheet.createRow(2);
				headerRow.setHeightInPoints(16);
				Cell headerCell;
				for (int i = 0; i < headerList1.length; i++) {
					headerCell = headerRow.createCell(i+1);
					headerCell.setCellValue(headerList1[i]);
					headerCell.setCellStyle(styles.get("Row3"));
				}
			
				int rowCount1 =3;
			
				String[] verticalList1 ={countryName,new Integer(year).toString()};
				Row header1 = sheet.createRow(rowCount1);
		    	createHeaderDataRow(verticalList1,sheet,styles,header1);
		    	rowCount1++;
		    	
		    	// Table Header
				final String[] headerList = {"Role Code","Master Roles","Low","Medium","High","VHigh","OffShore"};
				Row headerRow1 = sheet.createRow(5);
				headerRow1.setHeightInPoints(16);
				Cell headerCell1;
				for (int i = 0; i < headerList.length; i++) {
//					headerCell1 = headerRow1.createCell(i+1);
					headerCell1 = headerRow1.createCell(i);
					headerCell1.setCellValue(headerList[i]);
					headerCell1.setCellStyle(styles.get("Row3"));
				}
	 
				int rowCount = 6;
	        
		      for(int i=0; i<rPRateList.size(); i++)
		      {
		    	 /* String strLow="";
		    	  String strMed="";
		    	  String strHigh="";
		    	  String strVHigh="";
		    	  String  strOffhore="";
		    	  
		    	  if(rPRateList.get(i).getRateLow()!=null){
		    	  Double RateLow = Double.parseDouble(rPRateList.get(i).getRateLow().toString());
		    	  strLow =  String.format ("%,.2f", RateLow);
		    	  }
		    	  
		    	  if(rPRateList.get(i).getRateMedium()!=null){
		    	  Double RateMedium = Double.parseDouble(rPRateList.get(i).getRateMedium().toString());
		    	  strMed =  String.format ("%,.2f", RateMedium);
		    	  }
		    	  
		    	  if(rPRateList.get(i).getRateHigh()!=null){
		    	  Double RateHigh = Double.parseDouble(rPRateList.get(i).getRateHigh().toString());
		    	  strHigh =  String.format ("%,.2f", RateHigh);
		    	  }
		    	  
		    	   if(rPRateList.get(i).getRateVHigh()!=null){
		    	  Double RateVHigh = Double.parseDouble(rPRateList.get(i).getRateVHigh().toString());
		    	  strVHigh =  String.format ("%,.2f", RateVHigh);
		    	   }
		    	   
		    	   if(rPRateList.get(i).getRateOffshore()!=null){
		    	  Double RateOffshore = Double.parseDouble(rPRateList.get(i).getRateOffshore().toString());
		    	  strOffhore =  String.format ("%,.2f", RateOffshore);
		    	   }
		    	   
		    	  String[] verticalList ={rPRateList.get(i).getMasterRole().getMasterRoleName(), rPRateList.get(i).getMasterRole().getMasterRoleLongDescription(), strLow, strMed, strHigh, strVHigh, strOffhore};
		    	  Row header = sheet.createRow(rowCount);
		    	  createHeaderDataRow(verticalList,sheet,styles,header);
		    	  
		    	  Cell cellI = header.getCell(1);
		    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		    	  
		    	  cellI = header.getCell(2);
		    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		    	  
		    	  cellI = header.getCell(3);
		    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
		    	  
		    	  cellI = header.getCell(4);
		    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
		    	  
		    	  cellI = header.getCell(5);
		    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
		    	  
		    	  cellI = header.getCell(6);
		    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
		    	  
		    	  cellI = header.getCell(7);
		    	  cellI.setCellStyle(styles.get("DataRowColumnRightAlign"));
		    	  rowCount++;*/
		    	  
		    	  // Data need to changes in currency 
		    	   
		    	  String[] verticalList ={rPRateList.get(i).getMasterRole().getMasterRoleName(), rPRateList.get(i).getMasterRole().getMasterRoleLongDescription()};
		    	  Double[] rightPriceRateDataList =
					{
						new BigDecimal((rPRateList.get(i).getRateLow() == null) ? 0.00 : rPRateList.get(i).getRateLow()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal((rPRateList.get(i).getRateMedium() == null)? 0.00 : rPRateList.get(i).getRateMedium()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal((rPRateList.get(i).getRateHigh() == null)? 0.00 : rPRateList.get(i).getRateHigh()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal((rPRateList.get(i).getRateVHigh() == null) ? 0.00 : rPRateList.get(i).getRateVHigh()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal((rPRateList.get(i).getRateOffshore() == null) ? 0.00 : rPRateList.get(i).getRateOffshore()).setScale(2, RoundingMode.HALF_UP).doubleValue()
					};

		    	  Row header = sheet.createRow(rowCount);
		    	  createDataRowExcel(verticalList,rightPriceRateDataList,sheet,styles,header);
		    	  rowCount++;
		      }
	        
		      for(int columnIndex = 0; columnIndex < 11; columnIndex++) 
		      {
		    	  sheet.autoSizeColumn(columnIndex);
		      }
		      wb.write(out);
		      wb.close();
		      return out.toByteArray();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

public static byte[] getRateCardReport(List<RateCardDetails> list2) {
			
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
	        Sheet sheet = wb.createSheet("RateCard Details");
	        sheet.setFitToPage(true);
	        
	        // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	        Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("RateCard Details");
			titleCell.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
			
	     // Table Header
			final String[] list = {"RateCard ID","Customer","RC Name","Start Date","End Date","Country","City","Sales Vol",
					"Industry","Manual","Pending With","Pending Since"};
			
			
			Row headerRow1 = sheet.createRow(1);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < list.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(list[i]);
				headerCell1.setCellStyle(styles.get("Row3"));
			}
	 
	        int rowCount = 2;
	        
	       // int count=0;
	    	
	        String itkpoflag="";
	        String isManualRc="";
	        
	        String Country=" ";
	        String City=" ";
	        
	       // List<RateCardDetails> rcDetails = new ArrayList<RateCardDetails>();
	        for(RateCardDetails rc : list2){ 
	        
	          AppLoger.APPLOGGER.info("RateCardDetails ID............  "+ rc.getRcId());
	        	
	          if(rc.getIsItKpo()==0)
	          {
	        	  itkpoflag="IT";
	          }
	          else if(rc.getIsItKpo()==1)
	          {
	        	  itkpoflag="KPO";
	          }
	          
	          if(rc.getIsManualRc().equals("A") || rc.getIsManualRc().equals("H"))
	          {
	        	  isManualRc="No";
	          }
	          else if(rc.getIsManualRc().equals("M"))
	          {
	        	  isManualRc="Yes";
	          }
	          
	           //System.out.println("List*** ");
	          
	          // list2.stream().forEach(System.out::println);
	          
	          System.out.println("RC Location Size "+rc.getRateCardLocations().size()+" Details "+rc.getRateCardLocations().toString());
	          
	          if(rc.getRateCardLocations().size()>0)
	          {
	        	  if(rc.getRateCardLocations().size()==1)
	        	  {
	        		  Country=rc.getRateCardLocations().get(0).getCity().getCountry().getCountryName();
	        		  City= rc.getRateCardLocations().get(0).getCity().getCityName();
	        	  }
	        	  else
	        	  {
	        		  Country="Multiple";
	        		  City="Multiple";
	        	  }
	        	  
	          }
	          
	          
	          String[] rcList ={ String.valueOf(rc.getRcId()), rc.getCustomerVerticalMapping().getCustomer().getCustomerName(),
	    			  rc.getRcName(),rc.getRcStartDate(),rc.getRcEndDate(),Country,City,
	    			  rc.getTvc().toString(),itkpoflag,isManualRc,rc.getCurrentApproverId(),rc.getUpdatedOn()
	    	  	};
	    	  
	          Row header1 = sheet.createRow(rowCount);
	          createDataRow(rcList,sheet,styles,header1);
	          
	         rowCount++;
	    	}
	        
		     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
				     sheet.autoSizeColumn(columnIndex);
		      }
	        
			wb.write(out);
			wb.close();
			return out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}


	public static byte[] getMasterRolesExcelReport(List<MasterRoles> objMasterRolesList, int practiceId, String description,
		int subpracticeId, String subpracticeName) 
	{
		AppLoger.APPLOGGER.info("The practiceId is....... "+ practiceId);
		AppLoger.APPLOGGER.info("The practice descriptionis....... "+ description);
		AppLoger.APPLOGGER.info("The subpracticeId Id is............. "+subpracticeId);
		AppLoger.APPLOGGER.info("The subpracticeName is....... "+ subpracticeName);

		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try 
		{
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
	        Sheet sheet = wb.createSheet("Master Roles Details");
	        sheet.setFitToPage(true);
	        
	        // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	        Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(2);
			titleCell.setCellValue("Master Roles Details");
			titleCell.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
			
			
			final String[] headerList1 = {"Practice","Sub Practice"};
			Row headerRow = sheet.createRow(2);
			headerRow.setHeightInPoints(16);
			Cell headerCell;
			for (int i = 0; i < headerList1.length; i++) {
				headerCell = headerRow.createCell(i+1);
				headerCell.setCellValue(headerList1[i]);
				headerCell.setCellStyle(styles.get("Row3"));
			}
			
			int rowCount1 =3;
			
			String[] verticalList1 ={description,subpracticeName};
	    	  Row header1 = sheet.createRow(rowCount1);
	    	  createHeaderDataRow(verticalList1,sheet,styles,header1);
	    	  rowCount1++;
			
		    // Table Header
			final String[] headerList = {"Master Role Code","Short Description","Practice","Sub Practice","Syntel Role","Proficiency Level"};
			Row headerRow1 = sheet.createRow(5);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList[i]);
				headerCell1.setCellStyle(styles.get("Row3"));
			}
	 
	        int rowCount = 6;
	        
	        for(int i=0; i<objMasterRolesList.size(); i++)
	        { 
	    	  String[] verticalList ={ objMasterRolesList.get(i).getMasterRoleName(),objMasterRolesList.get(i).getMasterRoleShortDescription(), objMasterRolesList.get(i).getMasterSubPractice().getMasterPractice().getDescription(),
	    			  					objMasterRolesList.get(i).getMasterSubPractice().getSubpracticeName(), objMasterRolesList.get(i).getMasterSyntelRoles().getSyntelRoleName(), objMasterRolesList.get(i).getProficiencyLevelDescription()};
	    	  Row header = sheet.createRow(rowCount);
	
	    	  createDataRow(verticalList,sheet,styles,header);    	  
	    	  Cell cellI = header.getCell(0);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(1);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(2);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(3);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(4);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  cellI = header.getCell(5);
	    	  cellI.setCellStyle(styles.get("DataRowColumnLeftAlign"));
	    	  
	    	  rowCount++;
	        }
	        
		    for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
				     sheet.autoSizeColumn(columnIndex);			     
		    }
	     	wb.write(out);
			wb.close();
			return out.toByteArray();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}


	public static byte[] downloadUtilizationRoleExcel(List<RoleUtilizationYear> lstYears, List<RateCardRoleUtilization> lstRCRoleUtilization,
			List<RateCardDetails> rateCardDetails, boolean isBillCurrency, Double multifier, String currencyName, List<RateCardLocation> rcLocDetails) 
	{
		AppLoger.APPLOGGER.info("Inside getLocalCurrencyExcelReport function");
		AppLoger.APPLOGGER.info("List of years....... "+ lstYears);
		AppLoger.APPLOGGER.info("List of rcDetailsRoleUtilization....... "+ lstRCRoleUtilization);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = createStyles(wb);
		try {
			//create excel xls sheet
			Integer counter = 0;
			for(RoleUtilizationYear objYear: lstYears)
			{
				counter++;
				String strSheetName = objYear.getYearDesc().replace(",", " - "); 
				Sheet sheet = wb.createSheet(strSheetName);
				sheet.setFitToPage(true);
				createSheet(sheet,wb,objYear.getYear(),lstRCRoleUtilization,styles,rateCardDetails,isBillCurrency,multifier,currencyName,counter,rcLocDetails);
			}
			wb.write(out);
			wb.close(); 
			return out.toByteArray();			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}
	
	private static void createSheet(Sheet sheet, HSSFWorkbook wb, Integer intYear, List<RateCardRoleUtilization> lstRCRoleUtilization, 
			Map<String, CellStyle> styles, List<RateCardDetails> rateCardDetails, boolean isBillCurrency, Double multifier,
			String currencyName, Integer counter, List<RateCardLocation> rcLocDetails) 
	{
		 // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        /*Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(5);
		titleCell.setCellValue("Rate Card Creation - Role Utilization and Rates");
		titleCell.setCellStyle(styles.get("rcHeader"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$F$1:$L$1"));
		*/
		Integer rowCount = 0;
		rowCount = 1;
		
		rowCount = createCustomerPanel(sheet,styles,rateCardDetails,rowCount,counter,intYear);
		rowCount = rowCount+2;
		
		rowCount = createRCPanel(sheet,rowCount,rateCardDetails,currencyName,styles,counter,intYear,isBillCurrency);
		rowCount = rowCount+2;
		
		Row headerRow = sheet.createRow(rowCount);
		headerRow.setHeightInPoints(16);

		Cell cellUtiOn0 = headerRow.createCell(0);
		cellUtiOn0.setCellValue(" ");
		cellUtiOn0.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
	    sheet.setDisplayGridlines(false);
		Cell cellUtiOn1 = headerRow.createCell(1);
		cellUtiOn1.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn2 = headerRow.createCell(2);
		cellUtiOn2.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn3 = headerRow.createCell(3);
		cellUtiOn3.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn4 = headerRow.createCell(4);
		cellUtiOn4.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn5 = headerRow.createCell(5);
		cellUtiOn5.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn6 = headerRow.createCell(6);
		cellUtiOn6.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn7 = headerRow.createCell(7);
		cellUtiOn7.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn8 = headerRow.createCell(8);
		cellUtiOn8.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn9 = headerRow.createCell(9);
		cellUtiOn9.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn10 = headerRow.createCell(10);
		cellUtiOn10.setCellStyle(styles.get("rcHeaderOnsiteUtil"));

	    sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,10));
		
		
		createHeaders(headerRow,sheet,rateCardDetails,styles,rowCount,rcLocDetails);

		rowCount = rowCount+2;
		
		createExcelDataRows(lstRCRoleUtilization,sheet,rateCardDetails,styles,intYear,rowCount,isBillCurrency,multifier,rcLocDetails);
		
				
		for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
		{
			sheet.autoSizeColumn(columnIndex);			     
		}
		
	}


	private static Integer createCustomerPanel(Sheet sheet, Map<String, CellStyle> styles, List<RateCardDetails> rateCardDetails,Integer rowCount, Integer counter,Integer intYear) 
	{
		// Customer panel Start
		Row rcCustNamePanel = sheet.createRow(rowCount);
		Cell cellCustName = rcCustNamePanel.createCell(0);
		cellCustName.setCellStyle(styles.get("RowRateCardHeader2"));
		cellCustName.setCellValue(rateCardDetails.get(0).getCustomer().getCustomerName());
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
		
		Cell cellCustName1 = rcCustNamePanel.createCell(1);
		cellCustName1.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName2 = rcCustNamePanel.createCell(2);
		cellCustName2.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName3 = rcCustNamePanel.createCell(3);
		cellCustName3.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName4 = rcCustNamePanel.createCell(4);
		cellCustName4.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName5 = rcCustNamePanel.createCell(5);
		cellCustName5.setCellStyle(styles.get("RowRateCardHeader2"));
		rowCount++;
		
		//Row 2
		Row rcRow2 = sheet.createRow(rowCount);
		Cell cellYR = rcRow2.createCell(0);
		cellYR.setCellStyle(styles.get("DataRowColumnCenterAlign"));		
		cellYR.setCellValue("Year "+ counter +" - "+ sheet.getSheetName());
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
		
		Cell cellYr1 = rcRow2.createCell(1);
		cellYr1.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellYr2 = rcRow2.createCell(2);
		cellYr2.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellYr3 = rcRow2.createCell(3);
		cellYr3.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellYr4 = rcRow2.createCell(4);
		cellYr4.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellYr5 = rcRow2.createCell(5);
		cellYr5.setCellStyle(styles.get("RowRateCardHeader2"));
		
		return rowCount;
	}


	private static Integer createRCPanel(Sheet sheet, Integer rowCount, List<RateCardDetails> rateCardDetails,
			String currencyName, Map<String, CellStyle> styles, Integer counter,Integer intYear, boolean isBillCurrency) 
	{
		// RC panel Start
			Row rcPanel1 = sheet.createRow(rowCount);
			Cell cellRCIDTitle = rcPanel1.createCell(0);
			cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCIDTitle.setCellValue("Rate Card Id");
			
			Cell cellRCID = rcPanel1.createCell(1);
			cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellRCID.setCellValue(rateCardDetails.get(0).getRcId());
			
			
			Cell cellRCNameTitle = rcPanel1.createCell(2);
			cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCNameTitle.setCellValue("Name");
			
			Cell cellRCName = rcPanel1.createCell(3);
			cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCName.setCellValue(rateCardDetails.get(0).getRcName());
			
			Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
			cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCApprStatusTitle.setCellValue("Approver Status");
			
			Cell cellRCApprStatus = rcPanel1.createCell(5);
			cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCApprStatus.setCellValue(rateCardDetails.get(0).getStatusIndicator());
			
			rowCount++;
			
			//Row 2
			Row rcPanel2 = sheet.createRow(rowCount);
			Cell cellRCStartDateTitle = rcPanel2.createCell(0);
			cellRCStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellRCStartDateTitle.setCellValue("Start Date (dd/mm/yy)");
			
			Cell cellRCStartDate = rcPanel2.createCell(1);
			cellRCStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			String strDate[] = rateCardDetails.get(0).getRcStartDate().split(" ");	
			String Date[] =  strDate[0].split("-");
			String strYr = Date[0];
			String strMonth = Date[1];
			String strDay = Date[2];
			cellRCStartDate.setCellValue(strDay+ "/"+strMonth+ "/"+strYr);
			
			
			Cell cellRCEndDateTitle = rcPanel2.createCell(2);
			cellRCEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCEndDateTitle.setCellValue("Expected End Date (dd/mm/yy)");
			
			Cell cellRCEndDate = rcPanel2.createCell(3);
			cellRCEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			String strEndDate[] = rateCardDetails.get(0).getRcEndDate().split(" ");
			String EndDate[] =  strEndDate[0].split("-");
			String strEndYr = EndDate[0];
			String strEndMonth = EndDate[1];
			String strEndDay = EndDate[2];		
		    cellRCEndDate.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
			
			
		    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
			cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCAppMonthsTitle.setCellValue("Applicable Months");
			
			Cell cellRCAppMonths = rcPanel2.createCell(5);
			cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCAppMonths.setCellValue(rateCardDetails.get(0).getApplicableMonths());
			
			rowCount++;
			
			//Row 3
			Row rcPanel3 = sheet.createRow(rowCount);
			Cell cellCustTitle = rcPanel3.createCell(0);
			cellCustTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellCustTitle.setCellValue("Customer");
			
			Cell cellCust = rcPanel3.createCell(1);
			cellCust.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellCust.setCellValue(rateCardDetails.get(0).getCustomer().getCustomerName());
			
			
			Cell cellTCVTitle = rcPanel3.createCell(2);
			cellTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellTCVTitle.setCellValue("Expected TCV");
			
			Cell cellTCV = rcPanel3.createCell(3);
			cellTCV.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			cellTCV.setCellValue(rateCardDetails.get(0).getTvc() + " - " + currencyName);
			
			
		    Cell cellDiscTitle = rcPanel3.createCell(4);
		    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellDiscTitle.setCellValue("Discount");
			
			Cell cellDisc = rcPanel3.createCell(5);			
			cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			cellDisc.setCellValue(rateCardDetails.get(0).getVolumeDiscount() + "%");
			
			rowCount++;
			
			//Row 4
			Row rcPanel4 = sheet.createRow(rowCount);
			Cell cellOnUtiTitle = rcPanel4.createCell(0);
			cellOnUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellOnUtiTitle.setCellValue("Onsite Utilization %");
			
			Cell cellOnUti = rcPanel4.createCell(1);
			cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellOnUti.setCellValue(rateCardDetails.get(0).getExpectedOnsiteResourcePercentage() +"%");
			
			
			Cell cellOffUtiTitle = rcPanel4.createCell(2);
			cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOffUtiTitle.setCellValue("Offshore Utilization %");
			
			Cell cellOffUti = rcPanel4.createCell(3);
			cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellOffUti.setCellValue(rateCardDetails.get(0).getExpectedOffshoreResourcePercentage()+"%");
						
			
		    Cell cellGMPerTitle = rcPanel4.createCell(4);
		    cellGMPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellGMPerTitle.setCellValue("PM% Post VR");
			
			Cell cellGMPer = rcPanel4.createCell(5);
			cellGMPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			if(rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount() == null ||  
					rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount() == 0)
			{
				cellGMPer.setCellValue("-");
			}
			else			
			cellGMPer.setCellValue(new BigDecimal(rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount()).setScale(2, RoundingMode.HALF_UP).toString() +"%");

			rowCount++;
			
			//Row 5
			Row rcPanel5 = sheet.createRow(rowCount);
			Cell cellOnhrsTitle = rcPanel5.createCell(0);
			cellOnhrsTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellOnhrsTitle.setCellValue("Onsite hours/day");
			
			Cell cellOnhrs = rcPanel5.createCell(1);
			cellOnhrs.setCellStyle(styles.get("DataRowColumnLeftAlignNumber"));		
			Double dblOnHrs = (rateCardDetails.get(0).getOnsiteHoursPerDay() == null)? 0.00 : rateCardDetails.get(0).getOnsiteHoursPerDay();
			cellOnhrs.setCellValue(new BigDecimal(dblOnHrs).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			
			Cell cellOffHrsTitle = rcPanel5.createCell(2);
			cellOffHrsTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOffHrsTitle.setCellValue("Offshore hours/day");
			
			Cell cellOffHrs = rcPanel5.createCell(3);
			cellOffHrs.setCellStyle(styles.get("DataRowColumnLeftAlignNumber"));
			Double dblOffHrs = (rateCardDetails.get(0).getOffshoreHoursPerDay() == null)? 0.00 : rateCardDetails.get(0).getOffshoreHoursPerDay();
			cellOffHrs.setCellValue(new BigDecimal(dblOffHrs).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			
		    Cell cellBillCurrTitle = rcPanel5.createCell(4);
		    cellBillCurrTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellBillCurrTitle.setCellValue( isBillCurrency ? "Billing Currency" :"Local Currency");
			
			Cell cellBillCurr = rcPanel5.createCell(5);
			cellBillCurr.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellBillCurr.setCellValue(currencyName);
			
			rowCount++;
			
			//Row 6
			Row rcPanel6 = sheet.createRow(rowCount);
			Cell cellOldRCNumberTitle = rcPanel6.createCell(0);
			cellOldRCNumberTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellOldRCNumberTitle.setCellValue("Old rate card no");
			
			Cell cellOldRCNumber = rcPanel6.createCell(1);
			cellOldRCNumber.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			if(rateCardDetails.get(0).getRenewalRCId() != null)
				cellOldRCNumber.setCellValue(rateCardDetails.get(0).getRenewalRCId());
			else
				cellOldRCNumber.setCellValue("-");
			
			
			Cell cellOnYoyTitle = rcPanel6.createCell(2);
			cellOnYoyTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOnYoyTitle.setCellValue("Onsite YOY rate increase Year "+ counter);
			
			Cell cellOnYoy = rcPanel6.createCell(3);
			cellOnYoy.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellOnYoy.setCellValue("-");
			
			
		    Cell cellOffYoyTitle = rcPanel6.createCell(4);
		    cellOffYoyTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellOffYoyTitle.setCellValue("Offshore YOY rate increase Year "+ counter);
			
			Cell cellOffYoy = rcPanel6.createCell(5);
			cellOffYoy.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellOffYoy.setCellValue("-");
			
			for(int i=0; i<rateCardDetails.get(0).getyOYIncrementPercents().size();i++)
			{
				if(intYear.equals(rateCardDetails.get(0).getyOYIncrementPercents().get(i).getyOYIncYear()))
				{
					cellOnYoy.setCellValue(rateCardDetails.get(0).getyOYIncrementPercents().get(i).getIncrementPercentOnsite());
					cellOffYoy.setCellValue(rateCardDetails.get(0).getyOYIncrementPercents().get(i).getIncrementPercentOffshore());
				}
			}
			//RC panel end
		return rowCount;
	}


	private static void createExcelDataRows(List<RateCardRoleUtilization> rateCardRoleUtiDetails, Sheet sheet, List<RateCardDetails> rateCardDetails, 
			Map<String, CellStyle> styles, Integer intYear, Integer rowCount, boolean isBillCurrency, Double multifier, List<RateCardLocation> rcLocDetails) 
	{
		Double intOnsitePercentage = rateCardDetails.get(0).getExpectedOnsiteResourcePercentage();
		Double dblOnsiteUsagePer = 0.0; 
		Double dblOnsiteMasterRate = 0.0;
		Double dblMasterRateRevenue = 0.0;
		Double dblLocalCost  = 0.0;
		//Double dbldeputedCost = 0.0;
		Double dblTotalCost = 0.0;
		Double dblOnsiteProClientRate = 0.0;
		Double dblOnsiteMasterRateTotal = 0.0;
		Double dblOnsiteProposedRate = 0.0;
		Double dblOnsiteDiscPremium = 0.0;
		Double dblActualRateRevenue = 0.0;
		
		Double dblOffshoreUsage = 0.0;
		Double dblOffshorMasterRate = 0.0;
		Double dblOffshoreActualRateRevenue = 0.0;
		Double dblOffshoreMasterRateRevenue = 0.0;
		Double dblOffshoreTotalCost = 0.0;
		Double dblOffshorePropClientRate = 0.0;
		Double dblOffshoreDiscPremium = 0.0;
		Double dblOffshoreMasterRateTotal = 0.0;
		Double dblOffshoreProposedRate = 0.0;
		Double dblOffshoreGMPercent = 0.0;
		
		for(int i=0;i<rateCardRoleUtiDetails.size();i++)
		{
			if(intYear.equals(rateCardRoleUtiDetails.get(i).getTransactionYear()))
			{
				if(rateCardRoleUtiDetails.get(i).getDeputedCost() == null){
					rateCardRoleUtiDetails.get(i).setDeputedCost(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedCostBreakupId() == null){
					rateCardRoleUtiDetails.get(i).setDeputedCostBreakupId(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedCostOnUtilization() == null){
					rateCardRoleUtiDetails.get(i).setDeputedCostOnUtilization(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedHour() == null){
					rateCardRoleUtiDetails.get(i).setDeputedHour(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedMargin()== null){
					rateCardRoleUtiDetails.get(i).setDeputedMargin(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedRevenue()== null){
					rateCardRoleUtiDetails.get(i).setDeputedRevenue(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDeputedUsagePercent()== null){
					rateCardRoleUtiDetails.get(i).setDeputedUsagePercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticCost()== null){
					rateCardRoleUtiDetails.get(i).setDomesticCost(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticCostBreakupId()== null){
					rateCardRoleUtiDetails.get(i).setDomesticCostBreakupId(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticCostOnUtilization()== null){
					rateCardRoleUtiDetails.get(i).setDomesticCostOnUtilization(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticHour()== null){
					rateCardRoleUtiDetails.get(i).setDomesticCostOnUtilization(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticMargin()==null){
					rateCardRoleUtiDetails.get(i).setDomesticMargin(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticRevenue()==null){
					rateCardRoleUtiDetails.get(i).setDomesticRevenue(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()==null){
					rateCardRoleUtiDetails.get(i).setDomesticUsagePercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreActualGmPercent() ==null){
					rateCardRoleUtiDetails.get(i).setOffshoreActualGmPercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreCost() ==null){
					rateCardRoleUtiDetails.get(i).setOffshoreCost(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreCostBreakupId()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreCostBreakupId(0);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreDiscountPremium()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreDiscountPremium(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreHours()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreHours(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreMargin()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreMargin(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreMasterRate()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreMasterRate(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreMasterRateGmPercent()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreMasterRateGmPercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreProposedClientRate(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreRevenue()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreRevenue(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOffshoreUsage()== null){
					rateCardRoleUtiDetails.get(i).setOffshoreUsage(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteActualGmPercent() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteActualGmPercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteCost() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteCost(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteDiscountPremium() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteDiscountPremium(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteMasterGmPercent() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteMasterGmPercent(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteMasterRate(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteProposedClientRate(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteRevenue() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteRevenue(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getOnsiteUsage() == null){
					rateCardRoleUtiDetails.get(i).setOnsiteUsage(0.00);
				}
				if(rateCardRoleUtiDetails.get(i).getVolumeDiscount() == null){
					rateCardRoleUtiDetails.get(i).setVolumeDiscount(0.00);
				}
				
				
				
				
				if(intOnsitePercentage == 100 || rcLocDetails.get(0).getIsOffShore().equals("N"))
				{
					dblOnsiteUsagePer += rateCardRoleUtiDetails.get(i).getOnsiteUsage();
					dblOnsiteMasterRate += (rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage())/100;
					dblMasterRateRevenue += rateCardRoleUtiDetails.get(i).getDeputedHour() * rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * (rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					dblLocalCost += (rateCardRoleUtiDetails.get(i).getDomesticCost() * rateCardRoleUtiDetails.get(i).getDomesticUsagePercent() * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100)/100.00;
					///dbldeputedCost += (rateCardRoleUtiDetails.get(i).getDeputedCost() * ((100.00 - rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()) * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100))/100.00;
					
					dblOnsiteProClientRate += (rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					dblOnsiteMasterRateTotal += (rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage()) /100;
					dblOnsiteProposedRate += (rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage())/100;						
					
					dblActualRateRevenue += rateCardRoleUtiDetails.get(i).getDeputedHour() * rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					Double dblOnsiteMasterRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * multifier):rateCardRoleUtiDetails.get(i).getOnsiteMasterRate();
					Double dblOnsiteProposedClientRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * multifier):rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate();
					
					String[] onsiteVerticalHeaderList =
					{	
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getMasterPractice().getDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getSubpracticeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleLongDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getProficiencyLevelDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getBandGrade(),
						(rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE() == null) ?"":rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE().toString(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill().getSkillName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement().getElementName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge().getKnowledgeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getSyntelRoleDescription(),
						rateCardRoleUtiDetails.get(i).getRateCardRoles().getClientRole()
					};
					Double[] onsiteVerticalDataList =
					{
						//Utilization Onsite
						new BigDecimal(rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						//new BigDecimal(rateCardRoleUtiDetails.get(i).getDeputedUsagePercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOnsiteUsage()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOnsiteMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateGMValue(rateCardRoleUtiDetails.get(i), rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()),
						new BigDecimal(dblOnsiteProposedClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateDiscount(rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate(),rateCardRoleUtiDetails.get(i).getOnsiteMasterRate()),					 
						getActualGmPercent(rateCardRoleUtiDetails.get(i),rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate())							
					};
						
					Row header = sheet.createRow(rowCount);
					createRCDataRow(onsiteVerticalHeaderList,onsiteVerticalDataList,sheet,styles,header);
				}
				else if(intOnsitePercentage == 0)
				{
					
					Double dblOffshoreMasterRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * multifier):rateCardRoleUtiDetails.get(i).getOffshoreMasterRate();
					Double dblOffshoreProposedClientRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * multifier):rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate();
					
					dblOffshoreUsage += rateCardRoleUtiDetails.get(i).getOffshoreUsage();
					dblOffshorMasterRate += (rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage())/100;
	
					dblOffshoreActualRateRevenue += rateCardRoleUtiDetails.get(i).getOffshoreHours() * rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					dblOffshoreMasterRateRevenue += rateCardRoleUtiDetails.get(i).getOffshoreHours() * rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshoreTotalCost += rateCardRoleUtiDetails.get(i).getOffshoreCost() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshorePropClientRate += rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshoreMasterRateTotal += (rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage())/100;
					dblOffshoreProposedRate += rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100;
					
					String[] offshoreVerticalList =
					{	
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getMasterPractice().getDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getSubpracticeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleLongDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getProficiencyLevelDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getBandGrade(),
						(rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE() == null) ?"":rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE().toString(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill().getSkillName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement().getElementName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge().getKnowledgeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getSyntelRoleDescription(),
						rateCardRoleUtiDetails.get(i).getRateCardRoles().getClientRole()
					};
					Double[] onsiteVerticalDataList =
					{		
						//Utilization Offshore
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOffshoreUsage()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOffshoreMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOffshoreMasterRateGmPercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOffshoreProposedClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateDiscount(rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate(),rateCardRoleUtiDetails.get(i).getOffshoreMasterRate()),
						getActualGmPercentOffshore(rateCardRoleUtiDetails.get(i))
					};
						
					Row header = sheet.createRow(rowCount);
					createRCDataRow(offshoreVerticalList,onsiteVerticalDataList,sheet,styles,header);  
				}
				else
				{
					
					dblOnsiteUsagePer += rateCardRoleUtiDetails.get(i).getOnsiteUsage();
					dblOnsiteMasterRate += (rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage())/100;
					dblMasterRateRevenue += rateCardRoleUtiDetails.get(i).getDeputedHour() * rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * (rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					dblLocalCost += (rateCardRoleUtiDetails.get(i).getDomesticCost() * rateCardRoleUtiDetails.get(i).getDomesticUsagePercent() * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100)/100.00;
					//dbldeputedCost += (rateCardRoleUtiDetails.get(i).getDeputedCost() * ((100.00 - rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()) * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100))/100.00;
					
					dblOnsiteProClientRate += (rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					dblOnsiteMasterRateTotal += (rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage())/100;
					dblOnsiteProposedRate += (rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * rateCardRoleUtiDetails.get(i).getOnsiteUsage())/100;						
					
					dblActualRateRevenue += rateCardRoleUtiDetails.get(i).getDeputedHour() * rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOnsiteUsage()/100);
					
					
					dblOffshoreUsage += rateCardRoleUtiDetails.get(i).getOffshoreUsage();
					dblOffshorMasterRate += (rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage())/100;
	
					dblOffshoreActualRateRevenue += rateCardRoleUtiDetails.get(i).getOffshoreHours() * rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					dblOffshoreMasterRateRevenue += rateCardRoleUtiDetails.get(i).getOffshoreHours() * rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshoreTotalCost += rateCardRoleUtiDetails.get(i).getOffshoreCost() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshorePropClientRate += rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * (rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100);
					
					dblOffshoreMasterRateTotal += (rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage())/100;
					dblOffshoreProposedRate += rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * rateCardRoleUtiDetails.get(i).getOffshoreUsage()/100;
					
					Double dblOnsiteMasterRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOnsiteMasterRate() * multifier):rateCardRoleUtiDetails.get(i).getOnsiteMasterRate();
					Double dblOnsiteProposedClientRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate() * multifier):rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate();
					
					Double dblOffshoreMasterRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOffshoreMasterRate() * multifier):rateCardRoleUtiDetails.get(i).getOffshoreMasterRate();
					Double dblOffshoreProposedClientRateCalc = (isBillCurrency)?(rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate() * multifier):rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate();
					
					String[] verticalList =
					{	
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getMasterPractice().getDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterSubPractice().getSubpracticeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getMasterRoleLongDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getProficiencyLevelDescription(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getBandGrade(),
						(rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE() == null) ?"":rateCardRoleUtiDetails.get(i).getMasterRoles().getGcmCODE().toString(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkill().getSkillName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterXOSkillElement().getElementName(),
						(rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge() == null)?"":rateCardRoleUtiDetails.get(i).getRateCardRoles().getMasterX0Knowledge().getKnowledgeName(),
						rateCardRoleUtiDetails.get(i).getMasterRoles().getSyntelRoleDescription(),
						rateCardRoleUtiDetails.get(i).getRateCardRoles().getClientRole()
					};
					Double[] verticalDataList =
					{	
						//Utilization Onsite
						new BigDecimal(rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						//new BigDecimal(rateCardRoleUtiDetails.get(i).getDeputedUsagePercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOnsiteUsage()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOnsiteMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateGMValue(rateCardRoleUtiDetails.get(i), rateCardRoleUtiDetails.get(i).getDomesticUsagePercent()),
						new BigDecimal(dblOnsiteProposedClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateDiscount(rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate(),rateCardRoleUtiDetails.get(i).getOnsiteMasterRate()),					 
						getActualGmPercent(rateCardRoleUtiDetails.get(i),rateCardRoleUtiDetails.get(i).getOnsiteProposedClientRate()),

						//Utilization Offshore
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOffshoreUsage()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOffshoreMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(rateCardRoleUtiDetails.get(i).getOffshoreMasterRateGmPercent()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						new BigDecimal(dblOffshoreProposedClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue(),
						calculateDiscount(rateCardRoleUtiDetails.get(i).getOffshoreProposedClientRate(),rateCardRoleUtiDetails.get(i).getOffshoreMasterRate()),
						getActualGmPercentOffshore(rateCardRoleUtiDetails.get(i))
					};
					
					Row header = sheet.createRow(rowCount);
					createRCDataRow(verticalList,verticalDataList,sheet,styles,header);    	  
				}
				rowCount++;
			}
		}
		
		Row subTotalRow = sheet.createRow(rowCount);
		subTotalRow.setHeightInPoints(16);
		Cell cellTitle = subTotalRow.createCell(0);
		
		cellTitle.setCellStyle(styles.get("Row4"));
		cellTitle.setCellStyle(styles.get("rcHeadersubTotal"));
		cellTitle.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		cellTitle.setCellValue("Sub Total");

		Cell cellCustName1 = subTotalRow.createCell(1);
		cellCustName1.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName2 = subTotalRow.createCell(2);
		cellCustName2.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName3 = subTotalRow.createCell(3);
		cellCustName3.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName4 = subTotalRow.createCell(4);
		cellCustName4.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName5 = subTotalRow.createCell(5);
		cellCustName5.setCellStyle(styles.get("rcSubTotal"));

		Cell cellCustName6 = subTotalRow.createCell(6);
		cellCustName6.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName7 = subTotalRow.createCell(7);
		cellCustName7.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName8 = subTotalRow.createCell(8);
		cellCustName8.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName9 = subTotalRow.createCell(9);
		cellCustName9.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName10 = subTotalRow.createCell(10);
		cellCustName10.setCellStyle(styles.get("rcSubTotal"));
		Cell cellCustName11 = subTotalRow.createCell(11);
		cellCustName11.setCellStyle(styles.get("rcSubTotal"));
		
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,11));
		
		if(intOnsitePercentage == 100 || rcLocDetails.get(0).getIsOffShore().equals("N"))
		{
			Cell cellLocalEmpty = subTotalRow.createCell(12);
			cellLocalEmpty.setCellStyle(styles.get("rcSubTotal"));
			cellLocalEmpty.setCellValue("");
			
			/*Cell cellDeputedEmpty = subTotalRow.createCell(13);
			cellDeputedEmpty.setCellStyle(styles.get("rcSubTotal"));
			cellDeputedEmpty.setCellValue("");*/
			
			Cell cellUsagePer = subTotalRow.createCell(13);
			cellUsagePer.setCellStyle(styles.get("rcSubTotal"));
			cellUsagePer.setCellValue(new BigDecimal(dblOnsiteUsagePer).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellMasterRate = subTotalRow.createCell(14);
			cellMasterRate.setCellStyle(styles.get("rcSubTotal"));
			//cellMasterRate.setCellValue(new BigDecimal(dblOnsiteMasterRate).setScale(2, RoundingMode.HALF_UP).toString());
			cellMasterRate.setCellValue(new BigDecimal(isBillCurrency ? (dblOnsiteMasterRate * multifier) : dblOnsiteMasterRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellMasterRateGMPer = subTotalRow.createCell(15);
			cellMasterRateGMPer.setCellStyle(styles.get("rcSubTotal"));
			dblTotalCost = dblLocalCost;
			cellMasterRateGMPer.setCellValue(getGMPercent(dblMasterRateRevenue,dblTotalCost));
			
			Cell cellProClientRate = subTotalRow.createCell(16);
			cellProClientRate.setCellStyle(styles.get("rcSubTotal"));
			//dblOnsiteProClientRate = new BigDecimal(dblOnsiteProClientRate).setScale(2, RoundingMode.HALF_UP).doubleValue();
			dblOnsiteProClientRate = isBillCurrency ? (dblOnsiteProClientRate * multifier) : dblOnsiteProClientRate;
			cellProClientRate.setCellValue(new BigDecimal(dblOnsiteProClientRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellDiscPremium = subTotalRow.createCell(17);
			cellDiscPremium.setCellStyle(styles.get("rcSubTotal"));
			dblOnsiteDiscPremium =  dblOnsiteMasterRateTotal==0.00 ? 0.00 : -((((dblOnsiteMasterRateTotal - dblOnsiteProposedRate)/dblOnsiteMasterRateTotal) * 100) * 100)/100;
			if(Double.isNaN(dblOnsiteDiscPremium))
				dblOnsiteDiscPremium = 0.0;
			cellDiscPremium.setCellValue(new BigDecimal(dblOnsiteDiscPremium).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellGMPer = subTotalRow.createCell(18);
			cellGMPer.setCellStyle(styles.get("rcSubTotal"));
			
			cellGMPer.setCellValue(getGMPercent(dblActualRateRevenue,dblTotalCost));
		}
		else if(intOnsitePercentage == 0)
		{
			//Offshore data
			Cell cellOffShoreUsagePer = subTotalRow.createCell(12);
			cellOffShoreUsagePer.setCellStyle(styles.get("rcSubTotal"));
			cellOffShoreUsagePer.setCellValue(new BigDecimal(dblOffshoreUsage).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreMasterRate = subTotalRow.createCell(13);
			cellOffShoreMasterRate.setCellStyle(styles.get("rcSubTotal"));
			dblOffshorMasterRate = (dblOffshorMasterRate * 100)==0.00 ? 0.00:(dblOffshorMasterRate * 100)/100;
			//cellOffShoreMasterRate.setCellValue(dblOffshorMasterRate.toString());
			Double dblOffShoreMasterRateCalc = isBillCurrency ? (dblOffshorMasterRate * multifier) : dblOffshorMasterRate;
			cellOffShoreMasterRate.setCellValue(new BigDecimal(dblOffShoreMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreMasterRateGMPer = subTotalRow.createCell(14);
			cellOffShoreMasterRateGMPer.setCellStyle(styles.get("rcSubTotal"));
			cellOffShoreMasterRateGMPer.setCellValue(getGMPercent(dblOffshoreMasterRateRevenue,dblOffshoreTotalCost));
			
			Cell cellOffShoreProClientRate = subTotalRow.createCell(15);
			cellOffShoreProClientRate.setCellStyle(styles.get("rcSubTotal"));
			dblOffshorePropClientRate = (dblOffshorePropClientRate * 100)==0.00? 0.00:(dblOffshorePropClientRate * 100)/100;
			//cellOffShoreProClientRate.setCellValue(dblOffshorePropClientRate.toString());
			Double dblOffshorePropClientRateCalc = isBillCurrency ? (dblOffshorePropClientRate * multifier) : dblOffshorePropClientRate;
			cellOffShoreProClientRate.setCellValue(new BigDecimal(dblOffshorePropClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreDiscPremium = subTotalRow.createCell(16);
			cellOffShoreDiscPremium.setCellStyle(styles.get("rcSubTotal"));
			dblOffshoreDiscPremium = dblOffshoreMasterRateTotal==0.00?0.00:-((((dblOffshoreMasterRateTotal - dblOffshoreProposedRate)/dblOffshoreMasterRateTotal)*100)*100)/100;	    						 
			if(Double.isNaN(dblOffshoreDiscPremium))
				dblOffshoreDiscPremium = 0.0;
			cellOffShoreDiscPremium.setCellValue(new BigDecimal(dblOffshoreDiscPremium).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreGMPer = subTotalRow.createCell(17);
			cellOffShoreGMPer.setCellStyle(styles.get("rcSubTotal"));
			//dblOffshoreGMPercent = Double.parseDouble(getGMPercent(dblOffshoreActualRateRevenue,dblOffshoreTotalCost));
			dblOffshoreGMPercent = getGMPercent(dblOffshoreActualRateRevenue,dblOffshoreTotalCost);
			dblOffshoreGMPercent = (dblOffshoreGMPercent * 100)==0.00 ? 0.00: (dblOffshoreGMPercent * 100)/100;
			cellOffShoreGMPer.setCellValue(new BigDecimal(dblOffshoreGMPercent).setScale(2, RoundingMode.HALF_UP).doubleValue());
		}
		else
		{
			Cell cellLocalEmpty = subTotalRow.createCell(12);
			cellLocalEmpty.setCellStyle(styles.get("rcSubTotal"));
			cellLocalEmpty.setCellValue("");
			
			/*Cell cellDeputedEmpty = subTotalRow.createCell(13);
			cellDeputedEmpty.setCellStyle(styles.get("rcSubTotal"));
			cellDeputedEmpty.setCellValue("");
			*/
			Cell cellUsagePer = subTotalRow.createCell(13);
			cellUsagePer.setCellStyle(styles.get("rcSubTotal"));
			cellUsagePer.setCellValue(new BigDecimal(dblOnsiteUsagePer).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellMasterRate = subTotalRow.createCell(14);
			cellMasterRate.setCellStyle(styles.get("rcSubTotal"));
			dblOnsiteMasterRate = isBillCurrency ? (dblOnsiteMasterRate * multifier) : dblOnsiteMasterRate;
			cellMasterRate.setCellValue(new BigDecimal(dblOnsiteMasterRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellMasterRateGMPer = subTotalRow.createCell(15);
			cellMasterRateGMPer.setCellStyle(styles.get("rcSubTotal"));
			dblTotalCost = dblLocalCost ;
			cellMasterRateGMPer.setCellValue(getGMPercent(dblMasterRateRevenue,dblTotalCost));
			
			Cell cellProClientRate = subTotalRow.createCell(16);
			cellProClientRate.setCellStyle(styles.get("rcSubTotal"));
			dblOnsiteProClientRate = isBillCurrency ? (dblOnsiteProClientRate * multifier) : dblOnsiteProClientRate;
			cellProClientRate.setCellValue(new BigDecimal(dblOnsiteProClientRate).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellDiscPremium = subTotalRow.createCell(17);
			cellDiscPremium.setCellStyle(styles.get("rcSubTotal"));
			dblOnsiteDiscPremium =  dblOnsiteMasterRateTotal==0.00? 0.00 : - ((((dblOnsiteMasterRateTotal - dblOnsiteProposedRate)/dblOnsiteMasterRateTotal) * 100) * 100)/100;
			if(Double.isNaN(dblOnsiteDiscPremium))
				dblOnsiteDiscPremium = 0.0;
			cellDiscPremium.setCellValue(new BigDecimal(dblOnsiteDiscPremium).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellGMPer = subTotalRow.createCell(18);
			cellGMPer.setCellStyle(styles.get("rcSubTotal"));
			cellGMPer.setCellValue(getGMPercent(dblActualRateRevenue,dblTotalCost));
			
			//Offshore data
			Cell cellOffShoreUsagePer = subTotalRow.createCell(19);
			cellOffShoreUsagePer.setCellStyle(styles.get("rcSubTotal"));
			cellOffShoreUsagePer.setCellValue(new BigDecimal(dblOffshoreUsage).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreMasterRate = subTotalRow.createCell(20);
			cellOffShoreMasterRate.setCellStyle(styles.get("rcSubTotal"));
			dblOffshorMasterRate = (dblOffshorMasterRate * 100)==0.00? 0.00 : (dblOffshorMasterRate * 100)/100;
			//cellOffShoreMasterRate.setCellValue(dblOffshorMasterRate.toString());
			Double dblOffShoreMasterRateCalc = isBillCurrency ? (dblOffshorMasterRate * multifier) : dblOffshorMasterRate;
			cellOffShoreMasterRate.setCellValue(new BigDecimal(dblOffShoreMasterRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreMasterRateGMPer = subTotalRow.createCell(21);
			cellOffShoreMasterRateGMPer.setCellStyle(styles.get("rcSubTotal"));
			cellOffShoreMasterRateGMPer.setCellValue(getGMPercent(dblOffshoreMasterRateRevenue,dblOffshoreTotalCost));
			
			Cell cellOffShoreProClientRate = subTotalRow.createCell(22);
			cellOffShoreProClientRate.setCellStyle(styles.get("rcSubTotal"));
			dblOffshorePropClientRate = (dblOffshorePropClientRate * 100)==0.00 ? 0.00 : (dblOffshorePropClientRate * 100)/100;
			//cellOffShoreProClientRate.setCellValue(dblOffshorePropClientRate.toString());
			Double dblOffshorePropClientRateCalc = isBillCurrency ? (dblOffshorePropClientRate * multifier) : dblOffshorePropClientRate;
			cellOffShoreProClientRate.setCellValue(new BigDecimal(dblOffshorePropClientRateCalc).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreDiscPremium = subTotalRow.createCell(23);
			cellOffShoreDiscPremium.setCellStyle(styles.get("rcSubTotal"));
			dblOffshoreDiscPremium = dblOffshoreMasterRateTotal==0.00? 0.00: -((((dblOffshoreMasterRateTotal - dblOffshoreProposedRate)/dblOffshoreMasterRateTotal)*100)*100)/100;	    						 
			if(Double.isNaN(dblOffshoreDiscPremium))
				dblOffshoreDiscPremium = 0.0;
			cellOffShoreDiscPremium.setCellValue(new BigDecimal(dblOffshoreDiscPremium).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
			Cell cellOffShoreGMPer = subTotalRow.createCell(24);
			cellOffShoreGMPer.setCellStyle(styles.get("rcSubTotal"));
			//dblOffshoreGMPercent = Double.parseDouble(getGMPercent(dblOffshoreActualRateRevenue,dblOffshoreTotalCost));
			dblOffshoreGMPercent = getGMPercent(dblOffshoreActualRateRevenue,dblOffshoreTotalCost);
			dblOffshoreGMPercent = (dblOffshoreGMPercent * 100) == 0.00 ? 0.00 : (dblOffshoreGMPercent * 100)/100;
			cellOffShoreGMPer.setCellValue(new BigDecimal(dblOffshoreGMPercent).setScale(2, RoundingMode.HALF_UP).doubleValue());
			
		}
	}


	private static void createHeaders(Row headerRow, Sheet sheet, List<RateCardDetails> rateCardDetails, Map<String, CellStyle> styles, Integer rowCount, List<RateCardLocation> rcLocDetails) {
		Double intOnsitePercentage = rateCardDetails.get(0).getExpectedOnsiteResourcePercentage();
		if(intOnsitePercentage == 100 || rcLocDetails.get(0).getIsOffShore().equals("N"))
		{
			Cell cellUtiOn11 = headerRow.createCell(12);
			cellUtiOn11.setCellValue("Onsite");
			cellUtiOn11.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn12 = headerRow.createCell(13);
			cellUtiOn12.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn13 = headerRow.createCell(14);
			cellUtiOn13.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn14 = headerRow.createCell(15);
			cellUtiOn14.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn15 = headerRow.createCell(16);
			cellUtiOn15.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn16 = headerRow.createCell(17);
			cellUtiOn16.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn17 = headerRow.createCell(18);
			cellUtiOn17.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn18 = headerRow.createCell(19);
			cellUtiOn18.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			
			
			sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,12,19));
			
			final String[] OnsiteHeaderList = {"Master Role Code", "Practice", "Sub Practice","Role Description","Proficiency","Syntel Band/Grade","GCM Level","X.O Skills","X.O Elements","X.O Knowledge","Syntel Role","Customer Role",
					"Local (%)","Onsite Usage (%)","Onsite Master Rate","Onsite Master Rate PM (%)","Onsite Proposed Client Rate","Discount (-) / Premium (+)","Onsite PM (%)"};
			
			
			rowCount++;
			
			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < OnsiteHeaderList.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(OnsiteHeaderList[i]);
					headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
			}
		}
		else if(intOnsitePercentage == 0)
		{
			Cell cellUtiOn11 = headerRow.createCell(12);
			cellUtiOn11.setCellValue("Offshore");
			cellUtiOn11.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn12 = headerRow.createCell(13);
			cellUtiOn12.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn13 = headerRow.createCell(14);
			cellUtiOn13.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn14 = headerRow.createCell(15);
			cellUtiOn14.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn15 = headerRow.createCell(16);
			cellUtiOn15.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn16 = headerRow.createCell(17);
			cellUtiOn16.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			
			sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,12,17));
			
			final String[] offshoreHeaderList = {"Master Role Code", "Practice", "Sub Practice","Role Description","Proficiency","Syntel Band/Grade","GCM Level","X.O Skills","X.O Elements","X.O Knowledge","Syntel Role","Customer Role",					
					"Offshore Usage (%)","Offshore Master Rate","Offshore Master Rate PM (%)","Offshore Proposed Client Rate","Discount (-) / Premium (+)","Offshore PM (%)"};
			
			rowCount++;
			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < offshoreHeaderList.length; i++) 
			{
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(offshoreHeaderList[i]);
//				if(i < 11)
					headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
	/*			else
					headerCell1.setCellStyle(styles.get("RowOffshoreRCHeader"));
			*/}
		}
		else
		{
			Cell cellUtiOn0 = headerRow.createCell(0);
			cellUtiOn0.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn1 = headerRow.createCell(1);
			cellUtiOn1.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn2 = headerRow.createCell(2);
			cellUtiOn2.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn3 = headerRow.createCell(3);
			cellUtiOn3.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn4 = headerRow.createCell(4);
			cellUtiOn4.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn5 = headerRow.createCell(5);
			cellUtiOn5.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn6 = headerRow.createCell(6);
			cellUtiOn6.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn7 = headerRow.createCell(7);
			cellUtiOn7.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn8 = headerRow.createCell(8);
			cellUtiOn8.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn9 = headerRow.createCell(9);
			cellUtiOn9.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn10 = headerRow.createCell(10);
			cellUtiOn10.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn11 = headerRow.createCell(11);
			cellUtiOn11.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn12 = headerRow.createCell(12);
			cellUtiOn12.setCellValue("Onsite");
			cellUtiOn12.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn13 = headerRow.createCell(13);
			cellUtiOn13.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn14 = headerRow.createCell(14);
			cellUtiOn14.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn15 = headerRow.createCell(15);
			cellUtiOn15.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn16 = headerRow.createCell(16);
			cellUtiOn16.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn17 = headerRow.createCell(17);
			cellUtiOn17.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn18 = headerRow.createCell(18);
			cellUtiOn18.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOn19 = headerRow.createCell(19);
			cellUtiOn19.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			
			sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,12,19));
			
			Cell cellUtiOff = headerRow.createCell(20);
			cellUtiOff.setCellValue("Offshore");
			cellUtiOff.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOff20 = headerRow.createCell(21);
			cellUtiOff20.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOff21 = headerRow.createCell(22);
			cellUtiOff21.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOff22 = headerRow.createCell(23);
			cellUtiOff22.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOff23 = headerRow.createCell(24);
			cellUtiOff23.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			Cell cellUtiOff24 = headerRow.createCell(25);
			cellUtiOff24.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
			
			
			sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,20,25));
			
			final String[] headerList = {"Master Role Code", "Practice", "Sub Practice","Role Description","Proficiency","Syntel Band/Grade","GCM Level","X.O Skills","X.O Elements","X.O Knowledge","Syntel Role","Customer Role",
					"Local (%)","Onsite Usage (%)","Onsite Master Rate","Onsite Master Rate PM (%)","Onsite Proposed Client Rate","Discount (-) / Premium (+)","Onsite PM (%)",
					"Offshore Usage (%)","Offshore Master Rate","Offshore Master Rate PM (%)","Offshore Proposed Client Rate","Discount (-) / Premium (+)","Offshore PM (%)"};
			rowCount++;
			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) 
			{
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList[i]);
			//	if(i < 19)
					headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
			/*	else
					headerCell1.setCellStyle(styles.get("RowOffshoreRCHeader"));
			*/}
		}
		
	}


	private static Double getActualGmPercentOffshore(RateCardRoleUtilization rateCardRoleUtilization) 
	{		 
		Double actualRateRevenue = rateCardRoleUtilization.getOffshoreHours() * rateCardRoleUtilization.getOffshoreProposedClientRate();
		return getGMPercent(actualRateRevenue,rateCardRoleUtilization.getOffshoreCost());
	}

	private static Double getActualGmPercent(RateCardRoleUtilization rateCardRoleUtilization, Double onsiteProposedClientRate) 
	{
		Double actualRateRevenue = rateCardRoleUtilization.getDeputedHour() * onsiteProposedClientRate;
    	Double totalCost = ((rateCardRoleUtilization.getDomesticCost() * rateCardRoleUtilization.getDomesticUsagePercent())/100.00) + ((rateCardRoleUtilization.getDeputedCost() * (100.00 - rateCardRoleUtilization.getDomesticUsagePercent()))/100.00);
		return getGMPercent(actualRateRevenue,totalCost);
	}

	private static Double getGMPercent(Double actualRateRevenue, Double totalCost) 
	{
		Double dblMasterRateGMPer = ((actualRateRevenue-totalCost)/actualRateRevenue)*100.00; 
		if(Double.isNaN(dblMasterRateGMPer))
			dblMasterRateGMPer = 0.0;
		return dblMasterRateGMPer;
	}

	private static Double calculateGMValue(RateCardRoleUtilization rateCardRoleUtilization, Double domesticUsagePercent) 
	{
		Double  totalCost = ((rateCardRoleUtilization.getDomesticCost() * domesticUsagePercent)/100.00) + ((rateCardRoleUtilization.getDeputedCost() * (100.00-domesticUsagePercent))/100.00);
		Double  masterGm = ((rateCardRoleUtilization.getDomesticRevenue() - totalCost)/rateCardRoleUtilization.getDomesticRevenue())*100.00;
		if(Double.isNaN(masterGm))
			masterGm = 0.0;
		return masterGm;
	}
	
	private static Double calculateDiscount (Double proposedClientRate,Double masterRate)
	{
		Double onsiteDiscount = (double) (-(100 - ((proposedClientRate / masterRate)) * 100.00));
		return  onsiteDiscount;
	}
	
	private static Integer createDealPanel(Sheet sheet, Integer rowCount, List<DealDetailsView> dealList,Map<String, CellStyle> styles) 
	{

		// Deal panel Start
			Row rcPanel1 = sheet.createRow(rowCount);
			Cell cellRCIDTitle = rcPanel1.createCell(0);
			cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCIDTitle.setCellValue("Deal Id");
			
			Cell cellRCID = rcPanel1.createCell(1);
			cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellRCID.setCellValue(dealList.get(0).getDealId());
			
			
			Cell cellRCNameTitle = rcPanel1.createCell(2);
			cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCNameTitle.setCellValue("Deal Version (ID)");
			
			Cell cellRCName = rcPanel1.createCell(3);
			cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCName.setCellValue(dealList.get(0).getDealVersion());
			
			Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
			cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCApprStatusTitle.setCellValue("Customer Name");
			
			Cell cellRCApprStatus = rcPanel1.createCell(5);
			cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCApprStatus.setCellValue(dealList.get(0).getCustomerName());
			
			rowCount++;
			
			//Row 2
			Row rcPanel2 = sheet.createRow(rowCount);
			Cell cellDealStatusTitle = rcPanel2.createCell(0);
			cellDealStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellDealStatusTitle.setCellValue("Deal Status");
			
			Cell cellDealStatus= rcPanel2.createCell(1);
			cellDealStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellDealStatus.setCellValue(dealList.get(0).getDealStatus());
			
			
			Cell cellProjectTitle = rcPanel2.createCell(2);
			cellProjectTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellProjectTitle.setCellValue("Project Type");
			
			if(dealList.get(0).getDealTypeId()==1){
				if(dealList.get(0).getFpType()==0){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue("Development -  Fixed Price");   			//project Type remoaining
				}
				else if(dealList.get(0).getFpType()==1){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue( "Maintenance -  Fixed Price");   			//project Type remoaining
				}
			}
			else if(dealList.get(0).getDealTypeId()==2){
				if(dealList.get(0).getFpType()==0){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue("Development -  T&M");   			//project Type remoaining
				}
				else if(dealList.get(0).getFpType()==1){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue( "Maintenance -  T&M");   			//project Type remoaining
				}
			}
			
		    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
			cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCAppMonthsTitle.setCellValue("Deal Description");
			
			Cell cellRCAppMonths = rcPanel2.createCell(5);
			cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCAppMonths.setCellValue(dealList.get(0).getDealDescription());
			
			rowCount++;
			
			//Row 3
			Row rcPanel3 = sheet.createRow(rowCount);
			Cell cellStartDateTitle = rcPanel3.createCell(0);
			cellStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellStartDateTitle.setCellValue("Start Date (dd/mm/yyyy)");
			
			Cell cellStartDate = rcPanel3.createCell(1);
			cellStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			String strStartDate[] = dealList.get(0).getDealStartDate().split(" ");
			String startDate[] =  strStartDate[0].split("-");
			String strStartYr = startDate[0];
			String strStartMonth = startDate[1];
			String strStartDay = startDate[2];		
			cellStartDate.setCellValue(strStartDay+ "/"+strStartMonth+ "/"+strStartYr);
			
			Cell cellEndDateTitle = rcPanel3.createCell(2);
			cellEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellEndDateTitle.setCellValue("Start Date (dd/mm/yyyy)");
			
			Cell cellEndDate = rcPanel3.createCell(3);
			cellEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			String strEndDate[] = dealList.get(0).getDealEndDate().split(" ");
			String EndDate[] =  strEndDate[0].split("-");
			String strEndYr = EndDate[0];
			String strEndMonth = EndDate[1];
			String strEndDay = EndDate[2];		
			cellEndDate.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
			
		    Cell cellDiscTitle = rcPanel3.createCell(4);
		    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellDiscTitle.setCellValue("Percentage Close (%)");
			
			Cell cellDisc = rcPanel3.createCell(5);			
			cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			cellDisc.setCellValue(dealList.get(0).getPercentageClose() + "%");
			
			rowCount++;
			
			//Row 4
			Row rcPanel4 = sheet.createRow(rowCount);
			Cell cellDealTCVTitle = rcPanel4.createCell(0);
			cellDealTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellDealTCVTitle.setCellValue("Deal TCV");
			
			Cell cellOnUti = rcPanel4.createCell(1);
			cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellOnUti.setCellValue(dealList.get(0).getDealTCV());
			
			
			Cell cellOffUtiTitle = rcPanel4.createCell(2);
			cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOffUtiTitle.setCellValue("Currency");
			
			Cell cellOffUti = rcPanel4.createCell(3);
			cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellOffUti.setCellValue(dealList.get(0).getCurrency());
						
			
		    Cell cellPerTitle = rcPanel4.createCell(4);
		    cellPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellPerTitle.setCellValue("Penalty (%)");
			
			Cell cellPer = rcPanel4.createCell(5);
			cellPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellPer.setCellValue(dealList.get(0).getPenaltyPercentage());
			
			//Deal panel end
	
	
		return rowCount;
		
	}
	public static byte[] getCostExcelReport(List<CostBreakup> cost,List<DealFixLocation> deal,List<DealDetailsView>  dealList, Map<String, List<String>> map) 
	{
	AppLoger.APPLOGGER.info("Inside the Excel Download function");
	// TODO Auto-generated method stub
	HSSFWorkbook wb = new HSSFWorkbook();
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
	//create excel xls sheet
	Map<String, CellStyle> styles = createStyles(wb);
    Sheet sheet = wb.createSheet("FP Deal Creation - Cost Summary");
    sheet.setFitToPage(true);
    sheet.setDisplayGridlines(false);
    // create style for header cells
    CellStyle style = wb.createCellStyle();
    Font font = wb.createFont();
    font.setFontName("Arial");
    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
    font.setBold(true);
    font.setColor(HSSFColor.WHITE.index);
    style.setFont(font);
    int rowVal=0;
    Row titleRow = sheet.createRow(rowVal);
	titleRow.setHeightInPoints(20);
	Cell titleCell = titleRow.createCell(0);
	titleCell.setCellValue("FP Deal Creation - Cost Summary");
	titleCell.setCellStyle(styles.get("Header"));
	
	Cell titleCell4 = titleRow.createCell(1);
	titleCell4.setCellStyle(styles.get("Header"));
	Cell titleCell5 = titleRow.createCell(2);
	titleCell5.setCellStyle(styles.get("Header"));
	Cell titleCell6 = titleRow.createCell(3);
	titleCell6.setCellStyle(styles.get("Header"));
	Cell titleCell7 = titleRow.createCell(4);
	titleCell7.setCellStyle(styles.get("Header"));
	Cell titleCell8 = titleRow.createCell(5);
	titleCell8.setCellStyle(styles.get("Header"));
	Cell titleCell9 = titleRow.createCell(6);
	titleCell9.setCellStyle(styles.get("Header"));
	Cell titleCell10 = titleRow.createCell(7);
	titleCell10.setCellStyle(styles.get("Header"));
	Cell titleCell11 = titleRow.createCell(8);
	titleCell11.setCellStyle(styles.get("Header"));
	Cell titleCell12 = titleRow.createCell(9);
	titleCell12.setCellStyle(styles.get("Header"));
	sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
			
	rowVal+=2;
	
	Row titleRow2 = sheet.createRow(rowVal);
	titleRow2.setHeightInPoints(16);
	Cell dealTitleCell = titleRow2.createCell(0);
	dealTitleCell.setCellValue("Deal Details");
	dealTitleCell.setCellStyle(styles.get("Header"));
	
	Cell dealTitleCell4 = titleRow2.createCell(1);
	dealTitleCell4.setCellStyle(styles.get("Header"));
	Cell dealTitleCell5 = titleRow2.createCell(2);
	dealTitleCell5.setCellStyle(styles.get("Header"));
	Cell dealTitleCell6 = titleRow2.createCell(3);
	dealTitleCell6.setCellStyle(styles.get("Header"));
	Cell dealTitleCell7 = titleRow2.createCell(4);
	dealTitleCell7.setCellStyle(styles.get("Header"));
	Cell dealTitleCell8 = titleRow2.createCell(5);
	dealTitleCell8.setCellStyle(styles.get("Header"));
	
	sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
	
	rowVal+=2;
	
	rowVal = createDealPanel(sheet,rowVal,dealList,styles);
	
	rowVal+=2;
	
	Row titleRow3 = sheet.createRow(rowVal);
	titleRow3.setHeightInPoints(16);
	Cell costTitleCell = titleRow3.createCell(0);
	costTitleCell.setCellValue("Cost Summary");
	costTitleCell.setCellStyle(styles.get("Header"));
	
	Cell costTitleCell4 = titleRow3.createCell(1);
	costTitleCell4.setCellStyle(styles.get("Header"));
	Cell costTitleCell5 = titleRow3.createCell(2);
	costTitleCell5.setCellStyle(styles.get("Header"));
	Cell costTitleCell6 = titleRow3.createCell(3);
	costTitleCell6.setCellStyle(styles.get("Header"));
	Cell costTitleCell7 = titleRow3.createCell(4);
	costTitleCell7.setCellStyle(styles.get("Header"));
	Cell costTitleCell8 = titleRow3.createCell(5);
	costTitleCell8.setCellStyle(styles.get("Header"));
	
	sheet.addMergedRegion(CellRangeAddress.valueOf("$A$10:$F$10"));
	
	final String[] headerList1 = new String[12];  
	headerList1[0]="Direct Cost Details";
	int headerval=1;
	int size=0;
	for(int i=0;i<cost.size();i++){
		if(cost.get(i).getCostCode()==101){
		}
		else{
			size=i;
			break;
		}
	}
	
		if(deal.get(0).getTransitionMonth()!=null){
			headerList1[headerval]="Transition";
			headerval++;
		}
		
		if(cost.get(size).getYEAR_1()!=null){
			headerList1[headerval]="Year 1";
					headerval++;
		}
		if(cost.get(size).getYEAR_2()!=null){
			headerList1[headerval]="Year 2";
					headerval++;
		}
		
		
		if(cost.get(size).getYEAR_3()!=null){
			headerList1[headerval]="Year 3";
					headerval++;
		}
		
		
		if(cost.get(size).getYEAR_4()!=null){
			headerList1[headerval]="Year 4";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_5()!=null){
			headerList1[headerval]="Year 5";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_6()!=null){
			headerList1[headerval]="Year 6";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_7()!=null){
			headerList1[headerval]="Year 7";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_8()!=null){
			headerList1[headerval]="Year 8";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_9()!=null){
			headerList1[headerval]="Year 9";
					headerval++;
		}
		
		if(cost.get(size).getYEAR_10()!=null){
			headerList1[headerval]="Year 10";
					headerval++;
		}
		
			headerList1[headerval]="Total";
			headerval++;
	
		
	
	rowVal+=2;
	Row headerRow = sheet.createRow(rowVal);
	headerRow.setHeightInPoints(16);
	Cell headerCell;
	for (int i = 0; i < headerval; i++) {
		headerCell = headerRow.createCell(i);
		headerCell.setCellValue(headerList1[i]);
		headerCell.setCellStyle(styles.get("RowRateCardHeader2"));
	}

	rowVal ++;
	
	if(deal.get(0).getTransitionMonth()==null){
	for(int i=0;i<cost.size();i++){
		cost.get(i).setTransistion(null);
	}
	}
	
	

	
		
			for(int i=0;i<cost.size();i++){
		
				
				String[] verticalList;
				int verticalval=1;
				if(deal.get(0).getTransitionMonth()!=null){
					
				verticalList = new String[19];  
				verticalList[0]= cost.get(i).getCostType();
				verticalList[verticalval]= cost.get(i).getTransistion()==null ? "" : cost.get(i).getTransistion().toString() ;					// transition will be shown when transition month is not null
				verticalval++;
				}
				else{
				verticalList = new String[18];  
				verticalList[0]= cost.get(i).getCostType();
				}
					
					if(cost.get(size).getYEAR_1()!=null){
						if(cost.get(i).getYEAR_1()!=null){
							verticalList[verticalval]=cost.get(i).getYEAR_1().toString();
							verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
						
					}
					if(cost.get(size).getYEAR_2()!=null){
						if(cost.get(i).getYEAR_2()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_2().toString() ;
						verticalval++;
					}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
				  }
					if(cost.get(size).getYEAR_3()!=null){
						if(cost.get(i).getYEAR_3()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_3().toString() ;
						verticalval++;
					}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
				  }		
					if(cost.get(size).getYEAR_4()!=null){
						if(cost.get(i).getYEAR_4()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_4().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_5()!=null){
						if(cost.get(i).getYEAR_5()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_5().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_6()!=null){
						
						if(cost.get(i).getYEAR_6()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_6().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_7()!=null){
						if(cost.get(i).getYEAR_7()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_7().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_8()!=null){
						if(cost.get(i).getYEAR_8()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_8().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_9()!=null){
						if(cost.get(i).getYEAR_9()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_9().toString() ;
						verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					if(cost.get(size).getYEAR_10()!=null){
						if(cost.get(i).getYEAR_10()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_10().toString() ;
						verticalval++;
					 }
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
					}
					
				    if(cost.get(i).getYearTotal()!=null){
				    	verticalList[verticalval]= cost.get(i).getYearTotal().toString();		//total will be shown if year 2 is not null
						verticalval++;
				    }
				    else{
				    verticalList[verticalval]= "";		//total will be shown if year 2 is not null
					verticalval++;
					}
					
									
			  Row header1 = sheet.createRow(rowVal);
			  for (int j = 0; j < verticalval; j++) {
				  if(!(cost.get(i).getCostCode().equals(19))){     										// If Total Inndicator is 1  make it bold 
				  if(j == 0){
					  header1.setHeightInPoints(16);
						Cell headerCell3;
						headerCell3 = header1.createCell(j);
						headerCell3.setCellValue(verticalList[j]);
						headerCell3.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				  }else{
					header1.setHeightInPoints(16);
					Cell headerCell3;
					headerCell3 = header1.createCell(j);
					  if(verticalList[j]!=""){
						  if(verticalList[j].equals("0.0") || verticalList[j].equals("0")){
							  headerCell3.setCellValue(new Integer(0));
						  }else{
							headerCell3.setCellValue(new Double(verticalList[j]));
						  }  
						  }else{
								 headerCell3.setCellValue(verticalList[j]);
							  }
					headerCell3.setCellStyle(styles.get("RowColumnRightAlignNumber"));
				  }
				  }
				  else{
					  if(j == 0){
						  header1.setHeightInPoints(16);
							Cell headerCell3;
							headerCell3 = header1.createCell(j);
							headerCell3.setCellValue(verticalList[j]);
							headerCell3.setCellStyle(styles.get("costBreakupLGreyBold"));
					  }else{
						
						header1.setHeightInPoints(16);
						Cell headerCell3;
						headerCell3 = header1.createCell(j);
						  if(verticalList[j]!=""){
							  if(verticalList[j].equals("0.0") || verticalList[j].equals("0") ){
								  headerCell3.setCellValue(new Integer(0));
							  }else{
								headerCell3.setCellValue(new Double(verticalList[j]));
							  }  
							  }else{
							 headerCell3.setCellValue(verticalList[j]);
						  }
						  
						headerCell3.setCellStyle(styles.get("costBreakupRGreyBold"));
					  }  
				  }
				}
			  if(cost.get(i).getCostCode().equals(21) || cost.get(i).getCostCode().equals(102))
			  {
				rowVal=rowVal+2;
			  }
			  else
			  {
				  rowVal++;
			  }
			  
			}
			
			rowVal=rowVal+2;
			final String[] headerList2 = new String[2];  
			headerList2[0]="FX Rates";
			headerList2[1]="Exchange rates";
			
			Row headerRow1 = sheet.createRow(rowVal);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			
			for(int i =0;i<headerList2.length;i++){
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList2[i]);
				headerCell1.setCellStyle(styles.get("RowRateCardHeader2"));
				
			}
			
			 rowVal++;

			 Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
				getAllData = map;
				List<String> exchRate = getAllData.get("1");
				List<String> currencyCode = getAllData.get("2");
				
			
				for(int i=0;i<exchRate.size();i++){
					List<String> verticalList=new ArrayList<String>();
					verticalList.add(currencyCode.get(i)+" - INR");
					verticalList.add(exchRate.get(i));
					 Row header1 = sheet.createRow(rowVal);
					  for (int j = 0; j < verticalList.size(); j++) {
						  
						  header1.setHeightInPoints(16);
							Cell headerCell3;
							headerCell3 = header1.createCell(j);
							if(j==0){
								headerCell3.setCellValue(verticalList.get(j));
								headerCell3.setCellStyle(styles.get("DataRowColumnLeftAlign"));
								
							}
							else{
								headerCell3.setCellValue(new Double( verticalList.get(j)));
								headerCell3.setCellStyle(styles.get("RowColumnRightAlignNumber"));	
							}
							
					  }
					  rowVal++;
					  
					}
					
				
				
						/*if(countryDatas.get(i).!=null){
							verticalList[verticalval]=cost.get(i).getYEAR_1().toString();
							verticalval++;
						}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
						
					
					if(cost.get(size).getYEAR_2()!=null){
						if(cost.get(i).getYEAR_2()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_2().toString() ;
						verticalval++;
					}
						else{
							verticalList[verticalval]="";
							verticalval++;
						}
				  }*/
					
					
			 
		
		
/*		else if(deal.get(0).getTransitionMonth()!=null && cost.get(size).getYEAR_2()==null){

			for(int i=0;i<cost.size();i++){
		
				final String[] verticalList = new String[13];  
				verticalList[0]= cost.get(i).getCostType();
				int verticalval=1;
				verticalList[verticalval]= cost.get(i).getTransistion().toString();
				verticalval++;
				
				if(cost.get(i).getYEAR_1()!=null){
					verticalList[verticalval]=cost.get(i).getYEAR_1().toString() ;
					verticalval++;
				}
				verticalList[verticalval]= cost.get(i).getYearTotal().toString();		//total will not be shown if year 2 is  null
				verticalval++;
					
				  Row header1 = sheet.createRow(rowVal);
					for (int j = 0; j < verticalval; j++) {
						header1.setHeightInPoints(16);
						Cell headerCell2;
						headerCell2 = header1.createCell(j);
						headerCell2.setCellValue(verticalList[j]);
						headerCell2.setCellStyle(styles.get("Row4"));			
					}
					rowVal++;
	    	 }
		}*/
		
/*		else if(deal.get(0).getTransitionMonth()==null && cost.get(size).getYEAR_2()!=null){
			for(int i=0;i<cost.size();i++){
		
				final String[] verticalList = new String[13];  
				verticalList[0]= cost.get(i).getCostType();
				int verticalval=1;
				verticalList[verticalval]= cost.get(i).getTransistionyear().toString();   // transition will be only shown if transition month isnt empty
				verticalval++;
					if(cost.get(size).getYEAR_1()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_1().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_2()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_2().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_3()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_3().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_4()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_4().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_5()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_5().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_6()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_6().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_7()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_7().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_8()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_8().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_9()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_9().toString() ;
						verticalval++;
					}
					if(cost.get(size).getYEAR_10()!=null){
						verticalList[verticalval]=cost.get(i).getYEAR_10().toString() ;
						verticalval++;
					}
					
						verticalList[verticalval]= cost.get(i).getYearTotal().toString();
						verticalval++;
			
			
						  Row header1 = sheet.createRow(rowVal);
							for (int j = 0; j < verticalval; j++) {
								header1.setHeightInPoints(16);
								Cell headerCell2;
								headerCell2 = header1.createCell(j);
								headerCell2.setCellValue(verticalList[j]);
								headerCell2.setCellStyle(styles.get("Row4"));			
							}
							rowVal++;
	    	 }
		}*/
		
/*			else if(deal.get(0).getTransitionMonth()==null && cost.get(size).getYEAR_2()==null){

				for(int i=0;i<cost.size();i++){
			
					final String[] verticalList = new String[13];  
					verticalList[0]= cost.get(i).getCostType();
					int verticalval=1;
					verticalList[verticalval]= cost.get(i).getTransistionyear().toString(); // transition month and year[2-10] and total will only be shown if year2 != null and transition month!=null
					verticalval++;
						if(cost.get(i).getYEAR_1()!=null){
							verticalList[verticalval]=cost.get(i).getYEAR_1().toString() ;
							verticalval++;
						}
				
						  Row header1 = sheet.createRow(rowVal);
							for (int j = 0; j < verticalval; j++) {
								header1.setHeightInPoints(16);
								Cell headerCell2;
								headerCell2 = header1.createCell(j);
								headerCell2.setCellValue(verticalList[j]);
								headerCell2.setCellStyle(styles.get("Row4"));			
							}
							rowVal++;
		    	 }
				
				
			}*/
    	  
     for(int columnIndex = 0; columnIndex < 12; columnIndex++) {
		     sheet.autoSizeColumn(columnIndex);			     
      }
	wb.write(out);
	wb.close();
	return out.toByteArray();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;
}
	

	private static void createHeaderDataRowFP(String[] list1,Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2) {
			for (int i = 0; i < list1.length; i++) {
				headerRow2.setHeightInPoints(16);
				Cell headerCell2;
				headerCell2 = headerRow2.createCell(i);
				if(i<=6 ){
				headerCell2.setCellValue(list1[i]);
				}else{
				if(!list1[i].equals("")){	
				headerCell2.setCellValue(Double.valueOf(list1[i]));
				}
				else{
					headerCell2.setCellValue(list1[i]);
				}
				}
				
				headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			}
			
		}

	private static void createHeaderDataRowFPTotal(String[] list1,Sheet sheet1, Map<String, CellStyle> styles, Row headerRow2,Integer rowCount) {
		for (int i = 0; i < list1.length; i++) {
			headerRow2.setHeightInPoints(16);
			Cell headerCell2;
			headerCell2 = headerRow2.createCell(i);
			if(i <= 6){
			headerCell2.setCellValue(list1[i]);
			}else{
				if(!(list1[i].equals(""))){
				headerCell2.setCellValue(Double.valueOf(list1[i]));
			}else{
				headerCell2.setCellValue(list1[i]);
			}
			}
			if(i == 0){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 1){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 2){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 3){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 4){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 5){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 6){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else if(i == 7){
				headerCell2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			}else{
				headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			}
		}
		sheet1.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,7));
	}

  public static byte[] downloadFPDealCreationRoleSelectionExcel(List<PersistedFpDealRoles> xODataList,List<DealContraactorRole> contractList, List<FpDeal> parentHeadingDataList,
			List<Deal> parentHeadingData1List, int dealAutoTowerId,List<FPDealTower> parentNooftowerList, List<Country> countryList,List<CityFP> cityList, List<MasterXOSkillElement> x0skillEList,List<Currency> currencyList,List<XOSkillMaster> x0skillList,List<X0KnowledgeMaster> x0KnowledgeList) 
	{
		AppLoger.APPLOGGER.info("Inside downloadFPDealCreationRoleSelectionExcel function");
		AppLoger.APPLOGGER.info("parentHeadingDataList....... "+ parentHeadingDataList);
		AppLoger.APPLOGGER.info("List of parentHeadingData1List....... "+ parentHeadingData1List);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = createStyles(wb);
		Integer counter = 0;
		int two=-1;
		try {
			//create excel xls sheet
			for(FPDealTower objYear: parentNooftowerList)
			{
				counter++;
				two++;
				String strSheetName = objYear.getTowerName(); 
				Sheet sheet = wb.createSheet(strSheetName);
				sheet.setFitToPage(true);
				createSheetFP(sheet,wb,objYear.getDealTowerId(),xODataList,styles,parentHeadingDataList,parentHeadingData1List,counter,dealAutoTowerId,parentNooftowerList,two,countryList,cityList,x0skillEList,currencyList,x0skillList,x0KnowledgeList);
			}
			wb.write(out);
			wb.close();
			return out.toByteArray();				
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}


	
	private static void createSheetFP(Sheet sheet, HSSFWorkbook wb, int autoTowerID,List<PersistedFpDealRoles> xODataList,
			Map<String, CellStyle> styles, List<FpDeal> parentHeadingDataList, List<Deal> parentHeadingData1List, int dealAutoTowerId,
			Integer counter,List<FPDealTower> parentNooftowerList,int two,List<Country> countryList,List<CityFP> cityList, List<MasterXOSkillElement> x0skillEList,List<Currency> currencyList,List<XOSkillMaster> x0skillList,List<X0KnowledgeMaster> x0KnowledgeList) 
	{
		 // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
		
		Integer rowCount = 0;
		rowCount = 1;
		
		rowCount = createCustomerPanelFP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealAutoTowerId,two,autoTowerID,countryList,cityList);
		//tow = createCustomerPanel1FP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealVersionId,dealAutoTowerId,tow);
		rowCount = rowCount+1;
		
		rowCount = createRCPanelFP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealAutoTowerId,autoTowerID,currencyList);
		rowCount = rowCount+2;
		
		Row headerRow = sheet.createRow(rowCount);
		headerRow.setHeightInPoints(16);
		
		createHeadersFP(headerRow,sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealAutoTowerId,two);

		rowCount = rowCount+2;
		
		createExcelDataRowsFP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealAutoTowerId,autoTowerID,x0skillEList,x0skillList,x0KnowledgeList);
		
				
		for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
		{
			sheet.autoSizeColumn(columnIndex);			     
		}
		
	}

	
	
	private static Integer createCustomerPanelFP(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter,List<PersistedFpDealRoles> xODataList,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList, List<Deal> parentHeadingData1List, int dealAutoTowerId, int tow,int autoTowerID,List<Country> countryList,List<CityFP> cityList) 
	{ 
		// Customer panel Start
	
		Row rcCustNamePanel = sheet.createRow(rowCount);
		Cell cellCustName = rcCustNamePanel.createCell(0);
		cellCustName.setCellStyle(styles.get("RowRateCardHeader2"));
		cellCustName.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getCustomer().getCustomerName());
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
		
		Cell cellCustName1 = rcCustNamePanel.createCell(1);
		cellCustName1.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName2 = rcCustNamePanel.createCell(2);
		cellCustName2.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName3 = rcCustNamePanel.createCell(3);
		cellCustName3.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName4 = rcCustNamePanel.createCell(4);
		cellCustName4.setCellStyle(styles.get("RowRateCardHeader2"));
		Cell cellCustName5 = rcCustNamePanel.createCell(5);
		cellCustName5.setCellStyle(styles.get("RowRateCardHeader2"));
		rowCount=rowCount+2;
		
		//Row 2
		Row rcRow2 = sheet.createRow(rowCount);
		Cell cellYR = rcRow2.createCell(0);
		cellYR.setCellStyle(styles.get("RowRateCardHeader"));		
		cellYR.setCellValue("Tower");
		//sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
		
		Cell cellYr1 = rcRow2.createCell(1);
		cellYr1.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellYr1.setCellValue(parentNooftowerList.get(tow).getTowerName());
		Cell cellYr2 = rcRow2.createCell(2);
		cellYr2.setCellStyle(styles.get("RowRateCardHeader"));
		cellYr2.setCellValue("Country");
		Cell cellYr3 = rcRow2.createCell(3);
		cellYr3.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellYr3.setCellValue(getCountryNameWithId(parentNooftowerList.get(tow).getCountryId(),countryList));
		Cell cellYr4 = rcRow2.createCell(4);
		cellYr4.setCellStyle(styles.get("RowRateCardHeader"));
		cellYr4.setCellValue("City");
		Cell cellYr5 = rcRow2.createCell(5);
		cellYr5.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellYr5.setCellValue(getCityNameWithId(parentNooftowerList.get(tow).getCityId(),cityList)+" ("+(parentNooftowerList.get(tow).getCityDisc())+")");
		return rowCount;
	}

private static String getCityNameWithId(Integer cityId, List<CityFP> cityList) {
		String citiName = null;
		for(CityFP cityData : cityList) {
			if(cityId.equals(cityData.getCityId())){
				citiName = cityData.getCityName();
			}
		}
		return citiName;
	}


	

	private static String getCountryNameWithId(Integer countryId, List<Country> countryList) {
		String countryName = null;
		for(Country countryData : countryList) {
			if(countryId.equals(countryData.getCountryId())){
				countryName = countryData.getCountryName();
			}
		}
		return countryName;
	}

	private static Integer createRCPanelFP(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount,Integer counter,List<PersistedFpDealRoles> xODataList,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList, List<Deal> parentHeadingData1List, int dealAutoTowerId,int autoTowerID,List<Currency> currencyList) 
	{
		// RC panel Start
			Row rcPanel1 = sheet.createRow(rowCount);
			Cell cellRCIDTitle = rcPanel1.createCell(0);
			cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCIDTitle.setCellValue("Deal Id");
			
			Cell cellRCID = rcPanel1.createCell(1);
			cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellRCID.setCellValue(parentHeadingDataList.get(0). getCrmDealId());
			
			
			Cell cellRCNameTitle = rcPanel1.createCell(2);
			cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCNameTitle.setCellValue("Deal Version (ID)");
			
			Cell cellRCName = rcPanel1.createCell(3);
			cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCName.setCellValue(parentHeadingDataList.get(0).getDealVersion()+"("+parentHeadingDataList.get(0).getRpDealVersionId()+")");
			
			Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
			cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCApprStatusTitle.setCellValue("Customer Name");
			
			Cell cellRCApprStatus = rcPanel1.createCell(5);
			cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCApprStatus.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getCustomer().getCustomerName());
			
			rowCount++;
			
			//Row 2
			Row rcPanel2 = sheet.createRow(rowCount);
			Cell cellRCStartDateTitle = rcPanel2.createCell(0);
			cellRCStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellRCStartDateTitle.setCellValue("Deal Status");
			
			Cell cellRCStartDate = rcPanel2.createCell(1);
			cellRCStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			if(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStatusId() == 1){
			cellRCStartDate.setCellValue("Open");
			}else if(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStatusId() == 2){
				cellRCStartDate.setCellValue("Close");	
			}else if(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStatusId() == 3){
				cellRCStartDate.setCellValue("Won");
			}else{
				cellRCStartDate.setCellValue("Lost");
			}
			
			
			Cell cellRCEndDateTitle = rcPanel2.createCell(2);
			cellRCEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCEndDateTitle.setCellValue("Project Type");
			
			Cell cellRCEndDate = rcPanel2.createCell(3);
			cellRCEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			if(parentHeadingDataList.get(0).getFpProjectTypeId() == 1){
		    cellRCEndDate.setCellValue("Development - Fixed Price");
			}else if(parentHeadingDataList.get(0).getFpProjectTypeId() == 2){
				cellRCEndDate.setCellValue("Development - Manage Capacity");
			}else if(parentHeadingDataList.get(0).getFpProjectTypeId() == 3){
				cellRCEndDate.setCellValue("Maintenance - Fixed Price");
			}else{
				cellRCEndDate.setCellValue("Maintenance - Manage Capacity");
			}
			
		    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
			cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCAppMonthsTitle.setCellValue("Deal Description");
			
			Cell cellRCAppMonths = rcPanel2.createCell(5);
			cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCAppMonths.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealDescription());
			
			rowCount++;
			
			//Row 3
			Row rcPanel3 = sheet.createRow(rowCount);
			Cell cellCustTitle = rcPanel3.createCell(0);
			cellCustTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellCustTitle.setCellValue("Start Date (dd/mm/yyyy)");
			
			Cell cellCust = rcPanel3.createCell(1);
			cellCust.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			String strDate[] =parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStartDate().split(" ");	
			String Date[] =  strDate[0].split("-");
			String strYr = Date[0];
			String strMonth = Date[1];
			String strDay = Date[2];
			cellCust.setCellValue(strDay+ "/"+strMonth+ "/"+strYr);
			
			
			Cell cellTCVTitle = rcPanel3.createCell(2);
			cellTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellTCVTitle.setCellValue("Expected End Date (dd/mm/yyyy)");
			
			Cell cellTCV = rcPanel3.createCell(3);
			cellTCV.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			String strEndDate[] = parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealEndDate2().split(" ");
			String EndDate[] =  strEndDate[0].split("-");
			String strEndYr = EndDate[0];
			String strEndMonth = EndDate[1];
			String strEndDay = EndDate[2];		
			cellTCV.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
			
			
		    Cell cellDiscTitle = rcPanel3.createCell(4);
		    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellDiscTitle.setCellValue("Percentage Close (%)");
			
			Cell cellDisc = rcPanel3.createCell(5);			
			cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			cellDisc.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getPercentageClose());
			
			rowCount++;
			
			//Row 4
			Row rcPanel4 = sheet.createRow(rowCount);
			Cell cellOnUtiTitle = rcPanel4.createCell(0);
			cellOnUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellOnUtiTitle.setCellValue("Deal TCV");
			
			Cell cellOnUti = rcPanel4.createCell(1);
			cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellOnUti.setCellValue(parentHeadingData1List.get(0).getDealTcv());
			
			
			Cell cellOffUtiTitle = rcPanel4.createCell(2);
			cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOffUtiTitle.setCellValue("Currency");
			
			Cell cellOffUti = rcPanel4.createCell(3);
			cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellOffUti.setCellValue(getCurrencyName(parentHeadingDataList.get(0).getCurrencyId(),currencyList));
						
			
		    Cell cellGMPerTitle = rcPanel4.createCell(4);
		    cellGMPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellGMPerTitle.setCellValue("Penalty (%)");
			
			Cell cellGMPer = rcPanel4.createCell(5);
			cellGMPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellGMPer.setCellValue(parentHeadingDataList.get(0).getPenaltyPercentage());

			rowCount++;
			//RC panel end
		return rowCount;
	}

	private static String getCurrencyName(Integer currencyId, List<Currency> currencyList) {
		String currencyname= null;
		for(Currency currencyData : currencyList) {
			if(currencyId.equals(currencyData.getCurrencyId())){
				currencyname = currencyData.getCurrencyName();
			}
		}
		return currencyname;
	}
	
	private static void createExcelDataRowsFP(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter,List<PersistedFpDealRoles> xODataList,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList, List<Deal> parentHeadingData1List, int dealAutoTowerId,int autoTowerID, List<MasterXOSkillElement> x0skillEList,List<XOSkillMaster> x0skillList,List<X0KnowledgeMaster> x0KnowledgeList) 
	{
		for(int i=0;i<xODataList.size();i++)
		{
			
			if(xODataList.get(i).getDealAutoTowerId()==autoTowerID){
				String[] verticalList =
					{	
						(getRoleId(xODataList.get(i).getRcId())),
						xODataList.get(i).getMasterRoleShortDescription(),
						xODataList.get(i).getSyntelRoleName(),
						xODataList.get(i).getProficiencyLevelDescription(),
						xODataList.get(i).getBandGrade(),
						(getx0NameWithId(xODataList.get(i).getxOSkillIndex(),x0skillList)),
						(getx0ENameWithId(xODataList.get(i).getxOSkillElementIndex(),x0skillEList)),
					    (getx0KNameWithid(xODataList.get(i).getxOKnowledgeIndex(),x0KnowledgeList)),
						xODataList.get(i).getClientRole(),
					};
				Row header = sheet.createRow(rowCount);
				createHeaderDataRowFP(verticalList,sheet,styles,header);
				rowCount++;
			}
		}
	}
	
	private static String getRoleId(Integer rcId) {
		if(rcId == 0){
		 return "Master Role";	
		}else
			return Integer.toString(rcId);
		
	}


	private static String getx0KNameWithid(Integer integer, List<X0KnowledgeMaster> x0KnowledgeList) {
		String x0KName = null;
		for(X0KnowledgeMaster x0skillEData : x0KnowledgeList) {
			if(integer.equals(x0skillEData.getKnowledgeId())){
				x0KName = x0skillEData.getKnowledgeName();
				AppLoger.APPLOGGER.info("ElementName ............ "+ x0KName);
			}
		}
		return x0KName;
	}


	private static String getx0NameWithId(Integer parseInt, List<XOSkillMaster> x0skillList) {
		String x0skillName = null;
		for(XOSkillMaster x0skillEData : x0skillList) {
			if(parseInt.equals(x0skillEData.getSkillId())){
				x0skillName = x0skillEData.getSkillName();
				AppLoger.APPLOGGER.info("ElementName ............ "+ x0skillName);
			}
		}
		return x0skillName;
	}


	private static String getx0ENameWithId(Integer getxOSkillElementIndex, List<MasterXOSkillElement> x0skillEList) {
		String x0skillEName = null;
		for(MasterXOSkillElement x0skillEData : x0skillEList) {
			if(getxOSkillElementIndex.equals(x0skillEData.getSkillElementId())){
				x0skillEName = x0skillEData.getElementName();
				AppLoger.APPLOGGER.info("ElementName ............ "+ x0skillEName);
			}
		}
		return x0skillEName;
	}


	private static void createHeadersFP(Row headerRow,Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter,List<PersistedFpDealRoles> xODataList,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList, List<Deal> parentHeadingData1List, int dealAutoTowerId,int two)
	{
			
		

		Cell cellUtiOn0 = headerRow.createCell(0);
		cellUtiOn0.setCellValue("Role Details Of"+" "+parentNooftowerList.get(two).getTowerName());
		cellUtiOn0.setCellStyle(styles.get("rcHeaderOnsiteUtil1"));
	    sheet.setDisplayGridlines(false);
		Cell cellUtiOn1 = headerRow.createCell(1);
		cellUtiOn1.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn2 = headerRow.createCell(2);
		cellUtiOn2.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn3 = headerRow.createCell(3);
		cellUtiOn3.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn4 = headerRow.createCell(4);
		cellUtiOn4.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn5 = headerRow.createCell(5);
		cellUtiOn5.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn6 = headerRow.createCell(6);
		cellUtiOn6.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn7 = headerRow.createCell(7);
		cellUtiOn7.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn8 = headerRow.createCell(8);
		cellUtiOn8.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
	    sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,8));
		
		
		final String[] headerList = {"Rate Card Id", "Master Role Code", "Syntel Role","Proficiency","Syntel Band/Grade","X.O Skills","X.O Elements","X.O Knowledge","Client Role"};
			rowCount++;
			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < headerList.length; i++) 
			{
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(headerList[i]);
			
					headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
			}
		}
	
	@SuppressWarnings("deprecation")
	public static byte[] getDashboardExcelReport(List<MyDashboardRC> RC,List<MyDashboardDeal> deal,String rateCardStatus ,String dealStatus, List<FPDealTower> towerDetails,List<AppCode> appCodedetails) 
	{
		AppLoger.APPLOGGER.info("Inside the Excel Download function getDashboardExcelReport");
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try 
		{
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
		    Sheet sheet = wb.createSheet("Rate Card Details");
		    sheet.setFitToPage(true);
		    sheet.setDisplayGridlines(false);
		    
		    // create style for header cells
		    CellStyle style = wb.createCellStyle();
		    Font font = wb.createFont();
		    font.setFontName("Arial");
		    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		    font.setBold(true);
		    font.setColor(HSSFColor.WHITE.index);
		    style.setFont(font);
		    int rowVal=0;
		    Row titleRow = sheet.createRow(rowVal);
			titleRow.setHeightInPoints(20);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellValue("Rate Card Details-"+rateCardStatus);
			titleCell.setCellStyle(styles.get("Header"));
			
			Cell titleCell4 = titleRow.createCell(1);
			titleCell4.setCellStyle(styles.get("Header"));
			Cell titleCell5 = titleRow.createCell(2);
			titleCell5.setCellStyle(styles.get("Header"));
			Cell titleCell6 = titleRow.createCell(3);
			titleCell6.setCellStyle(styles.get("Header"));
			Cell titleCell7 = titleRow.createCell(4);
			titleCell7.setCellStyle(styles.get("Header"));
			Cell titleCell8 = titleRow.createCell(5);
			titleCell8.setCellStyle(styles.get("Header"));
			Cell titleCell9 = titleRow.createCell(6);
			titleCell9.setCellStyle(styles.get("Header"));
			Cell titleCell10 = titleRow.createCell(7);
			titleCell10.setCellStyle(styles.get("Header"));
			Cell titleCell11 = titleRow.createCell(8);
			titleCell11.setCellStyle(styles.get("Header"));
			Cell titleCell12 = titleRow.createCell(9);
			titleCell12.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
			
			rowVal+=2;
			
			final String[] headerList1 = {"Rate Card Id","Customer","RC Name","Start Date (dd/mm/yyyy)","Expected End Date (dd/mm/yyyy)","Country","City","Expected TCV","Industry","Last Action Date","Delivery SPOC","Category"};
			
			rowVal+=2;
			Row headerRow = sheet.createRow(rowVal);
			headerRow.setHeightInPoints(16);
			Cell headerCell;
			for (int i = 0; i < headerList1.length; i++) 
			{
				headerCell = headerRow.createCell(i);
				headerCell.setCellValue(headerList1[i]);
				headerCell.setCellStyle(styles.get("RowRateCardHeader2"));
			}

			rowVal ++;
			final String[] verticalList = new String[12];
			
			for(int i=0;i<RC.size();i++)
			{	if(!(RC.get(i).equals(null))){
				verticalList[0]=RC.get(i).getRcId().toString();
				verticalList[1]=RC.get(i).getCustomer().getCustomerName();
				verticalList[2]=RC.get(i).getRcName();
				String startDate[]=RC.get(i).getRcStartDate().split(" ");
				String sDFormat[]=startDate[0].split("-");
				startDate[0]=sDFormat[2]+"/"+sDFormat[1]+"/"+sDFormat[0];
				verticalList[3]=startDate[0];
				String endDate[]=RC.get(i).getExpectedRCEndDate().split(" ");
				String eDFormat[]=endDate[0].split("-");
				endDate[0]=eDFormat[2]+"/"+eDFormat[1]+"/"+eDFormat[0];
				verticalList[4]=endDate[0];
				verticalList[5]=RC.get(i).getBaseCountryName();
				verticalList[6]=RC.get(i).getBaseCityName();
				verticalList[7]=RC.get(i).getTvc().toString();
		
				//appCodedetails.get(0).
				String strIndustry = null;
				for(int k=0;k<appCodedetails.size();k++){
					if(appCodedetails.get(k).getAppCode().equals("RP_Industry")){
						if(RC.get(i).getCustomer().getIndustryName()!=null || RC.get(i).getCustomer().getIndustryName() !="null" ){
							if(RC.get(i).getCustomer().getIndustryName().equals(appCodedetails.get(k).getDescription())){
								strIndustry = appCodedetails.get(k).getCodeType();
							}
						}
					}
				}
				
				if(strIndustry != null){
					if(RC.get(i).getIsItKpo()==1)
					{
						verticalList[8]= strIndustry+"_IT";
					}	
					else
					{
						verticalList[8]=strIndustry+"_KPO";
					}
				}
				else {
					if(RC.get(i).getIsItKpo()==1)
					{
						verticalList[8]= "IT";
					}	
					else
					{
						verticalList[8]="KPO";
					}
					
				}
				
				
				if(RC.get(i).getCurrentApprovalStatus()==1 || RC.get(i).getCurrentApprovalStatus()==4)
				{
					String LADate[]=RC.get(i).getCreatedOn().split(" ");
					String LADFormat[]=LADate[0].split("-");
					LADate[0]=LADFormat[2]+"/"+LADFormat[1]+"/"+LADFormat[0];
					verticalList[9]=LADate[0];	
					
				}
				else
				{
					String LADate[]=RC.get(i).getUpdatedOn().split(" ");
					String LADFormat[]=LADate[0].split("-");
					LADate[0]=LADFormat[2]+"/"+LADFormat[1]+"/"+LADFormat[0];
					verticalList[9]=LADate[0];
				}
				
				verticalList[10]=RC.get(i).getSpocName()!=null ? RC.get(i).getSpocName() : "";
				
				if(RC.get(i).getIsManualRc()!=null){
				if(RC.get(i).getIsManualRc().equals("H"))
				{
					verticalList[11]="Hybrid";
				}
				else if(RC.get(i).getIsManualRc().equals("A"))
				{
					verticalList[11]="Automatic";
				}
				else if(RC.get(i).getIsManualRc().equals("M"))
				{
					verticalList[11]="Manual";
				}
				else
				{
					verticalList[11]="NA";
				}
				}
				
				
				Row header1 = sheet.createRow(rowVal);
				for (int j = 0; j < verticalList.length ; j++) 
				{
					if((j==0 || j==3 || j==4 || j==7 || j==9)){
						if(j==3 || j==4 || j==9)
						{

							header1.setHeightInPoints(16);
							Cell headerCell2;
							headerCell2 = header1.createCell(j);
							headerCell2.setCellValue(verticalList[j]);
							headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumber"));
						}
						else
						{
							header1.setHeightInPoints(16);
							Cell headerCell2;
							headerCell2 = header1.createCell(j);
							Float value=new Float(verticalList[j]);
							headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
							headerCell2.setCellValue(value);
							headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNum"));
						}
					}
					else
					{
						header1.setHeightInPoints(16);
						Cell headerCell2;
						headerCell2 = header1.createCell(j);
						headerCell2.setCellValue(verticalList[j]);
						headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));

					}		
				}
				rowVal++;
			}
			}
			
// Deal Sheet Starts here
			
			Sheet dealSheet = wb.createSheet("Deal Details");
		    dealSheet.setFitToPage(true);
		    dealSheet.setDisplayGridlines(false);
		    
		    font.setFontName("Arial");
		    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		    font.setBold(true);
		    font.setColor(HSSFColor.WHITE.index);
		    style.setFont(font);
		    rowVal=0;
		    Row titledealRow = dealSheet.createRow(rowVal);
		    titledealRow.setHeightInPoints(20);
			Cell dealTitleCell = titledealRow.createCell(0);
			dealTitleCell.setCellValue("Deal Details-"+dealStatus);
			dealTitleCell.setCellStyle(styles.get("Header"));
			
			Cell dealTitleCell4 = titledealRow.createCell(1);
			dealTitleCell4.setCellStyle(styles.get("Header"));
			Cell dealTitleCell5 = titledealRow.createCell(2);
			dealTitleCell5.setCellStyle(styles.get("Header"));
			Cell dealTitleCell6 = titledealRow.createCell(3);
			dealTitleCell6.setCellStyle(styles.get("Header"));
			Cell dealTitleCell7 = titledealRow.createCell(4);
			dealTitleCell7.setCellStyle(styles.get("Header"));
			Cell dealTitleCell8 = titledealRow.createCell(5);
			dealTitleCell8.setCellStyle(styles.get("Header"));
			Cell dealTitleCell9 = titledealRow.createCell(6);
			dealTitleCell9.setCellStyle(styles.get("Header"));
			Cell dealTitleCell10 = titledealRow.createCell(7);
			dealTitleCell10.setCellStyle(styles.get("Header"));
			Cell dealTitleCell11 = titledealRow.createCell(8);
			dealTitleCell11.setCellStyle(styles.get("Header"));
			Cell dealTitleCell12 = titledealRow.createCell(9);
			dealTitleCell12.setCellStyle(styles.get("Header"));
			dealSheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$J$1"));
			
			rowVal+=2;
			
			final String[] dealHeaderList = {"Deal Id","Deal Version Name","Customer","Deal Description","Start Date (dd/mm/yyyy)","End Date (dd/mm/yyyy)","Tower Count","Billing Currency","Deal TCV","Industry","Category","Deal Type","Delivery SPOC","Last Action Date"};
			
			rowVal+=2;
			Row dealHeaderRow = dealSheet.createRow(rowVal);
			dealHeaderRow.setHeightInPoints(16);
			Cell dealHeaderCell;
			for (int i = 0; i < dealHeaderList.length; i++) 
			{
				dealHeaderCell = dealHeaderRow.createCell(i);
				dealHeaderCell.setCellValue(dealHeaderList[i]);
				dealHeaderCell.setCellStyle(styles.get("RowRateCardHeader2"));
			}

			rowVal ++;
			final String[] dealVerticalList = new String[14];
			
			List<MyDashboardDeal> dealTemp=new ArrayList();
			
		for(int i=0;i<deal.size();i++){
					if(deal.get(i).getIsNewDeal()==2)
					{
						dealTemp.add(deal.get(i));
					}
		}

		for(int i=0;i<deal.size();i++){
			if(deal.get(i).getIsNewDeal()==2)
			{
				deal.remove(i);
			}
			}
		
		for(int i=0;i<dealTemp.size();i++){
			deal.add(dealTemp.get(i));
		}
		
			for(int i=0;i<deal.size();i++)
			{	
				if(!(deal.get(i).equals(null))){
				AppLoger.APPLOGGER.info("Ith Value without error --- "+ i);	
				dealVerticalList[0]=deal.get(i).getCrmDealId();
				dealVerticalList[1]=deal.get(i).getDealVersion();
				dealVerticalList[2]=deal.get(i).getCustomer().getCustomerName();
				dealVerticalList[3]=deal.get(i).getDealcrmstagesdata2().getDealDescription();
				String startDate[]=deal.get(i).getDealStartDate().split(" ");
				String sDFormat[]=startDate[0].split("-");
				startDate[0]=sDFormat[2]+"/"+sDFormat[1]+"/"+sDFormat[0];
				dealVerticalList[4]=startDate[0];
				
				String endDate[]=deal.get(i).getDealEndDate().split(" ");
				String eDFormat[]=endDate[0].split("-");
				endDate[0]=eDFormat[2]+"/"+eDFormat[1]+"/"+eDFormat[0];
				dealVerticalList[5]=endDate[0];
				int count = 0;
						for(int j=0;j<(towerDetails.size()-1);j++){
						if(deal.get(i).getRpDealVersionId().equals(towerDetails.get(j).getDealVersionId())) {
							count++;        
						}
						}
						dealVerticalList[6]=""+count;

				dealVerticalList[7]=deal.get(i).getAppCode().getDescription();
				dealVerticalList[8]=deal.get(i).getDealTcv().toString();

				String strIndustry = null;
				for(int k=0;k<appCodedetails.size();k++){
					if(appCodedetails.get(k).getAppCode().equals("RP_Industry")){
						if(deal.get(i).getCustomer().getIndustryName()!=null || deal.get(i).getCustomer().getIndustryName() !="null" ){
							if(deal.get(i).getCustomer().getIndustryName().equals(appCodedetails.get(k).getDescription())){
								strIndustry = appCodedetails.get(k).getCodeType();
							}
						}
					}
				}
				
				if(strIndustry != null){
					if(deal.get(i).getProjectIndustry()==1)
					{
						dealVerticalList[9]=strIndustry+"_IT";
					}	
					else
					{
						dealVerticalList[9]=strIndustry+"_KPO";
					}
					
					
				}
				else {
					if(deal.get(i).getProjectIndustry()==1)
					{
						dealVerticalList[9]="IT";
					}	
					else
					{
						dealVerticalList[9]="KPO";
					}
					
					
				}

				
				
			
				switch(deal.get(i).getIsNewDeal())
				{
				case 0:
						dealVerticalList[10]="Renewal";
						break;
				case 1:
						dealVerticalList[10]="New";
						break;
				case 2:
						dealVerticalList[10]="RFP/RFI";
						break;
				}
				
				switch(deal.get(i).getDealTypeId())
				{
				case 1:
					if((deal.get(i).getFpProjectTypeId()==1 || deal.get(i).getFpProjectTypeId()==2 )&& deal.get(i).getIsNewDeal()==1){
						dealVerticalList[11]="FPD";
						break;
					}
					
					if((deal.get(i).getFpProjectTypeId()==3 || deal.get(i).getFpProjectTypeId()==4 )&& deal.get(i).getIsNewDeal()==1){
						dealVerticalList[11]="FPM";
						break;
					}	
					
					if((deal.get(i).getFpProjectTypeId()==1 || deal.get(i).getFpProjectTypeId()==2 )&& deal.get(i).getIsNewDeal()==0){
						dealVerticalList[11]="FPD";
						break;
					}
					
					if((deal.get(i).getFpProjectTypeId()==3 || deal.get(i).getFpProjectTypeId()==4 )&& deal.get(i).getIsNewDeal()==0){
						dealVerticalList[11]="FPM";
						break;
					}
					
					if((deal.get(i).getFpProjectTypeId()==1 || deal.get(i).getFpProjectTypeId()==2 )&& deal.get(i).getIsNewDeal()==2){
						dealVerticalList[11]="RFP/RFI";
						break;
					}
					
					if((deal.get(i).getFpProjectTypeId()==3 || deal.get(i).getFpProjectTypeId()==4 )&& deal.get(i).getIsNewDeal()==2){
						dealVerticalList[11]="RFP/RFI";
						break;
					}
					
				case 2:
						dealVerticalList[11]="T&M";
						break;
				}
				
					dealVerticalList[12]=deal.get(i).getDealcrmstagesdata2().getSalesSpoc();	
					if(deal.get(i).getStatusIndicator()!=null){
						
						if(deal.get(i).getStatusIndicator().equals("Draft") || deal.get(i).getStatusIndicator().equals("Recycled"))
							
						{
							String LADate[]=deal.get(i).getCreatedDate().split(" ");
							String LADFormat[]=LADate[0].split("-");
							LADate[0]=LADFormat[2]+"/"+LADFormat[1]+"/"+LADFormat[0];
							dealVerticalList[13]=LADate[0];	
						}
						else
						{
							String LADate[]=deal.get(i).getUpdatedDate().split(" ");
							String LADFormat[]=LADate[0].split("-");
							LADate[0]=LADFormat[2]+"/"+LADFormat[1]+"/"+LADFormat[0];
							dealVerticalList[13]=LADate[0];
						}
						
					}
					else{
						dealVerticalList[13]="";
						
					}
					
				Row header1 = dealSheet.createRow(rowVal);
				for (int j = 0; j < dealVerticalList.length ; j++) 
				{
					if(( j==4 || j==5 || j==6 || j==8 || j==14)){
						if(j==4 || j==5 || j==14)
						{

							header1.setHeightInPoints(16);
							Cell headerCell2;
							headerCell2 = header1.createCell(j);
							headerCell2.setCellValue(dealVerticalList[j]);
							headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumber"));
						}
						else
						{
							header1.setHeightInPoints(16);
							Cell headerCell2;
							headerCell2 = header1.createCell(j);
							Integer value=new Integer(dealVerticalList[j]);
							headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
							headerCell2.setCellValue(value);
							headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNum"));
						}
					}
					else
					{
						header1.setHeightInPoints(16);
						Cell headerCell2;
						headerCell2 = header1.createCell(j);
						headerCell2.setCellValue(dealVerticalList[j]);
						headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));

					}
				}
				rowVal++;
			}
		}
			
			
// Deal Sheet Ends here			
			
			
			
			
			for(int columnIndex = 0; columnIndex <= 15; columnIndex++) 
			{
				sheet.autoSizeColumn(columnIndex);
				dealSheet.autoSizeColumn(columnIndex);			     
			}
			wb.write(out);
			wb.close();
			return out.toByteArray();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	public static byte[] getFPdealCalculationExcelReport(List<FPCalculationDetails> rPRateList,List<DealDetailsView> dealList) 
	{
		AppLoger.APPLOGGER.info("Inside the Excel Download function getDashboardExcelReport");
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Map<String, CellStyle> styles = createStyles(wb);
		List<String> temp = new ArrayList<String>();
		int flag=1;
		int count = 0;
		int size = 0;
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader1() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader1());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader2() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader2());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader3() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader3());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader4() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader4());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader5() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader5());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader6() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader6());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader7() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader7());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader8() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader8());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader9() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader9());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader10() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader10());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader11() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader11());				
		}

		/*for (int i = 0; i < rPRateList.size(); i++) {
			temp.add(rPRateList.get(i).getMonthYearHeader());
		}
*/
		List<String> uniqueMYHeader = new ArrayList<String>(new HashSet<String>(temp));
		String[] MYHeader = new String[uniqueMYHeader.size()];
		temp.toArray(MYHeader);

		AppLoger.APPLOGGER.info(count);
		try {
		for (int l = 0; l < MYHeader.length; l++) {
			String monthYearHeader[] = new String[2];
			String startMonth[] = new String[2];
			String endMonth[] = new String[2];
			Sheet sheet = null ;
			String headerData=MYHeader[l];
			if((MYHeader[l].equals(""))){
				continue;
			}
			if (!(MYHeader[l].equals("Summary"))) {
				monthYearHeader = MYHeader[l].split("/");
				startMonth = monthYearHeader[0].split("-");
				endMonth = monthYearHeader[1].split("-");

				 sheet = wb.createSheet(monthYearHeader[0] + " to " + monthYearHeader[1]);
				sheet.setFitToPage(true);
			}
			else{
				 sheet = wb.createSheet(MYHeader[l]);
				sheet.setFitToPage(true);				
			}
				// create style for header cells
				CellStyle style = wb.createCellStyle();
				Font font = wb.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
				font.setBold(true);
				font.setColor(HSSFColor.WHITE.index);
				style.setFont(font);
				int rowVal = 0;
				Row titleRow = sheet.createRow(rowVal);
				titleRow.setHeightInPoints(20);
				Cell titleCell = titleRow.createCell(0);
				titleCell.setCellValue("FP Deal Creation - Calculation Details");
				titleCell.setCellStyle(styles.get("Header"));

				Cell titleCell4 = titleRow.createCell(1);
				titleCell4.setCellStyle(styles.get("Header"));
				Cell titleCell5 = titleRow.createCell(2);
				titleCell5.setCellStyle(styles.get("Header"));
				Cell titleCell6 = titleRow.createCell(3);
				titleCell6.setCellStyle(styles.get("Header"));
				Cell titleCell7 = titleRow.createCell(4);
				titleCell7.setCellStyle(styles.get("Header"));
				Cell titleCell8 = titleRow.createCell(5);
				titleCell8.setCellStyle(styles.get("Header"));
				Cell titleCell9 = titleRow.createCell(6);
				titleCell9.setCellStyle(styles.get("Header"));
				Cell titleCell10 = titleRow.createCell(7);
				titleCell10.setCellStyle(styles.get("Header"));
				Cell titleCell11 = titleRow.createCell(8);
				titleCell11.setCellStyle(styles.get("Header"));
				Cell titleCell12 = titleRow.createCell(9);
				titleCell12.setCellStyle(styles.get("Header"));
				Cell titleCell13 = titleRow.createCell(10);
				titleCell13.setCellStyle(styles.get("Header"));
				Cell titleCell14 = titleRow.createCell(11);
				titleCell14.setCellStyle(styles.get("Header"));
				Cell titleCell15 = titleRow.createCell(12);
				titleCell15.setCellStyle(styles.get("Header"));
				Cell titleCell16 = titleRow.createCell(13);
				titleCell16.setCellStyle(styles.get("Header"));
				sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));
				rowVal += 2;
				String monthsArray[]=new String[12];
				
				rowVal = costInputdealPanel(sheet,rowVal,dealList,styles);
				
				 int start = 0, end = 0;
				if (!(MYHeader[l].equals("Summary"))) 
				{
					switch (startMonth[0]) {
					case "Jan":	start = 1; break;
					case "Feb":	start = 2; break;
					case "Mar": start = 3; break;
					case "Apr":	start = 4; break;
					case "May": start = 5; break;
					case "Jun":	start = 6; break;
					case "Jul":	start = 7; break;
					case "Aug": start = 8; break;
					case "Sep": start = 9; break;
					case "Oct":	start = 10;break;
					case "Nov":	start = 11;break;
					case "Dec": start = 12;break;
					}
					
					switch (endMonth[0]) {
					case "Jan":	end = 1; break;
					case "Feb":	end = 2; break;
					case "Mar":	end = 3; break;
					case "Apr":	end = 4; break;
					case "May": end = 5; break;
					case "Jun":	end = 6; break;
					case "Jul":	end = 7; break;
					case "Aug":	end = 8; break;
					case "Sep":	end = 9; break;
					case "Oct":	end = 10;break;
					case "Nov":	end = 11;break;
					case "Dec":	end = 12;break;
					}

					int j = 0;
					if(!(start>end)){
						for (int i = start; i <= end; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}
					}
					else {

						for (int i = start; i <= 12; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}

						for (int i = 0; i <= end; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}
						
					
						
					}
					 
				}
				else
				{ int x=0;
					for(int i=0;i<rPRateList.size();i++)
					{
						if(rPRateList.get(i).getCostType().equals("Total Indirect Cost") && (rPRateList.get(i).getMonthYearHeader().equals("Summary")) )
						{
							x=i;
							break;
						}
						
					}
					if(rPRateList.get(x).getStaffingFirstMonthCount()!=null  ){
					 monthsArray[0] = "YR - 1";
					}
					if(rPRateList.get(x).getStaffingSecondMonthCount()!=null ){
						 monthsArray[1] = "YR - 2";
						}
					if(rPRateList.get(x).getStaffingThirdMonthCount()!=null ){
						 monthsArray[2] = "YR - 3";
						}
					if(rPRateList.get(x).getStaffingFourthMonthCount()!=null ){
						 monthsArray[3] = "YR - 4";
						}
					if(rPRateList.get(x).getStaffingFifthMonthCount()!=null ){
						 monthsArray[4] = "YR - 5";
						}
					if(rPRateList.get(x).getStaffingSixthMonthCount()!=null ){
						 monthsArray[5] = "YR - 6";
						}
					if(rPRateList.get(x).getStaffingSeventhMonthCount()!=null ){
						 monthsArray[6] = "YR - 7";
						}
					if(rPRateList.get(x).getStaffingEighthMonthCount()!=null ){
						 monthsArray[7] = "YR - 8";
						}
					if(rPRateList.get(x).getStaffingNinthMonthCount()!=null ){
						 monthsArray[8] = "YR - 9";
						}
					if(rPRateList.get(x).getStaffingTenthMonthCount()!=null ){
						 monthsArray[9] = "YR - 10";
						}
					if(rPRateList.get(x).getStaffingEleventhMonthCount()!=null ){
						 monthsArray[10] = "YR - 11";
						}
					if(rPRateList.get(x).getStaffingTwelthMonthCount()!=null ){
						 monthsArray[11] = "YR - 12";
						}
				}
					int j = 0;
					
					// int start = 0, end = 0;

					/*	switch (startMonth[0]) {
						case "Jan":	start = 1; break;
						case "Feb":	start = 2; break;
						case "Mar": start = 3; break;
						case "Apr":	start = 4; break;
						case "May": start = 5; break;
						case "Jun":	start = 6; break;
						case "Jul":	start = 7; break;
						case "Aug": start = 8; break;
						case "Sep": start = 9; break;
						case "Oct":	start = 10;break;
						case "Nov":	start = 11;break;
						case "Dec": start = 12;break;
						}

						switch (endMonth[0]) {
						case "Jan":	end = 1; break;
						case "Feb":	end = 2; break;
						case "Mar":	end = 3; break;
						case "Apr":	end = 4; break;
						case "May": end = 5; break;
						case "Jun":	end = 6; break;
						case "Jul":	end = 7; break;
						case "Aug":	end = 8; break;
						case "Sep":	end = 9; break;
						case "Oct":	end = 10;break;
						case "Nov":	end = 11;break;
						case "Dec":	end = 12;break;
						}
					for (int i = start; i <= end; i++) {

						if (i == 1) {
							monthsArray[j] = "Jan";
							j++;
						}
						if (i == 2) {
							monthsArray[j] = "Feb";
							j++;
						}
						if (i == 3) {
							monthsArray[j] = "Mar";
							j++;
						}
						if (i == 4) {
							monthsArray[j] = "Apr";
							j++;
						}
						if (i == 5) {
							monthsArray[j] = "May";
							j++;
						}
						if (i == 6) {
							monthsArray[j] = "Jun";
							j++;
						}
						if (i == 7) {
							monthsArray[j] = "Jul";
							j++;
						}
						if (i == 8) {
							monthsArray[j] = "Aug";
							j++;
						}
						if (i == 9) {
							monthsArray[j] = "Sep";
							j++;
						}
						if (i == 10) {
							monthsArray[j] = "Oct";
							j++;
						}
						if (i == 11) {
							monthsArray[j] = "Nov";
							j++;
						}
						if (i == 12) {
							monthsArray[j] = "Dec";
							j++;
						}

					}
*/
					AppLoger.APPLOGGER.info(monthsArray);
					int headerSize = 0;
					final String[] headerList1 = new String[14];
					final String[] headerList2 = new String[14];
					headerList1[headerSize] = "Particulars";
					headerSize++;
					for (int i = 0; i < monthsArray.length; i++) {
						headerList2[headerSize] = monthsArray[i];
						headerSize++;
					}
					headerList2[headerSize] = "Total";
					rowVal += 2;
					Row headerRow = sheet.createRow(rowVal);
					headerRow.setHeightInPoints(16);
					Cell headerCell;
					for (int i = 0; i < headerList2.length; i++) {
						headerCell = headerRow.createCell(i);
						headerCell.setCellValue(headerList1[i]);
						headerCell.setCellStyle(styles.get("RowRateCardHeader2"));
					}
					rowVal ++;
					
					Row headerRow2 = sheet.createRow(rowVal);
					headerRow2.setHeightInPoints(16);
					Cell headerCell2;
					for (int i = 0; i < headerList2.length; i++) {
						headerCell2 = headerRow2.createCell(i);
						headerCell2.setCellValue(headerList2[i]);
						headerCell2.setCellStyle(styles.get("rcHeader"));
					}

					final String[] verticalList = new String[14];
					for (int i = 0; i < rPRateList.size(); i++) {
						if (rPRateList.get(i).getTotalIndicator().equals(1)) 
						{
							if(rPRateList.get(i).getMonthYearHeader().equals(headerData)){
							verticalList[0] = rPRateList.get(i).getCostType();
							verticalList[1] = rPRateList.get(i).getStaffingFirstMonthCount() == null ? "      " :  rPRateList.get(i).getStaffingFirstMonthCount().toString();
							verticalList[2] = rPRateList.get(i).getStaffingSecondMonthCount() == null ? "      "  : rPRateList.get(i).getStaffingSecondMonthCount().toString();
							verticalList[3] = rPRateList.get(i).getStaffingThirdMonthCount() == null ? "      " : rPRateList.get(i).getStaffingThirdMonthCount().toString();
							verticalList[4] = rPRateList.get(i).getStaffingFourthMonthCount() == null ? "      ": rPRateList.get(i).getStaffingFourthMonthCount().toString();
							verticalList[5] = rPRateList.get(i).getStaffingFifthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingFifthMonthCount().toString();
							verticalList[6] = rPRateList.get(i).getStaffingSixthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingSixthMonthCount().toString();
							verticalList[7] = rPRateList.get(i).getStaffingSeventhMonthCount() == null ? "      ": rPRateList.get(i).getStaffingSeventhMonthCount().toString();
							verticalList[8] = rPRateList.get(i).getStaffingEighthMonthCount() == null ? "      "  : rPRateList.get(i).getStaffingEighthMonthCount().toString();
							verticalList[9] = rPRateList.get(i).getStaffingNinthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingNinthMonthCount().toString();
							verticalList[10] = rPRateList.get(i).getStaffingTenthMonthCount() == null ? "      ": rPRateList.get(i).getStaffingTenthMonthCount().toString();
							verticalList[11] = rPRateList.get(i).getStaffingEleventhMonthCount() == null ? "      ": rPRateList.get(i).getStaffingEleventhMonthCount().toString();
							verticalList[12] = rPRateList.get(i).getStaffingTwelthMonthCount() == null ? "      "	: rPRateList.get(i).getStaffingTwelthMonthCount().toString();
							verticalList[13] = rPRateList.get(i).getYearlyTotal() == null ? "0"	: rPRateList.get(i).getYearlyTotal().toString();
							rowVal++;

							Row verticalRow = sheet.createRow(rowVal);
							verticalRow.setHeightInPoints(16);
							Cell verticalCell;
							for (int k = 0; k < verticalList.length; k++) {
								if (k == 0) {
									verticalCell = verticalRow.createCell(k);
									verticalList[k] = verticalList[k].replaceAll("_", " "); 
									verticalCell.setCellValue(verticalList[k]);
									verticalCell.setCellStyle(styles.get("DataRowColumnLeftAlign"));
								} else {
									if (verticalList[k] != "      ") {
										verticalCell = verticalRow.createCell(k);
										AppLoger.APPLOGGER.info(verticalList[k]);
										Float value = new Float(verticalList[k]);
										verticalCell.setCellType(Cell.CELL_TYPE_NUMERIC);
										verticalCell.setCellValue(Math.round(value));
										verticalCell.setCellStyle(styles.get("ColumnRightAlignNum"));
									} else {
										verticalCell = verticalRow.createCell(k);
										AppLoger.APPLOGGER.info(verticalList[k]);
										verticalCell.setCellValue(verticalList[k]);
										verticalCell.setCellStyle(styles.get("ColumnRightAlignNum"));

									}
								}
							}
						}
					}else if(rPRateList.get(i).getTotalIndicator().equals(2)){

						if(rPRateList.get(i).getMonthYearHeader().equals(headerData)){
						verticalList[0] = rPRateList.get(i).getCostType();
						verticalList[1] = rPRateList.get(i).getStaffingFirstMonthCount() == null ? "      " :  rPRateList.get(i).getStaffingFirstMonthCount().toString();
						verticalList[2] = rPRateList.get(i).getStaffingSecondMonthCount() == null ? "      "  : rPRateList.get(i).getStaffingSecondMonthCount().toString();
						verticalList[3] = rPRateList.get(i).getStaffingThirdMonthCount() == null ? "      " : rPRateList.get(i).getStaffingThirdMonthCount().toString();
						verticalList[4] = rPRateList.get(i).getStaffingFourthMonthCount() == null ? "      ": rPRateList.get(i).getStaffingFourthMonthCount().toString();
						verticalList[5] = rPRateList.get(i).getStaffingFifthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingFifthMonthCount().toString();
						verticalList[6] = rPRateList.get(i).getStaffingSixthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingSixthMonthCount().toString();
						verticalList[7] = rPRateList.get(i).getStaffingSeventhMonthCount() == null ? "      ": rPRateList.get(i).getStaffingSeventhMonthCount().toString();
						verticalList[8] = rPRateList.get(i).getStaffingEighthMonthCount() == null ? "      "  : rPRateList.get(i).getStaffingEighthMonthCount().toString();
						verticalList[9] = rPRateList.get(i).getStaffingNinthMonthCount() == null ? "      " : rPRateList.get(i).getStaffingNinthMonthCount().toString();
						verticalList[10] = rPRateList.get(i).getStaffingTenthMonthCount() == null ? "      ": rPRateList.get(i).getStaffingTenthMonthCount().toString();
						verticalList[11] = rPRateList.get(i).getStaffingEleventhMonthCount() == null ? "      ": rPRateList.get(i).getStaffingEleventhMonthCount().toString();
						verticalList[12] = rPRateList.get(i).getStaffingTwelthMonthCount() == null ? "      "	: rPRateList.get(i).getStaffingTwelthMonthCount().toString();
						verticalList[13] = rPRateList.get(i).getYearlyTotal() == null ? "0"	: rPRateList.get(i).getYearlyTotal().toString();
						rowVal++;

						Row verticalRow = sheet.createRow(rowVal);
						verticalRow.setHeightInPoints(16);
						Cell verticalCell;
						for (int k = 0; k < verticalList.length; k++) {
							if (k == 0) {
								verticalCell = verticalRow.createCell(k);
								verticalCell.setCellValue(verticalList[k]);
								verticalCell.setCellStyle(styles.get("DataRowColumnLeftAlignBold"));
							} else {
								if (verticalList[k] != "      ") {
									verticalCell = verticalRow.createCell(k);
									AppLoger.APPLOGGER.info(verticalList[k]);
									Float value = new Float(verticalList[k]);
									verticalCell.setCellType(Cell.CELL_TYPE_NUMERIC);
									verticalCell.setCellValue(Math.round(value));
									verticalCell.setCellStyle(styles.get("ColumnRightAlignNumBold"));
								} else {
									verticalCell = verticalRow.createCell(k);
									AppLoger.APPLOGGER.info(verticalList[k]);
									verticalCell.setCellValue(verticalList[k]);
									verticalCell.setCellStyle(styles.get("ColumnRightAlignNumBold"));

								}
							}
						}
					}
				
						
					}
					}

					for (int columnIndex = 0; columnIndex < 15; columnIndex++) {
						sheet.autoSizeColumn(columnIndex);
					}
				
			}
		wb.write(out);
		wb.close();
		return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static byte[] getPricingExcelReport(List<FpDeal> dealList, List<Currency> currencyList, List<DealPricingTemplate> pricingTemplateDetails) {
		AppLoger.APPLOGGER.info("Inside getPricingExcelReport function");
		AppLoger.APPLOGGER.info("List of Deal Details List....... "+ dealList);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map<String, CellStyle> styles = createStyles(wb);
		try {
			//create excel xls sheet
			Integer counter = 0;
			String strSheetName = "FP_Pricing_Template";
			Sheet sheet = wb.createSheet(strSheetName);
			Integer rowCount = 0;
			Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(20);
			Cell titleCell = titleRow.createCell(2);
			titleCell.setCellValue("FP Pricing Template");
			titleCell.setCellStyle(styles.get("Header"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$C$1:$E$1"));
			rowCount = 2;
			createPricingSheet(sheet,styles,wb,dealList,rowCount,currencyList,pricingTemplateDetails);
			wb.write(out);
			wb.close();
			return out.toByteArray();			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}


	private static void createPricingSheet(Sheet sheet, Map<String, CellStyle> styles, HSSFWorkbook wb, List<FpDeal> dealList, Integer rowCount, List<Currency> currencyList, List<DealPricingTemplate> pricingTemplateDetails) {
		
		
		Row dealPanel = sheet.createRow(rowCount);
		Cell cellRCIDTitle = dealPanel.createCell(0);
		cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellRCIDTitle.setCellValue("Customer Name");
		
		
		Cell cellCustomerName = dealPanel.createCell(1);
		cellCustomerName.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
		cellCustomerName.setCellValue(dealList.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName());
		
		
		Row crmDealIDrow = sheet.createRow(rowCount++);
		Cell cellCrMIDTitle = crmDealIDrow.createCell(0);
		cellCrMIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellCrMIDTitle.setCellValue("CRM Deal ID");
		
		Cell cellCRMId = crmDealIDrow.createCell(1);
		cellCRMId.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellCRMId.setCellValue(dealList.get(0).getCrmDealId());
		
		Row dealTypeRow = sheet.createRow(rowCount++);
		Cell cellDealTypeTitle = dealTypeRow.createCell(0);
		cellDealTypeTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellDealTypeTitle.setCellValue("Deal Type");
		
		Cell cellDealType = dealTypeRow.createCell(1);
		cellDealType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellDealType.setCellValue(getDealType(dealList.get(0).getIsNewDeal()));
		
		Row billingCurrencyRow = sheet.createRow(rowCount++);
		Cell cellBillingCurrencyTitle = billingCurrencyRow.createCell(0);
		cellBillingCurrencyTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellBillingCurrencyTitle.setCellValue("Billing Currency");
		
		Cell cellBillingCurrency = billingCurrencyRow.createCell(1);
		cellBillingCurrency.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellBillingCurrency.setCellValue(getCurrencyName(dealList.get(0).getCurrencyId(), currencyList));
		
		Row daysPercentRow = sheet.createRow(rowCount++);
		Cell cellDaysPerMonthTitle = daysPercentRow.createCell(0);
		cellDaysPerMonthTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellDaysPerMonthTitle.setCellValue("Days Per Month");
		
		Cell cellDaysPerMonth = daysPercentRow.createCell(1);
		cellDaysPerMonth.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellDaysPerMonth.setCellValue(dealList.get(0).getWorkingDays());
		
		Row daysHoursRow = sheet.createRow(rowCount++);
		Cell cellDaysOrHoursTitle = daysHoursRow.createCell(0);
		cellDaysOrHoursTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellDaysOrHoursTitle.setCellValue("Days/Hourly");
		
		Cell cellDaysOrHours = daysHoursRow.createCell(1);
		cellDaysOrHours.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellDaysOrHours.setCellValue("Hourly");
		
		
		Row onsiteHoursRow = sheet.createRow(rowCount++);
		Cell cellOnsiteHoursTitle = onsiteHoursRow.createCell(0);
		cellOnsiteHoursTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellOnsiteHoursTitle.setCellValue("Onsite Hours");
		
		Cell cellonsiteHours = onsiteHoursRow.createCell(1);
		cellonsiteHours.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellonsiteHours.setCellValue(dealList.get(0).getOnsiteHours());

		Row offShoreHoursRow = sheet.createRow(rowCount++);
		Cell cellOffshoreHoursTitle = offShoreHoursRow.createCell(0);
		cellOffshoreHoursTitle.setCellStyle(styles.get("RowRateCardHeader"));
		cellOffshoreHoursTitle.setCellValue("Offshore Hours");
		
		Cell cellOffshoreHours = offShoreHoursRow.createCell(1);
		cellOffshoreHours.setCellStyle(styles.get("DataRowColumnLeftAlign"));
		cellOffshoreHours.setCellValue(dealList.get(0).getOffShoreHours());
		
		rowCount = 11;
		Row headerRow = sheet.createRow(rowCount);
		headerRow.setHeightInPoints(16);

		createPriceHeaders(headerRow,sheet,styles,rowCount);
		
		rowCount = 13;
		
		createPricingDataRows(headerRow,sheet,styles,rowCount,pricingTemplateDetails);
		
		for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
		{
			sheet.autoSizeColumn(columnIndex);			     
		}
	}


	private static void createPricingDataRows(Row headerRow, Sheet sheet, Map<String, CellStyle> styles,
			Integer rowCount, List<DealPricingTemplate> pricingTemplateDetails) {
		final String[] verticalList = new String[24];
		for(DealPricingTemplate pricingData : pricingTemplateDetails){
			verticalList[0]=pricingData.getMasterRoleCode();
			verticalList[1]=pricingData.getSkillMix();
			verticalList[2]=pricingData.getBandGrade();
			verticalList[3]=pricingData.getCustomerRole();
			verticalList[4]=pricingData.getRcId().toString();
			verticalList[5]=pricingData.getTowerName();
			verticalList[6]=pricingData.getCurrentOnsiteBillRate().toString();
			verticalList[7]=pricingData.getCurrentOffshoreBillRate().toString();
			verticalList[8]=pricingData.getFteYearOneOnsiteManMonth().toString();
			verticalList[9]=pricingData.getFteYearOneOffshoreManMonth().toString();
			verticalList[10]=pricingData.getFteSecondYearOnsiteMonth().toString();
			verticalList[11]=pricingData.getFteSecondYearOffshoreMonth().toString();
			verticalList[12]=pricingData.getFteThirdYearOnsiteMonth().toString();
			verticalList[13]=pricingData.getFteYear3OffshoreMonth().toString();
			verticalList[14]=pricingData.getFteYear4OnsiteMonth().toString();
			verticalList[15]=pricingData.getFteYear4OffshoreMonth().toString();
			verticalList[16]=pricingData.getFteYear5OnsiteMonth().toString();
			verticalList[17]=pricingData.getFteYear5OffshoreMonth().toString();
			verticalList[18]=pricingData.getOnsiteTCVLocalC().toString();
			verticalList[19]=pricingData.getOffShoreTCVLocalC().toString();
			verticalList[20]=pricingData.getTcvLocalC().toString();
			verticalList[21]=pricingData.getOnsiteTcvBillingC().toString();
			verticalList[22]=pricingData.getOffshoreTCVBillingC().toString();
			verticalList[23]=pricingData.getTcvBillingC().toString();
			
			Row verticalRow = sheet.createRow(rowCount);
			verticalRow.setHeightInPoints(16);
			Cell verticalCell;
			for (int k = 0;k < verticalList.length; k++) 
			{
				verticalCell = verticalRow.createCell(k);
				verticalCell.setCellValue(verticalList[k]);
				verticalCell.setCellStyle(styles.get("DataRowColumnLeftAlign"));	
			}
			rowCount++;
		}
		
	}


	private static void createPriceHeaders(Row headerRow, Sheet sheet, Map<String, CellStyle> styles,
			Integer rowCount) {
		Cell cellcurrentBillingRate11 = headerRow.createCell(0);
		cellcurrentBillingRate11.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		
		Cell cellcurrentBillingRate12 = headerRow.createCell(1);
		cellcurrentBillingRate12.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		
		Cell cellcurrentBillingRate13 = headerRow.createCell(2);
		cellcurrentBillingRate13.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		
		Cell cellMonthyear14 = headerRow.createCell(3);
		cellMonthyear14.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		
		Cell cellUtiOn15 = headerRow.createCell(4);
		cellUtiOn15.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,5));
	
		Cell cellUtil5 = headerRow.createCell(5);
		cellUtil5.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil6 = headerRow.createCell(6);
		cellUtil6.setCellValue("Current Bill rate");
		cellUtil6.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,6,7));
		
		Cell cellUtil8 = headerRow.createCell(8);
		cellUtil8.setCellValue("FTE Manmonth Yr1");
		cellUtil8.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil9 = headerRow.createCell(9);
		cellUtil9.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,8,9));
		
		Cell cellUtil10 = headerRow.createCell(10);
		cellUtil10.setCellValue("FTE Manmonth Yr2");
		cellUtil10.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil11 = headerRow.createCell(11);
		cellUtil11.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,10,11));
		
		Cell cellUtil12 = headerRow.createCell(12);
		cellUtil12.setCellValue("FTE Manmonth Yr3");
		cellUtil12.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil13 = headerRow.createCell(13);
		cellUtil13.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,12,13));
		
		Cell cellUtil14 = headerRow.createCell(14);
		cellUtil14.setCellValue("FTE Manmonth Yr4");
		cellUtil14.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil15 = headerRow.createCell(15);
		cellUtil15.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,14,15));
		
		Cell cellUtil16 = headerRow.createCell(16);
		cellUtil16.setCellValue("FTE Manmonth Yr5");
		cellUtil16.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtil17 = headerRow.createCell(17);
		cellUtil17.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,16,17));
		
		Cell cellUtiOn18 = headerRow.createCell(18);
		cellUtiOn18.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn19 = headerRow.createCell(19);
		cellUtiOn19.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn20 = headerRow.createCell(20);
		cellUtiOn20.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn21 = headerRow.createCell(21);
		cellUtiOn21.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn22 = headerRow.createCell(22);
		cellUtiOn22.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		Cell cellUtiOn23 = headerRow.createCell(23);
		cellUtiOn23.setCellStyle(styles.get("rcHeaderOnsiteUtil"));
		sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,18,23));
		
		final String[] OnsiteHeaderList = {"Master Role Code", "Skill Mix - As per Staffing Tab", "Syntel Band/Grade - As per Staffing TAB","Customer Role","Rate card ID","Tower"
				,"Onsite","Offshore","Onsite","Offshore","Onsite","Offshore","Onsite","Offshore","Onsite","Offshore","Onsite","Offshore","Onsite TCV In local Currency","Offshore TCV In local currency","Total In local Currency","Onsite TCV In billing Currency","Offshore TCV In Billing currency","Tota In Billing Currency"};
		
		rowCount++;
		
		Row headerRow1 = sheet.createRow(rowCount);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < OnsiteHeaderList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(OnsiteHeaderList[i]);
				headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
		}
	}


	private static String getDealType(int isNewRenewalId) {
		if(isNewRenewalId == 0){
			 return "Renewal";	
			}else if(isNewRenewalId == 1) {
				return "New";
			} else {
				return "RFP/RFI";
			}
	}
	
	// please don't remove this funcation
	/*public static byte[] downloadWhatIfExcel(List<FPDealWhatIf> lstfpDealWhatIf,List<FPDealWhatIfEffort> lstfpDealWhatIfEffort, List<FPWhatIfContractTerms> lstfpDealContractTerms) {
		AppLoger.APPLOGGER.info("Inside the Excel Download function");
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		//create excel xls sheet
		Map<String, CellStyle> styles = createStyles(wb);
        Sheet sheet = wb.createSheet("Master Basic Allowance Details");
        sheet.setFitToPage(true);
        
        // create style for header cells
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(16);
		Cell titleCell = titleRow.createCell(2);
		titleCell.setCellValue("Master Basic Allowance Details");
		titleCell.setCellStyle(styles.get("Header"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
		
     // Table Header
		final String[] headerList = {"Particulars","Enter Revenue For Final Price","Threshold For BUH Approval","Threshold For CEO Approval","PM% as per Global Rate Card"};
		Row headerRow1 = sheet.createRow(5);
		headerRow1.setHeightInPoints(16);
		Cell headerCell1;
		for (int i = 0; i < headerList.length; i++) {
			headerCell1 = headerRow1.createCell(i);
			headerCell1.setCellValue(headerList[i]);
			headerCell1.setCellStyle(styles.get("Row3"));
		}
 
        int rowCount = 6;
        
        for(int i=0; i<lstfpDealWhatIf.size(); i++)
        {
        	
    	  Double userInputValue = lstfpDealWhatIf.get(i).getUserInputValue()	;
    	  Double buhThresholdValue = lstfpDealWhatIf.get(i).getBuhThresholdValue();
    	  Double ceoApprovalValue = lstfpDealWhatIf.get(i).getCeoApprovalValue();
    	  Double globalRateCardValue = Double.parseDouble(lstfpDealWhatIf.get(i).getGlobalRateCardValue());
    	  
    	  String[] getWhatIfDetails ={lstfpDealWhatIf.get(i).getDescription()};
    	  
    	  Double[] whatIfDetails =
				{
					//Utilization Onsite
					new BigDecimal(userInputValue).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(buhThresholdValue).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(ceoApprovalValue).setScale(2, RoundingMode.HALF_UP).doubleValue(),
					new BigDecimal(globalRateCardValue).setScale(2, RoundingMode.HALF_UP).doubleValue()
				};

    	  Row header = sheet.createRow(rowCount);
    	  createDataRowExcel(getWhatIfDetails,whatIfDetails,sheet,styles,header);
    	  rowCount++;
        }
        
	     for(int columnIndex = 0; columnIndex < 11; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);			     
	      }
		wb.write(out);
		wb.close();
		return out.toByteArray();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}*/
	
	@SuppressWarnings("deprecation")
	public static byte[] getFPdealCostInputExcelReport(List<DealIndirectCostInputs> rPRateList,int noOfYears,List<DealDetailsView> dealList) 
	{
		AppLoger.APPLOGGER.info("Inside the Excel Download function getDashboardExcelReport");
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Map<String, CellStyle> styles = createStyles(wb);
		List<String> temp = new ArrayList<String>();
		int flag=1;
		int count = 0;
		int size = 0;

		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader1() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader1());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader2() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader2());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader3() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader3());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader4() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader4());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader5() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader5());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader6() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader6());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader7() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader7());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader8() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader8());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader9() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader9());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader10() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader10());				
		}
		if(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader11() != null){
			temp.add(rPRateList.get(0).getStaffingDetailsHeader().getMonthYearHeader11());				
		}
		
		/*for (int i = 0; i < rPRateList.size(); i++) {
			temp.add(rPRateList.get(i).getMonthYearHeader());
		}*/

		List<String> uniqueMYHeader = new ArrayList<String>(new HashSet<String>(temp));
		String[] MYHeader = new String[temp.size()];
		temp.toArray(MYHeader);
		

		AppLoger.APPLOGGER.info(count);
		try {
		for (int l = 0; l < MYHeader.length; l++) {
			if(!(MYHeader[l].equals(""))) {
			String monthYearHeader[] = new String[2];
			String startMonth[] = new String[2];
			String endMonth[] = new String[2];
			Sheet sheet ;
			String headerData=MYHeader[l];
			if (!(MYHeader[l].equals("Summary"))) {
				monthYearHeader = MYHeader[l].split("/");
				startMonth = monthYearHeader[0].split("-");
				endMonth = monthYearHeader[1].split("-");
				 sheet = wb.createSheet(monthYearHeader[0] + " to " + monthYearHeader[1]);
				sheet.setFitToPage(true);
			}
			else{
				 sheet = wb.createSheet(MYHeader[l]);
				sheet.setFitToPage(true);				
			}
				// create style for header cells
				CellStyle style = wb.createCellStyle();
				Font font = wb.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
				font.setBold(true);
				font.setColor(HSSFColor.WHITE.index);
				style.setFont(font);
				int rowVal = 0;
				Row titleRow = sheet.createRow(rowVal);
				titleRow.setHeightInPoints(20);
				Cell titleCell = titleRow.createCell(0);
				titleCell.setCellValue("FP Deal Creation - Cost Inputs (Travel / Relocation)");
				titleCell.setCellStyle(styles.get("Header"));

				Cell titleCell4 = titleRow.createCell(1);
				titleCell4.setCellStyle(styles.get("Header"));
				Cell titleCell5 = titleRow.createCell(2);
				titleCell5.setCellStyle(styles.get("Header"));
				Cell titleCell6 = titleRow.createCell(3);
				titleCell6.setCellStyle(styles.get("Header"));
				Cell titleCell7 = titleRow.createCell(4);
				titleCell7.setCellStyle(styles.get("Header"));
				Cell titleCell8 = titleRow.createCell(5);
				titleCell8.setCellStyle(styles.get("Header"));
				Cell titleCell9 = titleRow.createCell(6);
				titleCell9.setCellStyle(styles.get("Header"));
				Cell titleCell10 = titleRow.createCell(7);
				titleCell10.setCellStyle(styles.get("Header"));
				Cell titleCell11 = titleRow.createCell(8);
				titleCell11.setCellStyle(styles.get("Header"));
				Cell titleCell12 = titleRow.createCell(9);
				titleCell12.setCellStyle(styles.get("Header"));
				Cell titleCell13 = titleRow.createCell(10);
				titleCell13.setCellStyle(styles.get("Header"));
				Cell titleCell14 = titleRow.createCell(11);
				titleCell14.setCellStyle(styles.get("Header"));
				Cell titleCell15 = titleRow.createCell(12);
				titleCell15.setCellStyle(styles.get("Header"));
				Cell titleCell16 = titleRow.createCell(13);
				titleCell16.setCellStyle(styles.get("Header"));
				sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));
				rowVal += 2;
				String monthsArray[]=new String[12];
				
				rowVal = costInputdealPanel(sheet,rowVal,dealList,styles);
				
				 int start = 0, end = 0;
				 
				if (!(MYHeader[l].equals("Summary"))) 
				{
					switch (startMonth[0]) {
					case "Jan":	start = 1; break;
					case "Feb":	start = 2; break;
					case "Mar": start = 3; break;
					case "Apr":	start = 4; break;
					case "May": start = 5; break;
					case "Jun":	start = 6; break;
					case "Jul":	start = 7; break;
					case "Aug": start = 8; break;
					case "Sep": start = 9; break;
					case "Oct":	start = 10;break;
					case "Nov":	start = 11;break;
					case "Dec": start = 12;break;
					}
					
					switch (endMonth[0]) {
					case "Jan":	end = 1; break;
					case "Feb":	end = 2; break;
					case "Mar":	end = 3; break;
					case "Apr":	end = 4; break;
					case "May": end = 5; break;
					case "Jun":	end = 6; break;
					case "Jul":	end = 7; break;
					case "Aug":	end = 8; break;
					case "Sep":	end = 9; break;
					case "Oct":	end = 10;break;
					case "Nov":	end = 11;break;
					case "Dec":	end = 12;break;
					}

					int j = 0;
					if(!(start>end)){
						for (int i = start; i <= end; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}
					}
					else {

						for (int i = start; i <= 12; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}

						for (int i = 0; i <= end; i++) {
							if (i == 1) {
								monthsArray[j] = "Jan";
								j++;
							}
							if (i == 2) {
								monthsArray[j] = "Feb";
								j++;
							}
							if (i == 3) {
								monthsArray[j] = "Mar";
								j++;
							}
							if (i == 4) {
								monthsArray[j] = "Apr";
								j++;
							}
							if (i == 5) {
								monthsArray[j] = "May";
								j++;
							}
							if (i == 6) {
								monthsArray[j] = "Jun";
								j++;
							}
							if (i == 7) {
								monthsArray[j] = "Jul";
								j++;
							}
							if (i == 8) {
								monthsArray[j] = "Aug";
								j++;
							}
							if (i == 9) {
								monthsArray[j] = "Sep";
								j++;
							}
							if (i == 10) {
								monthsArray[j] = "Oct";
								j++;
							}
							if (i == 11) {
								monthsArray[j] = "Nov";
								j++;
							}
							if (i == 12) {
								monthsArray[j] = "Dec";
								j++;
							}
						}
						
					
						
					}
					 
				}
				else
				{
					if(noOfYears!=0){
						
					}
					else{
							String endDate[]	= rPRateList.get(l).getEndDate().split("-");
							String startDate[]	= rPRateList.get(l).getStartDate().split("-");
							noOfYears= (Integer.parseInt(endDate[0])-Integer.parseInt(startDate[0]));
					}
					switch (noOfYears){
					case 1: 
						monthsArray[0]="YR-1";
						break;
					case 2:
							monthsArray[0]="yr-1";
							monthsArray[1]="yr-2";
							break;
					case 3:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						break;
					case 4:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						break;
					case 5:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						break;
					case 6:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						break;
					case 7:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						break;
					case 8:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						monthsArray[7]="yr-8";
						break;
					case 9:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						monthsArray[7]="yr-8";
						monthsArray[8]="yr-9";
						break;
					case 10:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						monthsArray[7]="yr-8";
						monthsArray[8]="yr-9";
						monthsArray[9]="yr-10";
						break;
					case 11:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						monthsArray[7]="yr-8";
						monthsArray[8]="yr-9";
						monthsArray[9]="yr-10";
						monthsArray[10]="yr-11";
						break;
					case 12:
						monthsArray[0]="yr-1";
						monthsArray[1]="yr-2";
						monthsArray[2]="yr-3";
						monthsArray[3]="yr-4";
						monthsArray[4]="yr-5";
						monthsArray[5]="yr-6";
						monthsArray[6]="yr-7";
						monthsArray[7]="yr-8";
						monthsArray[8]="yr-9";
						monthsArray[9]="yr-10";
						monthsArray[10]="yr-11";
						monthsArray[11]="yr-12";
						break;
					}
					
				}
					AppLoger.APPLOGGER.info(monthsArray);
					int headerSize = 0;
					final String[] headerList1 = new String[16];
					final String[] headerList2 = new String[16];
					headerList1[headerSize] = "Sr.No";
					headerSize++;
					headerList1[headerSize] = "Particulars";
					headerSize++;
					if(rPRateList.get(0).getIndirectCostType()==1 || rPRateList.get(0).getIndirectCostType()==2){
					headerList1[headerSize] = "Unit Of Measurement	";
					headerSize++;
					}
					else
					{
							headerList1[headerSize] = "Standard Rates";
							headerSize++;
					}
					
					for (int i = 0; i < monthsArray.length; i++) {
						headerList2[headerSize] = monthsArray[i];
						headerSize++;
					}
					headerList2[headerSize] = "Total";
					rowVal += 2;
					Row headerRow = sheet.createRow(rowVal);
					headerRow.setHeightInPoints(16);
					Cell headerCell;
					for (int i = 0; i < headerList1.length; i++) {
						headerCell = headerRow.createCell(i);
						headerCell.setCellValue(headerList1[i]);
						headerCell.setCellStyle(styles.get("RowRateCardHeader2"));
					}
					rowVal ++;
					Row headerRow2 = sheet.createRow(rowVal);
					headerRow2.setHeightInPoints(16);
					Cell headerCell2;
					for (int i = 0; i < headerList2.length; i++) {
						headerCell2 = headerRow2.createCell(i);
						headerCell2.setCellValue(headerList2[i]);
						headerCell2.setCellStyle(styles.get("rcHeader"));
					}
					final String[] verticalList = new String[16];
					int j=1;
					for (int i = 0; i < rPRateList.size(); i++) {
							if(rPRateList.get(i).getMonthYearHeader().equals(headerData)){
							verticalList[0] = ""+j;
							verticalList[1] = rPRateList.get(i).getIndirectCostDescription();	
						if(rPRateList.get(i).getIndirectCostDescription().equals("Laptops") || rPRateList.get(i).getIndirectCostDescription().equals("Data_Cards") 
								|| rPRateList.get(i).getIndirectCostDescription().equals("DataCard_Monthly_Recurring") || rPRateList.get(i).getIndirectCostDescription().equals("Mobile_Phones") 
								|| rPRateList.get(i).getIndirectCostDescription().equals("Mobile_Phones_Monthly_Recurring")){	
							verticalList[2] = rPRateList.get(i).getUnitCost() == null ? "      " : rPRateList.get(i).getUnitCost().toString() ; 
						}else{
							verticalList[2] = "      " ;
						}
							verticalList[3] = rPRateList.get(i).getStaffingFirstMonthCount() == null || monthsArray[0]==null ? "      " :  rPRateList.get(i).getStaffingFirstMonthCount().toString();
							verticalList[4] = rPRateList.get(i).getStaffingSecondMonthCount() == null || monthsArray[1]==null ? "      "  : rPRateList.get(i).getStaffingSecondMonthCount().toString();
							verticalList[5] = rPRateList.get(i).getStaffingThirdMonthCount() == null  || monthsArray[2]==null ? "      " : rPRateList.get(i).getStaffingThirdMonthCount().toString();
							verticalList[6] = rPRateList.get(i).getStaffingFourthMonthCount() == null || monthsArray[3]==null ? "      ": rPRateList.get(i).getStaffingFourthMonthCount().toString();
							verticalList[7] = rPRateList.get(i).getStaffingFifthMonthCount() == null || monthsArray[4]==null ? "      " : rPRateList.get(i).getStaffingFifthMonthCount().toString();
							verticalList[8] = rPRateList.get(i).getStaffingSixthMonthCount() == null || monthsArray[5]==null ? "      " : rPRateList.get(i).getStaffingSixthMonthCount().toString();
							verticalList[9] = rPRateList.get(i).getStaffingSeventhMonthCount() == null || monthsArray[6]==null ? "      ": rPRateList.get(i).getStaffingSeventhMonthCount().toString();
							verticalList[10] = rPRateList.get(i).getStaffingEighthMonthCount() == null || monthsArray[7]==null ? "      "  : rPRateList.get(i).getStaffingEighthMonthCount().toString();
							verticalList[11] = rPRateList.get(i).getStaffingNinthMonthCount() == null || monthsArray[8]==null ? "      " : rPRateList.get(i).getStaffingNinthMonthCount().toString();
							verticalList[12] = rPRateList.get(i).getStaffingTenthMonthCount() == null || monthsArray[9]==null ? "      ": rPRateList.get(i).getStaffingTenthMonthCount().toString();
							verticalList[13] = rPRateList.get(i).getStaffingEleventhMonthCount() == null || monthsArray[10]==null ? "      ": rPRateList.get(i).getStaffingEleventhMonthCount().toString();
							verticalList[14] = rPRateList.get(i).getStaffingTwelthMonthCount() == null || monthsArray[11]==null ? "      "	: rPRateList.get(i).getStaffingTwelthMonthCount().toString();
							verticalList[15] = rPRateList.get(i).getYearlyTotal() == null ? "0"	: rPRateList.get(i).getYearlyTotal().toString();
							rowVal++;

							Row verticalRow = sheet.createRow(rowVal);
							verticalRow.setHeightInPoints(16);
							Cell verticalCell;
							for (int k = 0; k < verticalList.length; k++) {
								if (k == 1 ||k == 2) {
									verticalCell = verticalRow.createCell(k);
									verticalCell.setCellValue(verticalList[k]);
									verticalCell.setCellStyle(styles.get("DataRowColumnLeftAlign"));
								} else {
									if (verticalList[k] != "      ") {
										verticalCell = verticalRow.createCell(k);
										AppLoger.APPLOGGER.info(verticalList[k]);
										Float value = new Float(verticalList[k]);
										verticalCell.setCellType(Cell.CELL_TYPE_NUMERIC);
										verticalCell.setCellValue(Math.round(value));
										verticalCell.setCellStyle(styles.get("ColumnRightAlignNum"));
									} else {
										verticalCell = verticalRow.createCell(k);
										AppLoger.APPLOGGER.info(verticalList[k]);
										verticalCell.setCellValue(verticalList[k]);
										verticalCell.setCellStyle(styles.get("ColumnRightAlignNum"));

									}
							}
						}
							j++;
							}
					}

					for (int columnIndex = 0; columnIndex < 15; columnIndex++) {
						sheet.autoSizeColumn(columnIndex);
					}
				
			}
		}
		wb.write(out);
		wb.close();
		return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	private static Integer costInputdealPanel(Sheet sheet, Integer rowCount, List<DealDetailsView> dealList,Map<String, CellStyle> styles) 
	{

		// Deal panel Start
			Row rcPanel1 = sheet.createRow(rowCount);
			Cell cellRCIDTitle = rcPanel1.createCell(0);
			cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCIDTitle.setCellValue("Deal Id");
			
			Cell cellRCID = rcPanel1.createCell(1);
			cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellRCID.setCellValue(dealList.get(0).getDealId());
			
			
			Cell cellRCNameTitle = rcPanel1.createCell(2);
			cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCNameTitle.setCellValue("Deal Version (ID)");
			
			Cell cellRCName = rcPanel1.createCell(3);
			cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCName.setCellValue(dealList.get(0).getDealVersion());
			
			Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
			cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCApprStatusTitle.setCellValue("Customer Name");
			
			Cell cellRCApprStatus = rcPanel1.createCell(5);
			cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCApprStatus.setCellValue(dealList.get(0).getCustomerName());
			
			rowCount++;
			
			//Row 2
			Row rcPanel2 = sheet.createRow(rowCount);
			Cell cellDealStatusTitle = rcPanel2.createCell(0);
			cellDealStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellDealStatusTitle.setCellValue("Deal Status");
			
			Cell cellDealStatus= rcPanel2.createCell(1);
			cellDealStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellDealStatus.setCellValue(dealList.get(0).getDealStatus());
			
			
			Cell cellProjectTitle = rcPanel2.createCell(2);
			cellProjectTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellProjectTitle.setCellValue("Project Type");
			
			if(dealList.get(0).getDealTypeId()==1){
				if(dealList.get(0).getFpType()==0){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue("Development -  Fixed Price");   			//project Type remoaining
				}
				else if(dealList.get(0).getFpType()==1){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue( "Maintenance -  Fixed Price");   			//project Type remoaining
				}
			}
			else if(dealList.get(0).getDealTypeId()==2){
				if(dealList.get(0).getFpType()==0){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue("Development -  T&M");   			//project Type remoaining
				}
				else if(dealList.get(0).getFpType()==1){
					Cell cellProjectType = rcPanel2.createCell(3);
					cellProjectType.setCellStyle(styles.get("DataRowColumnLeftAlign"));
					cellProjectType.setCellValue( "Maintenance -  T&M");   			//project Type remoaining
				}
			}
			
		    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
			cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellRCAppMonthsTitle.setCellValue("Deal Description");
			
			Cell cellRCAppMonths = rcPanel2.createCell(5);
			cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellRCAppMonths.setCellValue(dealList.get(0).getDealDescription());
			
			rowCount++;
			
			//Row 3
			Row rcPanel3 = sheet.createRow(rowCount);
			Cell cellStartDateTitle = rcPanel3.createCell(0);
			cellStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellStartDateTitle.setCellValue("Start Date (dd/mm/yyyy)");
			
			Cell cellStartDate = rcPanel3.createCell(1);
			cellStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			String strStartDate[] = dealList.get(0).getDealStartDate().split(" ");
			String startDate[] =  strStartDate[0].split("-");
			String strStartYr = startDate[0];
			String strStartMonth = startDate[1];
			String strStartDay = startDate[2];		
			cellStartDate.setCellValue(strStartDay+ "/"+strStartMonth+ "/"+strStartYr);
			
			Cell cellEndDateTitle = rcPanel3.createCell(2);
			cellEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellEndDateTitle.setCellValue("Start Date (dd/mm/yyyy)");
			
			Cell cellEndDate = rcPanel3.createCell(3);
			cellEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			String strEndDate[] = dealList.get(0).getDealEndDate().split(" ");
			String EndDate[] =  strEndDate[0].split("-");
			String strEndYr = EndDate[0];
			String strEndMonth = EndDate[1];
			String strEndDay = EndDate[2];		
			cellEndDate.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
			
		    Cell cellDiscTitle = rcPanel3.createCell(4);
		    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellDiscTitle.setCellValue("Percentage Close (%)");
			
			Cell cellDisc = rcPanel3.createCell(5);			
			cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
			cellDisc.setCellValue(dealList.get(0).getPercentageClose() + "%");
			
			rowCount++;
			
			//Row 4
			Row rcPanel4 = sheet.createRow(rowCount);
			Cell cellDealTCVTitle = rcPanel4.createCell(0);
			cellDealTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));		
			cellDealTCVTitle.setCellValue("Deal TCV");
			
			Cell cellOnUti = rcPanel4.createCell(1);
			cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellOnUti.setCellValue(dealList.get(0).getDealTCV());
			
			
			Cell cellOffUtiTitle = rcPanel4.createCell(2);
			cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
			cellOffUtiTitle.setCellValue("Currency");
			
			Cell cellOffUti = rcPanel4.createCell(3);
			cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellOffUti.setCellValue(dealList.get(0).getCurrency());
						
			
		    Cell cellPerTitle = rcPanel4.createCell(4);
		    cellPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
		    cellPerTitle.setCellValue("Penalty (%)");
			
			Cell cellPer = rcPanel4.createCell(5);
			cellPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
			cellPer.setCellValue(dealList.get(0).getPenaltyPercentage());
			
			//Deal panel end
	
	
		return rowCount;
		
	}
	
	
	public static byte[] downloadFPDealCreationStaffingExcel(List<FpDeal> parentHeadingDataList, int towerId,
			List<FPDealTower> parentNooftowerList, List<Country> countryList, List<CityFP> cityList,
			List<Currency> currencyList, List<StaffingDetails> staffingDataList,
			List<StaffingDetailsHeader> staffingDataHederList, List<MasterRole> bandDescList,
			List<Deal> parentHeadingData1List,List<Practice> pList,List<SubPractice> spList) {
			AppLoger.APPLOGGER.info("Inside downloadFPDealCreationRoleSelectionExcel function");
			AppLoger.APPLOGGER.info("parentHeadingDataList....... "+ parentHeadingDataList);
			AppLoger.APPLOGGER.info("List of parentHeadingData1List....... "+ parentHeadingDataList);
			
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Map<String, CellStyle> styles = createStyles(wb);
			Integer counter = 0;
			try {
				//create excel xls sheet
			for(int i=0;i<staffingDataHederList.size();i++)
			{
					
					String[] headerStfList =
							{	
								staffingDataHederList.get(i).getMonthYearHeader1(),
								staffingDataHederList.get(i).getMonthYearHeader2(),
								staffingDataHederList.get(i).getMonthYearHeader3(),
								staffingDataHederList.get(i).getMonthYearHeader4(),
								staffingDataHederList.get(i).getMonthYearHeader5(),
								staffingDataHederList.get(i).getMonthYearHeader6(),
								staffingDataHederList.get(i).getMonthYearHeader7(),
								staffingDataHederList.get(i).getMonthYearHeader8(),
								staffingDataHederList.get(i).getMonthYearHeader9(),
								staffingDataHederList.get(i).getMonthYearHeader10(),
								staffingDataHederList.get(i).getMonthYearHeader11(),
							};

				    List<String> list = new ArrayList<String>();

				    for(String s : headerStfList) {
				       if(s != null && s.length() > 0) {
				          list.add(s);
				       }
				    }

				    headerStfList = list.toArray(new String[list.size()]);
				for( int j = 0; j < headerStfList.length; j++)
				{
					counter++;
				
					String strSheetName = headerStfList[j];
					String strFPSheetName=strSheetName.replace("/", " to ");
					Sheet sheet = wb.createSheet(strFPSheetName);
					sheet.setFitToPage(true);
					createSheetFPS(sheet,wb,headerStfList[j],styles,parentHeadingDataList,counter,towerId,parentNooftowerList,countryList,cityList,currencyList,staffingDataList,staffingDataHederList,bandDescList,parentHeadingData1List,headerStfList.length,pList,spList);
				}
				
			}
				wb.write(out);
				wb.close();
				return out.toByteArray();				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return null;
			
		}


		
		private static void createSheetFPS(Sheet sheet, HSSFWorkbook wb, String headerYear,
				Map<String, CellStyle> styles, List<FpDeal> parentHeadingDataList,int dealAutoTowerId,
				Integer counter,List<FPDealTower> parentNooftowerList,List<Country> countryList,List<CityFP> cityList,List<Currency> currencyList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,List<MasterRole> bandDescList,
				List<Deal> parentHeadingData1List,int hdrlnt,List<Practice> pList,List<SubPractice> spList) 
		{
			 // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
			
			Integer rowCount = 0;
			rowCount = 1;
			String headerFP=""; 
			rowCount = createCustomerPanelFPS(sheet,styles,rowCount,counter,parentNooftowerList,parentHeadingDataList,dealAutoTowerId,countryList,cityList,staffingDataList,staffingDataHederList);
			//tow = createCustomerPanel1FP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealVersionId,dealAutoTowerId,tow);
			rowCount = rowCount+1;
			
			rowCount = createRCPanelFPS(sheet,styles,rowCount,counter,parentNooftowerList,parentHeadingDataList,dealAutoTowerId,currencyList,staffingDataList,staffingDataHederList,parentHeadingData1List);
			rowCount = rowCount+2;
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					headerFP = "Onsite - Local(Contractor)";
					rowCount = createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	
			
					rowCount =onsiteLocalContractor(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					break;
			  }
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					//headerFP = "Domestic (Local)";
					headerFP = "Local/Deputed (Landed) ";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

					rowCount =DomesticLocal(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotal(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			/*for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					headerFP = "Long Term H1";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =longTermH1(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotalLongterm(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}*/
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(0)){
					headerFP = "Short Term";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	

			
					rowCount =shortTerm(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotalShortTerm(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(2)){
					headerFP = "Total Onsite";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =TotalOnsite(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(3)){
					rowCount =subTotalGrandTotalOnsite(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					headerFP = "OffShore Contractor";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =offShoreContractor(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					break;
				}
			}

			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					headerFP = "Offshore";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	

			
					rowCount =offshore(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =OffShoreIncludingContractorTotal(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
			        break;
				}
			}
			
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(5)){
					headerFP = "Total Staffing";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =TotalStaffing(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(6)){
					rowCount =TotalStaffingT(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			
			/*rowCount =OnsiteYear(sheet,styles,rowCount,counter,parentNooftowerList,parentHeadingDataList,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
			rowCount=rowCount+2;
					*/
			for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
			{
				sheet.autoSizeColumn(columnIndex);			     
			}
			
		}

		private static Integer OnsiteYear(Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				List<FPDealTower> parentNooftowerList, List<FpDeal> parentHeadingDataList, int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(6)){
					String[] verticalList =
					{	
							"Onsite(%)/Year",
							"",
							"",
							"",
							"",
							"",
							"",
							staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer TotalStaffingT(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(6)){
					String[] verticalList =
					{	
							"Total Staffing",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
								staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer TotalStaffing(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(5)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;	
		}

		private static Integer OffShoreIncludingContractorTotal(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles,
				Integer rowCount, Integer counter,
				 int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(1)){
					String[] verticalList =
					{	
							"5. OffShore(Including Contractor) TOTAL",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
								staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer offshore(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					String[] verticalList =
					{	
							        staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer offShoreContractor(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer subTotalGrandTotalOnsite(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(3)){
					String[] verticalList =
					{	
							"4. SUB TOTAL(Grand Total Onsite)",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
								staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer TotalOnsite(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(2)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer subTotalShortTerm(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(1)){
					String[] verticalList =
					{	
							"3 .SUB TOTAL(short term)",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
								staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer shortTerm(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(0)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer subTotalLongterm(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(1)){
					String[] verticalList =
					{	
							"2. SUB TOTAL(Long term)",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer longTermH1(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer subTotal(List<Practice> pList,List<SubPractice> spList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(1)){
					String[] verticalList =
					{	
							"1. SUB TOTAL",
							"",
							"",
							"",
							"",
							"",
							"",
							"",
								staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFPTotal(verticalList,sheet,styles,header,rowCount);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer DomesticLocal(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount, Integer counter,
				 int dealAutoTowerId,
				List<StaffingDetails> staffingDataList, List<StaffingDetailsHeader> staffingDataHederList,
				String headerYear) {
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					String[] verticalList =
					{	
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
									staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
									staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
									staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
									staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
									staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
									staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
									staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
									staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
									staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
									staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
									staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
									staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
									staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
									staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
					};
					Row header = sheet.createRow(rowCount);
					createHeaderDataRowFP(verticalList,sheet,styles,header);
					rowCount++;
				}
			}
			return rowCount;
		}


		private static Integer onsiteLocalContractor(List<Practice> pList,List<SubPractice> spList,List<MasterRole> bandDescList,Sheet sheet, Map<String, CellStyle> styles, Integer rowCount,
				Integer counter,
				int dealAutoTowerId, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList,String headerYear) {
			
			DecimalFormat format = new DecimalFormat("0.#");
			for(int i=0;i<staffingDataList.size();i++)
			{
				
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					String[] verticalList =
						{	
							staffingDataList.get(i).getMasterRoleId()==null ? "" : getRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
							staffingDataList.get(i).getMasterRoleId()==null ? "" :getPractice(getPracticeId(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),pList),
							staffingDataList.get(i).getMasterRoleId()==null ? "" :getSubPractice(getSubPracticeId(staffingDataList.get(i).getMasterRoleId(),bandDescList),spList),
							staffingDataList.get(i).getMasterRoleId()==null ? "" :getSyntelRoleDescWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
							staffingDataList.get(i).getCustomerRole()==null ? "" : staffingDataList.get(i).getCustomerRole(),
							staffingDataList.get(i).getMasterRoleId()==null ? "" : getBandWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
							staffingDataList.get(i).getMasterRoleId()==null ? "" : getGCMCodeWithMasterId(staffingDataList.get(i).getMasterRoleId(),bandDescList),
							staffingDataList.get(i).getStfaffingBilling_Rate()==null ? "" : Double.toString((staffingDataList.get(i).getStfaffingBilling_Rate())),		
							staffingDataList.get(i).getStaffingFirstMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFirstMonthCount())),
							staffingDataList.get(i).getStaffingSecondMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSecondMonthCount())),
							staffingDataList.get(i).getStaffingThirdMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingThirdMonthCount())),
							staffingDataList.get(i).getStaffingFourthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFourthMonthCount())),
							staffingDataList.get(i).getStaffingFifthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingFifthMonthCount())),
							staffingDataList.get(i).getStaffingSixthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSixthMonthCount())),
							staffingDataList.get(i).getStaffingSeventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingSeventhMonthCount())),
							staffingDataList.get(i).getStaffingEighthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEighthMonthCount())),
							staffingDataList.get(i).getStaffingNinthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingNinthMonthCount())),
							staffingDataList.get(i).getStaffingTenthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTenthMonthCount())),
							staffingDataList.get(i).getStaffingEleventhMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingEleventhMonthCount())),
							staffingDataList.get(i).getStaffingTwelthMonthCount()==null ? "" : Double.toString((staffingDataList.get(i).getStaffingTwelthMonthCount())),
							staffingDataList.get(i).getYearlyTotal()==null ? "" : Double.toString(staffingDataList.get(i).getYearlyTotal()),
						};
						Row header = sheet.createRow(rowCount);
						createHeaderDataRowFP(verticalList,sheet,styles,header);
						rowCount++;
					
				}
			}
			return rowCount;
		}
		
		private static String getGCMCodeWithMasterId(Integer masterRoleId, List<MasterRole> bandDescList) {
			String gcmCode = null;
			for(MasterRole StaffingBandData : bandDescList) {
			   if(masterRoleId.equals(StaffingBandData.getMasterRoleId())){

				   if(StaffingBandData.getStrGcmCODE()!=null)
				   {
				   gcmCode = StaffingBandData.getStrGcmCODE().toString();
				   }
				   else{
					   gcmCode = "";
				   }
			   }
				   
			  }
			if(gcmCode.equals(null))
			{
				return ""; 
			}  
			else
			{
				return gcmCode; 
			}
		}
				
		private static String getBandWithMasterId(Integer masterRoleId, List<MasterRole> bandDescList) {
			String band = "";
			for(MasterRole StaffingBandData : bandDescList) {
			   if(masterRoleId.equals(StaffingBandData.getMasterRoleId())){
				   band = StaffingBandData.getBandGrade();
				   if(band==null)
					   return "";
				   else
				   return band;
			   }
			}
			return band;
		}



		private static String getRoleDescWithMasterId(Integer masterRoleId, List<MasterRole> bandDescList) 
		{
			String roleDesc = "";
				for(MasterRole StaffingBandData : bandDescList) 
				{				
					if(masterRoleId.equals(StaffingBandData.getMasterRoleId())){
						roleDesc = StaffingBandData.getMasterRoleName();
						if(roleDesc==null)
							return "";
						else
							return roleDesc;
					}
				}
			return roleDesc;
		}
		
		


		private static String getSyntelRoleDescWithMasterId(Integer masterRoleId, List<MasterRole> bandDescList) {
			String syntelRole ="";
			for(MasterRole StaffingBandData : bandDescList) {
				if(masterRoleId.equals(StaffingBandData.getMasterRoleId())){
					syntelRole = StaffingBandData.getSyntelRoleDescription();
					if(syntelRole==null)
						   return "";
					   else
					   return syntelRole;
			   }
			}
			return syntelRole;
		}

		private static Integer getSubPracticeId(Integer masterRoleId, List<MasterRole> bandDescList) {
			Integer syntelRole=0;
			for(MasterRole StaffingBandData : bandDescList) {
				if(masterRoleId.equals(StaffingBandData.getMasterRoleId())){
					syntelRole = StaffingBandData.getSubPracticeId();
					   return syntelRole;
			   }
			}
			return syntelRole;
		}

		
		

		private static String getPractice(Integer practiceId, List<Practice> pList) {
			String syntelRole = "";
			for(Practice StaffingBandData : pList) {
				if(practiceId.equals(StaffingBandData.getPracticeId())){
					syntelRole = StaffingBandData.getPracticeName();
					if(syntelRole==null)
						   return "";
					   else
					   return syntelRole;
			   }
			}
			return syntelRole;
		}


		private static Integer getPracticeId(Integer subPracticeId, List<SubPractice> spList) {
			Integer syntelRole=0;
			for(SubPractice StaffingBandData : spList) {
				if(subPracticeId.equals(StaffingBandData.getSubpracticeId())){
					syntelRole = StaffingBandData.getPracticeId();
					return syntelRole;
			   }
			}
			return syntelRole;
		}


		private static String getSubPractice(Integer subPracticeId, List<SubPractice> spList) {
			String syntelRole = "";
			for(SubPractice StaffingBandData : spList) {
				if(subPracticeId.equals(StaffingBandData.getSubpracticeId())){
					syntelRole = StaffingBandData.getSubpracticeName();
					if(syntelRole==null)
						   return "";
					   else
					   return syntelRole;
			   }
			}
			return syntelRole;
		}

		
		private static Integer createCustomerPanelFPS(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList, int dealAutoTowerId,List<Country> countryList,List<CityFP> cityList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList) 
		{ 
			// Customer panel Start
		
			Row rcCustNamePanel = sheet.createRow(rowCount);
			Cell cellCustName = rcCustNamePanel.createCell(0);
			cellCustName.setCellStyle(styles.get("RowRateCardHeader2"));
			cellCustName.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getCustomer().getCustomerName());
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
			
			Cell cellCustName1 = rcCustNamePanel.createCell(1);
			cellCustName1.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName2 = rcCustNamePanel.createCell(2);
			cellCustName2.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName3 = rcCustNamePanel.createCell(3);
			cellCustName3.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName4 = rcCustNamePanel.createCell(4);
			cellCustName4.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName5 = rcCustNamePanel.createCell(5);
			cellCustName5.setCellStyle(styles.get("RowRateCardHeader2"));
			rowCount=rowCount+2;
			
			//Row 2
			Row rcRow2 = sheet.createRow(rowCount);
			Cell cellYR = rcRow2.createCell(0);
			cellYR.setCellStyle(styles.get("RowRateCardHeader"));		
			cellYR.setCellValue("Tower");
			//sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
		    int towerId=((staffingDataHederList.get(0).getDealTowerId())-1);
			Cell cellYr1 = rcRow2.createCell(1);
			cellYr1.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellYr1.setCellValue(parentNooftowerList.get(towerId).getTowerName());
			Cell cellYr2 = rcRow2.createCell(2);
			cellYr2.setCellStyle(styles.get("RowRateCardHeader"));
			cellYr2.setCellValue("Country");
			Cell cellYr3 = rcRow2.createCell(3);
			cellYr3.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellYr3.setCellValue(getCountryNameWithId(parentNooftowerList.get(towerId).getCountryId(), countryList));
			Cell cellYr4 = rcRow2.createCell(4);
			cellYr4.setCellStyle(styles.get("RowRateCardHeader"));
			cellYr4.setCellValue("City");
			Cell cellYr5 = rcRow2.createCell(5);
			cellYr5.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellYr5.setCellValue(getCityNameWithId(parentNooftowerList.get(towerId).getCityId(),cityList)+" ("+(parentNooftowerList.get(towerId).getCityDisc())+")");
			return rowCount;
		}

	private static String getCityNameWithIdS(Integer cityId, List<CityFP> cityList) {
			String citiName = null;
			for(CityFP cityData : cityList) {
				if(cityId.equals(cityData.getCityId())){
					citiName = cityData.getCityName();
				}
			}
			return citiName;
		}


		

		private static String getCountryNameWithIdS(Integer countryId, List<Country> countryList) {
			String countryName = null;
			for(Country countryData : countryList) {
				if(countryId.equals(countryData.getCountryId())){
					countryName = countryData.getCountryName();
				}
			}
			return countryName;
		}

		private static Integer createRCPanelFPS(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount,Integer counter,List<FPDealTower> parentNooftowerList,List<FpDeal> parentHeadingDataList,int dealAutoTowerId,List<Currency> currencyList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,List<Deal> parentHeadingData1List) 
		{
			// RC panel Start
				Row rcPanel1 = sheet.createRow(rowCount);
				Cell cellRCIDTitle = rcPanel1.createCell(0);
				cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCIDTitle.setCellValue("Deal Id");
				
				Cell cellRCID = rcPanel1.createCell(1);
				cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellRCID.setCellValue(parentHeadingDataList.get(0). getCrmDealId());
				
				
				Cell cellRCNameTitle = rcPanel1.createCell(2);
				cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCNameTitle.setCellValue("Deal Version (ID)");
				
				Cell cellRCName = rcPanel1.createCell(3);
				cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCName.setCellValue(parentHeadingDataList.get(0).getDealVersion()+"("+parentHeadingDataList.get(0).getRpDealVersionId()+")");
				
				Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
				cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCApprStatusTitle.setCellValue("Customer Name");
				
				Cell cellRCApprStatus = rcPanel1.createCell(5);
				cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCApprStatus.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getCustomer().getCustomerName());
				
				rowCount++;
				
				//Row 2
				Row rcPanel2 = sheet.createRow(rowCount);
				Cell cellRCStartDateTitle = rcPanel2.createCell(0);
				cellRCStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellRCStartDateTitle.setCellValue("Deal Status");
				
				Cell cellRCStartDate = rcPanel2.createCell(1);
				cellRCStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				if(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStatusId() == 0){
				cellRCStartDate.setCellValue("Open");
					}else if(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStatusId() == 1){
					cellRCStartDate.setCellValue("Won");
				}else{
					cellRCStartDate.setCellValue("Lost");
				}
				
				
				Cell cellRCEndDateTitle = rcPanel2.createCell(2);
				cellRCEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCEndDateTitle.setCellValue("Project Type");
				
				Cell cellRCEndDate = rcPanel2.createCell(3);
				cellRCEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				if(parentHeadingDataList.get(0).getFpProjectTypeId() == 1){
			    cellRCEndDate.setCellValue("Development - Fixed Price");
				}else if(parentHeadingDataList.get(0).getFpProjectTypeId() == 2){
					cellRCEndDate.setCellValue("Development - Manage Capacity");
				}else if(parentHeadingDataList.get(0).getFpProjectTypeId() == 3){
					cellRCEndDate.setCellValue("Maintenance - Fixed Price");
				}else{
					cellRCEndDate.setCellValue("Maintenance - Manage Capacity");
				}
				
			    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
				cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCAppMonthsTitle.setCellValue("Deal Description");
				
				Cell cellRCAppMonths = rcPanel2.createCell(5);
				cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCAppMonths.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealDescription());
				
				rowCount++;
				
				//Row 3
				Row rcPanel3 = sheet.createRow(rowCount);
				Cell cellCustTitle = rcPanel3.createCell(0);
				cellCustTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellCustTitle.setCellValue("Start Date (dd/mm/yyyy)");
				
				Cell cellCust = rcPanel3.createCell(1);
				cellCust.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				String strDate[] =parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealStartDate().split(" ");	
				String Date[] =  strDate[0].split("-");
				String strYr = Date[0];
				String strMonth = Date[1];
				String strDay = Date[2];
				cellCust.setCellValue(strDay+ "/"+strMonth+ "/"+strYr);
				
				
				Cell cellTCVTitle = rcPanel3.createCell(2);
				cellTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellTCVTitle.setCellValue("Expected End Date (dd/mm/yyyy)");
				
				Cell cellTCV = rcPanel3.createCell(3);
				cellTCV.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				String strEndDate[] = parentHeadingDataList.get(0).getDealcrmstagesdata2().getDealEndDate2().split(" ");
				String EndDate[] =  strEndDate[0].split("-");
				String strEndYr = EndDate[0];
				String strEndMonth = EndDate[1];
				String strEndDay = EndDate[2];		
				cellTCV.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
				
				
			    Cell cellDiscTitle = rcPanel3.createCell(4);
			    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellDiscTitle.setCellValue("Percentage Close (%)");
				
				Cell cellDisc = rcPanel3.createCell(5);			
				cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
				cellDisc.setCellValue(parentHeadingDataList.get(0).getDealcrmstagesdata2().getPercentageClose());
				
				rowCount++;
				
				//Row 4
				Row rcPanel4 = sheet.createRow(rowCount);
				Cell cellOnUtiTitle = rcPanel4.createCell(0);
				cellOnUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellOnUtiTitle.setCellValue("Deal TCV");
				
				Cell cellOnUti = rcPanel4.createCell(1);
				cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellOnUti.setCellValue(parentHeadingData1List.get(0).getDealTcv());				
				
				Cell cellOffUtiTitle = rcPanel4.createCell(2);
				cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellOffUtiTitle.setCellValue("Currency");
				
				Cell cellOffUti = rcPanel4.createCell(3);
				cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellOffUti.setCellValue(getCurrencyName(parentHeadingDataList.get(0).getCurrencyId(),currencyList));
							
				
			    Cell cellGMPerTitle = rcPanel4.createCell(4);
			    cellGMPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellGMPerTitle.setCellValue("Penalty (%)");
				
				Cell cellGMPer = rcPanel4.createCell(5);
				cellGMPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellGMPer.setCellValue(parentHeadingDataList.get(0).getPenaltyPercentage());

				rowCount++;
				//RC panel end
			return rowCount;
		}

		private static String getCurrencyNameS(Integer currencyId, List<Currency> currencyList) {
			String currencyname= null;
			for(Currency currencyData : currencyList) {
				if(currencyId.equals(currencyData.getCurrencyId())){
					currencyname = currencyData.getCurrencyName();
				}
			}
			return currencyname;
		}
		
		private static Integer createHeadersFPS(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter, int dealAutoTowerId,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,String headerFP,String headerYear,int hdrlnt)
		{
			if(headerYear.equals("Summary")){
				Row headerRow = sheet.createRow(rowCount);
				headerRow.setHeightInPoints(16);
				Cell cellUtiOn0 = headerRow.createCell(0);
				cellUtiOn0.setCellValue(headerFP);
				cellUtiOn0.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			    sheet.setDisplayGridlines(false);
				Cell cellUtiOn1 = headerRow.createCell(1);
				cellUtiOn1.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn2 = headerRow.createCell(2);
				cellUtiOn2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn3 = headerRow.createCell(3);
				cellUtiOn3.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn4 = headerRow.createCell(4);
				cellUtiOn4.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn5 = headerRow.createCell(5);
				cellUtiOn5.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn6 = headerRow.createCell(6);
				cellUtiOn6.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
				Cell cellUtiOn7 = headerRow.createCell(7);
				cellUtiOn7.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			    sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,7));
			    rowCount++;
			    
			    final String[] headerList = {"Role Description", "Practice","Sub-Practice","Syntel Role","Customer Role", "Syntel Band/Grade ","GCM Level","Billing Rate","YR-1","YR-2","YR-3","YR-4","YR-5","YR-6","YR-7","YR-8","YR-9","YR-10","YR-11","YR-12","Total"};
			  /* String headerList[]= new String[16];
				headerList[0]="Role Description";
				headerList[1]="Customer Role";
				headerList[2]="Band Grade";
				headerList[15]="Total";
				for (int n = 0; n < hdrlnt-1; n++) {
					switch (n+3) { 
						case 3 : headerList[n+3]="YR"+(n+1); break;
		                case 4 : headerList[n+3]="YR"+(n+1); break;
		                case 5 : headerList[n+3]="YR"+(n+1); break;
		                case 6 : headerList[n+3]="YR"+(n+1); break;
		                case 7 : headerList[n+3]="YR"+(n+1); break;
		                case 8 : headerList[n+3]="YR"+(n+1); break;
		                case 9 : headerList[n+3]="YR"+(n+1); break;
		                case 10 : headerList[n+3]="YR"+(n+1); break; 
		                case 11 : headerList[n+3]="YR"+(n+1); break;
		                case 12 : headerList[n+3]="YR"+(n+1); break;             
		                case 13 : headerList[n+3]="YR"+(n+1); break;
		                case 14 : headerList[n+3]="YR"+(n+1); break;
		            }
		        } 
				for(int j = 0; j < headerList.length; j++) {
				     if(headerList[j] == null) 
				    	 headerList[j]="";
				}*/
				Row headerRow1 = sheet.createRow(rowCount);
				headerRow1.setHeightInPoints(16);
				Cell headerCell1;
				for (int i = 0; i < headerList.length; i++) 
				{
					headerCell1 = headerRow1.createCell(i);
					headerCell1.setCellValue(headerList[i]);
				
						headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
				}
				rowCount++;
			}else{
			Row headerRow = sheet.createRow(rowCount);
			headerRow.setHeightInPoints(16);
			Cell cellUtiOn0 = headerRow.createCell(0);
			cellUtiOn0.setCellValue(headerFP);
			cellUtiOn0.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
		    sheet.setDisplayGridlines(false);
			Cell cellUtiOn1 = headerRow.createCell(1);
			cellUtiOn1.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			Cell cellUtiOn2 = headerRow.createCell(2);
			cellUtiOn2.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			Cell cellUtiOn3 = headerRow.createCell(3);
			cellUtiOn3.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			Cell cellUtiOn4 = headerRow.createCell(4);
			cellUtiOn4.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			Cell cellUtiOn5 = headerRow.createCell(5);
			cellUtiOn5.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
			Cell cellUtiOn6 = headerRow.createCell(6);
			cellUtiOn6.setCellStyle(styles.get("rcHeaderOnsiteUtil12"));
		    sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,7));
		    rowCount++;
			
			
        	final String[] headerList = {"Role Description", "Practice","Sub-Practice","Syntel Role", "Customer Role", "Syntel Band/Grade","GCM Level","Billing Rate",staffingDataHederList.get(0).getStaffingFirstMonth(),staffingDataHederList.get(0).getStaffingSecondMonth(),staffingDataHederList.get(0).getStaffingThirdMonth(),staffingDataHederList.get(0).getStaffingFourthMonth(),staffingDataHederList.get(0).getStaffingFifthMonth(),staffingDataHederList.get(0).getStaffingSixthMonth(),staffingDataHederList.get(0).getStaffingSeventhMonth(),staffingDataHederList.get(0).getStaffingEighthMonth(),staffingDataHederList.get(0).getStaffingNinthMonth(),staffingDataHederList.get(0).getStaffingTenthMonth(),staffingDataHederList.get(0).getStaffingEleventhMonth(),staffingDataHederList.get(0).getStaffingTwelthMonth(),"Total"};
				Row headerRow1 = sheet.createRow(rowCount);
				headerRow1.setHeightInPoints(16);
				Cell headerCell1;
				for (int i = 0; i < headerList.length; i++) 
				{
					headerCell1 = headerRow1.createCell(i);
					headerCell1.setCellValue(headerList[i]);
				
						headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
				}
				rowCount++;
			}	
				return rowCount;
		}

		public static byte[] downloadTMDealCreationStaffingExcel(int towerId, List<Country> countryList,
				List<CityFP> cityList, List<Currency> currencyList, List<StaffingDetails> staffingDataList,
				List<StaffingDetailsHeader> staffingDataHederList, List<MasterRole> bandDescList,
				List<DealDetailsView> headingDataList,int rpDealVersionId,List<Practice> pList,List<SubPractice> spList) {
			AppLoger.APPLOGGER.info("Inside downloadFPDealCreationRoleSelectionExcel function");
			AppLoger.APPLOGGER.info("parentHeadingDataList....... "+ headingDataList);
			AppLoger.APPLOGGER.info("List of parentHeadingData1List....... "+ towerId);
			
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Map<String, CellStyle> styles = createStyles(wb);
			Integer counter = 0;
			try {
				//create excel xls sheet
			for(int i=0;i<staffingDataHederList.size();i++)
			{
					
					String[] headerStfList =
							{	
								staffingDataHederList.get(i).getMonthYearHeader1(),
								staffingDataHederList.get(i).getMonthYearHeader2(),
								staffingDataHederList.get(i).getMonthYearHeader3(),
								staffingDataHederList.get(i).getMonthYearHeader4(),
								staffingDataHederList.get(i).getMonthYearHeader5(),
								staffingDataHederList.get(i).getMonthYearHeader6(),
								staffingDataHederList.get(i).getMonthYearHeader7(),
								staffingDataHederList.get(i).getMonthYearHeader8(),
								staffingDataHederList.get(i).getMonthYearHeader9(),
								staffingDataHederList.get(i).getMonthYearHeader10(),
								staffingDataHederList.get(i).getMonthYearHeader11(),
							};

				    List<String> list = new ArrayList<String>();

				    for(String s : headerStfList) {
				       if(s != null && s.length() > 0) {
				    	   if(s.equals("Summary"))
				    	   {
				    		   list.add(s);
				    		   break;
				    	   }else
				          list.add(s);
				       }
				    }

				    headerStfList = list.toArray(new String[list.size()]);
				for( int j = 0; j < headerStfList.length; j++)
				{
					counter++;
				
					String strSheetName = headerStfList[j];
					String strFPSheetName=strSheetName.replace("/", " to ");
					Sheet sheet = wb.createSheet(strFPSheetName);
					sheet.setFitToPage(true);
					createSheetTMS(sheet,wb,headerStfList[j],styles,counter,towerId,countryList,cityList,currencyList,staffingDataList,staffingDataHederList,bandDescList,headerStfList.length,headingDataList,rpDealVersionId,pList,spList);
				}
				
			}
				wb.write(out);
				wb.close();
				return out.toByteArray();				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return null;
		}

		
		private static void createSheetTMS(Sheet sheet, HSSFWorkbook wb, String headerYear,
				Map<String, CellStyle> styles,Integer counter,int dealAutoTowerId,
				List<Country> countryList,List<CityFP> cityList,List<Currency> currencyList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,List<MasterRole> bandDescList,int hdrlnt,List<DealDetailsView> headingDataList,int rpDealVersionId,List<Practice> pList,List<SubPractice> spList) 
		{
			 // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
			
			Integer rowCount = 0;
			rowCount = 1;
			String headerFP=""; 
			rowCount = createCustomerPanelTMS(sheet,styles,rowCount,counter,dealAutoTowerId,countryList,cityList,staffingDataList,staffingDataHederList,headingDataList);
			//tow = createCustomerPanel1FP(sheet,styles,rowCount,counter,xODataList,parentNooftowerList,parentHeadingDataList,parentHeadingData1List,dealVersionId,dealAutoTowerId,tow);
			rowCount = rowCount+1;
			
			rowCount = createRCPanelTMS(sheet,styles,rowCount,counter,dealAutoTowerId,currencyList,staffingDataList,staffingDataHederList,headingDataList,rpDealVersionId);
			rowCount = rowCount+2;
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					headerFP = "Onsite - Local(Contractor)";
					rowCount = createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	
			
					rowCount =onsiteLocalContractor(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					break;
			  }
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					//headerFP = "Domestic (Local)";
					headerFP = "Local/Deputed (Landed)"; 
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

					rowCount =DomesticLocal(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) &&staffingDataList.get(i).getVisaId().equals(1) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotal(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			/*for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					headerFP = "Long Term H1";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =longTermH1(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(2) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotalLongterm(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}*/
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(0)){
					headerFP = "Short Term";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	

			
					rowCount =shortTerm(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(3) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =subTotalShortTerm(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(2)){
					headerFP = "Total Onsite";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =TotalOnsite(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(3)){
					rowCount =subTotalGrandTotalOnsite(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(1)){
					headerFP = "OffShore Contractor";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =offShoreContractor(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					break;
				}
			}

			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(0) && staffingDataList.get(i).getIsContractor().equals(0)){
					headerFP = "Offshore";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);	

			
					rowCount =offshore(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getVisaId().equals(4) && staffingDataList.get(i).getSumTotalType().equals(1)){
					rowCount =OffShoreIncludingContractorTotal(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
			        break;
				}
			}
			
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(5)){
					headerFP = "Total Staffing";
					rowCount =createHeadersFPS(sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerFP,headerYear,hdrlnt);

			
					rowCount =TotalStaffing(pList,spList,bandDescList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			for(int i=0;i<staffingDataList.size();i++)
			{
				if(staffingDataList.get(i).getMonthYearHeader().equals(headerYear) && staffingDataList.get(i).getSumTotalType().equals(6)){
					rowCount =TotalStaffingT(pList,spList,sheet,styles,rowCount,counter,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
					rowCount=rowCount+1;
					break;
				}
			}
			
			
			/*rowCount =OnsiteYear(sheet,styles,rowCount,counter,parentNooftowerList,parentHeadingDataList,dealAutoTowerId,staffingDataList,staffingDataHederList,headerYear);
			rowCount=rowCount+2;
					*/
			for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
			{
				sheet.autoSizeColumn(columnIndex);			     
			}
			
		}

		
		private static Integer createCustomerPanelTMS(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount, Integer counter, int dealAutoTowerId,List<Country> countryList,List<CityFP> cityList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,List<DealDetailsView> headingDataList) 
		{ 
			// Customer panel Start
		
			Row rcCustNamePanel = sheet.createRow(rowCount);
			Cell cellCustName = rcCustNamePanel.createCell(0);
			cellCustName.setCellStyle(styles.get("RowRateCardHeader2"));
			cellCustName.setCellValue(headingDataList.get(0).getCustomerName());
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
			
			Cell cellCustName1 = rcCustNamePanel.createCell(1);
			cellCustName1.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName2 = rcCustNamePanel.createCell(2);
			cellCustName2.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName3 = rcCustNamePanel.createCell(3);
			cellCustName3.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName4 = rcCustNamePanel.createCell(4);
			cellCustName4.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName5 = rcCustNamePanel.createCell(5);
			cellCustName5.setCellStyle(styles.get("RowRateCardHeader2"));
			rowCount=rowCount+2;
			
			//Row 2
			Row rcRow2 = sheet.createRow(rowCount);
			Cell cellYR = rcRow2.createCell(0);
			cellYR.setCellStyle(styles.get("RowRateCardHeader"));		
			cellYR.setCellValue("Country");
			//sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
			Cell cellYr1 = rcRow2.createCell(1);
			cellYr1.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellYr1.setCellValue(getCountryNameWithId(staffingDataList.get(0).getCountryId(), countryList));
			Cell cellYr2 = rcRow2.createCell(2);
			cellYr2.setCellStyle(styles.get("RowRateCardHeader"));
			cellYr2.setCellValue("City");
			Cell cellYr3 = rcRow2.createCell(3);
			cellYr3.setCellStyle(styles.get("DataRowColumnLeftAlign"));
			cellYr3.setCellValue(getCityNameWithId(staffingDataList.get(0).getCityId(),cityList));
			return rowCount;
		}
		
		private static Integer createRCPanelTMS(Sheet sheet, Map<String, CellStyle> styles,Integer rowCount,Integer counter,int dealAutoTowerId,List<Currency> currencyList,List<StaffingDetails> staffingDataList,List<StaffingDetailsHeader> staffingDataHederList,List<DealDetailsView> headingDataList,int rpDealVersionId) 
		{
			// RC panel Start
				Row rcPanel1 = sheet.createRow(rowCount);
				Cell cellRCIDTitle = rcPanel1.createCell(0);
				cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCIDTitle.setCellValue("Deal Id");
				
				Cell cellRCID = rcPanel1.createCell(1);
				cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellRCID.setCellValue(headingDataList.get(0).getDealId());
				
				
				Cell cellRCNameTitle = rcPanel1.createCell(2);
				cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCNameTitle.setCellValue("Deal Version (ID)");
				
				Cell cellRCName = rcPanel1.createCell(3);
				cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCName.setCellValue(headingDataList.get(0).getDealVersion()+"("+rpDealVersionId+")");
				
				Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
				cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCApprStatusTitle.setCellValue("Customer Name");
				
				Cell cellRCApprStatus = rcPanel1.createCell(5);
				cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCApprStatus.setCellValue(headingDataList.get(0).getCustomerName());
				
				rowCount++;
				
				//Row 2
				Row rcPanel2 = sheet.createRow(rowCount);
				Cell cellRCStartDateTitle = rcPanel2.createCell(0);
				cellRCStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellRCStartDateTitle.setCellValue("Deal Status");
				
				Cell cellRCStartDate = rcPanel2.createCell(1);
				cellRCStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCStartDate.setCellValue(headingDataList.get(0).getDealStatus());
				
				
				Cell cellRCEndDateTitle = rcPanel2.createCell(2);
				cellRCEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCEndDateTitle.setCellValue("Project Type");
				
				Cell cellRCEndDate = rcPanel2.createCell(3);
				cellRCEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				if(headingDataList.get(0).getFpType() != 1)
					cellRCEndDate.setCellValue("Development");
				else
					cellRCEndDate.setCellValue("Maintenance");	

				
			    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
				cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCAppMonthsTitle.setCellValue("Deal Description");
				
				Cell cellRCAppMonths = rcPanel2.createCell(5);
				cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCAppMonths.setCellValue(headingDataList.get(0).getDealDescription());
				
				rowCount++;
				
				//Row 3
				Row rcPanel3 = sheet.createRow(rowCount);
				Cell cellCustTitle = rcPanel3.createCell(0);
				cellCustTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellCustTitle.setCellValue("Start Date (dd/mm/yyyy)");
				
				Cell cellCust = rcPanel3.createCell(1);
				cellCust.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				String strDate[] =headingDataList.get(0).getDealStartDate().split(" ");	
				String Date[] =  strDate[0].split("-");
				String strYr = Date[0];
				String strMonth = Date[1];
				String strDay = Date[2];
				cellCust.setCellValue(strDay+ "/"+strMonth+ "/"+strYr);
				
				
				Cell cellTCVTitle = rcPanel3.createCell(2);
				cellTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellTCVTitle.setCellValue("Expected End Date (dd/mm/yyyy)");
				
				Cell cellTCV = rcPanel3.createCell(3);
				cellTCV.setCellStyle(styles.get("DataRowColumnLeftAlign"));	
				String strEndDate[] = headingDataList.get(0).getDealEndDate().split(" ");
				String EndDate[] =  strEndDate[0].split("-");
				String strEndYr = EndDate[0];
				String strEndMonth = EndDate[1];
				String strEndDay = EndDate[2];		
				cellTCV.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
				
				
			    Cell cellDiscTitle = rcPanel3.createCell(4);
			    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellDiscTitle.setCellValue("Percentage Close (%)");
				
				Cell cellDisc = rcPanel3.createCell(5);			
				cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
				cellDisc.setCellValue(headingDataList.get(0).getPercentageClose());
				
				rowCount++;
				
				//Row 4
				Row rcPanel4 = sheet.createRow(rowCount);
				Cell cellOnUtiTitle = rcPanel4.createCell(0);
				cellOnUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellOnUtiTitle.setCellValue("Deal TCV");
				
				Cell cellOnUti = rcPanel4.createCell(1);
				cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellOnUti.setCellValue(headingDataList.get(0).getDealTCV());				
				
				Cell cellOffUtiTitle = rcPanel4.createCell(2);
				cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellOffUtiTitle.setCellValue("Currency");
				
				Cell cellOffUti = rcPanel4.createCell(3);
				cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellOffUti.setCellValue(headingDataList.get(0).getCurrency());
							
				
			    Cell cellGMPerTitle = rcPanel4.createCell(4);
			    cellGMPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellGMPerTitle.setCellValue("Penalty (%)");
				
				Cell cellGMPer = rcPanel4.createCell(5);
				cellGMPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellGMPer.setCellValue(headingDataList.get(0).getPenaltyPercentage());

				rowCount++;
				//RC panel end
			return rowCount;
		}


		public static byte[] getRolesExcelDetails(List<Deal> dealData, int towerId, int autoDealTowerId, List<String> lstDetails, List<StaffingDetails> lstStaffing) 
		{
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
			//create excel xls sheet
			Map<String, CellStyle> styles = createStyles(wb);
	        Sheet sheet = wb.createSheet("Staffing-steady state");
	        sheet.setFitToPage(true);
	        sheet.setDisplayGridlines(false);
	        
	        int totalColIndex =0;
	        
	        int rowCount = 0;
	        Row HeadA = sheet.createRow(0);
	        Cell HeadACell = HeadA.createCell(0);
	        HeadACell.setCellValue("HeadA");
	        HeadACell.setCellStyle(styles.get("ColumnAlignLeft"));
	        
	        Cell cellDetails = HeadA.createCell(2);
	        cellDetails.setCellValue("Tower Name: "+ lstDetails.get(0) + ", Version Name: "+ lstDetails.get(1) + ", Tower Country: "+lstDetails.get(2) + 
	        		", Tower City: "+lstDetails.get(3) + ", Deal Currency: "+lstDetails.get(4));
	        cellDetails.setCellStyle(styles.get("ColumnAlignLeft"));
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$C$1:$S$1"));
	        
	        rowCount++;
	        Row SubHeadA = sheet.createRow(rowCount);
	        Cell SubHeadACell = SubHeadA.createCell(0);
	        SubHeadACell.setCellValue("SubHeadA");
	        SubHeadACell.setCellStyle(styles.get("ColumnAlignLeft"));
	        
	        rowCount++;
	        Row row2 = sheet.createRow(rowCount);
	        Cell row3Cell = row2.createCell(1);
	        row3Cell.setCellValue("Visa ID");
	        row3Cell.setCellStyle(styles.get("ColumnAlignLeft"));
	        
	        Cell DomesticCell = row2.createCell(2);
	        DomesticCell.setCellValue("Domestic");
	        DomesticCell.setCellStyle(styles.get("ColumnAlignLeftLeftTopBorder"));
	        
	        Cell CLCell = row2.createCell(3);
	        CLCell.setCellValue("Client Roles");
	        CLCell.setCellStyle(styles.get("ColumnAlignLeftLeftTopBorder"));
	        
	        rowCount++;
	        Row row3 = sheet.createRow(rowCount);
	        Cell DepCell = row3.createCell(2);
	        DepCell.setCellValue("Deputed");
	        DepCell.setCellStyle(styles.get("ColumnAlignLeftLeftMedTopHairBorder"));
	        
	        Cell DepEmptyCell = row3.createCell(3);
	        DepEmptyCell.setCellValue("");
	        DepEmptyCell.setCellStyle(styles.get("ColumnAlignLeftLeftMedTopHairBorder"));
	        
	        sheet.setColumnHidden(0, true);
	        sheet.setColumnHidden(1, true);
	        
	        Date dtDealStDate = getDate(dealData.get(0).getProjectStartDate());
			Date dtDealEndDate = getDate(dealData.get(0).getProjectEndDate());
			
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(dtDealStDate);
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(dtDealEndDate);
						
			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int intMonthDiff = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
			if(diffYear == 0)
				intMonthDiff++;
			else 
				intMonthDiff = intMonthDiff + diffYear;

			int intCellNumber = 5;			
			setMonthHeder(startCalendar,row3, intCellNumber,intMonthDiff,styles,sheet);
			
			Cell DepTotalEmptyCell = row3.createCell(5+intMonthDiff+1);
			DepTotalEmptyCell.setCellValue("");
			DepTotalEmptyCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightMedrBorder"));
			rowCount++;
			
			Row row4 = sheet.createRow(rowCount);
	        Cell OShoreCell = row4.createCell(2);
	        OShoreCell.setCellValue("Offshore");
	        OShoreCell.setCellStyle(styles.get("ColumnAlignLeftLeftMedTopHairBorder"));
	        
	        Cell OffEmptyCell = row4.createCell(3);
	        OffEmptyCell.setCellValue("");
	        OffEmptyCell.setCellStyle(styles.get("ColumnAlignLeftLeftMedTopHairBorder"));
	        
	        totalColIndex = setYearHeder(startCalendar,row4, intCellNumber,intMonthDiff,styles);
	        
	        rowCount++;
	        
	        // To set Header role border
	        for(int i=5; i<=totalColIndex;i++)
	        {
	        	Cell Total = row2.createCell(i);	        	
	        	Total.setCellStyle(styles.get("TopMedRightHairBorder"));
	        	if(i==totalColIndex)
	        		Total.setCellStyle(styles.get("TopMedRightMedBorder"));
	        }
	      
	        // Roles to be inserted	        
	        Boolean isContractor = false;
	        
	        for(StaffingDetails item : lstStaffing)
	        {
	    		if(item.getIsContractor() == 1)
	    		{
	    			isContractor = true;
	    		}
	    	}
	        
	        StringBuilder strBuilder = new StringBuilder();
	        String[] arrOfStr = null;
	        
	        int contractorRowIndex = 0; 
	        // Add Contractor role 
	        if(isContractor)
	        {
	        	rowCount = fillContractorRoles(lstStaffing,styles,totalColIndex,rowCount,sheet,intMonthDiff);
		        contractorRowIndex = rowCount;
	        }
	        
	        // Domestic (Local) OR Onsite - Work Permit  ==> Visa ID 1
	        AppLoger.APPLOGGER.info("Domestic (Local) OR Onsite - Work Permit  ==> Visa ID 1 Starts");
	        Row VisaIdOne = sheet.createRow(rowCount);
	        Cell VisaIdOneCell = VisaIdOne.createCell(2);
	        VisaIdOneCell.setCellValue("Domestic (Local)");
	        if(isContractor)
	        {
	        	VisaIdOneCell.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        	Cell CellCL = VisaIdOne.createCell(3);
	        	CellCL.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        	Cell CellBG = VisaIdOne.createCell(4);
	        	CellBG.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        	Cell CelltotalColIndex = VisaIdOne.createCell(totalColIndex);
	        	CelltotalColIndex.setCellStyle(styles.get("ColumnAlignRightRightBorder"));
	        }	        
	        else
	        {
	        	VisaIdOneCell.setCellStyle(styles.get("ColumnAlignLeftTopMedLeftMedFontBoldBorder"));
	        	setSectionHeaders(VisaIdOne,totalColIndex,styles);
	        }
	        
	        rowCount++;
	        contractorRowIndex = rowCount +1;
	        List<Object> list= null;
	        list = fillRoles(lstStaffing,rowCount, styles, 1,sheet,totalColIndex, intMonthDiff,startCalendar);
	        rowCount = Integer.valueOf(list.get(0).toString());
	        List<Object> lstColList = list.stream().skip(1).collect(Collectors.toList());
	        
	        
	        //5
	        Cell VerTotal = null;
	        VerTotal = setCellHorTotal(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex);
	        AppLoger.APPLOGGER.info("Domestic (Local) OR Onsite - Work Permit  ==> Visa ID 1 Ends");

	        rowCount++;

	        // Long Term H1 OR Onsite - Work Permit ==> Visa ID 2
	        AppLoger.APPLOGGER.info("Long Term H1 OR Onsite - Work Permit ==> Visa ID 2 Starts");
	        Row VisaIdTwo = sheet.createRow(rowCount);
	        Cell VisaIdTwoCell = VisaIdTwo.createCell(2);
	        VisaIdTwoCell.setCellValue("Long Term H1");
	        VisaIdTwoCell.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        setSectionHeadersOthers(VisaIdTwo,totalColIndex,styles);
	        
	        rowCount++;
	        int longTermH1RoleStarted = rowCount+1; // AS Formula places at the column name and Row numberwhich starts from 1 and not 0
	        list= null;
	        list = fillRoles(lstStaffing,rowCount, styles, 2,sheet,totalColIndex,intMonthDiff,startCalendar);
	        rowCount = Integer.valueOf(list.get(0).toString());
	        lstColList = list.stream().skip(1).collect(Collectors.toList());
	        
	        // rowCount++;
	        Cell VerTotalVID2 = null;
	        VerTotalVID2 = setCellHorTotalOther(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex,longTermH1RoleStarted);
	        AppLoger.APPLOGGER.info("Long Term H1 OR Onsite - Work Permit ==> Visa ID 2 Ends");
	        rowCount++;

	        // Short Term OR Short term Visa <= 3 Month ==> Visa ID 3
	        AppLoger.APPLOGGER.info("Short Term OR Short term Visa <= 3 Month ==> Visa ID 3 Starts");
	        Row VisaIdThree = sheet.createRow(rowCount);
	        Cell VisaIdThreeCell = VisaIdThree.createCell(2);
	        VisaIdThreeCell.setCellValue("Short Term");
	        VisaIdThreeCell.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        setSectionHeadersOthers(VisaIdThree,totalColIndex,styles);
	        rowCount++;
	        
	        int shortTermRoleStarted = rowCount+1; // AS Formula places at the column name and Row numberwhich starts from 1 and not 0
	        list= null;
	        list = fillRoles(lstStaffing,rowCount, styles, 3,sheet,totalColIndex,intMonthDiff,startCalendar);
	        rowCount = Integer.valueOf(list.get(0).toString());
	        lstColList = list.stream().skip(1).collect(Collectors.toList());
	        
	        //rowCount++;
	        Cell VerTotalVID3 = null;
	        VerTotalVID3 = setCellHorTotalOther(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex,shortTermRoleStarted);
	        AppLoger.APPLOGGER.info("Short Term OR Short term Visa <= 3 Month ==> Visa ID 3 Ends");
	        rowCount++;
	       	 
	        // Total Onsite Start
	        AppLoger.APPLOGGER.info("Total Onsite Starts");
	        
	        Row rwTOnsite = sheet.createRow(rowCount);
	        Cell clTOnsite = rwTOnsite.createCell(2);
	        clTOnsite.setCellValue("Total Onsite");
	        clTOnsite.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        setSectionHeadersOthers(rwTOnsite,totalColIndex,styles);
	        
	        rowCount++;
	        int totalOnsiteRoleStarted = rowCount;
	        if(isContractor)
	        {	
	        	//totalOnsiteRoleStarted = fillTotalOnsiteContarctor(rowCount,styles,lstFpDealRoleExcel,sheet,intMonthDiff,lstColList,totalColIndex);
	        	int intCntrRowIndex = 7;
	        	for(StaffingDetails item : lstStaffing)
		        {
	        		if(item.getMasterRoleId() == 1)
		    		{
	        			Row contrNM = sheet.createRow(rowCount);
	        			Cell CntrNMCell = contrNM.createCell(2);
	        			CntrNMCell.setCellValue(item.getMasterRoles().getMasterRoleName());
	        			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrCLCell = contrNM.createCell(3);
	        			CntrCLCell.setCellValue(item.getCustomerRole().isEmpty()?"":item.getCustomerRole());
	        			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrBGCell = contrNM.createCell(4);
	        			CntrBGCell.setCellValue(item.getMasterRoles().getGcmCODE());
	        			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));	
	        			
	        			for(int i=5; i<=intMonthDiff+5;i++)
	        			{
	        				int intCount = 0;
	        				for(Object obj : lstColList)
	        				{
	        					if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
	        					{
	        						intCount++;
	        						Cell Total = contrNM.createCell(i);
	        						Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	        						break;
	        					}
	        				}
	        				if(intCount < 1)
	        				{
	        					Cell ctrtTotal = contrNM.createCell(i);
	        					ctrtTotal.setCellType(CellType.FORMULA);		        	
	        					AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i)+""+intCntrRowIndex);
	        					ctrtTotal.setCellFormula(""+CellReference.convertNumToColString(i)+""+intCntrRowIndex);	        	
	        					ctrtTotal.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	        				}
	        			}
	        			intCntrRowIndex++;
	        			
	        			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
	        			int colIndex = totalColIndex -1;
	        			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
	        			
	        			for (Object obj : lstColList) 
	        			{
	        				strFormula += "-("+obj+""+(rowCount+1)+")";
	        			}
	        			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
	        			CntrTotalCell.setCellFormula(strFormula);
	        			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));	
	        			rowCount++;
		    		}
		        }
		        /*totalOnsiteRoleStarted = rowCount;*/
	        }
	       	       
	        //totalOnsiteRoleStarted = rowCount;	        
	        //if(!isContractor)
	        	totalOnsiteRoleStarted++;
	        
	        rowCount = fillTotalOnsiteRoles(lstStaffing,rowCount, styles,sheet,totalColIndex, intMonthDiff,contractorRowIndex,longTermH1RoleStarted,shortTermRoleStarted,lstColList);
	        
	        //rowCount++;
	        Cell VerTotalOnsiteTotal =null;
	        VerTotalOnsiteTotal = setCellHorTotalOther(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex,totalOnsiteRoleStarted);
	        AppLoger.APPLOGGER.info("Total Onsite Ends");
	        rowCount++;
	        
	        // OffShore Contractor Visa id 4 
	        AppLoger.APPLOGGER.info("OffShore Contractor Visa id 4 Starts");
	        
	        Row rwOff = sheet.createRow(rowCount);
	        Cell clOff = rwOff.createCell(2);
	        clOff.setCellValue("Offshore");
	        clOff.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        setSectionHeadersOthers(rwOff,totalColIndex,styles);
	        
	        rowCount++;
	        int totalOffshorewRoleStarted = rowCount;        
	        if(isContractor)
	        {   
	        	//totalOffshorewRoleStarted = fillOffContractor(lstFpDealRoleExcel,rowCount,sheet,styles,intMonthDiff,totalColIndex);
	        	for(StaffingDetails item : lstStaffing)
		        {
	        		if(item.getMasterRoleId() == 1)
		    		{
	        			Row contrNM = sheet.createRow(rowCount);
	        			Cell CntrVSId = contrNM.createCell(1);
	        			CntrVSId.setCellValue("4");
	        			CntrVSId.setCellStyle(styles.get("ColumnAlignRight"));
	        			
	        			Cell CntrNMCell = contrNM.createCell(2);
	        			CntrNMCell.setCellValue(item.getMasterRoles().getMasterRoleName());
	        			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrCLCell = contrNM.createCell(3);
	        			CntrCLCell.setCellValue(item.getCustomerRole().isEmpty()?"":item.getCustomerRole());
	        			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrBGCell = contrNM.createCell(4);
	        			CntrBGCell.setCellValue(item.getMasterRoles().getGcmCODE());
	        			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));		 
	        			
	        			Cell cellFirst = contrNM.createCell(5);
	    				cellFirst.setCellValue(0.00);
	    				cellFirst.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	    				
	        			int intEmptyCell = 1;
	        			Boolean isMonthReset = false;
	        			strBuilder = new StringBuilder();
	        			for(int i=6; i<=intMonthDiff+5;i++)
	        			{
	        				
	        				if(intEmptyCell == 12)
	        				{
	        					intEmptyCell=-1;
	        					isMonthReset = true;
	        					strBuilder.append(CellReference.convertNumToColString(i)+",");
	        				}
	        				else
	        				{
	        					Cell Total = contrNM.createCell(i);
	        					Total.setCellType(CellType.FORMULA);		        	
	        					int rowTotal = Total.getRowIndex()+1;
	        					if(isMonthReset)
	        					{
	        						isMonthReset = false;
	        						AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i-2)+""+rowTotal);
	        						Total.setCellFormula(""+CellReference.convertNumToColString(i-2)+""+rowTotal);
	        					}
	        					else
	        					{
	        						AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i-1)+""+rowTotal);
	        						Total.setCellFormula(""+CellReference.convertNumToColString(i-1)+""+rowTotal);	        	
	        					}
	        					Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));		        		
	        				}
	        				intEmptyCell++;
	        			}
	        			if(strBuilder.length()>0)
	        				arrOfStr = strBuilder.toString().split(",");  
	        			
	        			
	        			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
	        			int colIndex = totalColIndex -1;
	        			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
	        			
	        			for (String a : arrOfStr) 
	        			{
	        				strFormula += "-("+a+""+(rowCount+1)+")";
	        			}
	        			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
	        			CntrTotalCell.setCellFormula(strFormula);		        
	        			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));	
	        			rowCount++;
		    		}
		        }
	        	//totalOffshorewRoleStarted = rowCount;
	        }
	        
	        //totalOffshorewRoleStarted = rowCount;	
	        //if(!isContractor)
	        	totalOffshorewRoleStarted++;
	        
	        int offshoreRoleStarted = rowCount+1; // AS Formula places at the column name and Row number which starts from 1 and not 0
	        
	        list= null;
	        list = fillRoles(lstStaffing,rowCount, styles, 4,sheet,totalColIndex,intMonthDiff,startCalendar);
	        rowCount = Integer.valueOf(list.get(0).toString());
	        lstColList = list.stream().skip(1).collect(Collectors.toList());
	        
	        //rowCount++;
	        Cell VerTotalVID4 =null;
	        VerTotalVID4 = setCellHorTotalOther(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex,totalOffshorewRoleStarted);
	        AppLoger.APPLOGGER.info("OffShore Visa id 4 Ends");
	        rowCount++;
	        
	        /**/
	        Cell DomesticFormulaCell = row2.createCell(4);
	        AppLoger.APPLOGGER.info("Domestic Formula Cell: " + "="+CellReference.convertNumToColString(totalColIndex)+""+(VerTotal.getRowIndex()+1));
	        DomesticFormulaCell.setCellFormula(""+CellReference.convertNumToColString(totalColIndex)+""+(VerTotal.getRowIndex()+1));
	        DomesticFormulaCell.setCellStyle(styles.get("ColumnAlignRightRightTopBorder2Decimal"));
	        
	        Cell DepCellFormulaCell = row3.createCell(4);
	        AppLoger.APPLOGGER.info("Deputed Formula Cell: " + "SUM("+CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID2.getRowIndex()+1)+","+ CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID3.getRowIndex()+1)+")");
	        DepCellFormulaCell.setCellFormula("SUM("+CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID2.getRowIndex()+1)+","+ CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID3.getRowIndex()+1)+")");
	        DepCellFormulaCell.setCellStyle(styles.get("ColumnAlignRightTopHairRightMedLeftMedBorder2Decimal"));
	        
	        Cell OShoreCLFormulaCell = row4.createCell(4);
	        AppLoger.APPLOGGER.info("Offshore Formula Cell: " + "="+CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID4.getRowIndex()+1));
	        OShoreCLFormulaCell.setCellFormula(""+CellReference.convertNumToColString(totalColIndex)+""+(VerTotalVID4.getRowIndex()+1));
	        OShoreCLFormulaCell.setCellStyle(styles.get("ColumnAlignRightTopHairRightMedLeftMedBorder2Decimal"));
	        
	        // Total Staffing
	        AppLoger.APPLOGGER.info("Total Staffing Starts");
	        
	        Row rwTotalStaffing = sheet.createRow(rowCount);
	        Cell clTotalStaffing = rwTotalStaffing.createCell(2);
	        clTotalStaffing.setCellValue("Total Staffing");
	        clTotalStaffing.setCellStyle(styles.get("ColumnAlignLeftBold_TopHairRightMedLeftMedBorder"));
	        setSectionHeadersOthers(rwTotalStaffing,totalColIndex,styles);
	        
	        rowCount++;
	        int totalStaffingRoleStarted = rowCount+1;
	        if(isContractor)
	        {	
	        	//totalStaffingRoleStarted =  fillTotalStaffingContractor(rowCount,lstFpDealRoleExcel,styles,intMonthDiff,totalOnsiteRoleStarted,totalOffshorewRoleStarted,totalColIndex,lstColList,sheet);
	        	int intRowIndex  = totalOffshorewRoleStarted;
	        	int intcntrTotalOnsite = totalOnsiteRoleStarted;
	        	for(StaffingDetails item : lstStaffing)
		        {
	        		if(item.getMasterRoleId() == 1)
		    		{
	        			Row contrNM = sheet.createRow(rowCount);
	        			Cell CntrNMCell = contrNM.createCell(2);
	        			CntrNMCell.setCellValue(item.getMasterRoles().getMasterRoleName());
	        			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrCLCell = contrNM.createCell(3);
	        			CntrCLCell.setCellValue(item.getCustomerRole().isEmpty()?"":item.getCustomerRole());
	        			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
	        			Cell CntrBGCell = contrNM.createCell(4);
	        			CntrBGCell.setCellValue(item.getMasterRoles().getGcmCODE());
	        			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));	
	        			
	        			for(int i=5; i<=intMonthDiff+5;i++)
	        			{
	        				int intCount = 0;
	        				for(Object obj : lstColList)
	        				{
	        					if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
	        					{
	        						intCount++;
	        						Cell Total = contrNM.createCell(i);
	        						Total.setCellStyle(styles.get("ColumnAlignRight"));
	        						break;
	        					}
	        				}
	        				if(intCount < 1)
	        				{
	        					Cell ctrtTotal = contrNM.createCell(i);
	        					ctrtTotal.setCellType(CellType.FORMULA);		        	
	        					AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(i)+""+intcntrTotalOnsite+","+""+CellReference.convertNumToColString(i)+"" +intRowIndex+")");
	        					ctrtTotal.setCellFormula("SUM("+CellReference.convertNumToColString(i)+""+intcntrTotalOnsite+","+""+CellReference.convertNumToColString(i)+"" +intRowIndex+")");	        	
	        					ctrtTotal.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	        				}
	        			}
	        			
	        			intRowIndex++;
	        			intcntrTotalOnsite++;
	        			
	        			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
	        			int colIndex = totalColIndex -1;
	        			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
	        			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
	        			for (Object obj : lstColList) 
	        			{
	        				strFormula += "-("+obj+""+(rowCount+1)+")";
	        			}
	        			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
	        			CntrTotalCell.setCellFormula(strFormula);
	        			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));	
	        			rowCount++;
		    		}
		        }
		        //totalStaffingRoleStarted = rowCount;
	        	totalOnsiteRoleStarted = intcntrTotalOnsite;
	        	totalOffshorewRoleStarted = intRowIndex;
	        }	
	        
	        /*if(isContractor)
	        {
	        	totalOnsiteRoleStarted++;
	        	totalOffshorewRoleStarted++;
	        }*/
	        
	        rowCount = fillTotalStaffing(lstStaffing,rowCount, styles,sheet,totalColIndex, intMonthDiff,totalOnsiteRoleStarted,totalOffshorewRoleStarted, lstColList);
	        
	        //rowCount++;
	        Cell VerTotalStaffingTotal =null;
	        VerTotalStaffingTotal = setCellHorTotalOther(rowCount,styles,intMonthDiff,lstColList,sheet,totalColIndex,totalStaffingRoleStarted);
	        AppLoger.APPLOGGER.info("Total Staffing Ends.");
	        rowCount++;
	        Row lastRow = sheet.createRow(rowCount);
	        
	        for(int j=2;j<= totalColIndex;j++)
	        {
	        	Cell Cell = lastRow.createCell(j);
	        	Cell.setCellStyle(styles.get("ColumnAlignRightTopMedBorder"));	        			
	        }	
	        
	        rowCount++;
	        rowCount++;
	        
	        int intcolNos = intMonthDiff+ 5;
			for(int columnIndex = 0; columnIndex <= intcolNos; columnIndex++) 
			{
			    if(columnIndex <5) 
			    	sheet.autoSizeColumn(columnIndex);
			    else
			    	sheet.setColumnWidth(columnIndex, 2048);
			}
	    	
			int intlastColNo = intcolNos + 1;
			sheet.setColumnWidth(intlastColNo, 2560);
			
			wb.write(out);
			wb.close();
			return out.toByteArray();		
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			AppLoger.APPLOGGER.info("Error Occured in getRolesExcelDetails :" + e.getMessage());
		}
		return null;
	}

	private static int fillTotalStaffingContractor(int rowCount, List<FpDealRoleExcel> lstFpDealRoleExcel,
				Map<String, CellStyle> styles, int intMonthDiff, int totalOnsiteRoleStarted,
				int totalOffshorewRoleStarted, int totalColIndex, List<Object> lstColList, Sheet sheet) {
		for(FpDealRoleExcel item : lstFpDealRoleExcel)
        {
    		if(item.getMasterRoleId() == 1)
    		{
    			Row contrNM = sheet.createRow(rowCount);
    			Cell CntrNMCell = contrNM.createCell(2);
    			CntrNMCell.setCellValue(item.getMasterRole().getMasterRoleName());
    			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrCLCell = contrNM.createCell(3);
    			CntrCLCell.setCellValue(item.getClientRole().isEmpty()?"":item.getClientRole());
    			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrBGCell = contrNM.createCell(4);
    			CntrBGCell.setCellValue(item.getMasterRole().getBandGrade());
    			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));	
    			
    			for(int i=5; i<=intMonthDiff+5;i++)
    			{
    				int intCount = 0;
    				for(Object obj : lstColList)
    				{
    					if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
    					{
    						intCount++;
    						Cell Total = contrNM.createCell(i);
    						Total.setCellStyle(styles.get("ColumnAlignRight"));
    						break;
    					}
    				}
    				if(intCount < 1)
    				{
    					Cell ctrtTotal = contrNM.createCell(i);
    					ctrtTotal.setCellType(CellType.FORMULA);		        	
    					AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(i)+""+totalOnsiteRoleStarted+","+""+CellReference.convertNumToColString(i)+"" +totalOffshorewRoleStarted+")");
    					ctrtTotal.setCellFormula("SUM("+CellReference.convertNumToColString(i)+""+totalOnsiteRoleStarted+","+""+CellReference.convertNumToColString(i)+"" +totalOffshorewRoleStarted+")");	        	
    					ctrtTotal.setCellStyle(styles.get("ColumnAlignRight"));
    				}
    			}
    			
    			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
    			int colIndex = totalColIndex -1;
    			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
    			for (Object obj : lstColList) 
    			{
    				strFormula += "-("+obj+""+(rowCount+1)+")";
    			}
    			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
    			CntrTotalCell.setCellFormula(strFormula);
    			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder"));	
    			rowCount++;
    		}
        }
		return rowCount;
	}


	private static int fillOffContractor(List<FpDealRoleExcel> lstFpDealRoleExcel, int rowCount, Sheet sheet,
				Map<String, CellStyle> styles, int intMonthDiff, int totalColIndex) 
	{
		for(FpDealRoleExcel item : lstFpDealRoleExcel)
        {
    		if(item.getMasterRoleId() == 1)
    		{
    			Row contrNM = sheet.createRow(rowCount);
    			Cell CntrVSId = contrNM.createCell(1);
    			CntrVSId.setCellValue("4");
    			CntrVSId.setCellStyle(styles.get("ColumnAlignRight"));
    			
    			Cell CntrNMCell = contrNM.createCell(2);
    			CntrNMCell.setCellValue(item.getMasterRole().getMasterRoleName());
    			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrCLCell = contrNM.createCell(3);
    			CntrCLCell.setCellValue(item.getClientRole().isEmpty()?"":item.getClientRole());
    			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrBGCell = contrNM.createCell(4);
    			CntrBGCell.setCellValue(item.getMasterRole().getBandGrade());
    			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));		 
    			
    			int intEmptyCell = 1;
    			Boolean isMonthReset = false;
    			StringBuilder strBuilder = new StringBuilder();
    			String[] arrOfStr = null;  
    			for(int i=6; i<=intMonthDiff+5;i++)
    			{
    				
    				if(intEmptyCell == 12)
    				{
    					intEmptyCell=-1;
    					isMonthReset = true;
    					strBuilder.append(CellReference.convertNumToColString(i)+",");
    				}
    				else
    				{
    					Cell Total = contrNM.createCell(i);
    					Total.setCellType(CellType.FORMULA);		        	
    					int rowTotal = Total.getRowIndex()+1;
    					if(isMonthReset)
    					{
    						isMonthReset = false;
    						AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i-2)+""+rowTotal);
    						Total.setCellFormula(""+CellReference.convertNumToColString(i-2)+""+rowTotal);
    					}
    					else
    					{
    						AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i-1)+""+rowTotal);
    						Total.setCellFormula(""+CellReference.convertNumToColString(i-1)+""+rowTotal);	        	
    					}
    					Total.setCellStyle(styles.get("ColumnAlignRight"));		        		
    				}
    				intEmptyCell++;
    			}
    			if(strBuilder.length()>0)
    				arrOfStr = strBuilder.toString().split(",");  
    			
    			
    			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
    			int colIndex = totalColIndex -1;
    			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
    			
    			for (String a : arrOfStr) 
    			{
    				strFormula += "-("+a+""+(rowCount+1)+")";
    			}
    			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
    			CntrTotalCell.setCellFormula(strFormula);		        
    			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder"));	
    			rowCount++;
    		}
        }
		return rowCount;
	}


	private static int fillTotalOnsiteContarctor(int rowCount, Map<String, CellStyle> styles, List<FpDealRoleExcel> lstFpDealRoleExcel, Sheet sheet,
				int intMonthDiff, List<Object> lstColList, int totalColIndex) {
			// TODO Auto-generated method stub
		for(FpDealRoleExcel item : lstFpDealRoleExcel)
        {
    		if(item.getMasterRoleId() == 1)
    		{
    			Row contrNM = sheet.createRow(rowCount);
    			Cell CntrNMCell = contrNM.createCell(2);
    			CntrNMCell.setCellValue(item.getMasterRole().getMasterRoleName());
    			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrCLCell = contrNM.createCell(3);
    			CntrCLCell.setCellValue(item.getClientRole().isEmpty()?"":item.getClientRole());
    			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrBGCell = contrNM.createCell(4);
    			CntrBGCell.setCellValue(item.getMasterRole().getBandGrade());
    			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));	
    			
    			for(int i=5; i<=intMonthDiff+5;i++)
    			{
    				int intCount = 0;
    				for(Object obj : lstColList)
    				{
    					if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
    					{
    						intCount++;
    						Cell Total = contrNM.createCell(i);
    						Total.setCellStyle(styles.get("ColumnAlignRight"));
    						break;
    					}
    				}
    				if(intCount < 1)
    				{
    					Cell ctrtTotal = contrNM.createCell(i);
    					ctrtTotal.setCellType(CellType.FORMULA);		        	
    					AppLoger.APPLOGGER.info("Formula Cell: " + ""+CellReference.convertNumToColString(i)+"7");
    					ctrtTotal.setCellFormula(""+CellReference.convertNumToColString(i)+"7");	        	
    					ctrtTotal.setCellStyle(styles.get("ColumnAlignRight"));
    				}
    			}
    			
    			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
    			int colIndex = totalColIndex -1;
    			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
    			
    			for (Object obj : lstColList) 
    			{
    				strFormula += "-("+obj+""+(rowCount+1)+")";
    			}
    			AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
    			CntrTotalCell.setCellFormula(strFormula);
    			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder"));	
    			rowCount++;
    		}
        }
		return rowCount;
	}


	private static Cell setCellHorTotalOther(int rowCount, Map<String, CellStyle> styles, int intMonthDiff,
				List<Object> lstColList, Sheet sheet, int totalColIndex, int longTermH1RoleStarted) 
	{
		Row VisaIdTwoTotal = sheet.createRow(rowCount);
    	Cell SubTotalVisaIdTwo = VisaIdTwoTotal.createCell(2);
    	SubTotalVisaIdTwo.setCellValue("SubTotal");	    	
    	SubTotalVisaIdTwo.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	
    	Cell CellST_CL_VIdTwo = VisaIdTwoTotal.createCell(3);
    	CellST_CL_VIdTwo.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	Cell CellST_BG_VIdTwo = VisaIdTwoTotal.createCell(4);
    	CellST_BG_VIdTwo.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	
        for(int i=5; i<=intMonthDiff+5;i++)
        {
        	int intCount = 0;
        	for(Object obj : lstColList)
        	{
        		if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
				{
        			intCount++;
        			Cell Total = VisaIdTwoTotal.createCell(i);
        			Total.setCellStyle(styles.get("ColumnAlignRightTopBorder2Decimal"));
        			break;
				}
        	}
        	if(intCount < 1)
    		{
	        	Cell Total = VisaIdTwoTotal.createCell(i);
	        	Total.setCellType(CellType.FORMULA);		        	     
	        	int rowTotal = Total.getRowIndex();
	        	AppLoger.APPLOGGER.info("VisaIdTwo SubTotal Formula Cell: " + "SUM("+CellReference.convertNumToColString(i)+""+longTermH1RoleStarted +":"+CellReference.convertNumToColString(i)+""+rowTotal+")");
	        	Total.setCellFormula("SUM("+CellReference.convertNumToColString(i)+""+longTermH1RoleStarted +":"+CellReference.convertNumToColString(i)+""+rowTotal+")");	        	
	        	Total.setCellStyle(styles.get("ColumnAlignRightTopBorder2Decimal"));
    		}
        }
        
        Cell VerTotalVID2 = VisaIdTwoTotal.createCell(totalColIndex);
        int VerTotalTillVID2Cell = SubTotalVisaIdTwo.getRow().getRowNum();
        AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(totalColIndex)+""+longTermH1RoleStarted+":"+CellReference.convertNumToColString(totalColIndex)+""+VerTotalTillVID2Cell+")");
        VerTotalVID2.setCellFormula("SUM("+CellReference.convertNumToColString(totalColIndex)+""+longTermH1RoleStarted+":"+CellReference.convertNumToColString(totalColIndex)+""+VerTotalTillVID2Cell+")");
        VerTotalVID2.setCellStyle(styles.get("ColumnAlignRightTopThinRightMedBorder2Decimal"));
        
        return VerTotalVID2;
	}


	private static Cell setCellHorTotal(int rowCount, Map<String, CellStyle> styles, int intMonthDiff,
				List<Object> lstColList,Sheet sheet,int totalColIndex) 
	{
		Row VisaIdOneTotal = sheet.createRow(rowCount);	        
    	Cell SubTotal = VisaIdOneTotal.createCell(2);
    	SubTotal.setCellValue("SubTotal");
    	SubTotal.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	
    	Cell CellST_CL = VisaIdOneTotal.createCell(3);
    	CellST_CL.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	Cell CellST_BG = VisaIdOneTotal.createCell(4);
    	CellST_BG.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    	
    	
        for(int i=5; i<=intMonthDiff+5;i++)
        {
        	int intCount = 0;
        	for(Object obj : lstColList)
        	{
        		if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(i))))
				{
        			intCount++;
        			Cell Total = VisaIdOneTotal.createCell(i);
        			Total.setCellStyle(styles.get("ColumnAlignRightTopBorder2Decimal"));
        			break;
				}
        	}
        	if(intCount < 1)
    		{
    			Cell Total = VisaIdOneTotal.createCell(i);
	        	Total.setCellType(CellType.FORMULA);	        	     
	        	int rowTotal = Total.getRowIndex();
	        	
	        	AppLoger.APPLOGGER.info(" VisaIdOne SubTotal Formula Cell: " + "SUM("+CellReference.convertNumToColString(i)+"7:"+CellReference.convertNumToColString(i)+""+rowTotal+")");
	        	Total.setCellFormula("SUM("+CellReference.convertNumToColString(i)+"7:"+CellReference.convertNumToColString(i)+""+rowTotal+")");	        	
	        	Total.setCellStyle(styles.get("ColumnAlignRightTopBorder2Decimal"));
    		}
			
    	}
        
        Cell VerTotal = VisaIdOneTotal.createCell(totalColIndex);
        VerTotal.setCellType(CellType.FORMULA);
        int VerTotalTillCell = SubTotal.getRow().getRowNum();
        AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(totalColIndex)+"7:"+CellReference.convertNumToColString(totalColIndex)+""+VerTotalTillCell+")");
        VerTotal.setCellFormula("SUM("+CellReference.convertNumToColString(totalColIndex)+"7:"+CellReference.convertNumToColString(totalColIndex)+""+VerTotalTillCell+")");
        VerTotal.setCellStyle(styles.get("ColumnAlignRightTopThinRightMedBorder2Decimal"));
        return VerTotal;
	}


	private static int fillContractorRoles(List<StaffingDetails> lstStaffing, Map<String, CellStyle> styles,
				int totalColIndex, int rowCount,Sheet sheet, int intMonthDiff) 
	{
		Row contractor = sheet.createRow(rowCount);
    	Cell CntrCell = contractor.createCell(2);
    	CntrCell.setCellValue("Onsite - Local(Contractor)");
    	CntrCell.setCellStyle(styles.get("ColumnAlignLeftTopMedLeftMedFontBoldBorder"));
    	
    	setSectionHeaders(contractor,totalColIndex,styles);
    	
    	rowCount++;
    	for(StaffingDetails item : lstStaffing)
        {
    		StringBuilder strBuilder = new StringBuilder();
	        String[] arrOfStr = null;
    		
    		if(item.getMasterRoleId() == 1)
    		{
    			Row contrNM = sheet.createRow(rowCount);
    			Cell CntrVSId = contrNM.createCell(1);
    			CntrVSId.setCellValue("1");
    			CntrVSId.setCellStyle(styles.get("ColumnAlignRight"));
    			
    			Cell CntrNMCell = contrNM.createCell(2);
    			CntrNMCell.setCellValue(item.getMasterRoles().getMasterRoleName());
    			CntrNMCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrCLCell = contrNM.createCell(3);
    			CntrCLCell.setCellValue(item.getCustomerRole().isEmpty()?"":item.getCustomerRole());
    			CntrCLCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
    			Cell CntrBGCell = contrNM.createCell(4);
    			CntrBGCell.setCellValue(item.getMasterRoles().getGcmCODE());
    			CntrBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));		  
    			
    			int intEmptyCell = 1;
    			Boolean isMonthReset = false;
    			
    			Cell cellFirst = contrNM.createCell(5);
				cellFirst.setCellValue(0.00);
				cellFirst.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
				
    			for(int i=6; i<=intMonthDiff+5;i++)
    			{	
    				if(intEmptyCell == 12)
    				{
    					intEmptyCell = -1;
    					isMonthReset = true;
    					strBuilder.append(CellReference.convertNumToColString(i)+",");
    				}
    				else
    				{
    					Cell Total = contrNM.createCell(i);
    					Total.setCellType(CellType.FORMULA);		        	
    					int rowTotal = Total.getRowIndex()+1;
    					if(isMonthReset)
    					{
    						isMonthReset = false;
    						AppLoger.APPLOGGER.info("Onsite - Local(Contractor) Formula Cell MonthReset: " + ""+CellReference.convertNumToColString(i-2)+""+rowTotal);
    						Total.setCellFormula(""+CellReference.convertNumToColString(i-2)+""+rowTotal);	        	
    					}
    					else
    					{
    						AppLoger.APPLOGGER.info("Onsite - Local(Contractor) Formula Cell: " + ""+CellReference.convertNumToColString(i-1)+""+rowTotal);
    						Total.setCellFormula(""+CellReference.convertNumToColString(i-1)+""+rowTotal);
    					}
    					Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
    				}
    				intEmptyCell++;
    			}
    			
    			
    			if(strBuilder.length()>0)
    				arrOfStr = strBuilder.toString().split(",");  
    			
    			Cell CntrTotalCell = contrNM.createCell(totalColIndex);
    			int colIndex = totalColIndex -1;
    			AppLoger.APPLOGGER.info("Formula Cell: " + "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			CntrTotalCell.setCellFormula("SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")");
    			CellReference.convertNumToColString(CntrTotalCell.getRowIndex());
    			
    			String strFormala = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
    			for (String a : arrOfStr) 
    			{
    				strFormala += "-("+a+""+(rowCount+1)+")";
    			}
    			
    			AppLoger.APPLOGGER.info("Formula Cell: " + strFormala);
    			CntrTotalCell.setCellFormula(strFormala);
    			CntrTotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));	
    			rowCount++;
    		}
		}
    	return rowCount;
	}


	private static void setEmptyRow(Row emptyRow, int totalColIndex, Map<String, CellStyle> styles) {
		for(int j=3;j<= 4;j++)
        {
			Cell Cell = emptyRow.createCell(j);
			Cell.setCellStyle(styles.get("createTopHairRightMedBottomHairLeftMedBorderStyle"));
        }
		
		Cell cellTotalIndexHeader = emptyRow.createCell(totalColIndex);
		cellTotalIndexHeader.setCellStyle(styles.get("ColumnAlignRightRightBorder"));
			
		}


	private static void setSectionHeadersOthers(Row visaId, int totalColIndex, Map<String, CellStyle> styles) 
	{
		for(int j=3;j<= 4;j++)
        {
			Cell Cell = visaId.createCell(j);
			Cell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
        }
		
		Cell cellTotalIndexHeader = visaId.createCell(totalColIndex);
		cellTotalIndexHeader.setCellStyle(styles.get("ColumnAlignRightRightBorder"));
    		
	}


	private static void setSectionHeaders(Row contractor, int totalColIndex, Map<String, CellStyle> styles) 
	{
		for(int j=3;j<= totalColIndex;j++)
        {
        	Cell Cell = contractor.createCell(j);		        		        				        
    		if(j<5)
    			Cell.setCellStyle(styles.get("ColumnAlignRightRightTopBorder"));
    		else if(j==totalColIndex)
    			Cell.setCellStyle(styles.get("TopMedRightMedBorder"));
    		else
    			Cell.setCellStyle(styles.get("ColumnAlignRightTopMedBorder"));	        			
        }			
	}

	private static List<Object> fillRoles(List<StaffingDetails> lstStaffing, int rowCount,
				Map<String, CellStyle> styles, int visaId, Sheet sheet, int totalColIndex, int intMonthDiff, Calendar startCalendar) 
	{  		
		List<Object> lstData = new ArrayList<Object>();
		 
		String[] arrCol = null;
		for(int i=0; i<lstStaffing.size(); i++)
		{
	        StringBuilder strBuilder = new StringBuilder();
	        String[] arrOfStr = null;
	        
			if(lstStaffing.get(i).getMasterRoleId() != 1)
			{
				Row dataRow = sheet.createRow(rowCount);
				Cell dataCell = dataRow.createCell(1);
				dataCell.setCellValue(Integer.toString(visaId));
				dataCell.setCellStyle(styles.get("ColumnAlignRight"));
				Cell dataRCCell = dataRow.createCell(2);
				dataRCCell.setCellValue(lstStaffing.get(i).getMasterRoles().getMasterRoleName());
				dataRCCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataClRoleCell = dataRow.createCell(3);
				dataClRoleCell.setCellValue(lstStaffing.get(i).getCustomerRole());
				dataClRoleCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataBGCell = dataRow.createCell(4);
				dataBGCell.setCellValue(lstStaffing.get(i).getMasterRoles().getGcmCODE());
				dataBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));				
				
				Cell cellFirst = dataRow.createCell(5);
				cellFirst.setCellValue(0.00);
				cellFirst.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
				
				int MonthIndex = 1;
		        Boolean isMonthReset = false;
				for(int j=6; j<=intMonthDiff+5;j++)
				{
					if(MonthIndex == 12)
		        	{
		        		MonthIndex=-1;
		        		isMonthReset = true;		        		
						strBuilder.append(CellReference.convertNumToColString(j)+",");
		        	}
		        	else
		        	{
		        		Cell Total = dataRow.createCell(j);
		        		Total.setCellType(CellType.FORMULA);		        	
		        		int rowTotal = Total.getRowIndex()+1;

		        		if(isMonthReset)
			        	{
		        			isMonthReset = false;
							AppLoger.APPLOGGER.info("Individual cell  : Formula Cell: " + ""+CellReference.convertNumToColString(j-2)+""+rowTotal);
							Total.setCellFormula(""+CellReference.convertNumToColString(j-2)+""+rowTotal);	        	

			        	}
						else
						{
							AppLoger.APPLOGGER.info("Individual cell : Formula Cell: " + ""+CellReference.convertNumToColString(j-1)+""+rowTotal);
			        		Total.setCellFormula(""+CellReference.convertNumToColString(j-1)+""+rowTotal);
						}
		        		Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
		        	}
					MonthIndex++;
				}
				
				if(strBuilder.length()>0)
				{
					arrOfStr = strBuilder.toString().split(","); 
					arrCol = strBuilder.toString().split(","); 
				}
				
				Cell TotalCell = dataRow.createCell(totalColIndex);
				TotalCell.setCellType(CellType.FORMULA);	
				int colIndex = totalColIndex -1;
				
				String strFormala = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
				if(arrOfStr != null)
				{
			        for (String a : arrOfStr) 
			        {
			        	strFormala += "-("+a+""+(rowCount+1)+")";
			        }
				}
		        AppLoger.APPLOGGER.info("Same row Horizontal total: totalFormula Cell: " + strFormala);
				TotalCell.setCellFormula(strFormala);
				TotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));
				
				rowCount++;
			}
		}
		
		int intRowCount  = rowCount;
	    lstData.add(0,intRowCount);
	    if(arrCol != null)
		{
		    for (String a : arrCol) 
	        {
		    	 lstData.add(a);
	        }
		}
	    
		return lstData;
	}
	
	private static int fillTotalOnsiteRoles(List<StaffingDetails> lstStaffing, int rowCount,
			Map<String, CellStyle> styles, Sheet sheet, int totalColIndex, int intMonthDiff,
			int contractorRowIndex, int longTermH1RoleStarted, int shortTermRoleStarted, List<Object> lstColList) 
	{
		int intCntrRowIndex = contractorRowIndex;
		int intLngTrmH1RoleInd = longTermH1RoleStarted;
		int intShrtTrmRoleInd = shortTermRoleStarted;
		for(int i=0; i<lstStaffing.size(); i++)
		{
			if(lstStaffing.get(i).getMasterRoleId() != 1)
			{
				Row dataRow = sheet.createRow(rowCount);				
				Cell dataRCCell = dataRow.createCell(2);
				dataRCCell.setCellValue(lstStaffing.get(i).getMasterRoles().getMasterRoleName());
				dataRCCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataClRoleCell = dataRow.createCell(3);
				dataClRoleCell.setCellValue(lstStaffing.get(i).getCustomerRole().isEmpty()?"":lstStaffing.get(i).getCustomerRole());
				dataClRoleCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataBGCell = dataRow.createCell(4);
				dataBGCell.setCellValue(lstStaffing.get(i).getMasterRoles().getGcmCODE());
				dataBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				
				// Traverse through each cell
				for(int j=5; j<=intMonthDiff+5;j++)
		        {
					int intCount = 0;
		        	for(Object obj : lstColList)
		        	{
		        		if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(j))))
	    				{
		        			intCount++;		        			
		        			break;
	    				}
		        	}
		        	if(intCount < 1)
	        		{
		        		Cell Total = dataRow.createCell(j);
			        	Total.setCellType(CellType.FORMULA);
			        	AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(j)+""+intCntrRowIndex +","+CellReference.convertNumToColString(j)+""+intLngTrmH1RoleInd+","+ CellReference.convertNumToColString(j)+""+intShrtTrmRoleInd+")");
			        	Total.setCellFormula("SUM("+CellReference.convertNumToColString(j)+""+intCntrRowIndex +","+CellReference.convertNumToColString(j)+""+intLngTrmH1RoleInd+","+ CellReference.convertNumToColString(j)+""+intShrtTrmRoleInd+")");	        	
			        	Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	        		}
		        }
							
				intCntrRowIndex++;
				intLngTrmH1RoleInd++;
				intShrtTrmRoleInd++;
				   
				Cell TotalCell = dataRow.createCell(totalColIndex);
				int colIndex = totalColIndex -1;
				String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
				for (Object obj : lstColList) 
		        {
		        	strFormula += "-("+obj+""+(rowCount+1)+")";
		        }
				AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
				TotalCell.setCellFormula(strFormula);
				TotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));
				
				rowCount++;
			}
		}
		return rowCount;
	}
	
	private static int fillTotalStaffing(List<StaffingDetails> lstStaffing, int rowCount,
			Map<String, CellStyle> styles, Sheet sheet, int totalColIndex, int intMonthDiff,
			int totalOnsiteRoleStarted, int totalOffshorewRoleStarted, List<Object> lstColList) 
	{
		int intToOnsiteRoleIndex = totalOnsiteRoleStarted;
		int intToOffRoleIndex = totalOffshorewRoleStarted;
		
		for(int i=0; i<lstStaffing.size(); i++)
		{
			if(lstStaffing.get(i).getMasterRoleId() != 1)
			{
				Row dataRow = sheet.createRow(rowCount);				
				Cell dataRCCell = dataRow.createCell(2);
				dataRCCell.setCellValue(lstStaffing.get(i).getMasterRoles().getMasterRoleName());
				dataRCCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataClRoleCell = dataRow.createCell(3);
				dataClRoleCell.setCellValue(lstStaffing.get(i).getCustomerRole().isEmpty()?"":lstStaffing.get(i).getCustomerRole());
				dataClRoleCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				Cell dataBGCell = dataRow.createCell(4);
				dataBGCell.setCellValue(lstStaffing.get(i).getMasterRoles().getGcmCODE());
				dataBGCell.setCellStyle(styles.get("ColumnAlignLeftTopHairRightMedLeftMedBorder"));
				
				// Traverse through each cell
				for(int j=5; j<=intMonthDiff+5;j++)
		        {
					int intCount = 0;
		        	for(Object obj : lstColList)
		        	{
		        		if((obj.toString().equalsIgnoreCase(CellReference.convertNumToColString(j))))
	    				{
		        			intCount++;
		        			break;
	    				}
		        	}
		        	if(intCount < 1)
	        		{
		        		Cell Total = dataRow.createCell(j);
		        		Total.setCellType(CellType.FORMULA);
		        		AppLoger.APPLOGGER.info("Formula Cell: " + "SUM("+CellReference.convertNumToColString(j)+""+intToOnsiteRoleIndex+","+""+CellReference.convertNumToColString(j)+"" +intToOffRoleIndex+")");
		        		Total.setCellFormula("SUM("+CellReference.convertNumToColString(j)+""+intToOnsiteRoleIndex+","+""+CellReference.convertNumToColString(j)+"" +intToOffRoleIndex+")");	        	
		        		Total.setCellStyle(styles.get("ColumnAlignRight2Decimal"));
	        		}
		        }
							
				intToOnsiteRoleIndex++;
				intToOffRoleIndex++;
				
				
				Cell TotalCell = dataRow.createCell(totalColIndex);
				int colIndex = totalColIndex -1;
				
				String strFormula = "SUM(F"+(rowCount+1)+":"+CellReference.convertNumToColString(colIndex)+""+(rowCount+1)+")";
				for (Object obj : lstColList) 
		        {
		        	strFormula += "-("+obj+""+(rowCount+1)+")";
		        }
				AppLoger.APPLOGGER.info("Formula Cell: " + strFormula);
				TotalCell.setCellFormula(strFormula);
				TotalCell.setCellStyle(styles.get("ColumnAlignRightRightBorder2Decimal"));
				
				rowCount++;
			}
		}
		return rowCount;
	}


	public String getMonth(int month) 
	{
		return new DateFormatSymbols().getMonths()[month];  
	} 
	
	private static Date getDate(String strdate)
	{		
		SimpleDateFormat sdf = 
		        new SimpleDateFormat("yyyy-MM-dd");
		    Date date = null;;
			try 
			{
				date = sdf.parse(strdate);
			} 
			catch (ParseException e) 
			{				
				e.printStackTrace();
			} 
		
		return date;
	}
	
	private static void setMonthHeder(Calendar startCalendar, Row objrow, int intCellNumber, int intMonthDiff, Map<String, CellStyle> styles, Sheet sheet) 
	{
		AppLoger.APPLOGGER.info("Month Diff Value  : " + intMonthDiff);
		ArrayList<String> lstMonths = new ArrayList<>();
		
		Cell headerCell;
		int intEmptyCell = 0;
		int j = startCalendar.get(Calendar.MONTH);		
		for(int i=0;i<=intMonthDiff;i++)
		{
			AppLoger.APPLOGGER.info("Value of i : " + i);
			AppLoger.APPLOGGER.info("Value of j : " + j);
			AppLoger.APPLOGGER.info("Value of intEmptyCell : " + intEmptyCell);
			if(intEmptyCell == 12)
			{
				j = j-1;				
				intEmptyCell = -1;
				headerCell = objrow.createCell(intCellNumber);				
				headerCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightHairBorder"));
			}
			else			
			{
				String mothName = new DateFormatSymbols().getMonths()[j].toString().substring(0, 3);				
				headerCell = objrow.createCell(intCellNumber);
				headerCell.setCellValue(mothName);
				headerCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightHairBorder"));
				
				lstMonths.add(mothName);					
				AppLoger.APPLOGGER.info("Month Value : " + mothName);				
			}
						
			j++;
			if(j==12)
				j=0;
			intCellNumber++;
			intEmptyCell++;
		}

		AppLoger.APPLOGGER.info("Month List size : " + lstMonths.size());
	}
	
	private static int setYearHeder(Calendar startCalendar, Row objrow, int intCellNumber, int intMonthDiff, Map<String, CellStyle> styles) 
	{
		AppLoger.APPLOGGER.info("Month Diff Value  : " + intMonthDiff);		
		ArrayList<Integer> lstYears = new ArrayList<>();
		Cell headerCell;
		int intEmptyCell = 0;
		int j = startCalendar.get(Calendar.MONTH);
		int intYear =  startCalendar.get(Calendar.YEAR);
		for(int i=0;i<=intMonthDiff;i++)
		{
			AppLoger.APPLOGGER.info("Value of i : " + i);
			AppLoger.APPLOGGER.info("Value of j : " + j);
			
			if(intEmptyCell == 12)
			{
				j=j-1;			
				/*intYear++;*/
				intEmptyCell = -1;
				headerCell = objrow.createCell(intCellNumber);				
				headerCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightHairBorder"));
			}
			else
			{						
				headerCell = objrow.createCell(intCellNumber);
				headerCell.setCellValue(intYear);
				headerCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightHairBorder"));
				lstYears.add(intYear);
				
				AppLoger.APPLOGGER.info("Year Value : " + intYear);
			}
						
			j++;
			if(j==12)
			{
				j=0;
				intYear++;
			}
			intCellNumber++;
			intEmptyCell++;
		}
		
		headerCell = objrow.createCell(intCellNumber);
		headerCell.setCellValue("  Total  ");
		headerCell.setCellStyle(styles.get("ColumnAlignCenterTopHairRightMedrBorder"));
		AppLoger.APPLOGGER.info("Year List size : " + lstYears.size());
		return intCellNumber;
	}
	
	
	
		//Formatting Border Style
		private static CellStyle createTopBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop((short) 1);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopThinRightMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopHairBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.HAIR);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createRightMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createLeftTopMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopRightMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopMedRightHairBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.HAIR);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopMedRightMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		
		private static CellStyle createLeftMedTopHairBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderTop(BorderStyle.HAIR);
			style.setTopBorderColor(black);
			return style;
		}
		
		
		private static CellStyle createTopHairRightMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.HAIR);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopHairRightMedLeftMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.HAIR);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopHairRightHairBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.HAIR);
			style.setBorderRight(BorderStyle.HAIR);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopMedLeftMedFontBoldBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}
		
		private static CellStyle createTopHairRightMedBottomHairLeftMedBorderStyle(Workbook wb) {
			short black = IndexedColors.BLACK.getIndex();
			CellStyle style = wb.createCellStyle();			
			style.setBorderTop(BorderStyle.HAIR);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.HAIR);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setTopBorderColor(black);
			return style;
		}


		public static byte[] getDealSummeryReport(List<SDealReport> dealData,int vertical,String verticalName,String currentDate,String currentUser) {
			AppLoger.APPLOGGER.info("_____________________________________________________________________________________________");
			AppLoger.APPLOGGER.info("Inside the Excel Download function Deal Summery Report");
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			if(vertical != 9999999){
				int n=0;
			try 
			{
				//create excel xls sheet
				Map<String, CellStyle> styles = createStyles(wb);
			    Sheet sheet = wb.createSheet(verticalName);
			    sheet.setFitToPage(true);
			    sheet.setDisplayGridlines(false);
			    
			    // create style for header cells
			    CellStyle style = wb.createCellStyle();
			    Font font = wb.createFont();
			    font.setFontName("Arial");
			    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
			    font.setBold(true);
			    font.setColor(HSSFColor.WHITE.index);
			    style.setFont(font);
			    int rowVal=0;
			    
			    Row titleRow = sheet.createRow(rowVal);
			    titleRow.setHeightInPoints(20);
			    
			    
				Cell titleCell = titleRow.createCell(0);
				titleCell.setCellValue("Deal Details");
				titleCell.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				Cell titleCell2 = titleRow.createCell(1);
				titleCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell3 = titleRow.createCell(2);
				titleCell3.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell4 = titleRow.createCell(3);
				titleCell4.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell5 = titleRow.createCell(4);
				titleCell5.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell6 = titleRow.createCell(5);
				titleCell6.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell7 = titleRow.createCell(6);
				titleCell7.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell8 = titleRow.createCell(7);
				titleCell8.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell9 = titleRow.createCell(8);
				titleCell9.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell10 = titleRow.createCell(9);
				titleCell10.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				Cell titleCell11 = titleRow.createCell(10);
				titleCell11.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell12 = titleRow.createCell(11);
				titleCell12.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell13 = titleRow.createCell(12);
				titleCell13.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell14 = titleRow.createCell(13);
				titleCell14.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell15 = titleRow.createCell(14);
				titleCell15.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell16 = titleRow.createCell(15);
				titleCell16.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell17 = titleRow.createCell(16);
				titleCell17.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell18 = titleRow.createCell(17);
				titleCell18.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell19 = titleRow.createCell(18);
				titleCell19.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell20 = titleRow.createCell(19);
				titleCell20.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				Cell titleCell21 = titleRow.createCell(20);
				titleCell21.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell22 = titleRow.createCell(21);
				titleCell22.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell23 = titleRow.createCell(22);
				titleCell23.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell24 = titleRow.createCell(23);
				titleCell24.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell25 = titleRow.createCell(24);
				titleCell25.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell26 = titleRow.createCell(25);
				titleCell26.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell27 = titleRow.createCell(26);
				titleCell27.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell28 = titleRow.createCell(27);
				titleCell28.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell29 = titleRow.createCell(28);
				titleCell29.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell30 = titleRow.createCell(29);
				titleCell30.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				Cell titleCell31 = titleRow.createCell(30);
				titleCell31.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell32 = titleRow.createCell(31);
				titleCell32.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell33 = titleRow.createCell(32);
				titleCell33.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell34 = titleRow.createCell(33);
				titleCell34.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell35 = titleRow.createCell(34);
				titleCell35.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell36 = titleRow.createCell(35);
				titleCell36.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell37 = titleRow.createCell(36);
				titleCell37.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell38 = titleRow.createCell(37);
				titleCell38.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell39 = titleRow.createCell(38);
				titleCell39.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell40 = titleRow.createCell(39);
				titleCell31.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				Cell titleCell41 = titleRow.createCell(40);
				titleCell41.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell42 = titleRow.createCell(41);
				titleCell42.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell43 = titleRow.createCell(42);
				titleCell43.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell44 = titleRow.createCell(43);
				titleCell44.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell45 = titleRow.createCell(44);
				titleCell45.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				Cell titleCell46 = titleRow.createCell(45);
				titleCell46.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
				
				
				sheet.addMergedRegion(new CellRangeAddress(rowVal,rowVal,0,45));
				
				Cell titleCell47 = titleRow.createCell(46);
				titleCell47.setCellValue("MSA TCV Calculations");
				titleCell47.setCellStyle(styles.get("Header"));
				Cell titleCell48 = titleRow.createCell(47);
				titleCell48.setCellStyle(styles.get("Header"));
				Cell titleCell49 = titleRow.createCell(48);
				titleCell49.setCellStyle(styles.get("Header"));
				Cell titleCell50 = titleRow.createCell(49);
				titleCell50.setCellStyle(styles.get("Header"));
				Cell titleCell51 = titleRow.createCell(50);
				titleCell51.setCellStyle(styles.get("Header"));
				Cell titleCell52 = titleRow.createCell(51);
				titleCell52.setCellStyle(styles.get("Header"));
				Cell titleCell53 = titleRow.createCell(52);
				titleCell53.setCellStyle(styles.get("Header"));
				Cell titleCell54 = titleRow.createCell(53);
				titleCell54.setCellStyle(styles.get("Header"));
				Cell titleCell55 = titleRow.createCell(54);
				titleCell55.setCellStyle(styles.get("Header"));
				Cell titleCell56 = titleRow.createCell(55);
				titleCell56.setCellStyle(styles.get("Header"));
				Cell titleCell57 = titleRow.createCell(56);
				titleCell57.setCellStyle(styles.get("Header"));
				
				
				sheet.addMergedRegion(new CellRangeAddress(rowVal,rowVal,46,56));
				
				rowVal+=1;
			    
			    
			    

				final String[] headerList1 = {"Sr.No.","Last Action Date","New/Renewal/RFP-RFI","Vertical","Customer","Project Description","Version Name","Deal ID","Proj.Type","Capacity based","Billing Currency"
						,"Revenue","Cost","PM % (Gross)","SGA cost","OM%","Volume Discount","OM % after Volume disocunt","YTD Sold PM %","Start Dt","End Dt","LOB","Local HC","Deputed HC",
						"Offshore HC","Subcontractor Onsite","Subcontractor Offshore","Total HC","Onsite %","Off B2 %","Ap1%","Old Deal ID","Old PID","Old sold margin","Operating metrics of old deal",
						"Rate card ID","On Hrs per month","Off Hrs per month","Onsite Hrs","Off Hrs","Total Hrs","Onsite TCV","Off TCV","Total TCV","Additonal Disc","Requestor Name","On Hrs per month",
						"Off Hrs per month","Onsite Hrs","Off Hrs","Total Hrs","Onsite TCV","Off TCV","Total TCV","Additonal Disc","USER Name","Comments"};
				
				rowVal+=1;
				Row headerRow = sheet.createRow(rowVal);
				headerRow.setHeightInPoints(16);
				Cell headerCell;
				for (int i = 0; i < headerList1.length; i++) 
				{
					if(i < 46){
					headerCell = headerRow.createCell(i);
					headerCell.setCellValue(headerList1[i]);
					headerCell.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					}
					else
					{
						headerCell = headerRow.createCell(i);
						headerCell.setCellValue(headerList1[i]);
						headerCell.setCellStyle(styles.get("Header"));	
					}
				}

				rowVal ++;
				final String[] verticalList = new String[headerList1.length];
				
			for(int i=0;i<dealData.size();i++)
				{	if(!(dealData.get(i).equals(null))){
					verticalList[0]=""+n;
					n++;
					String[] sdate1 = new String[2];
					String[] sdate = new String[2];
					sdate1 = dealData.get(i).getDate().split(" ");
					sdate = sdate1[0].split("-");
					
					verticalList[1]=(sdate[0]+"/"+sdate[1]+"/"+sdate[2]);
					
					verticalList[2]=dealData.get(i).getNewRenewal();
					/*String startDate[]=dealData.get(i).getRcStartDate().split(" ");
					String sDFormat[]=startDate[0].split("-");
					startDate[0]=sDFormat[2]+"/"+sDFormat[1]+"/"+sDFormat[0];*/
					verticalList[3]=dealData.get(i).getVerticalName();
					/*String endDate[]=RC.get(i).getExpectedRCEndDate().split(" ");
					String eDFormat[]=endDate[0].split("-");
					endDate[0]=eDFormat[2]+"/"+eDFormat[1]+"/"+eDFormat[0];*/
					verticalList[4]=dealData.get(i).getCustomerName();
					verticalList[5]=dealData.get(i).getDealDescription();
					verticalList[6]=dealData.get(i).getDealVersion();
					if(dealData.get(i).getCrm_deal_id() == null)
					{
						verticalList[7] = "";
					} else{
						verticalList[7]=dealData.get(i).getCrm_deal_id().toString();
					}
					verticalList[8]=dealData.get(i).getProjectType();
					verticalList[9]=dealData.get(i).getCapacityBased();
					verticalList[10]=dealData.get(i).getCurrencyCode();
					
					if(dealData.get(i).getDealTcv() == null){
						verticalList[11]="";
					}else{
						verticalList[11]=dealData.get(i).getDealTcv().toString();
					}
					if(dealData.get(i).getDirectCost() == null){
						verticalList[12]="";
					}else{
						verticalList[12]=dealData.get(i).getDirectCost().toString();
					}
					if(dealData.get(i).getGM() == null){
						verticalList[13]="";
					}else{
						verticalList[13]=dealData.get(i).getGM().toString();
					}
					if(dealData.get(i).getSGAcost() == null){
						verticalList[14]="";
					}else{
						verticalList[14]=dealData.get(i).getSGAcost().toString();
					}
					if(dealData.get(i).getSGAcost() == null){
						verticalList[15]="";
					}else{
						verticalList[15]=dealData.get(i).getOM().toString();
					}
					if(dealData.get(i).getVolumediscountpercent() == null){
						verticalList[16]="";
					}else{
						verticalList[16]=dealData.get(i).getVolumediscountpercent().toString();
					}
					if(dealData.get(i).getOMpercentafterVolumedisocunt() == null){
						verticalList[17]="";
					}else{
						verticalList[17]=dealData.get(i).getOMpercentafterVolumedisocunt().toString();
					}
					if(dealData.get(i).getYTD_Sold_GM_percent() == null){
						verticalList[18]="";
					}else{
						verticalList[18]=dealData.get(i).getYTD_Sold_GM_percent().toString();
					}
					String[] DealStartDt = new String [2];
					String[] startDt = new String [2];
					DealStartDt = dealData.get(i).getDealStartDate().split(" ");
					startDt = DealStartDt[0].split("-");
					
					verticalList[19]=startDt[1]+"/"+startDt[2]+""+startDt[0] ;
					
					String[] DealendDt = new String [2];
					String[] endDt = new String [2];
					DealendDt = dealData.get(i).getDealStartDate().split(" ");
					endDt = DealendDt[0].split("-");
					
					verticalList[20]=endDt[1]+"/"+endDt[2]+""+endDt[0];
					//verticalList[20]=dealData.get(i).getDealEndDate();
					
					verticalList[21]=dealData.get(i).getLob_code();
					if(dealData.get(i).getLocal_HC() == null){
						verticalList[22]="";
					}else{
						verticalList[22]=dealData.get(i).getLocal_HC().toString();
					}
					if(dealData.get(i).getDeputed_HC() == null){
						verticalList[23]="";
					}else{
						verticalList[23]=dealData.get(i).getDeputed_HC().toString();
					}
					if(dealData.get(i).getOffshore_HC() == null){
						verticalList[24]="";
					}else{
						verticalList[24]=dealData.get(i).getOffshore_HC().toString();
					}
					if(dealData.get(i).getSubcontractorOnsiteHC() == null){
						verticalList[25]="";
					}else{
						verticalList[25]=dealData.get(i).getSubcontractorOnsiteHC().toString();
					}
					if(dealData.get(i).getSubcontractorOffshore() == null){
						verticalList[26]= "";
					}else{
						verticalList[26]=dealData.get(i).getSubcontractorOffshore().toString();
					}
					if(dealData.get(i).getTotal_HC() == null){
						verticalList[27]="";
					}else{
						verticalList[27]=dealData.get(i).getTotal_HC().toString();
					}
					if(dealData.get(i).getOnsite_percent() == null){
						verticalList[28]="";
					}
					else{
						verticalList[28]=dealData.get(i).getOnsite_percent().toString();
					}
					if(dealData.get(i).getOff_B2_percent() == null){
						verticalList[29]="";
					}else{
						verticalList[29]=dealData.get(i).getOff_B2_percent().toString();
					}
					if(dealData.get(i).getOffshore_CH() == null){
						verticalList[30]="";
					}else{
						verticalList[30]=dealData.get(i).getOffshore_CH().toString(); // ap1
					}
					if(dealData.get(i).getOld_Deal_ID() == null){
						verticalList[31]="";
					}else{
						verticalList[31]=dealData.get(i).getOld_Deal_ID().toString();
					}
					if(dealData.get(i).getOld_Project_ID() == null){
						verticalList[32]="";
					}else{
						verticalList[32]=dealData.get(i).getOld_Project_ID().toString();
					}
					if(dealData.get(i).getOld_sold_margin() == null){
						verticalList[33]="";
					}else{
						verticalList[33]=dealData.get(i).getOld_sold_margin().toString();
					}
					verticalList[34]=dealData.get(i).getOperating_metrics();
					if(dealData.get(i).getRate_card() == null){
						verticalList[35] = "";
					}else{
						verticalList[35]=dealData.get(i).getRate_card().toString();
					}
					if(dealData.get(i).getOn_Hrs_per_month() == null){
						verticalList[36]="";
					}else{
						verticalList[36]=dealData.get(i).getOn_Hrs_per_month().toString();
					}
					if(dealData.get(i).getOff_Hrs_per_month() == null){
						verticalList[37]="";
					}else{
						verticalList[37]=dealData.get(i).getOff_Hrs_per_month().toString();
					}
					if(dealData.get(i).getOnsite_Hrs() == null){
						verticalList[38]="";
					}else{
						verticalList[38]=dealData.get(i).getOnsite_Hrs().toString();
					}
					if(dealData.get(i).getOff_Hrs() == null){
						verticalList[39]="";
					}else{
						verticalList[39]=dealData.get(i).getOff_Hrs().toString();
					}
					if(dealData.get(i).getTotal_Hrs() == null){
						verticalList[40]="";
					}else{
						verticalList[40]=dealData.get(i).getTotal_Hrs().toString();
					}
					verticalList[41]=""; // Onsite TCV1
					verticalList[42]=""; // offshore TCV1
					verticalList[43]=""; //Total TCV1
					verticalList[44]=""; //Additoinal Disc
					verticalList[45]=dealData.get(i).getRequestor_Name();
					if(dealData.get(i).getOn_Hrs_per_month2() == null){
						verticalList[46]="";
					}else{
						verticalList[46]=dealData.get(i).getOn_Hrs_per_month2().toString();
					}
					if(dealData.get(i).getOff_Hrs_per_month2() == null){
						verticalList[47]="";
					}else{
						verticalList[47]=dealData.get(i).getOff_Hrs_per_month2().toString();
					}
					if(dealData.get(i).getOnsite_Hrs2() == null){
						verticalList[48]="";
					}else{
						verticalList[48]=dealData.get(i).getOnsite_Hrs2().toString();
					}
					if(dealData.get(i).getOff_Hrs2() == null){
						verticalList[49]="";
					}else{
						verticalList[49]=dealData.get(i).getOff_Hrs2().toString();
					}
					if(dealData.get(i).getTotal_Hrs2() == null){
						verticalList[50]="";
					}else{
						verticalList[50]=dealData.get(i).getTotal_Hrs2().toString();
					}
					verticalList[51]=""; //Onsite TCV 2
					verticalList[52]=""; //offshore TCV2
					verticalList[53]=""; //Total TCV2
					verticalList[54]=""; //Additional Discount2
					verticalList[55]=dealData.get(i).getUpdated_By(); // 
					verticalList[56]=dealData.get(i).getComments();
					
					
					Row header1 = sheet.createRow(rowVal);
					for (int j = 0; j < verticalList.length ; j++) 
					{
							if(j==0|| j==7|| j==11|| j==12|| j==13|| j==14|| j==15|| j==16|| j==17|| j==18|| 
									j==22|| j==23|| j==24|| j==25|| j==26|| j==27|| j==28|| j==29|| j==30|| 
									j==31|| j==32|| j==33|| j==36|| j==37|| j==38|| j==39|| j==40|| j==46|| j==47|| j==48|| j==49|| j==50 )
							{
								
								header1.setHeightInPoints(16);
								Cell headerCell2;
								headerCell2 = header1.createCell(j);
								if(verticalList[j]!=""){
									if(j==0){
										headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
										headerCell2.setCellValue(Integer.parseInt(verticalList[j]));
									}
									else{
								Float value=new Float(verticalList[j]);
								DecimalFormat numberFormat = new DecimalFormat("#.00");
								headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
								headerCell2.setCellValue(numberFormat.format(value));
										}
								}else{
									headerCell2.setCellValue(verticalList[j]);
								}
								headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNum"));
							}
						else
						{
							header1.setHeightInPoints(16);
							Cell headerCell2;
							headerCell2 = header1.createCell(j);
							if(verticalList[j] .equals("B2: %, AP1: %, Onsite: %")){
								verticalList[j] = "";
							}
							
							headerCell2.setCellValue(verticalList[j]);
							headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));

						}		
					}
					rowVal++;
				}
				}
				
				for(int columnIndex = 0; columnIndex <= verticalList.length; columnIndex++) 
				{
					sheet.autoSizeColumn(columnIndex);
				}
				wb.write(out);
				wb.close();
				return out.toByteArray();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			}
			
			
			else{
				List<String> verticalNameList = new ArrayList<>();
				int j=0,n=0;
				for(int i=0;i<dealData.size();i++){
					if(i==0){
						verticalNameList.add(dealData.get(i).getVerticalName());
					j++;
					}
					else{
						for(int k=0;k<dealData.size();k++){
							for(int l=0;l<verticalNameList.size();l++){
								if(!(verticalNameList.contains(dealData.get(k).getVerticalName())))
										{
									verticalNameList.add(dealData.get(k).getVerticalName());
										}
							}
						}
					}
				}
					AppLoger.APPLOGGER.info("Unique Verticals............  "+verticalNameList );
				
				try 
				{	
					for(int m=0;m<verticalNameList.size();m++){
					//create excel xls sheet
					Map<String, CellStyle> styles = createStyles(wb);
				    Sheet sheet = wb.createSheet(verticalNameList.get(m));
				    sheet.setFitToPage(true);
				    sheet.setDisplayGridlines(false);
				    
				    // create style for header cells
				    CellStyle style = wb.createCellStyle();
				    Font font = wb.createFont();
				    font.setFontName("Arial");
				    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
				    font.setBold(true);
				    font.setColor(HSSFColor.WHITE.index);
				    style.setFont(font);
/*				    int rowVal=0;
				    Row titleRow = sheet.createRow(rowVal);
					titleRow.setHeightInPoints(20);*/

				    int rowVal=0;
				    
				    Row titleRow = sheet.createRow(rowVal);
				    titleRow.setHeightInPoints(20);
				    
				    
					Cell titleCell = titleRow.createCell(0);
					titleCell.setCellValue("Deal Details");
					titleCell.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					Cell titleCell2 = titleRow.createCell(1);
					titleCell2.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell3 = titleRow.createCell(2);
					titleCell3.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell4 = titleRow.createCell(3);
					titleCell4.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell5 = titleRow.createCell(4);
					titleCell5.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell6 = titleRow.createCell(5);
					titleCell6.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell7 = titleRow.createCell(6);
					titleCell7.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell8 = titleRow.createCell(7);
					titleCell8.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell9 = titleRow.createCell(8);
					titleCell9.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell10 = titleRow.createCell(9);
					titleCell10.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					Cell titleCell11 = titleRow.createCell(10);
					titleCell11.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell12 = titleRow.createCell(11);
					titleCell12.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell13 = titleRow.createCell(12);
					titleCell13.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell14 = titleRow.createCell(13);
					titleCell14.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell15 = titleRow.createCell(14);
					titleCell15.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell16 = titleRow.createCell(15);
					titleCell16.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell17 = titleRow.createCell(16);
					titleCell17.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell18 = titleRow.createCell(17);
					titleCell18.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell19 = titleRow.createCell(18);
					titleCell19.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell20 = titleRow.createCell(19);
					titleCell20.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					Cell titleCell21 = titleRow.createCell(20);
					titleCell21.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell22 = titleRow.createCell(21);
					titleCell22.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell23 = titleRow.createCell(22);
					titleCell23.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell24 = titleRow.createCell(23);
					titleCell24.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell25 = titleRow.createCell(24);
					titleCell25.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell26 = titleRow.createCell(25);
					titleCell26.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell27 = titleRow.createCell(26);
					titleCell27.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell28 = titleRow.createCell(27);
					titleCell28.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell29 = titleRow.createCell(28);
					titleCell29.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell30 = titleRow.createCell(29);
					titleCell30.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					Cell titleCell31 = titleRow.createCell(30);
					titleCell31.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell32 = titleRow.createCell(31);
					titleCell32.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell33 = titleRow.createCell(32);
					titleCell33.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell34 = titleRow.createCell(33);
					titleCell34.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell35 = titleRow.createCell(34);
					titleCell35.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell36 = titleRow.createCell(35);
					titleCell36.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell37 = titleRow.createCell(36);
					titleCell37.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell38 = titleRow.createCell(37);
					titleCell38.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell39 = titleRow.createCell(38);
					titleCell39.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell40 = titleRow.createCell(39);
					titleCell31.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					Cell titleCell41 = titleRow.createCell(40);
					titleCell41.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell42 = titleRow.createCell(41);
					titleCell42.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell43 = titleRow.createCell(42);
					titleCell43.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell44 = titleRow.createCell(43);
					titleCell44.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell45 = titleRow.createCell(44);
					titleCell45.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					Cell titleCell46 = titleRow.createCell(45);
					titleCell46.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
					
					
					sheet.addMergedRegion(new CellRangeAddress(rowVal,rowVal,0,45));
					
					Cell titleCell47 = titleRow.createCell(46);
					titleCell47.setCellValue("MSA TCV Calculations");
					titleCell47.setCellStyle(styles.get("Header"));
					Cell titleCell48 = titleRow.createCell(47);
					titleCell48.setCellStyle(styles.get("Header"));
					Cell titleCell49 = titleRow.createCell(48);
					titleCell49.setCellStyle(styles.get("Header"));
					Cell titleCell50 = titleRow.createCell(49);
					titleCell50.setCellStyle(styles.get("Header"));
					Cell titleCell51 = titleRow.createCell(50);
					titleCell51.setCellStyle(styles.get("Header"));
					Cell titleCell52 = titleRow.createCell(51);
					titleCell52.setCellStyle(styles.get("Header"));
					Cell titleCell53 = titleRow.createCell(52);
					titleCell53.setCellStyle(styles.get("Header"));
					Cell titleCell54 = titleRow.createCell(53);
					titleCell54.setCellStyle(styles.get("Header"));
					Cell titleCell55 = titleRow.createCell(54);
					titleCell55.setCellStyle(styles.get("Header"));
					Cell titleCell56 = titleRow.createCell(55);
					titleCell56.setCellStyle(styles.get("Header"));
					Cell titleCell57 = titleRow.createCell(56);
					titleCell57.setCellStyle(styles.get("Header"));
					
					
					sheet.addMergedRegion(new CellRangeAddress(rowVal,rowVal,46,56));
					
					rowVal+=1;
				    
					final String[] headerList1 = {"Sr.No.","Last Action Date","New/Renewal/RFP-RFI","Vertical","Customer","Project Description","Version Name","Deal ID","Proj.Type","Capacity based","Billing Currency"
							,"Revenue","Cost","PM % (Gross)","SGA cost","OM%","Volume Discount","OM % after Volume disocunt","YTD Sold PM %","Start Dt","End Dt","LOB","Local HC","Deputed HC",
							"Offshore HC","Subcontractor Onsite","Subcontractor Offshore","Total HC","Onsite %","Off B2 %","Ap1%","Old Deal ID","Old PID","Old sold margin","Operating metrics of old deal",
							"Rate card ID","On Hrs per month","Off Hrs per month","Onsite Hrs","Off Hrs","Total Hrs","Onsite TCV","Off TCV","Total TCV","Additonal Disc","Requestor Name","On Hrs per month",
							"Off Hrs per month","Onsite Hrs","Off Hrs","Total Hrs","Onsite TCV","Off TCV","Total TCV","Additonal Disc","USER Name","Comments"};
					
					rowVal+=1;
					Row headerRow = sheet.createRow(rowVal);
					headerRow.setHeightInPoints(16);
					Cell headerCell;
					for (int i = 0; i < headerList1.length; i++) 
					{
						if(i < 46){
						headerCell = headerRow.createCell(i);
						headerCell.setCellValue(headerList1[i]);
						headerCell.setCellStyle(styles.get("DataRowColumnRightAlignNumSDReportBOLD"));
						}
						else
						{
							headerCell = headerRow.createCell(i);
							headerCell.setCellValue(headerList1[i]);
							headerCell.setCellStyle(styles.get("Header"));	
						}
					}

					rowVal ++;
					final String[] verticalList = new String[headerList1.length];
					
				for(int i=0;i<dealData.size();i++)
					{	
					if(!(dealData.get(i).equals(null)) && dealData.get(i).getVerticalName().equals(verticalNameList.get(m))){
						verticalList[0]=""+n;
						n++;
						String[] sdate1 = new String[2];
						String[] sdate = new String[2];
						sdate1 = dealData.get(i).getDate().split(" ");
						sdate = sdate1[0].split("-");
						
						verticalList[1]=(sdate[0]+"/"+sdate[1]+"/"+sdate[2]);
						
						verticalList[2]=dealData.get(i).getNewRenewal();
						/*String startDate[]=dealData.get(i).getRcStartDate().split(" ");
						String sDFormat[]=startDate[0].split("-");
						startDate[0]=sDFormat[2]+"/"+sDFormat[1]+"/"+sDFormat[0];*/
						verticalList[3]=dealData.get(i).getVerticalName();
						/*String endDate[]=RC.get(i).getExpectedRCEndDate().split(" ");
						String eDFormat[]=endDate[0].split("-");
						endDate[0]=eDFormat[2]+"/"+eDFormat[1]+"/"+eDFormat[0];*/
						verticalList[4]=dealData.get(i).getCustomerName();
						verticalList[5]=dealData.get(i).getDealDescription();
						verticalList[6]=dealData.get(i).getDealVersion();
						if(dealData.get(i).getCrm_deal_id() == null)
						{
							verticalList[7] = "";
						} else{
							verticalList[7]=dealData.get(i).getCrm_deal_id().toString();
						}
						verticalList[8]=dealData.get(i).getProjectType();
						verticalList[9]=dealData.get(i).getCapacityBased();
						verticalList[10]=dealData.get(i).getCurrencyCode();
						
						if(dealData.get(i).getDealTcv() == null){
							verticalList[11]="";
						}else{
							verticalList[11]=dealData.get(i).getDealTcv().toString();
						}
						if(dealData.get(i).getDirectCost() == null){
							verticalList[12]="";
						}else{
							verticalList[12]=dealData.get(i).getDirectCost().toString();
						}
						if(dealData.get(i).getGM() == null){
							verticalList[13]="";
						}else{
							verticalList[13]=dealData.get(i).getGM().toString();
						}
						if(dealData.get(i).getSGAcost() == null){
							verticalList[14]="";
						}else{
							verticalList[14]=dealData.get(i).getSGAcost().toString();
						}
						if(dealData.get(i).getSGAcost() == null){
							verticalList[15]="";
						}else{
							verticalList[15]=dealData.get(i).getOM().toString();
						}
						if(dealData.get(i).getVolumediscountpercent() == null){
							verticalList[16]="";
						}else{
							verticalList[16]=dealData.get(i).getVolumediscountpercent().toString();
						}
						if(dealData.get(i).getOMpercentafterVolumedisocunt() == null){
							verticalList[17]="";
						}else{
							verticalList[17]=dealData.get(i).getOMpercentafterVolumedisocunt().toString();
						}
						if(dealData.get(i).getYTD_Sold_GM_percent() == null){
							verticalList[18]="";
						}else{
							verticalList[18]=dealData.get(i).getYTD_Sold_GM_percent().toString();
						}
					/*	verticalList[19]=dealData.get(i).getDealStartDate();
						verticalList[20]=dealData.get(i).getDealEndDate();*/
						
						String[] DealStartDt = new String [2];
						String[] startDt = new String [2];
						DealStartDt = dealData.get(i).getDealStartDate().split(" ");
						startDt = DealStartDt[0].split("-");
						
						verticalList[19]=startDt[1]+"/"+startDt[2]+"/"+startDt[0] ;
						
						String[] DealendDt = new String [2];
						String[] endDt = new String [2];
						DealendDt = dealData.get(i).getDealStartDate().split(" ");
						endDt = DealendDt[0].split("-");
						
						verticalList[20]=endDt[1]+"/"+endDt[2]+"/"+endDt[0];
						//verticalList[20]=dealData.get(i).getDealEndDate();
						
						verticalList[21]=dealData.get(i).getLob_code();
						if(dealData.get(i).getLocal_HC() == null){
							verticalList[22]="";
						}else{
							verticalList[22]=dealData.get(i).getLocal_HC().toString();
						}
						if(dealData.get(i).getDeputed_HC() == null){
							verticalList[23]="";
						}else{
							verticalList[23]=dealData.get(i).getDeputed_HC().toString();
						}
						if(dealData.get(i).getOffshore_HC() == null){
							verticalList[24]="";
						}else{
							verticalList[24]=dealData.get(i).getOffshore_HC().toString();
						}
						if(dealData.get(i).getSubcontractorOnsiteHC() == null){
							verticalList[25]="";
						}else{
							verticalList[25]=dealData.get(i).getSubcontractorOnsiteHC().toString();
						}
						if(dealData.get(i).getSubcontractorOffshore() == null){
							verticalList[26]= "";
						}else{
							verticalList[26]=dealData.get(i).getSubcontractorOffshore().toString();
						}
						if(dealData.get(i).getTotal_HC() == null){
							verticalList[27]="";
						}else{
							verticalList[27]=dealData.get(i).getTotal_HC().toString();
						}
						if(dealData.get(i).getOnsite_percent() == null){
							verticalList[28]="";
						}
						else{
							verticalList[28]=dealData.get(i).getOnsite_percent().toString();
						}
						if(dealData.get(i).getOff_B2_percent() == null){
							verticalList[29]="";
						}else{
							verticalList[29]=dealData.get(i).getOff_B2_percent().toString();
						}
						if(dealData.get(i).getOffshore_CH() == null){
							verticalList[30]="";
						}else{
							verticalList[30]=dealData.get(i).getOffshore_CH().toString(); // ap1
						}
						if(dealData.get(i).getOld_Deal_ID() == null){
							verticalList[31]="";
						}else{
							verticalList[31]=dealData.get(i).getOld_Deal_ID().toString();
						}
						if(dealData.get(i).getOld_Project_ID() == null){
							verticalList[32]="";
						}else{
							verticalList[32]=dealData.get(i).getOld_Project_ID().toString();
						}
						if(dealData.get(i).getOld_sold_margin() == null){
							verticalList[33]="";
						}else{
							verticalList[33]=dealData.get(i).getOld_sold_margin().toString();
						}
						verticalList[34]=dealData.get(i).getOperating_metrics();
						if(dealData.get(i).getRate_card() == null){
							verticalList[35] = "";
						}else{
							verticalList[35]=dealData.get(i).getRate_card().toString();
						}
						if(dealData.get(i).getOn_Hrs_per_month() == null){
							verticalList[36]="";
						}else{
							verticalList[36]=dealData.get(i).getOn_Hrs_per_month().toString();
						}
						if(dealData.get(i).getOff_Hrs_per_month() == null){
							verticalList[37]="";
						}else{
							verticalList[37]=dealData.get(i).getOff_Hrs_per_month().toString();
						}
						if(dealData.get(i).getOnsite_Hrs() == null){
							verticalList[38]="";
						}else{
							verticalList[38]=dealData.get(i).getOnsite_Hrs().toString();
						}
						if(dealData.get(i).getOff_Hrs() == null){
							verticalList[39]="";
						}else{
							verticalList[39]=dealData.get(i).getOff_Hrs().toString();
						}
						if(dealData.get(i).getTotal_Hrs() == null){
							verticalList[40]="";
						}else{
							verticalList[40]=dealData.get(i).getTotal_Hrs().toString();
						}
						verticalList[41]=""; // Onsite TCV1
						verticalList[42]=""; // offshore TCV1
						verticalList[43]=""; //Total TCV1
						verticalList[44]=""; //Additoinal Disc
						verticalList[45]=dealData.get(i).getRequestor_Name();
						if(dealData.get(i).getOn_Hrs_per_month2() == null){
							verticalList[46]="";
						}else{
							verticalList[46]=dealData.get(i).getOn_Hrs_per_month2().toString();
						}
						if(dealData.get(i).getOff_Hrs_per_month2() == null){
							verticalList[47]="";
						}else{
							verticalList[47]=dealData.get(i).getOff_Hrs_per_month2().toString();
						}
						if(dealData.get(i).getOnsite_Hrs2() == null){
							verticalList[48]="";
						}else{
							verticalList[48]=dealData.get(i).getOnsite_Hrs2().toString();
						}
						if(dealData.get(i).getOff_Hrs2() == null){
							verticalList[49]="";
						}else{
							verticalList[49]=dealData.get(i).getOff_Hrs2().toString();
						}
						if(dealData.get(i).getTotal_Hrs2() == null){
							verticalList[50]="";
						}else{
							verticalList[50]=dealData.get(i).getTotal_Hrs2().toString();
						}
						verticalList[51]=""; //Onsite TCV 2
						verticalList[52]=""; //offshore TCV2
						verticalList[53]=""; //Total TCV2
						verticalList[54]=""; //Additional Discount2
						verticalList[55]=dealData.get(i).getUpdated_By(); // 
						verticalList[56]=dealData.get(i).getComments();
						
						
						Row header1 = sheet.createRow(rowVal);
						for ( j = 0; j < verticalList.length ; j++) 
						{
								if(j==0|| j==7|| j==11|| j==12|| j==13|| j==14|| j==15|| j==16|| j==17|| j==18|| 
										j==22|| j==23|| j==24|| j==25|| j==26|| j==27|| j==28|| j==29|| j==30|| 
										j==31|| j==32|| j==33|| j==36|| j==37|| j==38|| j==39|| j==40|| j==46|| j==47|| j==48|| j==49|| j==50 )
								{
									header1.setHeightInPoints(16);
									Cell headerCell2;
									headerCell2 = header1.createCell(j);
									if(verticalList[j]!=""){
										if(j==0){
											headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
											headerCell2.setCellValue(Integer.parseInt(verticalList[j]));

											
										}
										else{
											Float value=new Float(verticalList[j]);
											DecimalFormat numberFormat = new DecimalFormat("#.00");
											headerCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
											headerCell2.setCellValue(new Float(numberFormat.format(value)));
										}
									}else{
										headerCell2.setCellValue(verticalList[j]);
									}
									headerCell2.setCellStyle(styles.get("DataRowColumnRightAlignNum"));
								}
							else
							{
								header1.setHeightInPoints(16);
								Cell headerCell2;
								headerCell2 = header1.createCell(j);
								if(verticalList[j] .equals("B2: %, AP1: %, Onsite: %")){
									verticalList[j] = "";
								}
								
								headerCell2.setCellValue(verticalList[j]);
								headerCell2.setCellStyle(styles.get("DataRowColumnLeftAlign"));

							}		
						}
						rowVal++;
					}
					}
					
					for(int columnIndex = 0; columnIndex <= verticalList.length; columnIndex++) 
					{
						sheet.autoSizeColumn(columnIndex);
					}
					}
					wb.write(out);
					wb.close();
					return out.toByteArray();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
					}
				
			
			return null;
		}
			
		

		public static byte[] downloadRateCardRoleExcel(List<MstRpMasterRolesExcel> rcRoleDetails, List<RateCardDetails> rcDetails, List<Country> lstCountryDetails) {
			AppLoger.APPLOGGER.info("Inside getRateCardRoleExcel function");
			AppLoger.APPLOGGER.info("List of rcDetailsRoleUtilization....... " );
			
			HSSFWorkbook wb = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Map<String, CellStyle> styles = createStyles(wb);
			String currencyName = "";
			try {
				for(int i=0;i<lstCountryDetails.size();i++){
					if(lstCountryDetails.get(i).getCountryName().equals(rcDetails.get(0).getBaseCountryName())){
						currencyName =lstCountryDetails.get(i).getCurrencyCode();
					}
				}
				//create excel xls sheet
				Sheet sheet = wb.createSheet("Rate Card Roles");
				sheet.setFitToPage(true);
				createExcelRoleSheet(sheet,wb,styles,rcRoleDetails,rcDetails,currencyName);
				wb.write(out);
				wb.close(); 
				return out.toByteArray();			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return null;
		}
		
		private static void createExcelRoleSheet(Sheet sheet, HSSFWorkbook wb,Map<String, CellStyle> styles,List<MstRpMasterRolesExcel> rcRoleDetails, List<RateCardDetails> rcDetails,String currencyName) 
		{
			 // create style for header cells
	        CellStyle style = wb.createCellStyle();
	        Font font = wb.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	        /*Row titleRow = sheet.createRow(0);
			titleRow.setHeightInPoints(16);
			Cell titleCell = titleRow.createCell(5);
			titleCell.setCellValue("Rate Card Creation - Role Utilization and Rates");
			titleCell.setCellStyle(styles.get("rcHeader"));
			sheet.addMergedRegion(CellRangeAddress.valueOf("$F$1:$L$1"));
			*/
			Integer rowCount = 0;
			rowCount = 1;
			
			rowCount = createCustomerPanelRolesExcel(sheet,styles,rcDetails,rowCount);
			rowCount = rowCount+2;
			
			rowCount = createRCPanelRolesExcel(sheet,rowCount,styles,rcDetails,currencyName);
			rowCount = rowCount+2;
			
			Row headerRow = sheet.createRow(rowCount);
			headerRow.setHeightInPoints(16);
	
			createRoleHeaders(headerRow,sheet,styles,rowCount);

			rowCount = rowCount+2;
			
			createRolesExcelDataRows(rcRoleDetails,sheet,rcDetails,styles,rowCount);
			
					
			for(int columnIndex = 0; columnIndex < 25; columnIndex++) 
			{
				sheet.autoSizeColumn(columnIndex);			     
			}
		}
		
		private static Integer createCustomerPanelRolesExcel(Sheet sheet, Map<String, CellStyle> styles, List<RateCardDetails> rateCardDetails,Integer rowCount) 
		{
			// Customer panel Start
			Row rcCustNamePanel = sheet.createRow(rowCount);
			Cell cellCustName = rcCustNamePanel.createCell(0);
			cellCustName.setCellStyle(styles.get("RowRateCardHeader2"));
			cellCustName.setCellValue(rateCardDetails.get(0).getCustomer().getCustomerName());
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
			
			Cell cellCustName1 = rcCustNamePanel.createCell(1);
			cellCustName1.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName2 = rcCustNamePanel.createCell(2);
			cellCustName2.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName3 = rcCustNamePanel.createCell(3);
			cellCustName3.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName4 = rcCustNamePanel.createCell(4);
			cellCustName4.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellCustName5 = rcCustNamePanel.createCell(5);
			cellCustName5.setCellStyle(styles.get("RowRateCardHeader2"));
			rowCount++;
			
			//Row 2
			Row rcRow2 = sheet.createRow(rowCount);
			Cell cellYR = rcRow2.createCell(0);
			cellYR.setCellStyle(styles.get("DataRowColumnCenterAlign"));		
			cellYR.setCellValue(" "+ sheet.getSheetName());
			sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
			
			Cell cellYr1 = rcRow2.createCell(1);
			cellYr1.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellYr2 = rcRow2.createCell(2);
			cellYr2.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellYr3 = rcRow2.createCell(3);
			cellYr3.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellYr4 = rcRow2.createCell(4);
			cellYr4.setCellStyle(styles.get("RowRateCardHeader2"));
			Cell cellYr5 = rcRow2.createCell(5);
			cellYr5.setCellStyle(styles.get("RowRateCardHeader2"));
			
			return rowCount;
		}
		private static Integer createRCPanelRolesExcel(Sheet sheet, Integer rowCount, Map<String, CellStyle> styles, List<RateCardDetails> rateCardDetails,String currencyName) 
		{
			// RC panel Start
				Row rcPanel1 = sheet.createRow(rowCount);
				Cell cellRCIDTitle = rcPanel1.createCell(0);
				cellRCIDTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCIDTitle.setCellValue("Rate Card Id");
				
				Cell cellRCID = rcPanel1.createCell(1);
				cellRCID.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellRCID.setCellValue(rateCardDetails.get(0).getRcId());
				
				
				Cell cellRCNameTitle = rcPanel1.createCell(2);
				cellRCNameTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCNameTitle.setCellValue("Name");
				
				Cell cellRCName = rcPanel1.createCell(3);
				cellRCName.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCName.setCellValue(rateCardDetails.get(0).getRcName());
				
				Cell cellRCApprStatusTitle = rcPanel1.createCell(4);
				cellRCApprStatusTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCApprStatusTitle.setCellValue("Approver Status");
				
				Cell cellRCApprStatus = rcPanel1.createCell(5);
				cellRCApprStatus.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCApprStatus.setCellValue(rateCardDetails.get(0).getStatusIndicator());
				
				rowCount++;
				
				//Row 2
				Row rcPanel2 = sheet.createRow(rowCount);
				Cell cellRCStartDateTitle = rcPanel2.createCell(0);
				cellRCStartDateTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellRCStartDateTitle.setCellValue("Start Date (dd/mm/yy)");
				
				Cell cellRCStartDate = rcPanel2.createCell(1);
				cellRCStartDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				String strDate[] = rateCardDetails.get(0).getRcStartDate().split(" ");	
				String Date[] =  strDate[0].split("-");
				String strYr = Date[0];
				String strMonth = Date[1];
				String strDay = Date[2];
				cellRCStartDate.setCellValue(strDay+ "/"+strMonth+ "/"+strYr);
				
				
				Cell cellRCEndDateTitle = rcPanel2.createCell(2);
				cellRCEndDateTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCEndDateTitle.setCellValue("Expected End Date (dd/mm/yy)");
				
				Cell cellRCEndDate = rcPanel2.createCell(3);
				cellRCEndDate.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				String strEndDate[] = rateCardDetails.get(0).getRcEndDate().split(" ");
				String EndDate[] =  strEndDate[0].split("-");
				String strEndYr = EndDate[0];
				String strEndMonth = EndDate[1];
				String strEndDay = EndDate[2];		
			    cellRCEndDate.setCellValue(strEndDay+ "/"+strEndMonth+ "/"+strEndYr);
				
				
			    Cell cellRCAppMonthsTitle = rcPanel2.createCell(4);
				cellRCAppMonthsTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellRCAppMonthsTitle.setCellValue("Applicable Months");
				
				Cell cellRCAppMonths = rcPanel2.createCell(5);
				cellRCAppMonths.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellRCAppMonths.setCellValue(rateCardDetails.get(0).getApplicableMonths());
				
				rowCount++;
				
				//Row 3
				Row rcPanel3 = sheet.createRow(rowCount);
				Cell cellCustTitle = rcPanel3.createCell(0);
				cellCustTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellCustTitle.setCellValue("Customer");
				
				Cell cellCust = rcPanel3.createCell(1);
				cellCust.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellCust.setCellValue(rateCardDetails.get(0).getCustomer().getCustomerName());
				
				
				Cell cellTCVTitle = rcPanel3.createCell(2);
				cellTCVTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellTCVTitle.setCellValue("Expected TCV");
				
				Cell cellTCV = rcPanel3.createCell(3);
				cellTCV.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				BigDecimal tcvVal = new BigDecimal(rateCardDetails.get(0).getTvc(), MathContext.DECIMAL64);
				
				cellTCV.setCellValue(tcvVal + " - " + currencyName);
				
				
			    Cell cellDiscTitle = rcPanel3.createCell(4);
			    cellDiscTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellDiscTitle.setCellValue("Discount");
				
				Cell cellDisc = rcPanel3.createCell(5);			
				cellDisc.setCellStyle(styles.get("DataRowColumnLeftAlign"));			
				cellDisc.setCellValue(rateCardDetails.get(0).getVolumeDiscount() + "%");
				
				rowCount++;
				
				//Row 4
				Row rcPanel4 = sheet.createRow(rowCount);
				Cell cellOnUtiTitle = rcPanel4.createCell(0);
				cellOnUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellOnUtiTitle.setCellValue("Onsite Utilization %");
				
				Cell cellOnUti = rcPanel4.createCell(1);
				cellOnUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellOnUti.setCellValue(rateCardDetails.get(0).getExpectedOnsiteResourcePercentage() +"%");
				
				
				Cell cellOffUtiTitle = rcPanel4.createCell(2);
				cellOffUtiTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellOffUtiTitle.setCellValue("Offshore Utilization %");
				
				Cell cellOffUti = rcPanel4.createCell(3);
				cellOffUti.setCellStyle(styles.get("DataRowColumnLeftAlign"));
				cellOffUti.setCellValue(rateCardDetails.get(0).getExpectedOffshoreResourcePercentage()+"%");
							
				
			    Cell cellGMPerTitle = rcPanel4.createCell(4);
			    cellGMPerTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellGMPerTitle.setCellValue("PM% Post VR");
				
				Cell cellGMPer = rcPanel4.createCell(5);
				cellGMPer.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				if(rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount() == null ||  
						rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount() == 0)
				{
					cellGMPer.setCellValue("-");
				}
				else			
				cellGMPer.setCellValue(new BigDecimal(rateCardDetails.get(0).getCalculatedGMPercentagePostDiscount()).setScale(2, RoundingMode.HALF_UP).toString() +"%");

				rowCount++;
				
				//Row 5
				Row rcPanel5 = sheet.createRow(rowCount);
				Cell cellOnhrsTitle = rcPanel5.createCell(0);
				cellOnhrsTitle.setCellStyle(styles.get("RowRateCardHeader"));		
				cellOnhrsTitle.setCellValue("Onsite hours/day");
				
				Cell cellOnhrs = rcPanel5.createCell(1);
				cellOnhrs.setCellStyle(styles.get("DataRowColumnLeftAlignNumber"));		
				Double dblOnHrs = (rateCardDetails.get(0).getOnsiteHoursPerDay() == null)? 0.00 : rateCardDetails.get(0).getOnsiteHoursPerDay();
				cellOnhrs.setCellValue(new BigDecimal(dblOnHrs).setScale(2, RoundingMode.HALF_UP).doubleValue());
				
				
				Cell cellOffHrsTitle = rcPanel5.createCell(2);
				cellOffHrsTitle.setCellStyle(styles.get("RowRateCardHeader"));
				cellOffHrsTitle.setCellValue("Offshore hours/day");
				
				Cell cellOffHrs = rcPanel5.createCell(3);
				cellOffHrs.setCellStyle(styles.get("DataRowColumnLeftAlignNumber"));
				Double dblOffHrs = (rateCardDetails.get(0).getOffshoreHoursPerDay() == null)? 0.00 : rateCardDetails.get(0).getOffshoreHoursPerDay();
				cellOffHrs.setCellValue(new BigDecimal(dblOffHrs).setScale(2, RoundingMode.HALF_UP).doubleValue());
				
				
			    Cell cellBillCurrTitle = rcPanel5.createCell(4);
			    cellBillCurrTitle.setCellStyle(styles.get("RowRateCardHeader"));
			    cellBillCurrTitle.setCellValue("Billing Currency");
				
				Cell cellBillCurr = rcPanel5.createCell(5);
				cellBillCurr.setCellStyle(styles.get("DataRowColumnLeftAlign"));		
				cellBillCurr.setCellValue(currencyName);
				
				rowCount++;
				
				//RC panel end
			return rowCount;

			}
		private static void createRoleHeaders(Row headerRow, Sheet sheet,
				Map<String, CellStyle> styles, Integer rowCount) {

			final String[] OnsiteHeaderList = {"Master Role Code", "Syntel Role", "Proficiency","Syntel Band/Grade","GCM Level","X.O Skills","X.O Skills Element","X.O Knowledge","Customer Role","Comments"};
			
			rowCount++;
			
			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int i = 0; i < OnsiteHeaderList.length; i++) {
				headerCell1 = headerRow1.createCell(i);
				headerCell1.setCellValue(OnsiteHeaderList[i]);
					headerCell1.setCellStyle(styles.get("RowRateCardHeader"));			
			}
		}
		
		private static void createRolesExcelDataRows(List<MstRpMasterRolesExcel> rcRoleDetails, Sheet sheet,
				List<RateCardDetails> rcDetails, Map<String, CellStyle> styles, Integer rowCount) {
			for(int i = 0;i<rcRoleDetails.size();i++){
			final String[] OnsiteDataList = new String[10]  ;
			
			OnsiteDataList[0] =  rcRoleDetails.get(i).getMasterRoleShortDescription();
			OnsiteDataList[1] =  rcRoleDetails.get(i).getSyntelRoleName();
			OnsiteDataList[2] =  rcRoleDetails.get(i).getProficiencyLevelDescription();
			OnsiteDataList[3] =  rcRoleDetails.get(i).getBandGrade().toString();
			OnsiteDataList[4] =  rcRoleDetails.get(i).getGcmCODE().toString();
			OnsiteDataList[5] =  rcRoleDetails.get(i).getStrSkill_Name();
			OnsiteDataList[6] =  rcRoleDetails.get(i).getStrElement_Name();
			OnsiteDataList[7] =  rcRoleDetails.get(i).getStrKnowledgeName();
			OnsiteDataList[8] =  rcRoleDetails.get(i).getClientRole();
			OnsiteDataList[9] =  rcRoleDetails.get(i).getComments();
			

			Row headerRow1 = sheet.createRow(rowCount);
			headerRow1.setHeightInPoints(16);
			Cell headerCell1;
			for (int j = 0; j < OnsiteDataList.length; j++) {
				if(j==4){
					headerCell1 = headerRow1.createCell(j);
					headerCell1.setCellValue(OnsiteDataList[j]);
						headerCell1.setCellStyle(styles.get("DataRowColumnRightAlignNum"));	
				}
				else{
					headerCell1 = headerRow1.createCell(j);
					headerCell1.setCellValue(OnsiteDataList[j]);
						headerCell1.setCellStyle(styles.get("DataRowColumnLeftAlign"));	
				}	
			}
			rowCount++;
			}	
		}
		
}

	


