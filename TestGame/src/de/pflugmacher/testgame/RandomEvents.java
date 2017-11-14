package de.pflugmacher.testgame;

import java.util.Random;

import de.pflugmacher.testgame.controller.Asteroid;
import de.pflugmacher.testgame.model.GlobalPosition;

public class RandomEvents {
	double nextEventCounter;
	Random rn;
	public RandomEvents() {
		nextEventCounter = 1000;
		rn = new Random();
	}
	
	public void tick(double delta) {
		if (nextEventCounter <= 0) {
			castEvent();
			nextEventCounter = rn.nextInt(5000) + 500;
		}
		nextEventCounter -= delta;
	}
	
	private void castEvent() {
		double x = rn.nextInt(TestGame.window.getWidth());
		double y = 0;
		int size_x = rn.nextInt(50) + 50;
		int size_y = rn.nextInt(60) + 60;
		double degrees = x < TestGame.window.getWidth() / 2 ? rn.nextInt(60) + 30 : rn.nextInt(60) + 90;
		GlobalPosition asteroidGP = new GlobalPosition(x, y, degrees);
		asteroidGP.step = (1.001 - (((double)(size_x * size_y) - 3000) / 9000)) / 2;
		TestGame.actors.add(new Asteroid(asteroidGP, size_x, size_y));
	}
}
