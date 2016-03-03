import java.awt.Color;
import java.awt.Graphics;


public class Object {
	
	private int x;
	private int y;
	private int r;
	private double distance;
	private String colorType;
	private int number;
	private boolean withinRange;
	
	Object(int xPos, int yPos, int radius, String cType) {
		x = xPos;
		y = yPos;
		r = radius;
		colorType = cType;
		withinRange = false;
	}
	
	public int getX() {
		return x + r/2;
	}
	public int getY() {
		return y + r/2;
	}
	public double getDistance() {
		return distance;
	}
	public String getColor() {
		return colorType;
	}
	public void setRadius(int input) {
		r = input;
	}
	public void setDistance(double input) {
		distance = input;
	}
	public void setNumber(int input) {
		number = input;
	}
	public int getRadius() {
		return r;
	}
	public void setWithinRange(boolean input) {
		withinRange = input;
	}
	public boolean withinRange() {
		return withinRange;
	}
	
	public void paint(Graphics g) {
		if (colorType.equalsIgnoreCase("black")) 
			g.setColor(Color.black);
		if (colorType.equalsIgnoreCase("blue"))
			g.setColor(Color.blue);
		if (colorType.equalsIgnoreCase("green"))
			g.setColor(Color.green);
		if (colorType.equalsIgnoreCase("red"))
			g.setColor(Color.red);
		if (colorType.equalsIgnoreCase("yellow"))
			g.setColor(Color.yellow);
		
		g.fillOval(x, y, r, r);
		
		if ((colorType.equalsIgnoreCase("black")) ||(colorType.equalsIgnoreCase("blue"))) {
			g.setColor(Color.white);
		}
		else {
			g.setColor(Color.black);
		}
		
		//Display Number of object
		g.drawString(String.valueOf(number), x + r/2, y + r/2);
	}

}
