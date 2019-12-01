package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Monster {
	protected int hp;
	protected int speed;
	protected int x, y;
	protected boolean isIced;
	protected int iceTime;
	protected int remainingSteps;
	protected boolean isMoving;
	protected ImageView imageView = null;
	
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
	
	public void setHp(int _hp) {
		hp = _hp;
		if(hp<0) {
			hp = 0;
		}
	}
	
	public void setSpeed(int _speed) {
		speed = _speed;
		if(speed<0) {
			speed = 0;
		}
	}
	
	public void setX(int _x) {
		x = _x;
		if (imageView != null) {
			imageView.setX(_x);
		}
	}
	
	public void setY(int _y) {
		y = _y;
		if (imageView != null) {
			imageView.setY(_y);
		}
	}
	
	public void setIsIced(boolean _isIced) {
		isIced = _isIced;
	}
	
	public void setIceTime(int _iceTime) {
		iceTime = _iceTime;
	}
	
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
	
	public int getHp() {
		return hp;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getIsIced() {
		return isIced;
	}
	
	public int getIceTime() {
		return iceTime;
	}
	
	public int getRemainingSteps(){
		return remainingSteps;
	}
	
	public void initRemainingSteps() {
		remainingSteps = speed;
	}
	
	public void moved() {
		--remainingSteps;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public void setIsMoving(boolean _isMoving) {
		isMoving = _isMoving;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	abstract String getMonsterType();
	
	abstract int getDefaultSpeed();

	public void replenishHp() {
		// nothing in Monster
		
	}
	
}
