package conwaysGameofLifePackage;

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

/**
 * This class handles animation and the initialisation of the Grid object.
 * 
 * @author TeamFriendshipNumber3
 *
 */
public class UI extends Application {

	private int startWidth = 600;
	private int startHeight = 400;

	private BorderPane parentPane = new BorderPane();
	private ScrollPane scrollPane = new ScrollPane();  // TODO: make the scene pannable
	private Pane animationPane = new Pane();
	
	private Grid worldGrid = new Grid();

	private Pane controlPane = new Pane(); // controlPane - contains controlBox
	private HBox controlBox = new HBox(); // controlBox - contains buttons
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	/**
	 * Main entry point for the application
	 * <p>
	 * Initializes Pane objects
	 * Initializes Grid object
	 * Initializes Timeline EventHandlers for buttons
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.initialiseWorldGrid();
		this.initialisePanes();

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Adds Cell objects which must die in the next generation to a 'hit' list
				worldGrid.addCondemned();		
				// Removes the Cell objects which are in the 'hit' list from the cellGroup
				worldGrid.removeAliveCells();
				// Updates all objects in Grid object
				worldGrid.updateGrid();
			}
		});

		Scene mainScene = new Scene(parentPane, startWidth, startHeight);
		
		Timeline timeline = new Timeline(frame);
		timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);

		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(mainScene);
		primaryStage.show();

		playPause.setOnAction(new EventHandler<ActionEvent>() {
			int click = 0; 		// Keeps track of odd/even number of clicks so we can use as a rudimentary toggle button
			@Override
			public void handle(ActionEvent event) {
				if (click % 2 == 0) {
					timeline.play(); // on first press will pause
				} else {
					timeline.pause(); // every second press will play
				}
				click++;
			}
		});

		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				worldGrid.getCellGroup().getChildren().clear();
				worldGrid.getCurrentTestCells().clear();
				worldGrid.getNewlySpawnedCells().clear();
				initialiseWorldGrid();

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
		worldGrid.initialiseAliveCells(40);
		animationPane.getChildren().add(worldGrid.getCellGroup()); // calls on HashMap in grid
	}

	public void generateInitialCells(int numOfCells) {
		worldGrid.initialiseAliveCells(numOfCells);
	}

	/**
	 * Initializes panes and pane nesting
	 */
	public void initialisePanes() {
		initialiseButtons();
		controlPane.getChildren().add(controlBox);

		scrollPane.setContent(animationPane);
		parentPane.setTop(controlPane);
		parentPane.setCenter(scrollPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
