import java.awt.Color;
import java.awt.Graphics;


public class Turret {
	
	private int x;
	private int y;
	private int radius;
	private int range;
	private String colour = "black";
	
	Turret(int xPos, int yPos, int r, int ran) {
		x = xPos;
		y = yPos;
		radius = r;
		range = ran;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int newRange) {
		range = newRange;
	}
	public void setX(int input) {
		x = input;
	}
	public void setY(int input) {
		y = input;
	}
	public int getRadius() {
		return radius;
	}
	public void setColour(String input) {
		colour = input;
	}
	
	public void paint(Graphics g) {
		//Drawing turret itself
		switch (colour) {
		case "black":
			g.setColor(Color.BLACK);
			break;
		case "blue":
			g.setColor(Color.BLUE);
			break;
		case "green":
			g.setColor(Color.GREEN);
			break;
		case "yellow":
			g.setColor(Color.YELLOW);
			break;
		case "red":
			g.setColor(Color.RED);
			break;
		case "orange":
			g.setColor(Color.ORANGE);
			break;
		}
		g.fillOval(x, y, radius, radius);
		
		
		g.setColor(Color.BLACK);
		//Drawing turret range
		g.drawOval(x + radius/2 - range/2, y + radius/2 - range/2, range, range);
	}

}
