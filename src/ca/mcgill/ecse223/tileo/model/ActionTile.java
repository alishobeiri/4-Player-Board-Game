/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 39 "../../../../../TileO (updated Feb10).ump"
public class ActionTile extends Tile {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ActionTile Attributes
	private int inactivityPeriod;
	private int turnsUntilActive;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod) {
		super(aX, aY, aGame);
		inactivityPeriod = aInactivityPeriod;
		turnsUntilActive = 0;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setTurnsUntilActive(int aTurnsUntilActive) {
		boolean wasSet = false;
		turnsUntilActive = aTurnsUntilActive;
		wasSet = true;
		return wasSet;
	}

	public int getInactivityPeriod() {
		return inactivityPeriod;
	}

	public int getTurnsUntilActive() {
		return turnsUntilActive;
	}

	public void delete() {
		super.delete();
	}

	public String toString() {
		String outputString = "";
		return super.toString() + "[" + "inactivityPeriod" + ":" + getInactivityPeriod() + "," + "turnsUntilActive"
				+ ":" + getTurnsUntilActive() + "]" + outputString;
	}

	// Thomas
	// TODO
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
		
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = deck.getCurrentCard();
		//TODO check that this method works
		Mode mode = currentCard.getActionCardGameMode();

		currentGame.setMode(mode);
	}
}