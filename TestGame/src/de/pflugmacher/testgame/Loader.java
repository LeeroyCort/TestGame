package de.pflugmacher.testgame;

import de.pflugmacher.testgame.controller.Player;

public class Loader {
	public Loader() {
		Player actor = new Player(80, 80);
		TestGame.actors.add(actor);
	}
}
