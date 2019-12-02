package sample;

/**
 * Penguin subclass that models the monster Penguin
 * @author raymondcheng
 *
 */
public class Penguin extends Monster{
	
	/**
	 * default hp of monster
	 */
	public static int defaultHp = 6;
	/**
	 * default speed of monster
	 */
	public static int defaultSpeed = 3;
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
	 * imagePath of Penguin
	 */
	public static String _imagePath = "./src/main/resources/penguin.png";
	/**
	 * bonusHP of monster everytime Penguin replenishes
	 */
	public static int bonusHp = 1;
	/**
	 * maxHp of the monster
	 */
	public static int maxHp;
	
	/**
	 * constructor for Penguin class
	 */
	public Penguin() {
		super(defaultHp,defaultSpeed,defaultX,defaultY,defaultIsIced,defaultIceTime,defaultRemainingSteps,defaultIsMoving, _imagePath);
		maxHp = defaultHp;
	}
	
	/**
	 * overloaded constructor for Penguin class
	 * @param _hp hp of the monster
	 * @param _speed speed of the monster
	 * @param _x x position of the monster
	 * @param _y y position of the monster
	 * @param _isIced whether the monster is iced
	 * @param _iceTime iceTime of the monster
	 * @param _remainingSteps remainingSteps of the monster
	 * @param _isMoving whether the monster is moving
	 */
	public Penguin(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _remainingSteps, boolean _isMoving) {
		super(_hp, _speed, _x, _y, _isIced, _iceTime, _remainingSteps, _isMoving, _imagePath);
		maxHp = _hp;	
	}
	
	/**
	 * replenish its hp every once a while
	 */
	public void replenishHp() {
		hp += bonusHp;
		if (hp > maxHp){
			hp = maxHp;
		}
	}
	
	/**
	 * returns the String "Penguin"
	 * @return String returns "Penguin"
	 */
	public String getMonsterType() {
		return "Penguin";
	}

	/**
	 * returns default speed for Penguin monsters
	 * @return int returns defaultSpeed
	 */
	public int getDefaultSpeed() {
		return defaultSpeed;
	}
}
