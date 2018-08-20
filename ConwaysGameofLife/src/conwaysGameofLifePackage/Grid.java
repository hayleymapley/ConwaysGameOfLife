package conwaysGameofLifePackage;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class Grid {
	private Map<Position, AliveCell> aliveCells = new HashMap<>();
	
	//private Group cellGroup = new Group();

	public Grid() {
		
	}
	
//	public boolean isEmpty(Thing t) {
//		for (Node n : thingGroup.getChildren()) {
//			if (!n.equals(t)) {
//				if (t.getBoundsInParent().intersects(n.getBoundsInParent())) {
//					return false;
//				}
//			}
//
//		}
//		System.out.println("Obstacle are NOT colliding");
//		return true;
//	}

	
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
