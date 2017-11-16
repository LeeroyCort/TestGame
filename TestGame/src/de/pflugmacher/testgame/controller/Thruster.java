package de.pflugmacher.testgame.controller;

import java.awt.Graphics2D;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.Animation;
import de.pflugmacher.testgame.namelists.Controls;

public class Thruster extends Animation{	
	public int yOffset1;
	public int yOffset2;
	public int xOffset1;
	public int xOffset2;
	public Thruster(Actor parent) {
		this.animation = TestGame.assetController.animations.get("auratd");
		this.loop = true;
		this.parent = parent;
		this.gp = parent.gp;
		this.size_x = 30;
		this.size_y = 30;
		this.delay = 100;
		this.next_change = this.delay;
		this.yOffset1 = 40;
		this.yOffset2 = 50;
		this.xOffset1 = 22;
		this.xOffset2 = 8;
	}

	public void tick(double delta) {
		if (TestGame.keyController.actions.contains(Controls.Forward)) {
			this.size_y = 70;
			this.yOffset1 = 50;
			this.yOffset2 = 60;
		} else {
			this.size_y = 30;
			this.yOffset1 = 40;
			this.yOffset2 = 50;
		}
		super.tick(delta);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(animation[focusIndex], (int)getPositionX() + xOffset1, (int)getPositionY() + yOffset1, size_x, size_y, null);
		g.drawImage(animation[focusIndex], (int)getPositionX() - xOffset1, (int)getPositionY() + yOffset1, size_x, size_y, null);
		g.drawImage(animation[focusIndex], (int)getPositionX() + xOffset2, (int)getPositionY() + yOffset2, size_x, size_y, null);
		g.drawImage(animation[focusIndex], (int)getPositionX() - xOffset2, (int)getPositionY() + yOffset2, size_x, size_y, null);
	}
}
