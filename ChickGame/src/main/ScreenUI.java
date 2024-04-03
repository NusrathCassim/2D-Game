package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.Object_heart;
import object.Object_rock;

public class ScreenUI {
	GamePanel gp;
	Graphics2D g2;
	Font patrick;
	BufferedImage FullH, EmptyH, HalfH;
	BufferedImage rockImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter =0;
	
	
	public ScreenUI(GamePanel gp) {
		this.gp = gp;
		patrick = new Font("Patrick Hand SC", Font.BOLD, 30 );
		Object_rock rock = new Object_rock(gp);
		rockImage = rock.image;
		
//		
		Object_heart heart = new Object_heart(gp);
		FullH = heart.image;
		HalfH = heart.image2;
		EmptyH = heart.image3;
		
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
	
	
	//play mode
		if(gp.gameState == gp.playMode) {
			screenMsg();
			drawHearts();
			
				
			}
			
	//pause method
		if(gp.gameState == gp.pauseMode) {
			pauseMode();
			drawHearts();
			}			
	
	}
	
	public void screenMsg() {
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
	public void drawHearts() {
		
		int x = gp.tileSize*14;
		int y = gp.tileSize/2;
		int i = 0;
		//MAXLIFE
		while(i < gp.player.MAXLIFE/2) {
			g2.drawImage(EmptyH, x, y, null);
			i++;
			x += gp.tileSize;
		}
		//RESET
		 x = gp.tileSize*14;
		 y = gp.tileSize/2;
		 i = 0;
		 while(i < gp.player.LIFE) {
				g2.drawImage(HalfH, x, y, null);
				i++;
			if(i < gp.player.LIFE) {
				g2.drawImage(FullH, x, y, null);
			}
			i++;
			x += gp.tileSize;
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
