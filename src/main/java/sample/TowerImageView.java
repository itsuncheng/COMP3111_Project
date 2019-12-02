package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * TowerImageView class that contains a tower and its associated imageView for display on GUI
 * @author raymondcheng
 *
 */
public class TowerImageView{
	
	/**
	 * the tower this TowerImageView contains
	 */
	private BasicTower tower;
	/**
	 * the imageView this TowerImageView contains
	 */
	private ImageView imageView;
	
	/**
	 * width of the arena
	 */
	static int maxX = MyController.ARENA_WIDTH;
	/**
	 * height of the arena
	 */
	static int maxY = MyController.ARENA_HEIGHT;
	/**
	 * width of a grid in the arena
	 */
	static int stepX = MyController.GRID_WIDTH;
	/**
	 * height of a grid in the arena
	 */
	static int stepY = MyController.GRID_HEIGHT;

	/**
	 * constructor for TowerImageView class
	 * @param _tower a BasicTower
	 */
	public TowerImageView(BasicTower _tower) {
		tower = _tower;
		imageView = _tower.getImageView();
		
		if (imageView != null) {
			imageView.setFitWidth(MyController.GRID_WIDTH);
	    	imageView.setFitHeight(MyController.GRID_HEIGHT); 
		}
		setImageView(tower.getX(), tower.getY());
		
	}
	
	/**
	 * setting the imageView position
	 * @param _x x position
	 * @param _y y position
	 */
	public void setImageView(int _x, int _y) {
		
		if (imageView != null) {
		    
		    imageView.setX(_x); 
		    imageView.setY(_y); 
		      
		}
	}
	
	/**
	 * return tower of this TowerImageView
	 * @return BasicTower return tower of this TowerImageView 
	 */
	public BasicTower getTower() {
		return tower;
	}
	
	/**
	 * return imageView of this TowerImageView
	 * @return ImageView return imageView of this TowerImageView 
	 */
	public ImageView getImageView() {
		return imageView;
	}
	
	

}
