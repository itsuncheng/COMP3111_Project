package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Monster abstract base class
 * @author raymondcheng
 *
 */
public abstract class Monster {
	/**
	 * hp of the monster
	 */
	protected int hp;
	/**
	 * speed of the monster
	 */
	protected int speed;
	/**
	 * position x and y of the monster
	 */
	protected int x, y;
	/**
	 * whether the monster is iced (stopped)
	 */
	protected boolean isIced;
	/**
	 * number of frames the monster is iced (stopped) for
	 */
	protected int iceTime;
	/**
	 * remaining number of steps left in the current frame
	 */
	protected int remainingSteps;
	/**
	 * whether the Monster is still moving (have remaining steps)
	 */
	protected boolean isMoving;
	/**
	 * ImageView picture representing the monster on the GUI
	 */
	protected ImageView imageView = null;
	
	/**
	 * Constructor for Monster class
	 * @param _hp hp of monster
	 * @param _speed speed of monster 
	 * @param _x x position of the monster
	 * @param _y y position of the monster
	 * @param _isIced whether the monster is iced and stopped (can't move)
	 * @param _iceTime how many frames the monster is iced and stopped for
	 * @param _remainingSteps how many steps the monster still have in this current frame
	 * @param _isMoving whether the monster is still currently moving, equivalent to whether there are still remainingSteps
	 * @param _imagePath the directory where the image file of this monster is stored
	 */
	
	public Monster(int _hp, int _speed, int _x, int _y, boolean _isIced, int _iceTime, int _remainingSteps, boolean _isMoving, String _imagePath) {
		hp = _hp;
		speed = _speed;
		x = _x;
		y = _y;
		
		isIced = _isIced;
		iceTime = _iceTime;
		
		remainingSteps = _remainingSteps;
		isMoving = _isMoving;
		
		setImageView(_imagePath);
		
	}
	
	/**
	 * setting the hp of the monster
	 * @param _hp new hp of the monster
	 * 
	 */
	public void setHp(int _hp) {
		hp = _hp;
		if(hp<0) {
			hp = 0;
		}
	}
	
	/**
	 * setting the speed of the monster
	 * @param _speed new speed of the monster
	 * 
	 */
	public void setSpeed(int _speed) {
		speed = _speed;
		if(speed<0) {
			speed = 0;
		}
	}
	
	/**
	 * setting x position of the monster by setting its imageView to position x 
	 * @param _x x position of the monster
	 * 
	 */
	public void setX(int _x) {
		x = _x;
		if (imageView != null) {
			imageView.setX(_x);
		}
	}
	
	/**
	 * setting y position of the monster by setting its imageView to position x
	 * @param _y y position of the monster
	 * 
	 */
	public void setY(int _y) {
		y = _y;
		if (imageView != null) {
			imageView.setY(_y);
		}
	}
	
	/**
	 * setting whether the monster is iced by IceTower
	 * @param _isIced if monster is iced
	 * 
	 */
	public void setIsIced(boolean _isIced) {
		isIced = _isIced;
	}
	
	/**
	 * setting the iceTime for the monster
	 * @param _iceTime iceTime of the monster
	 * 
	 */
	public void setIceTime(int _iceTime) {
		iceTime = _iceTime;
	}
	
	/**
	 * setting the imageView for the monster
	 * @param _imagePath directory of the image file of the monster
	 * 
	 */
	public void setImageView(String _imagePath) {
		
		if (_imagePath == null) {
			return;
		}
		
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
	 * returns hp of the monster
	 * @return int returns hp of the monster
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * returns speed of the monster
	 * @return int returns speed of the monster
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * returns x position of the monster
	 * @return int returns x position of the monster
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * returns y position of the monster
	 * @return int returns y position of the monster
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * returns whether the monster is iced
	 * @return boolean returns isIced of the monster
	 */
	public boolean getIsIced() {
		return isIced;
	}
	
	/**
	 * returns iceTime of the monster
	 * @return int returns iceTime of the monster
	 */
	public int getIceTime() {
		return iceTime;
	}
	
	/**
	 * returns remainingSteps of the monster
	 * @return int returns remainingSteps of the monster
	 */
	public int getRemainingSteps(){
		return remainingSteps;
	}
	
	/**
	 * initializes remainingSteps of the monster: set remainingSteps to speed
	 * 
	 */
	public void initRemainingSteps() {
		remainingSteps = speed;
	}
	
	/**
	 * moves monster by one step: remainingSteps decrease by 1
	 * 
	 */
	public void moved() {
		--remainingSteps;
	}
	
	/**
	 * returns whether the monster is moving
	 * @return int returns isMoving of the monster
	 */
	public boolean getIsMoving() {
		return isMoving;
	}
	
	/**
	 * setting isMoving of the monster
	 * @param _isMoving isMoving of the monster
	 * 
	 */
	public void setIsMoving(boolean _isMoving) {
		isMoving = _isMoving;
	}
	
	/**
	 * returns ImageView of the monster
	 * @return ImageView returns ImageView of the monster
	 */
	public ImageView getImageView() {
		return imageView;
	}
	
	/**
	 * abstract method for returning the class name of the monster pointed to at runtime 
	 * @return String returns class name of the monster pointed to at runtime
	 */
	abstract String getMonsterType();
	
	/**
	 * abstract method for returning default speed of each Monster subclass
	 * @return int returns default speed of the monster pointed to at runtime
	 */
	abstract int getDefaultSpeed();

	/**
	 * to be overridden only by Penguin class which can replenish its hp
	 * 
	 */
	public void replenishHp() {
		// nothing in Monster
		
	}
	
}
