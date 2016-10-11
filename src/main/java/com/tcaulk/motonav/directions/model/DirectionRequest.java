package com.tcaulk.motonav.directions.model;

import java.util.List;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.model.TravelMode;

public class DirectionRequest {

	private String origin;
	private String destination;
	private TravelMode travelMode;
	private List<RouteRestriction> routeRestrictions;

	public DirectionRequest(String origin, String destination, TravelMode travelMode, List<RouteRestriction> routeRestrictions) {
		this.origin = origin;
		this.destination = destination;
		this.travelMode = travelMode;
		this.routeRestrictions = routeRestrictions;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public TravelMode getTravelMode() {
		return travelMode;
	}
	
	public void setTravelMode(TravelMode travelMode) {
		this.travelMode = travelMode;
	}

	public RouteRestriction[] getRouteRestrictions() {
		return routeRestrictions.toArray(new RouteRestriction[routeRestrictions.size()]);
	}

	public void setRouteRestrictions(List<RouteRestriction> routeRestrictions) {
		this.routeRestrictions = routeRestrictions;
	}
}
