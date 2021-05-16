package com.monty.pojo;

import java.util.List;

public class DistSession {
	private String session_id;
	private String date;
	private String available_capacity;
	private String min_age_limit;
	private String vaccine;
	private String available_capacity_dose1;
	private String available_capacity_dose2;
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAvailable_capacity() {
		return available_capacity;
	}
	public void setAvailable_capacity(String available_capacity) {
		this.available_capacity = available_capacity;
	}
	public String getMin_age_limit() {
		return min_age_limit;
	}
	public void setMin_age_limit(String min_age_limit) {
		this.min_age_limit = min_age_limit;
	}
	public String getVaccine() {
		return vaccine;
	}
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	public String getAvailable_capacity_dose1() {
		return available_capacity_dose1;
	}
	public void setAvailable_capacity_dose1(String available_capacity_dose1) {
		this.available_capacity_dose1 = available_capacity_dose1;
	}
	public String getAvailable_capacity_dose2() {
		return available_capacity_dose2;
	}
	public void setAvailable_capacity_dose2(String available_capacity_dose2) {
		this.available_capacity_dose2 = available_capacity_dose2;
	}
	@Override
	public String toString() {
		return "DistSession [session_id=" + session_id + ", date=" + date + ", available_capacity=" + available_capacity
				+ ", min_age_limit=" + min_age_limit + ", vaccine=" + vaccine + ", available_capacity_dose1="
				+ available_capacity_dose1 + ", available_capacity_dose2=" + available_capacity_dose2 + "]";
	}
	
	
	
	
	

}
