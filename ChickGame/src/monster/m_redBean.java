package monster;
import java.util.Random;

import Character.Character;
import main.GamePanel;

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
		
		protectedArea.x = 9;
		protectedArea.y = 15;
		protectedArea.width = 30;
		protectedArea.height = 33;
		protectedAreaDeafultX = protectedArea.x;
		protectedAreaDeafultY = protectedArea.y;
		getImage();
	}
	public void getImage() {
		left1 = setup("/monster/Monster1");
		left2 = setup("/monster/Monster2");
		left3 = setup("/monster/Monster3");
		right1 = setup("/monster/MonsterR1");
		right2 = setup("/monster/MonsterR2");
		right3 = setup("/monster/MonsterR3");
	}
	//update methods here, sprite no are different from default one
	public void update() {
		setAction();
		collisionOn = false;
		gp.checker.checkTile(this);//pass the npc 
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
//			else if (spriteNum == 4) {
//				spriteNum = 1;
//			}
			spriteCounter = 0;
		}
	}
	public void setAction() {
		Timer++;
		if(Timer == 240 ) {//4s
			Random random = new Random();
			int j = random.nextInt(100)+1;
//			if(j <=50 ) {
//				Direction = "right";
//			}
			if(j< 50 && j <= 100) {
				Direction = "left";
			}
			Timer = 0;
		}
	}
}
