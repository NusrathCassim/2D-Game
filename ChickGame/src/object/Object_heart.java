package object;
import Character.Character;
import main.GamePanel;

public class Object_heart extends Character {

	public Object_heart(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "Heart";
		image = setup("/objects/fullH");
		image2 = setup("/objects/HalfH");
		image3 = setup("/objects/EmptyH");
	}

}
