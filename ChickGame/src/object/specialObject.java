package object;
import Character.Character;
import main.GamePanel;

public class specialObject extends Character {
	public specialObject(GamePanel gp) {
		super(gp);
		name = "carrot";
		image = setup("/objects/carrot", gp.tileSize, gp.tileSize);
	}

	
		
		
		
		
	
}
