package conwaysGameofLifePackage;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;

public class Grid {

	// Grid fields
	private Group cellGroup = new Group();								// parent group that holds all cell objects
	private ArrayList<AliveCell> newlySpawnedCells = new ArrayList<>(); // holds test cells that have become alive that generation
	private ArrayList<TestCell> currentTestCells = new ArrayList<>(); // holds temporary cells in an AliveCell's neighbourhood
	private ArrayList<AliveCell> condemned = new ArrayList<>(); // holds alive cells that will die next turn (returned false for isAlive)

	// default Grid constructor
	public Grid() {

	}

	/**
	 * Checks the provided x and y positions against all AliveCells in cellGroup to
	 * establish if these are already used by another cell
	 * 
	 * @param x - potential x position
	 * 
	 * @param y - potential y position
	 *            
	 * @return - true if not already occupied or false if occupied
	 * 
	 */
	public boolean isCurrentPositionValid(int x, int y) { // takes an x and y position as parameters
		for (Node c : this.getCellGroup().getChildren()) { 
			if (((AliveCell) c).getxPos() == x && ((AliveCell) c).getyPos() == y) { // if cell c's x&y are equal to arguments x&y
				return false;														// won't place another cell in that location
			}
		}
		return true;
	}

	/**
	 * removes any AliveCells in the condemned List from the main collection
	 * (cellGroup)
	 */
	public void removeAliveCells() {
		for (AliveCell c : getCondemned()) {
			getCellGroup().getChildren().remove(c);
		}
	}

	/*
	 * A method to set up the initial alive cells in a testing configuration
	 * 
	 * @param numOfCells - the initial number of cells we want randomly placed in the world
	 */
	public void initialiseAliveCells(int numOfCells) {
		for (int i = 0; i < numOfCells; i++) {
			int potnX = generatePosition("x");
			int potnY = generatePosition("y");
			// calls method checking if cell can be placed at position
			while (!(isCurrentPositionValid(potnX, potnY))) { 
				potnX = generatePosition("x");
				potnY = generatePosition("y");
			}
			AliveCell cell = new AliveCell(potnX, potnY);
			addCell(cell); // adds the cell to the grid
		}
	}

	/**
	 * Adds any cell with more than 3 or less than 2 neighbours into condemned ArrayList.
	 * Resets this list first.
	 */
	public void addCondemned() {
		condemned = new ArrayList<>();
		for (Node c : getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			if (!cell.isCellAlive()) {
				condemned.add((AliveCell) c);
			}
		}
	}

	/**
	 * Method to update all AliveCells in the Grids Collection
	 * <p>
	 * first will remove all dead cells second will call update on all remaining
	 * cells
	 */
	public void updateGrid() {
		for (AliveCell c : getNewlySpawnedCells()) {
			addCell(c);
		}
		// clears both ArrayLists for use with next update
		getNewlySpawnedCells().clear();
		getCurrentTestCells().clear();

		for (Node c : getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			cell.update(this);
		}
	}

	/**
	 * Generates a random x & y coordinate for an Alive cell, 
	 * used for initializing simulation
	 * 
	 * @return - returns either (column * cell size) for x, 
	 * 			or (row * cell size) for y.
	 * 			If parameter passes neither "x" nor "y", returns 0.
	 */
	public int generatePosition(String pos) {
		int col;
		int row;
		switch (pos) {
		case "x":
			col = (int) (Math.random() * 30 + 15); // gives random column number between 10 and 40
			return col * Cell.getSize();
		case "y":
			row = (int) (Math.random() * 30 + 10); // gives random row number between 10 and 40
			return row * Cell.getSize();
		}
		return 0;
	}

	/**
	 * Adds AliveCell to cellGroup and relocates it to its internal x and y positions
	 * 
	 * @param cell  - the cell to add to the cellGroup's collection
	 */
	public void addCell(AliveCell cell) {
		cellGroup.getChildren().add(cell);
		cell.relocate(cell.getxPos(), cell.getyPos());
	}
	
	/**
	 * Tests to see if the space a TestCell would occupy is free
	 * 
	 * @param x - x coordinate of cell
	 * @param y - y coordinate of cell
	 * @return - returns true only if space at given x&y coordinate is empty
	 */
	public boolean isTestCellEmpty(int x, int y) {
		for (TestCell c : currentTestCells) {
			if (x == c.getxPos() && y == c.getyPos())
				return false;
		}
		return true;
	}

	// accessor methods

	public Group getCellGroup() {
		return cellGroup;
	}

	public void setCellGroup(Group cellGroup) {
		this.cellGroup = cellGroup;
	}

	public ArrayList<AliveCell> getNewlySpawnedCells() {
		return newlySpawnedCells;
	}

	public ArrayList<TestCell> getCurrentTestCells() {
		return currentTestCells;
	}

	public ArrayList<AliveCell> getCondemned() {
		return condemned;
	}

	public void setCondemned(ArrayList<AliveCell> condemned) {
		this.condemned = condemned;
	}
}
