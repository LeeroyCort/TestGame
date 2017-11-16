package de.pflugmacher.testgame.model;

public class AnimationResource {
	public int rows;
	public int cols;
	public String resource;
	public AnimationResource(String resource, int rows, int cols) {
		this.resource = resource;
		this.rows = rows;
		this.cols = cols;
	}
}
