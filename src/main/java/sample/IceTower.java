package sample;

import java.util.ArrayList;

public class IceTower extends BasicTower{
	
	private static int _attackPower = 1;
	private static int _range = 65;
	private static int _build_cost = 4;
	private static int _upgrade_cost = 5;
	
	public static String _imagePath = "./src/main/resources/iceTower.png";
	
	private static int icePower;
	private int iceTime;
	
	public IceTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost, _imagePath);
		
		icePower =  2;
		iceTime = 3;
	}
	
	public void upgrade() {
		++attackPower;
		++iceTime;
	}
	
	public void shoot(Monster m, ArrayList <Monster> monster) {
		m.setHp(m.getHp() - attackPower);
		if (!m.getIsIced())	{
			m.setSpeed(m.getSpeed() - icePower);
			m.setIsIced(true);
			m.setIceTime(iceTime);
		}
	}
	
	public String getTowerType() {
		return "Ice Tower";
	}
	
	public static int getUpgradeCost() {
		return upgrade_cost;
	}
	
}
	