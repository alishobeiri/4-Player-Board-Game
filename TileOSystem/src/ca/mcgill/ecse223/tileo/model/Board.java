/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 39 "../../../../../model.ump"
// line 123 "../../../../../model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private String boardName;

  //Board Associations
  private List<Connection> connections;
  private List<Game> games;
  private List<Tile> tiles;
  private Designer designer;
  private TileOSystem tileOSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(String aBoardName, Designer aDesigner, TileOSystem aTileOSystem)
  {
    boardName = aBoardName;
    connections = new ArrayList<Connection>();
    games = new ArrayList<Game>();
    tiles = new ArrayList<Tile>();
    if (aDesigner == null || aDesigner.getBoard() != null)
    {
      throw new RuntimeException("Unable to create Board due to aDesigner");
    }
    designer = aDesigner;
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create board due to tileOSystem");
    }
  }

  public Board(String aBoardName, String aNameForDesigner, TileOSystem aTileOSystemForDesigner, Deck aDeckForDesigner, TileOSystem aTileOSystem)
  {
    boardName = aBoardName;
    connections = new ArrayList<Connection>();
    games = new ArrayList<Game>();
    tiles = new ArrayList<Tile>();
    designer = new Designer(aNameForDesigner, aTileOSystemForDesigner, this, aDeckForDesigner);
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create board due to tileOSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBoardName(String aBoardName)
  {
    boolean wasSet = false;
    boardName = aBoardName;
    wasSet = true;
    return wasSet;
  }

  public String getBoardName()
  {
    return boardName;
  }

  public Connection getConnection(int index)
  {
    Connection aConnection = connections.get(index);
    return aConnection;
  }

  public List<Connection> getConnections()
  {
    List<Connection> newConnections = Collections.unmodifiableList(connections);
    return newConnections;
  }

  public int numberOfConnections()
  {
    int number = connections.size();
    return number;
  }

  public boolean hasConnections()
  {
    boolean has = connections.size() > 0;
    return has;
  }

  public int indexOfConnection(Connection aConnection)
  {
    int index = connections.indexOf(aConnection);
    return index;
  }

  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }

  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }

  public Designer getDesigner()
  {
    return designer;
  }

  public TileOSystem getTileOSystem()
  {
    return tileOSystem;
  }

  public static int minimumNumberOfConnections()
  {
    return 0;
  }

  public Connection addConnection()
  {
    return new Connection(this);
  }

  public boolean addConnection(Connection aConnection)
  {
    boolean wasAdded = false;
    if (connections.contains(aConnection)) { return false; }
    Board existingBoard = aConnection.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aConnection.setBoard(this);
    }
    else
    {
      connections.add(aConnection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConnection(Connection aConnection)
  {
    boolean wasRemoved = false;
    //Unable to remove aConnection, as it must always have a board
    if (!this.equals(aConnection.getBoard()))
    {
      connections.remove(aConnection);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addConnectionAt(Connection aConnection, int index)
  {  
    boolean wasAdded = false;
    if(addConnection(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectionAt(Connection aConnection, int index)
  {
    boolean wasAdded = false;
    if(connections.contains(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectionAt(aConnection, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfGames()
  {
    return 0;
  }

  public Game addGame(int aCurrentTurn, int aSpareConnectors, int aCurrentPlayer, int aNumberOfPlayers, TileOSystem aTileOSystem, Dice aDice, Deck aDeck)
  {
    return new Game(aCurrentTurn, aSpareConnectors, aCurrentPlayer, aNumberOfPlayers, aTileOSystem, this, aDice, aDeck);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Board existingBoard = aGame.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aGame.setBoard(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a board
    if (!this.equals(aGame.getBoard()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public boolean isNumberOfTilesValid()
  {
    boolean isValid = numberOfTiles() >= minimumNumberOfTiles();
    return isValid;
  }

  public static int minimumNumberOfTiles()
  {
    return 2;
  }

  public Tile addTile(boolean aWasVisited)
  {
    Tile aNewTile = new Tile(aWasVisited, this);
    return aNewTile;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Board existingBoard = aTile.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);

    if (isNewBoard && existingBoard.numberOfTiles() <= minimumNumberOfTiles())
    {
      return wasAdded;
    }
    if (isNewBoard)
    {
      aTile.setBoard(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a board
    if (this.equals(aTile.getBoard()))
    {
      return wasRemoved;
    }

    //board already at minimum (2)
    if (numberOfTiles() <= minimumNumberOfTiles())
    {
      return wasRemoved;
    }

    tiles.remove(aTile);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public boolean setTileOSystem(TileOSystem aTileOSystem)
  {
    boolean wasSet = false;
    if (aTileOSystem == null)
    {
      return wasSet;
    }

    TileOSystem existingTileOSystem = tileOSystem;
    tileOSystem = aTileOSystem;
    if (existingTileOSystem != null && !existingTileOSystem.equals(aTileOSystem))
    {
      existingTileOSystem.removeBoard(this);
    }
    tileOSystem.addBoard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (connections.size() > 0)
    {
      Connection aConnection = connections.get(connections.size() - 1);
      aConnection.delete();
      connections.remove(aConnection);
    }
    
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
    Designer existingDesigner = designer;
    designer = null;
    if (existingDesigner != null)
    {
      existingDesigner.delete();
    }
    TileOSystem placeholderTileOSystem = tileOSystem;
    this.tileOSystem = null;
    placeholderTileOSystem.removeBoard(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "boardName" + ":" + getBoardName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "designer = "+(getDesigner()!=null?Integer.toHexString(System.identityHashCode(getDesigner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tileOSystem = "+(getTileOSystem()!=null?Integer.toHexString(System.identityHashCode(getTileOSystem())):"null")
     + outputString;
  }
}