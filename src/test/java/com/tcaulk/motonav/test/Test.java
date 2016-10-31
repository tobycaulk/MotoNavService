package com.tcaulk.motonav.test;

import java.util.ArrayList;

import org.pmw.tinylog.Logger;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.GeoApiContext;
import com.google.maps.model.TravelMode;
import com.tcaulk.motonav.directions.model.DirectionRequest;
import com.tcaulk.motonav.directions.model.RouteContainer;
import com.tcaulk.motonav.error.exception.MNException;
import com.tcaulk.motonav.gms.GoogleMapsServiceImpl;

public class Test {

	private static final String DIRECTIONS_API_KEY = "AIzaSyD3ucofUitw_Zp-tSt31ezPuKAs9EfCAIA";
	
	public void testDirections() {
		GoogleMapsServiceImpl gms = new GoogleMapsServiceImpl();
		GeoApiContext context = null;
		try {
			context = gms.getGeoApiContext(DIRECTIONS_API_KEY);
			if(context != null) {
				DirectionRequest model = new DirectionRequest("315 Wilson Mills Road, Chardon, Ohio 44024", "Cleveland, Ohio", TravelMode.DRIVING, new ArrayList<RouteRestriction>());
				RouteContainer routes = gms.getRouteContainer(context, model);
				routes.notify();
			} else {
				Logger.error("Context is null");
			}
		} catch (MNException e) {
			Logger.error(e);
		}
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.testDirections();
	}
}
