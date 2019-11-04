package sample;

public class IceTower extends BasicTower{
	
	private int cost = 4;
	private int slowDownPower = 2;
	
	public IceTower(int _x, int _y) {
		super(_x, _y);
		
	}
	
	public void Upgrade() {
		++attackPower;
		++slowDownPower;
	}
	
	public void Shoot(Monster m) {
		if (IsInRange(m)) {
			m.setHp(m.getHp() - attackPower);
			m.setSpeed(m.getSpeed() - slowDownPower);
		}
	}
	
}
	