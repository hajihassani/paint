package paint.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import paint.entities.User;
import paint.managers.UserManager;

public class SignInUp extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private UserManager userMng = UserManager.getInstance();

	private JFrame frame;
	private JPanel panel;
	private JLabel msgLbl;
	private JLabel passLbl;
	private JPasswordField passTxt;
	private JButton sinBtn;
	private JButton supBtn;
	private JLabel userLbl;
	private JTextField userTxt;

	public SignInUp() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		panel = new JPanel();
		userLbl = new JLabel();
		passLbl = new JLabel();
		userTxt = new JTextField();
		passTxt = new JPasswordField();
		sinBtn = new JButton();
		supBtn = new JButton();
		msgLbl = new JLabel();

		frame.setTitle("Log in - Sign up");
		frame.setBounds(20, 20, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new GridLayout(1, 1));

	}

	private void showInterface() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		userLbl.setFont(new Font("Tahoma", 1, 12));
		userLbl.setText("Username");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 10;
		panel.add(userLbl, gbc);

		userTxt.setFont(new Font("Tahoma", 1, 12));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 150;
		gbc.ipady = 10;
		panel.add(userTxt, gbc);

		passLbl.setFont(new Font("Tahoma", 1, 12));
		passLbl.setText("Password");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipadx = 40;
		gbc.ipady = 10;
		panel.add(passLbl, gbc);

		passTxt.setFont(new Font("Tahoma", 1, 12));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 150;
		gbc.ipady = 10;
		panel.add(passTxt, gbc);

		sinBtn.setFont(new Font("Tahoma", 1, 12));
		sinBtn.setText("Sign In");
		sinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				sinBtnActionPerformed(evt);
			}
		});
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.ipady = 10;
		panel.add(sinBtn, gbc);

		supBtn.setFont(new Font("Tahoma", 1, 12));
		supBtn.setText("Sign Up");
		supBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				supBtnActionPerformed(evt);
			}
		});
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.ipady = 10;
		panel.add(supBtn, gbc);

		msgLbl.setFont(new Font("Tahoma", 1, 12));
		msgLbl.setHorizontalAlignment(0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.ipady = 10;
		panel.add(msgLbl, gbc);

		frame.add(panel);
		frame.setVisible(true);
	}

	private void sinBtnActionPerformed(java.awt.event.ActionEvent evt) {
		User user = userMng.load(userTxt.getText());
		if (user != null) {

			char[] passkey = passTxt.getPassword();
			String key = "";

			for (int i = 0; i < passkey.length; i++) {
				key += passkey[i];
			}

			if (user.getPassword().equals(key)) {
				DrawGui.user_id = user.getId();
				DrawGui.main(null);
				frame.dispose();
			} else {
				msgLbl.setText("Wrong username or Password!");
			}
		} else {
			msgLbl.setText("There is no such user!");
		}
	}

	private void supBtnActionPerformed(java.awt.event.ActionEvent evt) {
		User user = new User();
		user.setUsername(userTxt.getText());
		char[] passkey = passTxt.getPassword();
		String key = "";
		for (int i = 0; i < passkey.length; i++) {
			key += passkey[i];
		}
		user.setPassword(key);
		msgLbl.setText(userMng.save(user));
	}

	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInUp window = new SignInUp();
					window.showInterface();
				} catch (Exception e) {

				}
			}
		});

	}

}
