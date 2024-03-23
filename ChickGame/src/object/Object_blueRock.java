package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_blueRock extends MainObject{
	public Object_blueRock() {
		name = "rock_lv2";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/BlueRock.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
