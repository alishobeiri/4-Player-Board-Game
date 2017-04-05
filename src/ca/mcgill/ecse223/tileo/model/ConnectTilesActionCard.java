/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 72 "../../../../../TileO (updated Feb10).ump"
public class ConnectTilesActionCard extends ActionCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -629288172349309738L;

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public ConnectTilesActionCard(String aInstructions, Deck aDeck) {
		super(aInstructions, aDeck);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void changeGameModeToActionCard() {
		Deck deck = this.getDeck();
		Game game = deck.getGame();
		game.setMode(Mode.GAME_CONNECTTILESACTIONCARD);
	}

	public void delete() {
		super.delete();
	}

	public void play(Tile tile1, Tile tile2) throws InvalidInputException {
		Game game = this.getDeck().getGame();

		game.connectTiles(tile1, tile2);
		game.setMode(Mode.GAME);
		System.out.println("Mode changed");
	}

}