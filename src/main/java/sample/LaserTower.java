package sample;

import java.lang.Math;

public class LaserTower extends BasicTower{
	
	private static int _attackPower = 7;
	private static int _range = -1;
	private static int _build_cost = 7;
	private static int _upgrade_cost = 3;
	
	public static String _imagePath = "./src/main/resources/laserTower.png";
	
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
	}
	
	public boolean isInRange(Monster m) {
		
		return Math.abs(y - m.getY()) < 3 * MyController.GRID_HEIGHT;			
	}
	
	public boolean isInRange(int _x, int _y) {
		
		return Math.abs(y - _y) < 3 * MyController.GRID_HEIGHT;			
	}
	
	public void upgrade() {
		++attackPower;
	}
	
	public String getTowerType() {
		return "Laser Tower";
	}
}
