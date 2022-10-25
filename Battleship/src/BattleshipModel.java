// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{
	private Setup begin;
	private GameState state;

	private boolean clientConnected;
	
	private boolean serverReady;
	private boolean clientReady;
	
	BattleshipModel()
	{
		state = new GameState(2); //start game in awaiting connection mode
		begin = new Setup();
		serverReady = false;
		clientReady = false;
		clientConnected = false;
	}
	
	public void runSetup()
	{
		begin.startGame();
	}
	
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
	
}
