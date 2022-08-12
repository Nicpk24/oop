package problemdomain;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import client.ClientConnection;

/**
 * This is the GUI for the client
 * 
 * @author Nic Kelly
 */
public class GUI {
	// these are colours used through the application.
	private final Color c_background = Color.decode("#7B7B7B");
	private final Color c_client = Color.decode("#4F5F00");
	private final Color c_opponent = Color.decode("#DC3302");
	private final Color c_chat = Color.decode("#ADADAD");

	// these are the components of the gui that change
	private JFrame frame;
	private JTextField textField;
	private JTextArea chatHistory;
	private JButton connectButton;
	private JButton Disconnect;
	private JButton findGame;
	private JButton sendButton;
	private JPanel yourPanel;
	private JPanel myPanel;
	private ButtonBoard yourButtons;
	private ButtonBoard myButtons;

	// the username of the client
	public String username;

	// the client connection represented in a client object
	private Client c;

	/**
	 * NOT USED, this is for the WindowBuilder plugin. Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI window = new GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the gui.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * Most of this code is auto generated from the WindowBuilder eclipse plugin.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(c_background);
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		yourPanel = new JPanel();
		yourPanel.setBackground(c_opponent);
		yourButtons = new ButtonBoard(false, Color.RED, Color.DARK_GRAY, this);
		yourPanel.add(yourButtons.makePanel());

		myPanel = new JPanel();
		myPanel.setBackground(c_client);
		myButtons = new ButtonBoard(true, Color.GREEN, Color.DARK_GRAY, this);
		myPanel.add(myButtons.makePanel());

		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(c_background);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(yourPanel, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(myPanel, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
				.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(yourPanel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
								.addComponent(myPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)));
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] { yourPanel, myPanel });
		yourPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		myPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		makeConnection(); // promps the user to make a new connection

		connectButton = new ConnectButton("Connect", this);

		Disconnect = new ExitButton("Exit", this);

		findGame = new FindGameButton("Find game", this);

		sendButton = new SendButton("Send", this);

		textField = new JTextField();
		textField.setBackground(c_chat);
		textField.setColumns(10);

		chatHistory = new JTextArea();
		chatHistory.setBackground(c_chat);

		JLabel yourBoardTitle = new JLabel("Your Board");

		JLabel opponentBoardTitle = new JLabel("Opponent Board");
		GroupLayout gl_chatPanel = new GroupLayout(chatPanel);
		gl_chatPanel.setHorizontalGroup(gl_chatPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_chatPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_chatPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_chatPanel.createSequentialGroup().addComponent(connectButton)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(Disconnect).addGap(305)
								.addComponent(yourBoardTitle).addGap(115).addComponent(opponentBoardTitle)
								.addPreferredGap(ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
								.addComponent(findGame))
						.addGroup(gl_chatPanel.createSequentialGroup()
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(sendButton))
						.addComponent(chatHistory, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1158,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_chatPanel.setVerticalGroup(gl_chatPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_chatPanel.createSequentialGroup()
						.addGroup(gl_chatPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_chatPanel.createParallelGroup(Alignment.BASELINE).addComponent(Disconnect)
										.addComponent(connectButton).addComponent(findGame))
								.addGroup(gl_chatPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(yourBoardTitle).addComponent(opponentBoardTitle)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chatHistory, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_chatPanel.createParallelGroup(Alignment.BASELINE).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sendButton))));
		chatPanel.setLayout(gl_chatPanel);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * Displays the frame
	 */
	public void display() {
		frame.setVisible(true);
	}

	/**
	 * adds a message to the chathistory
	 * 
	 * @param message to be added
	 */
	public void addMessage(String message) {
		if (message != null && !"".equals(message)) {
			chatHistory.setText(chatHistory.getText() + message + "\n");
		}
	}

	/**
	 * NOT WORKING YET simulates a button press
	 * 
	 * @param i the i'th index of the button
	 * @param j the j'th index of the button
	 */
	public void pressButton(int i, int j) {
		yourButtons.press(i, j);
	}

	/**
	 * Makes a connection to a server
	 */
	public void makeConnection() {
		username = JOptionPane.showInputDialog("Enter a username");
		String host = JOptionPane.showInputDialog("Enter a host");
		String portString = JOptionPane.showInputDialog("Enter the port");

//		String host = "localhost";
//		String portString = "1234";

		int port;
		try {
			port = Integer.valueOf(portString);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Invalid entry"); // if port is entered invalid the client will stop
			return;
		}

		try {
			Socket socket = new Socket(host, port);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			this.c = new Client(socket, in, out, username); // setting the client object
			ClientConnection connection = new ClientConnection(this); // creating a thread for server inputs
			connection.start(); // starting the thread

		} catch (ConnectException e3) {
			JOptionPane.showMessageDialog(null, "Server not found");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			// This section of code will update the references to the connection when a new
			// connection is made.

			connectButton = ((ConnectButton) connectButton).updateButton("Connect", this);
			Disconnect = ((ExitButton) Disconnect).updateButton("Exit", this);
			findGame = ((FindGameButton) findGame).updateButton("Find game", this);
			sendButton = ((SendButton) sendButton).updateButton("Send", this);

			yourButtons.updateBoard(this);
			yourPanel.removeAll();
			yourPanel.add(yourButtons.makePanel());

			myButtons.updateBoard(this);
			myPanel.removeAll();
			myPanel.add(myButtons.makePanel());

		} catch (NullPointerException e) {
//			e.printStackTrace();
		}

	}

	/**
	 * @return the client of the GUI
	 */
	public Client getClient() {
		return this.c;
	}

	/**
	 * @return the message in the textField
	 */
	public String getMessage() {
		String message = textField.getText();
		textField.setText("");
		return message;
	}
}