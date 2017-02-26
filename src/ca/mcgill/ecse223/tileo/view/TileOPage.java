package ca.mcgill.ecse223.tileo.view;

import java.awt.Font;

import javax.swing.*;
import java.util.ArrayList;

public class TileOPage extends JFrame {
	
	//Components
	JLabel title = new JLabel("Tile-O");
	JLabel description = new JLabel("Saved Games:");
	JButton play = new JButton("Play");
	JButton create = new JButton("Create New Game");
	JList games;
	String[] gameNames = new String[10];
	JScrollPane scroll;
	
	//***TESTING***
	public static void main(String[] args){
		new TileOPage().setVisible(true);
	}
	
	//Constructor
	public TileOPage(){
		initComponents();
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 270);
		setResizable(false);
		
		title.setFont(new Font("Futura", Font.BOLD, 38));
		description.setFont(new Font("San Francisco", Font.PLAIN, 18));
		
		//***TESTING***
		
		gameNames[0] = "Game 1";
		gameNames[1] = "Game 2";
		gameNames[2] = "Game 3";
		
		games = new JList(gameNames);
		games.setFont(new Font("San Francisco", Font.PLAIN, 15));
		
		scroll = new JScrollPane(games);
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Component positioning
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(140, 140, 140)
						.addComponent(title))
				.addComponent(description)
				.addComponent(scroll)
				.addGroup(layout.createSequentialGroup()
						.addGap(40, 40, 40)
						.addComponent(play, 150, 150, 150)
						.addComponent(create, 150, 150, 150)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(title)
				.addGap(20, 20, 20)
				.addComponent(description)
				.addComponent(scroll)
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup()
						.addComponent(play)
						.addComponent(create)));
	}
	
}
