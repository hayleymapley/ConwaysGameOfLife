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
}
