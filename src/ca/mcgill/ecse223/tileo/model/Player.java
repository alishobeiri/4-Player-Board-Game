/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 22 "../../../../../TileO (updated Feb10).ump"
public class Player implements Serializable
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1273309447595594009L;

//------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Player> playersByNumber = new HashMap<Integer, Player>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int number;
  private int turnsUntilActive;

  //Player State Machines
  public enum Color { RED, BLUE, GREEN, YELLOW }
  private Color color;

  //Player Associations
  private Tile startingTile;
  private Tile currentTile;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aNumber, Game aGame)
  {
    turnsUntilActive = 0;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
    setColor(Color.RED);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      playersByNumber.remove(anOldNumber);
    }
    playersByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public static Player getWithNumber(int aNumber)
  {
    return playersByNumber.get(aNumber);
  }

  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getColorFullName()
  {
    String answer = color.toString();
    return answer;
  }

  public Color getColor()
  {
    return color;
  }

  public boolean setColor(Color aColor)
  {
    color = aColor;
    return true;
  }

  public Tile getStartingTile()
  {
    return startingTile;
  }

  public boolean hasStartingTile()
  {
    boolean has = startingTile != null;
    return has;
  }

  public Tile getCurrentTile()
  {
    return currentTile;
  }

  public boolean hasCurrentTile()
  {
    boolean has = currentTile != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean setStartingTile(Tile aNewStartingTile)
  {
    boolean wasSet = false;
    startingTile = aNewStartingTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentTile(Tile aNewCurrentTile)
  {
    boolean wasSet = false;
    currentTile = aNewCurrentTile;
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
    playersByNumber.remove(getNumber());
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startingTile = "+(getStartingTile()!=null?Integer.toHexString(System.identityHashCode(getStartingTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentTile = "+(getCurrentTile()!=null?Integer.toHexString(System.identityHashCode(getCurrentTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null")
     + outputString;
  }

	public ArrayList<Tile> generateMoves(Tile origin, int numberOfMoves){
		Deque<Tile> toVisit[] = new ArrayDeque[7];
        Set<Tile> history[] = new HashSet[7];
		ArrayList<Tile> visited[] = new ArrayList[7];
		visited[0] = new ArrayList<>();
		visited[0].add(origin); 
        toVisit[0] = new ArrayDeque<Tile>();
        history[0] = new HashSet<Tile>();
        history[0].add(origin);
		int layer = 0;
		toVisit[0].add(origin);
        while(layer<6){
            history[layer+1] = new HashSet<Tile>();
            visited[layer+1] = new ArrayList<Tile>();
            toVisit[layer+1] = new ArrayDeque<Tile>();
    		while(!toVisit[layer].isEmpty()){
        		Tile current = toVisit[layer].poll();
        		for(Tile t : getNeighbours(current)){
                    if(!history[layer].contains(t)){
                        visited[layer+1].add(t);
                        toVisit[layer+1].add(t);
                        history[layer+1].add(t);
                    }

    	        }
    	    }
            layer++;
        }
    	return visited[numberOfMoves];
    }
	
	public List<Tile> getNeighbours(Tile a){
		List<Tile> neighbours = new ArrayList<>();

		for(Connection c : a.getConnections()){
			for(Tile t : c.getTiles()){
				if(!(t.getX() == a.getX() && t.getY() == a.getY())){
					neighbours.add(t);
				}
			}
		}
		return neighbours;
	}
}