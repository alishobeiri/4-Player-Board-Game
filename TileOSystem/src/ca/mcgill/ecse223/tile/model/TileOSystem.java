package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/


import java.util.*;

// line 1 "model.ump"
// line 90 "model.ump"
public class TileOSystem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static TileOSystem theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TileOSystem Associations
  private List<User> users;
  private List<Board> boards;
  private List<Deck> decks;
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private TileOSystem()
  {
    users = new ArrayList<User>();
    boards = new ArrayList<Board>();
    decks = new ArrayList<Deck>();
    games = new ArrayList<Game>();
  }

  public static TileOSystem getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new TileOSystem();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }

  public Board getBoard(int index)
  {
    Board aBoard = boards.get(index);
    return aBoard;
  }

  public List<Board> getBoards()
  {
    List<Board> newBoards = Collections.unmodifiableList(boards);
    return newBoards;
  }

  public int numberOfBoards()
  {
    int number = boards.size();
    return number;
  }

  public boolean hasBoards()
  {
    boolean has = boards.size() > 0;
    return has;
  }

  public int indexOfBoard(Board aBoard)
  {
    int index = boards.indexOf(aBoard);
    return index;
  }

  public Deck getDeck(int index)
  {
    Deck aDeck = decks.get(index);
    return aDeck;
  }

  public List<Deck> getDecks()
  {
    List<Deck> newDecks = Collections.unmodifiableList(decks);
    return newDecks;
  }

  public int numberOfDecks()
  {
    int number = decks.size();
    return number;
  }

  public boolean hasDecks()
  {
    boolean has = decks.size() > 0;
    return has;
  }

  public int indexOfDeck(Deck aDeck)
  {
    int index = decks.indexOf(aDeck);
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

  public static int minimumNumberOfUsers()
  {
    return 0;
  }

  public User addUser(String aName)
  {
    return new User(aName, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    TileOSystem existingTileOSystem = aUser.getTileOSystem();
    boolean isNewTileOSystem = existingTileOSystem != null && !this.equals(existingTileOSystem);
    if (isNewTileOSystem)
    {
      aUser.setTileOSystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a tileOSystem
    if (!this.equals(aUser.getTileOSystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfBoards()
  {
    return 0;
  }

  public Board addBoard(String aBoardName, Designer aDesigner)
  {
    return new Board(aBoardName, aDesigner, this);
  }

  public boolean addBoard(Board aBoard)
  {
    boolean wasAdded = false;
    if (boards.contains(aBoard)) { return false; }
    TileOSystem existingTileOSystem = aBoard.getTileOSystem();
    boolean isNewTileOSystem = existingTileOSystem != null && !this.equals(existingTileOSystem);
    if (isNewTileOSystem)
    {
      aBoard.setTileOSystem(this);
    }
    else
    {
      boards.add(aBoard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBoard(Board aBoard)
  {
    boolean wasRemoved = false;
    //Unable to remove aBoard, as it must always have a tileOSystem
    if (!this.equals(aBoard.getTileOSystem()))
    {
      boards.remove(aBoard);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addBoardAt(Board aBoard, int index)
  {  
    boolean wasAdded = false;
    if(addBoard(aBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoards()) { index = numberOfBoards() - 1; }
      boards.remove(aBoard);
      boards.add(index, aBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBoardAt(Board aBoard, int index)
  {
    boolean wasAdded = false;
    if(boards.contains(aBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoards()) { index = numberOfBoards() - 1; }
      boards.remove(aBoard);
      boards.add(index, aBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBoardAt(aBoard, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfDecks()
  {
    return 0;
  }

  public Deck addDeck(Game aGame, Designer aDesigner)
  {
    return new Deck(aGame, aDesigner, this);
  }

  public boolean addDeck(Deck aDeck)
  {
    boolean wasAdded = false;
    if (decks.contains(aDeck)) { return false; }
    TileOSystem existingTileOSystem = aDeck.getTileOSystem();
    boolean isNewTileOSystem = existingTileOSystem != null && !this.equals(existingTileOSystem);
    if (isNewTileOSystem)
    {
      aDeck.setTileOSystem(this);
    }
    else
    {
      decks.add(aDeck);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDeck(Deck aDeck)
  {
    boolean wasRemoved = false;
    //Unable to remove aDeck, as it must always have a tileOSystem
    if (!this.equals(aDeck.getTileOSystem()))
    {
      decks.remove(aDeck);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addDeckAt(Deck aDeck, int index)
  {  
    boolean wasAdded = false;
    if(addDeck(aDeck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDecks()) { index = numberOfDecks() - 1; }
      decks.remove(aDeck);
      decks.add(index, aDeck);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDeckAt(Deck aDeck, int index)
  {
    boolean wasAdded = false;
    if(decks.contains(aDeck))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDecks()) { index = numberOfDecks() - 1; }
      decks.remove(aDeck);
      decks.add(index, aDeck);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDeckAt(aDeck, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfGames()
  {
    return 0;
  }

  public Game addGame(int aCurrentTurn, int aSpareConnectors, int aCurrentPlayer, int aNumberOfPlayers, Board aBoard, Dice aDice, Deck aDeck)
  {
    return new Game(aCurrentTurn, aSpareConnectors, aCurrentPlayer, aNumberOfPlayers, this, aBoard, aDice, aDeck);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    TileOSystem existingTileOSystem = aGame.getTileOSystem();
    boolean isNewTileOSystem = existingTileOSystem != null && !this.equals(existingTileOSystem);
    if (isNewTileOSystem)
    {
      aGame.setTileOSystem(this);
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
    //Unable to remove aGame, as it must always have a tileOSystem
    if (!this.equals(aGame.getTileOSystem()))
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

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (boards.size() > 0)
    {
      Board aBoard = boards.get(boards.size() - 1);
      aBoard.delete();
      boards.remove(aBoard);
    }
    
    while (decks.size() > 0)
    {
      Deck aDeck = decks.get(decks.size() - 1);
      aDeck.delete();
      decks.remove(aDeck);
    }
    
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
  }

}