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

	public HashMap<Integer, Ellipse2DCoord> playerTiles = new HashMap<Integer, Ellipse2DCoord>();

	public ArrayList<Connector2D> connectors = new ArrayList<Connector2D>();
	public HashMap<Connector2D, Connection> boardConnections = new HashMap<Connector2D, Connection>();

	Mode mode;
	TileType tileType = TileType.NORMAL;
	int inactiveTurns = 1;
	int playerNumber = 1;
	Rectangle2DCoord currentWinRectangle = null;
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
	
	public void setMode(Mode m){
		this.mode=m;
	}
	// Constructor
	public BoardPanel(Game.Mode m){
		//TESTING TODO: REMOVE
		game.setMode(m);
		initComponents();
		addMouseListener(new MouseSelectionListener());
	}
	
	public BoardPanel(BoardPanel oldBoard){
		this.boardConnections=oldBoard.boardConnections;
		this.boardTiles=oldBoard.boardTiles;
		this.connectors=oldBoard.connectors;
		this.rectangles=oldBoard.rectangles;
		this.playerTiles=oldBoard.playerTiles;
		this.mode=BoardPanel.Mode.GAME;
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
		
		if(game.getMode() == Game.Mode.DESIGN){
			for(Rectangle2DCoord rectangle: rectangles){
				g2d.setColor(new Color(230, 230, 230));
				g2d.draw(rectangle.coordRectangle);
			}
		}
		
		//Paint currently existing tiles
		for(Rectangle2DCoord rectangle: boardTiles.keySet()){
			
			if(game.getMode()==Game.Mode.GAME){
				if(TileOApplication.getDesignPanel().getHasRolled()){
					g2d.setColor(rectangle.color);
					g2d.fill(rectangle.coordRectangle);
					g2d.setColor(Color.GRAY);
					g2d.draw(rectangle.coordRectangle);
				}else{
					g2d.setColor(Color.WHITE);
					g2d.fill(rectangle.coordRectangle);
					g2d.setColor(Color.GRAY);
					g2d.draw(rectangle.coordRectangle);
				}
			}else{
				g2d.setColor(rectangle.color);
				g2d.fill(rectangle.coordRectangle);
				g2d.setColor(Color.GRAY);
				g2d.draw(rectangle.coordRectangle);
			}
		}
		
		for(Connector2D connector: connectors){
			g2d.setColor(Color.DARK_GRAY);
			g2d.fill(connector.c);
		}
		
		if(prev != null){
			g2d.setColor(Color.BLUE);
			g2d.fill(prev.coordRectangle);
			g2d.setColor(Color.GRAY);
			g2d.draw(prev.coordRectangle);
		}
		
		if(currentWinRectangle != null && game.getMode() == Game.Mode.DESIGN){
			g2d.setColor(Color.DARK_GRAY);
			g2d.fill(currentWinRectangle.coordRectangle);
			g2d.setColor(Color.BLACK);
			g2d.draw(currentWinRectangle.coordRectangle);
		}
		
		for(Ellipse2DCoord circle: playerTiles.values()){
			Ellipse2D player = new Ellipse2D.Float(GAP*(circle.coordX+1) + WIDTH*(circle.coordX), GAP*(circle.coordY+1) + HEIGHT*(circle.coordY), WIDTH, HEIGHT);
			g2d.setColor(circle.color);
			g2d.fill(player);
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
	
	public ArrayList<Connector2D> getConnector(Tile tile1, Tile tile2){
		ArrayList<Connector2D> list=new ArrayList<Connector2D>();
		for(Connector2D connect: connectors){
			if((tile1.getX()==connect.tileOne.getX() && tile1.getY()==connect.tileOne.getY() && tile2.getX()==connect.tileTwo.getX() && tile2.getY()==connect.tileTwo.getY()) || (tile1.getY()==connect.tileTwo.getY() && tile1.getX()==connect.tileTwo.getX() && tile2.getY()==connect.tileOne.getY() && tile2.getX()==connect.tileOne.getX())){
				list.add(connect);
			}
		}
		return list;
	}
	
	public void addConnection(Rectangle2DCoord rect1, Rectangle2DCoord rect2){
		if(boardTiles.containsKey(rect1) && boardTiles.containsKey(rect2)){
			Tile tile1 = boardTiles.get(rect1);
			Tile tile2 = boardTiles.get(rect2);
			Connection c = null;
			boolean enter = true;
			
			DesignModeController dmc = new DesignModeController();
			
			try{
				if(tile1.equals(tile2)){
					throw new InvalidInputException("They are the same tile");
				}
			c = dmc.connectTiles(tile1, tile2);
			}
			catch(InvalidInputException e){
				enter = false;
				prev = null;
				repaint();
				System.out.println("Tiles must be adjacent.");
			}
			if(enter){
				Connector2D connector;
				Rectangle2D connect=null;
				if(tile1.getX() == tile2.getX()){
					connect = getVerticalConnectionRect(tile1, tile2);
				}
				else if(tile1.getY() == tile2.getY()){
					connect = getHorizontalConnectionRect(tile1, tile2);
				}
				System.out.println("if entered");
				if(connect != null){
					System.out.println("2nd if entered");
					connector=new Connector2D(tile1, tile2, connect, c);
					connectors.add(connector);
					boardConnections.put(connector, c);
				}
				repaint();
			}
		}
	}
	
	public void removeConnection(Rectangle2DCoord rect1, Rectangle2DCoord rect2){
		if(boardTiles.containsKey(rect1) && boardTiles.containsKey(rect2)){
			Tile tile1 = boardTiles.get(rect1);
			Tile tile2 = boardTiles.get(rect2);
			
			DesignModeController dmc = new DesignModeController();
			
			ArrayList<Connector2D> connector=getConnector(tile1, tile2);
			
			for(Connector2D connect: connector){
				dmc.deleteConnection(connect.connect);
				connectors.remove(connect);
			}
			repaint();
		}
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
					boardTiles.put(rect, t);
					rect.setColor(Color.pink);
					repaint();
					System.out.println("Action Tile: " + inactiveTurns + " inactive turns.");
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					System.out.println("Tile already here");
				}
			}
			else if(tileType == TileType.WIN){
				if(currentWinRectangle != null){
					removeTile(currentWinRectangle);
				}
				Tile t=null;
				t = toc.chooseHiddenTile(rect.coordX, rect.coordY);
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
				try{
				for(Player player: game.getPlayers()){
					if(player.getStartingTile().getX()==rect.coordX && player.getStartingTile().getY()==rect.coordY){
						System.out.println("Player exists on tile");
						player.setStartingTile(null);
					}
				}
				}catch(Exception e){
					
				}
				System.out.println(connectors.size());
				ArrayList<Connector2D> temp=new ArrayList<Connector2D>();
				for(Connector2D connect: connectors){
					System.out.println("Inside Loop");
					if((connect.tileOne.getX()==rect.coordX && connect.tileOne.getY()==rect.coordY) || (connect.tileTwo.getX()==rect.coordX && connect.tileTwo.getY()==rect.coordY)){
						temp.add(connect);
					}
				}
				for(Connector2D connect: temp){
					connectors.remove(connect);
					boardConnections.remove(connect.connect);
					toc.deleteConnection(connect.connect);
				}
				for(Ellipse2DCoord circle: playerTiles.values()){
					if(circle.coordX==rect.coordX&&circle.coordY==rect.coordY){
						playerTiles.remove(circle);
					}
				}
				toc.removeTile(rect.coordX, rect.coordY);
			}catch (InvalidInputException e){
				System.out.println("Tile does not exist within game");
			}
			rect.setColor(Color.WHITE);
			boardTiles.remove(rect);
			
			repaint();
			}
	}

	public void movePlayer(Rectangle2DCoord rect){
		int playerNumber;
		if(rect.color.equals(Color.YELLOW)){
			playerNumber=game.getCurrentPlayer().getNumber();
			Ellipse2DCoord circle=new Ellipse2DCoord(rect.coordX, rect.coordY);
			switch(game.getCurrentPlayer().getColorFullName()){
				case "RED":
					circle.setColor(Color.RED);
					break;
				case "YELLOW":
					circle.setColor(Color.YELLOW);
					break;
				case "BLUE":
					circle.setColor(Color.BLUE);
					break;
				case "GREEN":
					circle.setColor(Color.GREEN);
					break;
			}
			playerTiles.put(playerNumber, circle);
			System.out.println("Homie we made it");
			TileOApplication.getDesignPanel().setHasRolled(false);
			repaint();
		}else{
			showMessage("Please select a valid tile");
		}
		
	}
	
	public void showMessage(String s){
		JOptionPane.showMessageDialog(this, s);
	}
	public ArrayList<Rectangle2DCoord> getRectangles(){
		return rectangles;
	}
	
	public void refreshBoard(){
		repaint();
	}
	
	//Work in progress on this one
	public void addPlayer(Rectangle2DCoord rect){
		DesignModeController toc = new DesignModeController();
		if(boardTiles.keySet().contains(rect)){
			try {
				System.out.println(playerNumber);
				
				if(playerTiles.containsKey(playerNumber)){
					playerTiles.remove(playerNumber);
				}
				
				Tile t=toc.assignStartingTile(rect.coordX, rect.coordY, playerNumber);
				Ellipse2DCoord circle=new Ellipse2DCoord(rect.coordX, rect.coordY);
				playerTiles.put(playerNumber, circle);
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
						circle.setColor(Color.GREEN);
						break;
				}
				System.out.println("Added player");
				repaint();
			} catch (Exception e) {
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
					}
					else if(mode == Mode.ADD_CONNECTION){
						if(prev == null){
							if(boardTiles.containsKey(rect)){
								prev = rect;
								repaint();
							}
							
						}
						else{
							addConnection(prev, rect);
							prev = null;
						}
					}
					else if(mode == Mode.REMOVE_CONNECTION){
						if(prev == null){
							if(boardTiles.containsKey(rect)){
								System.out.println(rect.coordX + " " + rect.coordY);
								prev = rect;
								repaint();
							}
							
						}
						else{
							removeConnection(prev, rect);
							prev = null;
						}
					}else if(mode == Mode.MOVE_PLAYER && TileOApplication.getDesignPanel().getHasRolled()){
						movePlayer(rect);
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
		Player player;
		
		public Ellipse2DCoord(int x, int y){
			coordX = x;
			coordY = y;
		} 
		
		public void setColor(Color c){
			color=c;
		}
	}
	
	class Connector2D{
		Tile tileOne;
		Tile tileTwo;
		Rectangle2D c;
		Connection connect;
		
		public Connector2D(Tile first, Tile second,Rectangle2D rect,Connection d){
			tileOne=first;
			tileTwo=second;
			c=rect;
			connect=d;
		}
		
	}
	
	public enum Mode{
		ADD_TILE, REMOVE_TILE, PLACE_PLAYER, ADD_CONNECTION, REMOVE_CONNECTION, GAME, MOVE_PLAYER
	}
	
	public enum TileType{
		NORMAL, ACTION, WIN
	}
	
}
