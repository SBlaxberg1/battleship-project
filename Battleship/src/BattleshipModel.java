// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{
	private Setup begin;
	private GameState state;

	private boolean clientConnected;
	
	private boolean serverReady;
	private boolean clientReady;
	
	private Tile[][] grid;
	private int numSunk = 0;
	
	BattleshipModel()
	{
		state = new GameState(2); //start game in awaiting connection mode
		serverReady = false;
		clientReady = false;
		clientConnected = false;
		
		grid = new Tile[10][10];
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				grid[i][j] = new Tile(i, j);
			}
		}
		begin = new Setup(grid);
	}
	
	/*
	public void runSetup()
	{
		begin.startGame();
	}
	*/
	
	public int getGameState()
	{
		return state.getState();
	}
	
	public void setGameState(int s)
	{
		state.setState(s);
	}
	
	public boolean getClientConnected()
	{
		return clientConnected;
	}
	
	public void setClientConnected(boolean b)
	{
		clientConnected = b;
	}
	
	public boolean getServerReady()
	{
		return serverReady;
	}
	
	public boolean getClientReady()
	{
		return clientReady;
	}
	
	public void setServerReady(boolean b)
	{
		serverReady = b;
	}
	
	public void setClientReady(boolean b)
	{
		clientReady = b;
	}
	
	public Setup getSetup()
	{
		return begin;
	}
	
	public String receiveShot (int x, int y) {
		if (grid[x][y].getOccupant() instanceof Ship) {
			grid[x][y].getOccupant().takeHit();
			if (grid[x][y].getOccupant().getIsSunk()) {
				numSunk++;
				if (numSunk == 5)
					return "You sunk their last ship and won the game!";
				if (grid[x][y].getOccupant() instanceof Carrier)
					return "You sunk their carrier!";
				if (grid[x][y].getOccupant() instanceof BShip)
					return "You sunk their battleship!";
				if (grid[x][y].getOccupant() instanceof Cruiser)
					return "You sunk their cruiser!";
				if (grid[x][y].getOccupant() instanceof Submarine)
					return "You sunk their submarine!";
				if (grid[x][y].getOccupant() instanceof Destroyer)
					return "You sunk their destroyer!";
			}
			return "hit";
		} else {
			return "miss";
		}
	}
}
