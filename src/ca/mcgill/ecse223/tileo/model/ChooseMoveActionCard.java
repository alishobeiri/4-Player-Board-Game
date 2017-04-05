/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 68 "../../../../../TileO (updated Feb10).ump"
public class ChooseMoveActionCard extends ActionCard implements Serializable {

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

  public ChooseMoveActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------
  public void changeGameModeToActionCard(){
	  Deck deck = this.getDeck();
	  Game game = deck.getGame();
	  game.setMode(Game.Mode.GAME_CHOOSEMOVEACTIONCARD);
  }

  public void delete()
  {
    super.delete();
  }
  
  //Added play method
  public List<Tile> play(int n){
	  List<Tile> tiles = new ArrayList<Tile>();
	  Game game = this.getDeck().getGame();
	  game.setMode(Game.Mode.GAME_CHOOSEMOVEACTIONCARD);
	  
	  //TODO Check this method works
	  tiles = game.getCurrentPlayer().generateMoves(n);
	  
	  return tiles;
  }

}