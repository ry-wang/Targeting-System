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
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Target extends JFrame implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;

	//Declaration of all GUI elements
	private JPanel contentPane;
	private JPanel pnlContent;

	private int ballSize;
	private int range;
	private int totalNumTargets;
	private String[][] dataArray;
	private String[] tableHeaders = new String[5];

	private JSlider sldSize;
	private JSlider sldRange;
	private JSlider sldTargetNum;
	private JSlider sldTurretX;
	private JSlider sldTurretY;

	//Declaration of classes
	private static Turret turret;
	private Settings settingFrame;
	private Object objectArray[];

	//Declaration of data table
	private JTable tblData;
	private JScrollPane scrollPane;

	private JLabel lblTurretXPos;
	private JLabel lblTurretYPos;
	private JLabel lblTurretRange;

	private JLabel lblTurretXValue;
	private JLabel lblTurretYValue;
	private JLabel lblTurretRangeValue;

	private JButton btnStart;
	private JButton btnPause;
	private JButton btnGenerate;
	private JButton btnStop;


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
		setTitle("Targeting System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Setting frame icon
		ImageIcon frameImg = new ImageIcon(this.getClass().getResource("/Images/TS-Icon.png"));
		setIconImage(frameImg.getImage());

		//Add new panelContent which is used for painting
		pnlContent = new panelContent();
		contentPane.add(pnlContent);

		//Create turret object
		turret = new Turret(pnlContent.getWidth()/2, pnlContent.getHeight()/2, 10, 20);

		//Set up the GUI and refresh
		setUpTable();
		generateButtons();
		generateLabels();
		generateSliders();
		generateMenu();
		pnlContent.repaint();
	}

	//Method that creates all buttons of UI
	public void generateButtons() {
		//Generate button
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(781, 520, 137, 52);
		contentPane.add(btnGenerate);
		btnGenerate.addActionListener(this);
		btnGenerate.setActionCommand("Generate");

		btnStart = new JButton("Start");
		btnStart.setActionCommand("Start");
		btnStart.addActionListener(this);
		btnStart.setBounds(992, 520, 137, 52);
		contentPane.add(btnStart);

		btnPause = new JButton("Pause");
		btnPause.setActionCommand("Pause");
		btnPause.setBounds(992, 520, 137, 52);
		btnPause.addActionListener(this);
		contentPane.add(btnPause);

		//Stop button
		btnStop = new JButton("Stop");
		btnStop.setBounds(781, 520, 137, 52);
		contentPane.add(btnStop);
		btnStop.addActionListener(this);
		btnStop.setActionCommand("Stop");
	}
	//Create labels
	public void generateLabels() {
		//Labels
		lblTurretXPos = new JLabel("Turret X-Position:");
		lblTurretXPos.setBounds(749, 389, 101, 14);
		contentPane.add(lblTurretXPos);

		lblTurretYPos = new JLabel("Turret Y-Position:");
		lblTurretYPos.setBounds(900, 389, 101, 14);
		contentPane.add(lblTurretYPos);

		lblTurretRange = new JLabel("Turret Range:");
		lblTurretRange.setBounds(1052, 389, 87, 14);
		contentPane.add(lblTurretRange);

		lblTurretXValue = new JLabel("");
		lblTurretXValue.setBounds(855, 389, 46, 14);
		contentPane.add(lblTurretXValue);

		lblTurretYValue = new JLabel("");
		lblTurretYValue.setBounds(1005, 389, 46, 14);
		contentPane.add(lblTurretYValue);

		lblTurretRangeValue = new JLabel("");
		lblTurretRangeValue.setBounds(1138, 389, 46, 14);
		contentPane.add(lblTurretRangeValue);

		//Size of target label
		JLabel lblSize = new JLabel("Size of Target");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(82, 560, 79, 26);
		contentPane.add(lblSize);

		//Turret Range label
		JLabel lblRange = new JLabel("Turret Range");
		lblRange.setHorizontalAlignment(SwingConstants.CENTER);
		lblRange.setBounds(322, 560, 79, 26);
		contentPane.add(lblRange);

		//Label for Number of Targets
		JLabel lblTargetNum = new JLabel("Number of Targets");
		lblTargetNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTargetNum.setBounds(543, 560, 123, 26);
		contentPane.add(lblTargetNum);

		//Label for turret x position
		JLabel lblTurretX = new JLabel("Turret X Position");
		lblTurretX.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurretX.setBounds(781, 476, 137, 26);
		contentPane.add(lblTurretX);

		//Label for turret y position
		JLabel lblTurretY = new JLabel("Turret Y Position");
		lblTurretY.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurretY.setBounds(992, 476, 137, 26);
		contentPane.add(lblTurretY);
	}
	//Create sliders	
	public void generateSliders() {
		//Create slider for size
		sldSize = new JSlider();
		sldSize.setMinimum(1);
		sldSize.setMaximum(3);
		sldSize.setValue(2);
		sldSize.setBounds(20, 530, 200, 26);
		sldSize.addChangeListener(this);
		contentPane.add(sldSize);

		//Create slider for range
		sldRange = new JSlider();
		sldRange.setValue(300);
		sldRange.setMinimum(20);
		sldRange.setMaximum(pnlContent.getHeight() - 50);
		sldRange.setBounds(260, 530, 200, 26);
		sldRange.addChangeListener(this);
		contentPane.add(sldRange);

		//Number of targets slider
		sldTargetNum = new JSlider();
		sldTargetNum.setValue(5);
		sldTargetNum.setMinimum(1);
		sldTargetNum.setMaximum(10);
		sldTargetNum.setBounds(508, 530, 200, 26);
		contentPane.add(sldTargetNum);

		//Slider for turret x position
		sldTurretX = new JSlider();
		sldTurretX.setBounds(749, 429, 200, 50);
		sldTurretX.setMinimum(5);
		sldTurretX.setValue(turret.getX());
		sldTurretX.setMaximum(pnlContent.getWidth() - 15);
		sldTurretX.addChangeListener(this);
		contentPane.add(sldTurretX);

		//Slider for turret y position
		sldTurretY = new JSlider();
		sldTurretY.setValue(turret.getY());
		sldTurretY.setMinimum(5);
		sldTurretY.setMaximum(pnlContent.getHeight() - turret.getRange());
		sldTurretY.setBounds(960, 429, 200, 50);
		sldTurretY.addChangeListener(this);
		contentPane.add(sldTurretY);

		sldTurretX.setValue(turret.getX());
		sldTurretY.setValue(turret.getY());
		sldRange.setValue(turret.getRange());

		sldTurretX.setValue(turret.getX());
		sldTurretY.setValue(turret.getY());
		sldRange.setValue(turret.getRange());
	}
	//Method to initialize all the data entries to 0
	public void setUpTable() {
		tableHeaders[0] = "Number";
		tableHeaders[1] = "Colour";
		tableHeaders[2] = "Distance";
		tableHeaders[3] = "Priority";
		tableHeaders[4] = "Within Range";
		dataArray = new String[10][5];

		tblData = new JTable(dataArray, tableHeaders);
		scrollPane = new JScrollPane(tblData);
		scrollPane.setBounds(749, 51, 411, 325);
		tblData.setVisible(false);
		contentPane.add(scrollPane);
	}
	//Generates Menu bar	
	public void generateMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1184, 21);
		contentPane.add(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem menuItem1 = new JMenuItem("About");
		menuItem1.setPreferredSize(new Dimension(150, menu.getPreferredSize().height));
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("About");
		menu.add(menuItem1);

		JMenuItem menuItem3 = new JMenuItem("Settings");
		menuItem3.setPreferredSize(new Dimension(150, menu.getPreferredSize().height));
		menuItem3.addActionListener(this);
		menuItem3.setActionCommand("Settings");
		menu.add(menuItem3);

		JMenuItem menuItem2 = new JMenuItem("Exit");
		menuItem2.setPreferredSize(new Dimension(150, menu.getPreferredSize().height));
		menuItem2.addActionListener(this);
		menuItem2.setActionCommand("Exit");
		menu.add(menuItem2);
	}

	//Method to deal with button clicks
	public void actionPerformed(ActionEvent e) {
		//When generate button is pressed, range of turret is set, and then generate objects method is called
		if (e.getActionCommand().equalsIgnoreCase("Generate")) {
			range = sldRange.getValue();
			generateObjects();
		}
		if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?") == 0) {
				System.exit(0);
			}
		}
		if (e.getActionCommand().equalsIgnoreCase("About")) {
			About aboutFrame = new About(contentPane.getX(), contentPane.getY(), contentPane.getWidth(), contentPane.getHeight());
			aboutFrame.setVisible(true);
		}
		if (e.getActionCommand().equalsIgnoreCase("Start")) {
			//Disable buttons and sliders, enable pause button
			btnStart.setVisible(false);
			btnPause.setVisible(true);
			btnGenerate.setVisible(false);
			sldTurretX.setEnabled(false);
			sldTurretY.setEnabled(false);
			sldRange.setEnabled(false);
			sldTargetNum.setEnabled(false);
			sldSize.setEnabled(false);
			btnStop.setVisible(true);
		}
		if (e.getActionCommand().equalsIgnoreCase("Settings")) {
			settingFrame = new Settings(contentPane.getX(), contentPane.getY(), contentPane.getWidth(), contentPane.getHeight(), turret);
			settingFrame.setVisible(true);
		}
		if (e.getActionCommand().equalsIgnoreCase("Pause")) {
			//Show start button again, hide pause button
			btnStart.setVisible(true);
			btnPause.setVisible(false);

		}
		if (e.getActionCommand().equalsIgnoreCase("Stop")) {
			//Enable buttons and sliders, hide pause button
			btnStart.setVisible(true);
			btnPause.setVisible(false);
			sldTurretX.setEnabled(true);
			sldTurretY.setEnabled(true);
			sldRange.setEnabled(true);
			sldTargetNum.setEnabled(true);
			sldSize.setEnabled(true);
			btnGenerate.setVisible(true);
			btnStop.setVisible(false);
		}
	}

	public void generateObjects() {
		int x;
		int y;
		String color = "";
		int c;

		//Removing rows from the table if a new set of objects are being generated, with total num less than before
		if (totalNumTargets > 0 && totalNumTargets > sldTargetNum.getValue()) {
			//Setting data to null
			for (int i = sldTargetNum.getValue(); i < totalNumTargets; i++) {
				dataArray[i][0] = "";
				dataArray[i][1] = "";
				dataArray[i][2] = "";
				dataArray[i][3] = "";
				dataArray[i][4] = "";
			}
		}

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
			//Creating object
			objectArray[i] = new Object(x, y, ballSize, color);
			//Setting info in dataArray
			dataArray[i][1] = color;
			dataArray[i][0] = String.valueOf(i+1);
			dataArray[i][3] = String.valueOf(objectArray[i].getPriority());
		}

		tblData.setRowHeight((scrollPane.getHeight()-21)/totalNumTargets);

		//Setting number attribute of each object
		setObjectNumber();
		//Calculate distances, which updates JTable, then repaint
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
				lblTurretRangeValue.setText(String.valueOf(turret.getRange()/2));
			}
			calculateDistances();
		}

		if (sldTurretX == evt.getSource()) {
			turret.setX(sldTurretX.getValue());
			lblTurretXValue.setText(String.valueOf(turret.getX()));
			calculateDistances();
		}
		if (sldTurretY == evt.getSource()) {
			turret.setY(sldTurretY.getValue());
			lblTurretYValue.setText(String.valueOf(turret.getY()));
			calculateDistances();
		}
		if (sldTargetNum == evt.getSource()) {
			totalNumTargets = sldTargetNum.getValue();
			return;
		}

		pnlContent.repaint();
	}
	public static Turret getTurretInstance() {
		return turret;
	}
	public void calculateDistances() {
		if (objectArray == null) {
			return;
		}
		double distance = 0;
		//Calculate distance for each object to turret
		for (int i = 0; i < objectArray.length; i++) {
			distance = 0;
			//Pythagorean Theorem
			distance += Math.pow(objectArray[i].getX() - turret.getX(), 2);
			distance += Math.pow(objectArray[i].getY() - turret.getY(), 2);
			distance = Math.sqrt(distance);
			objectArray[i].setDistance(distance);

			//Set within range attribute of object to true when distance is close enough
			if (objectArray[i].getDistance() - objectArray[i].getRadius()/2 <= (double)turret.getRange()/2) {
				objectArray[i].setWithinRange(true);
				dataArray[i][4] = "true";
			}
			else {
				objectArray[i].setWithinRange(false);
				dataArray[i][4] = "false";
			}

			dataArray[i][2] = String.valueOf((int) objectArray[i].getDistance());

			//Temp Display
			System.out.println("Object Number: " + (i+1) + "  Distance: " + objectArray[i].getDistance() + "cm" + " " + turret.getRange());
		}
		pnlContent.repaint();
		((AbstractTableModel) tblData.getModel()).fireTableDataChanged();
		tblData.setVisible(true);
		scrollPane.repaint();
	}

	class panelContent extends JPanel {

		private static final long serialVersionUID = 1L;

		panelContent() {
			this.setBounds(20, 50, 700, 450);
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
						int x1= objectArray[i].getX();
						int y1 = objectArray[i].getY();
						int x2 = turret.getX() + turret.getRadius()/2;
						int y2 = turret.getY() + turret.getRadius()/2;
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
