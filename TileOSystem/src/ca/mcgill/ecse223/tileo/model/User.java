/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 12 "../../../../../model.ump"
// line 101 "../../../../../model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;

  //User Associations
  private TileOSystem tileOSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, TileOSystem aTileOSystem)
  {
    name = aName;
    boolean didAddTileOSystem = setTileOSystem(aTileOSystem);
    if (!didAddTileOSystem)
    {
      throw new RuntimeException("Unable to create user due to tileOSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public TileOSystem getTileOSystem()
  {
    return tileOSystem;
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
      existingTileOSystem.removeUser(this);
    }
    tileOSystem.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TileOSystem placeholderTileOSystem = tileOSystem;
    this.tileOSystem = null;
    placeholderTileOSystem.removeUser(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tileOSystem = "+(getTileOSystem()!=null?Integer.toHexString(System.identityHashCode(getTileOSystem())):"null")
     + outputString;
  }
}