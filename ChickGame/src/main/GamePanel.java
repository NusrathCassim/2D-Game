package main;
import object.MainObject;
import tile.tileManager;
import Character.Player;
import Character.Character;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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
    KeyHandler KeyH = new KeyHandler(this);
    //sound class
    Sound sound = new Sound();
    
    
    //collision
   
    public checkCollision checker = new checkCollision(this);
    public Object_Methods methods = new Object_Methods(this);
    //UI
    public ScreenUI ui = new ScreenUI(this);
    
    Thread gameThread;
    
    
    public Player player = new Player(this, KeyH, methods);
    public MainObject obj[] = new MainObject[20]; //display up to 10 object at the same time
    //public MainObject special_obj[] = new MainObject[10]; //for special objects
    public Character npc[] = new Character[10];
    
    //game state
    public int gameState;
    public final int playMode = 1;
    public final int pauseMode= 2;
    
    
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
                
    }
    
    public void setupObject() {
    	methods.setObject();
    	methods.setNPC();
    	//playMusic(1);
    	gameState = playMode;
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
		}
		if(gameState == pauseMode) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		//tile
		tile.draw(g2);
		
		//object
		for(int i =0; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//NPC
		for(int i =0; i< npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		//player
		player.draw(g2);
		//
		ui.draw(g2);
		g2.dispose();
		
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
