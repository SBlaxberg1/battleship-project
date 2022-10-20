// (Model) 

public class Destroyer extends Ship {
	
	static Destroyer destroyer = null;
	
	private Destroyer(int l, int x, int y, boolean h)
	{
		super(l,x,y,h);
		name = "Submarine";
	}
	
	public static Destroyer getDestroyer(int x, int y, boolean h) {
		if (destroyer == null) {
			destroyer = new Destroyer(2, x, y, h);
			return destroyer;
		} else {
			return destroyer;
		}
	}
	
}
