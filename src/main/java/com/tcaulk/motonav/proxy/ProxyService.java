package com.tcaulk.motonav.proxy;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.GeoApiContext;
import com.google.maps.model.TravelMode;
import com.tcaulk.motonav.error.MNError;
import com.tcaulk.motonav.error.exception.MNException;
import com.tcaulk.motonav.gms.GoogleMapsServiceImpl;
import com.tcaulk.motonav.proxy.command.ProxyCommand;

public class ProxyService {

	private static final String DIRECTIONS_API_KEY = "AIzaSyD3ucofUitw_Zp-tSt31ezPuKAs9EfCAIA";
	
	private GoogleMapsServiceImpl gms = new GoogleMapsServiceImpl();
	private GeoApiContext context = null;
	
	private String destination;
	private TravelMode travelMode;
	private List<RouteRestriction> routeRestrictions = new ArrayList<>();
	
	public ProxyService() throws MNException {
		context = gms.getGeoApiContext(DIRECTIONS_API_KEY);
	}
	
	public void setInitialRoute(String destination, TravelMode travelMode, List<RouteRestriction> routeRestrictions) {
		this.destination = destination;
		this.travelMode = travelMode;
		this.routeRestrictions.addAll(routeRestrictions);
	}

	public ProxyCommand update(String currentLocation) {
		ProxyCommand proxyCommand = null;
		
		
		
		return proxyCommand;
	}
}
