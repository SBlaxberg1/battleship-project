public class Setup {
	
	private Carrier carrier;
	private BShip bShip;
	private Cruiser cruiser;
	private Submarine submarine;
	private Destroyer destroyer;
	private Tile[][] grid;
	
	public Setup(Tile[][] g) {
		grid = g;
	}
	
	public boolean placeCarrier(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		if (isHorizontal == true && column >= 6) {
			invalid = true;
		} else if (isHorizontal == false && row >= 6){
			invalid = true;
		}
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 5; i++) {
				if (grid[row][i].getOccupant() != null) {
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 5; i++) {
				if (grid[i][column].getOccupant() != null) {
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
		return invalid;
	}
	
	public boolean placeBShip(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;	
		
		if (isHorizontal == true && column >= 7) {
			invalid = true;
		} else if (isHorizontal == false && row >= 7){
			invalid = true;
		}
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 4; i++) {
				if (grid[row][i].getOccupant() != null) {
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 4; i++) {
				if (grid[i][column].getOccupant() != null) {
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
		return invalid;
	}
	
	public boolean placeCruiser(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;
		
		if (isHorizontal == true && column >= 8) {
			invalid = true;
		} else if (isHorizontal == false && row >= 8){
			invalid = true;
		}
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 3; i++) {
				if (grid[row][i].getOccupant() != null) {
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 3; i++) {
				if (grid[i][column].getOccupant() != null) {
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
		
		return invalid;
	}
	
	public boolean placeSubmarine(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;	
		
		if (isHorizontal == true && column >= 8) {
			invalid = true;
		} else if (isHorizontal == false && row >= 8){
			invalid = true;
		}
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 3; i++) {
				if (grid[row][i].getOccupant() != null) {
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 3; i++) {
				if (grid[i][column].getOccupant() != null) {
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
		return invalid;
	}
	
	public boolean placeDestroyer(int x, int y, boolean h) {
		boolean invalid = false;
		int row = x;
		int column = y;
		boolean isHorizontal = h;	
		
		if (isHorizontal == true && column >= 9) {
			invalid = true;
		} else if (isHorizontal == false && row >= 9){
			invalid = true;
		}
		
		if (invalid != true) {
		if (isHorizontal == true) {
			for (int i = column; i < column + 2; i++) {
				if (grid[row][i].getOccupant() != null) {
					invalid = true;
				}
			}
		} else {
			for (int i = row; i < row + 2; i++) {
				if (grid[i][column].getOccupant() != null) {
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
		return invalid;
	}
	
}
