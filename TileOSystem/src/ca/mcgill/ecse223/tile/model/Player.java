package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/



// line 20 "model.ump"
// line 109 "model.ump"
public class Player extends User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextIdentity = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String color;

  //Autounique Attributes
  private int identity;

  //Player Associations
  private Tile position;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName, TileOSystem aTileOSystem, String aColor, Tile aPosition, Game aGame)
  {
    super(aName, aTileOSystem);
    color = aColor;
    identity = nextIdentity++;
    boolean didAddPosition = setPosition(aPosition);
    if (!didAddPosition)
    {
      throw new RuntimeException("Unable to create player due to position");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public String getColor()
  {
    return color;
  }

  public int getIdentity()
  {
    return identity;
  }

  public Tile getPosition()
  {
    return position;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean setPosition(Tile aPosition)
  {
    boolean wasSet = false;
    //Must provide position to player
    if (aPosition == null)
    {
      return wasSet;
    }

    //position already at maximum (4)
    if (aPosition.numberOfPlayers() >= Tile.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Tile existingPosition = position;
    position = aPosition;
    if (existingPosition != null && !existingPosition.equals(aPosition))
    {
      boolean didRemove = existingPosition.removePlayer(this);
      if (!didRemove)
      {
        position = existingPosition;
        return wasSet;
      }
    }
    position.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to player
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (4)
    if (aGame.numberOfPlayers() >= Game.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removePlayer(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tile placeholderPosition = position;
    this.position = null;
    placeholderPosition.removePlayer(this);
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "identity" + ":" + getIdentity()+ "," +
            "color" + ":" + getColor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "position = "+(getPosition()!=null?Integer.toHexString(System.identityHashCode(getPosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null")
     + outputString;
  }
}