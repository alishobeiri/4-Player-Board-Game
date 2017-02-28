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
	public static final int C_WIDTH = 10;
	public static final int C_LENGTH = 15;
	
	
	//Attributes
	public Game.Mode m;
	public ArrayList<Rectangle2DCoord> rectangles = new ArrayList<Rectangle2DCoord>();
	public HashMap<Rectangle2DCoord, Tile> boardTiles = new HashMap<Rectangle2DCoord, Tile>();

	public ArrayList<Ellipse2DCoord> playerTiles = new ArrayList<Ellipse2DCoord>();

	public ArrayList<Rectangle2D> connectors = new ArrayList<Rectangle2D>();
	public HashMap<Rectangle2D, Connection> boardConnections = new HashMap<Rectangle2D, Connection>();

	Mode mode;
	TileType tileType = TileType.NORMAL;
	int inactiveTurns = 0;
	int playerNumber=1;
	Rectangle2DCoord currentWinRectangle;
	Rectangle2DCoord prev = null;
	
	TileO tileo = new TileO();

	Game game=TileOApplication.getCurrentGame();

	
	public void initComponents(){
		//Initialize the rectangles list to cover the whole board
		for(int i = 0; i < HORIZONTAL_RECTANGLES; i++){
			for(int j = 0; j < VERTICAL_RECTANGLES; j++){
				Rectangle2D r2d = new Rectangle2D.Float(GAP*(j+1) + WIDTH*j, GAP*(i+1) + HEIGHT*i, WIDTH, HEIGHT);
				Rectangle2DCoord currentRect = new Rectangle2DCoord(r2d, j, i);
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
		/*for(Rectangle2DCoord rectangle: rectangles){
			g2d.setColor(new Color(195, 195, 195));
			g2d.fill(rectangle.coordRectangle);
		}*/
		
		if(game.getMode() == Game.Mode.DESIGN){
			for(Rectangle2DCoord rectangle: rectangles){
				g2d.setColor(new Color(230, 230, 230));
				g2d.draw(rectangle.coordRectangle);
			}
		}
		
		//Paint currently existing tiles
		for(Rectangle2DCoord rectangle: boardTiles.keySet()){		
			g2d.setColor(rectangle.color);
			g2d.fill(rectangle.coordRectangle);
			g2d.setColor(Color.GRAY);
			g2d.draw(rectangle.coordRectangle);
		}
		

		//Look
		
		for(Ellipse2DCoord circle: playerTiles){
			Ellipse2D player = new Ellipse2D.Float(GAP*circle.coordX + WIDTH*circle.coordX, GAP*circle.coordY+HEIGHT*circle.coordY, WIDTH, HEIGHT);
			g2d.setColor(circle.color);
			g2d.fill(player);
		}
		

		Ellipse2D player = new Ellipse2D.Float(GAP*5 + WIDTH*4, GAP*7 + HEIGHT*6, WIDTH, HEIGHT);
		g2d.setColor(Color.RED);
		g2d.fill(player);
		
		g2d.setColor(Color.DARK_GRAY);
		for(Rectangle2D connector: connectors){
			g2d.fill(connector);
		}
		
		if(prev != null){
			g2d.setColor(Color.BLUE);
			g2d.fill(prev.coordRectangle);
			g2d.setColor(Color.GRAY);
			g2d.draw(prev.coordRectangle);
		}


	}
	
	public Rectangle2D getHorizontalConnectionRect(Tile tile1, Tile tile2){
		Tile tile;
		if(tile1.getX() < tile2.getX()){
			tile = tile1;
		}
		else{
			tile = tile2;
		}
		Rectangle2D rect = new Rectangle2D.Float((tile.getX()+1)*WIDTH + (tile.getX()+1)*GAP, (tile.getY())*HEIGHT + (tile.getY()+1)*GAP + 10, C_LENGTH, C_WIDTH);
		return rect;
	}
	
	public Rectangle2D getVerticalConnectionRect(Tile tile1, Tile tile2){
		Tile tile;
		if(tile1.getY() < tile2.getY()){
			tile = tile1;
		}
		else{
			tile = tile2;
		}
		Rectangle2D rect = new Rectangle2D.Float((tile.getX())*WIDTH + (tile.getX()+1)*GAP + 10, (tile.getY()+1)*HEIGHT + (tile.getY()+1)*GAP ,C_WIDTH ,C_LENGTH);
		return rect;
	}
	
	public void addConnection(Rectangle2DCoord rect1, Rectangle2DCoord rect2){
		Tile tile1 = boardTiles.get(rect1);
		Tile tile2 = boardTiles.get(rect2);
		Connection c = null;
		boolean enter = true;
		
		DesignModeController dmc = new DesignModeController();
		
		try{
		c = dmc.connectTiles(tile1, tile2);
		}
		catch(InvalidInputException e){
			enter = false;
			prev = null;
			repaint();
			System.out.println("Tiles must be adjacent.");
		}
		
		if(enter){
			
			Rectangle2D connector = null;
			if(tile1.getX() == tile2.getX()){
				connector = getVerticalConnectionRect(tile1, tile2);
			}
			else if(tile1.getY() == tile2.getY()){
				connector = getHorizontalConnectionRect(tile1, tile2);
			}
			if(connector != null){
				connectors.add(connector);
				boardConnections.put(connector, c);
				System.out.println("Connections added");
				System.out.print(boardConnections);
			}
			repaint();
		}
	}
	
	public void removeConnection(Rectangle2DCoord rect1, Rectangle2DCoord rect2){
		/*Tile tile1 = boardTiles.get(rect1);
		Tile tile2 = boardTiles.get(rect2);
		Connection c = null;
		boolean enter = true;
		
		DesignModeController dmc = new DesignModeController();
		
		try{
		c = dmc.deleteConnection(tile1, tile2);
		}
		catch(InvalidInputException e){
			enter = false;
			prev = null;
			repaint();
			System.out.println("Tiles must be adjacent.");
		}
		
		if(enter){
			
			Rectangle2D connector = null;
			if(tile1.getX() == tile2.getX()){
				connector = getVerticalConnectionRect(tile1, tile2);
			}
			else if(tile1.getY() == tile2.getY()){
				connector = getHorizontalConnectionRect(tile1, tile2);
			}
			if(connector != null){
				connectors.add(connector);
				boardConnections.put(connector, c);
				System.out.println("Connections added");
				System.out.print(boardConnections);
			}
			repaint();*/
	}
	
	
	public void addTile(Rectangle2DCoord rect){
		DesignModeController toc=new DesignModeController();
		if(!(boardTiles.keySet().contains(rect))){
			
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
				rect.setColor(Color.pink);
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
	
	public void removeTile(Rectangle2DCoord rect){
		DesignModeController toc=new DesignModeController();
		if(boardTiles.keySet().contains(rect)){
			try{
				toc.removeTile(rect.coordX, rect.coordY);
				for(Player player: game.getPlayers()){
					if(player.getStartingTile().getX()==rect.coordX && player.getStartingTile().getY()==rect.coordY){
						System.out.println("Player exists on tile");
						player.setStartingTile(null);
					}
				}
				for(Ellipse2DCoord circle: playerTiles){
					if(circle.coordX==rect.coordX&&circle.coordY==rect.coordY){
						playerTiles.remove(circle);
					}
				}
			}catch (Exception e){
				System.out.println("Tile does not exist within game");
			}
			rect.setColor(Color.WHITE);
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
			try {
				System.out.println(playerNumber);
				Tile t=toc.assignStartingTile(rect.coordX, rect.coordY, playerNumber);
				Ellipse2DCoord circle=new Ellipse2DCoord(rect.coordX, rect.coordY);
				playerTiles.add(circle);
				switch(playerNumber){
				case 1:
					circle.setColor(Color.RED);
					break;
				case 2:
					circle.setColor(Color.BLUE);
					break;
				case 3:
					circle.setColor(Color.YELLOW);
					break;
				case 4:
					circle.setColor(Color.BLUE);
					break;
				}
				System.out.println("Added player");
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
					if(mode == Mode.ADD_TILE){
						prev = null;
						addTile(rect);
					}
					else if(mode == Mode.REMOVE_TILE){
						prev = null;
						removeTile(rect);
					}
					else if(mode == Mode.PLACE_PLAYER){
						prev = null;
						addPlayer(rect);
					}else if(mode == Mode.ADD_CONNECTION){
						//addConnection()
					}
					else if(mode == Mode.ADD_CONNECTION){
						if(prev == null){
							prev = rect;
							repaint();
						}
						else{
							addConnection(prev, rect);
							prev = null;
						}
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
		Color color;
		
		public Rectangle2DCoord(Rectangle2D aRectangle, int x, int y){
			coordRectangle = aRectangle;
			coordX = x;
			coordY = y;
			color=Color.WHITE;
		}
		
		public void setColor(Color c){
			color=c;
		}
	}
	
	class Ellipse2DCoord{
		Ellipse2D circle;
		int coordX;
		int coordY;
		Color color;
		
		public Ellipse2DCoord(int x, int y){
			coordX = x;
			coordY = y;
		} 
		
		public void setColor(Color c){
			color=c;
		}
	}
	
	public enum Mode{
		ADD_TILE, REMOVE_TILE, PLACE_PLAYER, ADD_CONNECTION, REMOVE_CONNECTION
	}
	
	public enum TileType{
		NORMAL, ACTION, WIN
	}
	
}
