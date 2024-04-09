package AI;
import java.util.ArrayList;

import Character.Character;
import main.GamePanel;

public class pathFinder {
	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public pathFinder(GamePanel gp) {
		this.gp = gp;
		initializingNode();
	}
	public void initializingNode() {
		node = new Node[gp.maxScreenCol][gp.maxScreenRow];
		
		int col =0 ;
		int row = 0;
		
		while(col< gp.maxScreenCol && row<gp.maxScreenRow) {
			node[col][row] = new Node(col, row);
			col++;
			if(col == gp.maxScreenCol) {
				col =0;
				row++;
			}
		}
	}
	
	public void resetNode() {
		int col =0;
		int row = 0;
		while(col< gp.maxScreenCol && row<gp.maxScreenRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			
			col++;
			if(col == gp.maxScreenCol) {
				col =0;
				row++;
			}
			
			}
		
		//reset  other settings
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	
	public void setNode(int startCol, int startRow, int goalCol, int goalRow, Character Character) {
		 resetNode();
		 
		 startNode = node[startCol][startRow];
		 currentNode = startNode;
		 goalNode = node[goalCol][goalRow];
		 openList.add(currentNode);
		   int col =0;
			int row = 0;
			while(col< gp.maxScreenCol && row<gp.maxScreenRow) {
				int tileNum = gp.tile.mapT_Num[col][row];
				if(gp.tile.tile[tileNum].collision == true) {
					node[col][row].solid = true;
				}
				//since i dont have interactive tiles no need to check that part
				getCost(node[col][row]);		
				col++;
				if(col == gp.maxScreenCol) {
					col =0;
					row++;
				}
				
				
		}

	}
	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gcost = xDistance + yDistance;
		
		//hcost
		 xDistance = Math.abs(node.col - goalNode.col);
		 yDistance = Math.abs(node.row - goalNode.row);
		node.hcost = xDistance + yDistance;
		
		//fcost
		node.fcost = node.gcost + node.hcost;
	}
	
	public boolean search() {
		while(goalReached ==  false && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			if(row -1 >= 0 )
			{
				openNode(node[col][row-1]);
			}
			if(col -1 >= 0 )
			{
				openNode(node[col-1][row]);
			}
			if(row +1 < gp.maxScreenRow )
			{
				openNode(node[col][row+1]);
			}
			if(col +1 < gp.maxScreenCol) {
				openNode(node[col+1][row]);
			}
		//find the best node
			int bestNodeindex = 0;
			int bestNodefCost = 999;
			for (int i = 0; i < openList.size(); i++ ) {
				if(openList.get(i).fcost < bestNodefCost) {
					bestNodeindex = i;
					bestNodefCost = openList.get(i).fcost;
				}
				else if(openList.get(i).fcost == bestNodefCost) {
					if(openList.get(i).gcost < openList.get(bestNodeindex).gcost) {
						bestNodeindex = i;
					}
				}
			}
			//IF NO NODE, THEN WE END
			if(openList.size() == 0) {
				break;
			}
			currentNode = openList.get(bestNodeindex);
			if(currentNode == goalNode) {
				goalReached =  true;
				trackPath();
			}
			step++;
		}
		return goalReached;
	}
		
		
		public void openNode(Node node) {
			if(node.open == false && node.checked == false && node.solid == false) {
				
				node.open = true;
				node.parent = currentNode;
				openList.add(node);
				
			}
		
		}
		public void trackPath() {
			Node current = goalNode;
			while(current != startNode) {
				pathList.add(0, current);
				current = current.parent;
			}
		}
	
	
	
	
}
