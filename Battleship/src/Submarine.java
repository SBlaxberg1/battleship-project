// (Model)

public class Submarine extends Ship {
	
	static Submarine submarine = null;
	
	private Submarine(int l, int x, int y, boolean h)
	{
		super(l,x,y,h);
		name = "Submarine";
	}
	
	public static Submarine getSubmarine(int x, int y, boolean h) {
		if (submarine == null) {
			submarine = new Submarine(3, x, y, h);
			return submarine;
		} else {
			return submarine;
		}
	}
	
}
