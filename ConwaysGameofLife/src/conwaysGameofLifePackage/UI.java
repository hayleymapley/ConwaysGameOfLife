package conwaysGameofLifePackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;

/**
 * This class handles animation and the initialization of the Grid object.
 * 
 * @author TeamFriendshipNumber3
 *
 */
public class UI extends Application {

	private int startWidth = 650;
	private int startHeight = 400;

	private BorderPane parentPane = new BorderPane();
	private ScrollPane scrollPane = new ScrollPane();
	private Pane animationPane = new Pane();

	private Grid worldGrid = new Grid();

	private GridPane controlPane = new GridPane(); // controlPane - contains controlBox and other elements
	private Text title = new Text();
	
	private VBox countsBox = new VBox();
	private Text generationLabel = new Text();
	private Text generationCount = new Text();
	private Text cellLabel = new Text();
	private Text cellCount = new Text();
	
	private HBox controlButtons = new HBox(); // controlBox - contains buttons
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	private Image imgPlayPause = new Image("/play-pause.png");		//Play/pause button images (has three states)
	private ImageView playPauseView = new ImageView(imgPlayPause);
	private Image imgPlay = new Image("/play.png");
	private ImageView playView = new ImageView(imgPlay);
	private Image imgPause = new Image("/pause.png");
	private ImageView pauseView = new ImageView(imgPause);

	private Image imgQuit = new Image(getClass().getResourceAsStream("exit (1).png")); // Quit button image
	private ImageView quitView = new ImageView(imgQuit);
	private Image imgReset = new Image("/reset3.png");		// Reset button image
	private ImageView restartView = new ImageView(imgReset);
	
	private int count;

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

		KeyFrame frame = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				count++;
				generationCount.setText(Integer.toString(count));
				cellCount.setText(Integer.toString(worldGrid.getCellGroup().getChildren().size()));
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

		//		scrollPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		//			@Override
		//			public void handle(MouseEvent event) {
		//				int newX = 0;
		//				int newY = 0;
		//				AliveCell cell = new AliveCell(newX, newY);
		//				worldGrid.addCell(cell);
		//			}	
		//		});

		playPause.setOnAction(new EventHandler<ActionEvent>() {
			int click = 0; 		// Keeps track of odd/even number of clicks so we can use as a rudimentary toggle button
			@Override
			public void handle(ActionEvent event) {
				if (click % 2 == 0) {
					timeline.play(); 	// Every even press will play
					playPause.setGraphic(pauseView);
				} else {
					timeline.pause(); 	// Every odd press will pause
					playPause.setGraphic(playView);
				}
				click++;
			}
		});

		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Clear all existing Cell objects in Grid
				worldGrid.getCellGroup().getChildren().clear();
				// Clear all TestCell objects in Grid
				worldGrid.getCurrentTestCells().clear();
				// Clear all Cell objects recently conceived
				worldGrid.getNewlySpawnedCells().clear();
				// Create the Grid, adding Cell objects within
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
	 * Sets attributes for buttons and adds them to the controlBox (which sits in the controlPane)
	 */
	public void initialiseButtons() {
		playPause.setText("");
		restart.setText("");
		quit.setText("");
		// Play/pause button
		// Set image widths and heights (play/pause button has three states/images)
		playPauseView.setFitWidth(20);
		playPauseView.setFitHeight(20);
		playView.setFitWidth(20);
		playView.setFitHeight(20);
		pauseView.setFitWidth(20);
		pauseView.setFitHeight(20);
		// Set style and image of play button
		playPause.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000");
		playPause.setGraphic(playPauseView);

		// Quit button
		// Set image width, height
		quitView.setFitWidth(20);
		quitView.setFitHeight(20);
		// Set style and image of quit button
		quit.setStyle("-fx-background-color: #ffffff;-fx-border-color: #000000");
		quit.setGraphic(quitView);

		// Reset button
		restartView.setFitWidth(20);
		restartView.setFitHeight(20);
		// Set style and image of reset button
		restart.setStyle("-fx-background-color: #ffffff;-fx-border-color: #000000");
		restart.setGraphic(restartView);
		
		controlButtons.getChildren().addAll(playPause, restart, quit);
	}

	/**
	 * Sets up initial state of the simulation
	 * <p>
	 * Creates grid and creates initial AliveCells
	 */
	public void initialiseWorldGrid() {
		worldGrid = new Grid();
		worldGrid.initialiseAliveCells(300);
		animationPane.getChildren().add(worldGrid.getCellGroup());
	}

	/**
	 * Initializes panes and pane layout
	 */
	public void initialisePanes() {
		initialiseButtons();
		title.setText("GAME OF LIFE");
		title.setFont(Font.font(30));
		
		generationLabel.setText("\nGENERATION");
		generationLabel.setFont(Font.font(15));
		generationCount.setText("0");
		generationCount.setFont(Font.font(30));
		cellLabel.setText("\nCELLS");
		cellLabel.setFont(Font.font(15));
		cellCount.setText("300");
		cellCount.setFont(Font.font(30));
		countsBox.getChildren().addAll(generationLabel, generationCount, cellLabel, cellCount);
		countsBox.setAlignment(Pos.CENTER);
		
		controlPane.setPadding(new Insets(25, 25, 25, 25));
		controlPane.setVgap(20);
		controlPane.add(title, 0, 0);
		controlPane.add(controlButtons, 0, 1);
		controlPane.add(countsBox, 0, 2);
		
		
		title.setTextAlignment(TextAlignment.CENTER);
		controlButtons.setAlignment(Pos.CENTER);
		generationLabel.setTextAlignment(TextAlignment.CENTER);
		generationCount.setTextAlignment(TextAlignment.CENTER);

		scrollPane.setContent(animationPane);

		parentPane.setLeft(controlPane);
		parentPane.setCenter(scrollPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
