// A data model containing all information used in the game.

public class BattleshipModel 
{

	private Grid ServerGrid;
	private Grid ClientGrid;
	
	BattleshipModel()
	{
		ServerGrid = new Grid();
		ClientGrid = new Grid();
	}
	
}
