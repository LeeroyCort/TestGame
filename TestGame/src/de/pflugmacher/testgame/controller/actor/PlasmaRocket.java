package de.pflugmacher.testgame.controller.actor;

import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.model.Position;
import de.pflugmacher.testgame.namelists.ActorType;

public class PlasmaRocket extends Actor {
	PlasmaRocketAni plasmarocket;
	BufferedImage[] animation;
	double distanceTraveled = 0;
	
	public PlasmaRocket(int size_x, int size_y, double curve, boolean right, Actor parent) {
		this.parent = parent;
		this.gp = new GlobalPosition(parent.gp.position.x, parent.gp.position.y - 20, right ? 45 : 135);
		this.gp.step = 0.4;
		Position endpoint = new Position(parent.gp.position.x, parent.gp.position.y - 400);
		Position controllPoint = new Position(parent.gp.position.x + (right ? 400 : -400), parent.gp.position.y - 200);
		Position controllPoint2 = new Position(parent.gp.position.x + (right ? -400 : 400), parent.gp.position.y - 300);
		this.gp.doCurve(endpoint, controllPoint, controllPoint2);
		this.size_x = size_x;
		this.size_y = size_y;
		this.doesDamage = true;
		this.damage = 50;
		this.isHittable = true;
		this.plasmarocket = new PlasmaRocketAni(TestGame.assetController.animations.get("pinkEnergyBall"), (int)size_x, (int)size_y, 50, true, this);
		TestGame.animations.add(this.plasmarocket);
		this.actorType = ActorType.PlasmaRocket;
	}
	
	public void tick(double delta) {
		if (this.hitSuccsess) {
			this.isGarbadge = true;
		} else {
			this.gp.goStep(delta);
		}
	}

}
