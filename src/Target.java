import java.awt.Color;
import java.awt.Graphics;

//Target class, extends the abstract class Object

public class Target extends Object {
	
	//Fields of Target class
	private int priority;
	private double distance;
	private int number;
	private boolean withinRange;
	
	//Constructor for Target class
	Target(int xPos, int yPos, int r, String cType) {
		//Calls constructor for superclass, which is Object
		super(xPos, yPos, r, cType);
		//Other Target-specific variables are set
		this.withinRange = false;
		this.priority = (int) (Math.random()*5) + 1;
	}
	
	//Overloaded methods
	public int getX() {
		return x + radius/2;
	}
	public int getY() {
		return y + radius/2;
	}
	//Methods unique to this class
	public double getDistance() {
		return distance;
	}
	public void setRadius(int input) {
		radius = input;
	}
	public void setDistance(double input) {
		distance = input;
	}
	public void setNumber(int input) {
		number = input;
	}
	public int getTargetNumber() {
		return number;
	}
	public int getPriority() {
		return priority;
	}
	public void setWithinRange(boolean input) {
		withinRange = input;
	}
	public boolean withinRange() {
		return withinRange;
	}
	//Paint class, where the object is painted
	public void paint(Graphics g) {
		if (colour.equalsIgnoreCase("black")) 
			g.setColor(Color.black);
		if (colour.equalsIgnoreCase("blue"))
			g.setColor(Color.blue);
		if (colour.equalsIgnoreCase("green"))
			g.setColor(Color.green);
		if (colour.equalsIgnoreCase("red"))
			g.setColor(Color.red);
		if (colour.equalsIgnoreCase("yellow"))
			g.setColor(Color.yellow);
		
		g.fillOval(x, y, radius, radius);
		
		if ((colour.equalsIgnoreCase("black")) ||(colour.equalsIgnoreCase("blue"))) {
			g.setColor(Color.white);
		}
		else {
			g.setColor(Color.black);
		}
		
		//Display Number of object
		g.drawString(String.valueOf(number), x + radius/2, y + radius/2);
	}

}//End of Target class
