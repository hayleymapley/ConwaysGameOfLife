package conwaysGameofLifePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class Grid {

	private Group cellGroup = new Group();
	private ArrayList<AliveCell> newlySpawnedCells = new ArrayList<>();
	private ArrayList<TestCell> currentTestCells = new ArrayList<>();
	private ArrayList<AliveCell> condemned = new ArrayList<>(); // holds alive cells that will die next turn (returned
	// false for isAlive)

	public Grid() {

	}

	/**
	 * Checks the provided x and y positions against all AliveCells in cellGroup to
	 * establish if these are already used
	 * 
	 * @param x
	 *            - potential x position
	 * @param y
	 *            - potential y position
	 * @return - true if not already occupied or false if occupied
	 */
	public boolean isCurrentPositionValid(int x, int y) {
		for (Node c : this.getCellGroup().getChildren()) {
			if (((AliveCell) c).getxPos() == x && ((AliveCell) c).getyPos() == y) {
				return false;
			}
		}
		return true;
	}

	/**
	 * removes any AliveCells in the condemned List from the main collection
	 * (cellGroup)
	 */
	public void removeAliveCells() {
		System.out.println("called remove alive cells");
		for (AliveCell c : getCondemned()) {
			getCellGroup().getChildren().remove(c);
		}
	}

	/*
	 * A method to set up the initial alive cells in a testing configuration TODO:
	 * make this random or on click
	 * 
	 * @param worldGrid - the Grid object the simulation is running
	 */
	public void initialiseAliveCells(int numOfCells) {

		for (int i = 0; i < numOfCells; i++) {
			int potnX = generatePosition("x");
			int potnY = generatePosition("y");
			// call checkmethod
			while (!(isCurrentPositionValid(potnX, potnY))) {
				potnX = generatePosition("x");
				potnY = generatePosition("y");
			}
			AliveCell cell = new AliveCell(potnX, potnY); // TODO: make better way of initializing positions of cells at
			// beginning of simulation (mouse click or random 'seed')
			addCell(cell); // adds the cell to the grid
		}

	}

	/**
	 * adds any cell with more than 3 or less than 2 neighbours into condemned
	 * ArrayList resets this list first.
	 */
	public void addCondemned() {
		System.out.println("called addCondemned");
		condemned = new ArrayList<>();
		for (Node c : getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			if (!cell.isCellAlive()) {
				// System.out.println(cell.getxPos() + " " + cell.getyPos() + " Neighbour count
				// = " + cell.getNeighbourCount());
				condemned.add((AliveCell) c);
			}
		}
		// System.out.println("Condemned size =" + condemned.size());
	}

	/**
	 * Method to update all AliveCells in the Grids Collection
	 * <p>
	 * first will remove all dead cells second will call update on all remaining
	 * cells
	 */
	public void updateGrid() {
		// TODO: everything
		// remove dead cells
		// call update on alive cells
		System.out.println("called updategrid");
		for (AliveCell c : getNewlySpawnedCells()) {
			addCell(c);
		}
		getNewlySpawnedCells().clear(); // now clear for use with next update
		System.out.println(getNewlySpawnedCells() + " this should be empty");
		getCurrentTestCells().clear();
		System.out.println(getCurrentTestCells() + " this should be empty");

		for (Node c : getCellGroup().getChildren()) {
			AliveCell cell = (AliveCell) c;
			cell.update(this);
			System.out.println("update cell called");
		}
		// add newly spawned AliveCells to group

	}

	/**
	 * Generates a random Position object for an Alive cell, used for initialising
	 * simulation
	 * 
	 * @return - returns a new Position Object
	 */
	public int generatePosition(String pos) {
		// TODO make relative to col/row eg height * row
		int col;
		int row;
		switch (pos) {
		case "x":
			col = (int) (Math.random() * 10 + 40); // gives random column number between 0 and 20
			return col * Cell.getSize();
		case "y":
			row = (int) (Math.random() * 10 + 40); // gives random row number between 0 and 20
			return row * Cell.getSize();
		}
		return 0;
	}

	/**
	 * Adds AliveCell to cellGroup and relocates it ti its internal x and y
	 * positions
	 * 
	 * @param cell
	 *            - the cell to add to the cellGroup's collection
	 */
	public void addCell(AliveCell cell) {
		System.out.println("called addcell");
		cellGroup.getChildren().add(cell);
		cell.relocate(cell.getxPos(), cell.getyPos());
	}

	public boolean isTestCellEmpty(int x, int y) {
		for (TestCell c : currentTestCells) {
			if (x == c.getxPos() && y == c.getyPos())
				return false;
		}
		return true;
	}

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
