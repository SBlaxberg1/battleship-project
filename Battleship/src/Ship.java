// Abstract class for ships

public abstract class Ship {

	private int damage; // tracks how many hits the ship has taken
	private boolean isSunk; // tracks if boat is sunk
	private int length;
	private int locX; // tracks X coord of ship's head
	private int locY; // tracks Y coord of ship's head
	private boolean isHorizontal;
	protected String name;
	
	public Ship(int l, int x, int y, boolean h)
	{
		damage = 0;
		isSunk = false;
		length = l;
		locX = x;
		locY = y;
		isHorizontal = h;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getX()
	{
		return locX;
	}
	
	public int getY()
	{
		return locY;
	}
	
	public void setX(int newX)
	{
		locX = newX;
	}
	
	public void setY(int newY)
	{
		locY = newY;
	}
	
	public boolean getIsHorizontal()
	{
		return isHorizontal;
	}

	public void setIsHorizontal(boolean b)
	{
		isHorizontal = b;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean getIsSunk()
	{
		return isSunk;
	}
	
	public void takeHit()
	{
		damage++;
		if (damage == length)
		{
			sink();
		}
	}
	
	public void sink()
	{
		isSunk = true;
	}
	
}
