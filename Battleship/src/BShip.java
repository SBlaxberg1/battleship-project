// (Model)

public class BShip extends Ship {
	
	static BShip bship = null;
	
	private BShip(int l, int x, int y, boolean h)
	{
		super(l,x,y,h);
		name = "Battleship";
	}
	
	public static BShip getBShip(int x, int y, boolean h) {
		if (bship == null) {
			bship = new BShip(4, x, y, h);
			return bship;
		} else {
			return bship;
		}
	}
	
}
