package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import java.awt.Font;
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
	String initialMode = " ";
	JLabel mode = new JLabel("Mode: ");
	JLabel currentMode = new JLabel(initialMode);
	JButton addTile = new JButton("Add Tile");
	JButton removeTile = new JButton("Remove Tile");
	JButton setDeck = new JButton("Set Up Deck");
	JRadioButton normalTile = new JRadioButton("Normal Tile");
	JRadioButton actionTile = new JRadioButton("Action Tile");
	JRadioButton hiddenTile = new JRadioButton("Hidden Tile");
	Integer[] nums = {1, 2, 3, 4, 5, 6};
	JComboBox inactiveTurns = new JComboBox(nums);
	ButtonGroup ratioButtons = new ButtonGroup();
	Integer[] numberOfPlayersList = {2, 3, 4};
	JComboBox numberOfPlayers = new JComboBox(numberOfPlayersList);
	Integer[] playerToAddList = {1, 2, 3, 4};
	JComboBox playerToAdd = new JComboBox(playerToAddList);
	JLabel numberOfPlayersLabel = new JLabel(" Number of players:");
	JButton placePlayer = new JButton("Place Player");
	JButton addConnection = new JButton("Add Connection");
	JButton removeConnection = new JButton("Remove Connection");
	JButton play = new JButton("Play Game");
	
	//Constructor
	public DesignPage(){
		initComponents();
	}
	
	public void initComponents(){
		setSize(890, 680);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mode.setFont(new Font("Futura", Font.PLAIN, 20));
		currentMode.setFont(new Font("Futura", Font.BOLD, 20));
		
		play.setFont(new Font("San Francisco", Font.BOLD, 14));
		
		ratioButtons.add(normalTile);
		ratioButtons.add(actionTile);
		ratioButtons.add(hiddenTile);
		
		//Add action listeners
		normalTile.addActionListener(new NormalTileListener());
		actionTile.addActionListener(new ActionTileListener());
		hiddenTile.addActionListener(new HiddenTileListener());
		
		addTile.addActionListener(new AddTileListener());
		removeTile.addActionListener(new RemoveTileListener());
		
		placePlayer.addActionListener(new PlacePlayerListener());
		
		addConnection.addActionListener(new AddConnectionListener());
		removeConnection.addActionListener(new RemoveConnectionListener());
		
		setDeck.addActionListener(new SetDeckListener());
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		JSeparator line = new JSeparator();
		JSeparator line1 = new JSeparator();
		JSeparator line2 = new JSeparator();
		JSeparator line3 = new JSeparator();
		
		//Component placement
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(board)
				.addGroup(layout.createParallelGroup()
						.addComponent(mode)
						.addComponent(currentMode)
						.addComponent(line, 220, 220, 220)
						.addComponent(addTile, 220, 220, 220)
						.addComponent(normalTile)
						.addGroup(layout.createSequentialGroup()
								.addComponent(actionTile)
								.addGap(60, 60, 60)
								.addComponent(inactiveTurns, 60, 60, 60))
						.addComponent(hiddenTile)
						.addComponent(removeTile, 220, 220, 220)
						.addComponent(line1, 220, 220, 220)
						.addGroup(layout.createSequentialGroup()
								.addComponent(numberOfPlayersLabel, 130, 130, 130)
								.addGap(30, 30, 30)
								.addComponent(numberOfPlayers, 60, 60, 60))
						.addGroup(layout.createSequentialGroup()
								.addComponent(placePlayer, 120, 120, 120)
								.addGap(40, 40, 40)
								.addComponent(playerToAdd, 60, 60, 60)
								)
						.addComponent(line2, 220, 220, 220)
						.addComponent(addConnection, 220, 220, 220)
						.addComponent(removeConnection, 220, 220, 220)
						.addComponent(line3, 220, 220, 220)
						.addComponent(setDeck, 220, 220, 220)
						.addComponent(play, 220, 220, 220)
						)
		);
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(board)
				.addGap(5, 5, 5)
				.addGroup(layout.createSequentialGroup()
						.addComponent(mode)
						.addComponent(currentMode)
						.addComponent(line, 10, 10, 10)
						.addComponent(addTile)
						.addComponent(normalTile)
						.addGroup(layout.createParallelGroup()
								.addComponent(actionTile)
								.addComponent(inactiveTurns, 25, 25, 25))
						.addComponent(hiddenTile)
						.addComponent(removeTile)
						.addComponent(line1, 10, 10, 10)
						.addGroup(layout.createParallelGroup()
								.addComponent(numberOfPlayersLabel)
								.addComponent(numberOfPlayers, 25, 25, 25))
						.addGroup(layout.createParallelGroup()
								.addComponent(placePlayer)
								.addComponent(playerToAdd, 25, 25, 25)
								)
						.addComponent(line2, 10, 10, 10)
						.addComponent(addConnection)
						.addComponent(removeConnection)
						.addComponent(line3, 10, 10, 10)
						.addComponent(setDeck)
						.addGap(165, 165, 165)
						.addComponent(play)
						)
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
	
	class AddTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Add Tile");
		}
	}
	
	class RemoveTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Remove Tile");
		}
	}
	
	class PlacePlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Place Player");
		}
	}
	
	class AddConnectionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Add Connection");
		}
	}
	
	class RemoveConnectionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Remove Connection");
		}
	}
	
}
