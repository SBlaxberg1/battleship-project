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
		//placeShips(2);
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
				for (int i = 0; i < 5; i++) {
					clientGrid[row][i].setOccupant(clientCarrier);
				}
			} else {
				for (int i = 0; i < 5; i++) {
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
			serverCarrier = Carrier.getCarrier(row, column, isHorizontal);
			
			if (isHorizontal == true) {
				for (int i = column; i < column + 4; i++) {
					serverGrid[row][i].setOccupant(serverCarrier);
				}
			} else {
				for (int i = row; i < row + 4; i++) {
					serverGrid[i][column].setOccupant(serverCarrier);
				}
			}
		} else if (player == 2) {
			clientCarrier = Carrier.getCarrier(row, column, isHorizontal);
			if (isHorizontal == true) {
				for (int i = 0; i < 4; i++) {
					clientGrid[row][i].setOccupant(clientCarrier);
				}
			} else {
				for (int i = 0; i < 4; i++) {
					clientGrid[i][column].setOccupant(clientCarrier);
				}
			}
		}
	}
	
	public void placeCruiser(int player) {
		
	}
	
	public void placeSubmarine(int player) {
		
	}
	
	public void placeDestroyer(int player) {
		
	}
}
