package com.rightprice.auth.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.lang.Object;

import com.rightprice.auth.model.Customer;
import com.rightprice.auth.model.Deal;
import com.rightprice.auth.model.DealCrmStages2;
import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.model.FpDeal;
import com.rightprice.auth.model.RateCardApprovalAudit;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.RateCardSummaryDataNew;
import com.rightprice.auth.model.RatecardSummaryData;

public class RightPriceTMEmailTemplate {
	@SuppressWarnings("unused")

	public void triggerMail(int templateID,List<Deal> TMrateCardDetails,Integer rpVersionId,String currentuser,String dealVersion,String createdby,String updater,String deliveryData[],String  Leaddetails[],List<DealCrmStages2> crmDealdata,Map<Integer, List<Long>>dealInformation,String createbyEmail,String currentuserEmail, List<RateCardSummaryDataNew> rcDealInfo ,Map<Integer, List<Double>>rcOffOnValues,List<Customer> customerNames, String updatedByEmail,Map<Integer, List<String>> proxyEmail) {
		
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
		String tempId;
		String desc;
		String[] to = null;
		String[] cc = null;
		
		String CEOName = Leaddetails[0];
		String CEOEmail=Leaddetails[1];
		String CDOName=Leaddetails[2];
		String CDOEmail=Leaddetails[3];
		String deliveryHeadEmail=deliveryData[1];
		String BuHeadName=deliveryData[2];
		String deliveryHeadName = deliveryData[0];
		String BuHeadEmail=deliveryData[3];
		String RiskManagersPerson=deliveryData[4];
		String RiskManagersPersonemail=deliveryData[5];
		String SpocName="";
		int currencyId=0;
		String currencyName="";
		String Desc="";
		String FinalGMPercentages="0.0";
		
		String subject;
		String mailContent;
		long ExpectedOffshorePercentage=0;
		long ExpectedonsitePercentage=0;
		String OffshoreVals=null, onSiteVals=null;
		Double FinalGMPercentage=0.0;
		List<Integer> rcIds = new ArrayList<Integer>();
		List<String> gMpercts = new ArrayList<String>();
		
		int rcId=0;
		double gmPer=0.0;
		String gMpercentage="0.0";
		
		Map<Integer, List<Long>> getAllData = new HashMap<Integer, List<Long>>();
		getAllData = dealInformation;
		List<Long> domesticvalue = getAllData.get(1);
		List<Long> deputedvalue = getAllData.get(2);
		List<Long> offShorevalue = getAllData.get(3);
		List<Long> EffortsPM  = getAllData.get(4);
		
		Map<Integer, List<String>> proxyApproverEmail = new HashMap<Integer, List<String>>();
		 proxyApproverEmail = proxyEmail;
			List<String> DelievryProxy = proxyApproverEmail.get(1);
			List<String> BUhProxy = proxyApproverEmail.get(2);
			List<String> RiskManagersProxy = proxyApproverEmail.get(3);
			List<String> CDOProxy = proxyApproverEmail.get(4);
			List<String> CEOProxy = proxyApproverEmail.get(5);
			List<String> level1Proxy = proxyApproverEmail.get(6);
			List<String> level2Proxy = proxyApproverEmail.get(7);
		

		DecimalFormat df2 = new DecimalFormat("#0.00");
		
		Map<Integer, List<Double>> getOffshoreOnsitee = new HashMap<Integer, List<Double>>();
		getOffshoreOnsitee = rcOffOnValues;
		List<Double> offshorevalue= getOffshoreOnsitee.get(1);
			 DecimalFormat df = new DecimalFormat("#0.00");
			 		if(offshorevalue!=null){
			 		 if(offshorevalue.get(0) != null) {
			 			 OffshoreVals=(df2.format(offshorevalue.get(0)));
			 		 } else {
			 			 OffshoreVals = "-";
			 		 }
			 		 if(offshorevalue.get(0)==1.7976931348623157E308) {
			 			 OffshoreVals = "-";
			 		 }
			 		}
			 		else{
			 			 OffshoreVals = "-";
			 		}
			 		List<Double> onsiteValues = getOffshoreOnsitee.get(2);
			 		if(onsiteValues!=null){
			 		 if(onsiteValues.get(0) != null) {
			 			onSiteVals=(df.format(onsiteValues.get(0)));
			 		 } else {
			 			 onSiteVals = "0.00";
			 		 }
			 		 if(offshorevalue.get(0)==1.7976931348623157E308) {
			 			 onSiteVals = "-";
			 		 }
			 		}
			 		else {
			 			 onSiteVals = "0.00";
			 		 } 
			  

		 
		 
		
		/*Map<Integer, List<Double>> TMdealfinalGm = new HashMap<Integer, List<Double>>();
		TMdealfinalGm=TMdealInformation;
		List<Double> finalgm= TMdealfinalGm.get(1);*/
		
		/*Map<Integer, List<Integer>> rateCradInfo =new HashMap<Integer, List<Integer>>();
		rateCradInfo=rcDealInfo;
		List<Integer> rcIds= rcDealInfo.get(1);
		List<Integer> gmPerVals= rcDealInfo.get(2);*/
		
		
		for(DealCrmStages2 crmDealData : crmDealdata){
			
			 Desc=crmDealData.getDealDescription();
			 SpocName=crmDealData.getSalesSpoc();
			 currencyId=crmDealData.getCurrencyId();
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
		
		for(Customer customersNames : customerNames){
			
			 Desc=customersNames.getCustomerName();
			 
		}
		
		for(RateCardSummaryDataNew rcGmper : rcDealInfo){
			
			rcId=rcGmper.getRc_Id();
			rcIds.add(rcId);
			gmPer= rcGmper.getGm_Per();
			
			gMpercentage=df2.format(gmPer);
			gMpercts.add(gMpercentage);
			 
		}
			 
		
		
		EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
		//String approvalLink ="https://10.128.10.231/InsurancePortal/login";
		switch (templateID) {
		
		
		case 1: {
			AppLoger.APPLOGGER.info("Executing case 1");
			AppLoger.APPLOGGER.info("Mail Generated for TM send For approval");
			tempId = "Temp1";
			desc = "Case 1: Policy send For Approval";
			to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
			to[0] =deliveryHeadEmail ;
			cc = new String[1];
			cc[0]= createbyEmail ;
			
			if(DelievryProxy.get(0)!=null){
				cc = Arrays.copyOf(cc, cc.length + DelievryProxy.size());
				int j=0;
				if(DelievryProxy.get(0)!=null){
					for(int i=1;i<cc.length;i++)
					{
						cc[i]=DelievryProxy.get(j);
						j++;
					}
					
				}
			}
			else{
				cc = new String[1];
				cc[0]= createbyEmail ;
				}
			for(Deal rateData : TMrateCardDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				Double finalGMPercentage=rateData.getGmPercentage();
				if(finalGMPercentage!=null){
					FinalGMPercentage=finalGMPercentage;
					FinalGMPercentages=df2.format(FinalGMPercentage);
					
				} 
				else{
					FinalGMPercentages="0.0";
				}
				subject = "Process Sent to Delivery Head for "+rateData.getCrmDealId()+" with "+Desc+".";
				mailContent = "<p>Dear "+deliveryHeadName+",</p><br>"
						+ "<p>&emsp;&emsp;"+createdby+" has submitted staffing plan in their Fund Accounting related Opp ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+Desc+". </p>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
						+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
						+ "<table> <tbody><tr><td><b>Total Efforts</b></td><td>"+EffortsPM.get(0)  +"</td></tr>"
						+"<tr><td><b>Domestic</b></td><td>"+domesticvalue.get(0)  +"</td></tr>"
						+"<tr><td><b>Deputed</b></td><td>"+deputedvalue.get(0)  +" </td></tr>"
						+"<tr><td><b>Offshore</b></td><td>"+offShorevalue.get(0)+"   "+ "</td></tr>"
						+"<tr><td><b>Consolidated Deal PM%</b></td><td>"+FinalGMPercentages+"   "+ "%</td></tr>"
						
						+"<tr><td><b>(Rate card ID) Rate card PM%</b></td>"
						+ "<td>"
						+ "<table class='tblNoBorder'><tbody>";
				        for(int j=0;j<rcIds.size();j++)
							{
				        	mailContent+= "<tr><td>("+ rcIds.get(j)+ ")<strong>-</strong> </td>" + " <td>"+ gMpercts.get(j) + " %</td></tr>"; 
							}
				        mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Onsite : Offshore Mix</b></td><td>"+onSiteVals+":" +OffshoreVals+"     "+"</td></tr>"
						+"</tbody></table>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> Process Pending with Delivery Head </strong> </u></p>"
						+ "<br><br>Regards,<br>"+createdby+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		
		
		
		
		case 2: {
			AppLoger.APPLOGGER.info("Executing case 2");
			AppLoger.APPLOGGER.info("Mail Generated for Rate Card Recycled");
			desc = "Case 3: Policy  Approved";
			to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
			to[0] = createbyEmail;
			cc = new String[1];
			cc[0] = deliveryHeadEmail;
			for(Deal rateData : TMrateCardDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				Double finalGMPercentage=rateData.getGmPercentage();
				if(finalGMPercentage!=null){
					FinalGMPercentage=finalGMPercentage;
					FinalGMPercentages=df2.format(FinalGMPercentage);
				} 
				else{
					FinalGMPercentages="0.0";
				}
				
				subject = deliveryHeadName+" has recycled rate card prepared for  "+rateData.getCrmDealId()+" with "+Desc+".";
				mailContent = "<p>Dear Team,</p><br>"
						+ "<p>&emsp;&emsp;"+deliveryHeadName+"  has recycled costing of  Opp ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+Desc+". </p>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
						+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
						+ "<table> <tbody><tr><td><b>Total Efforts</b></td><td>"+EffortsPM.get(0)  +"</td></tr>"
						+"<tr><td><b>Domestic</b></td><td>"+domesticvalue.get(0)  +"</td></tr>"
						+"<tr><td><b>Deputed</b></td><td>"+deputedvalue.get(0)  +" </td></tr>"
						+"<tr><td><b>Offshore</b></td><td>"+offShorevalue.get(0)+"   "+ "</td></tr>"
						+"<tr><td><b>Consolidated Deal PM%</b></td><td>"+FinalGMPercentages+"   "+ "%</td></tr>"
						
						+"<tr><td><b>(Rate card ID) Rate card PM%</b></td>"
						+ "<td>"
						+ "<table class='tblNoBorder'><tbody>";
				        for(int j=0;j<rcIds.size();j++)
							{
				        	mailContent+= "<tr><td>("+ rcIds.get(j)+ ")<strong>-</strong> </td>" + " <td>"+ gMpercts.get(j) + " %</td></tr>"; 
							}
				        mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Onsite : Offshore Mix</b></td><td>"+onSiteVals+":" +OffshoreVals+"     "+"</td></tr>"
						+"</tbody></table>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> Request you to revisit the costing. </strong> </u></p>"
						+ "<br><br>Regards,<br>"+deliveryHeadName+"."
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
			}
			break;
			}
		
		
		case 3: {
			AppLoger.APPLOGGER.info("Executing case 3");
			AppLoger.APPLOGGER.info("Mail Generated for Rate Card Approved");
			tempId = "Temp1";
			desc = "Case 3: Policy  Approved";
			to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
			to[0] = createbyEmail;
			cc = new String[1];
			cc[0] = deliveryHeadEmail;
			for(Deal rateData : TMrateCardDetails) {
				String startDate = dateFormat(rateData.getProjectStartDate());
				String endDate=dateFormat(rateData.getProjectEndDate());
				Double finalGMPercentage=rateData.getGmPercentage();
				if(finalGMPercentage!=null){
					FinalGMPercentage=finalGMPercentage;
					FinalGMPercentages=df2.format(FinalGMPercentage);
				}
				else{
					FinalGMPercentages="0.0";
				}
				
				subject = "Process Sent to Delivery Head for "+rateData.getCrmDealId()+" with "+Desc+" has been approved";
				mailContent = "<p>Dear Team,</p><br>"
						+ "<p>&emsp;&emsp;"+deliveryHeadName+" has approved staffing plan in their Fund Accounting related Opp ID: "+rateData.getCrmDealId()+" opportunity that is being pursued with "+Desc+". </p>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> This plan has the following estimates: -</strong> </u></p>"
						+"<br>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
						+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
						+ "<table> <tbody><tr><td><b>Total Efforts</b></td><td>"+EffortsPM.get(0)  +"</td></tr>"
						+"<tr><td><b>Domestic</b></td><td>"+domesticvalue.get(0)  +"</td></tr>"
						+"<tr><td><b>Deputed</b></td><td>"+deputedvalue.get(0)  +" </td></tr>"
						+"<tr><td><b>Offshore</b></td><td>"+offShorevalue.get(0)+"   "+ "</td></tr>"
						+"<tr><td><b>Consolidated Deal PM%</b></td><td>"+FinalGMPercentages+"   "+ "%</td></tr>"
						
						+"<tr><td><b>(Rate card ID) Rate card PM%</b></td>"
						+ "<td>"
						+ "<table class='tblNoBorder'><tbody>";
				        for(int j=0;j<rcIds.size();j++)
							{
				        	mailContent+= "<tr><td>("+ rcIds.get(j)+ ")<strong>-</strong> </td>" + " <td>"+ gMpercts.get(j) + " %</td></tr>"; 
							}
				        mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Onsite : Offshore Mix</b></td><td>"+onSiteVals+":" +OffshoreVals+"     "+"</td></tr>"
						+"</tbody></table>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> Process Completed!! </strong> </u></p>"
						+ "<br><br>Regards,<br>"+deliveryHeadName+"."
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
