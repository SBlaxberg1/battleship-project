// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{
	
	private Setup begin;
	private GameState state;
	
	BattleshipModel()
	{
		state = new GameState(2); //start game in setup mode
		begin = new Setup();
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
}
