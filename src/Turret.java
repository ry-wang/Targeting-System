import java.awt.Color;
import java.awt.Graphics;


public class Turret extends Object {
	
	private int range;
	
	Turret(int xPos, int yPos, int r, int ran) {
		super(xPos, yPos, r, "black");
		this.range = ran;
	}
	
	public int getRange() {
		return range;
	}
	public void setRange(int newRange) {
		range = newRange;
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
