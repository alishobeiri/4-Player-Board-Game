package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game;

public class CreateGamePage extends JFrame {
	
	//Testing
	public static void main(String[] args){
		new CreateGamePage().setVisible(true);
	}

	//Components
	JButton create = new JButton("Create Game");
	Integer[] playerNumbers = {2, 3, 4};
	JComboBox numberOfPlayers = new JComboBox(playerNumbers);
	JLabel numberOfPlayersLabel = new JLabel("Number of Players:");
	JLabel nameLabel = new JLabel("Game Name: ");
	JTextField gameName = new JTextField();
	JLabel title = new JLabel("New Game");
	
	public CreateGamePage(){
		initComponents();
	}
	
	public void initComponents(){
		setTitle("New Game");
		setSize(300, 195);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Font settings
		title.setFont(new Font("Futura", Font.PLAIN, 26));
		numberOfPlayersLabel.setFont(new Font("San Francisco", Font.PLAIN, 14));
		nameLabel.setFont(new Font("San Francisco", Font.PLAIN, 14));
		
		//ActionListener
		create.addActionListener(new CreateListener());
		
		JSeparator line = new JSeparator();
		
		//Component positioning
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(75, 75, 75)
						.addComponent(title)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(gameName, 187, 187, 187))
				.addGroup(layout.createSequentialGroup()
						.addComponent(numberOfPlayersLabel)
						.addGap(95, 95, 95)
						.addComponent(numberOfPlayers, 60, 60, 60)
				)
				.addComponent(line, 280, 280, 280)
				.addGroup(layout.createSequentialGroup()
						.addGap(85, 85, 85)
						.addComponent(create)
						)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(title)
				.addGap(20, 20, 20)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(gameName, 25, 25, 25))
				.addGroup(layout.createParallelGroup()
						.addComponent(numberOfPlayersLabel)
						.addComponent(numberOfPlayers, 25, 25, 25))
				.addComponent(line, 10, 10, 10)
				//.addGap(10, 10, 10)
				.addComponent(create)
		);
	}
	
	class CreateListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			DesignModeController dmc = new DesignModeController();
			int players = (int) numberOfPlayers.getSelectedItem();
			try{
				dmc.createGame(players);
			}
			catch(InvalidInputException e){
				System.out.println(e.getMessage());
			}
			new DesignPage(gameName.getText(),(int) numberOfPlayers.getSelectedItem()).setVisible(true);
		}
	}
}
