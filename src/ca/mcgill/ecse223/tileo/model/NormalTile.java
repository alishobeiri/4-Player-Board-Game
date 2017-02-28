/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

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
		int nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) + 1;
		// Loop back if it is the last player
		nextPlayerIndex = nextPlayerIndex % currentGame.numberOfPlayers();
		// Get the next player
		Player nextPlayer = currentGame.getPlayer(nextPlayerIndex);
		// Set the next player as the current player
		currentGame.setCurrentPlayer(nextPlayer);
		
		this.setHasBeenVisited(true);
		
		currentGame.setMode(Mode.GAME);
	}
}