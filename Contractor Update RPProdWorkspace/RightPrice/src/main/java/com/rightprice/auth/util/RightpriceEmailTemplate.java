package com.rightprice.auth.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.model.RateCardDetails;

public class RightpriceEmailTemplate {

	public void triggerMail(int templateID,List<RateCardDetails> rateCardDetails,String syntelEmailId, String currentApprovalEmailId) {
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info(" triggerMail : ENTRY :");
		@SuppressWarnings("unchecked")
		String tempId;
		String desc;
		String[] to = null;
		String[] cc = null;
		String subject;
		String mailContent;
		EmailBeanForRightprice emailDetails = new EmailBeanForRightprice();
		//String approvalLink ="https://10.128.10.231/InsurancePortal/login";
		switch (templateID) {
		case 1: {
			AppLoger.APPLOGGER.info("Executing case 1");
			AppLoger.APPLOGGER.info("Mail Generated for Rate Card Approved");
			tempId = "Temp1";
			desc = "Case 1: Policy  Approved";
			to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
//			to[0] = "syntranet_dev@tsyntelorg.com";
			to[0] =  currentApprovalEmailId.toString();
			cc = new String[1];
//			cc[0] = "syntranet_dev@tsyntelorg.com";
			cc[0] = syntelEmailId.toString();
			for(RateCardDetails rateData : rateCardDetails) {
			subject = "Rate card Approved Successfully";
			mailContent = "<p>Hi,</p><br>"
					+ "<p>&emsp;&emsp;Following rate card is Approved successfully. </p>"
					+"<br>"
					+ "<table border= 1px> <thead>"
					+"<tr><th bgcolor=green><b>Approved Rate Card Id is</b></th></tr></thead><tbody>"
					+"<tr><td>"+rateData.getRcId()+"</td></tr></tbody>"
					+ "</table>"
					+ "<br><br>Regards,<br>Admin Team"
					+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";
			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent); 
			}
			break;
			}
		case 2: {
			AppLoger.APPLOGGER.info("Executing case 1");
			AppLoger.APPLOGGER.info("Mail Generated for Rate Card Rejection");
			tempId = "Temp2";
			desc = "Case 2: Policy  rejected";
			to = new String[1];
			//to[0] = emailList.get(1).emailId.toString();
//			to[0] = "syntranet_dev@tsyntelorg.com";
			to[0] =  currentApprovalEmailId.toString();
			cc = new String[1];
//			cc[0] = "syntranet_dev@tsyntelorg.com";
			cc[0] = syntelEmailId.toString();
			for(RateCardDetails rateData : rateCardDetails) {
			subject = "Rate card is rejected";
			mailContent = "<p>Hi,</p><br>"
					+ "<p>&emsp;&emsp;Following rate card is Rejected. </p>"
					+"<br>"
					+ "<table border= 1px> <thead>"
					+"<tr><th bgcolor=green><b>Rejected Rate Card Id is</b></th></tr></thead><tbody>"
					+"<tr><td>"+rateData.getRcId()+"</td></tr></tbody>"
					+ "</table>"
					+ "<br><br>Regards,<br>Admin Team"
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
			AppLoger.APPLOGGER.info("Mail Generated for Deactivation Of Rate Card");
			tempId = "Temp3";
			desc = "Case 3: Rate Card is Deactivated";
			to = new String[1];
//			to[0] = "syntranet_dev@tsyntelorg.com";
			to[0] =  currentApprovalEmailId.toString();
			cc = new String[1];
//			cc[0] = "syntranet_dev@tsyntelorg.com";
			cc[0] = currentApprovalEmailId.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				String startDate = dateFormat(rateData.getRcStartDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				subject = "Rate card has been deactivated - "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"- "+rateData.getRcId();
				
				mailContent = "<p>Dear "+rateData.getDeliverySpocName()+" and GFT Team,</p><br>"
						+ "<p>&emsp;&emsp;The rate card for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" has been deactivated .</p>"
						+"<br>"
						+ "<p>&emsp;&emsp;Rate Card was applicable for the period "+startDate+" to "+endDate+".</p>"
						+ "<table border= 1px> <thead>"
						+"<tr><th bgcolor=green><b>Rate Card ID.</b></th><th bgcolor=green><b>Rate Card Name</b></th></tr></thead><tbody>"
						+"<tr><td>"+rateData.getRcId()+ "</td><td>"+rateData.getRcName()+ "</td></tr>"
						+ "</table>"
						+ "<br><br>Regards,<br>Team"
						+ "<br><br><br><hr>*Note-Please do not respond to this e-mail, as this mail-box is for outbound messages only.";

			emailDetails.setTo(to);
			emailDetails.setCc(cc);
			emailDetails.setSubject(subject);
			emailDetails.setMailContent(mailContent);
				}
			break;
			}
		case 4: {
			AppLoger.APPLOGGER.info("Executing case 4");
			AppLoger.APPLOGGER.info("Mail Generated for Extension Of Rate Card");
			tempId = "Temp4";
			desc = "Case 4: Rate Card is Extended";
			to = new String[1];
//			to[0] = "syntranet_dev@tsyntelorg.com";
			to[0] =  currentApprovalEmailId.toString();
			cc = new String[1];
//			cc[0] = "syntranet_dev@tsyntelorg.com";
			cc[0] = currentApprovalEmailId.toString();
			for(RateCardDetails rateData : rateCardDetails) {
				String expectedEndDate = dateFormat(rateData.getExpectedRCEndDate());
				String endDate=dateFormat(rateData.getRcEndDate());
				subject = "Rate Card has been extended until "+expectedEndDate+" "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+"-"+rateData.getRcId();
				
			mailContent = "<p>Dear "+rateData.getDeliverySpocName()+" and GFT Team,</p><br>"
					+ "<p>&emsp;&emsp;The rate card for "+rateData.getCustomerVerticalMapping().getCustomer().getCustomerName()+" has been extended until "+ expectedEndDate+".</p>"
					+"<br>"
					+ "<p>&emsp;&emsp;End Date of the rate card earlier was "+endDate +" .</p>"
					+ "<table border= 1px> <thead>"
					+"<tr><th bgcolor=green><b>Rate Card ID.</b></th><th bgcolor=green><b>Rate Card Name</b></th><th bgcolor=green><b>Expected End Date</b></th></tr></thead><tbody>"
					+"<tr><td>"+rateData.getRcId()+ "</td><td>"+rateData.getRcName()+ "</td>"
					+"<td>"+expectedEndDate+ "</td></tr>"
					+ "</table>"
					+ "<br><br>Regards,<br>Team"
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
