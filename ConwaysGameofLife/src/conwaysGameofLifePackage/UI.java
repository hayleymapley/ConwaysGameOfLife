package conwaysGameofLifePackage;

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
import javafx.stage.*;

public class UI extends Application{
	
	private int startWidth = 600;
	private int startHeight = 400;
	
	private BorderPane canvas = new BorderPane();	// parent pane
	private ScrollPane pane = new ScrollPane();		// simulation pane
	private Pane controlPane = new Pane();			// control pane (for adding buttons)
	private HBox controlBox = new HBox();			// for containing buttons
	
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button quit = new Button();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initialisePanes();
		//initialise grid
		
		//create cells
		//add cells to map
		Cell newCell = new Cell();	
		
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
	}
	
	public void updateGrid() {
		//TODO: everything
		//remove dead cells
		//call update on alive cells
	}
	
	public void drawCells() {
		//TODO: everything
		//takes hashMap parameter
		//draws rect at position
	}
	
	public void initialisePanes() {
		initialiseButtons();
		controlBox.getChildren().addAll(playPause, restart, quit);
		controlPane.getChildren().add(controlBox);
		controlBox.setAlignment(Pos.CENTER);
		canvas.setTop(controlPane);
		canvas.setCenter(pane);
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
