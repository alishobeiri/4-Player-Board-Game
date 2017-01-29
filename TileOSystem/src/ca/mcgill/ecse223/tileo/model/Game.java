/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 30 "../../../../../model.ump"
// line 117 "../../../../../model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int currentTurn;
  private int spareConnectors;
  private int currentPlayer;
  private int numberOfPlayers;

  //Game Associations
  private List<Player> players;
  private TileOSystem tileOSystem;
  private Board board;
  private Dice dice;
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aCurrentTurn, int aSpareConnectors, int aCurrentPlayer, int aNumberOfPlayers, TileOSystem aTileOSystem, Board aBoard, Dice aDice, Deck aDeck)
  {
    currentTurn = aCurrentTurn;
    spareConnectors = aSpareConnectors;
    currentPlayer = aCurrentPlayer;
    numberOfPlayers = aNumberOfPlayers;
    players = new ArrayList<Player>();
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create game due to tileOSystem");
    }
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create game due to board");
    }
    if (aDice == null || aDice.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDice");
    }
    dice = aDice;
    if (aDeck == null || aDeck.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDeck");
    }
    deck = aDeck;
  }

  public Game(int aCurrentTurn, int aSpareConnectors, int aCurrentPlayer, int aNumberOfPlayers, TileOSystem aTileOSystem, Board aBoard, Designer aDesignerForDeck, TileOSystem aTileOSystemForDeck)
  {
    currentTurn = aCurrentTurn;
    spareConnectors = aSpareConnectors;
    currentPlayer = aCurrentPlayer;
    numberOfPlayers = aNumberOfPlayers;
    players = new ArrayList<Player>();
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create game due to tileOSystem");
    }
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create game due to board");
    }
    dice = new Dice(this);
    deck = new Deck(this, aDesignerForDeck, aTileOSystemForDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentTurn(int aCurrentTurn)
  {
    boolean wasSet = false;
    currentTurn = aCurrentTurn;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpareConnectors(int aSpareConnectors)
  {
    boolean wasSet = false;
    spareConnectors = aSpareConnectors;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentPlayer(int aCurrentPlayer)
  {
    boolean wasSet = false;
    currentPlayer = aCurrentPlayer;
    wasSet = true;
    return wasSet;
  }

  public int getCurrentTurn()
  {
    return currentTurn;
  }

  public int getSpareConnectors()
  {
    return spareConnectors;
  }

  public int getCurrentPlayer()
  {
    return currentPlayer;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }

  public TileOSystem getTileOSystem()
  {
    return tileOSystem;
  }

  public Board getBoard()
  {
    return board;
  }

  public Dice getDice()
  {
    return dice;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public boolean isNumberOfPlayersValid()
  {
    boolean isValid = numberOfPlayers() >= minimumNumberOfPlayers() && numberOfPlayers() <= maximumNumberOfPlayers();
    return isValid;
  }

  public static int minimumNumberOfPlayers()
  {
    return 2;
  }

  public static int maximumNumberOfPlayers()
  {
    return 4;
  }

  public Player addPlayer(String aName, TileOSystem aTileOSystem, String aColor, Tile aPosition)
  {
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return null;
    }
    else
    {
      return new Player(aName, aTileOSystem, aColor, aPosition, this);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return wasAdded;
    }

    Game existingGame = aPlayer.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aPlayer.setGame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a game
    if (this.equals(aPlayer.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }
    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
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
      existingTileOSystem.removeGame(this);
    }
    tileOSystem.addGame(this);
    wasSet = true;
    return wasSet;
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
      existingBoard.removeGame(this);
    }
    board.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    TileOSystem placeholderTileOSystem = tileOSystem;
    this.tileOSystem = null;
    placeholderTileOSystem.removeGame(this);
    Board placeholderBoard = board;
    this.board = null;
    placeholderBoard.removeGame(this);
    Dice existingDice = dice;
    dice = null;
    if (existingDice != null)
    {
      existingDice.delete();
    }
    Deck existingDeck = deck;
    deck = null;
    if (existingDeck != null)
    {
      existingDeck.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "currentTurn" + ":" + getCurrentTurn()+ "," +
            "spareConnectors" + ":" + getSpareConnectors()+ "," +
            "currentPlayer" + ":" + getCurrentPlayer()+ "," +
            "numberOfPlayers" + ":" + getNumberOfPlayers()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tileOSystem = "+(getTileOSystem()!=null?Integer.toHexString(System.identityHashCode(getTileOSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "dice = "+(getDice()!=null?Integer.toHexString(System.identityHashCode(getDice())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null")
     + outputString;
  }
}