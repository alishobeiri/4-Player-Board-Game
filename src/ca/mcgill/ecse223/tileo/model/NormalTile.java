/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 46 "../../../../../TileO (updated Feb10).ump"
public class NormalTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

	public void delete() {
		super.delete();
	}

	// Thomas
	// TODO
	public void land() {
		// Get the game that this tile is a part of 
		Game currentGame = getGame();
		// Get the player that wants to move to the tile
		Player currentPlayer = currentGame.getCurrentPlayer();
		// Set the current tile to this tile
		currentPlayer.setCurrentTile(this);
		
		//If the current player is the last player
		if(currentPlayer.getNumber() == currentGame.numberOfPlayers()){
			
		}
		
	}
}