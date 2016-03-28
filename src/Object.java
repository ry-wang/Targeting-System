//Abstract class that Turret and Target will inherit from
public abstract class Object {
	
	//Variables that subclasses all will contain
	protected int x;
	protected int y;
	protected int radius;
	protected String colour;
	
	//Methods that Turret and Target will contain
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
	//Constructor that is common to all subclasses
	//In constructor, x,y,radius,and colour are all set
	public Object(int x, int y, int r, String c) {
		this.x = x;
		this.y = y;
		this.radius = r;
		this.colour = c;
	}

}//End of abstract class
