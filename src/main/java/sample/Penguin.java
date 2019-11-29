package sample;

public class Penguin extends Monster{
	
	public static int defaultHp = 6;
	public static int defaultSpeed = 3;
	public static int defaultX = 0;
	public static int defaultY = 0;
	public static boolean defaultIsIced = false;
	public static int defaultIceTime = 0;
	public static int defaultMoving = 0;
	public static boolean defaultIsMoving = false;
	public static String _imagePath = "./src/main/resources/penguin.png";
	public static int bonusHp = 1;
	public static int maxHp;
	
	public Penguin() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime,defaultMoving,defaultIsMoving, _imagePath);
		maxHp = defaultHp;
	}
	
	public Penguin(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _moving, boolean _isMoving) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _moving, _isMoving, _imagePath);
		maxHp = _hp;	
	}
	
	public void replenishHp() {
		hp += bonusHp;
		if (hp > maxHp){
			hp = maxHp;
		}
	}
	
	String getMonsterType() {
		return "Penguin";
	}

	int getDefaultSpeed() {
		return defaultSpeed;
	}
}
