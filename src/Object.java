
public abstract class Object {
	
	protected int x;
	protected int y;
	protected int radius;
	protected String colour;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRadius() {
		return radius;
	}
	public void setColour(String input) {
		colour = input;
	}
	public void setX(int input) {
		x = input;
	}
	public void setY(int input) {
		y = input;
	}
	
	//final public void test(){}
	
	public Object(int x, int y, int r, String c) {
		this.x = x;
		this.y = y;
		this.radius = r;
		this.colour = c;
	}

}
