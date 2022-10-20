// (Model) A data model containing all information used in the game.

public class BattleshipModel 
{

	private Grid ServerGrid;
	private Grid ClientGrid;
	private GameState state;
	
	BattleshipModel()
	{
		ServerGrid = new Grid();
		ClientGrid = new Grid();
		state = new GameState(2); // starts the game in Setup Mode
	}
	
}
