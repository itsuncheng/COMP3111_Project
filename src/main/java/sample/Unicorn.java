package sample;

/**
 * Unicorn subclass that models the monster Unicorn
 * @author raymondcheng
 *
 */
public class Unicorn extends Monster{
	
	/**
	 * default hp of monster
	 */
	public static int defaultHp = 8;
	/**
	 * default speed of monster
	 */
	public static int defaultSpeed = 1;
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
	 * imagePath of Unicorn
	 */
	public static String _imagePath = "./src/main/resources/unicorn.png";
	
	/**
	 * constructor for Unicorn class 
	 */
	public Unicorn() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime,defaultRemainingSteps,defaultIsMoving, _imagePath);
	}
	
	/**
	 * overloaded constructor for Unicorn class
	 * @param _hp hp of the monster
	 * @param _speed speed of the monster
	 * @param _x x position of the monster
	 * @param _y y position of the monster
	 * @param _isIced whether the monster is iced
	 * @param _iceTime iceTime of the monster
	 * @param _remainingSteps remainingSteps of the monster
	 * @param _isMoving whether the monster is moving
	 */
	public Unicorn(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _remainingSteps, boolean _isMoving) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _remainingSteps, _isMoving, _imagePath);
		
	}
	
	/**
	 * returns the String "Unicorn"
	 * @return String returns "Unicorn"
	 */
	public String getMonsterType() {
		return "Unicorn";
	}
	
	/**
	 * returns default speed for Unicorn monsters
	 * @return int returns defaultSpeed
	 */
	public int getDefaultSpeed() {
		return defaultSpeed;
	}

}
