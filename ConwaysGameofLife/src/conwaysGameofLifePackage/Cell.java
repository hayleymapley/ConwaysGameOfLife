package conwaysGameofLifePackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	
	//fields
	private boolean isCellAlive;
	private final static int size = 5;
	private int xPos, yPos;
	
	// constructors
	public Cell() {
		super(size, size, Color.BLACK);
		
	}
	
	public Cell(int xPos, int yPos) {
		super(size, size, Color.BLACK);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	
	//methods
	public void update() { 
		
	}

	
	// accessor methods
	public static int getSize() {
		return size;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setCellAlive(boolean isCellAlive) {
		this.isCellAlive = isCellAlive;
	}
	
	public boolean isCellAlive() {
		return isCellAlive;
	}

}
