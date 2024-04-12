package object;

import Character.projectile;
import main.GamePanel;

public class obj_redBean extends projectile {
	GamePanel gp;
	
	public obj_redBean(GamePanel gp) {
		super(gp);
		this.gp = gp;
		alive = false;
		MAXLIFE = 80;
		LIFE = MAXLIFE;
		name = "Bean";
		speed = 6;
		attack = 1;
		getImage();
		
	}
	public void getImage() {
		up1 =setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		up2 = setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		down1 = setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		down2 =setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		
		left1=setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		left2=setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		right1=setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		right2=setup("/Ax/redBean", gp.tileSize, gp.tileSize);
		
	
	}
}
