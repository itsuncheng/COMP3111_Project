package sample;

import java.util.ArrayList;

/**
 * Catapult subclass
 * @author raymondcheng
 *
 */
public class Catapult extends BasicTower{
	
	/**
	 * default attack power of the tower
	 */
	private static int _attackPower = 5;
	/**
	 * default range of the tower
	 */
	private static int _range = 150;
	/**
	 * default build cost of the tower
	 */
	private static int _build_cost = 5;
	/**
	 * default upgrade cost of the tower
	 */
	private static int _upgrade_cost = 3;
	
	/**
	 * default low range of the tower
	 */
	private static int lowRange = 50;
	/**
	 *  number of frames the catapult needs to cool down for (cannot shoot) 
	 */
	private int coolDownTime;
	/**
	 * coolingDown of the catapult
	 */
	private int coolingDown;
	/**
	 *  whether the catapult is cooling down (cannot shoot)
	 */
	private boolean isCooling;
	/**
	 *  the last frame the catapult shot a monster
	 */
	private int lastShotTime;
	
	/**
	 * the directory where the image is stored at
	 */
	public static String _imagePath = "./src/main/resources/catapult.png";
	
	/**
	 * Catapult class constructor
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 */
	public Catapult(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		
		coolDownTime = 5;
		coolingDown = 0;
	}
	
	/**
	 * overloaded constructor for Catapult
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 * @param _attackPower attack power of the tower
	 * @param _range range of the tower
	 * @param _buildCost build cost of the tower
	 * @param _upgradeCost upgrade cost of the tower
	 * @param _lowRange catapult cannot attack at a position less than the lowRange from itself
	 * @param _coolDownTime number of frames the catapult needs to cool down for (cannot shoot)
	 * @param _coolingDown coolingDown of the catapult
	 * @param _imagePath directory where its image is stored at
	 */
	public Catapult(int _x, int _y, int _attackPower, int _range, int _buildCost, int _upgradeCost, int _lowRange, int _coolDownTime, int _coolingDown, String _imagePath) {
		super(_x, _y, _attackPower, _range, _buildCost, _upgradeCost, _imagePath);
		
		lowRange = _lowRange;
		coolDownTime = _coolDownTime;
		coolingDown = _coolingDown;
	}
	
	/**
	 * upgrade tower
	 */
	public void upgrade() {
		if (coolDownTime >= 0)
			--coolDownTime;
	}
	
	/**
	 * checks whether a monster is within the range of the tower
	 * @param m a monster
	 * @return boolean returns true if the monster is in range of the tower, otherwise false
	 */
	public boolean isInRange(Monster m) {
		double distance = Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
		return this.getRange() > distance && lowRange < distance;
	}
	
	/**
	 * checks whether a point (x,y) is within the range of the tower
	 * @param _x x position
	 * @param _y y position
	 * @return boolean returns true if the point is in range of the tower, otherwise false
	 */
	public boolean isInRange(int _x, int _y) {
		double distance = Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
		return this.getRange() > distance && lowRange < distance;
	}
	
	/**
	 * the tower shoots the monster in the arena
	 * @param m a monster
	 * @param a the arena
	 * @return boolean returns true if the tower successfully shoots the monster in the arena; otherwise, false
	 */
	public boolean shoot(Monster m,Arena a) {
		if (isShot != true) {
			if (isCooling && (a.getTime() - lastShotTime)>=coolDownTime) {
				isCooling = false;
			} 
			
			if(!isCooling) {	
				lastShotTime = a.getTime();
				int currentX = m.getX();
				int currentY = m.getY();
				for (int i=0; i<a.monsters.size(); ++i) { 
					double distance = Math.sqrt(Math.pow(currentX-a.monsters.get(i).getX(), 2)+Math.pow(currentY-a.monsters.get(i).getY(), 2));
					if (distance <= 25)
						a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
				}
				isCooling = true;
				return true;
				
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * return the class name of the tower
	 * @return String return the class name of the tower
	 */
	public String getTowerType() {
		return "Catapult";
	}
	
	/**
	 * return coolDownTime of tower
	 * @return int return coolDownTime of tower
	 */
	public int getCoolDownTime() {
		return coolDownTime;
	}

	/**
	 * returns low range of tower
	 * @return int return low range of tower
	 */
	public int getLowRange() {
		return lowRange;
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
