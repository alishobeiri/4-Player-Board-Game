/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 68 "../../../../../TileO (updated Feb10).ump"
public class RollDieActionCard extends ActionCard implements Serializable
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 7460499981901527552L;

//------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RollDieActionCard(String aInstructions, Deck aDeck)
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
  
  //Added play method
  public List<Tile> play(){
	  List<Tile> tiles = new ArrayList<Tile>();
	  Game game = this.getDeck().getGame();
	  
	  //TODO Check this method works
	  tiles = game.rollDie();
	  
	  return tiles;
  }

}