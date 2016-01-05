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
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Target extends JFrame implements ActionListener, ChangeListener{

	private JPanel contentPane;
	private JPanel pnlContent;

	private int ballSize;
	private JSlider sldSize;
	private JSlider sldRange;
	private int range;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(617, 498, 137, 52);
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
		sldRange.setValue(25);
		sldRange.setMinimum(1);
		sldRange.setMaximum(50);
		sldRange.setBounds(260, 490, 200, 26);
		sldRange.addChangeListener(this);
		contentPane.add(sldRange);

		JLabel lblRange = new JLabel("Range");
		lblRange.setHorizontalAlignment(SwingConstants.CENTER);
		lblRange.setBounds(322, 520, 79, 26);
		contentPane.add(lblRange);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Generate")) {
			range = sldRange.getValue();
			generateObjects();
			turret = new Turret(200, 400, 6, range);
		}

	}

	public void generateObjects() {
		int x;
		int y;
		String color = "";
		int c;

		if (sldSize.getValue() == 1) {
			ballSize = 2; // Radius
		}
		else if (sldSize.getValue() == 2) {
			ballSize = 5;
		}
		else {
			ballSize = 8;
		}
		for (int i = 0; i < objectArray.length; i++) {
			x = (int) (Math.random() * 400) + 50;
			y = (int) (Math.random() * 400) + 50;
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
	}
	public void setObjectNumber() {
		for (int i = 0; i < objectArray.length; i++) {
			objectArray[i].setNumber(i++);
		}
	}

	public void stateChanged(ChangeEvent evt) {
		if (sldSize == evt.getSource()) {

		}
		if (sldRange == evt.getSource()) {
			range = sldRange.getValue();
		}

	}
	
	class panelContent extends JPanel {
		panelContent() {
			this.setBounds(0, 0, 200, 200);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.black);
			g.drawString("test", 50, 50);
		}
	}
}
