
public class GameState {

	private int state; // represents the state of the game.
	// 0 = Server's Turn
	// 1 = Client's Turn
	// 2 = Awaiting Connection
	// 31 = Setup - Carrier
	// 32 = Setup - BShip
	// 33 = Setup - Cruiser
	// 34 = Setup - Submarine
	// 35 = Setup - Destroyer
	// 4 = Awaiting Other Playing Finishing Placing Ships
	// 5 = End Game State
	
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
