package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.controller.PlayModeController;

import java.awt.*;
import java.awt.event.*;

public class ChooseMovePopUp extends JFrame {

	// Components
	JButton OK = new JButton("OK");
	JLabel label = new JLabel("Choose the number of tiles that you want to move:");
	JComboBox num;
	GamePage gamePage;

	public ChooseMovePopUp(GamePage gPage) {
		initComponents();
		gamePage = gPage;
	}

	public void initComponents() {
		setTitle("Choose Additional Move Action Card");
		setSize(490, 115);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Set initial location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		// Add ActionListener
		OK.addActionListener(new OKListener());

		// Initialize JComboBox
		Integer[] numbers = { 1, 2, 3, 4, 5, 6 };
		num = new JComboBox(numbers);

		// Set Font
		label.setFont(new Font("San Francisco", Font.PLAIN, 15));

		// Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup().addGap(25).addComponent(label).addComponent(num, 70, 70, 70))
				.addGroup(layout.createSequentialGroup().addGap(175, 175, 175).addComponent(OK, 140, 140, 140)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup().addGap(12).addComponent(label)).addComponent(num))
				.addComponent(OK));
	}

	class OKListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// Need to incorporate OK button into action card
			// Logic
			dispose();
			// System.exit(0);
		}
	}

}
