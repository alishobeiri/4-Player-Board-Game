/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 21 "../../../../../TileOPersistence.ump"
// line 366 "../../../../../TileO(updatedMar22).ump"
public class ConnectTilesActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConnectTilesActionCard(String aInstructions, Deck aDeck)
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
  // line 372 "../../../../../TileO(updatedMar22).ump"
   public void play(Tile tile1, Tile tile2) throws InvalidInputException{
    Game game = this.getDeck().getGame();

		game.connectTiles(tile1, tile2);
		game.setMode(Mode.GAME);
		System.out.println("Mode changed");
  }

  // line 380 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
	  Game game = deck.getGame();
	  game.setMode(Mode.GAME_CONNECTTILESACTIONCARD);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 24 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -629288172349309738L ;

  
}