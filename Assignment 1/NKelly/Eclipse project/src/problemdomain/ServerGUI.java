package problemdomain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/** This class is the GUI for the server
 * @author Nic Kelly
 */
public class ServerGUI {

	private final Color c_background = Color.decode("#7B7B7B");
	private final Color c_client = Color.decode("#4F5F00");
	private final Color c_opponent = Color.decode("#DC3302");
	private final Color c_chat = Color.decode("#BBD6DF");

	private JTextPane textPane;
	public JFrame frame;

	/** NOT USED. Automatically created by WindowBuilder.
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ServerGUI window = new ServerGUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * Most of the code is auto-generated
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(c_background);
		frame.setBounds(100, 100, 851, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();

		JButton exitButton = new JButton("Close server");
		exitButton.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(exitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 809,
										Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 809,
										Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(exitButton).addContainerGap(18, Short.MAX_VALUE)));

		textPane = new JTextPane();
		textPane.setBackground(c_chat);
		scrollPane.setViewportView(textPane);
		frame.getContentPane().setLayout(groupLayout);
	}

	/** add a message to the GUi
	 * @param string to add to the GUI
	 */
	public void addMessage(String message) {
		textPane.setText(textPane.getText() + message + "\n");
	}
}
