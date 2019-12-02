package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main driver class to start up the GUI application
 * @author raymondcheng
 *
 */
public class Main extends Application {
	
	/**
	 * sets up the configuration for the GUI application using javafx
	 * @param primaryStage built-in argument required by javafx to load GUI application
	 */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
        MyController appController = (MyController)loader.getController();
        appController.createArena();
    }

    /**
     * main function (entry point of the program) which calls the start function above to load the GUI
     * @param args
     */
    public static void main(String[] args) {
    	
        launch(args);
    }
}
