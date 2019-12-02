package sample;

/**
 * Fox subclass
 * @author raymondcheng
 *
 */
public class Fox extends Monster{
	
	/**
	 * default hp of monster
	 */
	public static int defaultHp = 5;
	/**
	 * default speed of monster
	 */
	public static int defaultSpeed = 6;
	/**
	 * default x position of monster
	 */
	public static int defaultX = 0;
	/**
	 * default y position of monster
	 */
	public static int defaultY = 0;
	/**
	 * default isIced of monster
	 */
	public static boolean defaultIsIced = false;
	/**
	 * default iceTime of monster
	 */
	public static int defaultIceTime = 0;
	/**
	 * default remainingSteps of monster
	 */
	public static int defaultRemainingSteps = 0;
	/**
	 * default isMoving of monster
	 */
	public static boolean defaultIsMoving = false;
	/**
	 * imagePath of Fox
	 */
	public static String _imagePath = "./src/main/resources/fox.png";
	
	/**
	 * constructor for Fox class 
	 */
	public Fox() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime,defaultRemainingSteps,defaultIsMoving, _imagePath);
	}

	/**
	 * overloaded constructor for Fox class
	 * @param _hp hp of the monster
	 * @param _speed speed of the monster
	 * @param _x x position of the monster
	 * @param _y y position of the monster
	 * @param _isIced whether the monster is iced
	 * @param _iceTime iceTime of the monster
	 * @param _remainingSteps remainingSteps of the monster
	 * @param _isMoving whether the monster is moving
	 */
	
	public Fox(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _remainingSteps, boolean _isMoving) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _remainingSteps, _isMoving,_imagePath);
		
	}
	
	/**
	 * returns the String "Fox"
	 * @return String returns "Fox"
	 */
	String getMonsterType() {
		return "Fox";
	}
	
	/**
	 * returns default speed for Unicorn monsters
	 * @return int returns defaultSpeed
	 */
	int getDefaultSpeed() {
		return defaultSpeed;
	}
}
