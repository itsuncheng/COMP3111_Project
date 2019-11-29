package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class MonsterImageView{
	
	private Monster monster;
	private ImageView imageView;
	boolean directionDown;
	int numOfRightSteps;
	
	static int maxX = MyController.ARENA_WIDTH;
	static int maxY = MyController.ARENA_HEIGHT;
	static int stepX = MyController.GRID_WIDTH;
	static int stepY = MyController.GRID_HEIGHT;

	
	public MonsterImageView(Monster _monster) {
		monster = _monster;
		imageView = _monster.getImageView();
		
		if (imageView != null) {
		    imageView.setFitWidth(MyController.GRID_WIDTH);
		    imageView.setFitHeight(MyController.GRID_HEIGHT); 
			setImageView(0, 0);
		}
		
		directionDown = false;
		numOfRightSteps = 2;
		
	}
	
	public void moveOneGrid() {
		
		if (monster.isMoving == true) {
			int x = monster.getX();
			int y = monster.getY();
			
			if ((x == maxX-2*stepX && y == 0)) {
				
				x+=stepX;
				monster.setX(x);
				monster.setY(y);
				setImageView(monster.getX(), monster.getY());
				return;
			}
			
			if (y != 0 && y != maxY-stepY) {	// when monster is not at minX and maxX
				if(directionDown) {
					y+=stepY;	
				}else {
					y-=stepY;
				}
			}else {							// when monster is at minX and maxX
				if (numOfRightSteps==2) {			// time to change direction
					directionDown = !directionDown;		// change direction
					if(directionDown) {
						y+=stepY;	
					}else {
						y-=stepY;
					}
					numOfRightSteps = 0;
				}else {								// turning right
					x+=stepX;
					numOfRightSteps++;		
				}
	//			x+=stepX;
				
			}
			
			monster.setX(x);
			monster.setY(y);
			monster.moved();
			if (monster.getRemainingSteps() == 0) {
				monster.setIsMoving(false);
			}
			setImageView(monster.getX(), monster.getY());
		}
	}		
	
	public void stateEndOfEachFrame() {
		
		if (monster.isIced) {
			int nextIceTime = monster.getIceTime();
			monster.setIceTime(--nextIceTime);
			if (monster.getIceTime() == 0) {
				monster.setIsIced(false);
				monster.setSpeed(monster.getDefaultSpeed());
			}
		}
		
		if (monster.getMonsterType().equals("Penguin") ) {
			monster.replenishHp();
		}
		
	}
	
	public void removeFromArena(AnchorPane paneArena) {
		paneArena.getChildren().remove(imageView);
	}
	
	public void onHover() {
		
	}
	
	public void onHoverExit() {
		
	}
	
	
	public void setImageView(int _x, int _y) {
		
		if (imageView != null) {
		    
		    imageView.setX(_x); 
		    imageView.setY(_y); 
		      
		}
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	

}
