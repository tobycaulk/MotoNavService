package com.tcaulk.motonav.directions.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private List<Leg> legs = new ArrayList<>();

	public Route() {
		this(new ArrayList<>());
	}
	
	public Route(List<Leg> legs) {
		this.legs = legs;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}
	
	public void addLeg(Leg leg) {
		legs.add(leg);
	}
}
