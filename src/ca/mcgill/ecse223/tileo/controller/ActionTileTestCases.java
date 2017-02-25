package ca.mcgill.ecse223.tileo.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;

public class ActionTileTestCases {

	@Test
	public void test() {
		TileO tileO=TileOApplication.getTileO();
		Game game=new Game(0, tileO);
		TileOApplication.setCurrentGame(game);
		DesignModeController.createNewActionTile(5, 5, 5);
	}

}
