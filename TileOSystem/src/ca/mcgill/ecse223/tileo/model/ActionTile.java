/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 70 "../../../../../model.ump"
// line 155 "../../../../../model.ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int nextActiveTurn;
  private String typeOfAction;
  private int inactiveTurns;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(boolean aWasVisited, Board aBoard, int aNextActiveTurn, String aTypeOfAction, int aInactiveTurns)
  {
    super(aWasVisited, aBoard);
    nextActiveTurn = aNextActiveTurn;
    typeOfAction = aTypeOfAction;
    inactiveTurns = aInactiveTurns;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNextActiveTurn(int aNextActiveTurn)
  {
    boolean wasSet = false;
    nextActiveTurn = aNextActiveTurn;
    wasSet = true;
    return wasSet;
  }

  public boolean setTypeOfAction(String aTypeOfAction)
  {
    boolean wasSet = false;
    typeOfAction = aTypeOfAction;
    wasSet = true;
    return wasSet;
  }

  public int getNextActiveTurn()
  {
    return nextActiveTurn;
  }

  public String getTypeOfAction()
  {
    return typeOfAction;
  }

  public int getInactiveTurns()
  {
    return inactiveTurns;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "nextActiveTurn" + ":" + getNextActiveTurn()+ "," +
            "typeOfAction" + ":" + getTypeOfAction()+ "," +
            "inactiveTurns" + ":" + getInactiveTurns()+ "]"
     + outputString;
  }
}