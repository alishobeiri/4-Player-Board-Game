package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.security.InvalidParameterException;
import java.util.List;

public class DesignModeController {
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


	public void assignStartingTile(int x, int y, int playerNumber) throws Exception {
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
