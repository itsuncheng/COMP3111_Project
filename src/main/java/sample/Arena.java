package sample;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Arena class contains all the information that describes the state of a the Arena. 
 * Including Arena dimensions, Monsters on the Arena, Towers on the Arena, and more
 */
public class Arena {
	
	/**
	 * The number of frames that have passed, analogous to time
	 */
	private int time; 
	
	/**
	 * number of columns of grid in the arena.
	 */
	private final int numOfColumn; 
	
	/**
	 * number of rows of grid in the arena.
	 */
	private final int numOfRow; //number of Rows of grid in the arena. This is not the arena width in pixel
	
	
	/**
	 *  true if the grid at grid coordinate [row][Column] is green
	 */
	private boolean [][] isGreen; 	// true if the grid at grid coordinate [row][Column] is green, 
	
	/**
	 * true if the arena is a valid arena, false otherwise. Made for testing isGreen
	 */
	public boolean isValidArena; //true if the arena is a valid arena, false otherwise. Made for testing isGreen
	
	/**
	 * ArrayList of monsters on the arena
	 */
	public ArrayList <Monster> monsters; //ArrayList of monsters on the arena
	
	/**
	 * ArrayList of towers on the arena
	 */
	public ArrayList <BasicTower> towers; //ArrayList of towers on the arena
	
	/**
	 * The amount of money(Resource) the player has
	 */
	private int money;
	
	/**
	 * No Longer used
	 */
	public static Random rand = new Random();
	
	/**
	 * Constructor for Arena class
	 * @param numOfColumn The number of Columns on the arena
	 * @param numOfRow The numbers of Rows on the arena
	 * @param isGreen A 2D array where isGreen[row][column] is true if grid at grid coordinate (row,column) is a green grid
	 */
	public Arena(int numOfColumn, int numOfRow, boolean [][] isGreen) { //constructor of Arena class
		this.numOfColumn = numOfColumn;
		this.numOfRow = numOfRow;
		
		time = 0;
		
		isValidArena = true;
		
		monsters = new ArrayList<Monster>(0); 
	    //towers = new ArrayList<Tower>(0); 
	
		
		if(numOfRow != isGreen.length) { //check Column size
			System.out.println("Arena constructor invalid input: Column Size Mismatch");
			isValidArena = false;
		}
		
		if(numOfColumn != isGreen[0].length) {
			System.out.println("Arena constructor invalid input: Row Size Mismatch");
			isValidArena = false;
		}
		
		int RowSize = isGreen[0].length;
		for(int i = 0;i<isGreen.length;i++) { //check that all Row has same size
			if(isGreen[i].length != RowSize) {
				System.out.println("Arena constructor invalid input: non-uniform Row size");
				isValidArena = false;
			}
		}
		
		if(isValidArena) {
			this.isGreen = new boolean[numOfRow][numOfColumn];
				
			for(int i = 0; i < numOfRow;i++) {
				for(int j = 0; j < numOfColumn;j++) {
					this.isGreen[i][j] = isGreen[i][j];
				}
			}
		}
		
		
		System.out.println("Constructor Ends");
			
	}
	
	
	
	/**
	 * returns true if grid at grid coordinate (row,column) is green
	 * @param row row number of the grid (zero-based)
	 * @param column column number of grid (zero-based)
	 * @return returns true if grid at grid coordinate (row,column) is green
	 */
	public boolean isGreenGrid(int row, int column) { // returns true if grid at grid coordinate (x,y) is a green grid
		return isGreen[row][column];
	}
	
	
	/**
	 * add Monster newMonster to the arena.
	 * @param newMonster the Monster object to be added
	 */
	public void addMonster(Monster newMonster) {
		monsters.add(newMonster);
	}
	
	/**
	 * remove Monster monster from the Arena
	 * @param monster the Monster to be removed
	 */
	public void removeMonster(Monster monster) {
		boolean removeSuccess =  monsters.remove(monster);
		if(!removeSuccess)
			System.out.println("ERROR: Monster Removal Unsuccessful. To be removed monster is not found");
	}
	
	
	/**
	 * sets the money value of Arena to _money
	 * @param _money the value to set money in Arena to
	 */
	public void setMoney(int _money) {
		if(_money < 0) {
			System.out.println("ERROR: attempt to set Money to negative value. Value of money is unchanged");
		}
		this.money = _money;
	}
	
	/**
	 * add _money amount of money to field money
	 * @param _money amount of money to be added
	 */
	public void addMoney(int _money) {
		if(_money>0) {
			this.money = this.money+_money;
		}
		else {
			System.out.println("ERROR: attempt to add non-positive money");
		}
	}
	
	/**
	 * remove _money amount of money from the arena
	 * @param _money amount of money to be removed
	 */
	public void removeMoney(int _money) {
		if(_money>this.money) {
			System.out.println("ERROR:removeMoney(): not enough money");
		}
		else if(_money<0) {
			System.out.println("ERROR:removeMoney(): negative amount entered");
		}
		else {
			this.money = this.money - _money;
		}
	}
	
	
	/**
	 * Getter for money parameter in Arena
	 * @return the amount of money left
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Getter for time in the Arena(Frames)
	 * @return time in Arena(Frames)
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Increment the value of time in the Arena by one
	 */
	public void incrementTime() {
		time++;
	}
	
}



