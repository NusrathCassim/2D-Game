package object;
import Character.Character;
import main.GamePanel;

public class Heart extends Character{
	
	public Heart(GamePanel gp) {
		super(gp);
		name = "Heart";
		image = setup("/objects/fullH");
		image2 = setup("/objects/HalfH");
		image3 = setup("/objects/EmptyH");
	
		
	}
}
