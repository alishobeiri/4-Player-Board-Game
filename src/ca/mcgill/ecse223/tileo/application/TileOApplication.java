package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;

public class TileOApplication {
	private static TileO tileO;
	
	public static void main(String args[]){

		
	}
	
	public static Game getGame(){
		return tileO.getCurrentGame();
	}
	
	public static void save() {
		//Use to save
	}
	
	public static void load() {
		//Use to load
	}
}
