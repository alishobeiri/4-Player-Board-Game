/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 76 "../../../../../TileO (updated Feb10).ump"
public class RemoveConnectionActionCard extends ActionCard implements Serializable
{

  /**
	 * 
	 */
	private static final long serialVersionUID = -8619851883729304845L;

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
  
  public void play(Connection aConnection) throws InvalidInputException{
	  Game game = this.getDeck().getGame();
	  
	  //TODO Check this method works
	  game.removeConnection(aConnection);
  }

}