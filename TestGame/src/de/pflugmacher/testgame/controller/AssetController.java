package de.pflugmacher.testgame.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.pflugmacher.testgame.Utils;
import de.pflugmacher.testgame.model.AnimationResource;

public class AssetController {
	public HashMap<String, BufferedImage> images;
	public HashMap<String, byte[]> sounds;
	public HashMap<String, BufferedImage[]> animations;
	private String[] imageResources = {
			"asteroid.png", "galaxy_bg.png", "shield.png", "ship.png", 
			"blueLaser1.png", "blueLaser2.png",	"blueLaser3.png", "blueLaser4.png",	"blueLaser5.png", "blueLaser6.png",
			"yellowLaser1.png", "yellowLaser2.png", "yellowLaser3.png", "yellowLaser4.png", "yellowLaser5.png", "yellowLaser6.png", 
			"greenLaser1.png", "greenLaser2.png", "greenLaser3.png", "greenLaser4.png", "greenLaser5.png", "greenLaser6.png", 
			"lightblueLaser1.png", "lightblueLaser2.png", "lightblueLaser3.png", "lightblueLaser4.png", "lightblueLaser5.png", "lightblueLaser6.png",
			"pinkLaser1.png", "pinkLaser2.png", "pinkLaser3.png", "pinkLaser4.png", "pinkLaser5.png", "pinkLaser6.png", 
			"redLaser1.png", "redLaser2.png", "redLaser3.png", "redLaser4.png", "redLaser5.png", "redLaser6.png", 
	};
	private String[] soundResources = {
			"laser.wav", "laser_burst.wav", "plasma.wav", "Explosion.wav", "muffledExplosion.wav"
	};

	private ArrayList<AnimationResource> animationResources;
	
	private void preLoadAnimationResources() {
		animationResources = new ArrayList<AnimationResource>();
		animationResources.add(new AnimationResource("explosion1.png", 4, 4));
		animationResources.add(new AnimationResource("explosion2.png", 4, 4));
		animationResources.add(new AnimationResource("greenEnergyBall.png", 4, 8));
		animationResources.add(new AnimationResource("orangeEnergyBall.png", 4, 8));
		animationResources.add(new AnimationResource("pinkEnergyBall.png", 4, 8));
		animationResources.add(new AnimationResource("redEnergyBall.png", 4, 8));
		animationResources.add(new AnimationResource("yellowEnergyBall.png", 4, 8));
		animationResources.add(new AnimationResource("aura.png", 4, 8));
		animationResources.add(new AnimationResource("auratd.png", 4, 8));		 
	}
	
	public AssetController() {
		images = new HashMap<String, BufferedImage>();
		sounds = new HashMap<String, byte[]>();
		animations = new HashMap<String, BufferedImage[]>();
		preLoadAnimationResources();
		loadImages();
		loadSounds();
		loadAnimations();
	}
		
	private void loadImages() {
		for (String imageResource: imageResources) {
			try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/resources/images/" + imageResource));
			String imageName = imageResource.split("[.]")[0];
			images.put(imageName, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadAnimations() {
		for (AnimationResource animationResource: animationResources) {
			try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/resources/images/" + animationResource.resource));
			BufferedImage[] animation = Utils.CutImage(image, animationResource.rows, animationResource.cols) ;
			String animationName = animationResource.resource.split("[.]")[0];
			animations.put(animationName, animation);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadSounds() {
		for (String soundResource: soundResources) {
			try {
				InputStream stream = getClass().getResourceAsStream("/resources/sounds/" + soundResource);
				byte[] sound = new byte[stream.available()];
				stream.read(sound);
				String soundName = soundResource.split("[.]")[0];
				sounds.put(soundName, sound);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
