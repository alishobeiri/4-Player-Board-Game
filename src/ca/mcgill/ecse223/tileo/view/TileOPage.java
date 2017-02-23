package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.controller.TileOController;

import java.awt.*;
import java.awt.event.*;

public class TileOPage extends JFrame {

	// data elements
	private String error = null;

	public static void main(String[] args) {

		TileOPage page = new TileOPage();
		page.setVisible(true);

	}

	public TileOPage() {
		initComponents();
	}

	public void initComponents() {

		// Frame settings
		setTitle("Tile-O");
		setSize(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Components
		JButton playNewGame = new JButton(" Play New Game ");
		JButton loadGame = new JButton("    Load Game    ");
		JLabel title = new JLabel("   Tile-O   ");
		JPanel panel = new JPanel();

		// Title settings
		Font titleFont = new Font("Gill Sans", Font.BOLD, 36);
		title.setFont(titleFont);

		// Add components
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.add(title);
		panel.add(playNewGame);
		panel.add(loadGame);

		// ActionListeners
		loadGame.addActionListener(new loadButtonListener());
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

	public void playButtonActionPerformed(ActionEvent ev) {
		// clear error message
		error = null;

		// Call the controller
		TileOController toc = new TileOController();

		// update visual?
		// refresh();

	}
}
