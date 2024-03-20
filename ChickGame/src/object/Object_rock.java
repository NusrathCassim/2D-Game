package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_rock extends MainObject{
	public Object_rock() {
		name = "rock_lv1";
		try {
			image = ImageIO.read(getClass().getResource("/objects/FireRock.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
