package de.pflugmacher.testgame;

import de.pflugmacher.testgame.controller.actor.Enemy;
import de.pflugmacher.testgame.controller.actor.Player;

public class Loader {
	public Loader() {
		Player actor = new Player(80, 80);
		TestGame.actors.add(actor);
		TestGame.actors.add(new Enemy(100, 120));
	}
}
