package de.pflugmacher.testgame.model;

public class GlobalPosition {
	public Position position;
	public Direction direction;
	public double step;
	private int traveledPixels = 0;
	private double traveledPixelsRest = 0;
	
	public GlobalPosition() {
		position = new Position();
		direction = new Direction();
		step = 0.1;
	}

	public GlobalPosition(double x, double y) {
		position = new Position(x, y);
		direction = new Direction();
	}

	public GlobalPosition(double x, double y, double degrees) {
		position = new Position(x, y);
		direction = new Direction(degrees);
	}
	
	private void increaseTraveledPixels(double pixels) {
		int pixelsHole = (int)pixels;
		traveledPixelsRest = pixels - pixelsHole;
		pixelsHole += (int)traveledPixelsRest;
		traveledPixels += pixelsHole;
		traveledPixelsRest = traveledPixelsRest - (int)traveledPixelsRest;
	}
	
	public int getTraveledPixels() {
		return traveledPixels;
	}
	
	public void goStep(double delta) {
		position = direction.nextPositon(position, this.step * delta);
		increaseTraveledPixels(this.step * delta);
	}

	public void goStep(double step, double delta) {
		position = direction.nextPositon(position, step * delta);
		increaseTraveledPixels(step * delta);
	}
	
	public void doCurve(Position endpoint, Position controllPoint) {
		direction.doCurve(this.position, controllPoint, endpoint);
	}

	public void doCurve(Position endpoint, Position controllPoint, Position controllPoint2) {
		direction.doCurve(this.position, controllPoint, controllPoint2, endpoint);
	}
}
