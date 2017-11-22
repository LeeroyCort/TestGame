package de.pflugmacher.testgame.controller.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.controller.AudioController;
import de.pflugmacher.testgame.model.AIActor;
import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.namelists.ActorType;
import de.pflugmacher.testgame.namelists.Controls;
import de.pflugmacher.testgame.namelists.ShotType;

public class Enemy extends AIActor {
	
	BufferedImage image;
	float speedbuff;
	int shot_cooldown;

	AudioController laser;
	
	public Enemy(int size_x, int size_y) {
		this.image = TestGame.assetController.images.get("titantd");
		this.actorType = ActorType.Enemy;
		this.lifePoints = 100;
		this.gp = new GlobalPosition((TestGame.window.getWidth() / 2), 100);
		this.size_x = size_x;
		this.size_y = size_y;
		this.speedbuff = 0.4f;
		this.shot_cooldown = 0;
		this.isHittable = true;
		TestGame.animations.add(new Thruster((new int[][]{{9}, {-67}}), false, this));
		this.laser = new AudioController(TestGame.assetController.sounds.get("laser"), -20f);
	}

	public void tick(double delta) {
		if (TestGame.keyController.actions.contains(Controls.Shot) && shot_cooldown == 0) {
			TestGame.actors.add(new Shot(ShotType.GreenLaser, this.gp.position.x - 20, this.getPositionY() + size_y, -90, 20, 20, 1, this));
			TestGame.actors.add(new Shot(ShotType.GreenLaser, this.gp.position.x + 20, this.getPositionY() + size_y, -90, 20, 20, 1, this));
			laser.playSound();
			shot_cooldown = 500;
		} 

		if (shot_cooldown != 0) {
			if (shot_cooldown > 0) {
				shot_cooldown -= delta;
			} else {
				shot_cooldown = 0;
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}
}
