package de.pflugmacher.testgame.model;

import java.awt.Graphics2D;

import de.pflugmacher.testgame.namelists.ActorType;

public abstract class Actor {
	public GlobalPosition gp;
	public int size_x;
	public int size_y;
	public boolean isGarbadge = false;
	public boolean isHittable = false;
	public Actor parent = null;
	public boolean doesDamage = false;
	public boolean hitSuccsess = false;
	public double lifePoints;
	public double damage;
	public ActorType actorType;
	
	public void tick(double delta) {
			
	}
	
	public void render(Graphics2D g) {
		
	}
	
	public double getPositionX() {
		return gp.position.x - (size_x / 2);
	}
	
	public double getPositionY() {
		return gp.position.y - (size_y / 2);
	}
}
