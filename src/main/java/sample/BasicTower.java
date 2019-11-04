package sample;

import java.lang.Math;

public class BasicTower {
	protected int x, y;
	private int cost = 2;
	protected int attackPower = 1;
	protected int range = 65;
	
	public BasicTower(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public void Upgrade() {
		attackPower += 1;
	}
	
	public boolean IsInRange(Monster m) {
		return range > Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
	}
	
	public void Shoot(Monster m) {
		if (IsInRange(m)) {
			m.setHp(m.getHp() - attackPower);
		}
	}
	
	public int GetAttackPower() {
		return attackPower;
	}
	
	public int GetCost() {
		return cost;
	}
}
