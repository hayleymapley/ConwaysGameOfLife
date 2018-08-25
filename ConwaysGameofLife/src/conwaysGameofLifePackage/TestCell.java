package conwaysGameofLifePackage;

public class TestCell extends Cell {
	
	/**
	 * Default constructor
	 */
	public TestCell() {
		super();
	}
	
	/**
	 * Overloaded constructor - sets x and y positions of cell
	 * @param x - the x position
	 * @param y - the y position
	 */
	public TestCell(int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
	}
	
	/**
	 * Update method for test cells. 
	 * Calls method that creates new alive cells from test cells
	 * 
	 * @param worldGrid - passes in grid object
	 */
	public void update(Grid worldGrid) { 
		createLivingCell(worldGrid);
		
	}
	
	/**
	 * spawns an alive cell at the position this deadcell is at, if it has exactly 3 neighbours
	 * 
	 * @param worldGrid - passes in the grid object
	 */
	public void createLivingCell(Grid worldGrid) { 
		if (checkNeighbours(worldGrid) == 3) {
			AliveCell replacementCell = new AliveCell(this.getxPos(), this.getyPos());
			worldGrid.getNewlySpawnedCells().add(replacementCell);
		}
	}

}
