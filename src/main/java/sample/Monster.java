package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster {
	protected int hp;
	protected int speed;
	protected int x, y;
	protected ImageView imageView = null;
	
	public Monster(int _hp, int _speed, int _x, int _y, String _imagePath) {
		hp = _hp;
		speed = _speed;
		x = _x;
		y = _y;
		
		setImageView(_imagePath);
		
	}
	
	public void setHp(int _hp) {
		hp = _hp;
	}
	
	public void setSpeed(int _speed) {
		speed = _speed;
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
	
	public ImageView getImageView() {
		return imageView;
	}
	
}
