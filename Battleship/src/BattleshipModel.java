// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{
	private Setup begin;
	private GameState state;

	private boolean clientConnected;
	
	private boolean serverReady;
	private boolean clientReady;
	
	private Tile[][] grid;
	
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
	
	public boolean receiveShot (int x, int y) {
		if (grid[x][y].getOccupant() instanceof Ship) {
			grid[x][y].getOccupant().takeHit();
			return true;
		} else {
			return false;
		}
	}
	
}
