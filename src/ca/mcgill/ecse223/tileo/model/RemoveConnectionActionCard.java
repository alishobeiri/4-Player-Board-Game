/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 63 "../../../../../TileOPersistence.ump"
// line 386 "../../../../../TileO(updatedMar22).ump"
public class RemoveConnectionActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RemoveConnectionActionCard(String aInstructions, Deck aDeck)
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
  // line 392 "../../../../../TileO(updatedMar22).ump"
   public void play(Connection aConnection) throws InvalidInputException{
    Game game = this.getDeck().getGame();
		game.deleteConnection(aConnection);
		game.setMode(Mode.GAME);
  }

  // line 398 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
		Game game = deck.getGame();
		game.setMode(Mode.GAME_REMOVECONNECTIONACTIONCARD);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 66 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -8619851883729304845L ;

  
}