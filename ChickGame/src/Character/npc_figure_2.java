package Character;

import java.util.Random;

import main.GamePanel;
public class npc_figure_2 extends Character {

	public npc_figure_2(GamePanel gp) {
		super(gp);
		Direction = "right";
		speed = 1;
		getDarkChick();
	}
	
	public void getDarkChick() {
		left1 = setup("/npc/chick4-2", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/chick4-3", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/chick4-2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/chick4", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/chick4-0", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/chick4", gp.tileSize, gp.tileSize);
	}

	public void setAction() {
		Timer++;
		if(Timer == 240 ) {//4s
			Random random = new Random();
			int j = random.nextInt(100)+1;
			if(j <=50) {
				Direction = "right";
			}
			if(j> 50 && j <= 100) {
				Direction = "left";
			}
			Timer = 0;
		}
		
	}
}
