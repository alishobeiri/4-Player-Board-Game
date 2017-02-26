/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 80 "../../../../../TileO (updated Feb10).ump"
public class TeleportActionCard extends ActionCard implements Serializable
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 6478806027360279851L;

//------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TeleportActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------
  public void changeGameModeToActionCard(){
	  Deck deck = this.getDeck();
	  Game game = deck.getGame();
	  game.setMode(Mode.GAME_TELEPORTACTIONCARD);
  }

  public void delete()
  {
    super.delete();
  }
  
  public void play(Tile tile) throws InvalidInputException{
	  
	  //TODO Check if methods work.
	  if(tile instanceof NormalTile){
		  NormalTile normal = (NormalTile) tile;
		  normal.land();
	  }
	  else if(tile instanceof ActionTile){
		  ActionTile action = (ActionTile) tile;
		  action.land();
	  }
	  else if(tile instanceof WinTile){
		  WinTile win = (WinTile) tile;
		  win.land();
	  }
  }

}