package conwaysGameofLifePackage;

import javafx.scene.paint.Color;

public class AliveCell extends Cell {


	// default constructor
	public AliveCell() {
		super();
		this.setCellAlive(true);
	}

	// overloaded constructor
	public AliveCell(int x, int y) {
		super(x, y);
		this.setCellAlive(true);
	}

	public void update(Grid worldGrid) {
		// check whether cell survives next round
		// generate dead cells in unoccupied neighbours (call spawn deadcells method)
		this.willSurvive(worldGrid);
	}

	public void willSurvive(Grid worldGrid) {

		int neighbours = checkNeighbours(worldGrid);

		if(neighbours == 2 || neighbours == 3) {
			//return true;
			this.setCellAlive(true);
			System.out.println("neighbours " + neighbours);
		}	
		//return false;	
		else {
			this.setCellAlive(false);
		}
	}

	public void spawnDeadCell() { 
		//creates cells if condition from checkNeighBors() are met. 
	}

}
