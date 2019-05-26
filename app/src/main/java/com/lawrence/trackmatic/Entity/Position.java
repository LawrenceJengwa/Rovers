package com.lawrence.trackmatic.Entity;

import com.lawrence.trackmatic.enums.Orientation;


public class Position {

	private Coordinate coordinate;
	private Orientation orientation;

	public Position(Coordinate coordinate, Orientation orientation) {
		
		if (coordinate == null)
			throw new IllegalArgumentException("Coordinate cannot be null!");
		
		if (orientation == null)
			throw new IllegalArgumentException("Orientation cannot be null!");
		
		this.coordinate = coordinate;
		this.orientation = orientation;
	}


	public void turnRight() {
		orientation = orientation.nextRight();
	}


	public void turnLeft() {
		orientation = orientation.nextLeft();
	}


	public void goForward() {
		orientation.goForward(coordinate);
	}


	public String toString() {
		return coordinate.getX() + " " + coordinate.getY() + " " + orientation;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Orientation getOrientation() {
		return orientation;
	}
}
