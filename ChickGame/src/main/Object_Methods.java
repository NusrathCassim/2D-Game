package main;

import java.util.Random;
import object.Object_rock;
import Character.npc_figure_2;
import Character.npc_figures;

public class Object_Methods {
	GamePanel gp;
	Random random = new Random();
	
	public Object_Methods(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {      
        
		boolean validPositionFound = false;
	    do {
	        int obj_x = (random.nextInt((int)(gp.screenWidth/gp.tileSize)))*gp.tileSize;
	        int obj_y = (random.nextInt((int)(gp.screenHeight/gp.tileSize)))*gp.tileSize;

	        // Check if the random position is on a tile with number 3
	        int randomTileNumX = gp.tile.mapT_Num[obj_x / gp.tileSize][obj_y / gp.tileSize];
	        int randomTileNumY = gp.tile.mapT_Num[obj_x / gp.tileSize][obj_y / gp.tileSize]; // Check the tile below as well
	        if (randomTileNumX == 13 || randomTileNumY == 13 ||randomTileNumX == 32 ||randomTileNumY == 32 ) {
	           
	                gp.obj[0] = new Object_rock(gp);
	                gp.obj[0].obj_x = obj_x;
	                gp.obj[0].obj_y = obj_y;
	                validPositionFound = true;
	            }
	        
	    } while (!validPositionFound);
	
	}
	
	public void setNPC() {
		gp.npc[0] = new npc_figures(gp);
		gp.npc[0].x = gp.tileSize*3; 
		gp.npc[0].y = gp.tileSize*9;
		
		gp.npc[1] = new npc_figure_2(gp);
		gp.npc[1].x = gp.tileSize*5; 
		gp.npc[1].y = gp.tileSize*10;
	}
	
}
