package ca.mcgill.ecse223.tileo.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTileTestCases {

	@Test
	public void test() {
		TileOController control=new TileOController();
		control.createNewActionTile(5, 3, 5);
	}

}
