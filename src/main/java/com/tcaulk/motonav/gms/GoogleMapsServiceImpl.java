package com.tcaulk.motonav.gms;

import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.tcaulk.motonav.directions.model.DirectionRequest;
import com.tcaulk.motonav.directions.model.Leg;
import com.tcaulk.motonav.directions.model.Route;
import com.tcaulk.motonav.directions.model.RouteContainer;
import com.tcaulk.motonav.directions.model.Step;
import com.tcaulk.motonav.error.MNError;
import com.tcaulk.motonav.error.exception.MNException;
import com.tcaulk.motonav.util.Util;

public class GoogleMapsServiceImpl implements GoogleMapsService {

	@Override
	public GeoApiContext getGeoApiContext(String apiKey) throws MNException {
		Logger.info("Retrieving GeoApiContext");
		GeoApiContext geoApiContext = new GeoApiContext().setApiKey(apiKey);
		if(geoApiContext == null) {
			throw new MNException(MNError.INVALID_GMS_CONTEXT);
		}

		return geoApiContext;
	}

	@Override
	public DirectionsResult getDirectionsResult(GeoApiContext context, DirectionRequest directionModel) throws MNException {
		Logger.info("Retrieving directions from origin [" + directionModel.getOrigin() + "] to "
				+ " destination [" + directionModel.getDestination() + "]"
				+ " travel mode [" + directionModel.getTravelMode().toString() + "]"
				+ " route restrictions [" + Util.arrayToList(directionModel.getRouteRestrictions()) + "]");
		
		DirectionsResult result = null;
		try {
			result = DirectionsApi.newRequest(context)
				.mode(directionModel.getTravelMode())
				.avoid(directionModel.getRouteRestrictions())
				.origin(directionModel.getOrigin())
				.destination(directionModel.getDestination())
				.await();
		} catch (Exception e) {
			throw new MNException(MNError.COULD_NOT_RETRIEVE_DIRECTIONS_FROM_GOOGLE);
		}
		
		return result;
	}

	@Override
	public RouteContainer getRouteContainer(GeoApiContext context, DirectionRequest directionModel) throws MNException {
		RouteContainer routeContainer = new RouteContainer();
		
		DirectionsResult result = getDirectionsResult(context, directionModel);
		//Parse Route
		if(result != null) {
			for(DirectionsRoute directionsRoute : result.routes) {
				Route route = new Route();
				for(DirectionsLeg directionsLeg : directionsRoute.legs) {
					Distance legDistance = directionsLeg.distance;
					Duration legDuration = directionsLeg.duration;
					LatLng legStartLocation = directionsLeg.startLocation;
					LatLng legEndLocation = directionsLeg.endLocation;
					List<Step> steps = new ArrayList<>();
					for(DirectionsStep directionsStep : directionsLeg.steps) {
						Distance stepDistance = directionsStep.distance;
						Duration stepDuration = directionsStep.duration;
						LatLng stepStartLocation = directionsStep.startLocation;
						LatLng stepEndLocation = directionsStep.endLocation;
						Step step = new Step(stepDistance, stepDuration, stepStartLocation, stepEndLocation);
						steps.add(step);
					}
					Leg leg = new Leg(legDistance, legDuration, legStartLocation, legEndLocation, steps);
					route.addLeg(leg);
					routeContainer.addRoute(route);
				}
			}
		} else {
			throw new MNException(MNError.INVALID_GMS_DIRECTIONS);
		}
		
		return routeContainer;
	}
}
