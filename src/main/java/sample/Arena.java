package sample;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
	private int time; // time = number of frames that have passed
	
	private final int numOfColumn; //number of Columns of grid in the arena. This is not the arena height in pixel
	private final int numOfRow; //number of Rows of grid in the arena. This is not the arena width in pixel
	
	private boolean [][] isGreen; 	// true if the grid at grid coordinate [row][Column] is green, 
	
	public boolean isValidArena; //true if the arena is a valid arena, false otherwise. Made for testing isGreen
	
	public ArrayList <Monster> monsters; //ArrayList of monsters on the arena
	public ArrayList <BasicTower> towers; //ArrayList of towers on the arena
	
	private int money;
	
	public static Random rand = new Random();
	
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
	
	
	
	boolean isGreenGrid(int row, int column) { // returns true if grid at grid coordinate (x,y) is a green grid
		return isGreen[row][column];
	}
	
	
	void addMonster(Monster newMonster) {
		monsters.add(newMonster);
	}
	
	void removeMonster(Monster monster) {
		boolean removeSuccess =  monsters.remove(monster);
		if(!removeSuccess)
			System.out.println("ERROR: Monster Removal Unsuccessful. To be removed monster is not found");
	}
	
	void setMoney(int money) {
		if(money < 0) {
			System.out.println("ERROR: attempt to set Money to negative value. Value of money is unchanged");
		}
		this.money = money;
	}
	
	void addMoney(int money) {
		if(money>0) {
			this.money = this.money+money;
		}
		else {
			System.out.println("ERROR: attempt to add non-positive money");
		}
	}
	
	void removeMoney(int money) {
		if(money>this.money) {
			System.out.println("ERROR:removeMoney(): not enough money");
		}
		else if(money<=0) {
			System.out.println("ERROR:removeMoney(): non-positive amount entered");
		}
		else {
			this.money = this.money - money;
		}
	}
	
	int getMoney() {
		return money;
	}
	
	int getTime() {
		return time;
	}
	
	void incrementTime() {
		time++;
	}
	
}



