/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.awt.Color;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.view.BoardPanel;
import ca.mcgill.ecse223.tileo.view.Ellipse2DCoord;
import ca.mcgill.ecse223.tileo.view.Rectangle2DCoord;

// line 46 "../../../../../TileO (updated Feb10).ump"
public class NormalTile extends Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4242199115754805670L;

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public NormalTile(int aX, int aY, Game aGame) {
		super(aX, aY, aGame);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
		super.delete();
	}

	// Thomas
	public void land() {
		// Get the game that this tile is a part of
		Game currentGame = this.getGame();
		// Get the player that wants to move to the tile
		Player currentPlayer = currentGame.getCurrentPlayer();
		// Set the current tile to this tile
		currentPlayer.setCurrentTile(this);
		
		// Get increase the index of the current player by one
		/*int nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) + 1;
		// Loop back if it is the last player
		nextPlayerIndex = nextPlayerIndex % currentGame.numberOfPlayers();
		// Get the next player
		Player nextPlayer = currentGame.getPlayer(nextPlayerIndex);
		// Set the next player as the current player
		currentGame.setCurrentPlayer(nextPlayer);*/
		
		this.setHasBeenVisited(true);
		
		currentGame.setMode(Mode.GAME);
	}
	
	public void land(Rectangle2DCoord rect){
		Game game=TileOApplication.getCurrentGame();
		Player player = game.getCurrentPlayer();
		int gameIndex=TileOApplication.getTileO().indexOfGame(game)+1;
		Ellipse2DCoord circle = new Ellipse2DCoord(rect.getX(), rect.getY());
		int playerNumber = (gameIndex*4)+(game.getCurrentPlayer().getNumber()%4);
		PlayModeController pmc = new PlayModeController();
		if(playerNumber%4!=0){
			playerNumber=playerNumber-4;
		}
		switch(player.getColorFullName()){
			case "RED":
				circle.setColor(Color.RED);
				break;
			case "YELLOW":
				circle.setColor(Color.YELLOW);
				break;
			case "BLUE":
				circle.setColor(Color.BLUE);
				break;
			case "GREEN":
				circle.setColor(Color.GREEN);
				break;
		}
		BoardPanel board = TileOApplication.getBoard();
		board.playerTiles.put(playerNumber, circle);
		player.setCurrentTile(this);
		pmc.setNextPlayer();
		TileOApplication.getGamePage().refresh();
		board.refreshBoard();
/*		int playerNumber;
		PlayModeController pmc = new PlayModeController();
		Player player=game.getCurrentPlayer();
		int gameIndex=TileOApplication.getTileO().indexOfGame(game)+1;
		if(rect.color.equals(Color.pink)){
			playerNumber=(gameIndex*4)+(game.getCurrentPlayer().getNumber()%4);
			Ellipse2DCoord circle=new Ellipse2DCoord(rect.coordX, rect.coordY);
			switch(player.getColorFullName()){
				case "RED":
					circle.setColor(Color.RED);
					break;
				case "YELLOW":
					circle.setColor(Color.YELLOW);
					break;
				case "BLUE":
					circle.setColor(Color.BLUE);
					break;
				case "GREEN":
					circle.setColor(Color.GREEN);
					break;
			}
			System.out.println("Original player number");
			if(playerNumber%4!=0){
				playerNumber=playerNumber-4;
			}
			System.out.println("The player number is " + playerNumber);
			System.out.println("The hashmap after put is " + playerTiles.keySet());
			playerTiles.put(playerNumber, circle);
			System.out.println("The hashmap after put is " + playerTiles.keySet());
			Tile t=boardTiles.get(rect);
			player.setCurrentTile(boardTiles.get(rect));
			try {
				
				pmc.land(t);
				if(game.getMode()==Game.Mode.GAME){
					pmc.setNextPlayer();
				}
				System.out.println("Homie we made it");
				if(!visitedTiles.contains(rect)){
					visitedTiles.add(rect);
				}		
				TileOApplication.getGamePage().refresh();
				pmc.save();
				repaint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(TileOApplication.getGamePage().flag){
				showMessage("Please roll die again");
			}else{
				showMessage("Please select a valid tile");
			}
		}*/
	}
}