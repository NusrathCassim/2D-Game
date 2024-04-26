package monster;
import java.util.Random;

import Character.Character;
import main.GamePanel;
import object.obj_blueBean;

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
		project = new obj_blueBean(gp);
		
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
	    right1 = setup("/monster/Bigslime3", monsterSize, monsterSize);
	    right2 = setup("/monster/Bigslime4", monsterSize, monsterSize);
	    right3 = setup("/monster/Bigslime3", monsterSize, monsterSize);
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
			if(j <=25 ) {Direction = "left";}
			if(j> 25 && j <= 50) {Direction = "right";}
			if(j> 50 && j <= 75) {Direction = "up";}
			if(j> 75 && j <= 100) {Direction = "down";}
			Timer = 0;
			
	
		}
		int i = new Random(). nextInt(200)+1;
		if(i > 99 && project.alive == false) {
			project.set(x, y, Direction, true, this);
			gp.projectileList.add(project);
		}
		
		
		
	}
	
    public void damageReaction() {
		Timer++;
		Direction = gp.player.Direction;
	}

}
