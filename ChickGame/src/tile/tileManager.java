package tile;
import main.ToolBox;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.imageio.ImageIO;
import main.GamePanel;

public class tileManager {
	GamePanel gp;
	public Tile[] tile; //tile is the name
	public int mapT_Num[][];
	
	public tileManager(GamePanel gp) {
		this.gp = gp;
		tile= new Tile[75]; //can change this number
		mapT_Num = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadingMap("/map/map.txt");
	}
	
	public void setup(int index, String imageName, boolean collision) {
		ToolBox tool = new ToolBox();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = tool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getTileImage() {
		//load the tile images
		
			setup(0, "f1", true);
			setup(1, "f2", false);
			setup(2, "f3", true);
			setup(3, "f4", false);
			setup(4, "f5", true);
			setup(5, "f6", true);
			setup(6, "f7", false);
			setup(7, "f8", true);
			setup(8, "f9", true);
			setup(9, "f10", true);
			setup(10, "f11", true);
			setup(11, "f12", false);
			setup(12, "f13", true);
			setup(13, "s1", false);
			setup(14, "s2", false);
			setup(15, "s3", false);
			setup(16, "w1", true);
			setup(17, "w2", true);
			setup(18, "w3", true);
			setup(19, "w4", true);
			setup(20, "w5", true);
			setup(21, "w6", true);
			setup(22, "w7", true);
			setup(23, "w8", true);
			setup(24, "w9", true);
			setup(25, "j1", false);
			setup(26, "j2", true);
			setup(27, "j3", true);
			setup(28, "j4", true);
			setup(29, "j5", true);
			setup(30, "j6", true);
			setup(31, "g1", false);
			setup(32, "g2", false);
			setup(33, "g3", false);
			setup(34, "g4", false);
			setup(35, "g5", false);
			
			//house
			setup(36, "h1", false);
			setup(37, "h2", true);
			setup(38, "h3", true);
			setup(39, "h4", false);
			setup(40, "h5", false);
			setup(41, "h6", true);
			setup(42, "h7", true);
			setup(43, "h8", true);
			setup(44, "h9", false);
			setup(45, "h10", false);
			setup(46, "h11", false);
			setup(47, "h12", false);
			setup(48, "h13", false);
			setup(49, "h14", true);
			setup(50, "h15", true);
			setup(51, "h16", true);
			setup(52, "h17", true);
			setup(53, "h18", true);
			setup(54, "h19", true);
			setup(55, "h20", false);
			setup(56, "h21", true);
			setup(57, "h22", true);
			setup(58, "h23", true);
			setup(59, "h24", true);
			setup(60, "h25", true);
			setup(61, "h26", true);
			setup(62, "h27", true);
			setup(63, "hr28", true);
			setup(64, "hr29", true);
			setup(65, "hr30", true);
			setup(66, "hr31", true);
			setup(67, "ht1", true);
			setup(68, "ht2", true);
			//fence
			setup(69, "f14", false);
			setup(70, "f0", false);
			setup(71, "g6", false);
			
			
	}
	
	public void loadingMap(String filepath) {
		try {
			InputStream Is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(Is)); 
			
			int col =0;
			int row = 0;
			while(col < gp.maxScreenCol && row< gp.maxScreenRow) {
				String line = br.readLine();
				while(col<gp.maxScreenCol) {
					String data[] = line.split(" ");
					int num = Integer.parseInt(data[col]);
					
					mapT_Num[col][row]=num;
					col++;
				}
				if(col== gp.maxScreenCol) {
					col =0;
					row++;
				}
				
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int col =0;
		int row = 0;
		int x= 0;
		int y= 0;
		
		while(col< gp.maxScreenCol && row< gp.maxScreenRow) {
			int tileNum = mapT_Num[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y,null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol ) {
				col =0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
	

	
	
	
	
}
