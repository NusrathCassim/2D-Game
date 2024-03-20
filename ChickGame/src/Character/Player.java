package Character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.Object_Methods;

public class Player extends Character {
	
	GamePanel gp;
	KeyHandler KeyH;
	Object_Methods om;
	int Numrocks = 0;
		
		public Player(GamePanel gp, KeyHandler KeyH,Object_Methods om ) {
			this.gp = gp;
			this.KeyH = KeyH;
			this.om =om;
			setDefaultValue();
			getPlayerImage();
			
			//assigning collision area
			protectedArea = new Rectangle();
			protectedArea.x = 8;
			protectedArea.y = 8;
			protectedAreaDeafultX = protectedArea.x;
			protectedAreaDeafultY = protectedArea.y;
			protectedArea.width = 20; 
			protectedArea.height = 20;
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
			}
			if(KeyH.downPressed == true) {
				Direction = "down";				
			}
			if(KeyH.leftPressed == true) {
				Direction = "left";				
			}
			if(KeyH.rightPressed == true) {
				Direction = "right";
			}
			
			//collision
			collisionOn = false;
			gp.checker.checkTile(this);
			//check object collision
			int objIndex = gp.checker.checkObject(this, true);
			pickupObject(objIndex);
			
			
			
			//if collision is false player can move
			if(collisionOn == false) {
				switch(Direction) {
				case "up":
					y-= speed;
					break;
				case "down":
					y+= speed;	
					break;
				case "left":
					x-= speed;	
					break;
				case "right":
					x+= speed;	
					break;
					
				}
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
		public void pickupObject(int i) {
			if(i != 999) {
//				gp.obj[i] = null; //delete the object we touch
				String Object_Name = gp.obj[i].name;
				
				switch(Object_Name) {
				case "rock_lv1":
					Numrocks++;
					gp.obj[i] = null;
					om.setObject();
					//newObject();
					break;
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
