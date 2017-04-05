/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 69 "../../../../../TileOPersistence.ump"
// line 344 "../../../../../TileO(updatedMar22).ump"
public class RollDieActionCard extends ActionCard
{

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


  /**
   * Angel
   */
  // line 350 "../../../../../TileO(updatedMar22).ump"
   public List<Tile> play(){
    List<Tile> tiles = new ArrayList<Tile>();
		Game game = this.getDeck().getGame();
		game.setMode(Game.Mode.GAME_ROLLDIEACTIONCARD);

		tiles = game.rollDie();

		return tiles;
  }

  // line 360 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
		Game game = deck.getGame();
		game.setMode(Mode.GAME_ROLLDIEACTIONCARD);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 72 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 7460499981901527552L ;

  
}