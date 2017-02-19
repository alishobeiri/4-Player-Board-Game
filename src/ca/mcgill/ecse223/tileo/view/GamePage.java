package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePage extends JFrame{
	
	//TESTING
	public static void main(String[] args){
		GamePage view = new GamePage();
		view.setVisible(true);
	}
	
	//Components
	JPanel rightPanel = new JPanel();
	BoardPanel board = new BoardPanel();
	DeckPanel deck = new DeckPanel();
	JButton getCard = new JButton("Get Action Card");
	JButton rollDie = new JButton("Roll Die");
	JButton finishTurn = new JButton("Finish Turn");
	JTextField dieResult = new JTextField(20);
	JLabel status = new JLabel("Current Player:");
	JLabel currentPlayer = new JLabel("Player 1");
	JLabel deckTitle = new JLabel("Deck");
	
	public GamePage(){
		initComponents();
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(885, 680);
		
		//Settings for text field
		dieResult.setFont(new Font("Gill Sans", Font.BOLD, 56));
		dieResult.setEditable(false);
		
		//Change fonts
		status.setFont(new Font("Gill Sans", Font.PLAIN, 26));
		currentPlayer.setFont(new Font("Gill Sans", Font.BOLD, 30));
		deckTitle.setFont(new Font("Gill Sans", Font.BOLD, 26));
		
		//Set Group Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Add listeners for buttons
		getCard.addActionListener(new getCardListener());
		rollDie.addActionListener(new rollDieListener());
		finishTurn.addActionListener(new finishTurnListener());
		
		//Component placement
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addComponent(board, 647, 647, 647)
					.addGroup(layout.createParallelGroup()
							.addComponent(status)
							.addComponent(currentPlayer)
							.addGroup(layout.createSequentialGroup()
									.addGap(80, 80, 80)
									.addComponent(deckTitle))
							.addComponent(deck, 220, 220, 220)
							.addComponent(getCard, 220, 220, 220)
							.addGap(220, 220, 220)
							.addGroup(layout.createSequentialGroup()
									.addGap(70, 70, 70)
									.addComponent(dieResult, 80, 80, 80))
							.addComponent(rollDie, 220, 220, 220)
							.addComponent(finishTurn, 220, 220, 220))			
		);
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(board, 647, 647, 647)
					.addGroup(layout.createSequentialGroup()
						.addComponent(status)
						.addComponent(currentPlayer)
						.addGap(20, 20, 20)
						.addComponent(deckTitle)
						.addComponent(deck, 300, 300, 300)
						.addComponent(getCard)
						.addGap(35, 35, 35)
						.addComponent(dieResult, 80, 80, 80)
						.addComponent(rollDie)
						.addComponent(finishTurn))
		);
	}
	
	class getCardListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			//TODO Add button functionality.
		}
	}
	
	class rollDieListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			//TODO Add button functionality.
		}
	}
	
	class finishTurnListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			//TODO Add button functionality.
		}
	}
	

}
