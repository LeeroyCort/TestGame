package de.pflugmacher.testgame.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import de.pflugmacher.testgame.TestGame;
import de.pflugmacher.testgame.namelists.Controls;

public class KeyController implements KeyListener {
	public ArrayList<Controls> actions;
	
	public KeyController() {
		actions = new ArrayList<Controls>();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_W:
				if (!actions.contains(Controls.Forward))
					actions.add(Controls.Forward);
				break;
			case KeyEvent.VK_S:
				if (!actions.contains(Controls.Backward))
				actions.add(Controls.Backward);
				break;
			case KeyEvent.VK_A:
				if (!actions.contains(Controls.Left))
				actions.add(Controls.Left);
				break;
			case KeyEvent.VK_D:
				if (!actions.contains(Controls.Right))
				actions.add(Controls.Right);
				break;
			case KeyEvent.VK_SPACE:
				if (!actions.contains(Controls.Shot))
				actions.add(Controls.Shot);
				break;
			case KeyEvent.VK_F:
				if (!actions.contains(Controls.Shield))
				actions.add(Controls.Shield);
				break;
			case KeyEvent.VK_ESCAPE:
				TestGame.Close();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
			case KeyEvent.VK_W:
				while (actions.contains(Controls.Forward))
					actions.remove(Controls.Forward);
				break;
			case KeyEvent.VK_S:
				while (actions.contains(Controls.Backward))
					actions.remove(Controls.Backward);
				break;
			case KeyEvent.VK_A:
				while (actions.contains(Controls.Left))
					actions.remove(Controls.Left);
				break;
			case KeyEvent.VK_D:
				while (actions.contains(Controls.Right))
					actions.remove(Controls.Right);
				break;
			case KeyEvent.VK_SPACE:
				while (actions.contains(Controls.Shot))
					actions.remove(Controls.Shot);
				break;
			case KeyEvent.VK_F:
				while (actions.contains(Controls.Shield))
					actions.remove(Controls.Shield);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
