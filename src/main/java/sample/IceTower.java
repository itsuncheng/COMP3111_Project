package sample;

public class IceTower extends BasicTower{
	
	private static int _attackPower = 7;
	private static int _range = 65;
	private static int _build_cost = 4;
	private static int _upgrade_cost = 5;
	
	private int slowDownPower;
	
	public IceTower(int _x, int _y) {
		super(_x, _y, _attackPower, _range, _build_cost, _upgrade_cost);
		
		slowDownPower =  2;
		
	}
	
	public void upgrade() {
		++attackPower;
		++slowDownPower;
	}
	
	public void shoot(Monster m) {
		if (isInRange(m)) {
			m.setHp(m.getHp() - attackPower);
			m.setSpeed(m.getSpeed() - slowDownPower);
		}
	}
	
}
	