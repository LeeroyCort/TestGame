package de.pflugmacher.testgame.controller;

import java.util.HashMap;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.namelists.CollisionState;

public class CollisionController {	
	public static HashMap<Actor, CollisionState> getCollison(Actor actor) {
		HashMap<Actor, CollisionState> result = new HashMap<Actor, CollisionState>();
		if (!actor.isHittable) 
			return result;
		for (Actor other: TestGame.actors) {
			boolean same_parent = (actor.parent == other.parent && (actor.parent != null || other.parent != null));
			boolean is_parent = other.parent == actor || actor.parent == other;
			boolean is_itself = other == actor;
			if (other.isHittable && !same_parent && !is_parent && !is_itself) {
				CollisionState state = CollisionState.None;
				if (innerCollision(actor, other)) {
					state = CollisionState.CollisionInner;
				} else if (topCollision(actor, other)) {
					state = CollisionState.CollisionTop;
				} else if (downCollision(actor, other)) {
					state = CollisionState.CollisionDown;
				} else if (leftCollision(actor, other)) {
					state = CollisionState.CollisionLeft;
				} else if (rightCollision(actor, other)) {
					state = CollisionState.CollisionRight;
				}
				if (state != CollisionState.None) {
					if (other.doesDamage) {
						state = CollisionState.Hit;
						other.hitSuccsess = true;
						actor.lifePoints -= other.damage;
					}
					result.put(other, state);
				}
			}
		}
		return result;
	}
	
	private static boolean topCollision(Actor actor, Actor other) {
		boolean yNear = Math.abs(actor.getPositionY() - (other.getPositionY() + other.size_y)) < 2;
		boolean x1Line = actor.getPositionX() <= other.getPositionX() && actor.getPositionX() + actor.size_x >= other.getPositionX();
		boolean x2Line = actor.getPositionX() <= other.getPositionX() + other.size_x && actor.getPositionX() + actor.size_x >= other.getPositionX() + other.size_x;

		boolean x3Line = other.getPositionX() <= actor.getPositionX() && other.getPositionX() + other.size_x >= actor.getPositionX();
		boolean x4Line = other.getPositionX() <= actor.getPositionX() + actor.size_x && other.getPositionX() + other.size_x >= actor.getPositionX() + actor.size_x;
		return  yNear && (x1Line || x2Line || x3Line || x4Line);
	}

	private static boolean downCollision(Actor actor, Actor other) {
		boolean yNear = Math.abs((actor.getPositionY() + actor.size_y) - other.getPositionY()) < 2;
		boolean x1Line = actor.getPositionX() <= other.getPositionX() && actor.getPositionX() + actor.size_x >= other.getPositionX();
		boolean x2Line = actor.getPositionX() <= other.getPositionX() + other.size_x && actor.getPositionX() + actor.size_x >= other.getPositionX() + other.size_x;

		boolean x3Line = other.getPositionX() <= actor.getPositionX() && other.getPositionX() + other.size_x >= actor.getPositionX();
		boolean x4Line = other.getPositionX() <= actor.getPositionX() + actor.size_x && other.getPositionX() + other.size_x >= actor.getPositionX() + actor.size_x;
		return  yNear && (x1Line || x2Line || x3Line || x4Line);
	}

	private static boolean leftCollision(Actor actor, Actor other) {
		boolean xNear = Math.abs(actor.getPositionX() - (other.getPositionX() + other.size_x)) < 2;
		boolean y1Line = actor.getPositionY() <= other.getPositionY() && actor.getPositionY() + actor.size_y >= other.getPositionY();
		boolean y2Line = actor.getPositionY() <= other.getPositionY() + other.size_y && actor.getPositionY() + actor.size_y >= other.getPositionY() + other.size_y;

		boolean y3Line = other.getPositionY() <= actor.getPositionY() && other.getPositionY() + other.size_y >= actor.getPositionY();
		boolean y4Line = other.getPositionY() <= actor.getPositionY() + actor.size_y && other.getPositionY() + other.size_y >= actor.getPositionY() + actor.size_y;
		return  xNear && (y1Line || y2Line || y3Line || y4Line);
	}

	private static boolean rightCollision(Actor actor, Actor other) {
		boolean xNear = Math.abs((actor.getPositionX() + actor.size_x) - other.getPositionX()) < 2;
		boolean y1Line = actor.getPositionY() <= other.getPositionY() && actor.getPositionY() + actor.size_y >= other.getPositionY();
		boolean y2Line = actor.getPositionY() <= other.getPositionY() + other.size_y && actor.getPositionY() + actor.size_y >= other.getPositionY() + other.size_y;

		boolean y3Line = other.getPositionY() <= actor.getPositionY() && other.getPositionY() + other.size_y >= actor.getPositionY();
		boolean y4Line = other.getPositionY() <= actor.getPositionY() + actor.size_y && other.getPositionY() + other.size_y >= actor.getPositionY() + actor.size_y;
		return  xNear && (y1Line || y2Line || y3Line || y4Line);
	}
	
	private static boolean innerCollision(Actor actor, Actor other) {
		boolean betweenX_A = other.getPositionX() < actor.getPositionX() && other.getPositionX() + other.size_x > actor.getPositionX();
		boolean betweenX_O = actor.getPositionX() < other.getPositionX() && actor.getPositionX() + actor.size_x > other.getPositionX();
		boolean betweenY_A = other.getPositionY() < actor.getPositionY() && other.getPositionY() + other.size_x > actor.getPositionY();
		boolean betweenY_O = actor.getPositionY() < other.getPositionY() && actor.getPositionY() + actor.size_x > other.getPositionY();
		return (betweenX_A || betweenX_O) && (betweenY_A || betweenY_O); 
	}
}
