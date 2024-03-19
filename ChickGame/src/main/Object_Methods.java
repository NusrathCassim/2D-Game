package main;

import java.util.Random;
import object.Object_rock;
import tile.Tile;
import tile.tileManager;

public class Object_Methods {
	GamePanel gp;

	Random random = new Random();
	
	public Object_Methods(GamePanel gp) {
		this.gp = gp;
	}

	
	public void setObject() {
		gp.obj[0] = new Object_rock();
		gp.obj[0].obj_x = (random.nextInt((int)(gp.screenWidth/gp.tileSize)))*gp.tileSize;
		gp.obj[0].obj_y = (random.nextInt((int)(gp.screenHeight/gp.tileSize)))*gp.tileSize;
//		tileManager manager = new tileManager(gp);
//		  gp.obj[0] = new Object_rock();
//
//	        // Get reachable tiles
//	        Tile[] reachableTiles = manager.getreachableTiles();
//
//
//	        // Generate random coordinates only from reachable tiles
//	        int randomIndex =  random.nextInt(reachableTiles.length);
//	        gp.obj[0].obj_x = randomIndex* gp.tileSize;
//	        gp.obj[0].obj_y = randomIndex * gp.tileSize;
	
	}
	
	
	
}
