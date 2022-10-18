import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Represents one tile of ocean.

public class Tile extends JLabel
{
	private int x;
	private int y;
	private ImageIcon icon;
	private boolean isOccupied;
	
	public Tile(int x_cord, int y_cord, String img)
	{
		x = x_cord;
		y = y_cord;
		this.setIcon(new ImageIcon(img));
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setIcon(String img)
	{
		this.setIcon(new ImageIcon(img));
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
