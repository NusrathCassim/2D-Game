package main;

import java.util.Random;

import object.Object_blueRock;
import object.Object_rock;

public class Object_Methods {
	GamePanel gp;

	Random random = new Random();
	
	public Object_Methods(GamePanel gp) {
		this.gp = gp;
	}

	
	public void setObject() {
//		
//		int obj_x = (random.nextInt((int)(gp.screenWidth/gp.tileSize)))*gp.tileSize;
//		int obj_y = (random.nextInt((int)(gp.screenWidth/gp.tileSize)))*gp.tileSize;
		 
//	    gp.obj[1] = new Object_blueRock();
//    	gp.obj[1].obj_x = 11*gp.tileSize;
//        gp.obj[1].obj_y = 4*gp.tileSize;
        
        
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
	
	
	
}
