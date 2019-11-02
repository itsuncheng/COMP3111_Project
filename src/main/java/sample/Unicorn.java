package sample;

public class Unicorn extends Monster{
	
	public static int defaultHp = 8;
	public static int defaultSpeed = 4;
	public static int defaultX = -1;
	public static int defaultY = 0;
	public static String _imagePath = "./src/main/resources/unicorn.png";
	
	public Unicorn() {
		super(defaultHp,defaultSpeed,defaultX,defaultY, _imagePath);
	}
	
	public Unicorn(int _hp, int _speed, int _x, int _y) {
		super(_hp, _speed, _x, _y, _imagePath);
		
	}
	

}
