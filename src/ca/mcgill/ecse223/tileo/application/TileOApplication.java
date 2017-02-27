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
	private static DesignPage designPage;
	
	public static void main(String args[]){
		// Thomas - not actually sure this is the right way to do this
		// start UI
		TileO tileO=TileOApplication.getTileO();
		Game game=new Game(0, tileO);
		TileOApplication.setCurrentGame(game);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	designPage=new DesignPage();
                designPage.setVisible(true);
                //TODO remove following line after testing
//                new GamePage().setVisible(true);
            }
        });
	}
	
	public static TileO getTileO() {
		if (tileO == null) {
//load model
			tileO = load();
		}
		return tileO;
		
	}
	
	public static int getPlayer(){
		return designPage.getPlayer();
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
	
	public static void save() {
		//Use to save

		// Thomas - not sure this is right, copied from btms, also copied persistence java file
		PersistenceObjectStream.serialize(tileO);
	}
	
	// TODO basically copy the code from BTMS
	public static TileO load() {
		//Use to load
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (tileO == null) {
			tileO = new TileO();
		}
		else {
			
		}
		return tileO;
	}
}
