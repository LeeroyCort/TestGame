package de.pflugmacher.testgame.model;

public abstract class MovementTemplate {
	protected GlobalPosition gp;
	protected int traveledPixelsLast = 0;
	protected int directionChangeAfterPixels = 0;
	public MovementTemplate() {

	}
	
	public void update(double delta) {
		if (gp.direction.isInCurve()) {
			
		} else if (traveledPixelsLast == -1) {
			traveledPixelsLast = gp.getTraveledPixels();
		} else if (gp.getTraveledPixels() - traveledPixelsLast >= directionChangeAfterPixels) {
			directionChangeAfterPixels = dirChange();
			traveledPixelsLast = -1;
		}
		gp.goStep(delta);
	}
	
	protected int dirChange() {
		return 0;
	}
}
