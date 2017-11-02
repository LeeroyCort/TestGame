package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.namelists.ActorType;
import de.pflugmacher.testgame.namelists.CollisionState;
import de.pflugmacher.testgame.namelists.Controls;
import de.pflugmacher.testgame.namelists.ShotType;

public class Player extends Actor {
	
	BufferedImage image;
	float speedbuff;
	int shield_cooldown;
	int shield_running;
	int shot_cooldown;
	Shield shield;
	
	public Player(int size_x, int size_y) {
		this.image = TestGame.assetController.images.get("ship");
		this.size_x = size_x;
		this.size_y = size_y;
		this.x = (int)(TestGame.window.getWidth() / 2);
		this.y = TestGame.window.getHeight() - (size_y + 20);
		this.speedbuff = 1.0f;
		this.shield_cooldown = 0;
		this.shot_cooldown = 0;
		this.isHittable = true;
		this.actorType = ActorType.Player;
	}

	@Override
	public void tick(double delta) {
		HashMap<Actor, CollisionState> collisions = CollisionController.getCollison(this);
		
		if (TestGame.keyController.actions.contains(Controls.Forward) && this.y >= size_y / 2 && !collisions.containsValue(CollisionState.CollisionTop)) {
			this.y -= 0.6 * delta * speedbuff;
		}
		
		if (TestGame.keyController.actions.contains(Controls.Backward) && this.y <= (TestGame.window.getHeight() - (size_y / 2)) && !collisions.containsValue(CollisionState.CollisionDown)) {
			this.y += 0.6 * delta * speedbuff;
		}	
		
		if (TestGame.keyController.actions.contains(Controls.Left) && this.x >= size_x / 2 && !collisions.containsValue(CollisionState.CollisionLeft)) {
			this.x -= 0.6 * delta * speedbuff;
		}	
		
		if (TestGame.keyController.actions.contains(Controls.Right) && this.x <= (TestGame.window.getWidth() - (size_x / 2)) && !collisions.containsValue(CollisionState.CollisionRight)) {
			this.x += 0.6 * delta * speedbuff;
		}
		
		if (TestGame.keyController.actions.contains(Controls.Shield) && shield_cooldown == 0) {
			TestGame.actors.add(new Shield(5000, this));
			shield_cooldown = 10000;
		}

		if (TestGame.keyController.actions.contains(Controls.Shot) && shot_cooldown == 0) {
			TestGame.actors.add(new Shot(ShotType.BlueLaser, this.x - 20, this.getPositionY(), 20, 20, 1, this));
			TestGame.actors.add(new Shot(ShotType.BlueLaser, this.x + 20, this.getPositionY(), 20, 20, 1, this));
			TestGame.actors.add(new Shot(ShotType.RedLaser, this.x, this.getPositionY(), 20, 30, 1, this));
			shot_cooldown = 30;
		}
		
		if (shield_cooldown != 0) {
			if (shield_cooldown > 0) {
				shield_cooldown -= delta;
			} else {
				shield_cooldown = 0;
			}
		}
		if (shot_cooldown != 0) {
			if (shot_cooldown > 0) {
				shot_cooldown -= delta;
			} else {
				shot_cooldown = 0;
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, (int)getPositionX(), (int)getPositionY(), size_x, size_y, null);
	}
}
