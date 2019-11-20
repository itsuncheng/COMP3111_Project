package sample;

import java.lang.Math;
import java.util.ArrayList;

public class LaserTower extends BasicTower{
	
	private static int _attackPower = 7;
	private static int _range = -1;
	private static int _build_cost = 7;
	private static int _upgrade_cost = 3;
	
	private static int shootCost = 2;
	public static String _imagePath = "./src/main/resources/laserTower.png";
	
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
	}
	
	public boolean isInRange(Monster m) {
		
		return Math.abs(y - m.getY()) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public boolean isInRange(int _x, int _y) {
		
		return Math.abs(y - _y) < 3 ;//* MyController.GRID_HEIGHT;			
	}
	
	public void shoot(Monster m,Arena a) {
		a.removeMoney(shootCost);
		int currentY = m.getY();
		for (int i=0; i<a.monsters.size(); ++i) {
			if (Math.abs(currentY - a.monsters.get(i).getY()) <= 3) {
				a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
			}
		}
			
	}
	
	public void upgrade() {
		++attackPower;
	}
	
	public String getTowerType() {
		return "Laser Tower";
	}
	
	public static int getUpgradeCost() {
		return upgrade_cost;
	}
}
