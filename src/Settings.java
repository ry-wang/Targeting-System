import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Settings extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnReturn;

	/**
	 * Launch the application, only used for debugging
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings(100, 100, 500, 500);
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
	public Settings(int x, int y, int width, int height) {
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
		btnReturn.setBounds(161, 190, 111, 37);
		btnReturn.addActionListener(this);
		contentPane.setLayout(null);
		btnReturn.setActionCommand("Return");
		contentPane.add(btnReturn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Return")) {
			
			this.dispose();
		}
		
	}

}
