package Character;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ToolBox;

public class Character {
	GamePanel gp;
	//OBJECT ROCK 
	public int obj_x, obj_y;
	public int x, y;
	public int speed;
	public int attack;
	public String Direction = "left";
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, left4,
						right1, right2, right3, right4, damageIF,damageIB,damageIR,damageIL;
	public BufferedImage attackF1, attackF2,attackR1,attackR2,attackL1,attackL2,attackB1,attackB2;
	public BufferedImage sattackF1, sattackF2,sattackR1,sattackR2,sattackL1,sattackL2,sattackB1,sattackB2;
	public BufferedImage image, image2, image3;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int invincibleCounter =0;
	public int Timer= 0;
	public int dyingCounter = 0;
	public int diedMonsters = 0;
	public int hitcount = 0;
	public Character attacker;
	public String knockbackDirection;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean specialAttack = false;
	public boolean collisionOn = false;
	public boolean collision = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean onPath = false;
	public boolean knockback =  false;
	
	//MOnster attack coolDown 
	public long lastAttack = 0;
	public final long coolDownTime = 3000; //3sec
	int knockbackCounter = 0; 
	//colliding
	public Rectangle protectedArea = new Rectangle(0, 0, 48, 48);
	public Rectangle playerHitArea = new Rectangle(0, 0, 0, 0);

	public int protectedAreaDeafultX, protectedAreaDeafultY;
	
	
	public String name;
	public int MAXLIFE;
	public int LIFE;
	public int type; //monster = 2
	public projectile project;
	public int defaultSpeed ;
	

	public Character(GamePanel gp){
		this.gp =gp;
	}
	public void setAction() {
		
	}
	//MONSTER REACTION
	public void damageReaction() {
		
	}
	public void checkCollision() {
		collisionOn = false;
		gp.checker.checkTile(this);//pass the npc 
		gp.checker.checkObject(this, false);
		gp.checker.checkEntity(this, gp.npc);
		gp.checker.checkEntity(this, gp.Monster);
		boolean contactPlayer = gp.checker.checkPlayer(this);
		
		//MONSTER TOUCH PLAYER - LOOSE LIFE
				if(this.type == 2 && contactPlayer == true) {
					damageplayer();
				}
			
		
	}
	
	public void update() {
		if(knockback == true) {
			checkCollision();
			if(collisionOn == true) {
				knockbackCounter = 0;
				knockback = false;
				speed = defaultSpeed;
				
			}

			
			else if(collisionOn == false) {
				int bottom_player = y + gp.tileSize;
				int right_player = x + gp.tileSize;
				switch(knockbackDirection) {
				case "up":if(y > 0) {y-= speed;break; }
				case "down":if(bottom_player < gp.screenHeight) {y+= speed;	break;}
				case "left":if(x > 0) {x-= speed;break;}
				case "right":if(right_player < gp.screenWidth) {x+= speed;	break;}
					
				}
				
			}
			knockbackCounter++;
			if(knockbackCounter == 10) {
				knockbackCounter = 0;
				knockback = false;
				speed = defaultSpeed;
			}
		}
		else {
			setAction();
			checkCollision();
		
			if(collisionOn == false) {
				switch(Direction) {
				case "left":x-= speed;break;								
				case "right":
					if((x + gp.tileSize) <= gp.screenWidth) {
						x+= speed;	
						break;
					}	
				case "up":	y -=speed;break;
				case "down":y += speed;break;
					
				}
			}
		}
		
		spriteCounter++;
		if(spriteCounter > 20) {
			if(spriteNum == 1) {
				spriteNum=2;
			}else if(spriteNum==2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
			if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 40){ 
				invincible = false;
				invincibleCounter = 0;
			}
		}
			
	}
	

	public void damageplayer() {
		int specialObj = 0;
		if(gp.player.invincible == false) {
			gp.player.LIFE -=1;
			if(gp.player.LIFE == 2) {
				if(specialObj != 4) {
					gp.methods.setSpecialobj();
					specialObj++;
				}
				
			}
			gp.player.invincible = true;
		}
	}
//NPC Movement
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(Direction) {
		case "left":
			if(spriteNum ==1) {image = left1;}
			if(spriteNum ==2) {image = left2;}
			if(spriteNum ==3) {image = left3;}
			
			break;
		case "right":
			if(spriteNum ==1) {image = right1;}
			if(spriteNum ==2) {image = right2;}
			if(spriteNum ==3) {image = right3;}
			
			break;	
		case "up":
			if(spriteNum ==1) {image = up1;}
			if(spriteNum ==2) {image = up2;}
			if(spriteNum ==3) {image = up3;}
			
			break;	
		case "down":
			if(spriteNum ==1) {image = down1;}
			if(spriteNum ==2) {image = down2;}
			if(spriteNum ==3) {image = down3;}
			
			break;	
			
		}
		if(invincible ==  true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		g2.drawImage(image, x, y, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	
	public BufferedImage setup(String imagepath, int width, int height) {
		ToolBox tool = new ToolBox();
		BufferedImage Image = null;
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagepath +".png"));
			Image = tool.scaleImage(Image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
	public void draw(Graphics2D g2, GamePanel gp) {
		//DRAW METHOD FOR THE ROCKS
		g2.drawImage(image, obj_x, obj_y,gp.tileSize, gp.tileSize, null);
		
	}
	
	public void searchPath(int goalCol, int goalRow) {
		int startCol =  (x + protectedArea.x)/gp.tileSize;
		int startRow = (y + protectedArea.y)/gp.tileSize;
		
		gp.pathfinder.setNode(startCol, startRow, goalCol, goalRow, this);
		
		if(gp.pathfinder.search() == true) {
			int nextX = gp.pathfinder.pathList.get(0).col*gp.tileSize;
			int nextY = gp.pathfinder.pathList.get(0).row*gp.tileSize;
			
			int eLeftX = x + protectedArea.x;
			int eRightX = x + protectedArea.x + protectedArea.width;
			int eTopY = y + protectedArea.y;
			int eBottomY = y + protectedArea.y + protectedArea.height;
			
			
			if(eTopY > nextY && eLeftX >= nextX && eRightX < nextX +gp.tileSize) {
				Direction = "up";
			}
			else if(eTopY < nextY && eLeftX >= nextX && eRightX < nextX +gp.tileSize) {
				Direction = "down";
			}
			else if(eTopY >= nextY && eBottomY < nextY +gp.tileSize) {
				//LEFT or Right
				if(eLeftX > nextX) {
					Direction = "left";
				}
				if(eLeftX < nextX) {
					Direction = "right";
				}
				
			}
			else if(eTopY > nextY && eLeftX > nextX) {
					//up or left
					Direction = "up";
					checkCollision();
					if(collisionOn == true) {
						Direction = "left";
					}
				}
				
	
			else if(eTopY > nextY && eLeftX < nextX) {
					//up or right
					Direction = "up";
					checkCollision();
					if(collisionOn == true) {
						Direction = "right";
					}
				}
			else if(eTopY < nextY && eLeftX > nextX) {
					//down or left
					Direction = "down";
					checkCollision();
					if(collisionOn == true) {
						Direction = "left";
					}
				}
			else if(eTopY < nextY && eLeftX < nextX) {
					//down or right
					Direction = "down";
					checkCollision();
					if(collisionOn == true) {
						Direction = "right";
					}
				}
			
		}
		
	}
	public void getRandomDirection() {
		Timer++;
		if(Timer == 120 ) {//2s
			Random random = new Random();
			int j = random.nextInt(100)+1;
			if(j <=25 ) {Direction = "left";}
			if(j> 25 && j <= 50) {Direction = "right";}
			if(j> 50 && j <= 75) {
				Direction = "up";
			}
			if(j> 75 && j <= 100) {
				Direction = "down";
			}
			Timer = 0;
			
			
		}
	}
	public void VerifyStartCasing(Character target, int distance, int rate) {
		if(getTiledistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if(i == 0 ) {
				onPath = true;
			}
		}
	}
	public void VerifystopChaseStatus(Character target, int distance, int rate) {
		if(getTiledistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0 ) {
				onPath = false;
			}
		}
	}
	public int getXdistance(Character target) {
		int xDistance = Math.abs(x - target.x);
		return xDistance;
		
	}
	public int getYdistance(Character target) {
		int yDistance = Math.abs(y -target.y);
		return yDistance;
		
	}
	public int getTiledistance(Character target) {
		int tileDistance = (getXdistance(target) - getYdistance(target))/gp.tileSize;
		return tileDistance;
	}
	public int getGoalCol(Character target) {
		int goalCol = (target.x + target.protectedArea.x)/gp.tileSize;
		return goalCol;
	}
	public int getGoalRow(Character target) {
		int goalRow = (target.y + target.protectedArea.y)/gp.tileSize;
		return goalRow;
	
	}
	public boolean isSpecialAttack() {
	    return specialAttack;
	}
	public void knockBack(Character Character, Character attacker) {
		//Character.Direction = Direction;
		this.attacker= attacker;
		Character.knockbackDirection = attacker.Direction;
		
		Character.speed +=10;
		Character.knockback = true;
		
	}

	
	
}
