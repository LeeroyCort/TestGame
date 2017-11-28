package de.pflugmacher.testgame.model;

public class Curve {
	public double t;
	public Position startPoint;
	public Position endPoint;
	public Position controllPoint;
	public Position controllPoint2;
	private double curveLength;
	
	public Curve(Position startPoint, Position controllPoint, Position controllPoint2, Position endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.controllPoint = controllPoint;
		this.controllPoint2 = controllPoint2;
		this.t = 0;
		this.curveLength = getLength4();
		
	}
	public Curve(Position startPoint, Position controllPoint, Position endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.controllPoint = controllPoint;
		this.t = 0;
		this.curveLength = getLength3();
	}
	
	public Position next(double step) {
		Position pos;
		if (controllPoint2 == null) {
			pos = getFrom3();
		} else {
			pos = getFrom4();
		}
		t += Math.abs(step);
		if (t > 1.0) {
			t = 1.0;
		}
		
		return pos;
	}
	
	private Position getFrom4() {

		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;
		double x = uuu * startPoint.x; //first term
		  x += 3 * uu * t * controllPoint.x; //second term
		  x += 3 * u * tt * controllPoint2.x; //third term
		  x += ttt * endPoint.x; //fourth term

		double y = uuu * startPoint.y; //first term
		  y += 3 * uu * t * controllPoint.y; //second term
		  y += 3 * u * tt * controllPoint2.y; //third term
		  y += ttt * endPoint.y; //fourth term

		return new Position(x, y);
	}

	private Position getFrom3() {
		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double x = uu * startPoint.x; //first term
		  x += 2 * u * t * controllPoint.x; //second term
		  x += tt * endPoint.x; //third term

		double y = uu * startPoint.y; //first term
		  y += 2 * u * t * controllPoint.y; //second term
		  y += tt * endPoint.y; //third term

		return new Position(x, y);
	}
	
	private double getLength3() {
		double result = 0;
		Position last = startPoint;
		for (double i = 0; i <= 1; i+=0.001) {
			this.t = i;
			Position now;
			now = getFrom3();
			result += Math.sqrt(Math.exp(last.x - now.x) + Math.exp(last.y - now.y));
			last = now;
		}
		this.t = 0;
		return result;
	}

	private double getLength4() {
		double result = 0;
		Position last = startPoint;
		for (double i = 0; i <= 1; i+=0.001) {
			this.t = i;
			Position now;
			now = getFrom4();
			result += Math.sqrt(Math.exp(last.x - now.x) + Math.exp(last.y - now.y));
			last = now;
		}
		this.t = 0;
		return result;
	}
	
	public boolean isComplete() {
		return t == 1;
	}
	
	public double getCurveLength() {
		return curveLength;
	}
	
	public void resetToStart() {
		this.t = 0;
	}
}
