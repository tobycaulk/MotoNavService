package com.tcaulk.motonav.directions.model;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;

public class Leg {

	private Distance distance;
	private Duration duration;
	private LatLng startLocation;
	private LatLng endLocation;
	private List<Step> steps = new ArrayList<>();
	
	public Leg(Distance distance, Duration duration, LatLng startLocation, LatLng endLocation, List<Step> steps) {
		this.distance = distance;
		this.duration = duration;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.steps = steps;
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

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}
