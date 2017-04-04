package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

public class SendBackToStartActionCard extends ActionCard implements Serializable{
	
	public SendBackToStartActionCard(String aInstructions, Deck aDeck) {
		super(aInstructions, aDeck);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void changeGameModeToActionCard() {
		Deck deck = this.getDeck();
		Game game = deck.getGame();
		game.setMode(Mode.GAME_SENDBACKTOSTARTACTIONCARD);
	}
	
	public void play() throws InvalidInputException{
		Deck deck = this.getDeck();
		Game game = deck.getGame();
		Player player = game.getCurrentPlayer();
		player.getStartingTile().land();
		  
	  }

}
	


