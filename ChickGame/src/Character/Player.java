package Character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Character {
	
	GamePanel gp;
	KeyHandler KeyH;
		
		public Player(GamePanel gp, KeyHandler KeyH) {
			this.gp = gp;
			this.KeyH = KeyH;
			setDefaultValue();
			getPlayerImage();
		}
		
		public void setDefaultValue() {
			x = 100;
			y = 100;
			speed = 4;
			Direction = "down";
		}
		public void getPlayerImage() {
			try {
				up1 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/b1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/b2.png"));
				up3 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/b3.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/f1.png"));
				down2= ImageIO.read(getClass().getResourceAsStream("/Mainplayer/f2.png"));
				down3 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/f3.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/L1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/L2.png"));
				left3 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/L3.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/R1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/R2.png"));
				right3 = ImageIO.read(getClass().getResourceAsStream("/Mainplayer/R3.png"));
				
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void update() {
		if(KeyH.upPressed == true ||KeyH.downPressed == true|| KeyH.leftPressed == true ||
					KeyH.rightPressed==true){
			if(KeyH.upPressed == true) {
				Direction = "up";
				y-= speed;	
			}
			if(KeyH.downPressed == true) {
				Direction = "down";
				y+= speed;	
			}
			if(KeyH.leftPressed == true) {
				Direction = "left";
				x-= speed;	
			}
			if(KeyH.rightPressed == true) {
				Direction = "right";
				x+= speed;	
			}

			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum=2;
				}else if(spriteNum==2) {
					spriteNum = 3;
				}else if (spriteNum == 3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
			
		}
		
		public void draw(Graphics2D g2) {
			BufferedImage image = null;
			switch(Direction) {
			case "up":
				if(spriteNum ==1) {
				image = up1;
				}
				if(spriteNum ==2) {
					image = up2;
				}
				if(spriteNum ==3) {
					image = up3;
				}
				
				break;
			case "down":
				if(spriteNum ==1) {
					image = down1;
				}
				if(spriteNum ==2) {
					image = down2;
				}
				if(spriteNum ==3) {
					image = down3;
				}
				
				break;
			case "left":
				if(spriteNum ==1) {
					image = left1;
				}
				if(spriteNum ==2) {
					image = left2;
				}
				if(spriteNum ==3) {
					image = left3;
				}
				break;
			case "right":
				if(spriteNum ==1) {
					image = right1;
				}
				if(spriteNum ==2) {
					image = right2;
				}
				if(spriteNum ==3) {
					image = right3;
				}
				break;	
			}
			g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
			
		}

}
