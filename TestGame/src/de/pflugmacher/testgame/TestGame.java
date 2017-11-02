package de.pflugmacher.testgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import de.pflugmacher.testgame.controller.AssetController;
import de.pflugmacher.testgame.controller.KeyController;
import de.pflugmacher.testgame.model.Actor;
import de.pflugmacher.testgame.model.HUDElement;

public class TestGame {
	
	public static JFrame window;
	Thread gameLoop;
	
	public static KeyController keyController;
	public static AssetController assetController;
	public static ArrayList<Actor> actors;
	public static ArrayList<HUDElement> hudElements;
	
	private RandomEvents randomEvents;
	private long lastFrame;
	private long lastFPS;
	private int fps;
	private VolatileImage vImg = null;
	

	public static void main(String[] args) {
		new TestGame();
	}
	
	public TestGame() {
		initWindow();
		initGame();
	}
	
	private void initWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		width = 800;
		height = 600;
		
		keyController = new KeyController();
		window = new JFrame();
		window.setSize((int)width, (int)height);
		window.setResizable(false);
		//window.setUndecorated(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.addKeyListener(keyController);
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (System.currentTimeMillis());
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}
	
	public void updateFPS() {
		
		if (getTime() - lastFPS > 1000) {
			window.setTitle("FPS: " + fps + "   Actors: " + actors.size());
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	private void initGame() {
		actors = new ArrayList<Actor>();
		hudElements = new ArrayList<HUDElement>();
		assetController = new AssetController();
		randomEvents = new RandomEvents();
		
		gameLoop = new Thread() {
			
			public void run() {
				
				lastFPS = getTime();
				getDelta();
				vImg = window.createVolatileImage(window.getWidth(), window.getHeight());
				
				while(window.isVisible()) {
					
					try {
						
						update(getDelta());
						render();
						updateFPS();
						sleep(1);
						
					} catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		};
		
		gameLoop.start();
		new Loader();			
	}

	protected void update(double delta) {
		ArrayList<Actor> garbadge = new ArrayList<Actor>();
		for(int i = 0; i < actors.size(); i++) {

			Actor a = actors.get(i);
			a.tick(delta);
			if (a.isGarbadge || a.x < -200 || a.y < -200 || a.x > window.getWidth() + 200 || a.y > window.getHeight() + 200) {
				garbadge.add(a);
			}
		}
		actors.removeAll(garbadge);
		randomEvents.tick(delta);

		for(HUDElement hud: hudElements) {
			hud.tick(delta);
		}
	}
	
	protected void render() {
		
		if (vImg.validate(window.getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {
			vImg = window.createVolatileImage(window.getWidth(), window.getHeight());
		}

		Graphics2D g2d = (Graphics2D)vImg.getGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, window.getWidth(), window.getHeight());
		
		g2d.drawImage(assetController.images.get("galaxy_bg"), 0, 0, window.getWidth(), window.getHeight(), null);
		
		for(Actor a: actors) {
			a.render(g2d);
		}
		
		for(HUDElement hud: hudElements) {
			hud.render(g2d);
		}
		
		window.getContentPane().getGraphics().drawImage(vImg, 0, 0, window);
	}
	
	public static void Close() {
		window.dispose();
		System.exit(0);
	}

}
