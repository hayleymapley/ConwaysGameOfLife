package conwaysGameofLifePackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	
	private boolean isCellAlive;
	private final static int size = 5;
	private Position position;
	
	//TODO
	public Cell() {
		super(size, size, Color.BLACK);
		
	}
	
	public void update() { 
		
	}
}
