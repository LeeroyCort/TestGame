package de.pflugmacher.testgame.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Animation {
	public BufferedImage[] animation;
	public GlobalPosition gp;
	public int size_x;
	public int size_y;
	public double delay;
	public double next_change;
	public Actor parent;
	public boolean loop = false;
	public boolean isGarbadge = false;
	public int focusIndex = 0;
	
	public void tick(double delta) {
		
	}

	public void render(Graphics2D g) {
		
	}

	public double getPositionX() {
		return gp.position.x - (size_x / 2);
	}
	
	public double getPositionY() {
		return gp.position.y - (size_y / 2);
	}
}
