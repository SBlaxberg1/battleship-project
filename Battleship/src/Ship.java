// Abstract class for ships

public abstract class Ship {

	private int damage; // tracks how many hits the ship has taken
	private boolean sunk; // tracks if boat is sunk
	private int length;
	private int locX; // tracks X coord of ship's head
	private int locY; // tracks Y coord of ship's head
	private boolean isHorizontal;
	
	public Ship(int l, int x, int y, boolean h)
	{
		damage = 0;
		sunk = false;
		length = l;
		locX = x;
		locY = y;
		isHorizontal = h;
	}
	
	public boolean isSunk()
	{
		if (damage == length)
		{
			sunk = true;
			return true;
		}
		return false;
	}
	
	public void takeHit()
	{
		damage++;
	}
	
}
