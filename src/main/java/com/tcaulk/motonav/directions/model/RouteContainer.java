package com.tcaulk.motonav.directions.model;

import java.util.ArrayList;
import java.util.List;

public class RouteContainer {

	List<Route> routes = new ArrayList<>();
	
	public List<Route> getRoutes() {
		return routes;
	}
	
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	public void addRoute(Route route) {
		routes.add(route);
	}
}
