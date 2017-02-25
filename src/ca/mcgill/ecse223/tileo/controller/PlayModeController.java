package ca.mcgill.ecse223.tileo.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

public class PlayModeController {

	// Add controller methods that you need to the file


	// Action Card Methods

	public List<Tile> playRollDieActionCard() throws InvalidInputException {
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

		return tiles;
	}

	public void connectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException {
		Game game = TileOApplication.getCurrentGame();

		// Valid input checks
		if (!(game.getTiles().contains(tile1)) || !(game.getTiles().contains(tile2))) {
			throw new InvalidInputException("That tile is not in the game.");
		}

		if (!areAdjacent(tile1, tile2)) {
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

	}

	public void playRemoveConnectionActionCard(Connection aConnection) throws InvalidInputException {
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

	}

	public void playTeleportActionCart(Tile tile) throws InvalidInputException {
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
	}

	// Helper methods

	// Sets the current player to the next player
	public void setNextPlayer(Game game) {
		List<Player> players = game.getPlayers();
		Player current = game.getCurrentPlayer();
		int index = game.indexOfPlayer(current);
		if (players.get(index + 1) == null) {
			index = 0;
		}
		Player next = players.get(index);
		game.setCurrentPlayer(next);
	}

	// Checks if two tiles are adjacent (connected) to each other
	public boolean areAdjacent(Tile tile1, Tile tile2) {
		List<Connection> connections = tile1.getConnections();

		for (Connection c : connections) {
			if (c.getTiles().contains(tile1) && c.getTiles().contains(tile2)) {
				return true;
			}
		}

		return false;
	}

	// Sets the current card on the deck to the next one
	public void advanceCurrentCard(Deck deck) {
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

	// Thomas
	// Returns a list of possible moves the current player can make based on the
	// number they roll
	public List<Tile> rollDie() {
		Game game = TileOApplication.getCurrentGame();
		
		// Returns a list of possible moves the current player can make
		return game.rollDie();
	}

	// Thomas
	// TODO Implement validation and fix errors
	public void startGame(Game selectedGame) throws InvalidInputException {

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

	// Thomas
	// TODO
	public void land(Tile tile) throws InvalidInputException {
		// Validation check: Make sure tile exists as one of the game tiles
		Game game = tile.getGame();
		List<Tile> tiles = game.getTiles();
		// If the tile is in the list of game tiles
		if(tiles.indexOf(tile)!=-1){
			tile.land();
		}else{
			throw new InvalidInputException("Tile is not part of the game");
		}
	}
}
