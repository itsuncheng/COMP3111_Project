package sample;

import java.lang.Math;
import java.util.ArrayList;

/**
 * LaserTower subclass
 * @author raymondcheng
 *
 */
public class LaserTower extends BasicTower{
	
	/**
	 * default attack power of the tower
	 */
	private static int _attackPower = 5;
	/**
	 * default range of the tower
	 */
	private static int _range = 630;  
	/**
	 * default build cost of the tower
	 */
	private static int _build_cost = 7;
	/**
	 * default upgrade cost of the tower
	 */
	private static int _upgrade_cost = 3;
	/**
	 * default shoot cost of the tower
	 */
	private static int _shootCost = 2;
	
	/**
	 * shoot cost of the tower, each time it shoots a monster
	 */
	private int shootCost;

	/**
	 * the directory where the image is stored at
	 */
	public static String _imagePath = "./src/main/resources/laserTower.png";
	
	/**
	 * LaserTower class constructor
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 */
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		shootCost = _shootCost;
	}
	
	/**
	 * checks whether a monster is within the range of the tower
	 * @param m a monster
	 * @return boolean returns true if the monster is in range of the tower, otherwise false
	 */
	public boolean isInRange(Monster m) {
		
		return true;//Math.abs(y - m.getY()) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	/**
	 * checks whether a point (x,y) is within the range of the tower
	 * @param _x x position
	 * @param _y y position
	 * @return boolean returns true if the point is in range of the tower, otherwise false
	 */
	public boolean isInRange(int _x, int _y) {
		
		return true;//Math.abs(y - _y) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	/**
	 * the tower shoots the monster in the arena
	 * @param m a monster
	 * @param a the arena
	 * @return boolean returns true if the tower successfully shoots the monster in the arena; otherwise, false
	 */
	public boolean shoot(Monster m,Arena a) {   //this is done by the following step 
		
		if (isShot != true) {
			double currentX = m.getX();
			double currentY = m.getY();
			
			double slope = ((double)y - currentY)/((double)x - currentX); //1)finding the slope between tower and the current monster m
			
			for (int i=0; i<a.monsters.size(); ++i) {
				 double targetX = a.monsters.get(i).getX();  //2)getting x and y of each monsters in the list 
				 double targetY = a.monsters.get(i).getY();  
				 
				//3)using slope properties,getting x and y coordinate which form the shortest distance between target monster and the line drawn by tower and current monster
				 double tempX = (slope*targetY + slope*slope*x - slope*y + targetX)/(1 + slope*slope); 
				 double tempY = slope*(tempX-x) + y;
				 
				 double distance = Math.sqrt(Math.pow(targetX - tempX, 2)+Math.pow(targetY - tempY, 2));
				 
				 if (distance <= 3) {
					 boolean xIsInRange;
					 boolean yIsInRange;
				 
					 if (currentX >= x)
						 xIsInRange = targetX >= x;
					 else 
						 xIsInRange = targetX < x;
				 
					 if (currentY >= y)
						 yIsInRange = targetY >= y;
					 else 
						 yIsInRange = targetY < y;
				 
					 if (xIsInRange && yIsInRange ) {
						 if (a.getMoney() >= shootCost) {
							 a.removeMoney(shootCost);
							 a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
						 }
					 }
				 
				 }
			}
		}
		return true;
	}
	
	/**
	 * upgrade tower
	 */
	public void upgrade() {
		attackPower += 1;
		shootCost = _shootCost * (int) (attackPower/_attackPower);
	}
	
	/**
	 * return the class name of the tower
	 * @return String return the class name of the tower
	 */
	public String getTowerType() {
		return "Laser Tower";
	}

	/**
	 * setting shoot cost of the tower
	 * @param _shootCost shoot cost of the tower
	 */
	public void setShootCost(int _shootCost) {
		shootCost = _shootCost;
	}
	
	/**
	 * return shoot cost of the tower
	 * @return int return shoot cost of the tower
	 */
	public int getShootCost() {
		return shootCost;
	}
	
	/**
	 * return the default build cost of the tower
	 * @return int return the default build cost of the tower
	 */
	public static int getDefaultBuildCost() {
		return _build_cost;
	}
	
	/**
	 * return the default upgrade cost of the tower
	 * @return int return the default upgrade cost of the tower
	 */
	public static int getDefaultUpgradeCost() {
		return _upgrade_cost;
	}
}
