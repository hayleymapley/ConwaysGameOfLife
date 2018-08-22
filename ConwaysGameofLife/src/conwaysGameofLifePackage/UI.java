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

/**
 * This class draws the UI and any cells contained in the Grids HashMap
 * 
 * @author Group3
 *
 */
public class UI extends Application {

	private int startWidth = 600;
	private int startHeight = 400;

	// private Group displayGroup = new Group(); //TODO: add to group?
	private BorderPane canvas = new BorderPane(); // parent pane
	private ScrollPane scrollPane = new ScrollPane();
	private Pane simulationPane = new Pane(); // simulation pane TODO: make infinite
	private Pane controlPane = new Pane(); // control pane (for adding buttons)
	private HBox controlBox = new HBox(); // for containing buttons
	private Grid worldGrid = new Grid();

	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	/**
	 * Main Entry point for the application
	 * <p>
	 * Initializes Panes (nests panes from scene down) Initializes Grid (adds
	 * initial layout of AliveCells) Runs Timeline EventHandlers for buttons
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		initialiseWorldGrid();
		initialisePanes();

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO check survival boolean
				System.out.println("------------------------------");
				worldGrid.addCondemned();
				worldGrid.removeAliveCells();
				worldGrid.updateGrid();
				// update cells - calling on grid
				// draw cells - calling on grid which has hashmap
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
			int i = 0; // to keep track of odd/even number of clicks so we can use as a toggle button

			@Override
			public void handle(ActionEvent event) {
				if (i % 2 == 0) {
					timeline.pause(); // on first press will pause
				} else {
					timeline.play(); // every second press will play
				}
				i++;
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
		simulationPane.getChildren().add(worldGrid.getCellGroup()); // calls on HashMap in grid
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

		scrollPane.setContent(simulationPane);
		canvas.setTop(controlPane);
		canvas.setCenter(scrollPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
