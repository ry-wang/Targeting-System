import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Settings extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnReturn;
	private JButton btnSave;
	private Turret turretInstance;
	private JComboBox comboBox;
	//private boolean changedValue = false;

	/**
	 * Launch the application, only used for debugging
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings(100, 100, 500, 500, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings(int x, int y, int width, int height, Turret turret) {
		turretInstance = turret;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x + width/2 - 450/4, y + height/2 - 20, 450, 300);
		setTitle("Settings");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//Setting frame icon
		ImageIcon frameImg = new ImageIcon(this.getClass().getResource("/Images/TS-Icon.png"));
		setIconImage(frameImg.getImage());
		
		//Return button
		btnReturn = new JButton("Return");
		btnReturn.setBounds(70, 190, 111, 37);
		btnReturn.addActionListener(this);
		contentPane.setLayout(null);
		btnReturn.setActionCommand("Return");
		contentPane.add(btnReturn);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setBounds(146, 23, 138, 49);
		contentPane.add(lblSettings);
		
		JLabel lblTurretColour = new JLabel("Turret Colour: ");
		lblTurretColour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTurretColour.setBounds(70, 88, 105, 24);
		contentPane.add(lblTurretColour);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Green", "Yellow", "Red", "Orange"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(248, 92, 80, 20);
		comboBox.addActionListener(this);
		comboBox.setActionCommand("Change");
		contentPane.add(comboBox);
		
		btnSave = new JButton("Save");
		btnSave.setActionCommand("Save");
		btnSave.setBounds(248, 190, 111, 37);
		btnSave.addActionListener(this);
		btnSave.setEnabled(false);
		contentPane.add(btnSave);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Return")) {
			this.dispose();
		}
		if (e.getActionCommand().equalsIgnoreCase("Save")) {
			String colour = "black";
			btnSave.setEnabled(false);
			switch (comboBox.getSelectedIndex()){
				case 0:
					colour = "black";
				break;
				case 1:
					colour = "blue";
				break;
				case 2:
					colour = "green";
				break;
				case 3:
					colour = "yellow";
				break;
				case 4:
					colour = "red";
				break;
				case 5:
					colour = "orange";
				break;
			}
			//turretInstance.setColour(colour);
			Target.getTurretInstance().setColour(colour);
			//System.out.println("set");
			this.dispose();
		}
		if (e.getActionCommand().equalsIgnoreCase("Change")) {
			btnSave.setEnabled(true);
		}
	}
}
