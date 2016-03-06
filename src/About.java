import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;


public class About extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About(100, 100, 500, 500);
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
	public About(int x, int y, int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Appear in centre of main frame
		setBounds(x + width/2 - 450/4, y + height/2 - 20, 450, 300);
		setTitle("About");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Setting frame icon
		ImageIcon frameImg = new ImageIcon(this.getClass().getResource("/Images/TS-Icon.png"));
		setIconImage(frameImg.getImage());

		btnReturn = new JButton("Return");
		btnReturn.setBounds(161, 190, 111, 37);
		btnReturn.addActionListener(this);
		btnReturn.setActionCommand("Return");
		contentPane.add(btnReturn);

		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAbout.setBounds(161, 11, 111, 37);
		contentPane.add(lblAbout);

		JLabel lblAbout1 = new JLabel("Made by: Ryan Wang");
		lblAbout1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout1.setBounds(150, 74, 139, 25);
		contentPane.add(lblAbout1);

		JLabel lblAbout2 = new JLabel("Version: 1.0");
		lblAbout2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout2.setBounds(150, 106, 139, 25);
		contentPane.add(lblAbout2);

		JLabel lblAbout3 = new JLabel("Simple application that targets objects");
		lblAbout3.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout3.setBounds(111, 142, 219, 26);
		contentPane.add(lblAbout3);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Return")) {
			this.dispose();
		}

	}
}
