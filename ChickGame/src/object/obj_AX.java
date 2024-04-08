package object;

import Character.projectile;
import main.GamePanel;

public class obj_AX extends projectile{
	GamePanel gp;

	public obj_AX(GamePanel gp) {
		super(gp);
		this.gp = gp;
		alive = false;
		MAXLIFE = 80;
		LIFE = MAXLIFE;
		name = "ax";
		speed = 8;
		attack = 2;
		getImage();
		
	}
	public void getImage() {
		up1 =setup("/Ax/rotate1", gp.tileSize, gp.tileSize);
		up2 = setup("/Ax/rotate2", gp.tileSize, gp.tileSize);
		down1 = setup("/Ax/rotate3", gp.tileSize, gp.tileSize);
		down2 =setup("/Ax/rotate4", gp.tileSize, gp.tileSize);
		
		left1=setup("/Ax/rotate1", gp.tileSize, gp.tileSize);
		left2=setup("/Ax/rotate2", gp.tileSize, gp.tileSize);
		right1=setup("/Ax/rotate3", gp.tileSize, gp.tileSize);
		right2=setup("/Ax/rotate4", gp.tileSize, gp.tileSize);
		
	
	}
}
