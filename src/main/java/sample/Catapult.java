package sample;

public class Catapult extends BasicTower{
	private int cost = 5;
	private int range = 150;
	private int attackPower  = 5;
	private int lowRange = 50;
	private int coolDown = 5;
	
	
	public Catapult(int _x, int _y) {
		super(_x, _y);
	}
	 
	public void Upgrade() {
		if (coolDown >= 0)
			--coolDown;
	}
	
	public void Shoot() {
		
	}
}
