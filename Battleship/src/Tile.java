// (Model) Represents one tile of ocean.

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
	
	public void setOccupant(Ship s)
	{
		occupant = s;
	}
	
}
