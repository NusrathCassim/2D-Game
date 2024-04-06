package main;

import java.util.Random;

import Character.npc_figure_2;
import Character.npc_figures;
import monster.m_redBean;
import object.Object_rock;
import object.specialObject;

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
	public void setSpecialobj() {
		gp.obj[1] = new specialObject(gp);
		gp.obj[1].obj_x = gp.tileSize*6;
		gp.obj[1].obj_y= gp.tileSize*4;
	}
	
	public void setNPC() {
		gp.npc[0] = new npc_figures(gp);
		gp.npc[0].x = gp.tileSize*3; 
		gp.npc[0].y = gp.tileSize*9;
		
		gp.npc[1] = new npc_figure_2(gp);
		gp.npc[1].x = gp.tileSize*5; 
		gp.npc[1].y = gp.tileSize*10;
		
	}
	public void setMonster() {
		gp.Monster[0] = new m_redBean(gp);
		gp.Monster[0].x = gp.tileSize*17;
		gp.Monster[0].y = gp.tileSize*11;
		gp.Monster[1] = new m_redBean(gp);
		gp.Monster[1].x = gp.tileSize*14;
		gp.Monster[1].y = gp.tileSize*10;
	}
}
