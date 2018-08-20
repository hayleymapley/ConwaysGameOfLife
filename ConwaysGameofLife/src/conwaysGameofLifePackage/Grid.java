package conwaysGameofLifePackage;

import java.util.HashMap;
import java.util.Map;

public class Grid {
	private Map<Position, AliveCell> aliveCells = new HashMap<>();

	public Grid() {
		
	}
	
	public void updateAliveCells() {
		//get live cells and update each cell object. 
	}

	public void drawCells() {
		// draws cells into grid.
		//includes color black/white
	}
	
	public void addCell(Position position, AliveCell cell) {
		aliveCells.put(position, cell);
	}
	
	public Map<Position, AliveCell> getAliveCells() {
		return aliveCells;
	}

//	public void setAliveCells(Map<Position, AliveCell> aliveCells) {
//		this.aliveCells = aliveCells;
//	}
}
