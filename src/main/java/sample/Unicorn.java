package sample;

public class Unicorn extends Monster{
	
	public static int defaultHp = 8;
	public static int defaultSpeed = 1;
	public static int defaultX = 0;
	public static int defaultY = 0;
	public static boolean defaultIsIced = false;
	public static int defaultIceTime = 0;
	public static String _imagePath = "./src/main/resources/unicorn.png";
	
	public Unicorn() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime, _imagePath);
	}
	
	public Unicorn(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _imagePath);
		
	}
	
	String getMonsterType() {
		return "Unicorn";
	}
	
	int getDefaultSpeed() {
		return defaultSpeed;
	}

}
