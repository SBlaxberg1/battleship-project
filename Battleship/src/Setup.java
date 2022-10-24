import java.util.Scanner;

public class Setup {
	
	private Tile[][] serverGrid;
	private Tile[][] clientGrid;
	private Carrier serverCarrier;
	private Carrier clientCarrier;
	private BShip serverBShip;
	private BShip clientBShip;
	private Cruiser serverCruiser;
	private Cruiser clientCruiser;
	private Submarine serverSubmarine;
	private Submarine clientSubmarine;
	private Destroyer serverDestroyer;
	private Destroyer clientDestroyer;
	
	
	public Setup() {
		serverGrid = new Tile[10][10];
		clientGrid = new Tile[10][10];
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				serverGrid[i][j] = new Tile(i, j);
				clientGrid[i][j] = new Tile(i, j);
			}
		}
		
	}
	
	public void startGame() {
		
		placeCarrier(1);
		placeBShip(1);
		placeCruiser(1);
		placeSubmarine(1);
		placeDestroyer(1);
		
		placeCarrier(2);
		placeBShip(2);
		placeCruiser(2);
		placeSubmarine(2);
		placeDestroyer(2);
	}
	
	public void placeCarrier(int player) {
		Scanner input = new Scanner(System.in);
		boolean invalid;
		int row;
		int column;
		boolean isHorizontal;
		
		do {
		invalid = false;
		System.out.println("Place the Carrier (5 spaces)");
		System.out.print("Beginning coordinate row: ");
		row = input.nextInt();
		System.out.print("Beginning coordinate column: ");
		column = input.nextInt();
		System.out.print("Is the ship horizontal (True or False)? ");
		isHorizontal = input.nextBoolean();
		
		
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
		if (player == 1) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 5; i++) {
					if (serverGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 5; i++) {
					if (serverGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		} else if (player == 2) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 5; i++) {
					if (clientGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 5; i++) {
					if (clientGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		}
		}
		
		} while (invalid == true);
		
		if (player == 1) {
			serverCarrier = Carrier.getCarrier(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 5; i++) {
					serverGrid[row][i].setOccupant(serverCarrier);
				}
			} else {
				for (int i = row; i < row + 5; i++) {
					serverGrid[i][column].setOccupant(serverCarrier);
				}
			}
		} else if (player == 2) {
			clientCarrier = Carrier.getCarrier(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < column + 5; i++) {
					clientGrid[row][i].setOccupant(clientCarrier);
				}
			} else {
				for (int i = 0; i < row + 5; i++) {
					clientGrid[i][column].setOccupant(clientCarrier);
				}
			}
		}
	}
	
	public void placeBShip(int player) {
		Scanner input = new Scanner(System.in);
		boolean invalid;
		int row;
		int column;
		boolean isHorizontal;
		
		do {
		invalid = false;
		System.out.println("Place the Battleship (4 spaces)");
		System.out.print("Beginning coordinate row: ");
		row = input.nextInt();
		System.out.print("Beginning coordinate column: ");
		column = input.nextInt();
		System.out.print("Is the ship horizontal (True or False)? ");
		isHorizontal = input.nextBoolean();
		
		row -= 1;
		column -= 1;
		
		if (isHorizontal == true && column > 7) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 7){
			System.out.println("Invalid");
			invalid = true;
		}
		
		if (player == 1) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 4; i++) {
					if (serverGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 4; i++) {
					if (serverGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		} else if (player == 2) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 4; i++) {
					if (clientGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 4; i++) {
					if (clientGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		}
		
		} while (invalid == true);
		
		if (player == 1) {
			serverBShip = BShip.getBShip(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 4; i++) {
					serverGrid[row][i].setOccupant(serverBShip);
				}
			} else {
				for (int i = row; i < row + 4; i++) {
					serverGrid[i][column].setOccupant(serverBShip);
				}
			}
		} else if (player == 2) {
			clientBShip = BShip.getBShip(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < column + 4; i++) {
					clientGrid[row][i].setOccupant(clientBShip);
				}
			} else {
				for (int i = 0; i < row + 4; i++) {
					clientGrid[i][column].setOccupant(clientBShip);
				}
			}
		}
	}
	
	public void placeCruiser(int player) {
		Scanner input = new Scanner(System.in);
		boolean invalid;
		int row;
		int column;
		boolean isHorizontal;
		
		do {
		invalid = false;
		System.out.println("Place the Cruiser (3 spaces)");
		System.out.print("Beginning coordinate row: ");
		row = input.nextInt();
		System.out.print("Beginning coordinate column: ");
		column = input.nextInt();
		System.out.print("Is the ship horizontal (True or False)? ");
		isHorizontal = input.nextBoolean();
		
		row -= 1;
		column -= 1;
		
		if (isHorizontal == true && column > 8) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 8){
			System.out.println("Invalid");
			invalid = true;
		}
		
		if (player == 1) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					if (serverGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					if (serverGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		} else if (player == 2) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					if (clientGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					if (clientGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		}
		
		} while (invalid == true);
		
		if (player == 1) {
			serverCruiser = Cruiser.getCruiser(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					serverGrid[row][i].setOccupant(serverCruiser);
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					serverGrid[i][column].setOccupant(serverCruiser);
				}
			}
		} else if (player == 2) {
			clientCruiser = Cruiser.getCruiser(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < column + 3; i++) {
					clientGrid[row][i].setOccupant(clientCruiser);
				}
			} else {
				for (int i = 0; i < row + 3; i++) {
					clientGrid[i][column].setOccupant(clientCruiser);
				}
			}
		}
	}
	
	public void placeSubmarine(int player) {
		Scanner input = new Scanner(System.in);
		boolean invalid;
		int row;
		int column;
		boolean isHorizontal;
		
		do {
		invalid = false;
		System.out.println("Place the Submarine (3 spaces)");
		System.out.print("Beginning coordinate row: ");
		row = input.nextInt();
		System.out.print("Beginning coordinate column: ");
		column = input.nextInt();
		System.out.print("Is the ship horizontal (True or False)? ");
		isHorizontal = input.nextBoolean();
		
		row -= 1;
		column -= 1;
		
		if (isHorizontal == true && column > 8) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 8){
			System.out.println("Invalid");
			invalid = true;
		}
		
		if (player == 1) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					if (serverGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					if (serverGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		} else if (player == 2) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					if (clientGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					if (clientGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		}
		
		} while (invalid == true);
		
		if (player == 1) {
			serverSubmarine = Submarine.getSubmarine(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 3; i++) {
					serverGrid[row][i].setOccupant(serverSubmarine);
				}
			} else {
				for (int i = row; i < row + 3; i++) {
					serverGrid[i][column].setOccupant(serverSubmarine);
				}
			}
		} else if (player == 2) {
			clientSubmarine = Submarine.getSubmarine(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < column + 3; i++) {
					clientGrid[row][i].setOccupant(clientSubmarine);
				}
			} else {
				for (int i = 0; i < row + 3; i++) {
					clientGrid[i][column].setOccupant(clientSubmarine);
				}
			}
		}
	}
	
	public void placeDestroyer(int player) {
		Scanner input = new Scanner(System.in);
		boolean invalid;
		int row;
		int column;
		boolean isHorizontal;
		
		do {
		invalid = false;
		System.out.println("Place the Destroyer (2 spaces)");
		System.out.print("Beginning coordinate row: ");
		row = input.nextInt();
		System.out.print("Beginning coordinate column: ");
		column = input.nextInt();
		System.out.print("Is the ship horizontal (True or False)? ");
		isHorizontal = input.nextBoolean();
		
		row -= 1;
		column -= 1;
		
		if (isHorizontal == true && column > 9) {
			System.out.println("Invalid");
			invalid = true;
		} else if (isHorizontal == false && row > 9){
			System.out.println("Invalid");
			invalid = true;
		}
		
		if (player == 1) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 2; i++) {
					if (serverGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 2; i++) {
					if (serverGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		} else if (player == 2) {
			if (isHorizontal == true) {
				for (int i = column; i < column + 2; i++) {
					if (clientGrid[row][i].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			} else {
				for (int i = row; i < row + 2; i++) {
					if (clientGrid[i][column].getOccupant() != null) {
						System.out.println("There is a ship here already");
						invalid = true;
					}
				}
			}
		}
		
		} while (invalid == true);
		
		if (player == 1) {
			serverDestroyer = Destroyer.getDestroyer(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 2; i++) {
					serverGrid[row][i].setOccupant(serverDestroyer);
				}
			} else {
				for (int i = row; i < row + 2; i++) {
					serverGrid[i][column].setOccupant(serverDestroyer);
				}
			}
		} else if (player == 2) {
			clientDestroyer = Destroyer.getDestroyer(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < column + 2; i++) {
					clientGrid[row][i].setOccupant(clientDestroyer);
				}
			} else {
				for (int i = 0; i < row + 2; i++) {
					clientGrid[i][column].setOccupant(clientDestroyer);
				}
			}
		}
	}
}
