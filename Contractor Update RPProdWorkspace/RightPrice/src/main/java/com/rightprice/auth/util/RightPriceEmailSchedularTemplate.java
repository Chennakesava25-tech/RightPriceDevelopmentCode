package com.rightprice.auth.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.repository.RightPriceRepositoryImpl;

@SuppressWarnings("unused")
public class RightPriceEmailSchedularTemplate extends RightPriceRepositoryImpl{
	@SuppressWarnings("unchecked")
	public void triggerMail(List<RateCardDetails> getList,Session session,List deliverySpocName,List deliverySpocemail, List<String> cretaedByEmail){
		int m=0;
		int l=0;
		int n=0;
		for (Object object : getList) {
			RateCardDetails rcDetails = (RateCardDetails)object;
			   String desc="Schedular";
	       		String to[] = null;
				String cc[] = null;
				String subject = null;
				String mailContent = null;
	 			to = new String[1];
	 			cc = new String[1];
	 			for ( ;l<=deliverySpocemail.size();){
	 				to[0] = (String) deliverySpocemail.get(l);
	 				l++;
	 				break;
	 			}
	 			for (;m<=cretaedByEmail.size();){
	 				cc[0] = (String) cretaedByEmail.get(m);
	 				m++;
	 				break;
	 			}
	 			String deliverySpName="";
	 			EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
				String startDate = dateFormat(rcDetails.getRcStartDate());
				String endDate=dateFormat(rcDetails.getRcEndDate());
				int currencyId=rcDetails.getConsolidatedRcCurrencyId();
	 			String currencyName= getCurrencyname(currencyId);
				Integer isRenew=rcDetails.getIsRenewal();
				String isRenewal;
				if(!(isRenew==null)){
					 isRenewal = new Integer(isRenew).toString();
				
				if(isRenewal.equals("0")){
					isRenewal="NO";
				}
				else if (isRenewal.equals("1")){
					isRenewal="Yes";
				}
				}
				else {
					isRenewal="NA";
				}
				Integer oldRenewal=rcDetails.getRenewalRCId();
				String oldRenewalId;
				if(!(oldRenewal==null)){
					oldRenewalId = new Integer(oldRenewal).toString();
				}
				else {
					oldRenewalId="NA";
				}
				String isAutomate=rcDetails.getIsManualRc();
				if(isAutomate.equals("A")){
					isAutomate="Automated";
					
				}
				else if (isAutomate.equals("M")){
					isAutomate="Manual";
				}
				else if (isAutomate.equals("H")){
					isAutomate="Hybrid";
				}
				else 
	 				isAutomate="N/A";
				Double onsite = rcDetails.getExpectedOnsiteResourcePercentage();
				 Double offsite = rcDetails.getExpectedOffshoreResourcePercentage();
				// Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
				 DecimalFormat df = new DecimalFormat("#.00");
				 
				 String onsiteHrsDay="";
				 String offsiteHrsDay="";
				 Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
				 if(onsiteHrs != null) {
					 onsiteHrsDay=(df.format(onsiteHrs));
				 } else {
					 onsiteHrs = 0.00;
					onsiteHrsDay=(df.format(onsiteHrs));
				 }
				 Double oFFsiteHrs=rcDetails.getOffshoreHoursPerDay();
				 DecimalFormat decF = new DecimalFormat("#0.00");
				 if(oFFsiteHrs != null) {
					offsiteHrsDay =(decF.format(oFFsiteHrs));
				 } else {
					 oFFsiteHrs = 0.00; 
					 offsiteHrsDay =(decF.format(oFFsiteHrs));
				 }
				 
				 
				   String SummaryDetails[]=null;
				   int rcId=rcDetails.getRcId();
				    SummaryDetails=getRateCardValues(rcId,session);
				    String consolidate=SummaryDetails[0];
		       	    String consolidateGM=SummaryDetails[1];
		       	    String rightPriceGM=SummaryDetails[2];
		       	    String Difference=SummaryDetails[3];
		       	  Map<String, List<String>>  rcInformation=getRateCardInfo( rcId, session);
		       	Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
				getAllData = rcInformation;
				List<String> blendedvalues = getAllData.get("1");
				List<String> gmvalues = getAllData.get("2");
				List<String> utilMix = getAllData.get("3");
				List<String> locationlist = getAllData.get("4");
				//List<String> locationlist = getAllData.get("4");
				//List<String> cityName = getAllData.get("5");
				for ( ;n<=deliverySpocName.size();){
					deliverySpName = (String) deliverySpocName.get(n);
	 				n++;
	 				break;
	 			}
				subject = "Rate Card Id "+rcDetails.getRcId()+" of "+rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" is expiring on "+endDate+"." ;
				
				mailContent = "<p>Dear "+deliverySpName+" </p><br>"
						+ "<p>&emsp;&emsp;The rate card for  <strong> "+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> will be expiring on "+endDate+" .</p>"
						+"<br>"
						+ "<p>&emsp;&emsp;Please complete the renewal process as soon as possible. Ignore this email if it is already been done.</p>"
						+"<br>"
						+ "<p>&emsp;&emsp;<u><strong> Summary of Rate Card due to expire: </strong> </u></p>"
						+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
						+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
						+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
						+"<tr><td><b>Rate Card ID</b></td><td> "+ rcDetails.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
						+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated or Manual</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
						+"<tr><td><b>Region wise utilization</b></td>"
						+ "<td>"
						+ "<table class='tblNoBorder'><tbody>";
				        for(int i=0;i<utilMix.size();i++)
						{
							mailContent+= "<tr><td>"+ locationlist.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
						+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
						+ "<td><table class='tblNoBorder'><tbody>";
						for(int j=0;j<blendedvalues.size();j++)
						{
							mailContent+= "<tr><td>"+ locationlist.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
						+ "<table class='tblNoBorder'><tbody>";
						for(int k=0;k<blendedvalues.size();k++)
						{
							mailContent+= "<tr><td>"+ locationlist.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
						}
						mailContent+=	"</tbody></table></td></tr>"
						+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
						+"<tr><td><b>VR %</b></td><td>"+rcDetails.getVolumeDiscount()+ "</td></tr>"
						+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
						+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
						+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
						+"</tbody></table>"
						+ "<br><br>Regards,<br>GFT Team"
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		     emailDetails.setTo(to);
		     emailDetails.setCc(cc);
		      emailDetails.setSubject(subject);
		      emailDetails.setMailContent(mailContent);
			
			AppLoger.APPLOGGER.info("------------- mail -----------------");
			try {
				MailTrigger.sendMail(emailDetails);
				AppLoger.APPLOGGER.info("sent mail");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				AppLoger.APPLOGGER.info(e);
			}
			
		}
		
		
		
	}
	
	
public void triggerMail1(List<RateCardDetails> getList1,Session session,List deliverySpocName1,List deliverySpocemail1,List<String> cretaedByEmail1){
	int m=0;
	int l=0;
	int n=0;
	for (Object object : getList1) {
		RateCardDetails rcDetails = (RateCardDetails)object;
		   String desc="Schedular";
       		String to[] = null;
			String cc[] = null;
			String subject = null;
			String mailContent = null;
 			to = new String[1];
 			cc = new String[1];
 			for ( ;l<=deliverySpocemail1.size();){
 				to[0] = (String) deliverySpocemail1.get(l);
 				l++;
 				break;
 			}
 			for (;m<=cretaedByEmail1.size();){
 				cc[0] = (String) cretaedByEmail1.get(m);
 				m++;
 				break;
 			}
 			String deliverySpName="";
 			EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
			String startDate = dateFormat(rcDetails.getRcStartDate());
			String endDate=dateFormat(rcDetails.getRcEndDate());
			int currencyId=rcDetails.getConsolidatedRcCurrencyId();
 			String currencyName= getCurrencyname(currencyId);
			Integer isRenew=rcDetails.getIsRenewal();
			String isRenewal;
			if(!(isRenew==null)){
				 isRenewal = new Integer(isRenew).toString();
			
			if(isRenewal.equals("0")){
				isRenewal="NO";
			}
			else if (isRenewal.equals("1")){
				isRenewal="Yes";
			}
			}
			else {
				isRenewal="NA";
			}
			Integer oldRenewal=rcDetails.getRenewalRCId();
			String oldRenewalId;
			if(!(oldRenewal==null)){
				oldRenewalId = new Integer(oldRenewal).toString();
			}
			else {
				oldRenewalId="NA";
			}
			String isAutomate=rcDetails.getIsManualRc();
			if(isAutomate.equals("A")){
				isAutomate="Automated";
				
			}
			else if (isAutomate.equals("M")){
				isAutomate="Manual";
			}
			else if (isAutomate.equals("H")){
				isAutomate="Hybrid";
			}
			else 
 				isAutomate="N/A";
			Double onsite = rcDetails.getExpectedOnsiteResourcePercentage();
			 Double offsite = rcDetails.getExpectedOffshoreResourcePercentage();
			// Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 DecimalFormat df = new DecimalFormat("#.00");
			 
			 String onsiteHrsDay="";
			 String offsiteHrsDay="";
			 Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 if(onsiteHrs != null) {
				 onsiteHrsDay=(df.format(onsiteHrs));
			 } else {
				 onsiteHrs = 0.00;
				onsiteHrsDay=(df.format(onsiteHrs));
			 }
			 Double oFFsiteHrs=rcDetails.getOffshoreHoursPerDay();
			 DecimalFormat decF = new DecimalFormat("#0.00");
			 if(oFFsiteHrs != null) {
				offsiteHrsDay =(decF.format(oFFsiteHrs));
			 } else {
				 oFFsiteHrs = 0.00; 
				 offsiteHrsDay =(decF.format(oFFsiteHrs));
			 }
			 
			   String SummaryDetails[]=null;
			   int rcId=rcDetails.getRcId();
			    SummaryDetails=getRateCardValues(rcId,session);
			    String consolidate=SummaryDetails[0];
	       	    String consolidateGM=SummaryDetails[1];
	       	    String rightPriceGM=SummaryDetails[2];
	       	    String Difference=SummaryDetails[3];
	       	Map<String, List<String>>  rcInformation=getRateCardInfo( rcId, session);
	       	Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
			getAllData = rcInformation;
			List<String> blendedvalues = getAllData.get("1");
			List<String> gmvalues = getAllData.get("2");
			List<String> utilMix = getAllData.get("3");
			List<String> locationList = getAllData.get("4");
			/*List<String> countryName = getAllData.get("4");
			List<String> cityName = getAllData.get("5");*/
			for ( ;n<=deliverySpocName1.size();){
				deliverySpName = (String) deliverySpocName1.get(n);
 				n++;
 				break;
 			}
		
			subject = "Rate Card Id "+rcDetails.getRcId()+" of "+rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" is expiring on "+endDate+"." ;
			
			mailContent = "<p>Dear "+deliverySpName+" </p><br>"
					+ "<p>&emsp;&emsp;The rate card for  <strong> "+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> will be expiring on "+endDate+" .</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;Please complete the renewal process as soon as possible. Ignore this email if it is already been done.</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Summary of Rate Card due to expire: </strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rcDetails.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated or Manual</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
			        for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rcDetails.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
					+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+ "<br><br>Regards,<br>GFT Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		     emailDetails.setTo(to);
		     emailDetails.setCc(cc);
		      emailDetails.setSubject(subject);
		      emailDetails.setMailContent(mailContent);
			
			AppLoger.APPLOGGER.info("------------- mail -----------------");
			try {
				MailTrigger.sendMail(emailDetails);
				AppLoger.APPLOGGER.info("sent mail");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				AppLoger.APPLOGGER.info(e);
			}
			
		}
	}

public void triggerMail2(List<RateCardDetails> getList2, Session session,List deliverySpocName2,List deliverySpocemail2,List<String> cretaedByEmail2){
	int m=0;
	int l=0;
	int n=0;
	for (Object object : getList2) {
		RateCardDetails rcDetails = (RateCardDetails)object;
		   String desc="Schedular";
       		String to[] = null;
			String cc[] = null;
			String subject = null;
			String mailContent = null;
 			to = new String[1];
 			cc = new String[1];
 			for ( ;l<=deliverySpocemail2.size();){
 				to[0] = (String) deliverySpocemail2.get(l);
 				l++;
 				break;
 			}
 			for (;m<=cretaedByEmail2.size();){
 				cc[0] = (String) cretaedByEmail2.get(m);
 				m++;
 				break;
 			}
 			String deliverySpName="";
 			EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
			//String deliveryemail=getRCRequesterId(rcDetails.getDeliverySpocName());
			String startDate = dateFormat(rcDetails.getRcStartDate());
			String endDate=dateFormat(rcDetails.getRcEndDate());
			int currencyId=rcDetails.getConsolidatedRcCurrencyId();
 			String currencyName= getCurrencyname(currencyId);
			Integer isRenew=rcDetails.getIsRenewal();
			String isRenewal;
			if(!(isRenew==null)){
				 isRenewal = new Integer(isRenew).toString();
			
			if(isRenewal.equals("0")){
				isRenewal="NO";
			}
			else if (isRenewal.equals("1")){
				isRenewal="Yes";
			}
			}
			else {
				isRenewal="NA";
			}
			Integer oldRenewal=rcDetails.getRenewalRCId();
			String oldRenewalId;
			if(!(oldRenewal==null)){
				oldRenewalId = new Integer(oldRenewal).toString();
			}
			else {
				oldRenewalId="NA";
			}
			String isAutomate=rcDetails.getIsManualRc();
			if(isAutomate.equals("A")){
				isAutomate="Automated";
				
			}
			else if (isAutomate.equals("M")){
				isAutomate="Manual";
			}
			else if (isAutomate.equals("H")){
				isAutomate="Hybrid";
			}
			else 
 				isAutomate="N/A";
			Double onsite = rcDetails.getExpectedOnsiteResourcePercentage();
			 Double offsite = rcDetails.getExpectedOffshoreResourcePercentage();
			// Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 DecimalFormat df = new DecimalFormat("#.00");
			 
			 String onsiteHrsDay="";
			 String offsiteHrsDay="";
			 Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 if(onsiteHrs != null) {
				 onsiteHrsDay=(df.format(onsiteHrs));
			 } else {
				 onsiteHrs = 0.00;
				onsiteHrsDay=(df.format(onsiteHrs));
			 }
			 Double oFFsiteHrs=rcDetails.getOffshoreHoursPerDay();
			 DecimalFormat decF = new DecimalFormat("#0.00");
			 if(oFFsiteHrs != null) {
				offsiteHrsDay =(decF.format(oFFsiteHrs));
			 } else {
				 oFFsiteHrs = 0.00; 
				 offsiteHrsDay =(decF.format(oFFsiteHrs));
			 }
			 
			   String SummaryDetails[]=null;
			   int rcId=rcDetails.getRcId();
			    SummaryDetails=getRateCardValues(rcId,session);
			    String consolidate=SummaryDetails[0];
	       	    String consolidateGM=SummaryDetails[1];
	       	    String rightPriceGM=SummaryDetails[2];
	       	    String Difference=SummaryDetails[3];
	       	  Map<String, List<String>>  rcInformation=getRateCardInfo( rcId, session);
	       	Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
			getAllData = rcInformation;
			List<String> blendedvalues = getAllData.get("1");
			List<String> gmvalues = getAllData.get("2");
			List<String> utilMix = getAllData.get("3");
			List<String> locationList = getAllData.get("4");
			//List<String> countryName = getAllData.get("4");
			//List<String> cityName = getAllData.get("5");
			for ( ;n<=deliverySpocName2.size();){
				deliverySpName = (String) deliverySpocName2.get(n);
 				n++;
 				break;
 			}
           subject = "Rate Card Id "+rcDetails.getRcId()+" of "+rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" is expiring on "+endDate+"." ;
			
			mailContent = "<p>Dear "+deliverySpName+" </p><br>"
					+ "<p>&emsp;&emsp;The rate card for  <strong> "+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> will be expiring on "+endDate+" .</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;Please complete the renewal process as soon as possible. Ignore this email if it is already been done.</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Summary of Rate Card due to expire: </strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rcDetails.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated or Manual</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
			        for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rcDetails.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
					+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+ "<br><br>Regards,<br>GFT Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		     emailDetails.setTo(to);
		     emailDetails.setCc(cc);
		      emailDetails.setSubject(subject);
		      emailDetails.setMailContent(mailContent);
			
			AppLoger.APPLOGGER.info("------------- mail -----------------");
			try {
				MailTrigger.sendMail(emailDetails);
				AppLoger.APPLOGGER.info("sent mail");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				AppLoger.APPLOGGER.info(e);
			}
			
		}
		
	}
public void triggerMail3(List<RateCardDetails> getList3, Session session,List deliverySpocName3,List deliverySpocemail3,List<String> cretaedByEmail3){
	int m=0;
	int l=0;
	int n=0;
	for (Object object : getList3) {
		RateCardDetails rcDetails = (RateCardDetails)object;
		   String desc="Schedular";
       		String to[] = null;
			String cc[] = null;
			String subject = null;
			String mailContent = null;
 			to = new String[1];
 			cc = new String[1];
 			EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
			//String deliveryemail=getRCRequesterId(rcDetails.getDeliverySpocName());
 			to = new String[1];
 			cc = new String[1];
 			for ( ;l<=deliverySpocemail3.size();){
 				to[0] = (String) deliverySpocemail3.get(l);
 				l++;
 				break;
 			}
 			for (;m<=cretaedByEmail3.size();){
 				cc[0] = (String) cretaedByEmail3.get(m);
 				m++;
 				break;
 			}
 			String deliverySpName="";
			String startDate = dateFormat(rcDetails.getRcStartDate());
			String endDate=dateFormat(rcDetails.getRcEndDate());
			int currencyId=rcDetails.getConsolidatedRcCurrencyId();
 			String currencyName= getCurrencyname(currencyId);
			Integer isRenew=rcDetails.getIsRenewal();
			String isRenewal;
			if(!(isRenew==null)){
				 isRenewal = new Integer(isRenew).toString();
			
			if(isRenewal.equals("0")){
				isRenewal="NO";
			}
			else if (isRenewal.equals("1")){
				isRenewal="Yes";
			}
			}
			else {
				isRenewal="NA";
			}
			Integer oldRenewal=rcDetails.getRenewalRCId();
			String oldRenewalId;
			if(!(oldRenewal==null)){
				oldRenewalId = new Integer(oldRenewal).toString();
			}
			else {
				oldRenewalId="NA";
			}
			String isAutomate=rcDetails.getIsManualRc();
			if(isAutomate.equals("A")){
				isAutomate="Automated";
				
			}
			else if (isAutomate.equals("M")){
				isAutomate="Manual";
			}
			else if (isAutomate.equals("H")){
				isAutomate="Hybrid";
			}
			else 
 				isAutomate="N/A";
			Double onsite = rcDetails.getExpectedOnsiteResourcePercentage();
			 Double offsite = rcDetails.getExpectedOffshoreResourcePercentage();
			// Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 DecimalFormat df = new DecimalFormat("#.00");
			 
			 String onsiteHrsDay="";
			 String offsiteHrsDay="";
			 Double onsiteHrs=rcDetails.getOnsiteHoursPerDay();
			 if(onsiteHrs != null) {
				 onsiteHrsDay=(df.format(onsiteHrs));
			 } else {
				 onsiteHrs = 0.00;
				onsiteHrsDay=(df.format(onsiteHrs));
			 }
			 Double oFFsiteHrs=rcDetails.getOffshoreHoursPerDay();
			 DecimalFormat decF = new DecimalFormat("#0.00");
			 if(oFFsiteHrs != null) {
				offsiteHrsDay =(decF.format(oFFsiteHrs));
			 } else {
				 oFFsiteHrs = 0.00; 
				 offsiteHrsDay =(decF.format(oFFsiteHrs));
			 }
			 
			   String SummaryDetails[]=null;
			   int rcId=rcDetails.getRcId();
			    SummaryDetails=getRateCardValues(rcId,session);
			    String consolidate=SummaryDetails[0];
	       	    String consolidateGM=SummaryDetails[1];
	       	    String rightPriceGM=SummaryDetails[2];
	       	    String Difference=SummaryDetails[3];
	       	  Map<String, List<String>>  rcInformation=getRateCardInfo( rcId, session);
	       	Map<String, List<String>> getAllData = new HashMap<String, List<String>>();
			getAllData = rcInformation;
			List<String> blendedvalues = getAllData.get("1");
			List<String> gmvalues = getAllData.get("2");
			List<String> utilMix = getAllData.get("3");
			List<String> locationList = getAllData.get("4");
			/*List<String> countryName = getAllData.get("4");
			List<String> cityName = getAllData.get("5");*/
			for ( ;n<=deliverySpocName3.size();){
				deliverySpName = (String) deliverySpocName3.get(n);
 				n++;
 				break;
 			}
			subject = "Rate Card has been expired Id - "+rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" - "+rcDetails.getRcId()+"." ;
					mailContent = "<p>Dear "+deliverySpName+" and GFT Team </p><br>"
					+ "<p>&emsp;&emsp;The rate card for  <strong> "+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName()+" </strong> has expired.</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;Please complete the renewal process as soon as possible. Ignore this email if it is already been done.</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;<u><strong> Summary of Rate Card</strong> </u></p>"
					+"<style>table, th, td {border: 1px solid black;border-collapse: collapse;} "
					+ "table.tblNoBorder,table.tblNoBorder td{border: none !important; border-collapse: collapse !important;}</style>"
					+ "<table> <tbody><tr><td><b>Customer Name</b></td><td>"+ rcDetails.getCustomerVerticalMapping().getCustomer().getCustomerName() +"</td></tr>"
					+"<tr><td><b>Rate Card ID</b></td><td> "+ rcDetails.getRcId() +"</td></tr><tr><td><b>Renewal</b></td><td>"+ isRenewal +"</td></tr><tr><td><b>Old Rate Card Id</b></td>"
					+"<td>"+ oldRenewalId +"</td></tr><tr><td><b>Automated or Manual</b></td><td>"+ isAutomate +"</td></tr><tr><td><b>Onsite : Offshore Mix</b></td><td>" + onsite +":" + offsite +"</td></tr>"
					+"<tr><td><b>Region wise utilization</b></td>"
					+ "<td>"
					+ "<table class='tblNoBorder'><tbody>";
			        for(int i=0;i<utilMix.size();i++)
					{
						mailContent+= "<tr><td>"+ locationList.get(i)+ "<strong>-</strong> </td>" + "<td>"+ utilMix.get(i)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Onsite / Offshore Hrs./Day</b></td><td>"+onsiteHrsDay +"<strong>/</strong>"+ offsiteHrsDay+ "</td></tr>"
					+"<tr><td><b>Blended Rates (Avg. Blended Rates)</b></td>"
					+ "<td><table class='tblNoBorder'><tbody>";
					for(int j=0;j<blendedvalues.size();j++)
					{
						mailContent+= "<tr><td>"+ locationList.get(j)+ "<strong>-</strong> </td>" + "<td>"+ blendedvalues.get(j)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr><tr><td><b>PM %(Avg. PM %)</b></td><td>"
					+ "<table class='tblNoBorder'><tbody>";
					for(int k=0;k<blendedvalues.size();k++)
					{
						mailContent+= "<tr><td>"+ locationList.get(k)+ "<strong>-</strong> </td>" + "<td>"+ gmvalues.get(k)+ "</td></tr>"; 
					}
					mailContent+=	"</tbody></table></td></tr>"
					+"<tr><td><b>Consolidated PM %</b></td><td>"+consolidate+ "</td></tr>"
					+"<tr><td><b>VR %</b></td><td>"+rcDetails.getVolumeDiscount()+ "</td></tr>"
					+"<tr><td><b>Consolidated PM Post VR %</b></td><td>"+consolidateGM+ "</td></tr>"
					+"<tr><td><b>Right Price PM Post VR%</b><td>"+rightPriceGM+ "</td></tr>"
					+"<tr><td><b>Difference</b><td>"+Difference+ "</td></tr>"
					+"</tbody></table>"
					+ "<br><br>Regards,<br>GFT Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

		     emailDetails.setTo(to);
		     emailDetails.setCc(cc);
		      emailDetails.setSubject(subject);
		      emailDetails.setMailContent(mailContent);

		     emailDetails.setTo(to);
		     emailDetails.setCc(cc);
		      emailDetails.setSubject(subject);
		      emailDetails.setMailContent(mailContent);
			
			AppLoger.APPLOGGER.info("------------- mail -----------------");
			try {
				MailTrigger.sendMail(emailDetails);
				AppLoger.APPLOGGER.info("sent mail");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				AppLoger.APPLOGGER.info(e);
			}
			
		}
		
	}


public void triggerMail(int tempeleteID, String userNAme, String userEmail, int verticalId,String deliveryData[]) {
	// TODO Auto-generated method stub
	AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
	@SuppressWarnings("unchecked")
	String tempId;
	String desc;
	String[] to = null;
	String cc[] = null;
	EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
	String subject;
	String mailContent;
	String permitN="";
	int reqId=0;
//	String RightPricesuppporturl="Syndev@TATOSMAIL.COM";
	String RightPricesuppporturl="rightprice2.0_support@eviden.com";
	
		ArrayList<String> emailList = new ArrayList<String>();
		
		AppLoger.APPLOGGER.info("Executing case 1");
		tempId = "Temp1";
		desc = "Case 1: approved.";
		String DeliverHeadEmail=deliveryData[1];
		to = new String[1];
		to[0] = userEmail.toString();
		
		cc = new String[1];
		cc[0] = RightPricesuppporturl;
		subject = "Access has been revoked for Right Price 2.0";
		mailContent = "<p>Dear "+userNAme+" ,</p><br>"
				+ "<p>On account of your recent project changes, we have revoked access to Right Price 2.0. </p>"
				+"<br>"
				+" Should you have any queries, please feel free to contact us at "+RightPricesuppporturl+" "
				+ "<br><br>Regards,<br>Right Price 2.0 Support Team"
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);

		
		AppLoger.APPLOGGER.info("------------- mail -----------------");
		try {
			MailTrigger.sendMail(emailDetails);
			AppLoger.APPLOGGER.info("sent mail");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			AppLoger.APPLOGGER.info(e);
		}
		
}



public void triggerMailCRMChanges(int tempeleteID, String updatedOn,String remark,String crmid,String createdbyDeal,String DealCreatedByEmail) {
	// TODO Auto-generated method stub
	AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
	@SuppressWarnings("unchecked")
	String tempId;
	String desc;
	String[] to = null;
	String cc[] = null;
	EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
	String subject;
	String mailContent;
//	String RightPricesuppporturl="Syndev@TATOSMAIL.COM";
	String RightPricesuppporturl="rightprice2.0_support@eviden.com";
	
	String[] s = new String [2];
		s=updatedOn.split(" ");
	
		
		AppLoger.APPLOGGER.info("Executing case 1");
		tempId = "Temp1";
		desc = "Case 1: email staus Y.";
		to = new String[1];
		to[0] = DealCreatedByEmail.toString();
		
		cc = new String[1];
		cc[0] = RightPricesuppporturl;
		subject = "Recent Changes made in CRM for Deal "+crmid+".";
		mailContent = "<p>Dear "+createdbyDeal+" ,</p><br>"
				+ "<p>Below changes have been done in CRM for the deal,for the deal "+crmid+ ".</p>"
				+" The changes made are " +remark+ " on " + s[0] + " at " + s[1] + "." 
				+" Hence the deal has moved back to draft stage." 
                + "<br><br>Regards,<br>Right Price 2.0 Support Team"
				+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
		emailDetails.setTo(to);
		emailDetails.setCc(cc);
		emailDetails.setSubject(subject);
		emailDetails.setMailContent(mailContent);

		
		AppLoger.APPLOGGER.info("------------- mail -----------------");
		try {
			MailTrigger.sendMail(emailDetails);
			AppLoger.APPLOGGER.info("sent mail");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			AppLoger.APPLOGGER.info(e);
		}
		
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

	simpleDataFormat = new SimpleDateFormat("dd/MM/yyyy");
	return simpleDataFormat.format(date21);

}
}



