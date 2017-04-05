/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import ca.mcgill.ecse223.tileo.model.*;
import java.util.*;

// line 460 "../../../../../TileO(updatedMar22).ump"
public class ResetActionTilesActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ResetActionTilesActionCard(String aInstructions, Deck aDeck)
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

  // line 465 "../../../../../TileO(updatedMar22).ump"
   public void play(){
    Game game = this.getDeck().getGame();
    List<Tile> tiles = game.getTiles();
	for(Tile t : tiles){
		if(t instanceof ActionTile){
			((ActionTile)t).deactivate();
		}
	}
  }

  // line 475 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    // TODO Auto-generated method stub
  }

}