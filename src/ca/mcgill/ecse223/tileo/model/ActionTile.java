/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 39 "../../../../../TileO (updated Feb10).ump"
public class ActionTile extends Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1595118367869312744L;
	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ActionTile Attributes
	private int inactivityPeriod;
	private int turnsUntilActive;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod) {
		super(aX, aY, aGame);
		inactivityPeriod = aInactivityPeriod;
		turnsUntilActive = 0;
		setStatus(Status.Active);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setTurnsUntilActive(int aTurnsUntilActive) {
		boolean wasSet = false;
		turnsUntilActive = aTurnsUntilActive;
		wasSet = true;
		return wasSet;
	}

	public int getInactivityPeriod() {
		return inactivityPeriod;
	}

	public int getTurnsUntilActive() {
		return turnsUntilActive;
	}

	public void delete() {
		super.delete();
	}

	public String toString() {
		String outputString = "";
		return super.toString() + "[" + "inactivityPeriod" + ":" + getInactivityPeriod() + "," + "turnsUntilActive"
				+ ":" + getTurnsUntilActive() + "]" + outputString;
	}

	public void land() {
		// Get the game that this tile is a part of
		Game currentGame = this.getGame();
		// Get the player that wants to move to the tile
		Player currentPlayer = currentGame.getCurrentPlayer();
		// Set the current tile to this tile
		currentPlayer.setCurrentTile(this);
		
		currentPlayer.takeTurn();

		this.setHasBeenVisited(true);
		
		Deck deck = currentGame.getDeck();
		ActionCard currentCard = deck.getCurrentCard();
		
		currentCard.changeGameModeToActionCard();
		
		deactivate();
	}
	
	 //Test State Machines
	  public enum Status { Active, Inactive }
	  private Status status;
	  
	  //------------------------
	  // INTERFACE
	  //------------------------

	  public String getStatusFullName()
	  {
	    String answer = status.toString();
	    return answer;
	  }

	  public Status getStatus()
	  {
	    return status;
	  }

	  public boolean deactivate()
	  {
	    boolean wasEventProcessed = false;
	    
	    Status aStatus = status;
	    switch (aStatus)
	    {
	      case Active:
	        if (getInactivityPeriod()>0)
	        {
	        // line 5 "ActionTile.ump"
	          setTurnsUntilActive(getInactivityPeriod() + 1);
	          setStatus(Status.Inactive);
	          wasEventProcessed = true;
	          break;
	        }
	        break;
	      default:
	        // Other states do respond to this event
	    }

	    return wasEventProcessed;
	  }

	  public boolean takeTurn()
	  {
	    boolean wasEventProcessed = false;
	    
	    Status aStatus = status;
	    switch (aStatus)
	    {
	      case Inactive:
	        if (getTurnsUntilActive()>1)
	        {
	        // line 12 "ActionTile.ump"
	          setTurnsUntilActive(getTurnsUntilActive() - 1);
	          setStatus(Status.Inactive);
	          wasEventProcessed = true;
	          break;
	        }
	        if (getTurnsUntilActive()<=1)
	        {
	        // line 16 "ActionTile.ump"
	          setTurnsUntilActive(0);
	          setStatus(Status.Active);
	          wasEventProcessed = true;
	          break;
	        }
	        break;
	      default:
	        // Other states do respond to this event
	    }

	    return wasEventProcessed;
	  }

	  private void setStatus(Status aStatus)
	  {
	    status = aStatus;
	  }
}