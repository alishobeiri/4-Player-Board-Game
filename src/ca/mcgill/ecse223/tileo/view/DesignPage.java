package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;
import java.awt.event.*;

public class DesignPage extends JFrame {
	
	//***TESTING***
	public static void main(String[] args){
		new DesignPage().setVisible(true);
	}
	
	//Value Fields
	private int rollDieCards, connectTilesCards, removeConnectionCards, teleportCards, loseTurnCards;
	
	DeckSetUpPage deckSetUp = new DeckSetUpPage(this);
	
	//Components
	BoardPanel board = new BoardPanel();
	JButton addTile = new JButton("Add Tile");
	JButton removeTile = new JButton("Remove Tile");
	JButton setDeck = new JButton("Set Up Deck");
	JRadioButton normalTile = new JRadioButton("Normal Tile");
	JRadioButton actionTile = new JRadioButton("Action Tile");
	JRadioButton hiddenTile = new JRadioButton("Hidden Tile");
	String[] nums = {"1", "2", "3" ,"4", "5", "6"};
	JComboBox inactiveTurns = new JComboBox(nums);
	ButtonGroup ratioButtons = new ButtonGroup();
	
	//Constructor
	public DesignPage(){
		initComponents();
	}
	
	public void initComponents(){
		setSize(885, 680);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ratioButtons.add(normalTile);
		ratioButtons.add(actionTile);
		ratioButtons.add(hiddenTile);
		
		//Add action listeners
		normalTile.addActionListener(new NormalTileListener());
		actionTile.addActionListener(new ActionTileListener());
		hiddenTile.addActionListener(new HiddenTileListener());
		
		setDeck.addActionListener(new SetDeckListener());
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Component placement
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(board)
				.addGroup(layout.createParallelGroup()
						.addComponent(addTile, 220, 220, 220)
						.addComponent(normalTile)
						.addGroup(layout.createSequentialGroup()
								.addComponent(actionTile)
								.addComponent(inactiveTurns, 50, 50, 60))
						.addComponent(hiddenTile)
						.addComponent(removeTile, 220, 220, 220)
						.addComponent(setDeck, 220, 220, 220))
		);
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(board)
				.addGroup(layout.createSequentialGroup()
						.addComponent(addTile)
						.addComponent(normalTile)
						.addGroup(layout.createParallelGroup()
								.addComponent(actionTile)
								.addComponent(inactiveTurns, 25, 25, 25))
						.addComponent(hiddenTile)
						.addComponent(removeTile)
						.addComponent(setDeck))
		);	
		
	}
	
	public void setCardNumbers(int[] values){
		rollDieCards = values[0];
		connectTilesCards = values[1];
		removeConnectionCards = values[2];
		teleportCards = values[3];
		loseTurnCards = values[4];
	}
	
	class NormalTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			inactiveTurns.enable(false);
			inactiveTurns.requestFocus();
		}
	}
	
	class ActionTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			inactiveTurns.enable(true);
			inactiveTurns.requestFocus();
		}
	}
	
	class HiddenTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			inactiveTurns.enable(false);
			inactiveTurns.requestFocus();
		}
	}
	
	class SetDeckListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			
			deckSetUp.setVisible(true);
			
		}
	}
	
}
