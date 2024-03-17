package main;
import Character.Character;
public class checkCollision {
	GamePanel gp;
	public checkCollision(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Character Character) {
		int ChleftX = Character.x + Character.protectedArea.x;
		int ChrightX = Character.x + Character.protectedArea.x + Character.protectedArea.width;
		int ChTopY = Character.y + Character.protectedArea.y;
		int ChBottomY = Character.y + Character.protectedArea.y + Character.protectedArea.height;
		
		int ChLeftCol = ChleftX/ gp.tileSize; 
		int ChrightCol = ChrightX/gp.tileSize;
		int ChTopRow = ChTopY/gp.tileSize;
		int ChBottomRow = ChBottomY/gp.tileSize;
		
		int tileNum1, tileNum2;
		switch(Character.Direction) {
		
		case "up":
			ChTopRow = (ChTopY - Character.speed)/gp.tileSize;
			tileNum1 = gp.tile.mapT_Num[ChLeftCol][ChTopRow];
			tileNum2 = gp.tile.mapT_Num[ChrightCol][ChTopRow];
			if(gp.tile.tile[tileNum1].collision == true ||gp.tile.tile[tileNum2].collision == true) {
				Character.collisionOn = true;	
			}
			break;
		case "down":
			ChBottomRow =  (ChBottomY + Character.speed)/gp.tileSize;
			tileNum1 = gp.tile.mapT_Num[ChLeftCol][ChBottomRow];
			tileNum2 = gp.tile.mapT_Num[ChrightCol][ChBottomRow];
			if(gp.tile.tile[tileNum1].collision == true ||gp.tile.tile[tileNum2].collision == true) {
				Character.collisionOn = true;	
			}
			break;
			
		case "left":
			ChLeftCol = (ChleftX - Character.speed)/gp.tileSize;
			tileNum1 = gp.tile.mapT_Num[ChLeftCol][ChTopRow];
			tileNum2 = gp.tile.mapT_Num[ChLeftCol][ChBottomRow];
			if(gp.tile.tile[tileNum1].collision == true ||gp.tile.tile[tileNum2].collision == true) {
				Character.collisionOn = true;	
			}
			break;
			
		case "right":
			ChrightCol= (ChrightX + Character.speed)/gp.tileSize;
			tileNum1 = gp.tile.mapT_Num[ChrightCol][ChTopRow];
			tileNum2 = gp.tile.mapT_Num[ChrightCol][ChBottomRow];
			if(gp.tile.tile[tileNum1].collision == true ||gp.tile.tile[tileNum2].collision == true) {
				Character.collisionOn = true;	
			}
			break;	
		
		}
	}
	
}
