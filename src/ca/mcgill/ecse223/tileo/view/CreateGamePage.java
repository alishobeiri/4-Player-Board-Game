package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

public class CreateGamePage extends JFrame {

	//Components
	JButton create = new JButton("Create Game");
	Integer[] playerNumbers = {2, 3, 4};
	JComboBox numberOfPlayers = new JComboBox(playerNumbers);
	JLabel numberofPlayerLabel = new JLabel("Number of Players: ");
	JLabel nameLabel = new JLabel("Game Name: ");
	JTextField gameName = new JTextField();
	JLabel title = new JLabel("New Game");
	
	public void initComponents(){
		setTitle("New Game");
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Component positioning
		//layout.setHorizontalGroup(layout.);
	}
}
