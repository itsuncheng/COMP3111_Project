package sample;

import java.lang.Math;

public class BasicTower {
	protected int x, y;
	protected int attackPower;
	private int range;
	private int build_cost;
	private int upgrade_cost;
	
	public BasicTower(int _x, int _y) {
		x = _x;
		y = _y;
		
		attackPower = 1;
		range = 65;
		build_cost = 2;
		upgrade_cost = 1;
	}
	
	public BasicTower(int _x, int _y, int _attackPower, int _range, int _build_cost, int _upgrade_cost) {
		x = _x;
		y = _y;
		
		attackPower = _attackPower;
		range = _range;
		build_cost = _build_cost;
		upgrade_cost = _upgrade_cost;
	}
	
	public boolean isInRange(Monster m) {
		return range > Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
	}
	
	public void shoot(Monster m) {
		if (isInRange(m)) {
			m.setHp(m.getHp() - attackPower);
		}
	}
	
	public void upgrade() {
		attackPower += 1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAttackPower() {
		return attackPower;
	}

	public int getRange() {
		return range;
	}
	
	public int GetBuildCost() {
		return build_cost;
	}
	
	
	public int getUpgradeCost() {
		return upgrade_cost;
	}
}
