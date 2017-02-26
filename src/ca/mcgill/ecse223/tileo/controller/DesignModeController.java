package ca.mcgill.ecse223.tileo.controller;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import java.security.InvalidParameterException;

public class DesignModeController {
	public void createNewActionTile(int x, int y, int numTurns) throws InvalidInputException{
		TileO tileO=TileOApplication.getTileO();
		Game game=TileOApplication.getCurrentGame();
		try{
			new ActionTile(x, y, game, numTurns);
			TileOApplication.save();
		}catch(RuntimeException e){
			throw new InvalidInputException("Error");
		}
	}

	public void createStartingTile(int x, int y, int playerNumber) throws Exception {
		
	}
}
