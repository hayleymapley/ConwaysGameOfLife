package conwaysGameofLifePackage;

import javafx.scene.Node;
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
		System.out.println("called update method of AliveCell");
		// check whether cell survives next round
		this.willSurvive(worldGrid);
		// generate dead cells in unoccupied neighbours (call spawn deadcells method)
		spawnDeadCells(worldGrid);

	}

	public void willSurvive(Grid worldGrid) {
		System.out.println("called willSurve");
		int neighbours = checkNeighbours(worldGrid);

		if (neighbours == 2 || neighbours == 3) {
			// return true;
			this.setCellAlive(true);
			System.out.println("neighbours " + neighbours);
		}
		// return false;
		else {
			this.setCellAlive(false);
		}
	}

	public void spawnDeadCells(Grid worldGrid) {
		// creates cells if condition from checkNeighBors() are met.
		System.out.println("spawnDeadCells called, testing cell at x:" + getxPos() + " y:" + getyPos());

		boolean tl = true; // true means no occupation so can draw
		boolean tm = true;
		boolean tr = true;
		boolean ml = true;
		boolean mr = true;
		boolean bl = true;
		boolean bm = true;
		boolean br = true;

		for (Node c : worldGrid.getCellGroup().getChildren()) {

			AliveCell cell = (AliveCell) c;

			// If current check has same position as position checked then return false (not
			// a valid place to spawn
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
		// Now check if each position, is valid for spawning (ie not occupied)
		if (tl == true) { // can draw alive cell here
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos() - getSize())) {
				TestCell topLeft = new TestCell(this.getxPos() - getSize(), this.getyPos() - getSize()); // new dead
				// cell at
				// coordinates
				// checked
				worldGrid.getCurrentTestCells().add(topLeft);
				System.out.println("created cell at top left");

				topLeft.update(worldGrid);
			}
		}
		if (tm == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos(), this.getyPos() - getSize())) {
				TestCell topMid = new TestCell(this.getxPos(), this.getyPos() - getSize());
				worldGrid.getCurrentTestCells().add(topMid);
				System.out.println("created cell at top mid");

				topMid.update(worldGrid);
			}
		}
		if (tr == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos() - getSize())) {
				TestCell topRight = new TestCell(this.getxPos() + getSize(), this.getyPos() - getSize());
				worldGrid.getCurrentTestCells().add(topRight);
				System.out.println("created cell at top right");

				topRight.update(worldGrid);
			}
		}
		if (ml == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos())) {
				TestCell midLeft = new TestCell(this.getxPos() - getSize(), this.getyPos());
				worldGrid.getCurrentTestCells().add(midLeft);
				System.out.println("created cell at mid left");

				midLeft.update(worldGrid);
			}

		}
		if (mr == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos())) {
				TestCell midRight = new TestCell(this.getxPos() + getSize(), this.getyPos());
				worldGrid.getCurrentTestCells().add(midRight);
				System.out.println("created cell at mid right");
				midRight.update(worldGrid);
			}

		}
		if (bl == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos() - getSize(), this.getyPos() + getSize())) {
				TestCell bottomLeft = new TestCell(this.getxPos() - getSize(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomLeft);
				System.out.println("created cell at bottom left");

				bottomLeft.update(worldGrid);
			}

		}
		if (bm == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos(), this.getyPos() + getSize())) {
				TestCell bottomMid = new TestCell(this.getxPos(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomMid);
				System.out.println("created cell at bottom mid");

				bottomMid.update(worldGrid);
			}

		}
		if (br == true) {
			if (worldGrid.isTestCellEmpty(this.getxPos() + getSize(), this.getyPos() + getSize())) {
				TestCell bottomRight = new TestCell(this.getxPos() + getSize(), this.getyPos() + getSize());
				worldGrid.getCurrentTestCells().add(bottomRight);
				System.out.println("created cell at bottom right");

				bottomRight.update(worldGrid);
			}
		}

	}

}
