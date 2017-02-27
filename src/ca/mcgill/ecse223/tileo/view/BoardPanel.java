package ca.mcgill.ecse223.tileo.view;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class BoardPanel extends JPanel {
	
	//Constants
	public static final int HEIGHT = 30;
	public static final int WIDTH = 30;
	public static final int GAP = 15;
	public static final int HORIZONTAL_RECTANGLES = 14;
	public static final int VERTICAL_RECTANGLES = 14;
	
	//Attributes
	public Game.Mode m;
	public ArrayList<Rectangle2DCoord> rectangles = new ArrayList<Rectangle2DCoord>();
	public HashMap<Rectangle2DCoord, Tile> boardTiles = new HashMap<Rectangle2DCoord, Tile>();
	Mode mode;
	TileType tileType = TileType.NORMAL;
	int inactiveTurns = 0;
	int playerNumber=1;
	Rectangle2DCoord currentWinRectangle;
	
	//***TESTING*** TODO: REMOVE
	TileO tileo = new TileO();
	Game testGame=new Game(0, tileo);
	//NormalTile tile1 = new NormalTile(0, 0, game);
	//NormalTile tile2 = new NormalTile(5, 10, game);
	//NormalTile tile3 = new NormalTile(13, 3, game);

	//Game game=TileOApplication.getCurrentGame();

	
	public void initComponents(){
		//Initialize the rectangles list to cover the whole board
		for(int i = 0; i < HORIZONTAL_RECTANGLES; i++){
			for(int j = 0; j < VERTICAL_RECTANGLES; j++){
				Rectangle2D r2d = new Rectangle2D.Float(GAP*(j+1) + WIDTH*j, GAP*(i+1) + HEIGHT*i, WIDTH, HEIGHT);
				Rectangle2DCoord currentRect = new Rectangle2DCoord(r2d, i, j);
				rectangles.add(currentRect);
			}
		}
		
		//Initialize the hashmap to match every tile to a rectangle
		for(Tile t: game.getTiles()){
			Rectangle2DCoord r = getRectangle(t.getX(), t.getY());
			boardTiles.put(r, t);
		}
	}
	
	//Helper method
	public Rectangle2DCoord getRectangle(int x, int y){
		for(Rectangle2DCoord rect: rectangles){
			if(rect.coordX == x && rect.coordY == y){
				System.out.println("found");
				return rect;
			}
		}		
		return null;
	}
	
	// Constructor
	public BoardPanel(Game.Mode m){
		//TESTING TODO: REMOVE
		game.setMode(m);
		initComponents();
		addMouseListener(new MouseSelectionListener());
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void doDrawing(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Background
		RoundRectangle2D contour = new RoundRectangle2D.Float(0, 0, 646, 646, 10, 10); 
		g2d.setColor(new Color(195, 195, 195));
		g2d.fill(contour);
		g2d.setColor(new Color(170, 170, 170));
		g2d.draw(contour);
		
		//Reset board
		for(Rectangle2DCoord rectangle: rectangles){
			g2d.setColor(new Color(195, 195, 195));
			g2d.fill(rectangle.coordRectangle);
		}
		
		if(game.getMode()==Game.Mode.DESIGN){
			for(Rectangle2DCoord rectangle: rectangles){
				g2d.setColor(new Color(230, 230, 230));
				g2d.draw(rectangle.coordRectangle);
			}
		}
		
		//Paint currently existing tiles
		for(Rectangle2DCoord rectangle: boardTiles.keySet()){		
			g2d.setColor(Color.WHITE);
			g2d.fill(rectangle.coordRectangle);
			g2d.setColor(Color.GRAY);
			g2d.draw(rectangle.coordRectangle);
		}
		
		Ellipse2D player = new Ellipse2D.Float(GAP*5 + WIDTH*4, GAP*7 + HEIGHT*6, WIDTH, HEIGHT);
		g2d.setColor(Color.RED);
		g2d.fill(player);

	}
	
	public void addTile(Rectangle2DCoord rect){
		DesignModeController toc=new DesignModeController();
		if(!(boardTiles.keySet().contains(rect))){
			
			//TODO: Insert real addTile method from the controller
			
			if(tileType == TileType.NORMAL){
				try {
					NormalTile t = toc.addNormalTile(rect.coordX, rect.coordY);
					boardTiles.put(rect, t);
					repaint();
					System.out.println("Normal Tile");
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					System.out.println("Tile exists at that location");
				}

			}
			else if(tileType == TileType.ACTION){
				ActionTile t = null;
				try {
					t=toc.addActionTile(rect.coordX, rect.coordY, inactiveTurns);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					System.out.println("Tile already here");
				}
				boardTiles.put(rect, t);
				repaint();
				System.out.println("Action Tile: " + inactiveTurns + " inactive turns.");
			}
			else if(tileType == TileType.WIN){
				if(currentWinRectangle != null){
					removeTile(currentWinRectangle);
				}
				WinTile t = new WinTile(rect.coordX, rect.coordY, game);
				currentWinRectangle = rect;
				boardTiles.put(rect, t);
				repaint();
				System.out.println("Win Tile");
			}
		}
	}
	
	public void showMessage(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	public void removeTile(Rectangle2DCoord rect){
		DesignModeController toc=new DesignModeController();
		if(boardTiles.keySet().contains(rect)){
			
			//TODO: Insert real removeTile method from the controller
			try{
				toc.removeTile(rect.coordX, rect.coordY);
			}catch (Exception e){
				System.out.println("Tile does not exist within game");
			}
			boardTiles.remove(rect);
			repaint();
			}
	}

	public ArrayList<Rectangle2DCoord> getRectangles(){
		return rectangles;
	}
	
	
	//Work in progress on this one
	public void addPlayer(Rectangle2DCoord rect){
		DesignModeController toc = new DesignModeController();
		if(boardTiles.keySet().contains(rect)){
			Ellipse2D player = new Ellipse2D.Float(GAP*5 + WIDTH*4, GAP*7 + rect.coordX, rect.coordY, HEIGHT);
			try {
				NormalTile t=toc.addNormalTile(rect.coordX, rect.coordY);
				toc.assignStartingTile(rect.coordX, rect.coordY, playerNumber);
				boardTiles.put(rect, t);
				repaint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Player exists");
				e.printStackTrace();
			}
		}else{
			System.out.println("Please choose a valid tile");
		}
	}
	
	class MouseSelectionListener implements MouseListener{
		public void mouseClicked(MouseEvent ev){
			int x = ev.getX();
			int y = ev.getY();
			for(Rectangle2DCoord rect : getRectangles()){
				if(rect.coordRectangle.contains(x, y)){
					System.out.println("Found me!");
					if(mode == Mode.ADD_TILE){
						addTile(rect);
					}
					else if(mode == Mode.REMOVE_TILE){
						removeTile(rect);
					}
					else if(mode == Mode.PLACE_PLAYER){
						addPlayer(rect);
					}
				}
			}
		}

		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class Rectangle2DCoord{
		Rectangle2D coordRectangle;
		int coordX;
		int coordY;
		
		public Rectangle2DCoord(Rectangle2D aRectangle, int x, int y){
			coordRectangle = aRectangle;
			coordX = x;
			coordY = y;
		}
	}
	
	public enum Mode{
		ADD_TILE, REMOVE_TILE, PLACE_PLAYER, ADD_CONNECTION, REMOVE_CONNECTION
	}
	
	public enum TileType{
		NORMAL, ACTION, WIN
	}
	
}
