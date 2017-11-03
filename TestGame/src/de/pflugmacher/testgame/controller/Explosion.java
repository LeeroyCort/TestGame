package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.Animation;

public class Explosion extends Animation {

	public Explosion(BufferedImage[] animation, int size_x, int size_y, double delay, boolean loop, Actor parent) {
		this.x = parent.x;
		this.y = parent.y;
		this.delay = delay;
		this.size_x = this.size_y = (parent.size_x > parent.size_y ? parent.size_x : parent.size_y);
		this.animation = animation;
	}

	public void render(Graphics2D g) {
		g.drawImage(animation[focusIndex], (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}	

	public void tick(double delta) {
		next_change -= delta;
		if (next_change <= 0) {
			next_change = delay;
			focusIndex++;
			if (focusIndex == animation.length) {
				isGarbadge = true;
			}
		}
	}
}
