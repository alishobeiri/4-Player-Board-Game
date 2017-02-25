package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import java.security.InvalidParameterException;

public class DesignModeController {
	public void createNewActionTile(int x, int y, int numTurns) throws InvalidInputException{
		Game game=TileOApplication.getCurrentGame();
		try{
			new ActionTile(x, y, game, numTurns);
		}catch(RuntimeException e){
			throw new InvalidInputException("Error");
		}
	}

	public void createStartingTile(int x, int y, int playerNumber) throws Exception {
		Game game=TileOApplication.getCurrentGame();
		try{
			game.getPlayer(50);
		}catch(RuntimeException e){
			
		}
	}
}
