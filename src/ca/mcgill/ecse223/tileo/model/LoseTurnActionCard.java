/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 45 "../../../../../TileOPersistence.ump"
// line 480 "../../../../../TileO(updatedMar22).ump"
public class LoseTurnActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseTurnActionCard(String aInstructions, Deck aDeck)
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

  // line 484 "../../../../../TileO(updatedMar22).ump"
   public void changeGameModeToActionCard(){
    Deck deck = this.getDeck();
	  Game game = deck.getGame();
	  game.setMode(Mode.GAME_LOSETURNACTIONCARD);
  }

  // line 490 "../../../../../TileO(updatedMar22).ump"
   public void play(){
    Game game = this.getDeck().getGame();
	  Player player = game.getCurrentPlayer();
	  player.loseTurns(1);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 48 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = -6738972650323824494L ;

  
}