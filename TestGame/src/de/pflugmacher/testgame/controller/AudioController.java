package de.pflugmacher.testgame.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioController {
	private Clip clip;
	
	public AudioController(byte[] audioStream, float volume) {
		try {
			clip = AudioSystem.getClip();
	        BufferedInputStream bufferedStream = new BufferedInputStream(new ByteArrayInputStream(audioStream));
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);
	        clip.open(audioInputStream);
	        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        control.setValue(volume);                 		
	        			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void playSound() {
			clip.stop();
			clip.setFramePosition(0);
			clip.start();
	}
	
	public void loop(int count, int startOffset, int endOffset) {
		clip.setFramePosition(startOffset);
		clip.setLoopPoints(startOffset, clip.getFrameLength() - (endOffset+1));
		clip.loop(count);
	}
	
	public void stopPlaying() {
		clip.stop();
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
}
