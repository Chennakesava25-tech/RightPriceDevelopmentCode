package com.rightprice.auth.util;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rightprice.auth.model.CrmDeal;
import com.rightprice.auth.model.Deal;
import com.rightprice.auth.model.DealCrmStages2;
import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.model.FPDealWhatIfEffort;
import com.rightprice.auth.model.FpDeal;
import com.rightprice.auth.model.MyDashboardRC;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.VerticalWiseBUPOCDetails;
import java.util.Arrays;
import com.rightprice.auth.model.VerticalWiseBUPOCDetails;



    public class RightPriceFixedPriceEmailTemplate {
	@SuppressWarnings("unused")
	public void triggerMail(int templateID,List<FpDeal> FixedPriceDetails,Integer rpVersionId,String dealVersion,String createdby,String updater,String deliveryData[],
			String  Leaddetails[],List<DealCrmStages2> crmDealdata,String userComment,Map<Integer, List<Long>>dealInformation,String status,String createbyEmail,String updatedStaus,List<VerticalWiseBUPOCDetails> verticalPOCMailList, Map<Integer, List<String>> proxyEmail ) {
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
		String tempId;
		String desc;
		String[] to = null;
		String[] cc = null;
		String subject;
		String mailContent;
		String CEOName = Leaddetails[0];
		String CEOEmail=Leaddetails[1];
		String CDOName=Leaddetails[2];
		String CDOEmail=Leaddetails[3];
		String deliveryHeadEmail=deliveryData[1];
		String BuHeadName=deliveryData[2];
		String deliveryHeadName = deliveryData[0];
		String BuHeadEmail=deliveryData[3];
		/*String RiskManagersPerson=qualtiyMaildetails[0];
		String RiskManagersPersonemail=qualtiyMaildetails[1];*/
		String level1 = Leaddetails[4];
		String level1mail=Leaddetails[5];
		String level2=Leaddetails[6];
		String level2Email=Leaddetails[7];
		String JVCEO=Leaddetails[8];
		String JVCEOEmail=Leaddetails[9];
		String mailContents="";
		String mailContents1="";
		long revenueEstimated=0;
		Double consolidateOmPercent=0.0;
		Double conGmPercentage=0.0;
		long ExpectedOffshorePercentage=0;
		long ExpectedonsitePercentage=0;
		Double gmAfterVolumeDiscount=0.0;
		long directCost=0;
		Double projectSpecificCost=0.0;
		Double volumeDiscountPercent=0.0;
		String Status=status;
		String customerName="";
		DecimalFormat df2 = new DecimalFormat("#0.00");
		String conGmPercentages="";
		String consolidateOmPercents="";
		String gmAfterVolumeDiscounts="";
		Double projectMargin=0.0;
		Double projectMarginPercentage=0.0;
		Double pminclrisk=0.0;
		Double pminclRiskVD=0.0;
		long projectMargins=0;
		String projectMarginPercentages="";
		String pminclrisks="";
		String pminclRiskVDs="";
		String Desc=null,SpocName=null,currencyName=null,DealName=null,projectName=null,rbuProfitCenter=null;
		//String conGmPercentage=null,consolidateOmPercent=null,revenueEstimated=null,directCost=null,projectSpecificCost=null;
		//String ExpectedOffshorePercentage=null,ExpectedonsitePercentage=null,gmAfterVolumeDiscount=null,volumeDiscountPercent=null;;
		 int sizeofTo=verticalPOCMailList.size();
		 //int sizeof=qualtiyMaildetails.size();
		 //int size=adminMail.size();
		 Map<Integer, List<String>> proxyApproverEmail = new HashMap<Integer, List<String>>();
		 proxyApproverEmail = proxyEmail;
			List<String> DelievryProxy = proxyApproverEmail.get(1);
			List<String> BUhProxy = proxyApproverEmail.get(2);
			//List<String> RiskManagersProxy = proxyApproverEmail.get(3);
			List<String> CDOProxy = proxyApproverEmail.get(4);
			List<String> CEOProxy = proxyApproverEmail.get(5);
			List<String> level1Proxy = proxyApproverEmail.get(6);
			List<String> level2Proxy = proxyApproverEmail.get(7);
		

		int currencyId=0,dealTypeId=0,projectTypeId=0;
		 DecimalFormat difs = new DecimalFormat("#0.00");
		 difs.setRoundingMode(RoundingMode.DOWN);
		 if(userComment!=null){
			 userComment=userComment;
		 }
		 else
		 {
			 userComment="-"; 
		 }
	     for(FpDeal rateFPData : FixedPriceDetails){
	    	 
	    	String customerNames= rateFPData.getCustomerVerticalMapping().getCustomer().getCustomerName();
	    	if(customerNames!=null){
	    		customerName=customerNames;
	    	}
	    	else {
	    		customerName="-";
	    	}
			Double revenenue=rateFPData.getEstimatedRevenue();
			 if(revenenue != null) {
				 revenueEstimated=(Math.round(revenenue));
			 } else {
				 revenueEstimated = 0;
			 }
			 
			 String cost=rateFPData.getDirectCost();
			 if(cost != null) {
				 double value = Double.parseDouble(cost);
				 directCost=(Math.round(value));
			 } else {
				 directCost = 0;
			 }
			 String prjctCost=rateFPData.getProjectSpecificCost();
			 if(prjctCost != null) {
				 double value = Double.parseDouble(prjctCost);
				 projectSpecificCost=value;
			 } else {
				 projectSpecificCost = 0.0;
			 }
			 String volumePrcnt=rateFPData.getVolumeDiscountPercent();
			 if(volumePrcnt != null) {
				 
				 double value = Double.parseDouble(volumePrcnt);
				 volumeDiscountPercent=value;
			 } else {
				 volumeDiscountPercent = 0.0;
			 }
			 
			 Double gmVolumePrcnt=rateFPData.getGmAfterVolumeDiscount();
			 if(gmVolumePrcnt != null) {
				 gmAfterVolumeDiscount=(gmVolumePrcnt*100);
						 
			 } else {
				 gmAfterVolumeDiscount = 0.0;
			 }
			 if(gmAfterVolumeDiscount!= null) {
				 gmAfterVolumeDiscounts=(df2.format(gmAfterVolumeDiscount));
	 		 } else {
	 			gmAfterVolumeDiscounts = "-";
	 		 }
			 
			 Double onsitePercentage=rateFPData.getExpectedOnsitePercentage();
			 if(onsitePercentage != null) {
				 
				 ExpectedonsitePercentage=(Math.round(onsitePercentage));
			 } else {
				 ExpectedonsitePercentage = 0;
			 }
			 
             ExpectedOffshorePercentage= Math.abs(100-ExpectedonsitePercentage);
			 
			 Double gmPercent=rateFPData.getGmPercentage();
			 if(gmPercent != null) {
				 conGmPercentage=(gmPercent*100);
			 } else {
				 conGmPercentage = 0.0;
			 }
			 if(conGmPercentage!= null) {
				 conGmPercentages=(df2.format(conGmPercentage));
	 		 } else {
	 			conGmPercentages = "-";
	 		 }
			 
			 
			 Double consolidateOm =rateFPData.getGmAfterProjectSpecificCost();
			 if(consolidateOm != null) {
				 
				 consolidateOmPercent=(consolidateOm*100);
			 } else {
				 consolidateOmPercent = 0.0;
			 }
			 if(consolidateOmPercent!= null) {
				 consolidateOmPercents=(df2.format(consolidateOmPercent));
	 		 } else {
	 			consolidateOmPercents = "-";
	 		 }
			 
			 Double projectmg=rateFPData.getProjectMargin();
			 if(projectmg != null) {
				
				 projectMargins =(Math.round(projectmg));
			 } else {
				 projectMargins = 0;
			 }
			 
			 Double pMarginpercentage=rateFPData.getProjectMarginPercentage();
			 if(pMarginpercentage != null) {
				 projectMarginPercentage=(pMarginpercentage*100);
			 } else {
				 projectMarginPercentage = 0.0;
			 }
			 if(projectMarginPercentage!= null) {
				 projectMarginPercentages=(df2.format(projectMarginPercentage));
	 		 } else {
	 			projectMarginPercentages = "-";
	 		 }
			 
			 
			 Double pmincrisk=rateFPData.getPminclrisk();
			 if(pmincrisk != null) {
				 pminclrisk=(pmincrisk*100);
			 } else {
				 pminclrisk = 0.0;
			 }
			 if(pminclrisk!= null) {
				 pminclrisks=(df2.format(pminclrisk));
	 		 } else {
	 			pminclrisks = "-";
	 		 }
			 
			 
			 Double pminclRiskVDsperc=rateFPData.getPminclRiskVD();
			 if(pminclRiskVDsperc != null) {
				 pminclRiskVD=(pminclRiskVDsperc*100);
			 } else {
				 pminclRiskVD = 0.0;
			 }
			 if(pminclRiskVD!= null) {
				 pminclRiskVDs=(df2.format(pminclRiskVD));
	 		 } else {
	 			pminclRiskVDs = "-";
	 		 }
			 
			 dealTypeId=rateFPData.getDealTypeId();
			 switch (dealTypeId) {
		  		case 1 : 
		  			DealName = "FP";
		  			break;
		  		case 2 : 
		  			DealName = "T&M";
		  			break;
		  		default : 
		  			break;
			 }
			 
			 projectTypeId=rateFPData.getFpProjectTypeId();
			 switch (projectTypeId) {
		  		case 0 : 
		  			projectName = "NA";
		  			break;
		  		case 1 : 
		  			projectName = "Devel- FP";
		  			break;
		  		case 2 : 
		  			projectName = "Devel - Manage Cap";
		  			break;
		  		case 3 : 
		  			projectName = "Maint - FP";
		  			break;
		  		case 4 : 
		  			projectName = "Maint - Manage Cap";
		  			break;
		  		default : 
		  			break;
			 }
		}
		
		for(DealCrmStages2 crmDealData : crmDealdata){
			
			 Desc=crmDealData.getDealDescription();
			 SpocName=crmDealData.getSalesSpoc();
			 currencyId=crmDealData.getCurrencyId();
			 rbuProfitCenter=crmDealData.getRbuProfitCenter();
			 switch (currencyId) {
		  		case 1 : 
		  			currencyName = "USD";
		  			break;
		  		case 2 : 
		  			currencyName = "INR";
		  			break;
		  		case 3 : 
		  			currencyName = "CAD";
		  			break;
		  		case 4 : 
		  			currencyName = "EUR";
		  			break;
		  		case 5 : 
		  			currencyName = "GBP";
		  			break;
		  		case 6 : 
		  			currencyName = "AUD";
		  			break;
				case 7 : 
					currencyName = "Common";
					break;
				default : 
		  			break;
				}
		}
		
	
		
				Map<Integer, List<Long>> getAllData = new HashMap<Integer, List<Long>>();
				getAllData = dealInformation;
				List<Long> offshoreTotal = getAllData.get(1);
				List<Long> localvalue = getAllData.get(2);
				List<Long> deputedvalue = getAllData.get(3);
				List<Long> offShorevalue = getAllData.get(4);	
		
			 for(FpDeal rateData : FixedPriceDetails) {
					String startDate = dateFormat(rateData.getProjectStartDate());
					String endDate=dateFormat(rateData.getProjectEndDate());
			 mailContents="<style>table, th, td {border: 1px solid black;border-collapse: collapse;} </style>"
						+ "<table> <tbody>"
						+ "<tr><td><b>Consolidated Summary</b></td><td style=width:40%;color:black;text-align:center><strong>"+rateData.getDealVersion()+"("+rateData.getRpDealVersionId()+")</strong></td></tr>"
						+"<tr><td><b>Revenue ["+currencyName+" ] </b></td><td>"+revenueEstimated+"  "+"</td></tr>"
						+"<tr><td><b>Direct Cost ["+currencyName+" ] </b></td><td >"+directCost+"  "+ "</td></tr>"
						+"<tr><td><b>Project Specific SGA ["+currencyName+" ] </b></td><td> "+projectSpecificCost  +"  "+ "</td></tr>"
						+"<tr><td><b>Project Margin </b></td><td style=width:40%;color:black> "+projectMargins+"  "+ "</td></tr>"
						+"<tr><td><b>Project Margin% </b></td><td> "+projectMarginPercentages+"%  "+"  "+ "</td></tr>"
						+"<tr><td><b>Project Margin % incl. Risk </b></td><td> "+pminclrisks  +"%  "+"  "+ "</td></tr>"
						+"<tr><td><b>Volume Discount% </b></td><td>"+volumeDiscountPercent  + "%  "+"  "+"</td></tr>"
						+"<tr><td><b>PM% incl. Risk and VD </b></td><td>"+pminclRiskVDs +"%  "+"  "+ "</td></tr>"
                        +"<tr><td><b>Efforts</b></td><td>"+offshoreTotal.get(0)  +"</td></tr>"
						+"<tr><td><b>Domestic</b></td><td>"+localvalue.get(0)  +"</td></tr>"
						+"<tr><td><b>Deputed</b></td><td>"+deputedvalue.get(0)  +" </td></tr>"
						+"<tr><td><b>Offshore</b></td><td>"+offShorevalue.get(0)+"   "+ "</td></tr>"
						+"<tr><td><b>Onsite : Offshore Mix</b></td><td>"+ExpectedonsitePercentage+":" +ExpectedOffshorePercentage+"    "+"</td></tr>"
						+"</tbody></table>";
			 mailContents1= "<table> <tbody>"
						+ "<tr><td><b>Deal Type</b></td><td> "+DealName+ " </td></tr>"
						+"<tr><td><b>Project Type</b></td><td> "+projectName+ " </td></tr>"
						+"<tr><td><b>Deal Start date</b></td><td>"+startDate+ "</td></tr>"
						+"<tr><td><b>Deal End date</b></td><td>"+endDate+"</td></tr>"
						+"</tbody></table>"
						+"<br>";
			 }
			

		EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
		//String approvalLink ="https://10.128.10.231/InsurancePortal/login";
		switch (templateID) {
		
		case 10: {
			AppLoger.APPLOGGER.info("Executing case 29");
			AppLoger.APPLOGGER.info("WhenD");
			String username="";
		  	String userEmail="";
		  	String desg="";
		  	//to = new String[1];
			//cc = new String[1];
			String username1="";
		  	String userEmail1="";
		  	String desg1="";
		  	int itkpo=0;
		  	
		  	//int sizeof=qualtiyMaildetails.size();
		  	
		  	for(FpDeal rateData : FixedPriceDetails) {
		  		itkpo = rateData.getProjectIndustry();
		  	}
			switch (Status) {
	  		case "Delivery Head" : 
	  			username=deliveryHeadName;
	  			userEmail=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username="GFT";
	  			//userEmail="Syndev@TATOSMAIL.COM";
            	userEmail="gft_costing@eviden.com";
	  			desg="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username=CDOName;
	  			userEmail=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username=BuHeadName;
		  			userEmail=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username=JVCEO;
	  				userEmail=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "RiskManagers" : 
	  			username="<"+rbuProfitCenter+">"+"  "+ "RiskManagers" ;
	  			//userEmail="Syndev@TATOSMAIL.COM";
            	userEmail="dl-atos-regionalmanagers@eviden.com";
			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username=CEOName;
	  			userEmail=CEOEmail;
	  			desg="CEO";
	  			break;
	  		case "Level 1" : 
	  			username=level1;
	  			userEmail=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username=level2;
	  			userEmail=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
     			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
	  				userEmail1=JVCEOEmail;
		  			desg1="BU Head";
	  			break;
	  			}
	  		
	  		case "RiskManagers" : 
	  			
	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail1="dl-git-rp@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+"RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1="CEO";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  		default : 
	  			break;
			}
			desc = "Case 10: Deals Submitted";
			if(updatedStaus.equals("Delivery Head")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int ccSize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("CDO")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int ccSize=cc.length;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("BUH")||updatedStaus.equals("BU_Head")||updatedStaus.equals("BU Head")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int ccSize=cc.length;
				if(BUhProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=BUhProxy.get(j);
						j++;
					}
					
				}
			}
			if(updatedStaus.equals("CEO")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int ccSize=cc.length;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 1")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int j=0;
				if(level1Proxy.get(0)!=null){
					int ccSize=cc.length;
					cc = Arrays.copyOf(cc, cc.length + level1Proxy.size());
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 2")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createbyEmail;
				int ccSize=cc.length;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("GFT")){
				to = new String[1];
				cc = new String[1];
				to[0] = userEmail1;
				//to[1] = "vishal.arora@eviden.com";
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				cc[0] = createbyEmail;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
			}
				
				if(updatedStaus.equals("RiskManagers")){
					to = new String[2];
					cc = new String[2];
					to[0] = userEmail1;
					to[1] = "chennakesava-reddy.v@eviden.com";
					//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
					cc[0] = createbyEmail;
					cc = Arrays.copyOf(cc, cc.length + sizeofTo);
					int j=0;
					for(int i=1;i<cc.length;i++){
						cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
						j++;
				      }
					
			}
			
			for(FpDeal rateData : FixedPriceDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				subject = customerName+" â€“ "+Desc+" is awaiting your approval- Deal ID: "+rateData.getCrmDealId() ;

				mailContent = "<p>Dear "+username1+",</p>"
						+ "<p>&emsp;&emsp;"+createdby+" has submitted costing for "+Desc+" - Deal ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>";
						mailContent+=mailContents;	
						mailContent+= "<p>&emsp;&emsp;<u><strong> Deal Details: -</strong> </u></p>";
						
						mailContent+=mailContents1
						+ "<p>&emsp;&emsp;"+userComment+"</p>"
						+ "<br><br>Regards,<br> "+createdby+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
				
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
			}
			break;
			}
		case 29: {
			AppLoger.APPLOGGER.info("Executing case 29");
			AppLoger.APPLOGGER.info("When Deal rejected");
			String username="";
		  	String userEmail="";
		  	String desg="";
		  	//to = new String[1];
			//cc = new String[1];
			String username1="";
		  	String userEmail1="";
		  	String desg1="";
            int itkpo=0;
		  	
		  	for(FpDeal rateData : FixedPriceDetails) {
		  		itkpo = rateData.getProjectIndustry();
		  	}
			switch (Status) {
	  		case "Delivery Head" : 
	  			username=deliveryHeadName;
	  			userEmail=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username="GFT Costing Team";
	  			//userEmail="Syndev@TATOSMAIL.COM";
	  			userEmail="gft_costing@eviden.com";
	  			desg="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username=CDOName;
	  			userEmail=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username=BuHeadName;
		  			userEmail=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username=JVCEO;
	  				userEmail=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "RiskManagers" : 
	  			
	  			username="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail="dl-atos-regionalmanagers@eviden.com";
  			desg="<"+rbuProfitCenter+">"+"  "+"RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username=CEOName;
	  			userEmail=CEOEmail;
	  			desg="CEO";
	  			break;
	  		case "Level 1" : 
	  			username=level1;
	  			userEmail=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username=level2;
	  			userEmail=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
/*			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			username1=BuHeadName;
	  			userEmail1=BuHeadEmail;
	  			desg1="BU Head";
	  			break;
	  		case "RiskManagers" : 
	  			username1=RiskManagersPerson;
	  			userEmail1=RiskManagersPersonemail;
	  			desg1="RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1="CEO";
	  			
	  		default : 
	  			break;
			}*/
			desc = "Case 29: Policy  Rejected";
			if(Status.equals("GFT")){
				to = new String[1];
				cc = new String[1];
				to[0] = createbyEmail;
				//cc[0] = "Syndev@TATOSMAIL.COM";
				//cc[0] = "vishal.arora@eviden.com";
				cc[0] = userEmail;
				

			}
			
		
			else if(status.equals("RiskManagers")){
				to = new String[1];
				cc = new String[2];
				to[0] = createbyEmail;
				cc[0] = "chennakesava-reddy.v@eviden.com";
				//cc[0] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				cc[1] = userEmail;	
			}
			
			
			else

			{
				to = new String[1];
				cc = new String[1];
				to[0] = createbyEmail;
				cc[0] = userEmail;
			}
			for(FpDeal rateData : FixedPriceDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				subject = desg+" has recycled the Deal prepared for "+customerName+" - Deal ID: "+rateData.getCrmDealId()+" .";

				mailContent = "<p>Dear "+createdby+",</p><br>"
						+ "<p>&emsp;&emsp;"+username+" has recycled the costing for"+"-"+Desc+" - Deal ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+".</p>"
						+ "<p>&emsp;&emsp;<u><strong> This plan had the following estimates: -</strong> </u></p>"
						+"<br>";
						mailContent+=mailContents;	
						mailContent+= "<p>&emsp;&emsp;<u><strong> Deal Details: -</strong> </u></p>";
						mailContent+=mailContents1
						+ "<p>&emsp;&emsp;"+userComment+"</p>"
						+ "<br><br>Regards,<br>"+username+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
				
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		
		
		case 20: {
			AppLoger.APPLOGGER.info("Executing case 20");
			AppLoger.APPLOGGER.info("Mail Generated when Finally Approved");
			tempId = "Temp20";
			
			String username="";
		  	String userEmail="";
		  	String desg="";
		  	//to = new String[1];
			//cc = new String[1];
			String username1="";
		  	String userEmail1="";
		  	String desg1="";
            int itkpo=0;
		  	
		  	for(FpDeal rateData : FixedPriceDetails) {
		  		itkpo = rateData.getProjectIndustry();
		  	}
			switch (Status) {
	  		case "Delivery Head" : 
	  			username=deliveryHeadName;
	  			userEmail=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username="GFT Costing Team";
	  			//userEmail="Syndev@TATOSMAIL.COM";
                 userEmail="gft_costing@eviden.com";
	  			desg="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username=CDOName;
	  			userEmail=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username=BuHeadName;
		  			userEmail=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username=JVCEO;
		  			userEmail=JVCEOEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  		case "RiskManagers" : 
	  			
	  			username="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+"RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username=CEOName;
	  			userEmail=CEOEmail;
	  			desg="CEO";
	  			break;
	  		case "Level 1" : 
	  			username=level1;
	  			userEmail=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username=level2;
	  			userEmail=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
		  			userEmail1=JVCEOEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  		case "RiskManagers" : 
	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";
  			desg1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1="CEO";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  		default : 
	  			break;
			}
			desc = "Case 20: Policy  Approved";
			//to[0] = createbyEmail;
			//cc[0] = userEmail;
			
			if(Status.equals("GFT")){
				to = new String[1];
				cc = new String[1];
				to[0] = createbyEmail;
				cc[0] = userEmail;
				//cc[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//cc[1] ="vishal.arora@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
			}
			else if(Status.equals("RiskManagers")) {
				to = new String[1];
				cc = new String[2];
				to[0] = createbyEmail;
				cc[0] = userEmail;
				cc[1] = "chennakesava-reddy.v@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=2;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
					
			}
			else
			{
				to = new String[1];
				cc = new String[1];
				to[0] = createbyEmail;
				cc[0] = userEmail;
			}
			for(FpDeal rateData : FixedPriceDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				subject =  desg+" has reviewed the Deal Prepared for "+ customerName+" Deal Id: "+rateData.getCrmDealId();

				mailContent = "<p>Dear "+createdby+",</p>"
						+ "<p>&emsp;&emsp;"+username+" has approved costing for "+Desc+" - Deal ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
						+ "<p>&emsp;&emsp;<u><strong> The approval process is now complete -</strong> </u></p>"
						+"<br>";
						mailContent+=mailContents;	
						mailContent+= "<p>&emsp;&emsp;<u><strong> Deal Details: -</strong> </u></p>";
						mailContent+=mailContents1
						+ "<p>&emsp;&emsp;"+userComment+"</p>"
						+ "<br>Regards,<br> "+username+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
				
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		case 21: {
			AppLoger.APPLOGGER.info("Executing case 20");
			AppLoger.APPLOGGER.info("Mail Generated when send for approval");
			tempId = "Temp20";
			
			String username="";
		  	String userEmail="";
		  	String desg="";
		  	//to = new String[1];
			//cc = new String[2];
			String username1="";
		  	String userEmail1="";
		  	String desg1="";
            int itkpo=0;
		  	
		  	for(FpDeal rateData : FixedPriceDetails) {
		  		itkpo = rateData.getProjectIndustry();
		  	}
			switch (Status) {
	  		case "Delivery Head" : 
	  			username=deliveryHeadName;
	  			userEmail=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username="GFT Costing Team";
	  			//userEmail="Syndev@TATOSMAIL.COM";
     			userEmail="gft_costing@eviden.com";
	  			desg="GFT TEAM";
	  			break;
	  		case "CDO" : 
	  			username=CDOName;
	  			userEmail=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username=BuHeadName;
		  			userEmail=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username=JVCEO;
		  			userEmail=JVCEOEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  		case "RiskManagers" : 
	  			username="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username=CEOName;
	  			userEmail=CEOEmail;
	  			desg="CEO";
	  			break;
	  		case "Level 1" : 
	  			username=level1;
	  			userEmail=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username=level2;
	  			userEmail=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
     			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing TEAM";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
		  			userEmail1=JVCEOEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  		case "RiskManagers" : 
	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";
	  			//userEmail="Syndev@TATOSMAIL.COM";//QualityPersonemail;
	  			
	  			desg="<"+rbuProfitCenter+">"+"  "+  "RiskManagers Team";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1="CEO";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  			
	  		default : 
	  			break;
			}
			desc = "Case 21: Policy  Approved";
			//to[0] = userEmail1;
			//cc[0] = userEmail;
			//cc[1] = createbyEmail;
			
			if(Status.equals("GFT")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				//cc[2] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//cc[2] = "vishal.arora@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=2;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
			}
			if(Status.equals("Delivery Head")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					if(DelievryProxy.get(0)!=null){
						for(int i=2;i<cc.length;i++)
						{
							cc[i]=DelievryProxy.get(j);
							j++;
						}
						
					}
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
				
				
			}
			
			if(Status.equals("CDO")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
				
			}
			if(Status.equals("BUH")||Status.equals("BU_Head")||Status.equals("BU Head")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
					if(BUhProxy.get(0)!=null){
						cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
						int j=0;
						for(int i=2;i<cc.length;i++)
						{
							cc[i]=BUhProxy.get(j);
							j++;
						}
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
			}
			
			if(Status.equals("CEO")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
				
				
			}
			
			if(Status.equals("Level 1")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				
				if(level1Proxy.get(0)!=null){
					int sizeProxy=level1Proxy.size();
					cc = Arrays.copyOf(cc, cc.length + sizeProxy);
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
				
			}
			if(Status.equals("Level 2")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}
				
			}
			
			if(Status.equals("RiskManagers")){
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				//cc[2] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				cc[2] = "chennakesava-reddy.v@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=3;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				/*cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createbyEmail;
				
				if(RiskManagersProxy.get(0)!=null){
					int sizeProxy=RiskManagersProxy.size();
					cc = Arrays.copyOf(cc, cc.length + sizeProxy);
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=RiskManagersProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createbyEmail;
					}*/
				
			}
			
			if(updatedStaus.equals("Delivery Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("CDO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("BUH")||updatedStaus.equals("BU_Head")||updatedStaus.equals("BU Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(BUhProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=BUhProxy.get(j);
						j++;
					}
					
				}
			}
			if(updatedStaus.equals("CEO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 1")){
				to = new String[1];
				to[0] = userEmail1;
				int j=0;
				if(level1Proxy.get(0)!=null){
					int ccSize=cc.length;
					cc = Arrays.copyOf(cc, cc.length + level1Proxy.size());
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 2")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				
			}
			
			if(updatedStaus.equals("GFT")){
				to = new String[1];
				to[0] = userEmail1;
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//to[1] = "vishal.arora@eviden.com";
				int ccsize=cc.length;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=ccsize;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				
			}
			if(updatedStaus.equals("RiskManagers")){
				to = new String[2];
				to[0] = userEmail1;
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				to[1] = "chennakesava-reddy.v@eviden.com";
				int ccsize=cc.length;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=ccsize;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				/*to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(RiskManagersProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + RiskManagersProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=RiskManagersProxy.get(j);
						j++;
					}
					
				}*/
				
			}
			for(FpDeal rateData : FixedPriceDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());

				
				subject = desg+" has reviewed the Deal Prepared for "+ customerName+" Deal Id: "+rateData.getCrmDealId();

				mailContent = "<p>Dear "+username1+",</p>"
						+ "<p>&emsp;&emsp;"+username+" has approved costing for "+Desc+" - Deal ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
						+ "<p>&emsp;&emsp;<u><strong> Request Your Approval: -</strong> </u></p>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>";
						mailContent+=mailContents;	
						mailContent+= "<p>&emsp;&emsp;<u><strong> Deal Details: -</strong> </u></p>"
						+"<br>";
						mailContent+=mailContents1
						+ "<p>&emsp;&emsp;"+userComment+"</p>"
						+ "<br>Regards,<br> "+username+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
				
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		
		
		}
		try {
			
 			AppLoger.APPLOGGER.info(" triggerMail started");
			MailTrigger.sendMail(emailDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppLoger.APPLOGGER.info(" triggerMail : EXIT :");
	
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public void approvaltriggerMail(int templateID,String Leaddetails[],List<DealCrmStages2> crmDealdata,List<FpDeal> 
	FPdealdetails,String deliveryData[],List personDealEffortsDealdata,Map<Integer, List<Long>>dealInformation,String status,String userEmail,String approvedBy,String createdbyemail,String updatedStaus,String createdby,List<VerticalWiseBUPOCDetails> verticalPOCMailList, Map<Integer, List<String>> proxyEmail){
		String tempId;
		String desc;
		String[] to = null;
		String[] cc = null;
		String subject = null;
		String mailContent;
		String CEOName = Leaddetails[0];
		String CEOEmail=Leaddetails[1];
		String CDOName=Leaddetails[2];
		String CDOEmail=Leaddetails[3];
		String level1 = Leaddetails[4];
		String level1mail=Leaddetails[5];
		String level2=Leaddetails[6];
		String level2Email=Leaddetails[7];
		String deliveryHeadEmail=deliveryData[1];
		String BuHeadName=deliveryData[2];
		String deliveryHeadName = deliveryData[0];
		String BuHeadEmail=deliveryData[3];
		/*String RiskManagersName=deliveryData[4];
		String RiskManagersEmail=deliveryData[5];*/
		String JVCEO=Leaddetails[8];
		String JVCEOEmail=Leaddetails[9];
		 String s="";
		 String currentuserEmail=userEmail;
		int currencyId=0,dealTypeId=0,projectTypeId=0;
		List<Long> revenue = new ArrayList<Long>();
		List<Long> directCosts = new ArrayList<Long>();
		List<String> conGmPercentages = new ArrayList<String>();
		List<Double> projectSpecificCosts = new ArrayList<Double>();
		List<String> consolidateOmPercents  = new ArrayList<String>();
		List<Double> volumeDiscountPercents = new ArrayList<Double>();
		List<String> gmAfterVolumeDiscounts = new ArrayList<String>();
		List<Long> ExpectedonsitePercentages = new ArrayList<Long>();
		List<Long> ExpectedOffshorePercentages = new ArrayList<Long>();
		
		List<String> projectNames = new ArrayList<String>();
		List<String> DealNames = new ArrayList<String>();
		List<Integer> dealId= new ArrayList<Integer>();
		List<String> dealVesrion=new ArrayList<String>();
		List<Double> efforts  = new ArrayList<Double>();
		
		List<Long> projectMargins = new ArrayList<Long>();
		List<String> projectMarginPercentages = new ArrayList<String>();
		List<String> pminclrisks = new ArrayList<String>();
		List<String> pminclRiskVDs = new ArrayList<String>();
		
		efforts=personDealEffortsDealdata;
		
		long revenueEstimated=0;
		Double consolidateOmPercent=0.0;
		Double conGmPercentage=0.0;
		long ExpectedOffshorePercentage=0;
		long ExpectedonsitePercentage=0;
		Double gmAfterVolumeDiscount=0.0;
		long directCost=0;
		Double projectSpecificCost=0.0;
		Double volumeDiscountPercent=0.0;
		
		String conGmPer="";
		String consolidateOmPer="";
		String gmAfterVolumeper="";
		
		Double projectMargin=0.0;
		Double projectMarginPercentage=0.0;
		Double pminclrisk=0.0;
		Double pminclRiskVD=0.0;
		
		long projectMarginsper=0;
		String projectMarginPercentagesper="";
		String pminclrisksper="";
		String pminclRiskVDsper="";
		
		
		String Desc=null,SpocName=null,currencyName=null,startDate=null,endDate,rpdealId;
		String DealName=null,projectName=null,rbuProfitCenter=null;
		
		 int sizeofTo=verticalPOCMailList.size();
		// int sizeof=RiskManagersAdminMails.size();
		 Map<Integer, List<String>> proxyApproverEmail = new HashMap<Integer, List<String>>();
		 proxyApproverEmail = proxyEmail;
			List<String> DelievryProxy = proxyApproverEmail.get(1);
			List<String> BUhProxy = proxyApproverEmail.get(2);

			//List<String> qualityProxy = proxyApproverEmail.get(3);


			//List<String> RiskManagersProxy = proxyApproverEmail.get(3);

			List<String> CDOProxy = proxyApproverEmail.get(4);
			List<String> CEOProxy = proxyApproverEmail.get(5);
			List<String> level1Proxy = proxyApproverEmail.get(6);
			List<String> level2Proxy = proxyApproverEmail.get(7);
		 

		
		DecimalFormat difs = new DecimalFormat("#0.00");
		DecimalFormat df2 = new DecimalFormat("#0.00");
		difs.setRoundingMode(RoundingMode.DOWN);
		for(DealCrmStages2 crmDealData : crmDealdata){
			
			 Desc=crmDealData.getDealDescription();
			 SpocName=crmDealData.getSalesSpoc();
			 currencyId=crmDealData.getCurrencyId();
			 rbuProfitCenter=crmDealData.getRbuProfitCenter();
			 
			 switch (currencyId) {
		  		case 1 : 
		  			currencyName = "USD";
		  			break;
		  		case 2 : 
		  			currencyName = "INR";
		  			break;
		  		case 3 : 
		  			currencyName = "CAD";
		  			break;
		  		case 4 : 
		  			currencyName = "EUR";
		  			break;
		  		case 5 : 
		  			currencyName = "GBP";
		  			break;
		  		case 6 : 
		  			currencyName = "AUD";
		  			break;
				case 7 : 
					currencyName = "Common";
					break;
				default : 
		  			break;
				}
		    }
			 
			    for(int i=0;i<FPdealdetails.size();i++){
			    	s=FPdealdetails.get(i).getRpDealVersionId()+", "+s+"";
			    }
			    for(int i=0;i<FPdealdetails.size();i++){
			    	 Double revenenue=FPdealdetails.get(i).getEstimatedRevenue();
					 
					 if(revenenue != null) {
						 revenueEstimated=(Math.round(revenenue));
					 } else {
						 revenueEstimated = 0;
					 }
					 revenue.add(revenueEstimated);
				
					 String cost=FPdealdetails.get(i).getDirectCost();
					 if(cost != null) {
						 double value = Double.parseDouble(cost);
						 directCost=(Math.round(value));
					 } else {
						 directCost = 0;
					 }
					 directCosts.add(directCost);
					 
					 
					 
					 Double gmPercent=FPdealdetails.get(i).getGmPercentage();
					 if(gmPercent != null) {
						 conGmPercentage=gmPercent*100;
					 } else {
						 conGmPercentage = 0.0;
					 }
					 
					 if(conGmPercentage!= null) {
						 conGmPer=(df2.format(conGmPercentage));
			 		 } else {
			 			conGmPer = "-";
			 		 }
					 
					 conGmPercentages.add(conGmPer);
					 
					 String prjctCost=FPdealdetails.get(i).getProjectSpecificCost();
					 if(prjctCost != null) {
						 double value = Double.parseDouble(prjctCost);
						 projectSpecificCost=value;
						 
					 } else {
						 projectSpecificCost = 0.0;
					 } 
					 projectSpecificCosts.add(projectSpecificCost); 
				
					 Double consolidateOm =FPdealdetails.get(i).getGmAfterProjectSpecificCost();
					 if(consolidateOm != null) {
						 
						 consolidateOmPercent=consolidateOm*100;
					 } else {
						 consolidateOmPercent = 0.0;
					 }
					 if(consolidateOmPercent!= null) {
						 consolidateOmPer=(df2.format(consolidateOmPercent));
			 		 } else {
			 			consolidateOmPer = "-";
			 		 }
					 consolidateOmPercents.add(consolidateOmPer);
					 
				 
					 String volumePrcnt=FPdealdetails.get(i).getVolumeDiscountPercent();
					 if(volumePrcnt != null) {
						 
						 double value = Double.parseDouble(volumePrcnt);
						 volumeDiscountPercent=value;
					 } else {
						 volumeDiscountPercent = 0.0;
					 }
					 volumeDiscountPercents.add(volumeDiscountPercent); 
					 
				 
					 Double gmVolumePrcnt=FPdealdetails.get(i).getGmAfterVolumeDiscount();
					 if(gmVolumePrcnt != null) {
						 gmAfterVolumeDiscount=gmVolumePrcnt*100;
								 
					 } else {
						 gmAfterVolumeDiscount = 0.0;
					 }
					 if(gmAfterVolumeDiscount!= null) {
						 gmAfterVolumeper=(df2.format(gmAfterVolumeDiscount));
			 		 } else {
			 			gmAfterVolumeper = "-";
			 		 }
					 gmAfterVolumeDiscounts.add(gmAfterVolumeper);
					 
					 
					 
					 Double onsitePercentage=FPdealdetails.get(i).getExpectedOnsitePercentage();
					 if(onsitePercentage != null) {
						 
						 ExpectedonsitePercentage=(Math.round(onsitePercentage));
					 } else {
						 ExpectedonsitePercentage = 0;
					 }
					 
					 ExpectedonsitePercentages.add(ExpectedonsitePercentage);
					 
				 
			    
			    Double projectmg=FPdealdetails.get(i).getProjectMargin();
				 if(projectmg != null) {
					
					 projectMarginsper =(Math.round(projectmg));
				 } else {
					 projectMarginsper = 0;
				 }
				 projectMargins.add(projectMarginsper);
				 
				 
				 Double pMarginpercentage=FPdealdetails.get(i).getProjectMarginPercentage();
				 if(pMarginpercentage != null) {
					 projectMarginPercentage=(pMarginpercentage*100);
				 } else {
					 projectMarginPercentage = 0.0;
				 }
				 if(projectMarginPercentage!= null) {
					 projectMarginPercentagesper=(df2.format(projectMarginPercentage));
		 		 } else {
		 			projectMarginPercentagesper = "-";
		 		 }
				 projectMarginPercentages.add(projectMarginPercentagesper);
				 
				 
				 
				 
				 Double pmincrisk=FPdealdetails.get(i).getPminclrisk();
				 if(pmincrisk != null) {
					 pminclrisk=(pmincrisk*100);
				 } else {
					 pminclrisk = 0.0;
				 }
				 if(pminclrisk!= null) {
					 pminclrisksper=(df2.format(pminclrisk));
		 		 } else {
		 			pminclrisksper = "-";
		 		 }
				 pminclrisks.add(pminclrisksper);
				 
				 Double pminclRiskVDsperc=FPdealdetails.get(i).getPminclRiskVD();
				 if(pminclRiskVDsperc != null) {
					 pminclRiskVD=(pminclRiskVDsperc*100);
				 } else {
					 pminclRiskVD = 0.0;
				 }
				 if(pminclRiskVD!= null) {
					 pminclRiskVDsper=(df2.format(pminclRiskVD));
		 		 } else {
		 			pminclRiskVDsper = "-";
		 		 }
				 pminclRiskVDs.add(pminclRiskVDsper);
			    }
			    for(int i=0;i<FPdealdetails.size();i++){
			    dealTypeId=FPdealdetails.get(i).getDealTypeId();
				 switch (dealTypeId) {
			  		case 1 : 
			  			DealName = "FP";
			  			break;
			  		case 2 : 
			  			DealName = "T&M";
			  			break;
			  		default : 
			  			break;
				 }
				 DealNames.add(DealName);
				 
				 projectTypeId=FPdealdetails.get(i).getFpProjectTypeId();
				 switch (projectTypeId) {
			  		case 0 : 
			  			projectName = "NA";
			  			break;
			  		case 1 : 
			  			projectName = "Devel- FP";
			  			break;
			  		case 2 : 
			  			projectName = "Devel - Manage Cap";
			  			break;
			  		case 3 : 
			  			projectName = "Maint - FP";
			  			break;
			  		case 4 : 
			  			projectName = "Maint - Manage Cap";
			  			break;
			  		default : 
			  			break;
				 }
				 projectNames.add(projectName);
				 
				  int dealIds=FPdealdetails.get(i).getRpDealVersionId();
				  dealId.add(dealIds);
				  
				  String dealvesrions=FPdealdetails.get(i).getDealVersion();
				  dealVesrion.add(dealvesrions);
				
			    }
			    
			    
			    for(int k=0;k<ExpectedonsitePercentages.size();k++){
			    	ExpectedOffshorePercentages.add(Math.abs(100-(ExpectedonsitePercentages.get(k))));
			    }  
			
			    
			    
			    Map<Integer, List<Long>> getAllData = new HashMap<Integer, List<Long>>();
				getAllData = dealInformation;
				List<Long> offshoreTotal = getAllData.get(1);
				List<Long> localvalue = getAllData.get(2);
				List<Long> deputedvalue = getAllData.get(3);
				List<Long> offShorevalue = getAllData.get(4);		
		
		
		
		EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
		
		//List<RiskManagersAdminMail> qualtiyMaildetails = null;
		switch (templateID) {
		case 1: {
			String usernames="";
			String userEmails="";
			String userEmail1="";
			String username1="";
		  	String desg="";
		  	String desg1="";
		  	
		  	int itkpo=0;
			itkpo=FPdealdetails.get(0).getProjectIndustry();
				
		  	
			switch (status) {
	  		case "Delivery Head" : 
	  			usernames=deliveryHeadName;
	  			userEmails=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			usernames="GFt Costing Team";
	  			//userEmails="Syndev@TATOSMAIL.COM";
  			userEmails="gft_costing@eviden.com";
	  			desg="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			usernames=CDOName;
	  			userEmails=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				usernames=BuHeadName;
		  			userEmails=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				usernames=JVCEO;
	  				userEmails=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			usernames=CEOName;
	  			userEmails=CEOEmail;
	  			desg= "CEO";
	  			break;
	  		case "RiskManagers" : 
	  			

	  			usernames="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";	  			
	  			//userEmails="Syndev@TATOSMAIL.COM";//QualityPersonemail;
	  			userEmails="dl-atos-regionalmanagers@eviden.com";//QualityPersonemail
	  			desg="<"+rbuProfitCenter+">"+"  "+  "RiskManagers Team";

	  			break;
	  		case "Level 1" : 
	  			usernames=level1;
	  			userEmails=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			usernames=level2;
	  			userEmails=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
		
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
	  				userEmail1=JVCEOEmail;
		  			desg1="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1= "CEO";
	  			break;
	  		case "RiskManagers" : 
	  	

	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			//userEmail1="Syndev@TATOSMAIL.COM";//;
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  		default : 
	  			break;
			}
			AppLoger.APPLOGGER.info("Executing case 1");
			AppLoger.APPLOGGER.info("Mail Generated when Deal submitted ");
			tempId = "Temp1";
			desc = "Case 1: Deal  Approved";
			//to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
			//to[0] = deliveryHeadEmail;
			//to[1]="GFT tEam";
			//cc = new String[1];
			//cc[0] = createdbyemail;
			//cc[1] = deliveryTeam;
			
			
			if(updatedStaus.equals("Delivery Head")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int ccSize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("CDO")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int ccSize=cc.length;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("BUH")||updatedStaus.equals("BU_Head")||updatedStaus.equals("BU Head")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int ccSize=cc.length;
				if(BUhProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=BUhProxy.get(j);
						j++;
					}
					
				}
			}
			if(updatedStaus.equals("CEO")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int ccSize=cc.length;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 1")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int j=0;
				if(level1Proxy.get(0)!=null){
					int ccSize=cc.length;
					cc = Arrays.copyOf(cc, cc.length + level1Proxy.size());
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 2")){
				to = new String[1];
				to[0] = userEmail1;
				cc = new String[1];
				cc[0] = createdbyemail;
				int ccSize=cc.length;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("GFT")){
				to = new String[1];
				cc = new String[1];
				to[0] = userEmail1;
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//to[1] = "vishal.arora@eviden.com";
				cc[0] = createdbyemail;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				
			}
			
			
		    startDate = dateFormat(FPdealdetails.get(0).getProjectStartDate());
			endDate=dateFormat(FPdealdetails.get(0).getProjectEndDate());
			
            subject = FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+" â€“ "+Desc+" is awaiting your approval" ;
            
            mailContent = "<p>Dear "+username1+",</p>"
						+ "<p>&emsp;&emsp;"+createdby+" has submitted costing for  "+Desc+" - Deal ID: "+FPdealdetails.get(0).getCrmDealId()+" opportunity that is being pursued with "+FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} </style>"
						+ "<table> <tbody>"
						+"<tr> <td style=width:25%> <b>Consolidated Summary</b></td>";
						
						 for(int i=0;i<FPdealdetails.size();i++)
						 {
							 mailContent+= "<td style= width: 200px> "+  dealId.get(i)+"( "+ dealVesrion.get(i) +")</td>";
						 }
						 
			 mailContent+=	"</tr><tr><td> <b>Revenue ["+currencyName+" ] </b></td>";
							
				 		for(int i=0;i<revenue.size();i++)
				 		{
				 			mailContent+= "<td  style= width: 200px> "+ revenue.get(i)+ "</td>"; 
						}
				 		
				mailContent+= "</tr><tr><td style=width:25%> <b>Direct Cost ["+currencyName+" ] </b> </td>";
							
				 		for(int i=0;i<directCosts.size();i++)
				 		{
				 			mailContent+= "<td style=width: 200px> "+ directCosts.get(i)+ "</td>"; 
				 		}
				 		
						mailContent+=	"</tr><tr><td style=width:25%> <b>Project Specific SGA  ["+currencyName+" ] </b> </td>";
									
				 		for(int i=0;i<projectSpecificCosts.size();i++)
				 		{
				 			mailContent+= "<td style=width: 200px> "+ projectSpecificCosts.get(i)+ "%</td>"; 
				 		}
				 		
				 		mailContent+=	"</tr> <tr><td style=width:25%> <b>Project Margin </b> </td>";
						
				 		for(int i=0;i<projectMargins.size();i++)
				 		{
			 				mailContent+= "<td  style=width: 200px> "+ projectMargins.get(i)+ "%</td>"; 
				 		}
				 		
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % </b> </td>";
										
					 	for(int i=0;i<projectMarginPercentages.size();i++)
					 	{
					 		mailContent+= "<td style=width: 200px> "+ projectMarginPercentages.get(i)+ "%</td>"; 
					 	}
					 	mailContent+=	"</tr> <tr> <td style=width:25% > <b>Project Margin % incl. Risk  </b> </td>";
						
					 	for(int i=0;i<pminclrisks.size();i++)
					 	{
							mailContent+= "<td  style=width: 200px> "+ pminclrisks.get(i)+ "%</td>"; 
					 	}	
					 	
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Volume Discount% </b> </td>";
									
					 	for(int i=0;i<volumeDiscountPercents.size();i++)
					 	{
						 
							mailContent+= "<td  style=width: 200px> "+ volumeDiscountPercents.get(i)+ "%</td>"; 
					    }
					 	mailContent+=	"</tr> <tr> <td style=width:25% > <b>PM  % incl. Risk and VD  </b> </td>";
						
					 	for(int i=0;i<pminclRiskVDs.size();i++)
					 	{
							mailContent+= "<td  style=width: 200px> "+ pminclRiskVDs.get(i)+ "%</td>"; 
					 	}
				
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Efforts </b> </td>";	
									
					 for(int i=0;i<offshoreTotal.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ offshoreTotal.get(i)+ "</td>"; 
							
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Domestic </b></td>";	
									
					 for(int i=0;i<localvalue.size();i++)
					  {
						 mailContent+= "<td  style=width: 200px> "+ localvalue.get(i)+ "</td>";
					  }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Deputed </b></td>";	
										
					 for(int i=0;i<deputedvalue.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ deputedvalue.get(i)+ "</td>"; 
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Offshore </b></td>";	
											
					 for(int i=0;i<offShorevalue.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ offShorevalue.get(i)+ "</td>";
						 
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Onsite : Offshore Mix </b></td>";
												
					 for(int i=0;i<ExpectedonsitePercentages.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ ExpectedonsitePercentages.get(i)+":" +ExpectedOffshorePercentages.get(i)+ "</td>"; 
					 }
				mailContent+=	"</tr></tbody></table>"	
							+"</br>"
							+ "<p><u><strong> Deal Details: -</strong> </u></p>"
					
				+ "<table> <tbody>"
				+"<tr> <td style=width:25%> <b>Deal Type</b></td>";		
						for(int i=0;i<DealNames.size();i++)
						{
						mailContent+= "<td  style=width: 300px> "+ DealNames.get(i)+ "</td>"; 
						}
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Type    "+"       "+" </b></td>";
												
					 for(int i=0;i<projectNames.size();i++)
					 {
						 
							mailContent+= "<td  style=width: 300px > "+ projectNames.get(i)+ "</td>"; 
						
					 }
				mailContent+=	"</tr>"	
								+"<tr><td  style=width:35% ><b>Deal Start date   "+"        </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+startDate+ "</td></tr>"
								+"<tr><td  style=width:35% ><b>Deal End date    "+"         </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+endDate+"</td></tr>"		
								+"</tbody></table>"
								+"<br>"
						+ "<br><br>Regards,<br>"+SpocName+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
							
					break;
					}
		
	
		
		case 20: {
			
			AppLoger.APPLOGGER.info("Executing case 20");
			AppLoger.APPLOGGER.info("Mail Generated when Finally Approved");
			tempId = "Temp20";
			
			String usernames="";
			String userEmails="";
			String username1="";
		  	String userEmail1="";
		  	String desg="";
		  	String desg1="";
		  	int itkpo=0;
			itkpo=FPdealdetails.get(0).getProjectIndustry();
			switch (status) {
	  		case "Delivery Head" : 
	  			usernames=deliveryHeadName;
	  			userEmails=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			usernames="GFT Costing Team";
	  			//userEmails="Syndev@TATOSMAIL.COM";
	  			userEmails="gft_costing@eviden.com";
	  			desg="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			usernames=CDOName;
	  			userEmails=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				usernames=BuHeadName;
		  			userEmails=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				usernames=JVCEO;
	  				userEmails=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			usernames=CEOName;
	  			userEmails=CEOEmail;
	  			desg= "CEO";
	  			break;
	  		case "RiskManagers" : 
	  			usernames="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmails="dl-atos-regionalmanagers@eviden.com";//RiskManagersPersonemail;
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";

	  			break;
	  		case "Level 1" : 
	  			usernames=level1;
	  			userEmails=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			usernames=level2;
	  			userEmails=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
		
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				username1=BuHeadName;
		  			userEmail1=BuHeadEmail;
		  			desg1="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				username1=JVCEO;
	  				userEmail1=JVCEOEmail;
		  			desg1="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1= "CEO";
	  			break;
	  		case "RiskManagers" : 
	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";//RiskManagersPersonemail;
  			desg="<"+rbuProfitCenter+">"+"  "+  "RiskManagers Team";
	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  		default : 
	  			break;
			}
			
			AppLoger.APPLOGGER.info("Executing case 1");
			AppLoger.APPLOGGER.info("Mail Generated when finally approved ");
			tempId = "Temp1";
			desc = "Case 1: Deal  Approved";
			//to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
			//to[0] = createdbyemail;
			//to[1]="GFT tEam";
			//cc = new String[1];
			//cc[0] = userEmails;
			//cc[1] = deliveryTeam;
			
			
			if(status.equals("GFT"))
			{
				to = new String[1];
				cc = new String[1];
				to[0] = createdbyemail;
				cc[0] = userEmails;
				//cc[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=1;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				
			}
			else
			{
				to = new String[1];
				cc = new String[1];
				to[0] = createdbyemail;
				cc[0] = userEmails;
			}
			
		    startDate = dateFormat(FPdealdetails.get(0).getProjectStartDate());
			endDate=dateFormat(FPdealdetails.get(0).getProjectEndDate());
			
            subject = desg+" has reviewed the Deal Prepared for "+ FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+".";
            
            		mailContent="<p>Dear "+createdby+",</p>"
            		+ "<p>&emsp;&emsp;"+usernames+" has approved costing for "+Desc+" for following Deal ID: "+FPdealdetails.get(0).getCrmDealId()+" opportunity that is being pursued with "+FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
					+ "<p>&emsp;&emsp;<u><strong> The approval process is now complete -</strong> </u></p>"
					+"<br>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} </style>"
					+ "<table> <tbody>"
					+"<tr> <td style=width:25%> <b>Consolidated Summary</b></td>";
					
					 for(int i=0;i<FPdealdetails.size();i++)
					 {
						 mailContent+= "<td style= width: 200px> "+  dealId.get(i)+"( "+ dealVesrion.get(i) +")</td>";
					 }
					 
		 mailContent+=	"</tr><tr><td> <b>Revenue ["+currencyName+" ] </b></td>";
						
			 		for(int i=0;i<revenue.size();i++)
			 		{
			 			mailContent+= "<td  style= width: 200px> "+ revenue.get(i)+ "</td>"; 
					}
			 		
			mailContent+= "</tr><tr><td style=width:25%> <b>Direct Cost ["+currencyName+" ] </b> </td>";
						
			 		for(int i=0;i<directCosts.size();i++)
			 		{
			 			mailContent+= "<td style=width: 200px> "+ directCosts.get(i)+ "</td>"; 
			 		}
			 							 		
			mailContent+=	"</tr><tr><td style=width:25%> <b>Project Specific SGA  ["+currencyName+" ] </b> </td>";
								
			 		for(int i=0;i<projectSpecificCosts.size();i++)
			 		{
			 			mailContent+= "<td style=width: 200px> "+ projectSpecificCosts.get(i)+ "</td>"; 
			 		}
			 		
			 		mailContent+=	"</tr> <tr><td style=width:25%> <b>Project Margin</b> </td>";
					
			 		for(int i=0;i<projectMargins.size();i++)
			 		{
		 				mailContent+= "<td  style=width: 200px> "+ projectMargins.get(i)+ "</td>"; 
			 		}
			 		
			 		mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % </b> </td>";
									
				 	for(int i=0;i<projectMarginPercentages.size();i++)
				 	{
				 		mailContent+= "<td style=width: 200px> "+ projectMarginPercentages.get(i)+ "%</td>"; 
				 	}
				 	
				 	mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % incl. Risk </b> </td>";
					
				 	for(int i=0;i<pminclrisks.size();i++)
				 	{
						mailContent+= "<td  style=width: 200px> "+ pminclrisks.get(i)+ "%</td>"; 
				 	}
				 	
				 	mailContent+=	"</tr> <tr> <td style=width:25% > <b> Volume Discount% </b> </td>";
								
				 	for(int i=0;i<volumeDiscountPercents.size();i++)
				 	{
					 
						mailContent+= "<td  style=width: 200px> "+ volumeDiscountPercents.get(i)+ "%</td>"; 
				    }
			
				 	mailContent+=	"</tr> <tr> <td style=width:25% > <b> PM  % incl. Risk and VD </b> </td>";
					
				 	for(int i=0;i<pminclRiskVDs.size();i++)
				 	{
						mailContent+= "<td  style=width: 200px> "+ pminclRiskVDs.get(i)+ "%</td>";
						
				 	}
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Efforts </b> </td>";	
								
				 for(int i=0;i<offshoreTotal.size();i++)
				 {
					 mailContent+= "<td  style=width: 200px> "+ offshoreTotal.get(i)+ "</td>"; 
						
				 }
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Domestic </b></td>";	
								
				 for(int i=0;i<localvalue.size();i++)
				  {
					 mailContent+= "<td  style=width: 200px> "+ localvalue.get(i)+ "</td>";
				  }
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Deputed </b></td>";	
									
				 for(int i=0;i<deputedvalue.size();i++)
				 {
					 mailContent+= "<td  style=width: 200px> "+ deputedvalue.get(i)+ "</td>"; 
				 }
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Offshore </b></td>";	
										
				 for(int i=0;i<offShorevalue.size();i++)
				 {
					 mailContent+= "<td  style=width: 200px> "+ offShorevalue.get(i)+ "</td>";
					 
				 }
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Onsite : Offshore Mix </b></td>";
											
				 for(int i=0;i<ExpectedonsitePercentages.size();i++)
				 {
					 mailContent+= "<td  style=width: 200px> "+ ExpectedonsitePercentages.get(i)+":" +ExpectedOffshorePercentages.get(i)+ "</td>"; 
				 }
			mailContent+=	"</tr></tbody></table>"	
						+"</br>"
						+ "<p><u><strong> Deal Details: -</strong> </u></p>"
				
			+ "<table> <tbody>"
			+"<tr> <td style=width:25%> <b>Deal Type</b></td>";		
					for(int i=0;i<DealNames.size();i++)
					{
					mailContent+= "<td  style=width: 300px> "+ DealNames.get(i)+ "</td>"; 
					}
			mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Type    "+"       "+" </b></td>";
											
				 for(int i=0;i<projectNames.size();i++)
				 {
					 
						mailContent+= "<td  style=width: 300px > "+ projectNames.get(i)+ "</td>"; 
					
				 }
			mailContent+=	"</tr>"	
						+"<tr><td  style=width:35% ><b>Deal Start date   "+"        </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+startDate+ "</td></tr>"
						+"<tr><td  style=width:35% ><b>Deal End date    "+"         </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+endDate+"</td></tr>"		
						+"</tbody></table>"
						+"<br>"
						+ "<br><br>Regards,<br>"+usernames+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
							
					break;
					}
		
		
		
		case 35: {
			
			AppLoger.APPLOGGER.info("Executing case 20");
			AppLoger.APPLOGGER.info("Mail Generated when send for approval");
			tempId = "Case35";
			
			String usernames="";
			String userEmails="";
			String username1="";
		  	String userEmail1="";
		  	String desg="";
		  	String desg1="";
		  	int itkpo=0;
			itkpo=FPdealdetails.get(0).getProjectIndustry();
			switch (status) {
	  		case "Delivery Head" : 
	  			usernames=deliveryHeadName;
	  			userEmails=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			usernames="GFT Costing Team";
	  			//userEmails="Syndev@TATOSMAIL.COM";
	  			userEmails="gft_costing@eviden.com";
	  			desg="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			usernames=CDOName;
	  			userEmails=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				usernames=BuHeadName;
		  			userEmails=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				usernames=JVCEO;
	  				userEmails=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			usernames=CEOName;
	  			userEmails=CEOEmail;
	  			desg= "CEO";
	  			break;
	  		case "RiskManagers" : 
	  			
	  			usernames="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmails="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";
	  			break;
	  		case "Level 1" : 
	  			usernames=level1;
	  			userEmails=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			usernames=level2;
	  			userEmails=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
		
			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg1="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			//userEmail1="Syndev@TATOSMAIL.COM";
	  			userEmail1="gft_costing@eviden.com";
	  			desg1="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg1="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			username1=BuHeadName;
	  			userEmail1=BuHeadEmail;
	  			desg1="BU Head";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg1= "CEO";
	  			break;

	  		/*case "Quality" : 
	  			username1="Quality";
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";
	  			//userEmail1="Syndev@TATOSMAIL.COM";//QualityPersonemail;
	  			desg="Quality Team";*/

	  		case "RiskManagers" : 
	  			username1="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmail1="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";

	  			break;
	  		case "Level 1" : 
	  			username1=level1;
	  			userEmail1=level1mail;
	  			desg1=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			username1=level2;
	  			userEmail1=level2Email;
	  			desg1=level2;
	  			break;
	  		default : 
	  			break;
			}
			
			AppLoger.APPLOGGER.info("Executing case 1 for Multiple deals");
			AppLoger.APPLOGGER.info("Mail Generated when Deal send for approval ");
			tempId = "Temp1";
			desc = "Case 1: Deal  Approved";
			
			
			if(status.equals("Delivery Head")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					if(DelievryProxy.get(0)!=null){
						for(int i=2;i<cc.length;i++)
						{
							cc[i]=DelievryProxy.get(j);
							j++;
						}
						
					}
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;
					}
			}
			if(status.equals("CDO")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;}
				
			}
			if(status.equals("BUH")||status.equals("BU_Head")||status.equals("BU Head")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
					if(BUhProxy.get(0)!=null){
						cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
						int j=0;
						for(int i=2;i<cc.length;i++)
						{
							cc[i]=BUhProxy.get(j);
							j++;
						}
					}
					
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;
					}
			}
			if(status.equals("CEO")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;
					}
				
				
			}
			if(status.equals("Level 1")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				
				if(level1Proxy.get(0)!=null){
					int sizeProxy=level1Proxy.size();
					cc = Arrays.copyOf(cc, cc.length + sizeProxy);
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;}
				
			}
			if(status.equals("Level 2")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;
				}
				
			}
			if(status.equals("RiskManagers")){
				
				cc = new String[3];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				//cc[2] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				cc[2] = "chennakesava-reddy.v@eviden.com";
				AppLoger.APPLOGGER.info("inside RiskManagers 1");
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				AppLoger.APPLOGGER.info("inside RiskManagers 2");
				int j=0;
				for(int i=3;i<cc.length;i++){
					AppLoger.APPLOGGER.info("inside RiskManagers 3");
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
			}
				/*cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				
				if(RiskManagersProxy.get(0)!=null){
					int sizeProxy=RiskManagersProxy.size();
					cc = Arrays.copyOf(cc, cc.length + sizeProxy);
					int j=0;
					for(int i=2;i<cc.length;i++)
					{
						cc[i]=RiskManagersProxy.get(j);
						j++;
					}
					
				}
				else{
					cc = new String[2];
					cc[0] = userEmail;
					cc[1] = createdbyemail;
					}
				*/
			
			
			if(status.equals("GFT")){
				cc = new String[2];
				cc[0] = userEmail;
				cc[1] = createdbyemail;
				//cc[2] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//cc[2] = "vishal.arora@eviden.com";
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=2;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
			}
			if(updatedStaus.equals("RiskManagers")){
				to = new String[2];
				to[0] = userEmail1;
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				to[1] = "chenakesava-reddy.v@eviden.com";
				int ccsize=cc.length;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=ccsize;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				
				/*to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(RiskManagersProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + sizeofTo);
					int j=0;
					for(int i=3;i<cc.length;i++){
						cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
						j++;
				      }
				}*/
				
			}			
			
			if(updatedStaus.equals("Delivery Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(DelievryProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("CDO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CDOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CDOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CDOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("BUH")||updatedStaus.equals("BU_Head")||updatedStaus.equals("BU Head")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(BUhProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + BUhProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=BUhProxy.get(j);
						j++;
					}
					
				}
			}
			if(updatedStaus.equals("CEO")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(CEOProxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + CEOProxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=CEOProxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 1")){
				to = new String[1];
				to[0] = userEmail1;
				int j=0;
				if(level1Proxy.get(0)!=null){
					int ccSize=cc.length;
					cc = Arrays.copyOf(cc, cc.length + level1Proxy.size());
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level1Proxy.get(j);
						j++;
					}
					
				}
				
			}
			if(updatedStaus.equals("Level 2")){
				to = new String[1];
				to[0] = userEmail1;
				int ccSize=cc.length;
				if(level2Proxy.get(0)!=null){
					cc = Arrays.copyOf(cc, cc.length + level2Proxy.size());
					int j=0;
					for(int i=ccSize;i<cc.length;i++)
					{
						cc[i]=level2Proxy.get(j);
						j++;
					}
					
				}
				
			}
			
			
			if(updatedStaus.equals("GFT")){
				to = new String[1];
				to[0] = userEmail1;
				//to[1] = "Syndev@TATOSMAIL.COM";//"vishal.arora@eviden.com";
				//to[1] = "vishal.arora@eviden.com";
				int ccsize=cc.length;
				cc = Arrays.copyOf(cc, cc.length + sizeofTo);
				int j=0;
				for(int i=ccsize;i<cc.length;i++){
					cc[i]=verticalPOCMailList.get(j).getEmployeeemail();
					j++;
			      }
				
				
			}
			
		    startDate = dateFormat(FPdealdetails.get(0).getProjectStartDate());
			endDate=dateFormat(FPdealdetails.get(0).getProjectEndDate());
			
            subject = desg+" has reviewed the Deal Prepared for "+ FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+".";
            
            		mailContent="<p>Dear "+username1+",</p>"
            		+ "<p>&emsp;&emsp;"+usernames+" has approved costing for "+Desc+"  Deal ID: "+FPdealdetails.get(0).getCrmDealId()+" opportunity that is being pursued with "+FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+". </p>"
					+ "<p>&emsp;&emsp;<u><strong> Request Your Approval: -</strong> </u></p>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style>"
					+ "<table class='tblNoBorder'> <tbody>"
					+"<tr> <td style=width:25%> <b>Consolidated Summary</b></td>";
					
					 for(int i=0;i<FPdealdetails.size();i++)
					 {
						 mailContent+= "<td style= width: 200px> "+  dealId.get(i)+"( "+ dealVesrion.get(i) +")</td>";
					 }
					 
					mailContent+=	"</tr><tr><td> <b>Revenue ["+currencyName+" ] </b></td>";
						
						for(int i=0;i<revenue.size();i++)
						{
							mailContent+= "<td  style= width: 200px> "+ revenue.get(i)+ "</td>"; 
					}
						mailContent+= "</tr><tr><td style=width:25%> <b>Direct Cost ["+currencyName+" ] </b> </td>";
						
				 		for(int i=0;i<directCosts.size();i++)
				 		{
				 			mailContent+= "<td style=width: 200px> "+ directCosts.get(i)+ "</td>"; 
				 		}	
						
						mailContent+=	"</tr><tr><td style=width:25%> <b>Project Specific SGA  ["+currencyName+" ] </b> </td>";
						for(int i=0;i<projectSpecificCosts.size();i++)
						{			 			
							mailContent+= "<td style=width: 200px> "+ projectSpecificCosts.get(i)+ "</td>";
							}
						mailContent+=	"</tr> <tr><td style=width:25%> <b>Project Margin</b> </td>";
						for(int i=0;i<projectMargins.size();i++)
						{		 				mailContent+= "<td  style=width: 200px> "+ projectMargins.get(i)+ "</td>"; 
						}			 			
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % </b> </td>";	
						for(int i=0;i<projectMarginPercentages.size();i++)	
						{				 		mailContent+= "<td style=width: 200px> "+ projectMarginPercentages.get(i)+ "%</td>"; 
						}				 					 
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % incl. Risk </b> </td>";
						for(int i=0;i<pminclrisks.size();i++)				 	{		
							mailContent+= "<td  style=width: 200px> "+ pminclrisks.get(i)+ "%</td>"; 
							}				 					 
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Volume Discount% </b> </td>";
						for(int i=0;i<volumeDiscountPercents.size();i++)
						{					 						mailContent+= "<td  style=width: 200px> "+ volumeDiscountPercents.get(i)+ "%</td>";
						}							
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> PM  % incl. Risk and VD </b> </td>";	
						for(int i=0;i<pminclRiskVDs.size();i++)				 
						{						mailContent+= "<td  style=width: 200px> "+ pminclRiskVDs.get(i)+ "%</td>";
						}

	
					
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Efforts </b> </td>";	
								
					for(int i=0;i<offshoreTotal.size();i++)
					{
					 mailContent+= "<td  style=width: 200px> "+ offshoreTotal.get(i)+ "</td>"; 
						
					}
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Domestic </b></td>";	
								
					for(int i=0;i<localvalue.size();i++)
					{
					 mailContent+= "<td  style=width: 200px> "+ localvalue.get(i)+ "</td>";
					}
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Deputed </b></td>";	
									
					for(int i=0;i<deputedvalue.size();i++)
					{
					 mailContent+= "<td  style=width: 200px> "+ deputedvalue.get(i)+ "</td>"; 
					}
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Offshore </b></td>";	
										
					for(int i=0;i<offShorevalue.size();i++)
					{
					 mailContent+= "<td  style=width: 200px> "+ offShorevalue.get(i)+ "</td>";
					 
					}
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Onsite : Offshore Mix </b></td>";
											
					for(int i=0;i<ExpectedonsitePercentages.size();i++)
					{
					 mailContent+= "<td  style=width: 200px> "+ ExpectedonsitePercentages.get(i)+":" +ExpectedOffshorePercentages.get(i)+ "</td>"; 
					}
					mailContent+=	"</tr></tbody></table>"	
						+"</br>"
						+ "<p><u><strong> Deal Details: -</strong> </u></p>"
					
					+ "<table> <tbody>"
					+"<tr> <td style=width:25%> <b>Deal Type</b></td>";		
					for(int i=0;i<DealNames.size();i++)
					{
					mailContent+= "<td  style=width: 300px> "+ DealNames.get(i)+ "</td>"; 
					}
					mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Type    "+"       "+" </b></td>";
											
					for(int i=0;i<projectNames.size();i++)
					{
					 
						mailContent+= "<td  style=width: 300px > "+ projectNames.get(i)+ "</td>"; 
					
					}
					mailContent+=	"</tr>"	
						+"<tr><td  style=width:35% ><b>Deal Start date   "+"        </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+startDate+ "</td></tr>"
						+"<tr><td  style=width:35% ><b>Deal End date    "+"         </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+endDate+"</td></tr>"		
						+"</tbody></table>"
						+"<br>"
						+ "<br><br>Regards,<br>"+usernames+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
							
					break;
					}
		
		case 36: {
			AppLoger.APPLOGGER.info("Executing case 36");
			AppLoger.APPLOGGER.info("Mail Generated deal is rejected ");
			tempId = "Temp1";
			desc = "Case 1:Deals Rejected";
			to = new String[1];
			
			String usernames="";
			String userEmails="";
			String username1="";
		  	String userEmail1="";
		  	String desg="";
		  	String desg1="";
		  	int itkpo=0;
			itkpo=FPdealdetails.get(0).getProjectIndustry();
			
			switch (status) {
	  		case "Delivery Head" : 
	  			usernames=deliveryHeadName;
	  			userEmails=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			usernames="GFT Costing team";
	  			//userEmails="Syndev@TATOSMAIL.COM";
     			userEmails="gft_costing@eviden.com";
	  			desg="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			usernames=CDOName;
	  			userEmails=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			if(itkpo==0||itkpo==1){
	  				usernames=BuHeadName;
		  			userEmails=BuHeadEmail;
		  			desg="BU Head";
		  			break;
	  			}
	  			else if(itkpo==2){
	  				usernames=JVCEO;
	  				userEmails=JVCEOEmail;
		  			desg="BU Head";
	  			break;
	  			}
	  		case "CEO" : 
	  			usernames=CEOName;
	  			userEmails=CEOEmail;
	  			desg= "CEO";
	  			break;

	  		/*case "Quality" : 
	  			usernames="Quality";
	  			userEmails="dl-atos-regionalmanagers@eviden.com";
	  			//userEmails="Syndev@TATOSMAIL.COM";//QualityPersonemail;
	  			desg="Quality Team";*/

	  		case "RiskManagers" : 
	  			usernames="<"+rbuProfitCenter+">"+"  "+ "RiskManagers";
	  			userEmails="dl-atos-regionalmanagers@eviden.com";
	  			desg="<"+rbuProfitCenter+">"+"  "+ "RiskManagers Team";

	  			break;
	  			
	  		case "Level 1" : 
	  			usernames=level1;
	  			userEmails=level1mail;
	  			desg=level1;
	  			break;
	  			
	  		case "Level 2" : 
	  			usernames=level2;
	  			userEmails=level2Email;
	  			desg=level2;
	  			break;
	  		default : 
	  			break;
			}
		
/*			switch (updatedStaus) {
	  		case "Delivery Head" : 
	  			username1=deliveryHeadName;
	  			userEmail1=deliveryHeadEmail;
	  			desg="Delivery Head";
	  			break;
	  		case "GFT" : 
	  			username1="GFT Costing Team";
	  			userEmail1="gft_costing@eviden.com";
	  			desg="GFT Costing Team";
	  			break;
	  		case "CDO" : 
	  			username1=CDOName;
	  			userEmail1=CDOEmail;	
	  			desg="CDO";
	  			break;
	  		case "BU Head" : 
	  		case "BU_Head" :
	  		case "BUH" : 
	  			username1=BuHeadName;
	  			userEmail1=BuHeadEmail;
	  			desg1="BU Head";
	  			break;
	  		case "CEO" : 
	  			username1=CEOName;
	  			userEmail1=CEOEmail;
	  			desg= "CEO";
	  			
	  		case "RiskManagers" : 
	  			username1=RiskManagersName;
	  			userEmail1=RiskManagersEmail;
	  			desg="RiskManagers Team";
	  		default : 
	  			break;
			}
*/			
			//to[0] = emailList.get(1).emailId.toString();
			//to[0] = createdbyemail;
			//to[1]="GFT tEam";
			//cc = new String[1];
			//cc[0] = userEmails;
			//cc[1] = deliveryTeam;
			if(status.equals("GFT")){
				to = new String[1];
				cc = new String[1];
				to[0] = createdbyemail;
				//cc[0] = "Syndev@TATOSMAIL.COM";
				//cc[0] = "vishal.arora@eviden.com";
				cc[0] = userEmails;
				
				
			}
			else if(status.equals("RiskManagers")){
				to = new String[1];
				cc = new String[2];
				to[0] = createdbyemail;
				//cc[0] = "Syndev@TATOSMAIL.COM";
				cc[0] = "chennakesava-reddy.v@eviden.com";//"vishal.arora@eviden.com";
				cc[1] = userEmails;
				
				
			}
			else
			{
				to = new String[1];
				cc = new String[1];
				to[0] = createdbyemail;
				cc[0] = userEmails;
			}
			
		    startDate = dateFormat(FPdealdetails.get(0).getProjectStartDate());
			endDate=dateFormat(FPdealdetails.get(0).getProjectEndDate());
			
            subject = FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+" â€“ "+Desc+" has been recycled " ;
            
            mailContent = "<p>Dear "+createdby+",</p>"
            		    + "<p>&emsp;&emsp;"+usernames+" has recycled the costing for "+Desc+"  following Deal ID: "+FPdealdetails.get(0).getCrmDealId()+"  opportunity that is being pursued with "+FPdealdetails.get(0).getCustomerVerticalMapping().getCustomer().getCustomerName()+" . </p>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} </style>"
						+ "<table> <tbody>"
						+"<tr> <td style=width:25%> <b>Consolidated Summary</b></td>";
						
						 for(int i=0;i<FPdealdetails.size();i++)
						 {
							 mailContent+= "<td style= width: 200px> "+  dealId.get(i)+"( "+ dealVesrion.get(i) +")</td>";
						 }
						 
			 mailContent+=	"</tr><tr><td> <b>Revenue ["+currencyName+" ] </b></td>";
							
				 		for(int i=0;i<revenue.size();i++)
				 		{
				 			mailContent+= "<td  style= width: 200px> "+ revenue.get(i)+ "</td>"; 
						}
				 		
				mailContent+= "</tr><tr><td style=width:25%> <b>Direct Cost ["+currencyName+" ] </b> </td>";
							
				 		for(int i=0;i<directCosts.size();i++)
				 		{
				 			mailContent+= "<td style=width: 200px> "+ directCosts.get(i)+ "</td>"; 
				 		}
				 		
				mailContent+=	"</tr><tr><td style=width:25%> <b>Project Specific SGA  ["+currencyName+" ] </b> </td>";
									
				 		for(int i=0;i<projectSpecificCosts.size();i++)
				 		{
				 			mailContent+= "<td style=width: 200px> "+ projectSpecificCosts.get(i)+ "</td>"; 
				 		}
				 		
				 		mailContent+=	"</tr><tr><td style=width:25%> <b>Project Specific SGA  ["+currencyName+" ] </b> </td>";
						for(int i=0;i<projectSpecificCosts.size();i++)
						{			 			
							mailContent+= "<td style=width: 200px> "+ projectSpecificCosts.get(i)+ "</td>";
							}
						mailContent+=	"</tr> <tr><td style=width:25%> <b>Project Margin</b> </td>";
						for(int i=0;i<projectMargins.size();i++)
						{		 				mailContent+= "<td  style=width: 200px> "+ projectMargins.get(i)+ "</td>"; 
						}			 			
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % </b> </td>";	
						for(int i=0;i<projectMarginPercentages.size();i++)	
						{				 		mailContent+= "<td style=width: 200px> "+ projectMarginPercentages.get(i)+ "%</td>"; 
						}				 					 
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Margin % incl. Risk </b> </td>";
						for(int i=0;i<pminclrisks.size();i++)				 	{		
							mailContent+= "<td  style=width: 200px> "+ pminclrisks.get(i)+ "%</td>"; 
							}				 					 
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> Volume Discount% </b> </td>";
						for(int i=0;i<volumeDiscountPercents.size();i++)
						{					 						mailContent+= "<td  style=width: 200px> "+ volumeDiscountPercents.get(i)+ "%</td>";
						}							
						mailContent+=	"</tr> <tr> <td style=width:25% > <b> PM  % incl. Risk and VD </b> </td>";	
						for(int i=0;i<pminclRiskVDs.size();i++)				 
						{						mailContent+= "<td  style=width: 200px> "+ pminclRiskVDs.get(i)+ "%</td>";
						}

	
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Efforts </b> </td>";	
									
					 for(int i=0;i<offshoreTotal.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ offshoreTotal.get(i)+ "</td>"; 
							
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Domestic </b></td>";	
									
					 for(int i=0;i<localvalue.size();i++)
					  {
						 mailContent+= "<td  style=width: 200px> "+ localvalue.get(i)+ "</td>";
					  }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Deputed </b></td>";	
										
					 for(int i=0;i<deputedvalue.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ deputedvalue.get(i)+ "</td>"; 
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Offshore </b></td>";	
											
					 for(int i=0;i<offShorevalue.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ offShorevalue.get(i)+ "</td>";
						 
					 }
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Onsite : Offshore Mix </b></td>";
												
					 for(int i=0;i<ExpectedonsitePercentages.size();i++)
					 {
						 mailContent+= "<td  style=width: 200px> "+ ExpectedonsitePercentages.get(i)+":" +ExpectedOffshorePercentages.get(i)+ "</td>"; 
					 }
				mailContent+=	"</tr></tbody></table>"	
							+"</br>"
							+ "<p><u><strong> Deal Details: -</strong> </u></p>"
					
				+ "<table> <tbody>"
				+"<tr> <td style=width:25%> <b>Deal Type</b></td>";		
						for(int i=0;i<DealNames.size();i++)
						{
						mailContent+= "<td  style=width: 300px> "+ DealNames.get(i)+ "</td>"; 
						}
				mailContent+=	"</tr> <tr> <td style=width:25% > <b> Project Type    "+"       "+" </b></td>";
												
					 for(int i=0;i<projectNames.size();i++)
					 {
						 
							mailContent+= "<td  style=width: 300px > "+ projectNames.get(i)+ "</td>"; 
						
					 }
				mailContent+=	"</tr>"	
						+"<tr><td  style=width:35% ><b>Deal Start date   "+"        </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+startDate+ "</td></tr>"
						+"<tr><td  style=width:35% ><b>Deal End date    "+"         </b></td><td colspan="+projectNames.size()+"  style=width: 300px>"+endDate+"</td></tr>"		
						+"</tbody></table>"
						+"<br>"
						+ "<br><br>Regards,<br>"+usernames+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
						emailDetails.setTo(to);
						emailDetails.setCc(cc);
						emailDetails.setSubject(subject);
						emailDetails.setMailContent(mailContent);
							
					break;
					}
		
		         }
								
		try {
				
	 			AppLoger.APPLOGGER.info(" triggerMail started");
				MailTrigger.sendMail(emailDetails);
			
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		AppLoger.APPLOGGER.info(" triggerMail : EXIT :");

 }
		
	
	
	// Formatting Date Format
	private static String dateFormat(String serviceFromDate) {
		String date = serviceFromDate;
		SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date21 = null;
		try {
			date21 = simpleDataFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		simpleDataFormat = new SimpleDateFormat("MM/dd/yyyy");
		return simpleDataFormat.format(date21);
	}
	
}
