import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;


public class Target extends JFrame implements ActionListener, ChangeListener{

	//Declaration of all GUI elements
	private JPanel contentPane;
	private JPanel pnlContent;

	private int ballSize;
	private int range;
	private int totalNumTargets;
	private int[] dataArray;

	private JSlider sldSize;
	private JSlider sldRange;
	private JSlider sldTargetNum;
	private JSlider sldTurretX;
	private JSlider sldTurretY;

	//Declaration of classes
	private Turret turret;
	private Object objectArray[];
	private JTable tblData;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Target frame = new Target();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	/**
	 * Create the frame. Constructor
	 */
	public Target() {
		//Setting the frame
		setTitle("Targeting Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create turret object
		turret = new Turret(pnlContent.getWidth()/2, pnlContent.getHeight()/2, 10, 20);

		//Generate button
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(790, 480, 137, 52);
		contentPane.add(btnGenerate);
		btnGenerate.addActionListener(this);
		btnGenerate.setActionCommand("Generate");

		//Add new panelContent which is used for painting
		pnlContent = new panelContent();
		contentPane.add(pnlContent);

		//Create slider for size
		sldSize = new JSlider();
		sldSize.setMinimum(1);
		sldSize.setMaximum(3);
		sldSize.setValue(2);
		sldSize.setBounds(20, 490, 200, 26);
		sldSize.addChangeListener(this);
		contentPane.add(sldSize);

		//Size of target label
		JLabel lblSize = new JLabel("Size of Target");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(82, 520, 79, 26);
		contentPane.add(lblSize);

		//Create slider for range
		sldRange = new JSlider();
		sldRange.setValue(300);
		sldRange.setMinimum(20);
		sldRange.setMaximum(pnlContent.getHeight() - 50);
		sldRange.setBounds(260, 490, 200, 26);
		sldRange.addChangeListener(this);
		contentPane.add(sldRange);

		//Turret Range label
		JLabel lblRange = new JLabel("Turret Range");
		lblRange.setHorizontalAlignment(SwingConstants.CENTER);
		lblRange.setBounds(322, 520, 79, 26);
		contentPane.add(lblRange);

		//Number of targets slider
		sldTargetNum = new JSlider();
		sldTargetNum.setValue(5);
		sldTargetNum.setMinimum(1);
		sldTargetNum.setMaximum(10);
		sldTargetNum.setBounds(508, 490, 200, 26);
		contentPane.add(sldTargetNum);

		//Label for Number of Targets
		JLabel lblTargetNum = new JLabel("Number of Targets");
		lblTargetNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTargetNum.setBounds(543, 520, 123, 26);
		contentPane.add(lblTargetNum);

		//Slider for turret x position
		sldTurretX = new JSlider();
		sldTurretX.setBounds(758, 373, 200, 50);
		sldTurretX.setMinimum(5);
		sldTurretX.setValue(5);
		sldTurretX.setMaximum(pnlContent.getWidth() - 10);
		sldTurretX.addChangeListener(this);
		contentPane.add(sldTurretX);

		//Label for turret x position
		JLabel lblTurretX = new JLabel("Turret X Position");
		lblTurretX.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurretX.setBounds(790, 420, 137, 26);
		contentPane.add(lblTurretX);

		//Slider for turret y position
		sldTurretY = new JSlider();
		sldTurretY.setValue(5);
		sldTurretY.setMinimum(5);
		sldTurretY.setMaximum(pnlContent.getHeight() - turret.getRange());
		sldTurretY.setBounds(758, 289, 200, 50);
		sldTurretY.addChangeListener(this);
		contentPane.add(sldTurretY);

		//Label for turret y position
		JLabel lblTurretY = new JLabel("Turret Y Position");
		lblTurretY.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurretY.setBounds(790, 336, 137, 26);
		contentPane.add(lblTurretY);

		//Set up data array, and repaint the panel
		setUpArray();
		pnlContent.repaint();
	}
	
	//Method to initialize all the data entries to 0
	public void setUpArray() {
		dataArray = new int[10];
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = 0;
		}
	}

	//Method to deal with button clicks
	public void actionPerformed(ActionEvent e) {
		//When generate button is pressed, range of turret is set, and then generate objects method is called
		if (e.getActionCommand().equalsIgnoreCase("Generate")) {
			range = sldRange.getValue();
			generateObjects();
		}
	}

	public void generateObjects() {
		int x;
		int y;
		String color = "";
		int c;

		//Get total number of targets from slider value, then create objectArray
		totalNumTargets = sldTargetNum.getValue();
		objectArray = new Object[totalNumTargets];

		if (sldSize.getValue() == 1) {
			ballSize = 20; // Radius
		}
		else if (sldSize.getValue() == 2) {
			ballSize = 35;
		}
		else {
			ballSize = 50;
		}
		for (int i = 0; i < objectArray.length; i++) {
			x = (int) (Math.random() * 400) + 50;
			y = (int) (Math.random() * 300) + 50;
			c = (int) (Math.random() * 5) + 1;
			switch (c) {
			case 1: color = "black";
			break;
			case 2: color = "blue";
			break;
			case 3: color = "green";
			break;
			case 4: color = "red";
			break;
			case 5: color = "yellow";
			break;
			default: color = "black";
			}
			objectArray[i] = new Object(x, y, ballSize, color);
		}
		setObjectNumber();
		calculateDistances();
		pnlContent.repaint();
	}
	public void setObjectNumber() {
		for (int i = 0; i < objectArray.length; i++) {
			objectArray[i].setNumber((i + 1));
		}
	}
	
	public void stateChanged(ChangeEvent evt) {
		if (objectArray != null) {
			if (sldSize == evt.getSource()) {
				if (sldSize.getValue() == 1) {
					ballSize = 20; // Radius
				}
				else if (sldSize.getValue() == 2) {
					ballSize = 35;
				}
				else {
					ballSize = 50;
				}
				for (int i = 0; i < objectArray.length; i++) {
					objectArray[i].setRadius(ballSize);
				}
			}
		}
		else {
			sldSize.setValue(2);
		}
		
		if (sldRange == evt.getSource()) {
			range = sldRange.getValue();
			if (turret != null) {
				turret.setRange(range);
			}
		}
		
		if (sldTurretX == evt.getSource()) {
			System.out.println("run");
			turret.setX(sldTurretX.getValue());
			calculateDistances();
		}
		if (sldTurretY == evt.getSource()) {
			turret.setY(sldTurretY.getValue());
			calculateDistances();
		}
		if (sldTargetNum == evt.getSource()) {
			totalNumTargets = sldTargetNum.getValue();
			return;
		}
		
		pnlContent.repaint();
	}
	
	public void calculateDistances() {
		double distance = 0;
		//Calculate distance for each object to turret
		for (int i = 0; i < objectArray.length; i++) {
			distance = 0;
			distance += Math.pow(objectArray[i].getX() - turret.getX(), 2);
			distance += Math.pow(objectArray[i].getY() - turret.getY(), 2);
			objectArray[i].setDistance(Math.sqrt(distance));
			
			if (objectArray[i].getDistance() <= turret.getRange()) {
				objectArray[i].setWithinRange(true);
			}
			
			dataArray[i] = (int) objectArray[i].getDistance();
			
			//Temp Display
			System.out.println("Object Number: " + (i+1) + "  Distance: " + objectArray[i].getDistance() + "cm");
		}
	}

	class panelContent extends JPanel {
		panelContent() {
			this.setBounds(2, 10, 700, 450);
			this.setBorder(BorderFactory.createBevelBorder(0));
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(Color.black);

			if (objectArray != null) {
				for (int i = 0; i<objectArray.length; i++) {
					objectArray[i].paint(g);
					g.setColor(Color.blue);
					if (objectArray[i].withinRange()) {
						int x1= objectArray[i].getX()+ objectArray[i].getRadius()/2;
						int y1 = objectArray[i].getY() + objectArray[i].getRadius()/2;
						int x2 = turret.getX() + turret.getRange()/2;
						int y2 = turret.getY() + turret.getRange()/2;
						g.drawLine(x1, y1, x2, y2);
					}
				}
			}
			if (turret != null) {
				turret.paint(g);
			}
		}
	}
}
