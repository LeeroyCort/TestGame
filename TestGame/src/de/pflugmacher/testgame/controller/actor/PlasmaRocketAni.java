package de.pflugmacher.testgame.controller.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.Animation;

public class PlasmaRocketAni extends Animation {
	
	public PlasmaRocketAni(BufferedImage[] animation, int size_x, int size_y, double delay, boolean loop, Actor parent) {
		this.gp = parent.gp;
		this.delay = delay;
		this.size_x = parent.size_x;
		this.size_y = parent.size_y;
		this.animation = animation;
		this.loop = loop;
		this.parent = parent;
	}

	public void render(Graphics2D g) {
		g.drawImage(animation[focusIndex], (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}
	
	public void tick(double delta) {
		if (parent.isGarbadge) {
			this.isGarbadge = true;
		}
		super.tick(delta);
	}
}
