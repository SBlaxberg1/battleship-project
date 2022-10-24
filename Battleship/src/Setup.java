import java.util.Scanner;

public class Setup {
	
	private Tile[][] grid;
	private Carrier carrier;
	private Carrier clientCarrier;
	private BShip bShip;
	private BShip clientBShip;
	private Cruiser cruiser;
	private Cruiser clientCruiser;
	private Submarine submarine;
	private Submarine clientSubmarine;
	private Destroyer destroyer;
	private Destroyer clientDestroyer;
	
	
	public Setup() {
		grid = new Tile[10][10];
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				grid[i][j] = new Tile(i, j);
			}
		}
		
	}
	
	public void startGame() {
		
		placeCarrier(1, 1, true);
		placeBShip(2, 2, true);
		placeCruiser(3, 3, true);
		placeSubmarine(4, 4, true);
		placeDestroyer(5, 5, true);
		
	}
	
	public void placeCarrier(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		System.out.println("Place the Carrier (5 spaces)");	
		
		if (isHorizontal == true && column > 6) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 6){
			System.out.println("Invalid");
			invalid = true;
		}
		
		row -= 1;
		column -= 1;
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 5; i++) {
				if (grid[row][i].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 5; i++) {
				if (grid[i][column].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		}
		}
		
		if (invalid != true) {
			carrier = Carrier.getCarrier(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 5; i++) {
					grid[row][i].setOccupant(carrier);
				}
			} else {
				for (int i = row; i < row + 5; i++) {
					grid[i][column].setOccupant(carrier);
				}
			}
		}
	}
	
	public void placeBShip(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		System.out.println("Place the Battleship (4 spaces)");	
		
		if (isHorizontal == true && column > 7) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 7){
			System.out.println("Invalid");
			invalid = true;
		}
		
		row -= 1;
		column -= 1;
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 4; i++) {
				if (grid[row][i].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 4; i++) {
				if (grid[i][column].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		}
		}
		
		if (invalid != true) {
			bShip = BShip.getBShip(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 4; i++) {
					grid[row][i].setOccupant(bShip);
				}
			} else {
				for (int i = row; i < row + 4; i++) {
					grid[i][column].setOccupant(bShip);
				}
			}
		}
	}
	
	public void placeCruiser(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		System.out.println("Place the Cruiser (3 spaces)");	
		
		if (isHorizontal == true && column > 8) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 8){
			System.out.println("Invalid");
			invalid = true;
		}
		
		row -= 1;
		column -= 1;
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 3; i++) {
				if (grid[row][i].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 3; i++) {
				if (grid[i][column].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		}
		}
		
		if (invalid != true) {
			cruiser = Cruiser.getCruiser(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					grid[row][i].setOccupant(cruiser);
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					grid[i][column].setOccupant(cruiser);
				}
			}
		}
	}
	
	public void placeSubmarine(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		System.out.println("Place the Submarine (3 spaces)");	
		
		if (isHorizontal == true && column > 8) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 8){
			System.out.println("Invalid");
			invalid = true;
		}
		
		row -= 1;
		column -= 1;
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 3; i++) {
				if (grid[row][i].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 3; i++) {
				if (grid[i][column].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		}
		}
		
		if (invalid != true) {
			submarine = Submarine.getSubmarine(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					grid[row][i].setOccupant(submarine);
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					grid[i][column].setOccupant(submarine);
				}
			}
		}
	}
	
	public void placeDestroyer(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		System.out.println("Place the Destroyer (2 spaces)");	
		
		if (isHorizontal == true && column > 9) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 9){
			System.out.println("Invalid");
			invalid = true;
		}
		
		row -= 1;
		column -= 1;
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 2; i++) {
				if (grid[row][i].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 2; i++) {
				if (grid[i][column].getOccupant() != null) {
					System.out.println("There is a ship here already");
					invalid = true;
				}
			}
		}
		}
		
		if (invalid != true) {
			destroyer = Destroyer.getDestroyer(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 2; i++) {
					grid[row][i].setOccupant(destroyer);
				}
			} else {
				for (int i = row; i < row + 2; i++) {
					grid[i][column].setOccupant(destroyer);
				}
			}
		}
	}
}
