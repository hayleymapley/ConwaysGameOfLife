package conwaysGameofLifePackage;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Cell extends Rectangle {

	// Cell fields
	private boolean isCellAlive;
	private final static int size = 7;
	private int xPos, yPos;
	private int neighbourCount;

	/**
	 *  Default constructor
	 */
	public Cell() {
		super(size, size, Color.LIGHTBLUE);
		this.setStrokeType(StrokeType.INSIDE);
		this.setStroke(Color.CORNFLOWERBLUE);
	}
	
	/**
	 * Overloaded Constructor - takes x and y and sets the local fields
	 * @param xPos - the x position
	 * @param yPos - the y position
	 */
	public Cell(int xPos, int yPos) {
		super(size, size, Color.LIGHTBLUE);
		this.setStrokeType(StrokeType.INSIDE);
		this.setStroke(Color.CORNFLOWERBLUE);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Checks for an AliveCell's neighbours at all possible positions, 
	 * increasing count whenever a neighbour is found.
	 * 
	 * @param worldGrid - passes in the Grid object
	 * @return - returns the number of neighbours a cell has
	 */
	public int checkNeighbours(Grid worldGrid) {
		int count = 0; //neighbour count
		
		for(Node c : worldGrid.getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c; //casts cell node

			// checks top left
			if(this.getxPos() - size == cell.getxPos() && this.getyPos() - size == cell.getyPos()) {
				count++;
			}
			// checks top mid
			if(this.getxPos() == cell.getxPos() && this.getyPos() - size == cell.getyPos()) {
				count++;
			}
			// checks top right
			if(this.getxPos() + size == cell.getxPos() && this.getyPos() - size == cell.getyPos()) {
				count++;
			}
			// checks mid left
			if(this.getxPos() - size == cell.getxPos() && this.getyPos() == cell.getyPos()) {
				count++;
			}
			// checks mid right
			if(this.getxPos() + size == cell.getxPos() && this.getyPos() == cell.getyPos()) {
				count++;
			}
			// checks bottom left
			if(this.getxPos() - size == cell.getxPos() && this.getyPos() + size == cell.getyPos()) {
				count++;
			}
			// checks bottom mid
			if(this.getxPos() == cell.getxPos() && this.getyPos() + size == cell.getyPos()) {
				count++;
			}
			// checks bottom right
			if(this.getxPos() + size == cell.getxPos() && this.getyPos() + size == cell.getyPos()) {
				count++;
			}	
		}
		this.neighbourCount = count;
		return neighbourCount;
	}


	// Accessors
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

	public int getNeighbourCount() {
		return neighbourCount;
	}

	public void setNeighbourCount(int neighbourCount) {
		this.neighbourCount = neighbourCount;
	}

}
