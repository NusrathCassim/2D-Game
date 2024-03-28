package Character;

import main.GamePanel;
import java.util.Random;
public class npc_figures extends Character{

	public npc_figures(GamePanel gp) {
		super(gp);
		Direction = "left";
		speed = 1;
		getChickImage();
		
	}
	public void getChickImage() {
		
		left1 = setup("/npc/chick3-1");
		left2 = setup("/npc/chick3-2");
		left3 = setup("/npc/chick3-3");
		left4 = setup("/npc/chick3-4");
		right1 = setup("/npc/chick2-1");
		right2 = setup("/npc/chick2-2");
		right3 = setup("/npc/chick2-3");
		right4 = setup("/npc/chick2-4");
	}
	
	public void setAction() {
		Timer++;
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
