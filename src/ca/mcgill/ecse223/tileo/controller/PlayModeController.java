/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.view.*;
import javax.swing.*;

// line 3 "../../../../../PlayModeController.ump"
public class PlayModeController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayModeController State Machines
  public enum Mode { Idle, RollDie, Move, GameOver, Action, AddConnection, RemoveConnection, LoseTurn }
  private Mode mode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayModeController()
  {
    setMode(Mode.Idle);
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
        // line 18 "../../../../../PlayModeController.ump"
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
        // line 29 "../../../../../PlayModeController.ump"
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
          exitMode();
        // line 36 "../../../../../PlayModeController.ump"
          doLand(aTile);
          setMode(Mode.RollDie);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(aTile))
        {
          exitMode();
        // line 40 "../../../../../PlayModeController.ump"
          // Here the player is not set to the next one
          doLand(aTile);
          setMode(Mode.Action);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(aTile))
        {
          exitMode();
        // line 45 "../../../../../PlayModeController.ump"
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

  public boolean skipTurn()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Move:
        exitMode();
        // line 49 "../../../../../PlayModeController.ump"
        setNextPlayer();
        setMode(Mode.RollDie);
        wasEventProcessed = true;
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
          exitMode();
        // line 62 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.RollDie);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardAddConnection())
        {
          exitMode();
        // line 64 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.AddConnection);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardRemoveConnection())
        {
          exitMode();
        // line 66 "../../../../../PlayModeController.ump"
          displayCard();
          setMode(Mode.RemoveConnection);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardTeleport())
        {
          exitMode();
        // line 68 "../../../../../PlayModeController.ump"
          displayCard();
      doTeleport();
          setMode(Mode.Move);
          wasEventProcessed = true;
          break;
        }
        if (isCurrentCardLoseTurn())
        {
          exitMode();
        // line 74 "../../../../../PlayModeController.ump"
          displayCard();
          doLoseTurn();
          setMode(Mode.RollDie);
          wasEventProcessed = true;
          break;
        }
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
        exitMode();
        // line 95 "../../../../../PlayModeController.ump"
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
        exitMode();
        // line 108 "../../../../../PlayModeController.ump"
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
        // line 27 "../../../../../PlayModeController.ump"
        enableRollDieButton(false);
        break;
      case Move:
        // line 51 "../../../../../PlayModeController.ump"
        TileOApplication.getGamePage().enableSkipTurnButton(false);
        break;
      case Action:
        // line 60 "../../../../../PlayModeController.ump"
        enableGetActionCardButton(false);
        break;
      case AddConnection:
        // line 92 "../../../../../PlayModeController.ump"
        TileOApplication.getBoard().setMode(BoardPanel.Mode.GAME);
        break;
      case RemoveConnection:
        // line 105 "../../../../../PlayModeController.ump"
        TileOApplication.getBoard().setMode(BoardPanel.Mode.GAME);
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
        // line 26 "../../../../../PlayModeController.ump"
        enableRollDieButton(true);
        break;
      case GameOver:
        // line 55 "../../../../../PlayModeController.ump"
        endGame();
        break;
      case Action:
        // line 59 "../../../../../PlayModeController.ump"
        enableGetActionCardButton(true);
        break;
      case AddConnection:
        // line 89 "../../../../../PlayModeController.ump"
        TileOApplication.getBoard().setMode(BoardPanel.Mode.ADD_CONNECTION_ACTION_CARD);
        break;
      case RemoveConnection:
        // line 102 "../../../../../PlayModeController.ump"
        TileOApplication.getBoard().setMode(BoardPanel.Mode.REMOVE_CONNECTION_ACTION_CARD);
        break;
    }
  }

  public void delete()
  {}


  /**
   * 
   * 
   * ----------------------------------------------------*
   * 
   * Guard methods 				    *
   * 
   * ----------------------------------------------------
   * line 123 "../../../../../PlayModeController.ump"
   * line 128 "../../../../../PlayModeController.ump"
   */
  // line 131 "../../../../../PlayModeController.ump"
   public boolean isNormalTile(Tile aTile){
    if(aTile instanceof NormalTile){
      return true;
    }

    return false;
  }


  /**
   * 
   * line 132 "../../../../../PlayModeController.ump"
   * line 137 "../../../../../PlayModeController.ump"
   */
  // line 144 "../../../../../PlayModeController.ump"
   public boolean isActionTile(Tile aTile){
    if(aTile instanceof ActionTile){
      return true;
    }

    return false;
  }


  /**
   * 
   * line 141 "../../../../../PlayModeController.ump"
   * line 146 "../../../../../PlayModeController.ump"
   */
  // line 157 "../../../../../PlayModeController.ump"
   public boolean isWinTile(Tile aTile){
    if(aTile instanceof WinTile){
      return true;
    }

    return false;
  }


  /**
   * 
   * line 149 "../../../../../PlayModeController.ump"
   * line 155 "../../../../../PlayModeController.ump"
   */
  // line 170 "../../../../../PlayModeController.ump"
   public boolean isCurrentCardRollDie(){
    if(TileOApplication.getCurrentGame().getDeck().getCurrentCard() instanceof RollDieActionCard){
      return true;
    }
    return false;
  }


  /**
   * 
   * line 156 "../../../../../PlayModeController.ump"
   * line 163 "../../../../../PlayModeController.ump"
   */
  // line 182 "../../../../../PlayModeController.ump"
   public boolean isCurrentCardAddConnection(){
    if(TileOApplication.getCurrentGame().getDeck().getCurrentCard() instanceof ConnectTilesActionCard){
      return true;
    }
    return false;
  }


  /**
   * 
   * line 163 "../../../../../PlayModeController.ump"
   * line 171 "../../../../../PlayModeController.ump"
   */
  // line 194 "../../../../../PlayModeController.ump"
   public boolean isCurrentCardRemoveConnection(){
    if(TileOApplication.getCurrentGame().getDeck().getCurrentCard() instanceof RemoveConnectionActionCard){
      return true;
    }
    return false;
  }


  /**
   * 
   * line 170 "../../../../../PlayModeController.ump"
   * line 179 "../../../../../PlayModeController.ump"
   */
  // line 206 "../../../../../PlayModeController.ump"
   public boolean isCurrentCardTeleport(){
    if(TileOApplication.getCurrentGame().getDeck().getCurrentCard() instanceof TeleportActionCard){
      return true;
    }
    return false;
  }


  /**
   * 
   * line 177 "../../../../../PlayModeController.ump"
   * line 187 "../../../../../PlayModeController.ump"
   */
  // line 218 "../../../../../PlayModeController.ump"
   public boolean isCurrentCardLoseTurn(){
    if(TileOApplication.getCurrentGame().getDeck().getCurrentCard() instanceof LoseTurnActionCard){
      return true;
    }
    return false;
  }


  /**
   * 
   * line 184 "../../../../../PlayModeController.ump"
   * line 195 "../../../../../PlayModeController.ump"
   */
  // line 230 "../../../../../PlayModeController.ump"
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
   * 
   * 
   * 
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
   * line 221 "../../../../../PlayModeController.ump"
   * line 239 "../../../../../PlayModeController.ump"
   * line 252 "../../../../../PlayModeController.ump"
   */
  // line 289 "../../../../../PlayModeController.ump"
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


  /**
   * 
   * 
   * line 245 "../../../../../PlayModeController.ump"
   * line 264 "../../../../../PlayModeController.ump"
   * line 281 "../../../../../PlayModeController.ump"
   */
  // line 320 "../../../../../PlayModeController.ump"
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


  /**
   * 
   * 
   * line 287 "../../../../../PlayModeController.ump"
   * line 304 "../../../../../PlayModeController.ump"
   * line 325 "../../../../../PlayModeController.ump"
   */
  // line 366 "../../../../../PlayModeController.ump"
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


  /**
   * 
   * 
   * line 315 "../../../../../PlayModeController.ump"
   * line 332 "../../../../../PlayModeController.ump"
   * line 357 "../../../../../PlayModeController.ump"
   */
  // line 400 "../../../../../PlayModeController.ump"
   public void playTeleportActionCard() throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();
    Tile tile = game.getCurrentPlayer().getCurrentTile();

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

		tile.setHasBeenVisited(true);

		game.setMode(Game.Mode.GAME);
		
		//TileOApplication.save();
  }

   
   public void playLoseTurnActionCard() throws InvalidInputException{
	    Game game = TileOApplication.getCurrentGame();

			Deck deck = game.getDeck();

			ActionCard card = deck.getCurrentCard();

			advanceCurrentCard(deck);

			if (!(card instanceof LoseTurnActionCard)) {
				throw new InvalidInputException("Current card is not a Lose Turn Action Card");
			}

			LoseTurnActionCard loseTurnCard = (LoseTurnActionCard) card;
			
			loseTurnCard.play();
			System.out.println(game.getCurrentPlayer().getTurnsUntilActive());
      setNextPlayer();
			advanceCurrentCard(deck);

			game.setMode(Game.Mode.GAME);
   }

  /**
   * 
   * 
   * 
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
   * line 364 "../../../../../PlayModeController.ump"
   * line 384 "../../../../../PlayModeController.ump"
   * line 412 "../../../../../PlayModeController.ump"
   */
  // line 455 "../../../../../PlayModeController.ump"
   public void setNextPlayer(){
    Game currentGame = TileOApplication.getCurrentGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		int nextPlayerIndex = currentGame.indexOfPlayer(currentPlayer) + 1;
		// Loop back if it is the last player
		int nextPlayerNumber = nextPlayerIndex % currentGame.numberOfPlayers();
		// Get the next player
		Player nextPlayer = currentGame.getPlayer(nextPlayerNumber);
    while(nextPlayer.getPlayerStatus() == Player.PlayerStatus.Inactive){
      nextPlayer.takeTurn();
      nextPlayerIndex = currentGame.indexOfPlayer(nextPlayer) + 1;
      nextPlayerNumber = nextPlayerIndex % currentGame.numberOfPlayers();
      nextPlayer = currentGame.getPlayer(nextPlayerNumber);
    }
		// Set the next player as the current player
		currentGame.setCurrentPlayer(nextPlayer);
		int num = TileOApplication.getCurrentGame().getCurrentPlayer().getNumber() % 4;
		if(num == 0){
			num = 4;
		}
		TileOApplication.getGamePage().setCurrentPlayerLabel(num);
  }


  /**
   * 
   * 
   * 
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
   * line 389 "../../../../../PlayModeController.ump"
   * line 413 "../../../../../PlayModeController.ump"
   * line 448 "../../../../../PlayModeController.ump"
   */
  // line 493 "../../../../../PlayModeController.ump"
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
   * 
   * 
   * 
   * Sets the current card on the deck to the next one
   * line 413 "../../../../../PlayModeController.ump"
   * line 441 "../../../../../PlayModeController.ump"
   * line 478 "../../../../../PlayModeController.ump"
   */
  // line 525 "../../../../../PlayModeController.ump"
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
   * 
   * 
   * 
   * Thomas
   * Returns a list of possible moves the current player can make based on the
   * number they roll
   * line 428 "../../../../../PlayModeController.ump"
   * line 462 "../../../../../PlayModeController.ump"
   * line 501 "../../../../../PlayModeController.ump"
   */
  // line 550 "../../../../../PlayModeController.ump"
   public void doRollDie(){
    Game game = TileOApplication.getCurrentGame();
	   List<Tile> tiles = game.rollDie();
	   
	   if(tiles.size() == 0 || tiles == null){
		   TileOApplication.getGamePage().showMessage("No possible moves!");
		   //TODO: Add event to sm to allow to go back to ROLL DIE without landing
		   TileOApplication.getGamePage().enableSkipTurnButton(true);
	   }
	   else{
		   ArrayList tilesArray = new ArrayList<Tile>(tiles);
		   GamePage gamePage = TileOApplication.getGamePage();
		   gamePage.setPossibleMoves(tilesArray);
		   
		   BoardPanel board = TileOApplication.getBoard();
		   board.setMode(BoardPanel.Mode.MOVE_PLAYER);
		   board.refreshBoard();
	   }
  }


  /**
   * line 522 "../../../../../PlayModeController.ump"
   */
  // line 571 "../../../../../PlayModeController.ump"
   public void doTeleport(){
    GamePage gamePage = TileOApplication.getGamePage();
    	boolean isValid = true;
    	
    	try{
    		playTeleportActionCard();
    	}
    	catch(InvalidInputException e){
    		isValid = false;
    	}
    	
    	if(isValid){
    		gamePage.setAllTilesToPossible();
    		TileOApplication.getBoard().setMode(BoardPanel.Mode.MOVE_PLAYER);
    	}
  }
  public void doLoseTurn(){
      try{
        playLoseTurnActionCard();
      }catch(InvalidInputException e){
        System.out.println("There was an error");
      }
  }


  /**
   * 
   * 
   * 
   * Thomas
   * line 436 "../../../../../PlayModeController.ump"
   * line 489 "../../../../../PlayModeController.ump"
   * line 535 "../../../../../PlayModeController.ump"
   */
  // line 597 "../../../../../PlayModeController.ump"
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
			return;
		}
		// Check the game has a specified Win Tile
		if (!selectedGame.hasWinTile()) {
			showMessage("The game does not have a Win Tile");
			return;
		}
		if(!selectedGame.hasPlayers()){
			showMessage("The game does not have any added players");
			return;
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
		
		TileOApplication.changeGameMode();
  }

   

  /**
   * 
   * 
   * 
   * Thomas
   * line 503 "../../../../../PlayModeController.ump"
   * line 566 "../../../../../PlayModeController.ump"
   * line 614 "../../../../../PlayModeController.ump"
   */
  // line 678 "../../../../../PlayModeController.ump"
   public void doLand(Tile tile){
    tile.land();
			
			BoardPanel board = TileOApplication.getBoard();
			board.movePlayer(tile);
			
			board.setMode(BoardPanel.Mode.GAME);	
			board.resetTileColor();
			board.refreshBoard();
			
			if(!(tile instanceof ActionTile)){
				System.out.println("Player set to next");
				setNextPlayer();
			}
			
			TileOApplication.getGamePage().getDeckPanel().setToDefault();
  }


  /**
   * 
   * line 580 "../../../../../PlayModeController.ump"
   * line 633 "../../../../../PlayModeController.ump"
   */
  // line 701 "../../../../../PlayModeController.ump"
   public void doAddConnection(Tile tile1, Tile tile2){
    boolean isValid;
	  try{
		  playConnectTilesActionCard(tile1, tile2);
		  isValid = true;
	  }
	  catch(InvalidInputException e){
		  TileOApplication.getGamePage().showMessage("You cannot connect those tiles!");
		  isValid = false;
	  }
	  
	  if(isValid){
		  TileOApplication.getBoard().addConnectionAction(tile1, tile2);
	  }
	  	  
	  System.out.println("Was here");
	  TileOApplication.getGamePage().getDeckPanel().setToDefault();
  }


  /**
   * 
   * line 599 "../../../../../PlayModeController.ump"
   * line 653 "../../../../../PlayModeController.ump"
   */
  // line 725 "../../../../../PlayModeController.ump"
   public void displayCard(){
    //Display the current Action 
	  ActionCard c = TileOApplication.getCurrentGame().getDeck().getCurrentCard();
	  TileOApplication.getGamePage().getDeckPanel().setCardInfo(c);
  }


  /**
   * 
   * line 608 "../../../../../PlayModeController.ump"
   * line 660 "../../../../../PlayModeController.ump"
   */
  // line 736 "../../../../../PlayModeController.ump"
   public void doRemoveConnection(Tile tile1, Tile tile2){
    ArrayList<Connection> connections = new ArrayList<Connection>(TileOApplication.getCurrentGame().getConnections());
	   Connection current = null;
	   for(Connection c: connections){
		   if((c.getTile(0) == tile1 && c.getTile(1) == tile2) || (c.getTile(0) == tile2 && c.getTile(1) == tile1)){
			   current = c;
		   }
	   }
	   
	    boolean isValid;
		  try{
			  playRemoveConnectionActionCard(current);
			  isValid = true;
		  }
		  catch(InvalidInputException e){
			  TileOApplication.getGamePage().showMessage("Those tiles are not connected.");
			  isValid = false;
		  }
		  
		  if(isValid){
			  TileOApplication.getBoard().removeConnectionAction(tile1, tile2);
		  }
		  	  
		  TileOApplication.getGamePage().getDeckPanel().setToDefault();
  }


  /**
   * 
   * line 611 "../../../../../PlayModeController.ump"
   * line 687 "../../../../../PlayModeController.ump"
   */
  // line 767 "../../../../../PlayModeController.ump"
   public void enableRollDieButton(boolean flag){
    TileOApplication.enableRollDieButton(flag);
  }


  /**
   * 
   * line 616 "../../../../../PlayModeController.ump"
   * line 692 "../../../../../PlayModeController.ump"
   */
  // line 776 "../../../../../PlayModeController.ump"
   public void endGame(){
    showMessage("You found the hidden tile and won the game!");
  }


  /**
   * 
   * line 619 "../../../../../PlayModeController.ump"
   * line 697 "../../../../../PlayModeController.ump"
   */
  // line 785 "../../../../../PlayModeController.ump"
   public void showMessage(String s){
    JOptionPane.showMessageDialog(null, s);
  }


  /**
   * 
   * line 623 "../../../../../PlayModeController.ump"
   * line 702 "../../../../../PlayModeController.ump"
   */
  // line 794 "../../../../../PlayModeController.ump"
   public void enableGetActionCardButton(boolean enable){
    TileOApplication.getGamePage().enableGetActionCardButton(enable);
  }


  /**
   * 
   * 
   * line 516 "../../../../../PlayModeController.ump"
   * line 628 "../../../../../PlayModeController.ump"
   * line 711 "../../../../../PlayModeController.ump"
   */
  // line 805 "../../../../../PlayModeController.ump"
   public void save(){
    TileOApplication.save();
  }

}