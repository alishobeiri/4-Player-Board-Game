/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 51 "../../../../../TileOPersistence.ump"
// line 243 "../../../../../TileO(updatedMar22).ump"
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

  public void delete()
  {
    super.delete();
  }

  // line 247 "../../../../../TileO(updatedMar22).ump"
   public void land(){
    // Get the game that this tile is a part of
		Game currentGame = this.getGame();
		// Get the player that wants to move to the tile
		Player currentPlayer = currentGame.getCurrentPlayer();
		// Set the current tile to this tile
		currentPlayer.setCurrentTile(this);

		// TODO CHECK THIS METHOD
		currentPlayer.takeTurn();

		// Get increase the index of the current player by one
		/*
		 * int nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) + 1;
		 * // Loop back if it is the last player nextPlayerIndex =
		 * nextPlayerIndex % currentGame.numberOfPlayers(); // Get the next
		 * player Player nextPlayer = currentGame.getPlayer(nextPlayerIndex); //
		 * Set the next player as the current player
		 * currentGame.setCurrentPlayer(nextPlayer);
		 */

		this.setHasBeenVisited(true);

		currentGame.setMode(Mode.GAME);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 54 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -4242199115754805670L ;

  
}