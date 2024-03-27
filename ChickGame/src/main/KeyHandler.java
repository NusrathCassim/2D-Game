package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code ==  KeyEvent.VK_S) {
			downPressed = true;		
				}
		if(code ==  KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code ==  KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code ==  KeyEvent.VK_P) {
			if(gp.gameState == gp.playMode) {
				gp.gameState = gp.pauseMode;
			}else if(gp.gameState == gp.pauseMode){
				gp.gameState = gp.playMode;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code ==  KeyEvent.VK_S) {
			downPressed = false;		
				}
		if(code ==  KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code ==  KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

}
