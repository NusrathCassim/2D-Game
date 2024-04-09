package monster;
import java.util.Random;

import Character.Character;
import main.GamePanel;
import object.obj_redBean;

public class m_redBean extends Character{
	GamePanel gp; //update
	public m_redBean(GamePanel gp) {
		super(gp);
		this.gp = gp;
		Direction = "left";
		name = "RedBean";
		speed = 1;
		MAXLIFE = 3;
		LIFE = MAXLIFE;
		type = 2; 
		project = new obj_redBean(gp);
		protectedArea.x = 8;
		protectedArea.y = 18;
		protectedArea.width = 30;
		protectedArea.height = 32;
		protectedAreaDeafultX = protectedArea.x;
		protectedAreaDeafultY = protectedArea.y;
		getImage();
	}
	public void getImage() {
		up1 =  setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		up2 =  setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		up3 =  setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		down2= setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		down3 =  setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		
		left1 = setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Monster2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/Monster3", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/MonsterR1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/MonsterR2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/MonsterR3", gp.tileSize, gp.tileSize);
	}
	//update methods here, sprite no are different from default one
	public void update() {
		setAction();
		collisionOn = false;
		gp.checker.checkTile(this);
		gp.checker.checkEntity(this, gp.Monster);
		boolean contactPlayer = gp.checker.checkPlayer(this);
		
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				gp.player.LIFE -=1;
				gp.player.invincible = true;
			}
		}
		if(collisionOn == false) {
			switch(Direction) {
			case "left":
				x-= speed;	
					break;								
			case "right": 
				if((x + gp.tileSize) < gp.screenWidth) {
					x+= speed;	
					break;
					}
			case "up":
				y -=speed;
				break;
			case "down":
				y += speed;
				break;
				
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
		
		int xDistance = Math.abs(x - gp.player.x);
		int yDistance = Math.abs(y - gp.player.y);
		int tileDistance = (xDistance - yDistance)/gp.tileSize;
		if(onPath == false && tileDistance < 3) {
			int i = new Random().nextInt(100)+1;
			if(i > 50) {
				onPath = true;
			}
			
		}
		
		//IF MONSTER ABANDONT THE PLAYER
		if(onPath == true && tileDistance > 10) {
		
				onPath = false;
			
		}
		
		
		
		
		
	}
	public void setAction() {
		if(onPath == true) {
			int goalCol = (gp.player.x + gp.player.protectedArea.x)/gp.tileSize;
			int goalRow = (gp.player.y + gp.player.protectedArea.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
			
			int i = new Random(). nextInt(200)+1;
			if(i > 99 && project.alive == false) {
				project.set(x, y, Direction, true, this);
				gp.projectileList.add(project);
				
			}
		}else {
			Timer++;
			if(Timer == 120 ) {//2s
				Random random = new Random();
				int j = random.nextInt(100)+1;
				if(j <=25 ) {
					Direction = "left";
				}
				if(j> 25 && j <= 50) {
					Direction = "right";
				}
				if(j> 50 && j <= 75) {
					Direction = "up";
				}
				if(j> 75 && j <= 100) {
					Direction = "down";
				}
				Timer = 0;
				
				
			}
		}
		
		
	}
	
	
	//DAMAGE 
	public void damageReaction() {
		Timer = 0;
		onPath = true; 
	}
	}
