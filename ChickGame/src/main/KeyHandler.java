package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, AXkeypressed, spacePressed;
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
		//TILE STATE
		if(gp.gameState == gp.MainState) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum <0){
						gp.ui.commandNum = 1;
					}
				}
				if(code ==  KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 1){
						gp.ui.commandNum =0;
					}
				}
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum ==0) {
						gp.gameState =gp.playMode;
						gp.playSE(1);
						
					}
					if(gp.ui.commandNum ==1) {
						System.exit(0);
						
					}
				}
		}
		else if(gp.gameState == gp.playMode){
			
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
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if(code == KeyEvent.VK_F) {
				AXkeypressed = true;
			}
			if(code ==  KeyEvent.VK_P) {
			 gp.gameState = gp.pauseMode;
			      
			}
			if(code == KeyEvent.VK_SPACE) {
				spacePressed = true;
			}
		}
		
		else if(gp.gameState == gp.pauseMode) {
			if(code ==  KeyEvent.VK_P) {
				 gp.gameState = gp.playMode;
				 
			}
		}
		else if(gp.gameState == gp.gameoverState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum <0){
					gp.ui.commandNum = 1;
				}
			}
			if(code ==  KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1){
					gp.ui.commandNum =0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum ==0) {
					gp.gameState = gp.playMode;
					gp.retry();
					
				}
				if(gp.ui.commandNum ==1) {
					gp.gameState =gp.MainState;
					gp.restart();
					
				}
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
		if(code == KeyEvent.VK_F) {
			AXkeypressed = false;
		}
//		if(code == KeyEvent.VK_SPACE) {
//			spacePressed = false;
//		}
		
	}

}
