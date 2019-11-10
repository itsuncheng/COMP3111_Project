package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;

public class MyController {
    @FXML
    private Button buttonNextFrame;

    @FXML
    private Button buttonSimulate;

    @FXML
    private Button buttonPlay;

    @FXML
    private AnchorPane paneArena;

    @FXML
    private Label labelBasicTower;

    @FXML
    private Label labelIceTower;

    @FXML
    private Label labelCatapult;

    @FXML
    private Label labelLaserTower;

    static final int ARENA_WIDTH = 480;
    static final int ARENA_HEIGHT = 480;
    static final int GRID_WIDTH = 40;
    static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    
    Arena arena; //arena object													
  
    
    private int x = -1, y = 0; //where is my monster
    
    
    private ArrayList<MonsterImageView> monsterImageViewList = new ArrayList<MonsterImageView>();//An ArrayList of the monsterImageViews
    //private ArrayList<TowerImageView> towerImageViewList;  //TowerImageView to be implemented by Chris
    
    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        System.out.println("Play button clicked");
        Label newLabel = new Label();
        newLabel.setLayoutX(GRID_WIDTH / 4);
        newLabel.setLayoutY(GRID_WIDTH / 4);
        newLabel.setMinWidth(GRID_WIDTH / 2);
        newLabel.setMaxWidth(GRID_WIDTH / 2);
        newLabel.setMinHeight(GRID_WIDTH / 2);
        newLabel.setMaxHeight(GRID_WIDTH / 2);
        newLabel.setStyle("-fx-border-color: black;");
        newLabel.setText("*");
        newLabel.setBackground(new Background(new BackgroundFill(Color.MEDIUMBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        paneArena.getChildren().addAll(newLabel);
        
        //adding new comment for testing (1155) rick.
    }

    /**
     * A function that create the Arena
     */
    @FXML
    public void createArena() {
        if (grids[0][0] != null)
            return; //created already
        
        boolean [][] isGreen = new boolean [MAX_H_NUM_GRID][MAX_V_NUM_GRID]; 	//To be used in Arena constructor. Created here before the for loop
        																		//below so that I can record the greeness of a grid onto on to isGreen
        																		//as grids[][] is being created below.
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                Label newLabel = new Label();
                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1)) { // latter condition?
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                	isGreen[i][j]=false;	//recording whiteness
                }
                else {
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    isGreen[i][j]=true;		//recording greeness
                }
                newLabel.setLayoutX(j * GRID_WIDTH);  // why
                newLabel.setLayoutY(i * GRID_HEIGHT); // why
                newLabel.setMinWidth(GRID_WIDTH);
                newLabel.setMaxWidth(GRID_WIDTH);
                newLabel.setMinHeight(GRID_HEIGHT);
                newLabel.setMaxHeight(GRID_HEIGHT);
                newLabel.setStyle("-fx-border-color: black;");
                grids[i][j] = newLabel;
                paneArena.getChildren().addAll(newLabel);
                /*String s;				//testing the correctness of greeness recording
                if(isGreen[i][j])
                	s = "green";
                else
                	s = "white";
                
                grids[i][j].setText(s);*/
            }
        arena = new Arena(MAX_V_NUM_GRID,MAX_H_NUM_GRID,isGreen);
        
        
        
        //Start:Display endZone.png to end zone grid: Rick
        Image endZoneImage = new Image("file:src/main/resources/endZone.png");
        ImageView endZoneImageView = new ImageView(endZoneImage);
        endZoneImageView.setFitWidth(GRID_WIDTH);
        endZoneImageView.setFitHeight(GRID_HEIGHT);
        endZoneImageView.setLayoutX((MAX_V_NUM_GRID-1)*GRID_WIDTH);  
        endZoneImageView.setMouseTransparent(true);
              
        paneArena.getChildren().addAll(endZoneImageView);
        //End: Display endZone.png to end zone grid
        
      //Start:Display monsterSource.png to end monster Source grid: Rick
        Image monsterSourceImage = new Image("file:src/main/resources/monsterSource.png");
        ImageView monsterSourceImageView = new ImageView(monsterSourceImage);
        monsterSourceImageView.setFitWidth(GRID_WIDTH);
        monsterSourceImageView.setFitHeight(GRID_HEIGHT);
       	monsterSourceImageView.setMouseTransparent(true);
              
        paneArena.getChildren().addAll(monsterSourceImageView);
        //End: Display monsterSource.png to monsterSource grid
        
        
        //Start:This is example how to create monster and display it: Raymond
        Monster fox = new Fox();
        MonsterImageView monsterImageView = new MonsterImageView(fox);
        monsterImageViewList.add(monsterImageView); //adding the monster to the list, we can do this in Arena class
              
        paneArena.getChildren().addAll(monsterImageView.getImageView());
        //End: Display monster image to monster source grid
        
        //setMouseOver
        setDragAndDrop();
    }

    @FXML
    private void nextFrame() {
//        if (x == -1) {
//            grids[0][0].setText("M");
//            x = 0;
//            return;
//        }
//        if (y == MAX_V_NUM_GRID - 1)
//            return;
//        grids[y++][x].setText("");
//        grids[y][x].setText("M");
    	
    	// example of what every press of Next Frame should do (Raymond)
    	for(MonsterImageView monsterImageView: monsterImageViewList) {
	    	monsterImageView.moveAtEachFrame();
	    	Monster monster = monsterImageView.getMonster();
	    	
	    	if (monster.getHp() <= 0) {
	    		monsterImageView.removeFromArena(paneArena);
	    	}
    	}
    	
    }

    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop() {
        
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));
        
        for(int i = 0;i<MAX_H_NUM_GRID;i++) {
        	for(int j = 0; j < MAX_V_NUM_GRID;j++) {
        		final int tempI = i;
        		final int tempJ =j;
        		Label target = grids[i][j];
        		target.setOnDragDropped(new EventHandler<DragEvent>() {
        				    @Override
        				    public void handle(DragEvent event) {
        				        int gridX = pixelXToGridX((int)((Label)event.getGestureTarget()).getLayoutX());
        				    	int gridY = pixelYToGridY((int)((Label)event.getGestureTarget()).getLayoutY());
        				    	
        				    	
        				    	System.out.println(gridX+" "+gridY);
        				        Dragboard db = event.getDragboard();
        				        boolean success = false;
        				        System.out.println(db.getString());
        				        if (db.hasString()) {
        				            if(arena.isGreenGrid(gridY,gridX)) {
        				            	((Label)event.getGestureTarget()).setText(db.getString());
        				            	success = true;
        				            }
        				            else {
        				            	System.out.println("Cannot Build Tower on Non-Green Grid");
        				            }
        				            
        				        }
        				        event.setDropCompleted(success);
        				        event.consume();

        				    }
        				    private int pixelXToGridX(int pixelX) { //returns the X-grid coordinate of a given X-pixel coordinate
        				    	return pixelX/GRID_WIDTH;
        				    }
        				    
        				    private int pixelYToGridY(int pixelY) { //returns the Y-grid coordinate of a given Y-pixel coordinate
        				    	return pixelY/GRID_HEIGHT;
        				    }
        			 
        		});

        		
        		
        		
                //well, you can also write anonymous class or even lambda
                //Anonymous class
                target.setOnDragOver(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
                        System.out.println("onDragOver at ["+tempI+"]["+tempJ+"]");

                        /* accept it only if it is  not dragged from the same node
                         * and if it has a string data */
                        if (event.getGestureSource() != target &&
                                event.getDragboard().hasString()) {
                            /* allow for both copying and moving, whatever user chooses */
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });

                target.setOnDragEntered(new EventHandler <DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture entered the target */
                        //System.out.println("onDragEntered["+i+"]["+j+"]");
                        /* show to the user that it is an actual gesture target */
                        if (event.getGestureSource() != target &&
                                event.getDragboard().hasString()) {
                            target.setStyle("-fx-border-color: blue;");
                        }

                        event.consume();
                    }
                });
                //lambda
                
                target.setOnDragExited((event) -> {
                        /* mouse moved away, remove the graphical cues */
                        target.setStyle("-fx-border-color: black;");
                        System.out.println("Exit");
                        event.consume();
                });
        	}
        }
    }
}

class DragEventHandler implements EventHandler<MouseEvent> {
    private Label source;
    public DragEventHandler(Label e) {
        source = e;
    }
    @Override
    public void handle (MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);

        event.consume();
    }
}

class DragDroppedEventHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        System.out.println("xx");
        Dragboard db = event.getDragboard();
        boolean success = false;
        System.out.println(db.getString());
        if (db.hasString()) {
            ((Label)event.getGestureTarget()).setText(db.getString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();

    }
  
}





