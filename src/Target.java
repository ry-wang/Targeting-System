import java.awt.BorderLayout;
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
		
		JPanel pnlContent = new JPanel();
		pnlContent.setBounds(10, 11, 764, 456);
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
		if (sldSize.getValue() == 1) {
			ballSize = 2;
		}
		else if (sldSize.getValue() == 2) {
			ballSize = 5;
		}
		else {
			ballSize = 8;
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
	
	public void paint(Graphics g) {
		super.paint(g);
	}
}
