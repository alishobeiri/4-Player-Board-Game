package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TileOPage extends JFrame {
	
	public static void main(String[] args){
		
		TileOPage page = new TileOPage();
		page.setVisible(true);

	}
	
	public TileOPage(){
		initComponents();
	}
	
	public void initComponents(){
		
		//Frame settings
		setTitle("Tile-O");
		setSize(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Components
		JButton playNewGame = new JButton(" Play New Game ");
		JButton loadGame = new JButton("    Load Game    ");
		JLabel title = new JLabel("   Tile-O   ");
		JPanel panel = new JPanel();
		
		//Title settings
		Font titleFont = new Font("Gill Sans", Font.BOLD, 36);
		title.setFont(titleFont);
		
		//Add components
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.add(title);
		panel.add(playNewGame);
		panel.add(loadGame);
		
		//ActionListeners
		loadGame.addActionListener(new playListener());
	}
	
	class playListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(new TileOPage());
		}
	}
}
