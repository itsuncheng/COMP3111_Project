package sample;

public class Penguin extends Monster{
	
	public static int defaultHp = 6;
	public static int defaultSpeed = 3;
	public static int defaultX = 0;
	public static int defaultY = 0;
	
	public static String _imagePath = "./src/main/resources/penguin.png";
	public static int bonusHp = 2;
	public static int maxHp;
	
	public Penguin() {
		super(defaultHp,defaultSpeed,defaultX,defaultY, _imagePath);
		maxHp = defaultHp;
	}
	
	public Penguin(int _hp, int _speed, int _x, int _y) {
		super(_hp, _speed, _x, _y, _imagePath);
		maxHp = _hp;	
	}
	

//	@Override
//	public void moveAtEachFrame() {
//		// TODO Auto-generated method stub
//		super.moveAtEachFrame();
//		replenishHp();
//	}
	
	public void replenishHp() {
		hp += bonusHp;
		if (hp > maxHp){
			hp = maxHp;
		}
	}

}
