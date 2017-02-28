package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.view.BoardPanel;
import ca.mcgill.ecse223.tileo.view.DeckPanel;
import ca.mcgill.ecse223.tileo.view.DesignPage;
import ca.mcgill.ecse223.tileo.view.GamePage;
import ca.mcgill.ecse223.tileo.view.TileOPage;


public class TileOApplication {
	private static TileO tileO;
	private static String filename = "data.tileO";
	private static TileOPage mainMenu;
	private static DesignPage designPage;
	private static GamePage gamePage;

	public static void main(String args[]){

		TileO tileO=TileOApplication.getTileO();
		


		Game game=new Game(0, tileO);
		TileOApplication.setCurrentGame(game);
		//designPage = new DesignPage(4);
		game.addPlayer(new Player(0, game));

		TileOApplication.setCurrentGame(game);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	//Check the setMode here
            	//game.setMode(Game.Mode.GAME);
            	//TileOApplication.designPage.setVisible(true);
            	//TileOApplication.designPage.setResizable(true);
            	//new GamePage().setVisible(true);
                //TODO remove following line after testing
                //new GamePage().setVisible(true);
            	
            	new TileOPage(tileO).setVisible(true);
            }
        });
	}
	
	public static TileO getTileO() {
		if (tileO == null) {
			tileO = load();
		}
		return tileO;
		
	}
	
	public static Game getCurrentGame(){
		return tileO.getCurrentGame();
	}
	
	//Thomas generated - on advice of Berk in tutorial
	public static boolean setCurrentGame(Game aNewCurrentGame){
		boolean wasSet = false;
		tileO.setCurrentGame(aNewCurrentGame);
		wasSet = true;
		return wasSet;
	}

	public static void changeGameMode(BoardPanel board){
		gamePage = new GamePage(board);
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Game game=getCurrentGame();
            	System.out.println("Num of player " + game.numberOfPlayers());
            	System.out.println("Num of connection " + game.getCurrentConnectionPieces());
            	System.out.println("Num of tiles " + game.getTiles().size());
            	System.out.println("Num of cards " + game.getDeck().numberOfCards());
            	//Check the setMode here
            	//game.setMode(Game.Mode.GAME);
            	getCurrentGame().setMode(Game.Mode.GAME);
            	TileOApplication.designPage.setVisible(false);
            	TileOApplication.gamePage.setVisible(true);
            	TileOApplication.gamePage.setResizable(true);
                //TODO remove following line after testing
//                new GamePage().setVisible(true);
            }
        });
	}
	
	public static void setDesignGame(DesignPage d){
		designPage=d;
	}
	public static void save() {
		// Thomas - not sure this is right, copied from btms, also copied persistence java file
		PersistenceObjectStream.serialize(tileO);
	}
	
	// TODO basically copy the code from BTMS
	public static TileO load() {
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();

		if (tileO == null) {
			tileO = new TileO();
		}
		else {
			
		}
		return tileO;
	}
}
