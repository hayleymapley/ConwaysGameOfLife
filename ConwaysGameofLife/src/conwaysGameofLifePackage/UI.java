package conwaysGameofLifePackage;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.*;

/**This class draws the UI and any cells contained in the Grids HashMap
 * 
 * @author Group3 
 *
 */
public class UI extends Application{
	
	private int startWidth = 600;
	private int startHeight = 400;
	
//	private Group displayGroup = new Group();		//TODO: add to group?
	private BorderPane canvas = new BorderPane();			// parent pane
	private ScrollPane scrollPane = new ScrollPane();
	private Pane simulationPane = new Pane();				// simulation pane TODO: make infinite
	private Pane controlPane = new Pane();					// control pane (for adding buttons)
	private HBox controlBox = new HBox();					// for containing buttons
	private Grid worldGrid = new Grid();
	
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	/**
	 * Main Entry point for the application
	 * <p>
	 * Initializes Panes (nests panes from scene down)
	 * Initializes Grid (adds initial layout of AliveCells)
	 * Runs Timeline
	 * EventHandlers for buttons
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initialiseWorldGrid();
		initialisePanes();
		
		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//update cells - calling on grid
				//draw cells - calling on grid which has hashmap
			}
		});
		
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
		timeline.play();
		
		primaryStage.setTitle("Game of Life");
		Scene mainScene = new Scene(canvas, startWidth, startHeight);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		playPause.setOnAction(new EventHandler<ActionEvent>() {
			int i = 0;	//to keep track of odd/even number of clicks so we can use as a toggle button
			@Override
			public void handle(ActionEvent event) {
				if(i%2 == 0) {
					timeline.pause();		//on first press will pause
				} else {
					timeline.play();		//every second press will play
				}
				i++;
			}
		});
		
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			}
		});
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
	}
	
	/**
	 * Sets attributes for buttons
	 */
	public void initialiseButtons() {
		playPause.setText("Play/Pause");
		restart.setText("Restart");
		quit.setText("Quit");
		controlBox.getChildren().addAll(playPause, restart, quit);
	}
	/**
	 * Method to set up initial state of the simulation
	 * <p>
	 * creates grid, creates initial AliveCells, Initial Draw() of simulation
	 */
	public void initialiseWorldGrid() {
		worldGrid = new Grid();
		for (int i = 0; i<80; i++) {
			initialiseAliveCells(worldGrid);
		}
		simulationPane.getChildren().add(worldGrid.getCellGroup()); 			// calls on HashMap in grid
	}
	
	/*
	 * A method to set up the initial alive cells in a testing configuration  TODO: make this random or on click
	 * 
	 * @param worldGrid - the Grid object the simulation is running
	 */
	public void initialiseAliveCells(Grid worldGrid) {
		AliveCell cell = new AliveCell(generatePosition("x"), generatePosition("y")); 				// TODO: make better way of initializing positions of cells at beginning of simulation (mouse click or random 'seed')
		worldGrid.addCell(cell); 	// adds the cell to the grid
		
	}
	
	/**
	 * Method to update all AliveCells in the Grids Collection
	 * <p>
	 * first will remove all dead cells
	 * second will call update on all remaining cells
	 */
	public void updateGrid() {
		//TODO: everything
		//remove dead cells
		//call update on alive cells
	}
	
	/**
	 * iterates through collection of all alive cells and draws them at their location (by adding them to simulation Pane)
	 * 
	 * @param aliveCells - collection of all alive cells from the Grids 
	 */
	public void drawCells(Group group) {
		
		for (Node c: group.getChildren()) {
			Cell cell = (AliveCell) c;
			//TODO:
			simulationPane.getChildren().add(cell);		// may need to cast
			
		}
		
//		for (Map.Entry<Position, AliveCell> e : aliveCells.entrySet()) {
//			simulationPane.getChildren().add(e.getValue());
//			e.getValue().relocate(e.getKey().getxPos(), e.getKey().getyPos());
//		}
	}
	
	/**
	 * Generates a random Position object for an Alive cell, used for initialising simulation
	 * 
	 * @return - returns a new Position Object
	 */
	public int generatePosition(String pos) {
		//TODO make relative to col/row eg height * row
		int col;
		int row;
		 switch (pos) {
		 case "x" :
			 col = (int)(Math.random()*20);	//gives random column number between 0 and 20
			 return col*Cell.getSize();
		 case "y" :
			 row = (int)(Math.random()*20);	//gives random row number between 0 and 20
			 return row*Cell.getSize();
		 }
		 return 0;
	}
	
	/**
	 * Initializes panes and pane nesting
	 */
	public void initialisePanes() {
		initialiseButtons();
		controlPane.getChildren().add(controlBox);
		
		scrollPane.setContent(simulationPane);
		canvas.setTop(controlPane);
		canvas.setCenter(scrollPane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
