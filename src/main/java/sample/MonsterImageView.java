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
		
	    imageView.setFitWidth(MyController.GRID_WIDTH);
	    imageView.setFitHeight(MyController.GRID_HEIGHT); 
		setImageView(0, 0);
		
		directionDown = false;
		numOfRightSteps = 2;
		
	}
	
	public void moveAtEachFrame() {
		
		int x = monster.getX();
		int y = monster.getY();
		int speed = monster.getSpeed();
		
		
		
		// write this in MyController
		
		
		for (int i=0; i<speed; i++) {
			
			if ((x == maxX-2*stepX && y == 0)) {
				
				x+=stepX;
				monster.setX(x);
				monster.setY(y);
				setImageView(monster.getX(), monster.getY());
				
				System.out.println("Gameover");
				return;
			}
			
			if (y != 0 && y != maxY-stepY) {	// when monster is not at minX and maxX
				if(directionDown) {
					y+=stepY;	
				}else {
					y-=stepY;
				}
				System.out.println("y: " + y + ", maxY: " + maxY);
			}else {							// when monster is at minX and maxX
				if (numOfRightSteps==2) {			// time to change direction
					directionDown = !directionDown;		// change direction
					if(directionDown) {
						y+=stepY;	
					}else {
						y-=stepY;
					}
					numOfRightSteps = 0;
					System.out.println("y: " + y + ", maxY: " + maxY);
				}else {								// turning right
					x+=stepX;
					numOfRightSteps++;		
					System.out.println("x: " + x + ", maxX: " + maxX);
				}
//				x+=stepX;
				
			}
		}
		
		monster.setX(x);
		monster.setY(y);
		setImageView(monster.getX(), monster.getY());
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
