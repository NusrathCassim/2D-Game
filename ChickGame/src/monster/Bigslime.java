package monster;
import java.util.Random;

import Character.Character;
import main.GamePanel;

public class Bigslime extends Character {
	GamePanel gp;
	public Bigslime(GamePanel gp) {
		super(gp);
		this.gp= gp;
		Direction="left";
		defaultSpeed = 1;
		speed = defaultSpeed;
		MAXLIFE = 10;
		LIFE = MAXLIFE;
		type = 1;
		//project = new obj_Bigslime(gp);
		
		protectedArea.x = 4;
		protectedArea.y = 12;
		protectedArea.width = 80;
		protectedArea.height = 87;
		protectedAreaDeafultX = protectedArea.x;
		protectedAreaDeafultY = protectedArea.y;
		getImage();
	}
	
	private void getImage() {
		int monsterSize = gp.tileSize*2; // Set the monster's size based on the tileSize variable
		up1 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    up2 = setup("/monster/Bigslime2", monsterSize, monsterSize);
	    up3 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    down1 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    down2= setup("/monster/Bigslime2", monsterSize, monsterSize);
	    down3 = setup("/monster/Bigslime1", monsterSize, monsterSize);

	    left1 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    left2 = setup("/monster/Bigslime2", monsterSize, monsterSize);
	    left3 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    right1 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	    right2 = setup("/monster/Bigslime2", monsterSize, monsterSize);
	    right3 = setup("/monster/Bigslime1", monsterSize, monsterSize);
	}
//	public void update() {
//		
//	  super.update();
//	
//		
//	}
	public void setAction() {
		Timer++; //actionlockcounter
		if(Timer == 240 ) {//4s
			Random random = new Random();
			int j = random.nextInt(100)+1;
			if(j <=50) {
				Direction = "left";
			}
			if(j> 50 && j <= 100) {
				Direction = "right";
			}
			Timer = 0;
		}
		}

}
