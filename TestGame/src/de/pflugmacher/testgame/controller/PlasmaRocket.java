package de.pflugmacher.testgame.controller;

import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.Utils;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;

public class PlasmaRocket extends Actor {
	PlasmaRocketAni plasmarocket;
	BufferedImage[] animation;
	double distanceTraveled = 0;
	
	public PlasmaRocket(int size_x, int size_y, double curve, boolean right, Actor parent) {
		this.parent = parent;
		this.gp = new GlobalPosition(parent.gp.position.x, parent.gp.position.y - 20, right ? 45 : 135);
		this.gp.step = 0.4;
		this.gp.direction.doCurve(curve, 400);
		this.size_x = size_x;
		this.size_y = size_y;
		this.doesDamage = true;
		this.damage = 50;
		this.isHittable = true;
		animation = Utils.CutImage(TestGame.assetController.images.get("pinkEnergyBall"), 4, 8);
		this.plasmarocket = new PlasmaRocketAni(animation, (int)size_x, (int)size_y, 50, true, this);
		TestGame.animations.add(this.plasmarocket);
	}
	
	public void tick(double delta) {
		if (this.hitSuccsess) {
			this.isGarbadge = true;
		} else {
			this.gp.goStep(delta);
		}
	}

}
