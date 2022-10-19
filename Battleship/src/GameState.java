
public class GameState {

	private int state; // represents the state of the game.
	// 0 = Server
	// 1 = Client
	// 2 = Setup
	
	public GameState(int s)
	{
		state = s;
	}
	
	public int getState()
	{
		return state;
	}
	
	public void setState(int s)
	{
		state = s;
	}
}
