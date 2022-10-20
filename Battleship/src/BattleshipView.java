// (View) Provides the GUI and displays information to the user.

public class BattleshipView 
{
	private NetworkWindow netWindow;
	private GameWindow gameWindow;
	private BattleshipModel b_model;
	
	BattleshipView(BattleshipModel model)
	{
		b_model = model;
		netWindow = new NetworkWindow();
		gameWindow = new GameWindow();
	}
}
