package de.pflugmacher.testgame.controller.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;

public class Shield extends Actor {

	int lifetime;
	BufferedImage image;
	int size_x;
	int size_y;
	
	public Shield(int lifetime, Actor parent) {
		this.lifetime = lifetime;
		this.parent = parent;
		this.size_x = 120;
		this.size_y = 120;
		this.image = TestGame.assetController.images.get("shield");
		this.lifePoints = 1000;
		this.isHittable = true;
		this.gp = new GlobalPosition();
	}

	public void tick(double delta) {
		if (lifetime <= 0 || this.lifePoints <= 0) {
			this.isGarbadge = true;
		}
		lifetime -= (int)delta;
		this.gp.position.x = parent.gp.position.x;
		this.gp.position.y = parent.gp.position.y;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)gp.position.x - (size_x / 2), (int)gp.position.y - (size_y / 2), size_x, size_y, null);
	}
}
