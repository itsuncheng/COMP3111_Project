package sample;

import java.util.ArrayList;

public class IceTower extends BasicTower{
	
	private static int _attackPower = 1;
	private static int _range = 65;
	private static int _build_cost = 4;
	private static int _upgrade_cost = 5;
	
	public static String _imagePath = "./src/main/resources/iceTower.png";
	
	private int icePower;
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
	
	public String getTowerType() {
		return "Ice Tower";
	}
	
	public int getIcePower() {
		return icePower;
	}
	
	public int getIceTime() {
		return iceTime;
	}
	
	public static int getDefaultBuildCost() {
		return _build_cost;
	}
	
	
	public static int getDefaultUpgradeCost() {
		return _upgrade_cost;
	}
	
}
	