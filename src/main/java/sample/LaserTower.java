package sample;

import java.lang.Math;

public class LaserTower extends BasicTower{
	private int cost = 7;
	private int attackPower = 7;
	
	
	public LaserTower(int _x, int _y) {
		super(_x, _y);
	}
	
	public void Upgrade() {
		++attackPower;
	}
	
	public boolean IsInRange(Monster m) {
		return Math.abs(y - m.getY()) < 3;			
	}
}
