package ca.mcgill.ecse223.tileo.controller;

import org.junit.Test;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.TileO;

public class ActionTileTestCases {

	@Test
	public void test() {
		TileO tileO=TileOApplication.getTileO();
		Game game=new Game(0, tileO);
		TileOApplication.setCurrentGame(game);
		TileOController control=new TileOController();
		try {
			control.assignStartingTile(5, 4, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//<<<<<<< HEAD
//=======
//		DesignModeController.createNewActionTile(5, 5, 5);
		
//		TileOController.startGame(game);
//>>>>>>> origin/master
	}

}
