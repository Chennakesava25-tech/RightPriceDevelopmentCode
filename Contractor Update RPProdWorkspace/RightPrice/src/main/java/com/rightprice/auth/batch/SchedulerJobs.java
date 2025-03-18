package com.rightprice.auth.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rightprice.auth.util.MailTrigger;
import com.rightprice.auth.model.EmailBeanForRightprice;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.repository.RightPriceRepositoryImpl;
import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.SkillforecastStaffingDateRange;


@Component
public class SchedulerJobs {
	
	@Autowired
	private RightPriceRepository rpr;
	
	public String getName(){
		
		AppLoger.APPLOGGER.info("calling repo");
		AppLoger.APPLOGGER.info(rpr.getCountry().getBody());
		AppLoger.APPLOGGER.info("returning current name");
		return "Syntel";
	}

		public void getDeliveryListForMailTrigger() {
			 rpr.getDeliveryListForMailTrigger();
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

	/*	public void insertInSkillForecastStaffing() {
			
				 rpr.insertInSkillForecastStaffing();
		         }*/
		
	/*	public void storeProcCRMDeal() {
			
			 rpr.storeProcCRMDeal();
	         }*/
	/*	
		public void stafficRangeRegularProc() {
			
			 rpr.stafficRangeRegularProc();
	         }
		*/

		public void updatedverticalMailTrigger() {
			
				 rpr.updatedverticalMailTrigger();
		         }
		
		public void CRMHistoryChangesEmailSchedule() {
			
			 rpr.CRMHistoryChangesEmailSchedule();
	         }
			
		}

		
		


