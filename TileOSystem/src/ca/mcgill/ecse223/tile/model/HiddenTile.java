package ca.mcgill.ecse223.tile.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/


import java.util.*;

// line 52 "model.ump"
// line 136 "model.ump"
public class HiddenTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HiddenTile Attributes
  private boolean wasFound;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HiddenTile(boolean aWasVisited, Board aBoard)
  {
    super(aWasVisited, aBoard);
    wasFound = false;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWasFound(boolean aWasFound)
  {
    boolean wasSet = false;
    wasFound = aWasFound;
    wasSet = true;
    return wasSet;
  }

  public boolean getWasFound()
  {
    return wasFound;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "wasFound" + ":" + getWasFound()+ "]"
     + outputString;
  }
}