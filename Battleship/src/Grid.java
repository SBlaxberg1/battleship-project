// Represents a field of tiles. Note that this is data only, not GUI.

public class Grid
{
	private final int SIZE = 10;
	
	public Tile[][] gameGrid;
	
	public Grid()
	{
		// Setup game grid
		gameGrid = new Tile[SIZE][SIZE];
		
		for (int i=0; i<SIZE; i++)
		{
			for (int j=0; j<SIZE; j++)
			{
				gameGrid[i][j] = new Tile(i,j);		
			}
		}
	}
	
}
