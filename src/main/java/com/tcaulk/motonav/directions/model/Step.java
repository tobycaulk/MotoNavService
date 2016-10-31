package com.tcaulk.motonav.directions.model;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;

public class Step {

	private Distance distance;
	private Duration duration;
	private LatLng startLocation;
	private LatLng endLocation;
	private String address;
	
	public Step(Distance distance, Duration duration, LatLng startLocation, LatLng endLocation, String address) {
		this.distance = distance;
		this.duration = duration;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.address = address;
	}

	public Distance getDistance() {
		return distance;
	}
	
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	
	public Duration getDuration() {
		return duration;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public LatLng getStartLocation() {
		return startLocation;
	}
	
	public void setStartLocation(LatLng startLocation) {
		this.startLocation = startLocation;
	}
	
	public LatLng getEndLocation() {
		return endLocation;
	}
	
	public void setEndLocation(LatLng endLocation) {
		this.endLocation = endLocation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
