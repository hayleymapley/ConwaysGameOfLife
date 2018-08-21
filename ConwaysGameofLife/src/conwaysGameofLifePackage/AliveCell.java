package conwaysGameofLifePackage;

public class AliveCell extends Cell {
	
	
	// default constructor
	public AliveCell() {
		super();
	}
	
	// overloaded constructor
	public AliveCell(int x, int y) {
		super(x, y);
	}
	
	public void update() {
		// check whether cell survives next round
		// generate dead cells in unoccupied neighbours (call spawn deadcells method)
		
		
	}
	
	public void spawnDeadCell() { 
		//creates cells if condition from checkNeighBors() are met. 
	}

}
