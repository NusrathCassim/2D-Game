package monster;
import java.util.Random;

import Character.Character;
import main.GamePanel;
import object.obj_Nut;

public class NutMonster extends Character{
	GamePanel gp;
	public NutMonster(GamePanel gp) {
		super(gp);
		this.gp = gp;
		Direction = "left";
		name = "NutMonster";
		defaultSpeed = 1;
		speed = defaultSpeed;
		MAXLIFE = 3;
		LIFE = MAXLIFE;
		type = 2; 
		project = new obj_Nut(gp);
		protectedArea.x = 8;
		protectedArea.y = 18;
		protectedArea.width = 30;
		protectedArea.height = 32;
		protectedAreaDeafultX = protectedArea.x;
		protectedAreaDeafultY = protectedArea.y;
		getImage();
	}
	private void getImage() {
		up1 =  setup("/monster/nutM1", gp.tileSize, gp.tileSize);
		up2 =  setup("/monster/nutM2", gp.tileSize, gp.tileSize);
		up3 =  setup("/monster/nutM3", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/nutM1", gp.tileSize, gp.tileSize);
		down2= setup("/monster/nutM2", gp.tileSize, gp.tileSize);
		down3 =  setup("/monster/nutM3", gp.tileSize, gp.tileSize);
		
		left1 = setup("/monster/nutM1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/nutM2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/nutM3", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/nutM4", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/nutM5", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/nutM6", gp.tileSize, gp.tileSize);
		
	}
	public void update() {
		
		super.update();
		VerifyStartCasing(gp.player, 3, 100);
		//IF MONSTER ABANDONT THE PLAYER
		if(onPath == true) {
		VerifystopChaseStatus(gp.player, 10, 100);
		}
		
	}
	public void setAction() {
		if(onPath == true) {
			//search a direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			
			//check if its shoots
			int i = new Random(). nextInt(200)+1;
			if(i > 99 && project.alive == false) {
				project.set(x, y, Direction, true, this);
				gp.projectileList.add(project);
				
			}
		}else {
			//get Random Direction
			getRandomDirection();
		}
		
	}
	
    public void damageReaction() {
    	Timer = 0;
		onPath = true; 
	}


}
