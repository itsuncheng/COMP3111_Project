///**
// * 
// * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
// * 
// */
package sample;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxRobot;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
 

public class MyControllerTest extends ApplicationTest {
	
	MyController outsideController;

	private Scene s;
	private MyController appController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        appController = (MyController)loader.getController();
        outsideController = appController;
        appController.createArena();   		
        appController.createArena();
	}

	
	@Test
	public void testPlayButton() {
		clickOn("#buttonPlay");
	}
	
	@Test
	public void testNextFrameButton() {
		clickOn("#buttonPlay");
		for(int i = 0;i<20;i++) {
			clickOn("#buttonNextFrame");
		}
	}
	
	@Test
	public void testTower() {
		clickOn("#buttonPlay");
		Label[][] grids = appController.getGrids();
		for (int i=0; i<grids.length; i++) {
			for (int j=0; j<grids[0].length; j++) {
				if (j % 2 == 1 && i == grids.length/2) {
					Label h = (Label)grids[i][j];
					drag("#labelBasicTower").dropTo(h);
					clickOn(h);
					clickOn("#buttonUpgradeTower");
					clickOn(h);
				}
			}
		}
		
		for(int i = 0;i<16;i++) {
			clickOn("#buttonNextFrame");
		}
		
		
	}
	
	@Test
	public void testIceTower() {
		clickOn("#buttonPlay");
		Label[][] grids = appController.getGrids();
		
		Label h1 = (Label)grids[grids.length/2][1];
		drag("#labelIceTower").dropTo(h1);		
		
	}
	
	@Test
	public void testLaserTower() {
		clickOn("#buttonPlay");
		Label[][] grids = appController.getGrids();
		
		Label h1 = (Label)grids[grids.length/2][1];
		drag("#labelLaserTower").dropTo(h1);		
		
	}
	
	@Test
	public void testCatapultTower() {
		clickOn("#buttonPlay");
		Label[][] grids = appController.getGrids();
		
		Label h1 = (Label)grids[grids.length/2][1];
		drag("#labelCatapult").dropTo(h1);
	}
	
	@Test
	public void testDestroyTower() {
		clickOn("#buttonPlay");
		Label[][] grids = appController.getGrids();
		
		Label h1 = (Label)grids[grids.length/2][1];
		drag("#labelBasicTower").dropTo(h1);
		clickOn(h1);
		clickOn("#buttonDestroyTower");
		
	}
	
}
