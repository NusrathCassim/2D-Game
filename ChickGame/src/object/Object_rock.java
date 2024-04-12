package object;
import Character.Character;
import main.GamePanel;

public class Object_rock extends Character{
	
	
	public Object_rock(GamePanel gp) {
		super(gp);
		
		name = "rock_lv1";
		image = setup("/objects/FireRock", gp.tileSize, gp.tileSize);
		
	}
}
