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

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        MyController appController = (MyController)loader.getController();
        outsideController = appController;
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
	
	public void testNextFrameButton2() {
		clickOn("#buttonPlay");
		for(int i = 0;i<16;i++) {
			clickOn("#buttonNextFrame");
		}
	}
		//drag("#labelBasicTower").dropTo(20,20);
		
	@Test
	public void testShowMonsterInfo() {
		clickOn("#buttonPlay");
		clickOn("#buttonNextFrame");
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				
				if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
					//drag("#labelBasicTower").dropTo(h);
					moveTo(h);
			}
		}
		
		/*AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
					Assert.assertEquals(h.getText(), "M");
			} 
		}*/
	}
	
	@Test
	public void testMakeBasicTowerInfo() {
		clickOn("#buttonPlay");
		
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		
		Label h = new Label();
		
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				h = (Label)i;
				
				if (h.getLayoutX() == 40 && h.getLayoutY() == 0)
					break;
			}
		}
		/*moveTo("#labelBasicTower");
		drag();
		moveTo("#labelBasicTower");
		sleep(1000);
		moveTo(h);
		drop();*/
		
		
		for(int i = 0;i<21;i++) {
			clickOn("#buttonNextFrame");
		}
		
		/*AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
					Assert.assertEquals(h.getText(), "M");
			}
		}*/
	}
	
	
	/*@Test
	/*public void testGetTowerFromText() {
		BasicTower bTower;
		bTower = outsideController.getTowerFromText("Basic Tower",40,0);
		bTower = outsideController.getTowerFromText("Ice Tower",40,40);
		bTower = outsideController.getTowerFromText("Catapult",40,80);
		bTower = outsideController.getTowerFromText("Laser Tower",40,120);
		
	}*/
}
