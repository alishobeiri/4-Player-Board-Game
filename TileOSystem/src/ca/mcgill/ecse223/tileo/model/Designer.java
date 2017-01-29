/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 17 "../../../../../model.ump"
// line 106 "../../../../../model.ump"
public class Designer extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Designer Associations
  private Board board;
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Designer(String aName, TileOSystem aTileOSystem, Board aBoard, Deck aDeck)
  {
    super(aName, aTileOSystem);
    if (aBoard == null || aBoard.getDesigner() != null)
    {
      throw new RuntimeException("Unable to create Designer due to aBoard");
    }
    board = aBoard;
    if (aDeck == null || aDeck.getDesigner() != null)
    {
      throw new RuntimeException("Unable to create Designer due to aDeck");
    }
    deck = aDeck;
  }

  public Designer(String aName, TileOSystem aTileOSystem, String aBoardNameForBoard, TileOSystem aTileOSystemForBoard, Game aGameForDeck, TileOSystem aTileOSystemForDeck)
  {
    super(aName, aTileOSystem);
    board = new Board(aBoardNameForBoard, this, aTileOSystemForBoard);
    deck = new Deck(aGameForDeck, this, aTileOSystemForDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Board getBoard()
  {
    return board;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public void delete()
  {
    Board existingBoard = board;
    board = null;
    if (existingBoard != null)
    {
      existingBoard.delete();
    }
    Deck existingDeck = deck;
    deck = null;
    if (existingDeck != null)
    {
      existingDeck.delete();
    }
    super.delete();
  }

}