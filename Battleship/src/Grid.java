import java.awt.Dimension;
import javax.swing.JPanel;

// Represents a field of tiles.

public class Grid extends JPanel
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
				gameGrid[i][j] = new Tile(i,j,"ocean_empty.png");
				Dimension d = new Dimension(10,10);
				gameGrid[i][j].setSize(d);
				this.add(gameGrid[i][j]);
			}
		}
	}
	
}
