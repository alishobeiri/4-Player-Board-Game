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
	
	Boolean hasRolled=false;
	ArrayList<BoardPanel.Rectangle2DCoord> possibleMoves=new ArrayList<BoardPanel.Rectangle2DCoord>();
	// Components
	JPanel rightPanel = new JPanel();
	BoardPanel board;
	DeckPanel deck = new DeckPanel();
	JButton rollDie = new JButton("Roll Die");
	JTextField dieResult = new JTextField(20);
	JLabel status = new JLabel("Current Player:");
	JLabel currentPlayer = new JLabel("Player ");
	TileOPage mainMenu;

	public GamePage(BoardPanel oldBoard) {
		game=TileOApplication.getCurrentGame();
		currentPlayer.setText("Player " + game.getCurrentPlayer().getNumber() + "'s turn");
		game.setMode(Game.Mode.GAME);
		board=oldBoard;
		board.setMode(BoardPanel.Mode.GAME);
		board.setVisible(true);
		initComponents();
	}
	
	public GamePage(TileOPage aMainMenu){
		mainMenu = aMainMenu;
		game = TileOApplication.getCurrentGame();
		board = new BoardPanel(game.getMode());
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(885, 682);
		setResizable(false);

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
	
	public void setHasRolled(Boolean flag){
		hasRolled=flag;
	}
	
	public boolean getHasRolled(){
		return hasRolled;
	}

	class getCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(!hasRolled){
				rollDieActionPerformed(ev);
			}else{
				showMessage("Please select a highlighted tile to move to");
			}
		}
	}

	class rollDieListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(!hasRolled){
				for(BoardPanel.Rectangle2DCoord rect: possibleMoves){
					rect.setColor(Color.WHITE);
				}
				possibleMoves.clear();
				rollDieActionPerformed(ev);
			}else{
				showMessage("Please select a highlighted tile to move to");
			}
		}
	}

	class finishTurnListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(!hasRolled){
				
			}else{
				showMessage("Please select a highlighted tile to move to");
			}
		}
	}

	class addConnectionListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(!hasRolled){
				
			}else{
				showMessage("Please select a highlighted tile to move to");
			}
		}
	}

	class removeConnectionListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(!hasRolled){
				
			}else{
				showMessage("Please select a highlighted tile to move to");
			}
		}
	}


	// Thomass
	public void rollDieActionPerformed(ActionEvent ev) {
		// clear error message

				hasRolled=true;
				// Call the controller
				PlayModeController toc = new PlayModeController();
				Game game = TileOApplication.getCurrentGame();
				Player currentPlayer = game.getCurrentPlayer();
				Tile currentTile = currentPlayer.getCurrentTile();

				// pass the returned list of tiles somewhere
				// need to update the visual with the number of the die roll but only
				// the list of tiles is returned
				java.util.List<Tile> tiles = toc.rollDie();
				if(tiles == null || tiles.size() == 0){
					showMessage("No possible moves! Sucks to be you!");
					return;
				}
				for(Tile t : tiles){
					BoardPanel.Rectangle2DCoord rect = this.board.getRectangle(t.getX(), t.getY());
					if(rect != null){
						possibleMoves.add(rect);
						rect.setColor(Color.pink);
					}
				}
				board.setMode(BoardPanel.Mode.MOVE_PLAYER);

				refresh();
				board.refreshBoard();
				// update die visual

	}	

	public void refresh() {
		board.refreshBoard();
		currentPlayer.setText("Player " + game.getCurrentPlayer().getNumber() + "'s turn");
		String actionCardTitle;
		String actionCardDescription;
		PlayModeController gmc = new PlayModeController();
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
				/*try{
					//gmc.playRollDieActionCard();
				}
				catch(InvalidInputException e){
					System.out.println("Roll Die Error");
				}
				break;*/
			
			case GAME_CONNECTTILESACTIONCARD:
				actionCardTitle = "Connect Tiles Action Card";
				actionCardDescription = "You can create a new connection by selecting two adjacent tiles.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				/*try{
					//gmc.playConnectTilesActionCard(tile1, tile2);
				}
				catch(InvalidInputException e){
					System.out.println("Connect Tiles Error");
				}
				break;*/
				
			case GAME_REMOVECONNECTIONACTIONCARD:
				actionCardTitle = "Remove Connection Action Card";
				actionCardDescription = "You can remove a connection by selecting two connected tiles.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				/*try{
					//gmc.playRemoveConnectionActionCard();
				}
				catch(InvalidInputException e){
					System.out.println("Remove Connection Error");
				}
				break;*/
				
			case GAME_TELEPORTACTIONCARD:
				actionCardTitle = "Teleport Action Card";
				actionCardDescription = "You can move to any tile on the board.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
				/*try{
					//gmc.playTeleportActionCard();
				}
				catch(InvalidInputException e){
					System.out.println("Teleport Error");
				}
				break;*/
				
			case GAME_LOSETURNACTIONCARD:
				actionCardTitle = "Lose Turn Action Card";
				actionCardDescription = "You lost a turn.";
				deck.setCardInfo(actionCardTitle, actionCardDescription);
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
	
	private void loseTurn(){
		Game.Mode mode = game.getMode();
		if(mode == Game.Mode.GAME_LOSETURNACTIONCARD){
			PlayModeController.setNextPlayer(game);
			refresh();
		}
	}
	
}
