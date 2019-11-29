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
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.lang.Math;

public class MyController {
	
    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonNextFrame;

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
    
    @FXML
    private Button buttonDestroyTower;

    @FXML
    private Button buttonUpgradeTower;

    static final int ARENA_WIDTH = 480;
    static final int ARENA_HEIGHT = 480;
    static final int GRID_WIDTH = 40;
    static final int GRID_HEIGHT = 40;
    static final int INITIAL_MONEY = 20;

    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;

    private Label grids[][] = new Label [MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    private boolean isGridOccupied[][] = new boolean [MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    
    Arena arena; //arena object													
    
    public static boolean gameOver = false;
    
    public static Random rand = new Random();
    public static int new_monster_speed_inc = 0;
    public static int max_speed_inc = 4;
    public static int frameNum = 1;
    public static int moneyReward = 5;
    
    private ArrayList<MonsterImageView> monsterImageViewList = new ArrayList<MonsterImageView>();//An ArrayList of the monsterImageViews
    private ArrayList<TowerImageView> towerImageViewList = new ArrayList<TowerImageView>();  //TowerImageView to be implemented by Chris
    private ArrayList<ImageView> collisionImageViewList = new ArrayList<ImageView>();
    private ArrayList<Shape> rangeShapeList = new ArrayList<Shape>();
    private ArrayList<Rectangle> shotIndicatingRecList = new ArrayList<Rectangle>();
    private ArrayList<Rectangle> laserList = new ArrayList<Rectangle>();
    private static TowerImageView selectedTowerImageView = null;
    private static Map<BasicTower, Monster> towerAndTargetMonsterMap = new HashMap(); 
    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        buttonNextFrame.setVisible(true);
        
        setDragAndDrop();
        
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
        for (int i = 0; i < MAX_V_NUM_GRID; i++) {
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
                
                isGridOccupied[i][j] = false;
            }
        }
        arena = new Arena(MAX_V_NUM_GRID,MAX_H_NUM_GRID,isGreen);
        arena.setMoney(INITIAL_MONEY);
        labelMoney.setText(String.valueOf(arena.getMoney()));
        
        
        // display monsterSourceImage to grid
        Image monsterSourceImage = new Image("file:src/main/resources/monsterSource.png");
        ImageView monsterSourceImageView = new ImageView(monsterSourceImage);
        monsterSourceImageView.setFitWidth(GRID_WIDTH);
        monsterSourceImageView.setFitHeight(GRID_HEIGHT);
       	monsterSourceImageView.setMouseTransparent(true);
              
        paneArena.getChildren().addAll(monsterSourceImageView);
        
        
        // display monsterEndZoneImage to grid
        Image endZoneImage = new Image("file:src/main/resources/endZone.png");
        ImageView endZoneImageView = new ImageView(endZoneImage);
        endZoneImageView.setFitWidth(GRID_WIDTH);
        endZoneImageView.setFitHeight(GRID_HEIGHT);
        endZoneImageView.setLayoutX((MAX_V_NUM_GRID-1)*GRID_WIDTH);  
        endZoneImageView.setMouseTransparent(true);
        
        paneArena.getChildren().addAll(endZoneImageView);
        

    }

    @FXML
    private void nextFrame() {
        
    	if(gameOver)
    		return;
    	
    	for (ImageView collisionImageView: collisionImageViewList)
    		paneArena.getChildren().removeAll(collisionImageView);
    	collisionImageViewList.clear();
    	
    	for (Rectangle shotIndicatingRec: shotIndicatingRecList)
    		paneArena.getChildren().removeAll(shotIndicatingRec);
    	shotIndicatingRecList.clear();
    	
    	for (Rectangle laser: laserList)
    		paneArena.getChildren().removeAll(laser);
    	laserList.clear();
    	
    	for (MonsterImageView mIV: monsterImageViewList) {
    		mIV.getMonster().setIsMoving(true);
    		mIV.getMonster().initRemainingSteps();
    	}
    	
    	for (TowerImageView tIV: towerImageViewList) {
    		tIV.getTower().setIsShot(false);
    	}
    	
    	towerAndTargetMonsterMap.clear();
    	
	    while (isOneMonsterMoving()) {
    		for(MonsterImageView mIV: monsterImageViewList) {
	    		//move each monster
	    		mIV.moveOneGrid();
	    		
	    		//check if it is gameover
	    		if ((mIV.getImageView().getX() == (MAX_V_NUM_GRID-1)*GRID_WIDTH) && mIV.getImageView().getY() == 0){
	    			System.out.println("Gameover");
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Warn");
	        		alert.setHeaderText(null);
	        		alert.setContentText("Monster reached end point, game is over!");
	        		alert.showAndWait();
	        		
	        		gameOver = true;
	        		return;
	    		}
	    		
	    		
	    	}
    		
    		for(TowerImageView tIV: towerImageViewList) {
            	
    			if (tIV.getTower().getIsShot() == false) {
	        		BasicTower tower = tIV.getTower();
	        		
	        		boolean targetFound = false;	//true if there is a monster in range, false otherwise
	        		Monster targetMonster = null;	//stores the Monster that the tower will shoot
	        		double targetMonsterDFE = Double.POSITIVE_INFINITY; //Distance From Endzone: distance between the the top-left corners of EZ and TargetMonster
	        		
	        		for (MonsterImageView mIV: monsterImageViewList) {
	        			
	        			Monster monster = mIV.getMonster();
	//        			System.out.println("monsterX"+monster.getX()+"  monsterY:"+monster.getY()+"towerX"+tower.getX()+"  towerY:"+tower.getY());
	        			if (tower.isInRange(monster)) {
	//        				System.out.println("a monster is in range");
	        				targetFound = true;
	        				
	        				double monsterX = monster.getX();
	        				double monsterY = monster.getY();
	        				int endZoneX = (MAX_V_NUM_GRID-1)*GRID_WIDTH;
	        				int endZoneY = 0;
	        				  				
	        				double monsterDFE = Math.sqrt(((monsterX-endZoneX)*(monsterX-endZoneX))+((monsterY-endZoneY)*(monsterY-endZoneY)));
	        				if(monsterDFE < targetMonsterDFE) {
	        					targetMonster = monster;
	        					targetMonsterDFE = monsterDFE;
	        				}
	        			}	
	        		}
	        		
	        		if (targetFound) {
	
	        			if (tower.getTowerType() == "Laser Tower") {
	        				Rectangle laser = new Rectangle();
	        				
	        				laser.setFill(Color.BLUE);
	        				laser.setOpacity(50);
	        				laser.setWidth(ARENA_WIDTH*2);
	        				laser.setHeight(6);
	        				double towerX = tower.getX();
	        				double towerY = tower.getY();
	        				laser.setX(towerX-1.5+GRID_WIDTH/2);
	        				laser.setY(towerY-1.5+GRID_WIDTH/2);
	        				   	
	        				double monsterX = targetMonster.getX();
	        				double monsterY = targetMonster.getY();
	        				double angle = Math.atan2(monsterY - towerY, monsterX - towerX);
	        				angle = Math.toDegrees(angle);
	        				Rotate rotate = new Rotate(angle, towerX+GRID_WIDTH/2, towerY+GRID_WIDTH/2);
	        				laser.getTransforms().addAll(rotate);
	        				laser.setMouseTransparent(true);
	        				laserList.add(laser);
	        				
	        				paneArena.getChildren().addAll(laser);
	        			}
	        				
	        			tower.shoot(targetMonster,arena);
	        			tower.setIsShot(true);
	        			
	        			towerAndTargetMonsterMap.put(tower, targetMonster);
	        			
	        		}
        		}
        		
        	}
            
	    }
	    
	    for (Entry<BasicTower, Monster> entry : towerAndTargetMonsterMap.entrySet()) {
	    	
	    	BasicTower tower = entry.getKey();
	    	Monster targetMonster = entry.getValue();
	    	
	    	System.out.println("<" + tower.getTowerType() + ">@(<" + pixelXToGridX(tower.getX()) +">.<" + pixelYToGridY(tower.getY()) + ">) -> "
					+ "<" + targetMonster.getMonsterType() + ">@(<" + pixelXToGridX(targetMonster.getX()) + ">, <" + pixelYToGridY(targetMonster.getY()) + ">)");
			
			//Create Shot Indicating rectangle (A loop that surrounds a tower and a monster where the tower has shot the monster) 
			double filletRadius = 1.5 * (GRID_WIDTH/2.0);
			double monsterX = targetMonster.getX();
			double monsterY = targetMonster.getY();
			double towerX = tower.getX();
			double towerY = tower.getY();
			
//    				System.out.println("monsterX:"+monsterX+"  monsterY:"+monsterY
//    						+"   towerX"+towerX+"  towerY:"+towerY
//    						);
			
			double distance = Math.sqrt(((monsterX-towerX)*(monsterX-towerX))+((monsterY-towerY)*(monsterY-towerY)));
			
			
			Rectangle shotIndicatingRec = new Rectangle();
			
			shotIndicatingRec.setFill(Color.TRANSPARENT);
			shotIndicatingRec.setStroke(Color.ORANGE);
			shotIndicatingRec.setStrokeWidth(2.0); 
			
			shotIndicatingRec.setWidth(filletRadius + distance + filletRadius);
			shotIndicatingRec.setHeight(2*filletRadius);
			
			shotIndicatingRec.setArcWidth(2*filletRadius); 
		    shotIndicatingRec.setArcHeight(2*filletRadius);  
			
			shotIndicatingRec.setX(towerX - (filletRadius - (GRID_WIDTH/2)));
			shotIndicatingRec.setY(towerY - (filletRadius - (GRID_WIDTH/2)));
			
			double xDisplacementMRelT = monsterX - towerX; //x-displacement of monster relative to tower;
			double yDisplacementMRelT = monsterY - towerY; //y-displacement of monster relative to tower;
			
			double angle = Math.atan2(yDisplacementMRelT, xDisplacementMRelT);  //atan2 returns range from 0 - 2pi
			angle = Math.toDegrees(angle);
			
			Rotate rotate = new Rotate(angle,towerX+(GRID_WIDTH/2),towerY+(GRID_WIDTH/2));
			shotIndicatingRec.getTransforms().addAll(rotate);
			shotIndicatingRec.setMouseTransparent(true);
			shotIndicatingRecList.add(shotIndicatingRec);
			
			paneArena.getChildren().addAll(shotIndicatingRec);
	    }
	    
	    
	    for(MonsterImageView mIV: monsterImageViewList) {
	    	mIV.stateEndOfEachFrame();
	    }
    	Monster new_monster;
		int typeOfMonster = rand.nextInt(3);
		if (typeOfMonster == 0) {
			new_monster = new Fox();
		} else if (typeOfMonster == 1) {
			new_monster = new Unicorn();
		} else {
			new_monster = new Penguin();
		}
		
		// make generated monsters faster every 4 frames
		new_monster.setSpeed(new_monster.getSpeed() + new_monster_speed_inc);
		if (frameNum % 4 == 0 && new_monster_speed_inc < max_speed_inc) {
			new_monster_speed_inc += 1;
		}
		frameNum += 1;
		
		System.out.println("<" + new_monster.getMonsterType() + ">:<" + new_monster.getHp() + "> generated");
		
		arena.addMonster(new_monster);
		
		
		MonsterImageView monsterImageView = new MonsterImageView(new_monster);
        monsterImageViewList.add(monsterImageView); //adding the monster to the list, we can do this in Arena class
        
        Popup popup = new Popup();
      	Label label = new Label();
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
        
        paneArena.getChildren().addAll(monsterImageView.getImageView());  
        
        for(int i=0; i<monsterImageViewList.size(); i++) {
        	MonsterImageView mIV = monsterImageViewList.get(i);
        	Monster monster = mIV.getMonster();
			if (monster.getHp() <= 0) {
				//create collision.png image on the dead monster grid
				Image collisionImage = new Image("file:src/main/resources/collision.png");
		        ImageView collisionImageView = new ImageView(collisionImage);
		        collisionImageView.setFitWidth(GRID_WIDTH);
		        collisionImageView.setFitHeight(GRID_HEIGHT);
		        collisionImageView.setX(mIV.getImageView().getX());
		        collisionImageView.setY(mIV.getImageView().getY());
		        collisionImageViewList.add(collisionImageView);
		        
		        paneArena.getChildren().addAll(collisionImageView);
				
		        //remove monster from arena and monsterImageViewList
				monsterImageViewList.remove(mIV);
				arena.removeMonster(monster);
				paneArena.getChildren().removeAll(mIV.getImageView());
				
		        //earn some money
				arena.addMoney(moneyReward);
			}
        }
		
        labelMoney.setText(String.valueOf(arena.getMoney())); //update GUI money label
    	
    }

    public BasicTower getTowerFromText(String s, int x, int y) {
    	BasicTower tower = null;
    	int buildCost = 0;
    	
    	if (s.equals("Basic Tower")) {
    		if (arena.getMoney() >= BasicTower.getBuildCost()) {
    			tower = new BasicTower(x, y);
    			buildCost = BasicTower.getBuildCost();
    		}
    	} else if (s.equals("Ice Tower")) {
    		if (arena.getMoney() >= IceTower.getBuildCost()) {
    			tower = new IceTower(x, y);
    			buildCost = IceTower.getBuildCost();
    		}
    	} else if (s.equals("Laser Tower")) {
    		if (arena.getMoney() >= LaserTower.getBuildCost()) {
    			tower = new LaserTower(x, y);
    			buildCost = LaserTower.getBuildCost();
    		}
    	} else if (s.equals("Catapult")){
    		if (arena.getMoney() >= Catapult.getBuildCost()) {
    			tower = new Catapult(x, y);
    			buildCost = Catapult.getBuildCost();
    		}
    	}
    	
    	arena.removeMoney(buildCost);
		labelMoney.setText(String.valueOf(arena.getMoney()));
		
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
				            if(arena.isGreenGrid(gridY,gridX) && !isGridOccupied[gridY][gridX]) {
				            	
	//        				            	System.out.println("db.getString(): " + db.getString());
				            	BasicTower tower = getTowerFromText(db.getString(), pixelX, pixelY);
				            	if (tower != null) {
					            	TowerImageView towerImageView = new TowerImageView(tower); 
					          
	//	        				                System.out.println("tower: " + tower);
	//	        				                System.out.println("towerImageView: " + towerImageView);
					                
					                
					            	towerImageViewList.add(towerImageView); //adding the tower to the list, we can do this in Arena class
					            	Popup popup = new Popup();
					            	Label label = new Label();
					            	label.setMinWidth(80); 
					                label.setMinHeight(50);
					            	popup.getContent().add(label); 
					                
					            	towerImageView.getImageView().setOnMouseEntered((new EventHandler<MouseEvent>() { 
				                	   public void handle(MouseEvent event) {
				                		   //displaying popup of tower information
				                		   label.setText("Attack Power: " + towerImageView.getTower().getAttackPower() + ", Range: " + towerImageView.getTower().getRange() + ", Build Cost: " + towerImageView.getTower().getBuildCost() + 
			                		    		  ", Upgrade Cost: " + towerImageView.getTower().getUpgradeCost()); 
				                		    Node source = (Node) event.getSource();
				                		    Window stage = source.getScene().getWindow();
	        				                popup.show(stage);
	        				                
	        				                //show range on GUI: New Implementation
	        				                int towerCenterX = tower.getX()+(GRID_WIDTH/2);
	        				                int towerCenterY = tower.getY()+(GRID_HEIGHT/2);
	        				                
	        				                if(tower instanceof Catapult) {  //Catapult needs to be seperated because its range is a donut not a circle ;)
//	        				                	System.out.println("mouse-over a Catapult");
	        				                	Catapult c = (Catapult) tower;  //Cast to Catapult to access Catapult function
	        				                	Circle circle = new Circle();
	        				                	circle.setCenterX(towerCenterX);
	        				                	circle.setCenterY(towerCenterY);
	        				                	circle.setRadius(c.getRange());
	        				                	circle.setFill(Color.TRANSPARENT);
	        				                	circle.setStroke(Color.PINK);
	        				                	circle.setStrokeType(StrokeType.INSIDE);
	        				                	circle.setStrokeWidth(c.getRange()-c.getLowRange());
	        				                	circle.setOpacity(0.3);
	        				                	circle.setMouseTransparent(true);
	        				                	paneArena.getChildren().addAll(circle);
	        				                	rangeShapeList.add(circle);
	        				                }
	        				                else if(tower instanceof BasicTower || tower instanceof IceTower || tower instanceof LaserTower){
//	        				                	System.out.println("mouse-over a BasicTower, IceTower, or LaserTower");
	        				                	Circle circle = new Circle();
	        				                	circle.setCenterX(towerCenterX);
	        				                	circle.setCenterY(towerCenterY);
	        				                	circle.setRadius(tower.getRange());
	        				                	circle.setFill(Color.PINK);
	        				                	circle.setOpacity(0.3);
	        				                	circle.setMouseTransparent(true);
	        				                	paneArena.getChildren().addAll(circle);
	        				                	rangeShapeList.add(circle);
	        				                	
	        				                }
			                		   } 
				                	}));
					            	
					            	towerImageView.getImageView().setOnMouseExited((new EventHandler<MouseEvent>() { 
				                	   public void handle(MouseEvent event) { 
				                		//hiding popup of tower information
				                		  if ((popup != null) && popup.isShowing()) {
				                			  popup.hide();
				                		  }
				                		  for (Shape s: rangeShapeList) {
				                			 paneArena.getChildren().remove(s);
				                		  }
				                		 rangeShapeList.clear();
			                		   } 
				                	}));
					            	
					            	towerImageView.getImageView().setOnMouseClicked((new EventHandler<MouseEvent>() { 
				                	   public void handle(MouseEvent event) { 
				                		// show pane for destroy or upgrade tower
				                		   selectedTowerImageView = towerImageView;
				                		   if (!buttonDestroyTower.isVisible() && !buttonUpgradeTower.isVisible()) {
	 				                		   buttonDestroyTower.setVisible(true);
	 				                		   buttonUpgradeTower.setVisible(true);
				                		   } else {
				                			  buttonDestroyTower.setVisible(false);
	 				                		  buttonUpgradeTower.setVisible(false);
				                		   }
				                		   
			                		   } 
				                	}));
					            	
					                paneArena.getChildren().addAll(towerImageView.getImageView());
					                
	//        				            	((Label)event.getGestureTarget()).setText(db.getString());
					            	success = true;
					            	isGridOccupied[gridY][gridX] = true;
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
    
    public boolean isOneMonsterMoving(){
    	
    	boolean isOneMonsterMoving = false;
    	for (MonsterImageView mIV: monsterImageViewList) {
    		if (mIV.getMonster().getIsMoving() == true)
    			isOneMonsterMoving = true;
    	}
    	return isOneMonsterMoving;
    }
    
    public void destroyTower() {
    	if (selectedTowerImageView != null) {
    		int pixelX = selectedTowerImageView.getTower().getX();
    		int pixelY = selectedTowerImageView.getTower().getY();
    		int gridX = pixelXToGridX(pixelX);
    		int gridY = pixelYToGridY(pixelY);
    		isGridOccupied[gridY][gridX] = false;
    		
	    	towerImageViewList.remove(selectedTowerImageView);
	    	paneArena.getChildren().remove(selectedTowerImageView.getImageView());
	    	buttonDestroyTower.setVisible(false);
  		    buttonUpgradeTower.setVisible(false);
    	}
    }
    
    public void upgradeTower() {
    	if (selectedTowerImageView != null) {
    		int upgradeCost = selectedTowerImageView.getTower().getUpgradeCost();
    		System.out.println("upgrade cost: " + upgradeCost);
    		if (Integer.parseInt(labelMoney.getText()) >= upgradeCost) {
    			//upgrade tower
    			selectedTowerImageView.getTower().upgrade();
    			// decrease money
    			arena.removeMoney(upgradeCost);
    			labelMoney.setText(String.valueOf(arena.getMoney()));
    			// update tower info on GUI
    			
    			System.out.println(selectedTowerImageView.getTower().getTowerType() + " tower is being upgraded");
    		} else {
    			System.out.println("not enough resource to upgrade " + selectedTowerImageView.getTower().getTowerType() + " tower");
    		}
    	}
    }
    
    public Label[][] getGrids(){
    	return grids;
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

