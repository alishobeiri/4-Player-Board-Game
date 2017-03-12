/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.*;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.view.BoardPanel;

// line 3 "../../../../../PlayModeController.ump"
public class PlayModeController
{
	
	//Added variables
	
	Game game;
	
	//End of added variables
	
	
	
	
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayModeController State Machines
  public enum Mode { Idle, RollDie, Move, GameOver, Action, RollDieAgain, Teleport, AddConnection, RemoveConnection, LoseTurn }
  private Mode mode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayModeController()
  {
    setMode(Mode.Idle);
    game=TileOApplication.getCurrentGame();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getModeFullName()
  {
    String answer = mode.toString();
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Idle:
        // line 17 "../../../../../PlayModeController.ump"
        doStartGame();
        setMode(Mode.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean dieRolled()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case RollDie:
        exitMode();
        // line 27 "../../../../../PlayModeController.ump"
        doRollDie();
        setMode(Mode.Move);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean land(Tile aTile)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Move:
        if (isNormalTile(aTile))
        {
        // line 34 "../../../../../PlayModeController.ump"
          doLand(aTile);
          setMode(Mode.RollDie);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(aTile))
        {
        // line 38 "../../../../../PlayModeController.ump"
          // Here the player is not set to the next one
          doLand(aTile);
          setMode(Mode.Action);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(aTile))
        {
        // line 43 "../../../../../PlayModeController.ump"
          doLand(aTile);
          setMode(Mode.GameOver);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean getActionCard()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Action:
        if (isCurrentCardRollDie())
        {
        // line 54 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.RollDieAgain);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardAddConnection())
        {
        // line 56 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.AddConnection);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardRemoveConnection())
        {
        // line 58 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.RemoveConnection);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardTeleport())
        {
        // line 60 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.Teleport);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardLoseTurn())
        {
        // line 62 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.LoseTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean dieRolledAgain()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case RollDieAgain:
        setMode(Mode.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean teleport()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Teleport:
        setMode(Mode.Move);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean addConnection(Tile tile1,Tile tile2)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case AddConnection:
        // line 76 "../../../../../PlayModeController.ump"
        doAddConnection(tile1, tile2);
        setMode(Mode.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean removeConnection(Tile tile1,Tile tile2)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case RemoveConnection:
        // line 83 "../../../../../PlayModeController.ump"
        doRemoveConnection(tile1, tile2);
        setMode(Mode.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean changePlayerState()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case LoseTurn:
        setMode(Mode.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitMode()
  {
    switch(mode)
    {
      case RollDie:
        // line 26 "../../../../../PlayModeController.ump"
        enableRollDieButton(false);
        break;
    }
  }

  private void setMode(Mode aMode)
  {
    mode = aMode;

    // entry actions and do activities
    switch(mode)
    {
      case RollDie:
        // line 25 "../../../../../PlayModeController.ump"
        enableRollDieButton(true);
        break;
      case GameOver:
        // line 50 "../../../../../PlayModeController.ump"
        endGame();
        break;
    }
  }

  public void delete()
  {}


  /**
   * ----------------------------------------------------*
   * 
   * Guard methods 				    *
   * 
   * ----------------------------------------------------
   */
  // line 102 "../../../../../PlayModeController.ump"
   public boolean isNormalTile(Tile aTile){
    if(aTile instanceof NormalTile){
      return true;
    }

    return false;
  }

  // line 111 "../../../../../PlayModeController.ump"
   public boolean isActionTile(Tile aTile){
    if(aTile instanceof ActionTile){
      return true;
    }

    return false;
  }

  // line 120 "../../../../../PlayModeController.ump"
   public boolean isWinTile(Tile aTile){
    if(aTile instanceof WinTile){
      return true;
    }

    return false;
  }

  // line 128 "../../../../../PlayModeController.ump"
  public boolean isCurrentCardRollDie(){
    if(game.getDeck().getCurrentCard() instanceof RollDieActionCard){
      return true;
    }
    return false;
  }

  // line 135 "../../../../../PlayModeController.ump"
  public boolean isCurrentCardAddConnection(){
    if(game.getDeck().getCurrentCard() instanceof ConnectTilesActionCard){
      return true;
    }
    return false;
  }

  // line 142 "../../../../../PlayModeController.ump"
  public boolean isCurrentCardRemoveConnection(){
    if(game.getDeck().getCurrentCard() instanceof RemoveConnectionActionCard){
      return true;
    }
    return false;
  }

  // line 149 "../../../../../PlayModeController.ump"
  public boolean isCurrentCardTeleport(){
    if(game.getDeck().getCurrentCard() instanceof TeleportActionCard){
      return true;
    }
    return false;
  }

  // line 156 "../../../../../PlayModeController.ump"
  public boolean isCurrentCardLoseTurn(){
    if(game.getDeck().getCurrentCard() instanceof LoseTurnActionCard){
      return true;
    }
    return false;
  }


  /**
   * ----------------------------------------------------*
   * 
   * Added methods from previous verion of controller  *
   * 
   * ----------------------------------------------------
   */
  // line 169 "../../../../../PlayModeController.ump"
   public ActionCard pickActionCard(Game game){
    game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		ActionCard newCard;
		if(deck.indexOfCard(deck.getCurrentCard()) == Deck.maximumNumberOfCards()){
			deck.shuffle();
			newCard = deck.getCurrentCard();
		}
		else{
			newCard = deck.getCurrentCard();
		}
		if (newCard instanceof RollDieActionCard) {
            game.setMode(Game.Mode.GAME_ROLLDIEACTIONCARD);
        } else if (newCard instanceof ConnectTilesActionCard) {
            game.setMode(Game.Mode.GAME_CONNECTTILESACTIONCARD);
        } else if (newCard instanceof RemoveConnectionActionCard) {
            game.setMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD);
        } else if (newCard instanceof TeleportActionCard) {
            game.setMode(Game.Mode.GAME_TELEPORTACTIONCARD);
        } else if (newCard instanceof LoseTurnActionCard) {
            game.setMode(Game.Mode.GAME_LOSETURNACTIONCARD);
        }
			
		newCard = deck.getCard(deck.indexOfCard(deck.getCurrentCard())+1);
		deck.setCurrentCard(newCard);
		return newCard;
  }


  /**
   * public void action(){
   * Game game = TileOApplication.getCurrentGame();
   * Game.Mode mode = game.getMode();
   * 
   * if(mode == Mode.GAME_CONNECTTILESACTIONCARD){
   * 
   * }
   * 
   * else if(mode == Mode.GAME_LOSETURNACTIONCARD){
   * 
   * }
   * 
   * else if(mode == Mode.GAME_REMOVECONNECTIONACTIONCARD){
   * 
   * }
   * 
   * else if(mode == Mode.GAME_ROLLDIEACTIONCARD){
   * 
   * }
   * else if(mode == Mode.GAME_TELEPORTACTIONCARD){
   * 
   * }
   * 
   */
  // line 221 "../../../../../PlayModeController.ump"
   public List<Tile> playRollDieActionCard() throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		ActionCard card = deck.getCurrentCard();

		// Check if the current card is a Roll Die Card
		if (!(card instanceof RollDieActionCard)) {
			throw new InvalidInputException("The current card is not a Roll Die Action Card");
		}

		RollDieActionCard rollDieCard = (RollDieActionCard) card;

		List<Tile> tiles = new ArrayList<Tile>();
		tiles = rollDieCard.play();

		advanceCurrentCard(deck);

		game.setMode(Game.Mode.GAME);
		
		//TileOApplication.save();

		return tiles;
  }

  // line 245 "../../../../../PlayModeController.ump"
   public void playConnectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();

		// Valid input checks
		if (!(game.getTiles().contains(tile1)) || !(game.getTiles().contains(tile2))) {
			throw new InvalidInputException("That tile is not in the game.");
		}

		if (!areAdjacent(tile1, tile2)) {
			System.out.println("Tile one and two : " +  tile1.getX() + " "+ tile1.getY() + " " + tile2.getX() + " " + tile2.getY());
			throw new InvalidInputException("The tiles are not adjacent.");
		}

		if (!(game.getCurrentConnectionPieces() > 0)) {
			throw new InvalidInputException("There are no spare connection pieces left.");
		}

		Deck deck = game.getDeck();
		ActionCard card = deck.getCurrentCard();

		// Check if current card is Connect Tiles Card
		if (!(card instanceof ConnectTilesActionCard)) {
			throw new InvalidInputException("The current card is not a Connect Tiles Action Card");
		}

		ConnectTilesActionCard connectTilesCard = (ConnectTilesActionCard) card;

		connectTilesCard.play(tile1, tile2);
		
		setNextPlayer();

		advanceCurrentCard(deck);

		game.setMode(Game.Mode.GAME);

		
		//TileOApplication.save();
  }

  // line 287 "../../../../../PlayModeController.ump"
   public void playRemoveConnectionActionCard(Connection aConnection) throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();

		if (!(game.getConnections().contains(aConnection))) {
			throw new InvalidInputException("The connections did not exist.");
		}

		Deck deck = game.getDeck();
		ActionCard card = deck.getCurrentCard();

		if (!(card instanceof RemoveConnectionActionCard)) {
			throw new InvalidInputException("Current card is not a Remove Connection Action Card");
		}

		RemoveConnectionActionCard removeConnectionCard = (RemoveConnectionActionCard) card;

		removeConnectionCard.play(aConnection);

		setNextPlayer();

		advanceCurrentCard(deck);

		game.setMode(Game.Mode.GAME);
		
		//TileOApplication.save();
  }

  // line 315 "../../../../../PlayModeController.ump"
   public void playTeleportActionCard(Tile tile) throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();

		if (!(game.getTiles().contains(tile))) {
			throw new InvalidInputException("The tile is not in the game.");
		}

		Deck deck = game.getDeck();

		ActionCard card = deck.getCurrentCard();

		advanceCurrentCard(deck);

		if (!(card instanceof TeleportActionCard)) {
			throw new InvalidInputException("Current card is not a Teleport Action Card");
		}

		TeleportActionCard teleportCard = (TeleportActionCard) card;

		teleportCard.play(tile);

		tile.setHasBeenVisited(true);

		game.setMode(Game.Mode.GAME);
		
		//TileOApplication.save();
  }


  /**
   * public List<Tile> generateMoves(Tile origin, int numberOfMoves){
   * Deque<Tile> toVisit = new ArrayDeque<>();
   * List<Tile> visited[] = new List[6];
   * visited[0] = new ArrayList<>();
   * visited[0].add(origin);
   * int layer = 0;
   * toVisit.add(origin);
   * while(!toVisit.isEmpty() && layer < 5){
   * Tile current = toVisit.poll();
   * layer++;
   * visited[layer] = new ArrayList<Tile>();
   * for(Tile t : getNeighbours(current)){
   * visited[layer].add(t);
   * toVisit.add(t);
   * }
   * }
   * return visited[numberOfMoves-1];
   * 
   * Helper methods
   * Sets the current player to the next player
   */
  // line 364 "../../../../../PlayModeController.ump"
   public void setNextPlayer(){
	   game=TileOApplication.getCurrentGame();
    List<Player> players = game.getPlayers();
		Player current = game.getCurrentPlayer();
		int index = game.indexOfPlayer(current);
		if(index==game.numberOfPlayers()){
			index=1;
		}
		Player next = players.get(index);
		game.setCurrentPlayer(next);
  }


  /**
   * public List<Tile> getNeighbours(Tile a){
   * List<Tile> neighbours = new ArrayList<>();
   * 
   * for(Connection c : a.getConnections()){
   * for(Tile t : c.getTiles()){
   * if(!(t.getX() == a.getX() && t.getY() == a.getY())){
   * neighbours.add(t);
   * }
   * }
   * }
   * return neighbours;
   * 
   * Checks if two tiles are adjacent (connected) to each other
   */
  // line 389 "../../../../../PlayModeController.ump"
   public boolean areAdjacent(Tile tile1, Tile tile2){
    int xOne = tile1.getX();
		int xTwo = tile1.getX();
		int yOne = tile1.getY();
		int yTwo = tile1.getY();
		boolean adjacent = true;
		if(Math.abs(xOne - xTwo) > 1)
		{
			adjacent = false;
		}
		if(Math.abs(yOne - yTwo) > 1)
		{
			adjacent = false;
		}
		if(Math.abs(yOne - yTwo) == 1 && Math.abs(xOne - xTwo) == 1)
		{
			adjacent = false;
		}
		

		return adjacent;
  }


  /**
   * Sets the current card on the deck to the next one
   */
  // line 413 "../../../../../PlayModeController.ump"
   public void advanceCurrentCard(Deck deck){
    ActionCard card = deck.getCurrentCard();
		int index = deck.indexOfCard(card);
		try{
			ActionCard nextCard = deck.getCard(index+1);
			deck.setCurrentCard(nextCard);
		}catch(IndexOutOfBoundsException e){
			deck.shuffle();
			index=0;
			ActionCard nextCard = deck.getCard(index);
			deck.setCurrentCard(nextCard);
		}
  }


  /**
   * Thomas
   * Returns a list of possible moves the current player can make based on the
   * number they roll
   */
  // line 428 "../../../../../PlayModeController.ump"
   public List<Tile> doRollDie(){
    Game game = TileOApplication.getCurrentGame();
		List<Tile> tiles = game.rollDie();
		return tiles;
  }


  /**
   * Thomas
   */
  // line 436 "../../../../../PlayModeController.ump"
   public void doStartGame(){
    /* VARIABLES */
	   	Game selectedGame=TileOApplication.getCurrentGame();
		List<Tile> tiles;
		List<Player> players;
		Tile startingTile;
		Deck deck;
		TileO tileo;
		
		// Get the TileO instance
		tileo = TileOApplication.getTileO();
		// Set the game to the selected game
		tileo.setCurrentGame(selectedGame);

		/* GET */
		// Get the deck in the game
		deck = selectedGame.getDeck();
		// Get all tiles in current game
		tiles = selectedGame.getTiles();
		// Get all players in current game
		players = selectedGame.getPlayers();

		/* VALIDATION */
		// Check there are the right number of cards in the deck
		if (deck.numberOfCards() != selectedGame.NumberOfActionCards) {
			showMessage("The deck has the wrong number of Action Cards");
		}
		// Check the game has a specified Win Tile
		if (!selectedGame.hasWinTile()) {
			showMessage("The game does not have a Win Tile");
		}
		if(!selectedGame.hasPlayers()){
			showMessage("The game does not have any added players");
		}

		/* ACTION */
		// Shuffle the deck
		deck.shuffle();
		

		// Set all tiles in selectedGame to unvisited
		for (Tile tile : tiles) {
			tile.setHasBeenVisited(false);
		}

		// Place all players on board, and set their starting tiles to visited
		for (Player player : players) {
			// Check the starting tile has been set for each player
			if (player.hasStartingTile()) {
				startingTile = player.getStartingTile();
				player.setCurrentTile(startingTile);
				startingTile.setHasBeenVisited(true);
				TileOApplication.save();
			} else {
				showMessage("The starting position is not set for a player");
			}
		}

		/* SET */
		// Set the first player as the current player
		selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
		// Set the number of connection pieces to the default value
		selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
		// Set the game mode to GAME
		selectedGame.setMode(Game.Mode.GAME);
  }


  /**
   * Thomas
   */
  // line 503 "../../../../../PlayModeController.ump"
   public void doLand(Tile tile) {
    // Validation check: Make sure tile exists as one of the game tiles
		Game game = tile.getGame();
		List<Tile> tiles = game.getTiles();
		// If the tile is in the list of game tiles
		if(tiles.indexOf(tile)!=-1){
			tile.land();
			//TileOApplication.save();
		}else{
			showMessage("Tile is not part of the game");
		}
  }
   
  public void doAddConnection(Tile tile1, Tile tile2){
	  
  }
  
  public void displayCard(){
	  ActionCard c = TileOApplication.getCurrentGame().getDeck().getCurrentCard();
	  TileOApplication.getGamePage().getDeckPanel().setCardInfo(c);
  }
  
  public void doRemoveConnection(Tile tile1, Tile tile2){
	  
  }
  
  public void enableRollDieButton(boolean flag){
	  TileOApplication.enableRollDieButton(flag);
  }
  
  public void endGame(){
	  
  }
   
  public void showMessage(String s){
	  JOptionPane.showConfirmDialog(null, s);
  }
 

  // line 516 "../../../../../PlayModeController.ump"
   public static  void save(){
    TileOApplication.save();
  }
   
  //NEW METHOD SIGNATURES: 
   
   

}