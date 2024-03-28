package Character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ToolBox;

public class Character {
	GamePanel gp;
	public int x, y;
	public int speed;
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, left4,
						right1, right2, right3, right4;
	
	public String Direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	
	//colliding
	public Rectangle protectedArea = new Rectangle(0, 0, 48, 48);
	public int protectedAreaDeafultX, protectedAreaDeafultY;
	public boolean collisionOn = false;
	
	public int Timer= 0;
	

	public Character(GamePanel gp){
		this.gp =gp;
	}
	public void setAction() {
		
	}
	public void update() {
		setAction();
		collisionOn = false;
		gp.checker.checkTile(this);//pass the npc 
		if(collisionOn == false) {
			switch(Direction) {
			case "left":
				x-= speed;	
					break;								
			case "right":
				x+= speed;	
				break;	
			}
		}
		spriteCounter++;
		if(spriteCounter > 20) {
			if(spriteNum == 1) {
				spriteNum=2;
			}else if(spriteNum==2) {
				spriteNum = 1;
			}
//			else if (spriteNum == 3) {
//				spriteNum = 4;
//			}
//			else if (spriteNum == 4) {
//				spriteNum = 1;
//			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(Direction) {
		case "left":
			if(spriteNum ==1) {
				image = left1;
			}
			if(spriteNum ==2) {
				image = left2;
			}
			if(spriteNum ==3) {
				image = left3;
			}
			if(spriteNum ==4) {
				image = left4;
			}
			break;
		case "right":
			if(spriteNum ==1) {
				image = right1;
			}
			if(spriteNum ==2) {
				image = right2;
			}
			if(spriteNum ==3) {
				image = right3;
			}
			if(spriteNum ==4) {
				image = right4;
			}
			break;	
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	public BufferedImage setup(String imagepath) {
		ToolBox tool = new ToolBox();
		BufferedImage Image = null;
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagepath +".png"));
			Image = tool.scaleImage(Image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return Image;
	}
	
	
}
