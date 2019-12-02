package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.Math;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * BasicTower base class
 * @author raymondcheng
 *
 */
public class BasicTower {
	/**
	 * x and y positions of the tower
	 */
	protected int x, y;
	/**
	 * attack power of the tower
	 */
	protected int attackPower;
	/**
	 * range of the tower
	 */
	private int range;
	/**
	 * build cost of the tower
	 */
	private int build_cost;
	/**
	 * upgrade cost of the tower
	 */
	protected int upgrade_cost;
	/**
	 * whether the tower has shot
	 */
	protected boolean isShot = false;
	/**
	 * imageView of the tower
	 */
	protected ImageView imageView = null;
	
	/**
	 * default attack power of the tower
	 */
	public static int _attackPower = 3;
	/**
	 * default range of the tower
	 */
	public static int _range = 65;
	/**
	 * default build cost of the tower
	 */
	public static int _build_cost = 2;
	/**
	 * default upgrade cost of the tower
	 */
	public static int _upgrade_cost = 1;
	
	/**
	 * the directory where the image is stored at
	 */
	public static String _imagePath = "./src/main/resources/basicTower.png";
	
	/**
	 * constructor for BasicTower
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 */
	public BasicTower(int _x, int _y) {
		x = _x;
		y = _y;
		
		attackPower = _attackPower;
		range = _range;
		build_cost = _build_cost;
		upgrade_cost = _upgrade_cost;
		
		setImageView(_imagePath);
	}
	
	/**
	 * overloaded constructor for Basic Tower
	 * @param _x x position of the tower
	 * @param _y y position of the tower
	 * @param _attackPower attack power of the tower
	 * @param _range range of the tower
	 * @param _build_cost build cost of the tower
	 * @param _upgrade_cost upgrade cost of the tower
	 * @param _imagePath directory where its image is stored at
	 */
	public BasicTower(int _x, int _y, int _attackPower, int _range, int _build_cost, int _upgrade_cost, String _imagePath) {
		x = _x;
		y = _y;
		
		attackPower = _attackPower;
		range = _range;
		build_cost = _build_cost;
		upgrade_cost = _upgrade_cost;
		
		setImageView(_imagePath);
	}
	
	/**
	 * checks whether a monster is within the range of the tower
	 * @param m a monster
	 * @return boolean returns true if the monster is in range of the tower, otherwise false
	 */
	public boolean isInRange(Monster m) {
		return range > Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
	}
	
	/**
	 * checks whether a point (x,y) is within the range of the tower
	 * @param _x x position
	 * @param _y y position
	 * @return boolean returns true if the point is in range of the tower, otherwise false
	 */
	public boolean isInRange(int _x, int _y) {
		return range > Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
	}
	
	/**
	 * the tower shoots the monster in the arena
	 * @param m a monster
	 * @param a the arena
	 * @return boolean returns true if the tower successfully shoots the monster in the arena; otherwise, false
	 */
	public boolean shoot(Monster m, Arena a) {
		if (isShot != true)
			m.setHp(m.getHp() - attackPower);
		
		return true;
	}
	
	/**
	 * upgrades the tower by increasing its attack power by 1
	 */
	public void upgrade() {
		attackPower += 1;
	}
	
	/**
	 * setting imageView for tower
	 * @param _imagePath directory where the image is stored at
	 */
	public void setImageView(String _imagePath) {
		Image image = null;
		
		try {
			image = new Image(new FileInputStream(_imagePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		if (image != null) {
		    imageView = new ImageView(image); 
		}
	}
	
	/**
	 * return the x position of the tower
	 * @return int return the x position of the tower
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * return the y position of the tower
	 * @return int return the y position of the tower
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * return the attack power of the tower
	 * @return int return the attack power of the tower
	 */
	public int getAttackPower() {
		return attackPower;
	}
	
	/**
	 * return the range of the tower
	 * @return int return the range of the tower
	 */
	public int getRange() {
		return range;
	}
	
	/**
	 * return the default build cost of the tower
	 * @return int return the default build cost of the tower
	 */
	public static int getDefaultBuildCost() {
		return _build_cost;
	}
	
	/**
	 * return the default upgrade cost of the tower
	 * @return int return the default upgrade cost of the tower
	 */
	public static int getDefaultUpgradeCost() {
		return _upgrade_cost;
	}
	
	/**
	 * return the build cost of the tower
	 * @return int return the build cost of the tower
	 */
	public int getBuildCost() {
		return build_cost;
	}
	
	/**
	 * return the upgrade cost of the tower
	 * @return int return the upgrade cost of the tower
	 */
	public int getUpgradeCost() {
		return upgrade_cost;
	}

	/**
	 * return the imageView of the tower
	 * @return ImageView return the imageView of the tower
	 */
	public ImageView getImageView() {
		return imageView;
	}
	
	/**
	 * return the class name of the tower
	 * @return String return the class name of the tower
	 */
	public String getTowerType() {
		return "Basic Tower";
	}
	
	/**
	 * return whether the tower has shot
	 * @return boolean return true if the tower has shot; otherwise, false
	 */
	public boolean getIsShot() {
		return isShot;
	}
	
	/**
	 * setting isShot for tower
	 * @param _isShot whether the tower has shot
	 */
	public void setIsShot(boolean _isShot) {
		isShot = _isShot;
	}
	
	/**
	 * for LaserTower class to override this method
	 * @return int return 0 for tower as no shootcost for basicTower
	 */
	public int getShootCost() {
		// TODO Auto-generated method stub
		return 0;
	}
		
	/**
	 * for IceTower class to override this method
	 * @return int return 0 for tower as no icePower for basicTower
	 */
	public int getIcePower() {
		return 0;
	}
	
	/**
	 * for IceTower class to override this method
	 * @return int return 0 for tower as no iceTime for basicTower
	 */
	public int getIceTime() {
		return 0;
	}
	
	/**
	 * for Catapult class to override this method
	 * @return int return 0 for tower as no coolDownTime for basicTower
	 */
	public int getCoolDownTime() {
		return 0;
	}
}
