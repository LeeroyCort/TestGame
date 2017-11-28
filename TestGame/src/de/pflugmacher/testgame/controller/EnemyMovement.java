package de.pflugmacher.testgame.controller;

import java.util.Random;

import de.pflugmacher.testgame.model.GlobalPosition;
import de.pflugmacher.testgame.model.MovementTemplate;

public class EnemyMovement extends MovementTemplate {
	Random rn;
	public EnemyMovement(GlobalPosition gp) {
		this.gp = gp;
		this.rn = new Random();
	}
	
	public int dirChange() {
		return  0;
	}
}
