package de.pflugmacher.testgame.model;

public class Direction {
	private double glob_degrees;
	
	public Direction() {
		glob_degrees = -90.0;
	}
	
	public Direction(double degrees) {
		glob_degrees = degrees % 360;
	}
	
	public void changeDegreesFromCurrent(double degrees, boolean right) {
		if (right) {
			glob_degrees -= degrees % 360;
		} else  {
			glob_degrees += degrees % 360;
		}		
	}
	
	public void changeDegreesFromCurrent(double degrees) {
		glob_degrees += degrees % 360;
	}

	public void changeDegreesFromGlobal(double degrees) {
		glob_degrees = degrees % 360;
	}
	
	public double getDegrees() {
		return glob_degrees;
	}

	public double getRadian() {
		return Math.toRadians(glob_degrees);
	}
	
	public Position nextPositon(Position pos, double step) {
		pos.x += Math.cos(Math.toRadians(glob_degrees)) * step;
		pos.y += Math.sin(Math.toRadians(glob_degrees)) * step;
		return pos;
	}
}
