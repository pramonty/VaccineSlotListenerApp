package com.monty.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.monty.pojo.CenterList;
import com.monty.services.HitApi;

@Component
public class HitApiImpl implements HitApi {

	
	@Autowired
	RestTemplate restTemplate;
	
	//Change the date as per your need
	@Override
	public CenterList getCenters(String districtId) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<CenterList> response = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id="+districtId+"&date=16-05-2021", HttpMethod.GET,entity,CenterList.class);
		return response.getBody();
	}
	
	

}
