package conwaysGameofLifePackage;

import javafx.scene.Node;

public class AliveCell extends Cell {

	/**
	 *  Default constructor
	 */
	public AliveCell() {
		super();
		this.setCellAlive(true);
	}

	/**
	 * Overloaded constructor - sets x and y of cell
	 * @param x - x position
	 * @param y - y position
	 */
	public AliveCell(int x, int y) {
		super(x, y);
		this.setCellAlive(true);
	}
	
	/**
	 * AliveCells update method.
	 * 
	 * Calls the method that decides if a cell survives to the next round.
	 * Calls method that spawns test cells in all empty neighbour spaces around AliveCell
	 * 
	 * @param worldGrid - passes in Grid object
	 */
	public void update(Grid worldGrid) {
		this.willSurvive(worldGrid);
		spawnDeadCells(worldGrid);
	}
	
	/**
	 * Method determines whether an AliveCell has the correct number
	 * of neighbours to survive to the next round. Finds number of 
	 * neighbours a cell has by calling the checkNeighbours method,
	 * then sets appropriate return value of setCellAlive method.
	 * 
	 * @param worldGrid - passes in Grid object
	 */
	public void willSurvive(Grid worldGrid) {
		int neighbours = checkNeighbours(worldGrid);

		if (neighbours == 2 || neighbours == 3) {
			this.setCellAlive(true);
		}
		else {
			this.setCellAlive(false);
		}
	}
	
	/**
	 * Creates a boolean set to true representing each neighbouring position of an AliveCell.
	 * Checks to see if there is already a cell in each position - if so changes boolean to false.
	 * Any positions remaining true will spawn a test cell.
	 * 
	 * @param worldGrid - passes in Grid object
	 */

	public void spawnDeadCells(Grid worldGrid) {

		boolean tl = true;
		boolean tm = true;
		boolean tr = true;
		boolean ml = true;
		boolean mr = true;
		boolean bl = true;
		boolean bm = true;
		boolean br = true;

		for (Node c : worldGrid.getCellGroup().getChildren()) {

			AliveCell cell = (AliveCell) c;
			
			// checks top left
			if (this.getxPos() - getSize() == cell.getxPos() && this.getyPos() - getSize() == cell.getyPos()) {
				tl = false;
			}
			// checks top mid
			if (this.getxPos() == cell.getxPos() && this.getyPos() - getSize() == cell.getyPos()) {
				tm = false;
			}
			// checks top right
			if (this.getxPos() + getSize() == cell.getxPos() && this.getyPos() - getSize() == cell.getyPos()) {
				tr = false;
			}
			// checks mid left
			if (this.getxPos() - getSize() == cell.getxPos() && this.getyPos() == cell.getyPos()) {
				ml = false;
			}
			// checks mid right
			if (this.getxPos() + getSize() == cell.getxPos() && this.getyPos() == cell.getyPos()) {
				mr = false;
			}
			// checks bottom left
			if (this.getxPos() - getSize() == cell.getxPos() && this.getyPos() + getSize() == cell.getyPos()) {
				bl = false;
			}
			// checks bottom mid
			if (this.getxPos() == cell.getxPos() && this.getyPos() + getSize() == cell.getyPos()) {
				bm = false;
			}
			// checks bottom right
			if (this.getxPos() + getSize() == cell.getxPos() && this.getyPos() + getSize() == cell.getyPos()) {
				br = false;
			}
		}
		
		// After initial true/false checks, any positions remaining true will spawn a test cell.
		
		if (tl == true) { // checks top left
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos() - getSize())) {
				TestCell topLeft = new TestCell(this.getxPos() - getSize(), this.getyPos() - getSize());
				worldGrid.getCurrentTestCells().add(topLeft);

				topLeft.update(worldGrid);
			}
		}
		if (tm == true) { // checks top mid
			if (worldGrid.isTestCellEmpty(this.getxPos(), this.getyPos() - getSize())) {
				TestCell topMid = new TestCell(this.getxPos(), this.getyPos() - getSize());
				worldGrid.getCurrentTestCells().add(topMid);

				topMid.update(worldGrid);
			}
		}
		if (tr == true) { // checks top right
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos() - getSize())) {
				TestCell topRight = new TestCell(this.getxPos() + getSize(), this.getyPos() - getSize());
				worldGrid.getCurrentTestCells().add(topRight);

				topRight.update(worldGrid);
			}
		}
		if (ml == true) { // checks mid left
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos())) {
				TestCell midLeft = new TestCell(this.getxPos() - getSize(), this.getyPos());
				worldGrid.getCurrentTestCells().add(midLeft);

				midLeft.update(worldGrid);
			}
		}
		if (mr == true) { // checks mid right
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos())) {
				TestCell midRight = new TestCell(this.getxPos() + getSize(), this.getyPos());
				worldGrid.getCurrentTestCells().add(midRight);
				midRight.update(worldGrid);
			}
		}
		if (bl == true) { // checks bottom left
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos() + getSize())) {
				TestCell bottomLeft = new TestCell(this.getxPos() - getSize(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomLeft);

				bottomLeft.update(worldGrid);
			}
		}
		if (bm == true) { // checks bottom mid
			if (worldGrid.isTestCellEmpty(this.getxPos(), this.getyPos() + getSize())) {
				TestCell bottomMid = new TestCell(this.getxPos(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomMid);

				bottomMid.update(worldGrid);
			}
		}
		if (br == true) { // checks bottom right
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos() + getSize())) {
				TestCell bottomRight = new TestCell(this.getxPos() + getSize(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomRight);

				bottomRight.update(worldGrid);
			}
		}
	}

}
