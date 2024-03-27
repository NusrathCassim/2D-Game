package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.Object_rock;

public class ScreenUI {
	GamePanel gp;
	Graphics2D g2;
	Font patrick;
	BufferedImage rockImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter =0;
	
	
	public ScreenUI(GamePanel gp) {
		this.gp = gp;
		patrick = new Font("Patrick Hand SC", Font.BOLD, 30 );
		Object_rock rock = new Object_rock(gp);
		rockImage = rock.image;
		
		
	}
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(patrick);
		g2.setColor(Color.WHITE);
		g2.drawImage(rockImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize, gp.tileSize, null);
		g2.drawString("X "+ gp.player.Numrocks, 65, 60);
		
	
	//playmode
		if(gp.gameState == gp.playMode) {
				if(messageOn == true) {
					g2.setFont(g2.getFont().deriveFont((float) 25.0));
					g2.drawString(message, gp.tileSize/2,gp.tileSize*2);
					messageCounter++;
					if(messageCounter > 120) {
						messageCounter = 0;
						messageOn = false;
					}	
				}
			}
			
	//pause method
		if(gp.gameState == gp.pauseMode) {
			pauseMode();
			}			
	
	}
	
	public void pauseMode() {
		String text= "PAUSED!!";
		int x = getCenterX(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	public int getCenterX(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();	
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
}
