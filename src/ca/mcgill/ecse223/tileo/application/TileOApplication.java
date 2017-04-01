package ca.mcgill.ecse223.tileo.application;

import ca.mcgill.ecse223.tileo.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.view.BoardPanel;
import ca.mcgill.ecse223.tileo.view.CreateGamePage;
import ca.mcgill.ecse223.tileo.view.DeckPanel;
import ca.mcgill.ecse223.tileo.view.DesignPage;
import ca.mcgill.ecse223.tileo.view.GamePage;
import ca.mcgill.ecse223.tileo.view.TileOPage;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;

public class TileOApplication {
	private static TileO tileO;
	private static String filename = "data.tileO";
	private static TileOPage mainMenu;
	private static DesignPage designPage;
	private static GamePage gamePage;
	private static BoardPanel board;
	private static CreateGamePage createPage;
	private static PlayModeController pmc;

	public static void main(String args[]) {

		TileO tileO = TileOApplication.getTileO();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				mainMenu = new TileOPage(tileO);
				mainMenu.setVisible(true);
			}
		});
	}

	public static TileO getTileO() {
		if (tileO == null) {
			tileO = load();
		}
		return tileO;

	}

	public static Game getCurrentGame() {
		return tileO.getCurrentGame();
	}

	public static boolean setCurrentGame(Game aNewCurrentGame) {
		boolean wasSet = false;
		tileO.setCurrentGame(aNewCurrentGame);
		wasSet = true;
		return wasSet;
	}

	public static void changeGameMode() {
		getCurrentGame().setMode(Game.Mode.GAME);
	}

	public static void setMainMenu(TileOPage d) {
		mainMenu = d;
	}

	public static TileOPage getMainMenu() {
		return mainMenu;
	}

	public static void createDesignGame(TileOPage menu) {
		designPage = new DesignPage(menu);
		board = designPage.getBoard();
		designPage.setVisible(true);
	}

	public static BoardPanel getBoard() {
		return board;
	}

	public static void setBoard(BoardPanel aBoard) {
		board = aBoard;
	}

	public static void deleteGame() {
		gamePage.setVisible(false);
		gamePage.dispose();
	}

	public static void setDesignGame(DesignPage d) {
		designPage = d;
	}

	public static void setGamePage(GamePage g) {
		gamePage = g;
	}

	public static GamePage getGamePage() {
		return gamePage;
	}

	public static void refreshDie(int number) {
		gamePage.refreshDie(number);
	}

	public static void save() {
		PersistenceObjectStream.serialize(tileO);
	}

	// TODO : Fix
	public static DesignPage getDesignPage() {
		return designPage;
	}

	public static TileO load() {
		PersistenceObjectStream.setFilename(filename);
		tileO = (TileO) PersistenceObjectStream.deserialize();

		if (tileO == null) {
			tileO = new TileO();
		} else {

		}
		return tileO;
	}

	public static void enableRollDieButton(boolean enable) {
		try {
			gamePage.enableRollDieButton(enable);
		} catch (NullPointerException e) {
			System.out.println("GamePage is null.");
		}
	}

	public static PlayModeController getPlayModeController() {
		return pmc;
	}

	public static void setPlayModeController(PlayModeController aPMC) {
		pmc = aPMC;
	}

	public static void saveBoard(BoardPanel oldBoard) {
		board = new BoardPanel(oldBoard);
	}
}
