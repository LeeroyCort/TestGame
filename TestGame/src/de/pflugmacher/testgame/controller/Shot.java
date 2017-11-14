package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.namelists.ActorType;
import de.pflugmacher.testgame.namelists.ShotType;

public class Shot extends Actor {
	BufferedImage image;
	ShotType shotType; 
	
	public Shot(ShotType shotType, double x, double y, int size_x, int size_y, double speed, Actor parent) {
		this.isHittable = true;
		this.doesDamage = true;
		this.parent = parent;
		this.gp = new GlobalPosition(x, y);
		this.gp.step = speed;
		this.size_x = size_x;
		this.size_y = size_y;
		this.shotType = shotType;
		this.image = getImage();
		this.damage = 1;
		this.actorType = ActorType.Shot;
	}

	public void tick(double delta) {
		if (this.hitSuccsess) {
			this.isGarbadge = true;
		} else {
			this.gp.goStep(delta);
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)this.getPositionX(), (int)this.getPositionY(), this.size_x, this.size_y, null);
	}
	
	private BufferedImage getImage() {
		String imageStr = "";
		switch (this.shotType) {
		case BlueLaser:
			imageStr = "blueLaser";
			break;
		case YellowLaser:
			imageStr = "yellowLaser";
			break;
		case GreenLaser:
			imageStr = "greenLaser";
			break;
		case LightblueLaser:
			imageStr = "lightblueLaser";
			break;
		case PinkLaser:
			imageStr = "pinkLaser";
			break;
		case RedLaser:
			imageStr = "redLaser";
			break;
		}
		Random rn = new Random();
		imageStr += String.valueOf(rn.nextInt(6-1) + 1);
		return TestGame.assetController.images.get(imageStr);
	}
}
