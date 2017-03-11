/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;

// line 3 "../../../../../PlayModeControllerModel.ump"
public class PlayModeControllerNew
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayModeControllerNew State Machines
  public enum Mode { Normal, RollDieAgain, Teleport, AddConnection, RemoveConnection, LoseTurn }
  public enum ModeNormal { Null, RollDie, ChooseTile }
  public enum ModeRollDieAgain { Null, RollDieAC, ChooseTileAC }
  private Mode mode;
  private ModeNormal modeNormal;
  private ModeRollDieAgain modeRollDieAgain;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayModeControllerNew()
  {
    setModeNormal(ModeNormal.Null);
    setModeRollDieAgain(ModeRollDieAgain.Null);
    setMode(Mode.Normal);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getModeFullName()
  {
    String answer = mode.toString();
    if (modeNormal != ModeNormal.Null) { answer += "." + modeNormal.toString(); }
    if (modeRollDieAgain != ModeRollDieAgain.Null) { answer += "." + modeRollDieAgain.toString(); }
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public ModeNormal getModeNormal()
  {
    return modeNormal;
  }

  public ModeRollDieAgain getModeRollDieAgain()
  {
    return modeRollDieAgain;
  }

  public boolean tileToTeleportChosen()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Teleport:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean connectionAdded()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case AddConnection:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean connectionRemoved()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case RemoveConnection:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playerStateChanged()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case LoseTurn:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterNormal()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case Null:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitNormal()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case RollDie:
        setModeNormal(ModeNormal.Null);
        wasEventProcessed = true;
        break;
      case ChooseTile:
        setModeNormal(ModeNormal.Null);
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
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case RollDie:
        exitModeNormal();
        // line 10 "../../../../../PlayModeControllerModel.ump"
        doDieRolled();
        setModeNormal(ModeNormal.ChooseTile);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean moveChosen()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        setModeNormal(ModeNormal.RollDie);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean landedOnRollDieAgain()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        exitMode();
        setModeRollDieAgain(ModeRollDieAgain.RollDieAC);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean landedOnTeleport()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        exitMode();
        setMode(Mode.Teleport);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean landedOnAddConnection()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        exitMode();
        setMode(Mode.AddConnection);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean landedOnRemoveConnection()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        exitMode();
        setMode(Mode.RemoveConnection);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean landedOnLoseTurn()
  {
    boolean wasEventProcessed = false;
    
    ModeNormal aModeNormal = modeNormal;
    switch (aModeNormal)
    {
      case ChooseTile:
        exitMode();
        setMode(Mode.LoseTurn);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterRollDieAgain()
  {
    boolean wasEventProcessed = false;
    
    ModeRollDieAgain aModeRollDieAgain = modeRollDieAgain;
    switch (aModeRollDieAgain)
    {
      case Null:
        setModeRollDieAgain(ModeRollDieAgain.RollDieAC);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitRollDieAgain()
  {
    boolean wasEventProcessed = false;
    
    ModeRollDieAgain aModeRollDieAgain = modeRollDieAgain;
    switch (aModeRollDieAgain)
    {
      case RollDieAC:
        setModeRollDieAgain(ModeRollDieAgain.Null);
        wasEventProcessed = true;
        break;
      case ChooseTileAC:
        setModeRollDieAgain(ModeRollDieAgain.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean dieRolledAC()
  {
    boolean wasEventProcessed = false;
    
    ModeRollDieAgain aModeRollDieAgain = modeRollDieAgain;
    switch (aModeRollDieAgain)
    {
      case RollDieAC:
        setModeRollDieAgain(ModeRollDieAgain.ChooseTileAC);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean tileChosenAC()
  {
    boolean wasEventProcessed = false;
    
    ModeRollDieAgain aModeRollDieAgain = modeRollDieAgain;
    switch (aModeRollDieAgain)
    {
      case ChooseTileAC:
        exitMode();
        setModeNormal(ModeNormal.RollDie);
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
      case Normal:
        exitNormal();
        break;
      case RollDieAgain:
        exitRollDieAgain();
        break;
    }
  }

  private void setMode(Mode aMode)
  {
    mode = aMode;

    // entry actions and do activities
    switch(mode)
    {
      case Normal:
        if (modeNormal == ModeNormal.Null) { setModeNormal(ModeNormal.RollDie); }
        break;
      case RollDieAgain:
        if (modeRollDieAgain == ModeRollDieAgain.Null) { setModeRollDieAgain(ModeRollDieAgain.RollDieAC); }
        break;
    }
  }

  private void exitModeNormal()
  {
    switch(modeNormal)
    {
      case RollDie:
        // line 11 "../../../../../PlayModeControllerModel.ump"
        enableRollDieButton(false);
        break;
    }
  }

  private void setModeNormal(ModeNormal aModeNormal)
  {
    modeNormal = aModeNormal;
    if (mode != Mode.Normal && aModeNormal != ModeNormal.Null) { setMode(Mode.Normal); }

    // entry actions and do activities
    switch(modeNormal)
    {
      case RollDie:
        // line 9 "../../../../../PlayModeControllerModel.ump"
        enableRollDieButton(true);
        break;
    }
  }

  private void setModeRollDieAgain(ModeRollDieAgain aModeRollDieAgain)
  {
    modeRollDieAgain = aModeRollDieAgain;
    if (mode != Mode.RollDieAgain && aModeRollDieAgain != ModeRollDieAgain.Null) { setMode(Mode.RollDieAgain); }
  }

  public void delete()
  {}


  /**
   * dieRolled method implementation
   */
  // line 61 "../../../../../PlayModeControllerModel.ump"
   public void doDieRolled(){
    Game game = TileOApplication.getCurrentGame();
				Player currentPlayer = game.getCurrentPlayer();
				Tile currentTile = currentPlayer.getCurrentTile();
				
				java.util.List<Tile> tiles = new ArrayList<Tile>();
					tiles = rollDie();
					
					if(tiles == null || tiles.size() == 0){
						showMessage("No possible moves!");
						try {
							land(currentPlayer.getCurrentTile());
						} catch (InvalidInputException e) {
							e.printStackTrace();
						}
						setNextPlayer(game);
						TileOApplication.getDesignPanel().refresh();
						return;
					}
				
				//This shows the possible moves in pink
				for(Tile t : tiles){
					BoardPanel.Rectangle2DCoord rect = this.board.getRectangle(t.getX(), t.getY());
					if(rect != null){


						rect.setColor(Color.pink);
						
					}
				}
				board.setMode(BoardPanel.Mode.MOVE_PLAYER);
				refresh();
				board.refreshBoard();
  }

  // line 96 "../../../../../PlayModeControllerModel.ump"
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
  // line 148 "../../../../../PlayModeControllerModel.ump"
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

		game.setMode(Mode.GAME);
		
		//TileOApplication.save();

		return tiles;
  }

  // line 172 "../../../../../PlayModeControllerModel.ump"
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
		
		setNextPlayer(game);

		advanceCurrentCard(deck);

		game.setMode(Mode.GAME);

		
		//TileOApplication.save();
  }

  // line 214 "../../../../../PlayModeControllerModel.ump"
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

		setNextPlayer(game);

		advanceCurrentCard(deck);

		game.setMode(Mode.GAME);
		
		//TileOApplication.save();
  }

  // line 242 "../../../../../PlayModeControllerModel.ump"
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

		game.setMode(Mode.GAME);
		
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
  // line 291 "../../../../../PlayModeControllerModel.ump"
   public void setNextPlayer(Game game){
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
  // line 316 "../../../../../PlayModeControllerModel.ump"
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
  // line 340 "../../../../../PlayModeControllerModel.ump"
   public void advanceCurrentCard(Deck deck){
    ActionCard card = deck.getCurrentCard();
		int index = deck.indexOfCard(card);
		if (deck.getCard(index + 1) == null) {
			// TODO Check that this method works.
			deck.shuffle();
			index = 0;
		}
		ActionCard nextCard = deck.getCard(index);
		deck.setCurrentCard(nextCard);
  }


  /**
   * Thomas
   * Returns a list of possible moves the current player can make based on the
   * number they roll
   */
  // line 355 "../../../../../PlayModeControllerModel.ump"
   public List<Tile> rollDie(){
    Game game = TileOApplication.getCurrentGame();
		List<Tile> tiles = game.rollDie();
		return tiles;
  }


  /**
   * Thomas
   */
  // line 363 "../../../../../PlayModeControllerModel.ump"
   public void startGame(Game selectedGame) throws InvalidInputException{
    /* VARIABLES */
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
			throw new InvalidInputException("The deck has the wrong number of Action Cards");
		}
		// Check the game has a specified Win Tile
		if (!selectedGame.hasWinTile()) {
			throw new InvalidInputException("The game does not have a Win Tile");
		}
		if(!selectedGame.hasPlayers()){
			throw new InvalidInputException("The game does not have any added players");
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
				throw new InvalidInputException("The starting position is not set for a player");
			}
		}

		/* SET */
		// Set the first player as the current player
		selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
		// Set the number of connection pieces to the default value
		selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
		// Set the game mode to GAME
		selectedGame.setMode(Mode.GAME);
  }


  /**
   * Thomas
   */
  // line 430 "../../../../../PlayModeControllerModel.ump"
   public void land(Tile tile) throws InvalidInputException{
    // Validation check: Make sure tile exists as one of the game tiles
		Game game = tile.getGame();
		List<Tile> tiles = game.getTiles();
		// If the tile is in the list of game tiles
		if(tiles.indexOf(tile)!=-1){
			tile.land();
			//TileOApplication.save();
		}else{
			throw new InvalidInputException("Tile is not part of the game");
		}
  }

  // line 443 "../../../../../PlayModeControllerModel.ump"
   public static  void save(){
    TileOApplication.save();
  }

}