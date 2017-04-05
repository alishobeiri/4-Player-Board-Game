/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import java.util.*;

// line 92 "../../../../../TileOPersistence.ump"
// line 274 "../../../../../TileO(updatedMar22).ump"
public class WinTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
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


  /**
   * TODO
   */
  // line 280 "../../../../../TileO(updatedMar22).ump"
   public void land(){
    // Get the game that this tile is a part of
		Game currentGame = this.getGame();
		// Get the player that wants to move to the tile
		Player currentPlayer = currentGame.getCurrentPlayer();
		// Set the current tile to this tile
		currentPlayer.setCurrentTile(this);

		//TODO CHECK THIS METHOD
		currentPlayer.takeTurn();
		
		// Get increase the index of the current player by one
		int nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) + 1;
		// Loop back if it is the last player
		nextPlayerIndex = nextPlayerIndex % currentGame.numberOfPlayers();
		// Get the next player
		Player nextPlayer = currentGame.getPlayer(nextPlayerIndex);
		// Set the next player as the current player
		currentGame.setCurrentPlayer(nextPlayer);

		this.setHasBeenVisited(true);
		
		TileOApplication.getGamePage().refresh();
		TileOApplication.getGamePage().showMessage("You have found the hidden tile and won the game, good job big boy!");
		TileOApplication.getCurrentGame().setMode(Game.Mode.GAME_WON);
		TileOApplication.deleteGame();

		currentGame.setMode(Mode.GAME_WON);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 95 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 5300378506557006889L ;

  
}