package com.tcaulk.motonav.gms;

import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.GeocodingResult;
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
		try {
			//Parse Route
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
						//Start is the current street you're on
						LatLng stepStartLocation = directionsStep.startLocation;
						GeocodingResult[] geocodingResult = GeocodingApi.newRequest(context).latlng(stepStartLocation).await();
						String streetName = geocodingResult[0].addressComponents[1].longName;
						String town = geocodingResult[0].addressComponents[2].longName;
						String state = geocodingResult[0].addressComponents[4].longName;
						String zipCode = geocodingResult[0].addressComponents[6].longName;
						String country = geocodingResult[0].addressComponents[5].longName;
						String address = streetName + ", " + town + ", " + state + " " + zipCode + ", " + country;
						//End is the next street you're going to be on
						LatLng stepEndLocation = directionsStep.endLocation;
						Step step = new Step(stepDistance, stepDuration, stepStartLocation, stepEndLocation, address);
						steps.add(step);
					}
					Leg leg = new Leg(legDistance, legDuration, legStartLocation, legEndLocation, steps);
					route.addLeg(leg);
					routeContainer.addRoute(route);
				}
			}
		} catch(Exception e) {
			throw new MNException(MNError.INVALID_GMS_DIRECTIONS);
		}
		
		return routeContainer;
	}
}
