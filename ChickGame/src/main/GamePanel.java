package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import AI.pathFinder;
import Character.Character;
import Character.Player;
import tile.tileManager;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 15;
    final int scale = 3;
  
    public final int tileSize = originalTileSize * scale;//display
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight =  tileSize*maxScreenRow;
    
    
    int FPS = 60;
    public tileManager tile = new tileManager(this);
    public KeyHandler KeyH = new KeyHandler(this);
 
    //sound class
    Sound sound = new Sound();
    
    
    //collision
    public pathFinder pathfinder = new pathFinder(this);
    public checkCollision checker = new checkCollision(this);
    public Object_Methods methods = new Object_Methods(this);
    //UI
    public ScreenUI ui = new ScreenUI(this);
    
    Thread gameThread;
    
    
    public Player player = new Player(this, KeyH, methods);
   
    public Character obj[] = new Character[10]; //display up to 10 object at the same time
    public Character npc[] = new Character[10];
    public Character Monster[] = new Character[20];
    public ArrayList<Character> projectileList = new ArrayList<>();
    
    ArrayList<Character> Allentity = new ArrayList<>();
    
    
    //game state
    public int gameState;
    public final int playMode = 1;
    public final int pauseMode= 2;
    public final int gameoverState = 8;
    public final int MainState =0;
    public final int gameWin =3;
    
    Character character = new Character(this);
    boolean specialAttack = character.isSpecialAttack();
    
    
    int noOfRemainingMonster = player.updateDiedMonsters();
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
                
    }
  
    
    //SETTING UP THE GAME
    public void setupObject() {
    	methods.setObject();
    	methods.setNPC();
    	methods.setMonster();
    	
    	
    	//playMusic(1);
    	gameState = MainState;
    }
    public void retry() {
    	player.RestorePosition();
    	player.restoreLife() ;
    	methods.setNPC();
    	
    	if (noOfRemainingMonster > 0 && specialAttack == true) {
            methods.setBigSlime();
            methods.setMonster();
            }
    	if(noOfRemainingMonster == 0 && specialAttack == true) {
    		 methods.setBigSlime();
    	}
           
        
    }
    public void restart() {
    	player.setDefaultValue();
    	methods.setObject();
    	methods.setNPC();
    	methods.setMonster();
    	
    }
    
    public void startGameThread() {
    	gameThread = new Thread(this);
    	gameThread.start();
    }

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; //0.016666s
		double nextDrawTime = System.nanoTime()+ drawInterval;
		
		while(gameThread != null) {
			
			// update information of character position
			update();
			// draw the screen with updated information
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime < 0) {
					remainingTime =0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		if(gameState == playMode)
		{
			player.update();
			//NPC
			for(int i = 0 ; i<npc.length; i++) {
				if(npc[i]!= null) {
					npc[i].update();
				}
			}
			//MONSTER
			for(int i = 0 ; i<Monster.length; i++) {
				if(Monster[i]!= null) {
					Monster[i].update();
				}
			}
			//AX
			for(int i = 0 ; i< projectileList.size(); i++) {
				if(projectileList.get(i)!= null) {
					if(projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}

		}
		if(gameState == pauseMode) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//MAIN SCREEN
		if(gameState == MainState) {
			ui.draw(g2);
		}
		//OTHER
		else {
			//tile
			tile.draw(g2);
			Allentity.add(player);
			
			//object
			for(int i =0; i<obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			//NPC
			for(int i =0; i< npc.length; i++) {
				if(npc[i] != null) {
					Allentity.add(npc[i]);
				}
			}
			//MONSTER
			for(int i = 0 ; i < Monster.length; i++) {
				if(Monster[i]!= null) {
					Allentity.add(Monster[i]);
				}
			}
			//AX;
			for(int i = 0 ; i < projectileList.size(); i++) {
				if(projectileList.get(i)!= null) {
					Allentity.add(projectileList.get(i));
				}
			}
			//sorting
			Collections.sort(Allentity, new Comparator<Character>() {

				@Override
				public int compare(Character c1, Character c2) {
					int result = Integer.compare(c1.y, c2.y);
					return result;
				}

			});
			//DRAW
			for(int i= 0; i < Allentity.size(); i++) {
				Allentity.get(i).draw(g2);
			}
			//empty the entity list
			Allentity.clear();
			ui.draw(g2);
			
		}
		
		
	}
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	public void stopMusic() {
		sound.stop();
	}
	public void playSE(int i) { //sound effect
		sound.setFile(i);
		sound.play();
	}

}
