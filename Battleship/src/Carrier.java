// (Model)

public class Carrier extends Ship {
	
	static Carrier carrier = null;
	
	private Carrier(int l, int x, int y, boolean h)
	{
		super(l,x,y,h);
		name = "Carrier";
	}
	
	public static Carrier getCarrier(int x, int y, boolean h) {
		if (carrier == null) {
			carrier = new Carrier(5, x, y, h);
			return carrier;
		} else {
			return carrier;
		}
	}
	
}
