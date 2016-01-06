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
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Target extends JFrame implements ActionListener, ChangeListener{

	private JPanel contentPane;
	private JPanel pnlContent;

	private int ballSize;
	private int range;
	private int totalNumTargets;

	private JSlider sldSize;
	private JSlider sldRange;
	private JSlider sldTargetNum;


	private Turret turret;
	private Object objectArray[];

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
		setTitle("Targeting Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(805, 480, 137, 52);
		contentPane.add(btnGenerate);
		btnGenerate.addActionListener(this);
		btnGenerate.setActionCommand("Generate");

		pnlContent = new panelContent();
		contentPane.add(pnlContent);

		sldSize = new JSlider();
		sldSize.setMinimum(1);
		sldSize.setMaximum(3);
		sldSize.setValue(2);
		sldSize.setBounds(20, 490, 200, 26);
		sldSize.addChangeListener(this);
		contentPane.add(sldSize);

		JLabel lblSize = new JLabel("Size of Target");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(82, 520, 79, 26);
		contentPane.add(lblSize);

		sldRange = new JSlider();
		sldRange.setValue(300);
		sldRange.setMinimum(100);
		sldRange.setMaximum(600);
		sldRange.setBounds(260, 490, 200, 26);
		sldRange.addChangeListener(this);
		contentPane.add(sldRange);

		JLabel lblRange = new JLabel("Turret Range");
		lblRange.setHorizontalAlignment(SwingConstants.CENTER);
		lblRange.setBounds(322, 520, 79, 26);
		contentPane.add(lblRange);

		sldTargetNum = new JSlider();
		sldTargetNum.setValue(5);
		sldTargetNum.setMinimum(1);
		sldTargetNum.setMaximum(10);
		sldTargetNum.setBounds(508, 490, 200, 26);
		contentPane.add(sldTargetNum);

		JLabel lblTargetNum = new JLabel("Number of Targets");
		lblTargetNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTargetNum.setBounds(543, 520, 123, 26);
		contentPane.add(lblTargetNum);
		
		pnlContent.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Generate")) {
			range = sldRange.getValue();
			generateObjects();
			turret = new Turret(0, 0, 10, range);
		}

	}

	public void generateObjects() {
		int x;
		int y;
		String color = "";
		int c;

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
		pnlContent.repaint();
	}
	public void setObjectNumber() {
		for (int i = 0; i < objectArray.length; i++) {
			objectArray[i].setNumber((i + 1));
		}
	}

	public void stateChanged(ChangeEvent evt) {
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
			pnlContent.repaint();
		}
		if (sldRange == evt.getSource()) {
			range = sldRange.getValue();
			if (turret != null) {
				turret.setRange(range);
				pnlContent.repaint();
			}
		}
		if (sldTargetNum == evt.getSource()) {
			totalNumTargets = sldTargetNum.getValue();
		}
	}

	class panelContent extends JPanel {
		panelContent() {
			this.setBounds(2, 2, 700, 450);
			this.setBorder(BorderFactory.createBevelBorder(0));
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(Color.black);

			if (objectArray != null) {
				for (int i = 0; i<objectArray.length; i++) {
					objectArray[i].paint(g);
				}
			}
			if (turret != null) {
				turret.paint(g);
			}
		}
	}
}
