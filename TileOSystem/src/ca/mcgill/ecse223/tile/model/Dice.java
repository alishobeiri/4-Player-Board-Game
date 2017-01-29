package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/



// line 63 "model.ump"
// line 147 "model.ump"
public class Dice
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Dice Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Dice(Game aGame)
  {
    if (aGame == null || aGame.getDice() != null)
    {
      throw new RuntimeException("Unable to create Dice due to aGame");
    }
    game = aGame;
  }

  public Dice(int aCurrentTurnForGame, int aSpareConnectorsForGame, int aCurrentPlayerForGame, int aNumberOfPlayersForGame, TileOSystem aTileOSystemForGame, Board aBoardForGame, Deck aDeckForGame)
  {
    game = new Game(aCurrentTurnForGame, aSpareConnectorsForGame, aCurrentPlayerForGame, aNumberOfPlayersForGame, aTileOSystemForGame, aBoardForGame, this, aDeckForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

}