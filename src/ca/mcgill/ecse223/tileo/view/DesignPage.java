package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.view.BoardPanel.Mode;
import ca.mcgill.ecse223.tileo.view.BoardPanel.TileType;
import java.awt.Font;
import java.awt.event.*;

public class DesignPage extends JFrame {
	
	//***TESTING***
	/*public static void main(String[] args){
		new DesignPage().setVisible(true);
	}*/
	
	//Value Fields
	private int rollDieCards, connectTilesCards, removeConnectionCards, teleportCards, loseTurnCards;
	private int players;
	private Game game;
	
	DeckSetUpPage deckSetUp = new DeckSetUpPage(this);
	
	//Components
	BoardPanel board = new BoardPanel(Game.Mode.DESIGN);
	String initialMode = "New Game";
	JLabel mode = new JLabel("Mode: ");
	JLabel currentMode = new JLabel(initialMode);
	JButton addTile = new JButton("Add Tile");
	JButton removeTile = new JButton("Remove Tile");
	JButton setDeck = new JButton("Set Up Deck");
	JRadioButton normalTile = new JRadioButton("Normal Tile");
	JRadioButton actionTile = new JRadioButton("Action Tile");
	JRadioButton hiddenTile = new JRadioButton("Hidden Tile");
	Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	JComboBox inactiveTurns = new JComboBox(nums);
	ButtonGroup ratioButtons = new ButtonGroup();
	JComboBox playerToAdd;
	JLabel numberOfPlayersLabel = new JLabel(" Number of players:");
	JButton placePlayer = new JButton("Place Player");
	JButton addConnection = new JButton("Add Connection");
	JButton removeConnection = new JButton("Remove Connection");
	JButton play = new JButton("Play Game");
	TileOPage mainMenu;
	
	//Constructor
	public DesignPage(TileOPage aMainMenu){
		mainMenu = aMainMenu;
		game = TileOApplication.getCurrentGame();
		game.setMode(Game.Mode.DESIGN);
		players = game.getPlayers().size();
		initComponents();
	}
	
	public DesignPage(Game aGame, TileOPage aMainMenu){
		mainMenu = aMainMenu;
		game = aGame;
		initComponents();
	}
	
	public void initExistingGameComponents(){
		players = game.getPlayers().size();
		Deck deck = game.getDeck();
		
	}
	
	public BoardPanel getBoard(){
		return board;
	}
	
	public void initComponents(){
		setSize(885, 682);
		setResizable(false);
		addWindowListener(new CloseListener());
		
		mode.setFont(new Font("San Francisco", Font.PLAIN, 20));
		currentMode.setFont(new Font("San Francisco", Font.BOLD, 20));
		
		play.setFont(new Font("San Francisco", Font.BOLD, 14));
		
		ratioButtons.add(normalTile);
		ratioButtons.add(actionTile);
		ratioButtons.add(hiddenTile);
		
		normalTile.setSelected(true);
		
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
		
		inactiveTurns.addActionListener(new InactiveTurnsListener());
		
		play.addActionListener(new PlayGameListener());

		//play.addActionListener(new PlayGameListener());
		//play.addActionListener(new PlayerToAddListener());
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		JSeparator line = new JSeparator();
		JSeparator line1 = new JSeparator();
		JSeparator line2 = new JSeparator();
		JSeparator line3 = new JSeparator();
		JSeparator line4 = new JSeparator();
		
		//Initialize JComboBox
		Integer[] playerNums = new Integer[players];
		TileO tileO = TileOApplication.getTileO();
		int gameIndex = tileO.indexOfGame(game);
		int j = 4*(gameIndex) + 1;
		for(int i = 0; i < players; i++){
			playerNums[i] = j;
			j++;
		}
		
		playerToAdd = new JComboBox(playerNums);
		playerToAdd.addActionListener(new PlayerToAddListener());
		
		playerToAdd.addActionListener(new PlayerToAddListener());
		
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
								.addComponent(placePlayer, 120, 120, 120)
								.addGap(20, 20, 20)
								.addComponent(playerToAdd, 80, 80, 80)
								)
						.addComponent(line2, 220, 220, 220)
						.addComponent(addConnection, 220, 220, 220)
						.addComponent(removeConnection, 220, 220, 220)
						.addComponent(line3, 220, 220, 220)
						.addComponent(setDeck, 220, 220, 220)
						.addComponent(line4, 220, 220, 220)
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
								.addComponent(placePlayer)
								.addComponent(playerToAdd, 25, 25, 25)
								)
						.addComponent(line2, 10, 10, 10)
						.addComponent(addConnection)
						.addComponent(removeConnection)
						.addComponent(line3, 10, 10, 10)
						.addComponent(setDeck)
						.addComponent(line4, 10, 10, 10)
						.addGap(187, 187, 187)
						.addComponent(play)
						)
		);	
		
	}
	
	public void setCardNumbers(int[] values) throws InvalidInputException{
		DesignModeController toc=new DesignModeController();
		rollDieCards = values[0];
		connectTilesCards = values[1];
		removeConnectionCards = values[2];
		teleportCards = values[3];
		loseTurnCards = values[4];
		toc.buildDeck(values[0], values[1], values[2], values[3], values[4]);
	}
	
	public void setFrameName(String name){
		setTitle(name);
	}

	public void deleteWindow(){
		this.setResizable(false);
		TileOApplication.saveBoard(board);
		board.setVisible(false);
		dispose();
	}
	

	
	class NormalTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			board.tileType = TileType.NORMAL;
		}
	}
	
	class ActionTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			board.tileType = TileType.ACTION;
		}
	}
	
	class HiddenTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			board.tileType = TileType.WIN;
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
			board.mode = Mode.ADD_TILE;
		}
	}
	
	class RemoveTileListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Remove Tile");
			board.mode = Mode.REMOVE_TILE;
		}
	}
	
	class PlacePlayerListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Place Player");
			board.playerNumber = (int) playerToAdd.getSelectedItem();
			board.mode= Mode.PLACE_PLAYER;
		}
	}
	
	class AddConnectionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Add Connection");
			board.mode = Mode.ADD_CONNECTION;
		}
	}
	
	class RemoveConnectionListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			currentMode.setText("Remove Connection");
			board.mode = Mode.REMOVE_CONNECTION;
		}
	}

	class InactiveTurnsListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			board.inactiveTurns = (int) inactiveTurns.getSelectedItem();
		}
	}
	
	class PlayerToAddListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			board.playerNumber = (int) playerToAdd.getSelectedItem();
		}
		
	}
	
	class PlayGameListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			
			DesignModeController dmc = new DesignModeController();
			dmc.goToGameMode();
			/*PlayModeController poc=new PlayModeController();
			poc.startGame();
			poc.save();
			mainMenu.refresh();*/

		}
	}
	
	class CloseListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			mainMenu.refresh();
		}
			
		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
