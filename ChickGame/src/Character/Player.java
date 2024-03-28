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
import main.ToolBox;

public class Player extends Character {
	
	KeyHandler KeyH;
	Object_Methods om;
	public int Numrocks = 0;
		
		public Player(GamePanel gp, KeyHandler KeyH,Object_Methods om ) {
			super(gp);
			this.KeyH = KeyH;
			this.om =om;
			setDefaultValue();
			getPlayerImage();
			
			//assigning collision area
			protectedArea = new Rectangle();
			protectedArea.x = 6;
			protectedArea.y = 6;
			protectedAreaDeafultX = protectedArea.x;
			protectedAreaDeafultY = protectedArea.y;
			protectedArea.width = 20; 
			protectedArea.height = 20;
		}
		
		public void setDefaultValue() {
			x = 200; //position of the player
			y = 200;
			speed = 4;
			Direction = "down";
		}
		public void getPlayerImage() {
		
				up1 =setup("/Mainplayer/b1");
				up2 = setup("/Mainplayer/b2");
				up3 = setup("/Mainplayer/b3");
				down1 =setup("/Mainplayer/f1");
				down2= setup("/Mainplayer/f2");
				down3 =setup("/Mainplayer/f3");
				left1 = setup("/Mainplayer/L1");
				left2 = setup("/Mainplayer/L2");
				left3 = setup("/Mainplayer/L3");
				right1 =setup("/Mainplayer/R1");
				right2 =setup("/Mainplayer/R2");
				right3 = setup("/Mainplayer/R3");
				
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
			//NPC collision
			int npcIndex = gp.checker.checknpc(this, gp.npc);
			npcIntereact(npcIndex);
			int bottom_player = y +gp.tileSize;
			 int right_player = x + gp.tileSize;
			//if collision is false player can move
			if(collisionOn == false) {
				//this if statement under each case, will keep the sprite inside the display
				switch(Direction) {
				case "up":
				if(y > 0) {
					y-= speed;
					break; }
				case "down":
					
					if(bottom_player < gp.screenHeight) {
					y+= speed;	
					break;
					}
				case "left":
					if(x >  0) {
						x-= speed;	
						break;							
					}
					
				case "right":
					if(right_player < gp.screenWidth) {
					x+= speed;	
					break;
					}
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
					gp.playSE(4);
					Numrocks++;
					if(Numrocks == 1) {
						gp.ui.showMessage("This rock make Extra damage to the Monster");
						
					}
					gp.obj[i] = null;
					
					om.setObject(); //new rock
					break;
				
				}
				
			}
		}
		//INTEREACTION OF NPC WITH PLAYER
		public void npcIntereact(int i){
			if(i !=999) {
				System.out.println("pagaya");
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
			g2.drawImage(image, x, y, null);
			
		}

}
