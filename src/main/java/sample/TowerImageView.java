package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class TowerImageView{
	
	private BasicTower tower;
	private ImageView imageView;
	
	static int maxX = MyController.ARENA_WIDTH;
	static int maxY = MyController.ARENA_HEIGHT;
	static int stepX = MyController.GRID_WIDTH;
	static int stepY = MyController.GRID_HEIGHT;

	
	public TowerImageView(BasicTower _tower) {
		tower = _tower;
		imageView = _tower.getImageView();
		
	    imageView.setFitWidth(MyController.GRID_WIDTH);
	    imageView.setFitHeight(MyController.GRID_HEIGHT); 
		setImageView(tower.getX(), tower.getY());
		
	}
	
	public void attackAtEachFrame() {
		
		
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
	
	public BasicTower getTower() {
		return tower;
	}
	
	public ImageView getImageView() {
		return imageView;
	}
	
	

}
