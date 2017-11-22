package de.pflugmacher.testgame.controller.actor;

import java.awt.Graphics2D;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.Animation;

public class Thruster extends Animation{	
	public int[][] Offsets;
	private int size_bonus;
	private int offset_bonus;
	private boolean topDown;
	private double lastY;
	public Thruster(int[][] Offsets, boolean topDown, Actor parent) {
		this.topDown = topDown;
		if (topDown)
			this.animation = TestGame.assetController.animations.get("auratd");
		else
			this.animation = TestGame.assetController.animations.get("aura");
		this.loop = true;
		this.parent = parent;
		this.gp = parent.gp;
		this.lastY = gp.position.y;
		this.size_x = 30;
		this.size_y = 30;
		this.delay = 100;
		this.next_change = this.delay;
		this.Offsets = Offsets;
	}

	public void tick(double delta) {
		if (topDown ? gp.position.y < lastY : gp.position.y > lastY) {
			this.size_bonus = 40;
			this.offset_bonus = topDown ? 10 : 30;
		} else {
			this.size_bonus = 0;
			this.offset_bonus = 0;
		}
		this.lastY = gp.position.y;
		super.tick(delta);
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < Offsets[0].length; i++) {
			int xOffset = Offsets[0][i];
			int yOffset = Offsets[1][i] - offset_bonus;
			g.drawImage(animation[focusIndex], (int)getPositionX() + xOffset, (int)getPositionY() + yOffset, size_x, size_y + size_bonus, null);
			g.drawImage(animation[focusIndex], (int)getPositionX() - xOffset, (int)getPositionY() + yOffset, size_x, size_y + size_bonus, null);
		}
	}
}
