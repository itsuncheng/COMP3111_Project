package sample;

import java.util.ArrayList;

public class Catapult extends BasicTower{
	
	private static int _attackPower = 5;
	private static int _range = 150;
	private static int _build_cost = 5;
	private static int _upgrade_cost = 3;
	
	private static int lowRange = 50;
	private int coolDownTime;
	private int coolingDown;
	private boolean isCooling;
	private int lastShotTime;
	
	public static String _imagePath = "./src/main/resources/catapult.png";
	
	
	public Catapult(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		
		coolDownTime = 5;
		coolingDown = 0;
	}
	
	
	public Catapult(int _x, int _y, int _attackPower, int _range, int _buildCost, int _upgradeCost, int _lowRange, int _coolDownTime, int _coolingDown, String _imagePath) {
		super(_x, _y, _attackPower, _range, _buildCost, _upgradeCost, _imagePath);
		
		lowRange = _lowRange;
		coolDownTime = _coolDownTime;
		coolingDown = _coolingDown;
	}
	
	public void upgrade() {
		if (coolDownTime >= 0)
			--coolDownTime;
	}
	
	public boolean isInRange(Monster m) {
		double distance = Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
		return this.getRange() > distance && lowRange < distance;
	}
	
	public boolean isInRange(int _x, int _y) {
		double distance = Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
		return this.getRange() > distance && lowRange < distance;
	}
	
	
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
	
	public String getTowerType() {
		return "Catapult";
	}
	
	public static int getUpgradeCost() {
		return upgrade_cost;
	}
	
	public int getLowRange() {
		return lowRange;
	}
	
}
