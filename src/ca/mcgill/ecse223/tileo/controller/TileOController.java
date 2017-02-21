package ca.mcgill.ecse223.tileo.controller;

import java.security.InvalidParameterException;
import java.util.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

public class TileOController {

	//Add controller methods that you need to the file 
	public void createNewActionTile(int x, int y, int numTurns) throws Exception{
		Game game = TileOApplication.getCurrentGame();
	}
	
	public void createStartingTile(int x, int y, int playerNumber) throws Exception{
		
	}
	
	//Action Card Methods
	
	public List<Tile> playRollDieActionCard() throws InvalidInputException{
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		ActionCard card = deck.getCurrentCard();
		
		//Check if the current card is a Roll Die Card
		if(!(card instanceof RollDieActionCard)){
			throw new InvalidInputException("The current card is not a Roll Die Action Card");
		}
		
		RollDieActionCard rollDieCard = (RollDieActionCard) card;
		
		List<Tile> tiles = new ArrayList<Tile>();
		//tiles = rollDieCard.play();
		
		advanceCurrentCard(deck);
		
		game.setMode(Mode.GAME);
		
		return tiles;
	}
	
	public void connectTilesActionCard(Tile tile1, Tile tile2) throws InvalidInputException{
		Game game = TileOApplication.getCurrentGame();
		
		//Valid input checks
		if(!(hasTile(game.getTiles(), tile1)) || !(hasTile(game.getTiles(), tile2))){
			throw new InvalidInputException("That tile is not in the game.");
		}
		
		if(!areAdjacent(tile1, tile2)){
			throw new InvalidInputException("The tiles are not adjacent.");
		}
		
		if(!(game.getCurrentConnectionPieces() > 0)){
			throw new InvalidInputException("There are no spare connection pieces left.");
		}
		
		Deck deck = game.getDeck();
		ActionCard card = deck.getCurrentCard();
		
		//Check if current card is Connect Tiles Card
		if(!(card instanceof ConnectTilesActionCard)){
			throw new InvalidInputException("The current card is not a Connect Tiles Action Card");
		}
		
		ConnectTilesActionCard connectTilesCard = (ConnectTilesActionCard) card;
		
		//connectTilesCard.play(tile1, tile2);
		
		
	}
	
	//Helper methods
	public boolean hasTile(List<Tile> tiles, Tile tile){
		Iterator iter = tiles.iterator();
		
		while(iter.hasNext()){
			if(iter.next() == tile){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean areAdjacent(Tile tile1, Tile tile2){
		List<Connection> connections = tile1.getConnections();
		
		for(Connection c: connections){
			if(hasTile(c.getTiles(), tile1) && hasTile(c.getTiles(), tile2)){
				return true;
			}
		}
		
		return false;
	}
	
	public void advanceCurrentCard(Deck deck){
		ActionCard card = deck.getCurrentCard();
		int index = deck.indexOfCard(card);
		ActionCard nextCard = deck.getCard(index);
		deck.setCurrentCard(nextCard);
	}
	
	//TESTING METHODS
	/*public List<Tile> rollDie(){
		return new ArrayList<Tile>();
	}*/
	
	
}
