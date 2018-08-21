package conwaysGameofLifePackage;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private ArrayList<AliveCell> condemned = new ArrayList<>();	//holds alive cells that will die next turn (returned false for isAlive)
	
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
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//TODO check survival boolean
				addCondemned();
				removeAliveCells();
				updateGrid();
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
//				simulationPane.getChildren().removeAll(worldGrid.getCellGroup().getChildren());
//				initialiseWorldGrid();
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
		for (int i = 0; i<40; i++) {
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
		int potnX = generatePosition("x");
		int potnY = generatePosition("y");
		//call checkmethod
		while (!(worldGrid.isCurrentPositionValid(potnX, potnY))) {
			potnX = generatePosition("x");
			potnY = generatePosition("y");
		}
		AliveCell cell = new AliveCell(potnX ,potnY); 				// TODO: make better way of initializing positions of cells at beginning of simulation (mouse click or random 'seed')
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
		for(Node c : worldGrid.getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			cell.update(worldGrid);
		}
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
			 col = (int)(Math.random()*10);	//gives random column number between 0 and 20
			 return col*Cell.getSize();
		 case "y" :
			 row = (int)(Math.random()*10);	//gives random row number between 0 and 20
			 return row*Cell.getSize();
		 }
		 return 0;
	}
	
	/**
	 * removes any AliveCells in the condemned List from the main collection (cellGroup)
	 */
	public void removeAliveCells() {
		for(AliveCell c : condemned) {
			worldGrid.getCellGroup().getChildren().remove(c);
		}
	}
	
	/**
	 * adds any cell with more than 3 or less than 2 neighbours into condemned ArrayList
	 * resets this list first.
	 */
	public void addCondemned() {
		condemned = new ArrayList<>();
		for(Node c : worldGrid.getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			if(!cell.isCellAlive()) {
				System.out.println(cell.getxPos() + " " + cell.getyPos() + " Neighbour count = " + cell.getNeighbourCount());
				condemned.add((AliveCell) c);
			}
		}
		System.out.println("Condemned size =" + condemned.size());
	}
	
//	public void removeAliveCells() {
//		int size = worldGrid.getCellGroup().getChildren().size();
//		for(int i = 0; i < size; i++) {
//			AliveCell cell = (AliveCell)worldGrid.getCellGroup().getChildren().get(i);
//			if(!cell.isCellAlive()) {
//				worldGrid.getCellGroup().getChildren().remove(i);
//				size --;
//			}
//		}
//	}
	
//	public boolean isCurrentPositionValid(int x, int y) {
//		for (Node c: worldGrid.getCellGroup().getChildren()) {
//			if (((AliveCell) c).getxPos() == x && ((AliveCell) c).getyPos() == y) {
//				return false;
//			}
//		}
//		return true;
//	}
	
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
