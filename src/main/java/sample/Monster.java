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
		
		setImageView(_imagePath, _x, _y);
		
	}
	
	
	public void moveAtEachFrame() {
		int maxX = MyController.ARENA_HEIGHT;
		int maxY = MyController.ARENA_WIDTH;
		
		boolean directionDown = true;
		int numOfRightSteps = 0;
		
		//write this in MyController
//		if (x == maxX-1 || x == maxY-1) {
//			System.out.println("Gameover");
//			return;
//		}
		
		for (int i=0; i<speed; i++) {
			if (x != 0 || x != maxX-1) {	// when monster is not at minX and maxX
				if(directionDown) {
					x++;	
				}else {
					x--;
				}
			}else {							// when monster is at minX and maxX
				if (numOfRightSteps==2) {			// time to change direction
					directionDown = !directionDown;		// change direction
					if(directionDown) {
						x++;	
					}else {
						x--;
					}
					numOfRightSteps = 0;
				}else {								// turning right
					y++;
					numOfRightSteps++;		
				}
				
			}
		}
	}
	
	public void removeFromArena() {
		
	}
	
	public void onHover() {
		
	}
	
	public void onHoverExit() {
		
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
	
	public void setImageView(String _imagePath, int _x, int _y) {
		Image image = null;
		
		try {
			image = new Image(new FileInputStream(_imagePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		if (image != null) {
		    imageView = new ImageView(image); 
		    
		    imageView.setX(_x); 
		    imageView.setY(_y); 
		      
		    imageView.setFitHeight(MyController.GRID_WIDTH); 
		    imageView.setFitWidth(MyController.GRID_WIDTH); 
		      
		    imageView.setPreserveRatio(true); 
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
