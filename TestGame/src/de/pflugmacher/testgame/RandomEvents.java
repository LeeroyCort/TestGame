package de.pflugmacher.testgame;

import java.util.Random;

import de.pflugmacher.testgame.controller.Asteroid;

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
			nextEventCounter = rn.nextInt(20) + 500;
		}
		nextEventCounter -= delta;
	}
	
	private void castEvent() {
		double x = rn.nextInt(TestGame.window.getWidth());
		double y = 0;
		int size_x = rn.nextInt(50) + 50;
		int size_y = rn.nextInt(60) + 60;
		double dir_x = y < TestGame.window.getWidth() / 2 ? rn.nextDouble() : rn.nextDouble() * -1 ;
		double dir_y = rn.nextDouble();
		double speed = rn.nextDouble() - 0.2;
		TestGame.actors.add(new Asteroid(x, y, size_x, size_y, dir_x, dir_y, speed));
	}
}
