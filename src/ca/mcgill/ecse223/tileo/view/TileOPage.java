package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.TileOController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.TileO;

import java.awt.*;
import java.awt.event.*;

public class TileOPage extends JFrame {

	// data elements
	private String error = null;
	// UI elements
	private JLabel errorMessage;

	public static void main(String[] args) {

		TileOPage page = new TileOPage();
		page.setVisible(true);

	}

	public TileOPage() {
		initComponents();
	}

	public void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// Frame settings
		setTitle("Tile-O");
		setSize(300, 150);
		// TODO set to false
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Components
		JButton playNewGameButton = new JButton(" Play New Game ");
		JButton loadGameButton = new JButton("    Load Game    ");
		JLabel titleLabel = new JLabel("   Tile-O   ");
		JPanel panel = new JPanel();

		// Title settings
		Font titleFont = new Font("Gill Sans", Font.BOLD, 36);
		titleLabel.setFont(titleFont);

		// Add components
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.add(titleLabel);
		panel.add(playNewGameButton);
		panel.add(loadGameButton);
		panel.add(errorMessage);

		// ActionListeners
		playNewGameButton.addActionListener(new playButtonListener());
		loadGameButton.addActionListener(new loadButtonListener());
	}

	// Thomas
	class playButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			// TODO Add button functionality.
			playButtonActionPerformed(ev);
		}
	}

	class loadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(new TileOPage());
		}
	}

	// Thomas
	// TODO this button should launch design mode, then there should be a start
	// game button on the design mode page
	public void playButtonActionPerformed(ActionEvent ev) {
		// clear error message
		error = null;

		// Call the controller
		TileOController toc = new TileOController();

		// TODO not sure which of the two following lines are correct
		// TileO tileO = new TileO();
		TileO tileO = TileOApplication.getTileO();

		// TODO this game should be either the loaded game or a new game i think
		Game game = new Game(32, tileO);

		try {
			toc.startGame(game);
		} catch (ca.mcgill.ecse223.tileo.controller.InvalidInputException e) {
			error = e.getMessage();
		}

		// open the game page
		new GamePage().setVisible(true);
		// close the tileO page
		this.dispose();
		// update visual?
		refresh();

	}

	private void refresh() {
		// error
		errorMessage.setText(error);

	}
}
