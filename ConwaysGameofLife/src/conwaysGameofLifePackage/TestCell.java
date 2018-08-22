package conwaysGameofLifePackage;

import javafx.scene.paint.Color;

public class TestCell extends Cell {

	private boolean isActive;
	
	public TestCell() {
		super();
	}
	
	public TestCell(int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
	}
	
	public void update(Grid worldGrid) { 
	 //checkNeighbors()
		createLivingCell(worldGrid);
		
	}
	
//	public void checkNeighbors() { 
//		//checks number of neighbors. 
//	}
	
	public void createLivingCell(Grid worldGrid) { 
		//draws a live at the position this deadcell is at. if enough neighbours
		if (checkNeighbours(worldGrid) == 3) {
			AliveCell replacementCell = new AliveCell(this.getxPos(), this.getyPos());
			worldGrid.getNewlySpawnedCells().add(replacementCell);
			System.out.println("living cell made at: x" + getxPos() + " y" + getyPos());
		}
	}

}
