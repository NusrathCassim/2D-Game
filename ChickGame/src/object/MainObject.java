package object;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import main.GamePanel;

public class MainObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int obj_x, obj_y;
	public Rectangle protectedArea = new Rectangle(0,0,48,48);
	public int 	protectedAreaDeafultX =0;
	public int protectedAreaDeafultY = 0;
	
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = obj_x - gp.player.x ;
		int screenY = obj_y - gp.player.y;
		
//		if(obj_x + gp.tileSize > gp.player.x &&
//			obj_x - gp.tileSize < gp.player.x &&
//			obj_y + gp.tileSize > gp.player.y &&
//			obj_y - gp.tileSize < gp.player.y ) {
			
			g2.drawImage(image, obj_x, obj_y,gp.tileSize, gp.tileSize, null);
//		}
	}
}
