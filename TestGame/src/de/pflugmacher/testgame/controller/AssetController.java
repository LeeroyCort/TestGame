package de.pflugmacher.testgame.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AssetController {
	public HashMap<String, BufferedImage> images;
	public HashMap<String, byte[]> sounds;
	private String[] imageResources = {
			"asteroid.png", "galaxy_bg.png", "shield.png", "ship.png", "explosion1.png",
			"blueLaser1.png", "blueLaser2.png",	"blueLaser3.png", "blueLaser4.png",	"blueLaser5.png", "blueLaser6.png",
			"yellowLaser1.png", "yellowLaser2.png", "yellowLaser3.png", "yellowLaser4.png", "yellowLaser5.png", "yellowLaser6.png", 
			"greenLaser1.png", "greenLaser2.png", "greenLaser3.png", "greenLaser4.png", "greenLaser5.png", "greenLaser6.png", 
			"lightblueLaser1.png", "lightblueLaser2.png", "lightblueLaser3.png", "lightblueLaser4.png", "lightblueLaser5.png", "lightblueLaser6.png",
			"pinkLaser1.png", "pinkLaser2.png", "pinkLaser3.png", "pinkLaser4.png", "pinkLaser5.png", "pinkLaser6.png", 
			"redLaser1.png", "redLaser2.png", "redLaser3.png", "redLaser4.png", "redLaser5.png", "redLaser6.png", 
			"greenEnergyBall.png", "orangeEnergyBall.png", "pinkEnergyBall.png", "redEnergyBall.png", "yellowEnergyBall.png"
	};
	private String[] soundResources = {
			"laser.wav", "laser_burst.wav", "plasma.wav"
	};
	
	public AssetController() {
		images = new HashMap<String, BufferedImage>();
		sounds = new HashMap<String, byte[]>();
		loadImages();
		loadSounds();
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
