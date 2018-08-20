package conwaysGameofLifePackage;

import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.*;

public class UI extends Application{
	private int startWidth = 600;
	private int startHeight = 400;
	
	private ScrollPane pane = new ScrollPane();


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Game of Life");
		primaryStage.show();
		
	}
	
	public void updateGrid() {
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
