package de.pflugmacher.testgame.controller.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.Animation;
import de.pflugmacher.testgame.namelists.AnimationType;

public class Explosion extends Animation {

	public Explosion(BufferedImage[] animation, int size_x, int size_y, double delay, boolean loop, Actor parent) {
		this.gp = parent.gp;
		this.delay = delay;
		this.size_x = this.size_y = (parent.size_x > parent.size_y ? parent.size_x : parent.size_y);
		this.animation = animation;
		this.animationType = AnimationType.Explosion;
	}

	public void render(Graphics2D g) {
		g.drawImage(animation[focusIndex], (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}	
}
