import java.awt.Color;
import java.awt.Graphics;

//Turret class, inherits from Object
public class Turret extends Object {
	
	//Fields of Turret class
	private int range;
	
	//Constructor for this class
	Turret(int xPos, int yPos, int r, int ran) {
		//Calls constructor for superclass, which is Object
		super(xPos, yPos, r, "black");
		//Turret-specific variables are set
		this.range = ran;
	}
	
	//Additional methods unique to this class
	public int getRange() {
		return range;
	}
	public void setRange(int newRange) {
		range = newRange;
	}
	//Paint class, where the turret is painted
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
