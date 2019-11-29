package sample;

public class Fox extends Monster{
	
	public static int defaultHp = 5;
	public static int defaultSpeed = 6;
	public static int defaultX = 0;
	public static int defaultY = 0;
	public static boolean defaultIsIced = false;
	public static int defaultIceTime = 0;
	public static int defaultMoving = 0;
	public static boolean defaultIsMoving = false;
	public static String _imagePath = "./src/main/resources/fox.png";
	
	public Fox() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime,defaultMoving,defaultIsMoving, _imagePath);
	}
	
	public Fox(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _moving, boolean _isMoving) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _moving, _isMoving,_imagePath);
		
	}

	String getMonsterType() {
		return "Fox";
	}
	
	int getDefaultSpeed() {
		return defaultSpeed;
	}
}
