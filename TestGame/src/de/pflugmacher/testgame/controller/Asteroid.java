package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.Utils;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.namelists.ActorType;
import de.pflugmacher.testgame.namelists.CollisionState;

public class Asteroid extends Actor {
	BufferedImage image;
	double dir_x;
	double dir_y;
	double speed;
	public Asteroid(double x, double y, int size_x, int size_y, double dir_x, double dir_y, double speed) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.x = x;
		this.y = y;
		image = TestGame.assetController.images.get("asteroid");
		this.isHittable = true;
		this.dir_x = dir_x;
		this.dir_y = dir_y;
		this.speed = speed;
		this.lifePoints = 50;
		this.actorType = ActorType.Asteroid;
		this.damage = 100;
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
			BufferedImage[] ani = Utils.CutImage(TestGame.assetController.images.get("explosion1"), 4, 4);
			TestGame.animations.add(new Explosion(ani, size_x , size_x, (int)50, false, this));
		}
		
		this.x += speed * delta * dir_x;
		this.y += speed * delta * dir_y;
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}
}
