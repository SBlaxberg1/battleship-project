// (Launcher) Test driver to launch the game.

public class BattleshipGame 
{

	public static void main(String[] args)
	{
		BattleshipModel model = new BattleshipModel();
		BattleshipView view = new BattleshipView(model);
		BattleshipController controller = new BattleshipController(model,view);
		
	}

}
