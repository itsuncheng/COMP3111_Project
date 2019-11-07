package sample;

import java.lang.Math;

public class LaserTower extends BasicTower{
	
	private static int _attackPower = 7;
	private static int _range = -1;
	private static int _build_cost = 7;
	private static int _upgrade_cost = 3;
	
	
	public LaserTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost);
	}
	
	public boolean isInRange(Monster m) {
		
		return Math.abs(y - m.getY()) < 3;			
	}
	
	public void upgrade() {
		++attackPower;
	}
}
