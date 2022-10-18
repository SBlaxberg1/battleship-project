// Represents one tile of ocean. Note that this is data only, not GUI.

public class Tile
{
	private int x;
	private int y;
	private boolean isOccupied;
	
	public Tile(int x_cord, int y_cord)
	{
		x = x_cord;
		y = y_cord;
		isOccupied = false;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean getOccupied()
	{
		return isOccupied;
	}
	
	public void setOccupied(boolean b)
	{
		isOccupied = b;
	}
	
}
