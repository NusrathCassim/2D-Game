package main;

import object.Object_rock;

public class Object_Methods {
	GamePanel gp;
	public Object_Methods(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Object_rock();
		gp.obj[0].obj_x = 1*gp.tileSize;
		gp.obj[0].obj_y = 6*gp.tileSize;
		
		gp.obj[1] = new Object_rock(); 
		gp.obj[1].obj_x = 3*gp.tileSize;
		gp.obj[1].obj_y = 10*gp.tileSize;
	}
	
}
