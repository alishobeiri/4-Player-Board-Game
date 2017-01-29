package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/


import java.util.*;

// line 82 "model.ump"
// line 164 "model.ump"
public class Deck
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Associations
  private Game game;
  private Designer designer;
  private TileOSystem tileOSystem;
  private List<ActionCard> actionCards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(Game aGame, Designer aDesigner, TileOSystem aTileOSystem)
  {
    if (aGame == null || aGame.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aGame");
    }
    game = aGame;
    if (aDesigner == null || aDesigner.getDeck() != null)
    {
      throw new RuntimeException("Unable to create Deck due to aDesigner");
    }
    designer = aDesigner;
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create deck due to tileOSystem");
    }
    actionCards = new ArrayList<ActionCard>();
  }

  public Deck(int aCurrentTurnForGame, int aSpareConnectorsForGame, int aCurrentPlayerForGame, int aNumberOfPlayersForGame, TileOSystem aTileOSystemForGame, Board aBoardForGame, Dice aDiceForGame, String aNameForDesigner, TileOSystem aTileOSystemForDesigner, Board aBoardForDesigner, TileOSystem aTileOSystem)
  {
    game = new Game(aCurrentTurnForGame, aSpareConnectorsForGame, aCurrentPlayerForGame, aNumberOfPlayersForGame, aTileOSystemForGame, aBoardForGame, aDiceForGame, this);
    designer = new Designer(aNameForDesigner, aTileOSystemForDesigner, aBoardForDesigner, this);
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create deck due to tileOSystem");
    }
    actionCards = new ArrayList<ActionCard>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game getGame()
  {
    return game;
  }

  public Designer getDesigner()
  {
    return designer;
  }

  public TileOSystem getTileOSystem()
  {
    return tileOSystem;
  }

  public ActionCard getActionCard(int index)
  {
    ActionCard aActionCard = actionCards.get(index);
    return aActionCard;
  }

  public List<ActionCard> getActionCards()
  {
    List<ActionCard> newActionCards = Collections.unmodifiableList(actionCards);
    return newActionCards;
  }

  public int numberOfActionCards()
  {
    int number = actionCards.size();
    return number;
  }

  public boolean hasActionCards()
  {
    boolean has = actionCards.size() > 0;
    return has;
  }

  public int indexOfActionCard(ActionCard aActionCard)
  {
    int index = actionCards.indexOf(aActionCard);
    return index;
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
      existingTileOSystem.removeDeck(this);
    }
    tileOSystem.addDeck(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfActionCardsValid()
  {
    boolean isValid = numberOfActionCards() >= minimumNumberOfActionCards() && numberOfActionCards() <= maximumNumberOfActionCards();
    return isValid;
  }

  public static int requiredNumberOfActionCards()
  {
    return 32;
  }

  public static int minimumNumberOfActionCards()
  {
    return 32;
  }

  public static int maximumNumberOfActionCards()
  {
    return 32;
  }

  public ActionCard addActionCard()
  {
    if (numberOfActionCards() >= maximumNumberOfActionCards())
    {
      return null;
    }
    else
    {
      return new ActionCard(this);
    }
  }

  public boolean addActionCard(ActionCard aActionCard)
  {
    boolean wasAdded = false;
    if (actionCards.contains(aActionCard)) { return false; }
    if (numberOfActionCards() >= maximumNumberOfActionCards())
    {
      return wasAdded;
    }

    Deck existingDeck = aActionCard.getDeck();
    boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);

    if (isNewDeck && existingDeck.numberOfActionCards() <= minimumNumberOfActionCards())
    {
      return wasAdded;
    }

    if (isNewDeck)
    {
      aActionCard.setDeck(this);
    }
    else
    {
      actionCards.add(aActionCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeActionCard(ActionCard aActionCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aActionCard, as it must always have a deck
    if (this.equals(aActionCard.getDeck()))
    {
      return wasRemoved;
    }

    //deck already at minimum (32)
    if (numberOfActionCards() <= minimumNumberOfActionCards())
    {
      return wasRemoved;
    }
    actionCards.remove(aActionCard);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
    Designer existingDesigner = designer;
    designer = null;
    if (existingDesigner != null)
    {
      existingDesigner.delete();
    }
    TileOSystem placeholderTileOSystem = tileOSystem;
    this.tileOSystem = null;
    placeholderTileOSystem.removeDeck(this);
    while (actionCards.size() > 0)
    {
      ActionCard aActionCard = actionCards.get(actionCards.size() - 1);
      aActionCard.delete();
      actionCards.remove(aActionCard);
    }
    
  }

}