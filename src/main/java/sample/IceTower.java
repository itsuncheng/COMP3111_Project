package sample;

import java.util.ArrayList;

/**
 * IceTower subclass that models the tower IceTower
 * @author raymondcheng
 *
 */
public class IceTower extends BasicTower{
	
	/**
	 * default attack power of the tower
	 */
	private static int _attackPower = 1;
	/**
	 * default range of the tower
	 */
	private static int _range = 65;
	/**
	 * default build cost of the tower
	 */
	private static int _build_cost = 4;
	/**
	 * default upgrade cost of the tower
	 */
	private static int _upgrade_cost = 5;
	
	/**
	 * the directory where the image is stored at
	 */
	public static String _imagePath = "./src/main/resources/iceTower.png";
	
	/**
	 * icePower of the tower, how much the striked monster reduces its speed by
	 */
	private int icePower;
	/**
	 * iceTime of the tower, number of frames the striked monster is iced (stopped) for
	 */
	private int iceTime;
	
	/**
	 * IceTower class constructor
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 */
	public IceTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		
		icePower =  2;
		iceTime = 3;
	}
	
	/**
	 * upgrade tower
	 */
	public void upgrade() {
		++attackPower;
		++iceTime;
	}
	
	/**
	 * the tower shoots the monster in the arena
	 * @param m a monster
	 * @param a the arena
	 * @return boolean returns true if the tower successfully shoots the monster in the arena; otherwise, false
	 */
	public boolean shoot(Monster m,Arena a) {
		if (isShot != true) {	
			m.setHp(m.getHp() - attackPower);
			if (!m.getIsIced())	{
				m.setSpeed(m.getSpeed() - icePower);
				if (m.getSpeed() < 1) {
					m.setSpeed(1);
				}
				m.setIsIced(true);
				m.setIceTime(iceTime);
			}
		}
		
		return true;
	}
	
	/**
	 * return the class name of the tower
	 * @return String return the class name of the tower
	 */
	public String getTowerType() {
		return "Ice Tower";
	}
	
	/**
	 * return icePower of the tower
	 * @return int return icePower of the tower
	 */
	public int getIcePower() {
		return icePower;
	}
	
	/**
	 * return iceTime of the tower
	 * @return int return iceTime of the tower
	 */
	public int getIceTime() {
		return iceTime;
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
	