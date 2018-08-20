package conwaysGameofLifePackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.stage.*;

public class UI extends Application{
	
	private int startWidth = 600;
	private int startHeight = 400;
	
	private BorderPane canvas = new BorderPane();
	private ScrollPane pane = new ScrollPane();
	
	private Button playPause = new Button();
	private Button restart = new Button();
	private Button clear = new Button();
	private Button quit = new Button();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//initialise grid
		//create cells
		//add cells to map
	
		
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
		
		primaryStage.show();
	}
	
	public void initialiseButtons() {
		//TODO
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
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
