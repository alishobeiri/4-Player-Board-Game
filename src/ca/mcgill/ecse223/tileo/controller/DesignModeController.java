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
		Tile action = new ActionTile(x, y, game, numTurns);
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
}
