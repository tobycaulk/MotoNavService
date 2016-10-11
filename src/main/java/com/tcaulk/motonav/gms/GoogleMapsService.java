package com.tcaulk.motonav.gms;

import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.tcaulk.motonav.directions.model.DirectionRequest;
import com.tcaulk.motonav.directions.model.RouteContainer;
import com.tcaulk.motonav.error.exception.MNException;

public interface GoogleMapsService {

	public GeoApiContext getGeoApiContext(String apiKey) throws MNException;
	public DirectionsResult getDirectionsResult(GeoApiContext context, DirectionRequest directionModel) throws MNException;
	public RouteContainer getRouteContainer(GeoApiContext context, DirectionRequest directionModel) throws MNException;
	
}
