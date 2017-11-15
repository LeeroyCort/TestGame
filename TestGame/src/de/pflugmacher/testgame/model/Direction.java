package de.pflugmacher.testgame.model;

public class Direction {
	private double glob_degrees;
	private double traveledPixels;
	private double path_length;
	private boolean doCurve = false;
	private double curveAngle;
	
	public Direction() {
		glob_degrees = 90.0;
	}
	
	public Direction(double degrees) {
		glob_degrees = (degrees % 360) * -1;
	}
	
	public void changeDegreesFromCurrent(double degrees, boolean right) {
		if (right) {
			glob_degrees -= (degrees % 360) * -1;
		} else  {
			glob_degrees += (degrees % 360) * -1;
		}		
	}
	
	public void changeDegreesFromCurrent(double degrees) {
		glob_degrees += (degrees % 360) * -1;
	}

	public void changeDegreesFromGlobal(double degrees) {
		glob_degrees = (degrees % 360) * -1;
	}
	
	public double getDegrees() {
		return glob_degrees;
	}

	public double getRadian() {
		return Math.toRadians(glob_degrees);
	}
	
	public Position nextPositon(Position pos, double step) {
		if (this.doCurve) {
			if (traveledPixels >= path_length) {
				this.doCurve = false;
			} else {
				this.traveledPixels += step;
				changeDegreesFromCurrent(curveAngle / (path_length / step));
			}
		}
		pos.x += Math.cos(Math.toRadians(glob_degrees)) * step;
		pos.y += Math.sin(Math.toRadians(glob_degrees)) * step;
		return pos;
	}
	
	public void doCurve(double degrees, double path_length) {
		this.doCurve = true;
		this.traveledPixels = 0;
		this.path_length = path_length;
		this.curveAngle = degrees;
	}
}
