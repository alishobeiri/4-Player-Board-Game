/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 420 "../../../../../TileO(updatedMar22).ump"
public class ChooseMoveActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ChooseMoveActionCard(String aInstructions, Deck aDeck)
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
   * Added play method
   */
  // line 425 "../../../../../TileO(updatedMar22).ump"
   public List<Tile> play(int n){
    List<Tile> tiles = new ArrayList<Tile>();
	  Game game = this.getDeck().getGame();
	  game.setMode(Game.Mode.GAME_CHOOSEMOVEACTIONCARD);
	  
	  //TODO Check this method works
	  tiles = game.getCurrentPlayer().generateMoves(n);
	  
	  return tiles;
  }

  // line 436 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
	  Game game = deck.getGame();
	  game.setMode(Game.Mode.GAME_CHOOSEMOVEACTIONCARD);
  }

}