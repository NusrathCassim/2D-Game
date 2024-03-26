package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Object_rock extends MainObject{
	GamePanel gp;
	
	public Object_rock(GamePanel gp) {
		this.gp = gp;
		
		name = "rock_lv1";
		try {
			image = ImageIO.read(getClass().getResource("/objects/FireRock.png"));
			tool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
