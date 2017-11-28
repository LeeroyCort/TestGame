package de.pflugmacher.testgame.model;

public class Direction {
	private double glob_degrees;
	private boolean doCurve = false;
	private Curve curve;
	private double curveLength;
	
	public Direction() {
		glob_degrees = 90.0;
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
	
	public boolean isInCurve() {
		return doCurve;
	}
	
	public Position nextPositon(Position pos, double step) {
		if (this.doCurve) {
			this.doCurve = !curve.isComplete();
			pos = curve.next(1 / (curveLength / step));
		} else {
			pos.x += Math.cos(Math.toRadians(glob_degrees * -1)) * step;
			pos.y += Math.sin(Math.toRadians(glob_degrees * -1)) * step;
		}
		return pos;
	}
	
	public void doCurve(Position start, Position controllPoint, Position end) {
		this.doCurve = true;
		this.curve = new Curve(start, controllPoint, end);
		this.curveLength = curve.getCurveLength();
	}

	public void doCurve(Position start, Position controllPoint, Position controllPoint2, Position end) {
		this.doCurve = true;
		this.curve = new Curve(start, controllPoint, controllPoint2, end);
		this.curveLength = curve.getCurveLength();
	}
}
