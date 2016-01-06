import java.awt.Color;
import java.awt.Graphics;


public class Object {
	
	private int x;
	private int y;
	private int r;
	private double distance;
	private String colorType;
	private int number;
	
	Object(int xPos, int yPos, int radius, String cType) {
		x = xPos;
		y = yPos;
		r = radius;
		colorType = cType;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public double getDistance() {
		return distance;
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
