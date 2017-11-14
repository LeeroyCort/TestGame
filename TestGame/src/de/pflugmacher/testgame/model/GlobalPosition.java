package de.pflugmacher.testgame.model;

public class GlobalPosition {
	public Position position;
	public Direction direction;
	public double step;
	
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
	
	public void goStep(double delta) {
		position = direction.nextPositon(position, this.step * delta);
	}

	public void goStep(double step, double delta) {
		position = direction.nextPositon(position, step * delta);
	}
}
