package com.rightprice.auth.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rightprice.auth.model.SkillforecastStaffingDateRange;
import com.rightprice.auth.util.AppLoger;


@Component
public class ScheduledTasks {
	
	
	@Autowired
	private SchedulerJobs schedulerJobsList;
	
	
/*	
    public RightPriceRepositoryImpl getRightPriceRepositoryImpl() {
		return rightPriceRepositoryImpl;
	}
    
    @Autowired
	public void setRightPriceRepositoryImpl(RightPriceRepositoryImpl rightPriceRepositoryImpl) {
		this.rightPriceRepositoryImpl = rightPriceRepositoryImpl;
	}

*/
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    
    
    @Scheduled(fixedRate = 24*60*60*1000)
    public void reportCurrentTime() {
    	AppLoger.APPLOGGER.info("--------------------------------------");
    	AppLoger.APPLOGGER.info("The time is now "+ dateFormat.format(new Date()));
    	String fixedRate= "5000";
    	//AppLoger.APPLOGGER.info(schedulerJobsList.getName());
    	AppLoger.APPLOGGER.info("This is scheduled event with fixed rate of {} ms"+ fixedRate);
    	
	    }
    
    @Scheduled(cron = "0 0 6 * * *")
    public void reportCurrentTCron() {
    	AppLoger.APPLOGGER.info("The Cron is now scheduled for everyday at 6 AM  and curr time is {} "+ dateFormat.format(new Date()));
    	schedulerJobsList.getDeliveryListForMailTrigger();
    }
    
    
  /*  @Scheduled(cron = "0 0 8 * * *")
    public void InsertCron() {
    	AppLoger.APPLOGGER.info("The Cron is scheduled to insert at every night at 12 hour and curr time is {} "+ dateFormat.format(new Date()));
    	schedulerJobsList.updatedverticalMailTrigger();
    }*/
    
   @Scheduled(cron = "0 0/10 * * * ?")
    public void InsertCron1() {
    	AppLoger.APPLOGGER.info("The Cron is scheduled to insert at every night at 12 hour and curr time is {} "+ dateFormat.format(new Date()));
    	schedulerJobsList.CRMHistoryChangesEmailSchedule();
    }
    
}