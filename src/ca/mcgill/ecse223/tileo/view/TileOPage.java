package ca.mcgill.ecse223.tileo.view;

import java.awt.Font;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class TileOPage extends JFrame {
	
	//Components
	JLabel title = new JLabel("Tile-O");
	JLabel description = new JLabel("Saved Games:");
	JButton play = new JButton("Play");
	JButton create = new JButton("Create New Game");
	JList games;
	String[] gameNames = new String[10];
	JScrollPane scroll;
	TileO tileO;
	List<Game> tileOGames;
	DefaultListModel model;
	HashMap <String, Game> existingGames = new HashMap<String, Game>();
	HashMap <Game, Boolean> savedGames = new HashMap<Game, Boolean>();
	
	//***TESTING***
	/*public static void main(String[] args){
		new TileOPage().setVisible(true);
	}*/
	
	//Constructor
	public TileOPage(TileO aTileO){
		tileO = aTileO;
		tileOGames = tileO.getGames();
		model = new DefaultListModel();
		games = new JList(model);
		TileOApplication.setMainMenu(this);
		initComponents();
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 270);
		setResizable(false);
		
		title.setFont(new Font("Futura", Font.BOLD, 38));
		description.setFont(new Font("San Francisco", Font.PLAIN, 18));
		
		scroll = new JScrollPane(games);
		
		for(int i = 0; i < tileOGames.size(); i++){
			Game current = tileOGames.get(i);
			int index = i+1;
			existingGames.put("Game " + index + " - " + current.getMode(), current);
			savedGames.put(current, true);
		}
		
		for(String s: existingGames.keySet()){
			System.out.println(s);
			model.addElement(s);
		}
		
		games.setFont(new Font("San Francisco", Font.PLAIN, 15));
		
		//Change layout manager
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Action Listener
		create.addActionListener(new CreateListener());
		
		play.addActionListener(new PlayListener());
		
		//Component positioning
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(140, 140, 140)
						.addComponent(title))
				.addComponent(description)
				.addComponent(scroll)
				.addGroup(layout.createSequentialGroup()
						.addGap(40, 40, 40)
						.addComponent(play, 150, 150, 150)
						.addComponent(create, 150, 150, 150)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(title)
				.addGap(20, 20, 20)
				.addComponent(description)
				.addComponent(scroll)
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup()
						.addComponent(play)
						.addComponent(create)));
	}
	
	public TileOPage getPage(){
		return this;
	}
	
	public void refresh(){
		tileO = TileOApplication.getTileO();
		tileOGames = tileO.getGames();
		model = new DefaultListModel();
		for(int i = 0; i < tileOGames.size(); i++){
			Game current = tileOGames.get(i);
			int index = i+1;
			existingGames.put("Game " + index + " - " + current.getMode(), current);
		}
		
		for(String s: existingGames.keySet()){
			System.out.println(s);
			model.addElement(s);
		}
		
		games.setModel(model);
	}
	
	class CreateListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			new CreateGamePage(getPage()).setVisible(true);
		}
	}
	
	class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			DesignModeController dmc = new DesignModeController();
			Game g = null;
			if(!games.isSelectionEmpty()){
				String gameName = (String) games.getSelectedValue();
				g = existingGames.get(gameName);
				if(g != null){
					if(g.getMode() == Mode.DESIGN){
						dmc.setTileOApplicationCurrentGame(g);
						new DesignPage(getPage()).setVisible(true);
					}
					else{
						dmc.setTileOApplicationCurrentGame(g);
						new GamePage(getPage()).setVisible(true);
					}
				}
			}

			else{
				System.out.println("You must select or create a game");
			}
			
			DesignPage designPage=new DesignPage(getPage());
			TileOApplication.addPrevDesignGame(designPage);
			designPage.setVisible(true);
		}
	}
	
	
	
}
