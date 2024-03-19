package object;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import main.GamePanel;

public class MainObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int obj_x, obj_y;
	public Rectangle protectedArea = new Rectangle(0,0,48,48);
	public int 	protectedAreaDeafultX =0;
	public int protectedAreaDeafultY = 0;
	Random random;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
//		if(obj_x + gp.tileSize > gp.player.x &&
//			obj_x - gp.tileSize < gp.player.x &&
//			obj_y + gp.tileSize > gp.player.y &&
//			obj_y - gp.tileSize < gp.player.y ) {}
			
		  
			g2.drawImage(image, obj_x, obj_y,gp.tileSize, gp.tileSize, null);

	}
	
}
