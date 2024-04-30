package Character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import main.Object_Methods;
import object.obj_AX;

public class Player extends Character {
	
	public KeyHandler KeyH;
	Object_Methods om;
	public int Numrocks = 0;
	public int Maxrock = 9;
	
		public Player(GamePanel gp, KeyHandler KeyH,Object_Methods om ) {
			super(gp);
			this.KeyH = KeyH;
			this.om =om;
			setDefaultValue();
			getPlayerImage();
			playAttackImage();
			attackWithSpecialWeapon();
			//assigning collision area
			protectedArea = new Rectangle();
			protectedArea.x = 6;
			protectedArea.y = 6;
			protectedAreaDeafultX = protectedArea.x;
			protectedAreaDeafultY = protectedArea.y;
			protectedArea.width = 25; 
			protectedArea.height = 20;
			
			playerHitArea.width = 38;
			playerHitArea.height = 38;
			
		}
		
		public void setDefaultValue() {
			x = 200; //position of the player
			y = 200;
			defaultSpeed = 5;
			speed = defaultSpeed;
			Direction = "down";
			//player life
			MAXLIFE = 8;
			LIFE = MAXLIFE;
			project = new obj_AX(gp);
			
		}
		public void RestorePosition() {
			x = 200; //position of the player
			y = 200;
			defaultSpeed = 5;
			speed = defaultSpeed;
			Direction = "down";
		}
		public void restoreLife() {
			LIFE = MAXLIFE;
			invincible = false;
		}
		
		
		public void getPlayerImage() {
		
				up1 =setup("/Mainplayer/b1", gp.tileSize, gp.tileSize);
				up2 = setup("/Mainplayer/b2", gp.tileSize, gp.tileSize);
				up3 = setup("/Mainplayer/b3", gp.tileSize, gp.tileSize);
				down1 =setup("/Mainplayer/f1", gp.tileSize, gp.tileSize);
				down2= setup("/Mainplayer/f2", gp.tileSize, gp.tileSize);
				down3 =setup("/Mainplayer/f3", gp.tileSize, gp.tileSize);
				left1 = setup("/Mainplayer/L1", gp.tileSize, gp.tileSize);
				left2 = setup("/Mainplayer/L2", gp.tileSize, gp.tileSize);
				left3 = setup("/Mainplayer/L3", gp.tileSize, gp.tileSize);
				right1 =setup("/Mainplayer/R1", gp.tileSize, gp.tileSize);
				right2 =setup("/Mainplayer/R2", gp.tileSize, gp.tileSize);
				right3 = setup("/Mainplayer/R3", gp.tileSize, gp.tileSize);
				damageIL =  setup("/Mainplayer/damageL", gp.tileSize, gp.tileSize);
				damageIR =  setup("/Mainplayer/damageR", gp.tileSize, gp.tileSize);
				damageIF =  setup("/Mainplayer/damageF", gp.tileSize, gp.tileSize);
				damageIB =  setup("/Mainplayer/damageB", gp.tileSize, gp.tileSize);
			
			}
		//AX
		public void playAttackImage() {
			attackF1 = setup("/Mainplayer/attackF1", gp.tileSize , gp.tileSize*2);
			attackF2 =  setup("/Mainplayer/attackF2", gp.tileSize, gp.tileSize*2);
			attackB1 = setup("/Mainplayer/attackB1", gp.tileSize, gp.tileSize*2);
			attackB2 =  setup("/Mainplayer/attackB2", gp.tileSize , gp.tileSize*2);
			attackL1 = setup("/Mainplayer/attackL1", gp.tileSize*2 , gp.tileSize);
			attackL2 =  setup("/Mainplayer/attackL2", gp.tileSize*2, gp.tileSize);
			attackR1 = setup("/Mainplayer/attackR1", gp.tileSize*2, gp.tileSize);
			attackR2 =  setup("/Mainplayer/attackR2", gp.tileSize*2 , gp.tileSize);
					
		}
		//attack with special weapon
		public void attackWithSpecialWeapon() {
			sattackF1 = setup("/Mainplayer/specialAF1", gp.tileSize , gp.tileSize*2);
			sattackF2 =  setup("/Mainplayer/specialAF2", gp.tileSize, gp.tileSize*2);
			sattackB1 = setup("/Mainplayer/specialAB1", gp.tileSize, gp.tileSize*2);
			sattackB2 =  setup("/Mainplayer/specialAB2", gp.tileSize , gp.tileSize*2);
			sattackL1 = setup("/Mainplayer/specialAL1", gp.tileSize*2 , gp.tileSize);
			sattackL2 =  setup("/Mainplayer/specialAL2", gp.tileSize*2, gp.tileSize);
			sattackR1 = setup("/Mainplayer/specialAR1", gp.tileSize*2, gp.tileSize);
			sattackR2 =  setup("/Mainplayer/specialAR2", gp.tileSize*2 , gp.tileSize);
		}
		
		
		
		
		
		public void update() {
				if(attacking ==  true) {
					attackEnemy();
				}
				else if(specialAttack ==  true) {
					attackEnemy();
				}
			
				else if(KeyH.upPressed == true ||KeyH.downPressed == true|| KeyH.leftPressed == true ||
						KeyH.rightPressed==true || KeyH.enterPressed == true || KeyH.spacePressed == true){
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
				//start attacking
				if(KeyH.enterPressed == true) {
					attacking = true;
					gp.playSE(5);
				}
				//special weapon
				if(KeyH.spacePressed == true && Numrocks == 10) {
					specialAttack = true;
				}
				//collision
				collisionOn = false;
				gp.checker.checkTile(this);
				//check object collision
				int objIndex = gp.checker.checkObject(this, true);
				pickupObject(objIndex);
				//NPC collision
				int npcIndex = gp.checker.checkEntity(this, gp.npc);
				npcIntereact(npcIndex);
				//Monster Collision
				int MonsterIndex = gp.checker.checkEntity(this, gp.Monster);
				MonsterInteract(MonsterIndex);
				//special object collision
			
				
				int bottom_player = y + gp.tileSize;
				int right_player = x + gp.tileSize;
				//if collision is false player can move
				if(collisionOn == false && KeyH.enterPressed == false &&  KeyH.spacePressed == false)   {
					//this if statement under each case, will keep the sprite inside the display
					switch(Direction) {
					case "up":if(y > 0) {y-= speed;break; }
					case "down":if(bottom_player < gp.screenHeight) {y+= speed;	break;}
					case "left":if(x > 0) {x-= speed;break;}
					case "right":if(right_player < gp.screenWidth) {x+= speed;	break;}
					}
				}
				KeyH.enterPressed = false;
				KeyH.spacePressed = false;
				
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
			if(gp.KeyH.AXkeypressed == true && project.alive ==  false) {
				
				project.set(x, y, Direction, true, this);
				//add to the array list
				gp.projectileList.add(project);
				gp.playSE(7);
			}
			
		
			if(invincible == true) {
				invincibleCounter++;
				if(invincibleCounter > 60) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
			
			//GAMEOVER FRAME
			if(LIFE <= 0) {
				gp.gameState = gp.gameoverState;
				gp.playSE(9);
				
			}
			//GAME WINFrame
			if(gp.Monster[0] == null && gp.Monster[1] == null &&gp.Monster[2] == null && gp.Monster[3] == null
					&& gp.Monster[4] == null && LIFE > 0 ) {
				gp.gameState = gp.gameWin;
				gp.playSE(8);
			}
			
			
		}
		
		
		public void attackEnemy() {
			spriteCounter++;
			if(spriteCounter <=5) {
				spriteNum = 1;
			}if(spriteCounter > 5 && spriteCounter <=25) {
				spriteNum = 2;
			//check attack range lands on a monster	
			 int currentx = x;
			 int currenty = y;
			 int solidAreaWidth = protectedArea.width;
			 int solidAreaHeigh = protectedArea.height;
			 
			 switch(Direction) {
			 case "up" : y -= playerHitArea.height; break;
			 case "down": y += playerHitArea.height; break;
			 case "left" : x -= playerHitArea.width; break;
			 case "right": x += playerHitArea.width; break;
			 }
			 
			 solidAreaWidth = protectedArea.width;
			 solidAreaHeigh = protectedArea.height;
			 //check monster collision with updated data
			 int monsterIndex = gp.checker.checkEntity(this, gp.Monster);
			 damageMonster(monsterIndex, this);
			 x = currentx;
			 y = currenty;
			 protectedArea.width = solidAreaWidth;
			 protectedArea.height = solidAreaHeigh;
			 
			}if(spriteCounter >25) {
				spriteNum = 1;
				spriteCounter = 0;
				attacking = false;
				specialAttack = false;
			}
		}
		public int noOfRocks(int num) {
			Numrocks = Numrocks + num;
			return Numrocks;
			
		}
		
		public void pickupObject(int i) {
			if(i != 999) {
//				gp.obj[i] = null; //delete the object we touch
				String Object_Name = gp.obj[i].name;			
				switch(Object_Name) {
				case "rock_lv1":
					
						gp.playSE(4);
						//Numrocks++;
						noOfRocks(1); //calculate the no of rocks
						if(Numrocks == 1) {
							gp.ui.showMessage("This rock make Extra damage to the Monster");
						}
						gp.obj[i] = null;
						if(Numrocks <= Maxrock) {om.setObject(); }
							 //new rock
						if(Numrocks >= 10) {
							gp.ui.showMessage("You have unlocked a new weapon, which you can use to destroy BIGSLIME.");
						
						}
						if(Numrocks >=10) {
							om.setBigSlime();
							om.setnutMonter();
						}
					
					break;
				case "carrot":
					 
					gp.playSE(6);
					gp.obj[i] = null;
					
					gp.ui.showMessage("YOU GOT A BONUS LIFE!!");
					LIFE += 2;
					break;
				}
				
			}
		}
		//INTEREACTION OF NPC WITH PLAYER
		public void npcIntereact(int i){
			if(i !=999) {
				
			}
		}
		//MONSTER TOUCH AND INTERATION
		
		
		public void MonsterInteract(int i) {
			if(i !=999) {
				if(invincible == false) {
					LIFE -=1; //character lifes
					hitcount++;
					invincible = true;
					if(hitcount == 4 ) {
						om.setSpecialobj();
						
					}
				}
				
			}
		}
		
		//DAMAGE THE MONSTER
		public int updateDiedMonsters() {
		    diedMonsters++;
		    
		    if(diedMonsters == 2 && specialAttack == false && Numrocks != 10 ) {
		        om.setMonster();
		        diedMonsters = 0;
		        
		    }
//		    else if(specialAttack == true ) {
//		        om.setBigSlime();
//		    }
		     
		    // Any other logic related to died monsters
			return diedMonsters;
			//System.out.println(diedMonsters);
		}
		public void damageMonster(int i, Character attacker) {
			if(i != 999) {
				
				if(gp.Monster[i].invincible == false) {
					knockBack(gp.Monster[i], attacker);
					if(gp.Monster[i].type == 1) {gp.Monster[i].LIFE -= 2;}
					else {gp.Monster[i].LIFE -= 1;}
					gp.Monster[i].damageReaction();
					gp.Monster[i].invincible = true;
					if(gp.Monster[i].LIFE <= 0) {
						gp.Monster[i] = null;
						updateDiedMonsters();
						//AFTER 5 SECONDS 
						
					}
				}
			}
			else {
				//MISS THE DIRECT AX ATTACK
			}
		}
	
		public void draw(Graphics2D g2) {
			BufferedImage image = null;
			int tempx = x;
			int tempy = y;
			switch(Direction) {
			case "up":
				if(attacking == false) {
					if(spriteNum ==1) {
						image = up1;
						if(invincible == true) {
							image =  damageIB;
						}
						}
						if(spriteNum ==2) {
							image = up2;
						}
						if(spriteNum ==3) {
							image = up3;
						}
				}
				if(attacking == true) {
					tempy = y-gp.tileSize;
					if(spriteNum ==1) {
						image = attackB1;
					}
					if(spriteNum ==2) {
						image = attackB2;
					}
				}
				if(specialAttack == true) {
					tempy = y-gp.tileSize;
					if(spriteNum ==1) {
						image = sattackB1;
					}
					if(spriteNum ==2) {
						image = sattackB2;
					}
				}
				
				break;
			case "down":
				if(attacking == false) {
					if(spriteNum ==1) {
						image = down1;
						if(invincible == true) {
							image =  damageIF;
						}
					}
					if(spriteNum ==2) {
						image = down2;
					}
					if(spriteNum ==3) {
						image = down3;
					}
				}
				if(attacking == true) {
					if(spriteNum ==1) {
						image = attackF1;
					}
					if(spriteNum ==2) {
						image = attackF2;
					}
				}
				if(specialAttack == true) {
					if(spriteNum ==1) {
						image = sattackF1;
					}
					if(spriteNum ==2) {
						image = sattackF2;
					}
				}
				
				break;
			case "left":
				if(attacking == false) {
					if(spriteNum ==1) {
						image = left1;
						if(invincible ==true) {
							image = damageIL;
						}
					}
					if(spriteNum ==2) {
						image = left2;
					}
					if(spriteNum ==3) {
						image = left3;
					}
				}
				if(attacking == true) {
					tempx = x - gp.tileSize;
					if(spriteNum ==1) {
						image = attackL1;
					}
					if(spriteNum ==2) {
						image = attackL2;
					}
				}
				if(specialAttack == true) {
					tempx = x - gp.tileSize;
					if(spriteNum ==1) {
						image = sattackL1;
					}
					if(spriteNum ==2) {
						image = sattackL2;
					}
				}
				
				break;
			case "right":
				if(attacking == false) {
					if(spriteNum ==1) {
						image = right1;
						if(invincible ==true) {
							image = damageIR;
						}
					}
					if(spriteNum ==2) {
						image = right2;
					}
					if(spriteNum ==3) {
						image = right3;
					}        
				}
				if(attacking == true) {
					if(spriteNum ==1) {
						image = attackR1;
					}
					if(spriteNum ==2) {
						image = attackR2;
					}
				}  
				
				if(specialAttack == true) {
					if(spriteNum ==1) {
						image = sattackR1;
					}
					if(spriteNum ==2) {
						image = sattackR2;
					}
				}
				
				break;	
			}

			g2.drawImage(image, tempx, tempy, null);
			
		}

}
