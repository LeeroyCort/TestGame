package de.pflugmacher.testgame.controller;

import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.Utils;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.model.Position;

public class PlasmaRocket extends Actor {
	PlasmaRocketAni plasmarocket;
	BufferedImage[] animation;
	double distanceTraveled = 0;
	public PlasmaRocket(double size_x, double size_y, Position pos) {
		this.gp = new GlobalPosition(pos.x, pos.y);
		animation = Utils.CutImage(TestGame.assetController.images.get("pinkEnergyBall"), 8, 4);
		this.plasmarocket = new PlasmaRocketAni(animation, (int)size_x, (int)size_y, 50, true, this);
		TestGame.animations.add(this.plasmarocket);
		this.gp.step = 0.2;
	}
	
	public void tick(double delta) {
		if (this.hitSuccsess) {
			this.isGarbadge = true;
		} else {
			this.gp.direction.changeDegreesFromCurrent(0.01);
			this.gp.goStep(delta);
		}
	}

}
