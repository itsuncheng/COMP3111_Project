package sample;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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
    
    @FXML
    private Label labelMoney;

    static final int ARENA_WIDTH = 480;
    static final int ARENA_HEIGHT = 480;
    static final int GRID_WIDTH = 40;
    static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    
    Arena arena; //arena object													
    
    public static Random rand = new Random();
    public static int new_monster_speed_inc = 1;
    public static int moneyReward = 5;
    
    private ArrayList<MonsterImageView> monsterImageViewList = new ArrayList<MonsterImageView>();//An ArrayList of the monsterImageViews
    private ArrayList<TowerImageView> towerImageViewList = new ArrayList<TowerImageView>();  //TowerImageView to be implemented by Chris
    private ArrayList<ImageView> collisionImageViewList = new ArrayList<ImageView>();
    private ArrayList<Circle> rangeCircleList = new ArrayList<Circle>();
    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        System.out.println("Play button clicked");
        buttonNextFrame.setVisible(true);
        
        setDragAndDrop();
        
        
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
        
        //Start:Display monsterSource.png to end monster Source grid: Rick
        Image monsterSourceImage = new Image("file:src/main/resources/monsterSource.png");
        ImageView monsterSourceImageView = new ImageView(monsterSourceImage);
        monsterSourceImageView.setFitWidth(GRID_WIDTH);
        monsterSourceImageView.setFitHeight(GRID_HEIGHT);
       	monsterSourceImageView.setMouseTransparent(true);
              
        paneArena.getChildren().addAll(monsterSourceImageView);
        //End: Display monsterSource.png to monsterSource grid
        
        
        //Start:Display endZone.png to end zone grid: Rick
        Image endZoneImage = new Image("file:src/main/resources/endZone.png");
        ImageView endZoneImageView = new ImageView(endZoneImage);
        endZoneImageView.setFitWidth(GRID_WIDTH);
        endZoneImageView.setFitHeight(GRID_HEIGHT);
        endZoneImageView.setLayoutX((MAX_V_NUM_GRID-1)*GRID_WIDTH);  
        endZoneImageView.setMouseTransparent(true);
        
        paneArena.getChildren().addAll(endZoneImageView);
        
        //End: Display endZone.png to end zone grid

    }

    @FXML
    private void nextFrame() {
        
    	for (ImageView collisionImageView: collisionImageViewList)
    		paneArena.getChildren().removeAll(collisionImageView);
    	collisionImageViewList.clear();
    	
    	for(TowerImageView tIV: towerImageViewList) {
    		BasicTower tower = tIV.getTower();
    		ImageView towerImageView = tIV.getImageView();
    		
    		for (MonsterImageView mIV: monsterImageViewList) {
    			Monster monster = mIV.getMonster();
    			ImageView monsterImageView = mIV.getImageView();
    			if (tower.isInRange(monster)) {
	    			tower.shoot(monster);
	    			System.out.println("<" + tower.getTowerType() + ">@(<" + pixelXToGridX(tower.getX()) +">.<" + pixelYToGridY(tower.getY()) + ">) -> "
	    					+ "<" + monster.getMonsterType() + ">@(<" + pixelXToGridX(monster.getX()) + ">, <" + pixelYToGridY(monster.getY()) + ">)");
	    			if (monster.getHp() <= 0) {
	    				//create collision.png image on the dead monster grid
	    				Image collisionImage = new Image("file:src/main/resources/collision.png");
	    		        ImageView collisionImageView = new ImageView(collisionImage);
	    		        collisionImageView.setFitWidth(GRID_WIDTH);
	    		        collisionImageView.setFitHeight(GRID_HEIGHT);
	    		        collisionImageView.setX(monsterImageView.getX());
	    		        collisionImageView.setY(monsterImageView.getY());
	    		        
	    		        collisionImageViewList.add(collisionImageView);
	    		        paneArena.getChildren().addAll(collisionImageView);
	    		        
	    		        //remove monster from arena and monsterImageViewList
	    				paneArena.getChildren().remove(monsterImageView);
	    				monsterImageViewList.remove(mIV);
	    				
	    				//earn some money
	    				labelMoney.setText(String.valueOf(Integer.parseInt(labelMoney.getText()) + moneyReward));
	    	    	}
	    			break;
    			}
    			
    			
    		}
    		
    	}

    	
    	for(MonsterImageView mIV: monsterImageViewList) {
    		//move each monster
    		mIV.moveAtEachFrame();
    		
    		//check if it is gameover
    		if ((mIV.getImageView().getX() == (MAX_V_NUM_GRID-1)*GRID_WIDTH) && mIV.getImageView().getY() == 0){
    			System.out.println("Gameover");
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Warn");
        		alert.setHeaderText(null);
        		alert.setContentText("Monster reached end point, game is over!");
        		alert.showAndWait();
        		
    		}
    	}
    	
    	Monster new_monster;
		int typeOfMonster = rand.nextInt(3);
		if (typeOfMonster == 0) {
			new_monster = new Fox();
			System.out.println("<Fox>:<" + new_monster.getHp() + "> generated");
		} else if (typeOfMonster == 1) {
			new_monster = new Unicorn();
			System.out.println("<Unicorn>:<" + new_monster.getHp() + "> generated");
		} else {
			new_monster = new Penguin();
			System.out.println("<Penguin>:<" + new_monster.getHp() + "> generated");
		}
		
		// make generated monsters faster every frame
		new_monster.setSpeed(new_monster.getSpeed() + new_monster_speed_inc);
		new_monster_speed_inc += 1;
		
		
		MonsterImageView monsterImageView = new MonsterImageView(new_monster);
        monsterImageViewList.add(monsterImageView); //adding the monster to the list, we can do this in Arena class
        
        Popup popup = new Popup();
      	Label label = new Label();
      	label.setText("HP: " + new_monster.getHp()); 
        label.setMinWidth(80); 
        label.setMinHeight(50); 
        popup.getContent().add(label); 
        monsterImageView.getImageView().setOnMouseEntered((new EventHandler<MouseEvent>() { 
     	   public void handle(MouseEvent event) { 
     		   
     		    label.setText("HP: " + monsterImageView.getMonster().getHp()); 
	     		Node source = (Node) event.getSource();
	  		    Window stage = source.getScene().getWindow();
	            popup.show(stage);
     	   }
     	}));
        
        monsterImageView.getImageView().setOnMouseExited((new EventHandler<MouseEvent>() { 
      	   public void handle(MouseEvent event) { 
     		  if ((popup != null) && popup.isShowing()) {
     			  popup.hide();
     		  }
      	   }
      	}));
        
        //        monsterImageView.getImageView().setMouseTransparent(true);
        paneArena.getChildren().addAll(monsterImageView.getImageView());
    	
    }

    public BasicTower getTowerFromText(String s, int x, int y) {
    	BasicTower tower = null;
    	if (s.equals("Basic Tower")) {
    		if (Integer.parseInt(labelMoney.getText()) >= BasicTower.getBuildCost()) {
    			tower = new BasicTower(x, y);
    			labelMoney.setText(String.valueOf(Integer.parseInt(labelMoney.getText()) - BasicTower.getBuildCost()));
    		}
    	} else if (s.equals("Ice Tower")) {
    		if (Integer.parseInt(labelMoney.getText()) >= IceTower.getBuildCost()) {
    			tower = new IceTower(x, y);
    			labelMoney.setText(String.valueOf(Integer.parseInt(labelMoney.getText()) - IceTower.getBuildCost()));
    		}
    	} else if (s.equals("Laser Tower")) {
    		if (Integer.parseInt(labelMoney.getText()) >= LaserTower.getBuildCost()) {
    			tower = new LaserTower(x, y);
    			labelMoney.setText(String.valueOf(Integer.parseInt(labelMoney.getText()) - LaserTower.getBuildCost()));
    		}
    	} else if (s.equals("Catapult")){
    		if (Integer.parseInt(labelMoney.getText()) >= Catapult.getBuildCost()) {
    			tower = new Catapult(x, y);
    			labelMoney.setText(String.valueOf(Integer.parseInt(labelMoney.getText()) - Catapult.getBuildCost()));
    		}
    	}
    	return tower;
    }
    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop() {
        
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        Label source3 = labelCatapult;
        Label source4 = labelLaserTower;
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));
        source3.setOnDragDetected(new DragEventHandler(source3));
        source4.setOnDragDetected(new DragEventHandler(source4));
        
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
        				    	
        				    	int pixelX = (int)((Label)event.getGestureTarget()).getLayoutX();
        				    	int pixelY = (int)((Label)event.getGestureTarget()).getLayoutY();
        		
        				    	System.out.println(gridX+" "+gridY);
        				        Dragboard db = event.getDragboard();
        				        boolean success = false;
//        				        System.out.println(db.getString());
        				        if (db.hasString()) {
        				            if(arena.isGreenGrid(gridY,gridX)) {
        				            	
        				            	System.out.println("db.getString(): " + db.getString());
        				            	BasicTower tower = getTowerFromText(db.getString(), pixelX, pixelY);
        				            	if (tower != null) {
	        				            	TowerImageView towerImageView = new TowerImageView(tower); 
//	        				                System.out.println("tower: " + tower);
//	        				                System.out.println("towerImageView: " + towerImageView);
	        				                
	        				                
	        				            	towerImageViewList.add(towerImageView); //adding the monster to the list, we can do this in Arena class
	        				            	Popup popup = new Popup();
	        				            	Label label = new Label();
	        				            	label.setText("Attack Power: " + tower.getAttackPower() + ", Build Cost: " + tower.getBuildCost() + 
				                		    		  ", Upgrade Cost: " + tower.getUpgradeCost()); 
	        				            	label.setMinWidth(80); 
	        				                label.setMinHeight(50);
	        				            	popup.getContent().add(label); 
	        				                
	        				            	towerImageView.getImageView().setOnMouseEntered((new EventHandler<MouseEvent>() { 
	    				                	   public void handle(MouseEvent event) { 
	    				                		   //displaying popup of tower information
	    				                		    Node source = (Node) event.getSource();
	    				                		    Window stage = source.getScene().getWindow();
			        			 
			        				                popup.show(stage);
			        				                
			        				                //show range on GUI
			        				                for(int a = 0;a<MAX_H_NUM_GRID;a++) {
			        				                	for(int b = 0; b < MAX_V_NUM_GRID;b++) {
			        				                		if (!arena.isGreenGrid(a,b)) {
				        				                		int pixelX2 = (int) grids[a][b].getLayoutX() + GRID_WIDTH/2;
				        				                		int pixelY2 = (int) grids[a][b].getLayoutY() + GRID_HEIGHT/2;
	//			        				                		int pixelX2 = gridXToPixelX(gridX2);
	//			        				                		int pixelY2 = gridYToPixelY(gridY2);
				        				                		System.out.println(pixelX2 + " " + pixelY2);
				        				                		if (tower.isInRange(pixelX2, pixelY2)) {
				        				                			System.out.println("in range");
				        				                			Circle circle = new Circle(pixelX2, pixelY2, 10);
				        				                			circle.setStyle("-fx-background-color: yellow;");
				        				                			paneArena.getChildren().addAll(circle);
				        				                			rangeCircleList.add(circle);
				        				                		}
			        				                		}
			        				                		
			        				                	}
			        				                }
					                		   } 
	    				                	}));
	        				            	
	        				            	towerImageView.getImageView().setOnMouseExited((new EventHandler<MouseEvent>() { 
	     				                	   public void handle(MouseEvent event) { 
	     				                		//hiding popup of tower information
	     				                		  if ((popup != null) && popup.isShowing()) {
	     				                			  popup.hide();
	     				                		  }
	     				                		  for (Circle c: rangeCircleList) {
	     				                			 paneArena.getChildren().remove(c);
	     				                		  }
	     				                		 rangeCircleList.clear();
	 				                		   } 
	     				                	}));
	        				            	
	//        				            	towerImageView.getImageView().setMouseTransparent(true);
	        				                paneArena.getChildren().addAll(towerImageView.getImageView());
	        				                
	//        				            	((Label)event.getGestureTarget()).setText(db.getString());
	        				            	success = true;
        				            	} else {
        				            		Alert alert = new Alert(AlertType.INFORMATION);
        				            		alert.setTitle("Warn");
        				            		alert.setHeaderText(null);
        				            		alert.setContentText("Not enough resource to build tower");

        				            		alert.showAndWait();
        				            	}
        				            	
        				            }
        				            else {
        				            	System.out.println("Cannot Build Tower on Non-Green Grid");
        				            }
        				            
        				        }
        				        event.setDropCompleted(success);
        				        event.consume();

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
    
    private int pixelXToGridX(int pixelX) { //returns the X-grid coordinate of a given X-pixel coordinate
    	return pixelX/GRID_WIDTH;
    }
    
    private int pixelYToGridY(int pixelY) { //returns the Y-grid coordinate of a given Y-pixel coordinate
    	return pixelY/GRID_HEIGHT;
    }
    
    private int gridXToPixelX(int gridX) { //returns the X-grid coordinate of a given X-pixel coordinate
    	return gridX * GRID_WIDTH;
    }
    
    private int gridYToPixelY(int gridY) { //returns the Y-grid coordinate of a given Y-pixel coordinate
    	return gridY * GRID_HEIGHT;
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
//            ((Label)event.getGestureTarget()).setText(db.getString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();

    }
  
}

