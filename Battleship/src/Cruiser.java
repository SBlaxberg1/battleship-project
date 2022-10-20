// (Model)

public class Cruiser extends Ship {
	
	static Cruiser cruiser = null;
	
	private Cruiser(int l, int x, int y, boolean h)
	{
		super(l,x,y,h);
		name = "Cruiser";
	}
	
	public static Cruiser getCruiser(int x, int y, boolean h) {
		if (cruiser == null) {
			cruiser = new Cruiser(3, x, y, h);
			return cruiser;
		} else {
			return cruiser;
		}
	}
	
}
