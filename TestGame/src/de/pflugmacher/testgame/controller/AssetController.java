package de.pflugmacher.testgame.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AssetController {
	public HashMap<String, BufferedImage> images;
	public HashMap<String, byte[]> sounds;
	
	public AssetController() {
		images = new HashMap<String, BufferedImage>();
		sounds = new HashMap<String, byte[]>();
		loadImages();
	}
	
	private void loadImages() {
		File imageFolder = new File("assets/images/");
		String[] folders = imageFolder.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		ArrayList<File> imageFiles = new ArrayList<File>(Arrays.asList(imageFolder.listFiles()));
		for (String folder: folders) {
			imageFiles.addAll(Arrays.asList(new File("assets/images/" + folder + "/").listFiles()));
		}
		for (File ImageFile: imageFiles) {
			if (ImageFile.isFile()) {		
				String imagename = ImageFile.getName().split("[.]")[0];
				try {
					BufferedImage imageBuff = ImageIO.read(new FileInputStream(ImageFile));
					images.put(imagename, imageBuff);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
