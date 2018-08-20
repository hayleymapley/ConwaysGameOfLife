package conwaysGameofLifePackage;

import java.security.acl.Group;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.*;

/**This class draws the UI and any cells contained in the Grids HashMap
 * 
 * @author Group3 
 *
 */
public class UI extends Application{
	
	private int startWidth = 600;
	private int startHeight = 400;
	
	private Group displayGroup = new Group();		//TODO: add to group?
	private BorderPane canvas = new BorderPane();			// parent pane
	private Pane simulationPane = new Pane();				// simulation pane TODO: make infinite
	private Pane controlPane = new Pane();					// control pane (for adding buttons)
	private HBox controlBox = new HBox();					// for containing buttons
	
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	/**
	 * Main Entry point for the application
	 * 
	 * Initializes Panes (nests panes from scene down)
	 * Initializes Grid (adds initial layout of AliveCells)
	 * Runs Timeline
	 * EventHandlers for buttons
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initialisePanes();
		initialiseGrid();
		
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
	
	public void initialiseButtons() {
		playPause.setText("Play/Pause");
		restart.setText("Restart");
		quit.setText("Quit");
		controlBox.getChildren().addAll(playPause, restart, quit);
	}
	
	public void initialiseGrid() {
		Grid worldGrid = new Grid();
		AliveCell cell = new AliveCell();
		worldGrid.addCell(generatePosition(), cell); 	// adds the cell to the grid
		drawCells(worldGrid.getAliveCells()); 			// calls on HashMap in grid
	}
	
	public void updateGrid() {
		//TODO: everything
		//remove dead cells
		//call update on alive cells
	}
	
	public void drawCells(Map<Position, AliveCell> aliveCells) {
		//TODO: everything
		for (Map.Entry<Position, AliveCell> e : aliveCells.entrySet()) {
//			e.getKey();
			simulationPane.getChildren().add(e.getValue());
			e.getValue().relocate(e.getKey().getxPos(), e.getKey().getyPos());
		}
	}
	
	public Position generatePosition() {
		//TODO make realtive to col/row eg height * row
		Position newPosition = new Position(20, 20);
		return newPosition;
	}
	
	public void initialisePanes() {
		initialiseButtons();
		
		controlPane.getChildren().add(controlBox);
		controlBox.setAlignment(Pos.CENTER);
		canvas.setTop(controlPane);
		canvas.setCenter(simulationPane);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
