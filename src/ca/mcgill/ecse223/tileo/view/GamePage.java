package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;


public class GamePage extends JFrame {

	private Game game;
	
	ArrayList<BoardPanel.Rectangle2DCoord> possibleMoves=new ArrayList<BoardPanel.Rectangle2DCoord>();
	ArrayList<BoardPanel.Rectangle2DCoord> secondMoves=new ArrayList<BoardPanel.Rectangle2DCoord>();
	// Components
	JPanel rightPanel = new JPanel();
	BoardPanel board;
	DeckPanel deck = new DeckPanel();
	JButton rollDie = new JButton("Roll Die");
	JTextField dieResult = new JTextField(20);
	JLabel status = new JLabel("Current Player:");
	JLabel currentPlayer = new JLabel("Player ");
	TileOPage mainMenu;
	boolean flag=false;

	public GamePage(BoardPanel oldBoard) {
		game=TileOApplication.getCurrentGame();
		int player=game.getCurrentPlayer().getNumber();
		if(player%4==0){
			player=4;
		}
		currentPlayer.setText("Player " + game.getCurrentPlayer().getNumber()%4 + "'s turn");
		game.setMode(Game.Mode.GAME);
		board=oldBoard;
		board.setMode(BoardPanel.Mode.GAME);
		board.resetTileColor();
		board.setVisible(true);
		mainMenu = TileOApplication.getMainMenu();
		initComponents();
	}
	
	public GamePage(TileOPage aMainMenu){
		mainMenu = aMainMenu;
		game = TileOApplication.getCurrentGame();
		board = new BoardPanel(game.getMode());
		board.resetTileColor();
		initComponents();
	}

	public void initComponents() {
		setSize(885, 682);
		setResizable(false);
		addWindowListener(new CloseListener());
		
		board.resetTileColor();

		// Settings for text field
		dieResult.setFont(new Font("Futura", Font.BOLD, 56));
		dieResult.setEditable(false);

		// Change fonts
		status.setFont(new Font("Futura", Font.PLAIN, 26));
		currentPlayer.setFont(new Font("Futura", Font.BOLD, 26));

		// Set Group Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Add listeners for buttons
		rollDie.addActionListener(new rollDieListener());
		
		JSeparator line1 = new JSeparator();
		JSeparator line2 = new JSeparator();
		
		// Component placement
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(board, 647, 647, 647)
				.addGroup(layout.createParallelGroup()
						.addComponent(status)
						.addComponent(currentPlayer)
						.addComponent(line1, 220, 220, 220)
						.addComponent(deck, 220, 220, 220)
						.addComponent(line2, 220, 220, 220)
						.addGap(220, 220, 220)
						.addComponent(rollDie, 220, 220, 220)
						.addGroup(layout.createSequentialGroup()
								.addGap(85, 85, 85)
								.addComponent(dieResult, 50, 50, 50)
								)
				)
		);
		layout.setVerticalGroup(layout.createParallelGroup()
				.addComponent(board, 647, 647, 647)
				.addGroup(layout.createSequentialGroup()
						.addComponent(status)
						.addComponent(currentPlayer)
						.addComponent(line1, 20 , 20, 20)
						.addComponent(deck, 300, 300, 300)
						.addComponent(line2, 20 , 20, 20)
						.addComponent(rollDie)
						.addComponent(dieResult, 60, 60, 60)));
	}

	public void rollDieActionPerformed(ActionEvent ev) {
		// clear error message

				// Call the controller
				PlayModeController toc = new PlayModeController();
				Game game = TileOApplication.getCurrentGame();
				Player currentPlayer = game.getCurrentPlayer();
				Tile currentTile = currentPlayer.getCurrentTile();

				// pass the returned list of tiles somewhere
				// need to update the visual with the number of the die roll but only
				// the list of tiles is returned
				
				java.util.List<Tile> tiles = new ArrayList<Tile>();
					tiles = toc.rollDie();
					
					if(tiles == null || tiles.size() == 0){
						showMessage("No possible moves!");
						try {
							toc.land(currentPlayer.getCurrentTile());
						} catch (InvalidInputException e) {
							e.printStackTrace();
						}
						toc.setNextPlayer(game);
						TileOApplication.getDesignPanel().refresh();
						return;
					}
				
				//This shows the possible moves in pink
				for(Tile t : tiles){
					BoardPanel.Rectangle2DCoord rect = this.board.getRectangle(t.getX(), t.getY());
					if(rect != null){

						rect.setColor(Color.pink);
						
					}
				}
				board.setMode(BoardPanel.Mode.MOVE_PLAYER);
				refresh();
				board.refreshBoard();
	}	

	public void refresh() {
		board.refreshBoard();
		PlayModeController gmc = new PlayModeController();
		
		int player=game.getCurrentPlayer().getNumber()%4;
		if(player%4==0){
			player=4;
		}
		currentPlayer.setText("Player " + player + "'s turn");

		String actionCardTitle;
		String actionCardDescription;
		
		switch(game.getMode()){
			case GAME:
				deck.setToDefault();
				break;
				
			case GAME_WON:

				break;
				
			case GAME_ROLLDIEACTIONCARD:
				actionCardTitle = "Roll Die Action Card";
				actionCardDescription = "You can roll the die again.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				rollDieAgain();
				/*try{
					//gmc.playRollDieActionCard();
				}
				catch(InvalidInputException e){
					System.out.println("Roll Die Error");
				}*/
				break;
			
			case GAME_CONNECTTILESACTIONCARD:
				actionCardTitle = "Connect Tiles Action Card";
				actionCardDescription = "You can create a new connection by selecting two adjacent tiles.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				if(board.prev != null && board.curr != null){
					Tile tile1 = board.boardTiles.get(board.prev);
					Tile tile2 = board.boardTiles.get(board.curr);
					try{
						gmc.playConnectTilesActionCard(tile1, tile2);
						board.addConnection(board.getRectangle(tile1.getX(), tile1.getY()), board.getRectangle(tile2.getX(), tile2.getY()), true);
					}
					catch(InvalidInputException e){
						System.out.println("Connect Tiles Error");
						e.printStackTrace();
					}
				}else{
					board.mode=BoardPanel.Mode.ADD_CONNECTION_ACTION_CARD;
					board.addConnection(board.prev, board.curr, true);
				}
				board.refreshBoard();
				break;
				
			case GAME_REMOVECONNECTIONACTIONCARD:
				actionCardTitle = "Remove Connection Action Card";
				actionCardDescription = "You can remove a connection by selecting two connected tiles.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				if(board.currentConnection != null){
					System.out.println("if 3");
					try{
						gmc.playRemoveConnectionActionCard(board.currentConnection);
					}
					catch(InvalidInputException e){
						System.out.println("Connect Tiles Error");
						e.printStackTrace();
					}
				}
				else if(board.prev != null && board.curr != null){
					System.out.println("if 2");
					board.removeConnection(board.prev, board.curr, true);
				}
				else{
					System.out.println("if 1");
					board.mode = BoardPanel.Mode.REMOVE_CONNECTION_ACTION_CARD;
				}
				break;
				
			case GAME_TELEPORTACTIONCARD:
				actionCardTitle = "Teleport Action Card";
				actionCardDescription = "You can move to any tile on the board.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				//showMessage("You have received a teleport card, please choose any tile to move to");
				teleportCard();

/*				try{
					//gmc.playTeleportActionCard();
				}
				catch(InvalidInputException e){
					System.out.println("Teleport Error");
				}*/
				break;
				
			case GAME_LOSETURNACTIONCARD:
				actionCardTitle = "Lose Turn Action Card";
				actionCardDescription = "You lost a turn.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				showMessage("You landed on a lose turn action card, you will have to skip your next turn");
				break;
		}
	}

	// Thomas
	public void refreshDie(int number) {
		dieResult.setText(Integer.toString(number));
	}
	
	public void showMessage(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	class CloseListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			
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
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
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
	
/*	private void loseTurn(){
		Game.Mode mode = game.getMode();
		PlayModeController pmc = new PlayModeController();
		if(mode == Game.Mode.GAME_LOSETURNACTIONCARD){
			pmc.setNextPlayer(TileOApplication.getCurrentGame());
			refresh();
		}
	}*/

	public void teleportCard(){
		Game.Mode mode = game.getMode();
		PlayModeController pmc = new PlayModeController();
			board.paintAllPink();
			board.mode=BoardPanel.Mode.TELEPORT;
			flag=true;
			board.refreshBoard();

	}


	private void rollDieAgain(){
		PlayModeController pmc = new PlayModeController();
		java.util.List<Tile> moves = null;
		System.out.println("My fird");
		try {
			moves = pmc.playRollDieActionCard();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(moves == null){
			showMessage("No Possible Moves");
			pmc.setNextPlayer(game);
			refresh();
		}
		for(Tile t : moves){
			BoardPanel.Rectangle2DCoord rect = board.getRectangle(t.getX(), t.getY());
			rect.setColor(Color.pink);
			System.out.println("sup homie");
		}
		flag=true;
		board.mode = BoardPanel.Mode.ROLL_DIE;
		board.refreshBoard();
	}
	
	public void enableRollDieButton(boolean enabled){
		rollDie.setEnabled(enabled);
	}
	
	class rollDieListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
				for(BoardPanel.Rectangle2DCoord rect: possibleMoves){
					rect.setColor(Color.WHITE);
				}
				possibleMoves.clear();
				rollDieActionPerformed(ev);
				
	
		}
	}
	
}
