package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Math;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BasicTower {
	protected int x, y;
	protected static int attackPower;
	private static int range;
	private static int build_cost;
	private static int upgrade_cost;
	protected ImageView imageView = null;
	
	public static String _imagePath = "./src/main/resources/basicTower.png";
	
	public BasicTower(int _x, int _y) {
		x = _x;
		y = _y;
		
		attackPower = 3;
		range = 65;
		build_cost = 2;
		upgrade_cost = 1;
		
		setImageView(_imagePath);
	}
	
	public BasicTower(int _x, int _y, int _attackPower, int _range, int _build_cost, int _upgrade_cost, String _imagePath) {
		x = _x;
		y = _y;
		
		attackPower = _attackPower;
		range = _range;
		build_cost = _build_cost;
		upgrade_cost = _upgrade_cost;
		
		setImageView(_imagePath);
	}
	
	public boolean isInRange(Monster m) {
		return range > Math.sqrt(Math.pow(x-m.getX(), 2)+Math.pow(y-m.getY(), 2));
	}
	
	public boolean isInRange(int _x, int _y) {
		return range > Math.sqrt(Math.pow(x-_x, 2)+Math.pow(y-_y, 2));
	}
	
	public void shoot(Monster m) {
		m.setHp(m.getHp() - attackPower);
	}
	
	public void upgrade() {
		attackPower += 1;
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAttackPower() {
		return attackPower;
	}

	public int getRange() {
		return range;
	}
	
	public static int getBuildCost() {
		return build_cost;
	}
	
	
	public int getUpgradeCost() {
		return upgrade_cost;
	}

	public ImageView getImageView() {
		return imageView;
	}
	
	public String getTowerType() {
		return "Basic Tower";
	}
}
