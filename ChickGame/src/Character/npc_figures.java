package Character;

import java.util.Random;

import main.GamePanel;
public class npc_figures extends Character{

	public npc_figures(GamePanel gp) {
		super(gp);
		Direction = "left";
		speed = 1;
		getChickImage();
		
	}
	public void getChickImage() {
		
		left1 = setup("/npc/chick3-1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/chick3-2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/chick3-1", gp.tileSize, gp.tileSize);
		
		right1 = setup("/npc/chick2-1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/chick2-2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/chick2-1", gp.tileSize, gp.tileSize);
	}
//	public void update() {
//		spriteCounter++;
//		if(spriteCounter > 20) {
//			if(spriteNum == 1) {
//				spriteNum=2;
//			}else if(spriteNum==2) {
//				spriteNum = 1;
//			}
//			spriteCounter = 0;
//		}
//	}
//	
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
