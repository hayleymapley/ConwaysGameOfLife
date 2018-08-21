package conwaysGameofLifePackage;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class Grid {
//	private Map<Position, AliveCell> aliveCells = new HashMap<>();
	
	private Group cellGroup = new Group();

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

//	public void updateAliveCells() {
//		//get live cells and update each cell object. 
//		
//	}
	
	/**
	 * Checks the provided x and y positions against all AliveCells in cellGroup to establish if these are already used
	 * 
	 * @param x - potential x position
	 * @param y - potential y position
	 * @return - true if not already occupied or false if occupied
	 */
	public boolean isCurrentPositionValid(int x, int y) {
		for (Node c: this.getCellGroup().getChildren()) {
			if (((AliveCell) c).getxPos() == x && ((AliveCell) c).getyPos() == y) {
				return false;
			}
		}
		return true;
	}

//	public void drawCells() {
//		// draws cells into grid.
//		//includes color black/white
//	}
	
	/**
	 * Adds AliveCell to cellGroup and relocates it ti its internal x and y positions
	 * @param cell - the cell to add to the cellGroup's collection
	 */
	public void addCell(AliveCell cell) {
		cellGroup.getChildren().add(cell);
		cell.relocate(cell.getxPos(), cell.getyPos());
	}
	
//	public Map<Position, AliveCell> getAliveCells() {
//		return aliveCells;
//	}

	
	public Group getCellGroup() {
		return cellGroup;
	}

	public void setCellGroup(Group cellGroup) {
		this.cellGroup = cellGroup;
	}

//	public void setAliveCells(Map<Position, AliveCell> aliveCells) {
//		this.aliveCells = aliveCells;
//	}
}
