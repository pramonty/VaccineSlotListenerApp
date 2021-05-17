package com.monty.app;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.monty.pojo.Center;
import com.monty.pojo.CenterList;
import com.monty.pojo.DistSession;
import com.monty.services.AudioPlayer;
import com.monty.services.HitApi;
import com.monty.services.MailSender;
import com.sendgrid.SendGrid;

@Component
public class ScheduledTask {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ScheduledTask.class);
	
	@Autowired
	SendGrid sg;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	HitApi hitApi;
	
	@Autowired
	AudioPlayer audioPlayer;
	
	//Change the fixed delay as per requirements. this is in ms
	@Scheduled(fixedDelay = 10000,initialDelay = 5000)
	public void checkAvailability() {
		
		LOGGER.info("Starting Check");
		String messageBody="";
		
		
		
        availableSlotDistrict( messageBody,"446","BBSR");
        availableSlotDistrict( messageBody,"457","CTC");
        //availableSlotDistrict( messageBody,"141","CDEL");
	}



	private void availableSlotDistrict( String messageBody,String districtId,String districtName) {
		LOGGER.info("Checking for: "+districtName);
		//ResponseEntity<CenterList> response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id="+districtId+"&date=15-05-2021", HttpMethod.GET,entity,CenterList.class);
		try {
		CenterList centerList=hitApi.getCenters(districtId);
		boolean foundSlot=false;
        LOGGER.info("The response: "+centerList.getCenters().size());
        
        
        for(Center cntr: centerList.getCenters()) {
        	for(DistSession sess:cntr.getSessions()) {
        			if(Integer.parseInt(sess.getAvailable_capacity_dose1())>1 && Integer.parseInt(sess.getMin_age_limit())==18) {
        				LOGGER.info("Available");
        				LOGGER.info(sess.toString());
        				foundSlot=true;
        				messageBody=cntr.getName()+" and date "+sess.getDate();
        				break;
        			}
        			
        			//this break is to raise alarm at the very first slot opening. Comment/Uncomment as per your wish
					/*
					 * if(foundSlot) { break; }
					 */
        		
        	}
        	
        }
		
        LOGGER.info("Scanned through all");
        if(foundSlot) {
        	//mailSender.sendMail("Available slots "+districtName, "Available for: "+messageBody);
        	LOGGER.info("Available for: "+messageBody);
        	audioPlayer.soundAlarm();
        	
       }
		}catch(Exception exp) {
			//LOGGER.info("Number Format Exception for: "+sess.toString());
			LOGGER.info("Some exception occured: ",exp);
    	}
	}
	
	

}
