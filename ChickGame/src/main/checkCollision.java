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
		String Direction = Character.Direction;
		if(Character.knockback == true) {
			Direction = Character.knockbackDirection;
		}
		
		
		
		
		switch(Direction) {
		
		case "up":
			ChTopRow = (ChTopY - Character.speed)/gp.tileSize;
			tileNum1 = gp.tile.mapT_Num[ChLeftCol][ChTopRow]; //number that i have assigned for the tiles ex flower tile=1
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
		
		
		public int checkObject(Character Character, boolean player) {
			int index = 999;
			String Direction = Character.Direction;
			if(Character.knockback == true) {
				Direction = Character.knockbackDirection;
			}
			
			for(int i =0; i<gp.obj.length; i++) {
				if(gp.obj[i] != null ) {
					//characters solid area position
					Character.protectedArea.x = Character.x + Character.protectedArea.x;
					Character.protectedArea.y = Character.y + Character.protectedArea.y;
					
					
					//objects solid area position
					gp.obj[i].protectedArea.x = gp.obj[i].obj_x + gp.obj[i].protectedArea.x;
					gp.obj[i].protectedArea.y = gp.obj[i].obj_y + gp.obj[i].protectedArea.y;
					
					
					switch(Direction) {
					case "up":
						Character.protectedArea.y -= Character.speed;
						break;
					case "down":
						Character.protectedArea.y += Character.speed;
						break;
					case "left":
						Character.protectedArea.x -= Character.speed;
						break;
					case "right":
						Character.protectedArea.x += Character.speed;
						break;
					}
					if(Character.protectedArea.intersects(gp.obj[i].protectedArea)) {
						if(gp.obj[i].collision == true) {
							Character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					Character.protectedArea.x = Character.protectedAreaDeafultX;
					Character.protectedArea.y = Character.protectedAreaDeafultY;
					gp.obj[i].protectedArea.x = gp.obj[i].protectedAreaDeafultX;
					gp.obj[i].protectedArea.y = gp.obj[i].protectedAreaDeafultY;
					
				}
			}
			
			return index;
		}
		//MOSTER AND
		public int checkEntity(Character Character, Character[] target) {
				int index = 999;
				String Direction = Character.Direction;
				if(Character.knockback == true) {
					Direction = Character.knockbackDirection;
				}
				
			for(int i =0; i<target.length; i++) {
				if(target[i] != null ) {
					//characters solid area position
					Character.protectedArea.x = Character.x + Character.protectedArea.x;
					Character.protectedArea.y = Character.y + Character.protectedArea.y;
					
					
					//objects solid area position
					target[i].protectedArea.x = target[i].x + target[i].protectedArea.x;
					target[i].protectedArea.y = target[i].y + target[i].protectedArea.y;
					
					
					switch(Direction) {
					case "up":Character.protectedArea.y -= Character.speed; break;
					case "down":Character.protectedArea.y += Character.speed; break;
					case "left": Character.protectedArea.x -= Character.speed;break;
					case "right":Character.protectedArea.x += Character.speed;break;
					}
					if(Character.protectedArea.intersects(target[i].protectedArea)){
						if(target[i] != Character) {
							Character.collisionOn = true;
							index = i;	
						}
					}
					Character.protectedArea.x = Character.protectedAreaDeafultX;
					Character.protectedArea.y = Character.protectedAreaDeafultY;
					target[i].protectedArea.x = target[i].protectedAreaDeafultX;
					target[i].protectedArea.y = target[i].protectedAreaDeafultY;
					
				}
			}
			
			return index;
		}
		
		public boolean checkPlayer(Character Character) {
			String Direction = Character.Direction;
			if(Character.knockback == true) {
				Direction = Character.knockbackDirection;
			}
			
			boolean contactPlayer = false;
			//characters solid area position
			Character.protectedArea.x = Character.x + Character.protectedArea.x;
			Character.protectedArea.y = Character.y + Character.protectedArea.y;
			
			
			//objects solid area position
			gp.player.protectedArea.x = gp.player.x + gp.player.protectedArea.x;
			gp.player.protectedArea.y = gp.player.y + gp.player.protectedArea.y;
			
			
			switch(Direction) {
			case "up":Character.protectedArea.y -= Character.speed; break;
			case "down":Character.protectedArea.y += Character.speed; break;
			case "left": Character.protectedArea.x -= Character.speed;break;
			case "right":Character.protectedArea.x += Character.speed;break;
			}
			if(Character.protectedArea.intersects(gp.player.protectedArea)){
				
					Character.collisionOn = true;
					contactPlayer = true;
				
			}
			Character.protectedArea.x = Character.protectedAreaDeafultX;
			Character.protectedArea.y = Character.protectedAreaDeafultY;
			gp.player.protectedArea.x =gp.player.protectedAreaDeafultX;
			gp.player.protectedArea.y = gp.player.protectedAreaDeafultY;
			return contactPlayer;
		}
		
	}
	

