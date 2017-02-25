/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 80 "../../../../../TileO (updated Feb10).ump"
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
  
  //Angel
  public void play(Tile tile) throws InvalidInputException{ 
	  //TODO Check if methods work.
	  tile.land();
  }

}