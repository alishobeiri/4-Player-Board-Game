/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 75 "../../../../../TileOPersistence.ump"
// line 404 "../../../../../TileO(updatedMar22).ump"
public class TeleportActionCard extends ActionCard
{

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

  public void delete()
  {
    super.delete();
  }


  /**
   * Angel
   */
  // line 410 "../../../../../TileO(updatedMar22).ump"
   public void play(Tile tile) throws InvalidInputException{
    tile.land();
  }

  // line 414 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
		Game game = deck.getGame();
		game.setMode(Mode.GAME_TELEPORTACTIONCARD);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 78 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 6478806027360279851L ;

  
}