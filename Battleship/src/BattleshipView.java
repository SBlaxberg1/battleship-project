// (View) Provides the GUI and displays information to the user.

public class BattleshipView 
{
	private GameWindow window;
	private BattleshipModel b_model;
	
	BattleshipView(BattleshipModel model)
	{
		b_model = model;
		window = new GameWindow();
	}
}
