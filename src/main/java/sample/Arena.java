package sample;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
	private final int numOfColumn; //number of Columns of grid in the arena. This is not the arena height in pixel
	private final int numOfRow; //number of Rows of grid in the arena. This is not the arena width in pixel
	
	private boolean [][] isGreen; 	// true if the grid at grid coordinate [x][y] is green, 
									//x specifies Row number
									//y specifies Column number
	
	private ArrayList <Monster> monsters; //ArrayList of monsters on the arena
	private ArrayList <BasicTower> towers; //ArrayList of towers on the arena
	
	public static Random rand = new Random();
	
	public Arena(int numOfColumn, int numOfRow, boolean [][] isGreen) { //constructor of Arena class
		this.numOfColumn = numOfColumn;
		this.numOfRow = numOfRow;
		
		monsters = new ArrayList<Monster>(0); 
	    //towers = new ArrayList<Tower>(0); 
	
		
		if(numOfRow != isGreen.length) { //check Column size
			System.out.println("Arena constructor invalid input: Column Size Mismatch");
		}
		
		if(numOfColumn != isGreen[0].length) {
			System.out.println("Arena constructor invalid input: Row Size Mismatch");
		}
		
		int RowSize = isGreen[0].length;
		for(int i = 0;i<isGreen.length;i++) { //check that all Row has same size
			if(isGreen[i].length != RowSize) {
				System.out.println("Arena constructor invalid input: non-uniform Row size");
			}
		}
		
		
		this.isGreen = new boolean[numOfRow][numOfColumn];
		
		
		// Note from Raymond:
		// I think it's better if we initialize isGreen in here instead of passing it in as an argument
		// something like this:
		
//		for(int i = 0; i < numOfRow;i++) {
//			for(int j = 0; j < numOfColumn;j++) {
//				if (j % 2 == 0) {
//					//do additional checking to decide isGreen[i][j]
//				} else {
//					//do additional checking
//				}
//			}
//		
//		}
			
		for(int i = 0; i < numOfRow;i++) {
			for(int j = 0; j < numOfColumn;j++) {
				this.isGreen[i][j] = isGreen[i][j];
			}
		}
		
		
		System.out.println("Constructor Ends");
			
	}
	
	
	
	boolean isGreenGrid(int x, int y) { // returns true if grid at grid coordinate (x,y) is a green grid
		return isGreen[x][y];
	}
	
	void update() { 
		/*advance the arena by one frame:
			Things done in this function():
				1. loop through each monster to let each tower attack it
				2. move monsters and generate a monster if needed (I think this is part of task for Raymond) 
		*/
		
		//1. loop through each monster to let each tower attach it
		
		for (int i=0; i<monsters.size(); i++) {
			
			Monster currentMonster = monsters.get(i);
//			int x = currentMonster.getX();
//			int y = currentMonster.getY();
			
			for(int j = 0;j<towers.size();j++) {
				towers.get(j).shoot(currentMonster);
			}
			
			if (currentMonster.getHp() <= 0) {
//				currentMonster.removeFromArena();
			}
		}
		
		
		//2. move monsters and generate a monster if needed (I think this is part of task for Raymond) 
		
		for (int i=0; i<monsters.size(); i++) {
			Monster currentMonster = monsters.get(i);
//			currentMonster.moveAtEachFrame();
		}
		
		Monster monster;
		
		int typeOfMonster = rand.nextInt(3);
		if (typeOfMonster == 0) {
			monster = new Fox();
		} else if (typeOfMonster == 1) {
			monster = new Unicorn();
		} else {
			monster = new Penguin();
		}
		
		// need to increase difficulty of monster as game goes on
		// every two frames, increase speed by 1
		// so if (currentFrame - previousFrame) > 1 
		monster.setSpeed(monster.getSpeed() + 1);
		// previousFrame = currentFrame
		
		
		
	}
	
	/* temp main() used for testing Arena class
	public static void main(String[] args) {
        System.out.println("running this main");
        boolean [][] isGreen = 	{	{true,false},
        							{false,true},
        							{true,true}
        						};
        Arena arena = new Arena(2,3,isGreen);
        System.out.println(arena.isGreenGrid(2, 0));
    }
    */
    
	
}
