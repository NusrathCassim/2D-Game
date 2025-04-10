package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/Game-music.wav");
		soundURL[1] = getClass().getResource("/sound/Game-2.wav");
		soundURL[2] = getClass().getResource("/sound/jump.wav");
		soundURL[3] = getClass().getResource("/sound/rock-grab.wav");
		soundURL[4] = getClass().getResource("/sound/fireRock.wav");
		soundURL[5] = getClass().getResource("/sound/swing.wav");
		soundURL[6] = getClass().getResource("/sound/carrot.wav");
		soundURL[7] = getClass().getResource("/sound/rotate.wav");
		soundURL[8] = getClass().getResource("/sound/Win.wav");
		soundURL[9] = getClass().getResource("/sound/gameOver.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			
		}
	}
	
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
