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
		defaultSpeed = 1;
		speed = defaultSpeed;
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
		up2 =  setup("/monster/Monster2", gp.tileSize, gp.tileSize);
		up3 =  setup("/monster/Monster3", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		down2= setup("/monster/Monster2", gp.tileSize, gp.tileSize);
		down3 =  setup("/monster/Monster3", gp.tileSize, gp.tileSize);
		
		left1 = setup("/monster/Monster1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Monster2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/Monster3", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/MonsterR1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/MonsterR2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/MonsterR3", gp.tileSize, gp.tileSize);
	}
	//update methods here, sprite no are different from default one
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
	
	
	//DAMAGE 
	public void damageReaction() {
		Timer = 0;
		onPath = true; 
	 }
	}
