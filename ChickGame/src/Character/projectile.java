package Character;

import main.GamePanel;

public class projectile extends Character{
	
Character user;
	public projectile(GamePanel gp) {
		super(gp);
		
	}
	public void set(int x, int y, String Direction, boolean alive, Character user) {
		this.x = x;
		this.y = y;
		this.Direction  = Direction;
		this.alive= alive;
		this.user = user;
		this.LIFE = this.MAXLIFE;
		
	}
	public void update() {
		
		if(user == gp.player ) {
			int monsterIndex = gp.checker.checkEntity(this, gp.Monster);
			if(monsterIndex != 999 ) {
				gp.player.damageMonster(monsterIndex);
				//ONCE HITS MONSTER IT DISSAPEAR
				alive = false;
			}
		}
		if(user != gp.player ) {
			boolean contactPlayer = gp.checker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				damageplayer();
				alive = false;
			}
		}
		
		
		
		
		
		
		switch(Direction) {
		case "up":  y-= speed;break; 
		case "down":  y+= speed;	break;
		case "left":  x-= speed;break;
		case "right":  x+= speed;	break;
		}
		LIFE--;
		if(LIFE <=0) {alive = false;}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			
			spriteCounter =0;
		}
		
	}
}
