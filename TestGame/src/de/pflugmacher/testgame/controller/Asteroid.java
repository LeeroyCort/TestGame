package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.namelists.ActorType;
import de.pflugmacher.testgame.namelists.CollisionState;

public class Asteroid extends Actor {
	BufferedImage image;
	AudioController boom;
	public Asteroid(GlobalPosition gp, int size_x, int size_y) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.gp = gp;
		image = TestGame.assetController.images.get("asteroid");
		this.isHittable = true;
		this.lifePoints = (size_x * size_y) / 100;
		this.actorType = ActorType.Asteroid;
		this.damage = (size_x * size_y) / 100;
		this.boom = new AudioController(TestGame.assetController.sounds.get("Explosion"), -5.0f);
	}
	
	public void tick(double delta) {
		HashMap<Actor, CollisionState> collisions = CollisionController.getCollison(this);
		if (collisions.containsValue(CollisionState.Hit)) {
			if (this.lifePoints <= 0) {
				this.isGarbadge = true;
			}
		} else if (collisions.containsValue(CollisionState.CollisionInner)) {
			this.doesDamage = true;
			this.isGarbadge = this.hitSuccsess;
		}
		if (this.isGarbadge) {
			TestGame.animations.add(new Explosion(TestGame.assetController.animations.get("explosion1"), size_x , size_x, (int)50, false, this));
			this.boom.playSound();
		}

		this.gp.goStep(delta);
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}
}
