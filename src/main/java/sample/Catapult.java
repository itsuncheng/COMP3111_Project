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
	
	public static String _imagePath = "./src/main/resources/catapult.png";
	
	
	public Catapult(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		
		coolDownTime = 5;
		coolingDown = 0;
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
	
	
	public void shoot(Monster m,Arena a) {
		if (coolingDown > 0) {
			--coolingDown;
		} else {
			coolingDown = coolDownTime;
			int currentX = m.getX();
			int currentY = m.getY();
			for (int i=0; i<a.monsters.size(); ++i) { 
				double distance = Math.sqrt(Math.pow(currentX-a.monsters.get(i).getX(), 2)+Math.pow(currentY-a.monsters.get(i).getY(), 2));
				if (distance <= 25)
					a.monsters.get(i).setHp(a.monsters.get(i).getHp() - this.attackPower);
			}
			
		}
	}
	
	public String getTowerType() {
		return "Catapult";
	}
	
	public static int getUpgradeCost() {
		return upgrade_cost;
	}
}
