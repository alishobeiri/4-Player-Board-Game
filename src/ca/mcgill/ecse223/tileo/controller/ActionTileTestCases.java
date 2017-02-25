package ca.mcgill.ecse223.tileo.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;

public class ActionTileTestCases {

	@Test
	public void test() {
		TileOController control=new TileOController();
		TileO tileO=TileOApplication.getTileO();
		Game game=new Game(0, tileO);
		TileOApplication.setCurrentGame(game);
		control.createNewActionTile(5, 3, 5);
		control.createNewActionTile(5, 3, 5);
	}

}
