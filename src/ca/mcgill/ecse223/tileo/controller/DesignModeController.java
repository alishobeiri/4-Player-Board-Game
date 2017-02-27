package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.security.InvalidParameterException;
import java.util.List;

public class DesignModeController {
    /* Creates a game, adds a number of players and sets it in the controller
     *
     * @param numberOfPlayers An integer value corresponding to the number of
     *                        players in the game
     *
     * @throws InvalidInputException thrown if the number of players are less 
     *                               than the minimum or greater than the 
     *                               maximum.
     */
    public void createGame(int numberOfPlayers) throws InvalidInputException{
        TileO app = TileOApplication.getTileO();
        Game game = new Game(Game.SpareConnectionPieces, app);

        if(numberOfPlayers<Game.minimumNumberOfPlayers() ||
           numberOfPlayers>Game.maximumNumberOfPlayers()){
            throw new InvalidInputException("Invalid Number of Players");
        }

        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < numberOfPlayers; i++){
            players[i] = game.addPlayer(i);
        }

    }
    /* Adds a Regular tile into the game board at the specified location
     *
     * @param X an integer for the correspending x coordinate for the tile
     * @param Y an integer for the corresponding y coordinate for the tile
     * 
     * @throws InvalidInputException if there is a tile at the location or 
     *                               if x and y are out of range
     */

    public void addTile(int X, int Y) throws InvalidInputException{
        Game game = TileOApplication.getCurrentGame();
        List<Tile> tiles = game.getTiles();
        if(getTileFromBoard(X, Y, tiles) != null){
            throw new InvalidInputException("Tile already exists at that location");
        }
        Tile tile = new NormalTile(X, Y, game);
    }
    /* Removes a tile from the game board at the specified location
     *
     * @param X an integer for the correspending x coordinate for the tile
     * @param Y an integer for the corresponding y coordinate for the tile
     * 
     * @throws InvalidInputException if there is no tile at the location
     */
    public void removeTile(int X, int Y) throws InvalidInputException{
        Game game = TileOApplication.getCurrentGame();
        List<Tile> tiles = game.getTiles();
        Tile tile = getTileFromBoard(X, Y, tiles);
        if (tile == null){
            throw new InvalidInputException("No tile at that location");
        }
        else{
            tile.delete();
        }
    }
    private Tile getTileFromBoard(int x, int y, List<Tile> board){
        for(Tile t : board){
            if(t.getX()==x && t.getY()==y){
                return t;
            }
        }
        return null;
    }
	public void createNewActionTile(int x, int y, int numTurns) throws InvalidInputException{
		TileO tileO=TileOApplication.getTileO();
		Game game=TileOApplication.getCurrentGame();
		List<Tile> list=game.getTiles();
		for(Tile tile: list){
			if(tile.getX()==x && tile.getY()==y){
				throw new InvalidInputException("A tile already exists in that position.");
			}
		try{
			new ActionTile(x, y, game, numTurns);
			TileOApplication.save();
		}catch(RuntimeException e){
			throw new InvalidInputException("Error");
		}
		game.setMode(Mode.DESIGN);
		new ActionTile(x, y, game, numTurns);
		}
	}
    

	public void assignStartingTile(int x, int y, int playerNumber) throws InvalidInputException {
		Game game=TileOApplication.getCurrentGame();
		Player player;
		try{
			player=game.getPlayer(playerNumber-1);
		}catch(Exception e){
			throw new InvalidInputException("Player does not exist or might not have been initialized");
		}
		boolean found=false;
		for(Tile tile : game.getTiles()){
			if(tile.getX()==x && tile.getY()==y){
				found=true;
				player.setStartingTile(tile);
			}
		}
		if(!found){
			throw new InvalidInputException("The tile could not be found");
		}		
	}
	
	public void buildDeck(int rollDie, int addConnect, int removeConnect, int teleport, int loseTurn) throws InvalidInputException{
		Game game=TileOApplication.getCurrentGame();
		Deck deck=new Deck(game);
		if(rollDie+addConnect+removeConnect+teleport+loseTurn!=32){
			throw new InvalidInputException("Please make sure the total number of cards adds up to 32");
		}
		for(int i=0;i<rollDie;i++){
			new RollDieActionCard("You have the opportunity to roll again", deck);
		}
		for(int i=0;i<addConnect;i++){
			new ConnectTilesActionCard("Please connect two tiles", deck);
		}
		for(int i=0;i<removeConnect;i++){
			new RemoveConnectionActionCard("Please choose a connection to remove", deck);
		}
		for(int i=0;i<teleport;i++){
			new TeleportActionCard("Please choose a tile to teleport to", deck);
		}
		for(int i=0;i<loseTurn;i++){
			new LoseTurnActionCard("You have lost your turn", deck);
		}
	}
	
	
}
