package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/


import java.util.*;

// line 58 "model.ump"
// line 141 "model.ump"
public class Connection
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Connection Associations
  private List<Tile> tiles;
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Connection(Board aBoard)
  {
    tiles = new ArrayList<Tile>();
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create connection due to board");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  /**
   * Each connection must have 2 tiles associated with it
   */
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

  public Board getBoard()
  {
    return board;
  }

  public boolean isNumberOfTilesValid()
  {
    boolean isValid = numberOfTiles() >= minimumNumberOfTiles() && numberOfTiles() <= maximumNumberOfTiles();
    return isValid;
  }

  public static int requiredNumberOfTiles()
  {
    return 2;
  }

  public static int minimumNumberOfTiles()
  {
    return 2;
  }

  public static int maximumNumberOfTiles()
  {
    return 2;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (numberOfTiles() >= maximumNumberOfTiles())
    {
      return wasAdded;
    }

    tiles.add(aTile);
    if (aTile.indexOfConnection(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTile.addConnection(this);
      if (!wasAdded)
      {
        tiles.remove(aTile);
      }
    }
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    if (!tiles.contains(aTile))
    {
      return wasRemoved;
    }

    if (numberOfTiles() <= minimumNumberOfTiles())
    {
      return wasRemoved;
    }

    int oldIndex = tiles.indexOf(aTile);
    tiles.remove(oldIndex);
    if (aTile.indexOfConnection(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTile.removeConnection(this);
      if (!wasRemoved)
      {
        tiles.add(oldIndex,aTile);
      }
    }
    return wasRemoved;
  }

  public boolean setBoard(Board aBoard)
  {
    boolean wasSet = false;
    if (aBoard == null)
    {
      return wasSet;
    }

    Board existingBoard = board;
    board = aBoard;
    if (existingBoard != null && !existingBoard.equals(aBoard))
    {
      existingBoard.removeConnection(this);
    }
    board.addConnection(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Tile> copyOfTiles = new ArrayList<Tile>(tiles);
    tiles.clear();
    for(Tile aTile : copyOfTiles)
    {
      aTile.removeConnection(this);
    }
    Board placeholderBoard = board;
    this.board = null;
    placeholderBoard.removeConnection(this);
  }

}