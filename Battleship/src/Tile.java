// Represents one tile of ocean. Note that this is data only, not GUI.

public class Tile
{
	private int x;
	private int y;
	private Ship occupant;
	
	public Tile(int x_cord, int y_cord)
	{
		x = x_cord;
		y = y_cord;
		occupant = null;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Ship getOccupant()
	{
		return occupant;
	}
	
	public void setOccupied(Ship s)
	{
		occupant = s;
	}
	
}
