package ca.mcgill.ecse223.tileo.view;

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
	public ArrayList<Rectangle2DCoord> rectangles = new ArrayList<Rectangle2DCoord>();
	public ArrayList<Tile> gameTiles = new ArrayList<Tile>();
	public HashMap<Rectangle2DCoord, Tile> boardTiles = new HashMap<Rectangle2DCoord, Tile>();
	Mode mode;
	
	//***TESTING*** TODO: REMOVE
	TileO tileo = new TileO();
	Game game = new Game(32, tileo);
	NormalTile tile1 = new NormalTile(0, 0, game);
	NormalTile tile2 = new NormalTile(5, 10, game);
	NormalTile tile3 = new NormalTile(13, 3, game);

	
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
		for(Tile t: gameTiles){
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
	
	public BoardPanel(){
		//TESTING TODO: REMOVE
		gameTiles.add(tile1);
		gameTiles.add(tile2);
		gameTiles.add(tile3);
		
		initComponents();
		addMouseListener(new MouseSelectionListener());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void doDrawing(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Contour line
		RoundRectangle2D contour = new RoundRectangle2D.Float(0, 0, 646, 646, 10, 10);
		g2d.setColor(new Color(208, 208, 208));
		g2d.fill(contour);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.draw(contour);
		
		for(Rectangle2DCoord rectangle: rectangles){
			g2d.setColor(new Color(208, 208, 208));
			g2d.fill(rectangle.coordRectangle);
		}
		
		for(Rectangle2DCoord rectangle: boardTiles.keySet()){		
			g2d.setColor(Color.WHITE);
			g2d.fill(rectangle.coordRectangle);
			g2d.setColor(Color.GRAY);
			g2d.draw(rectangle.coordRectangle);		
			}

	}
	
	public void addTile(Rectangle2DCoord rect){
		if(!(boardTiles.keySet().contains(rect))){
			
			//TODO: Insert real addTile method from the controller
			
			Tile t = new NormalTile(rect.coordX, rect.coordY, game);
			gameTiles.add(t);
			boardTiles.put(rect, t);
			repaint();
		}
	}
	
	public void removeTile(Rectangle2DCoord rect){
		if(boardTiles.keySet().contains(rect)){
			
			//TODO: Insert real removeTile method from the controller
			
			Tile t = boardTiles.get(rect);
			gameTiles.remove(t); //TODO: Check if this is still necessary
			boardTiles.remove(rect);
			repaint();
			}
	}

	public ArrayList<Rectangle2DCoord> getRectangles(){
		return rectangles;
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
		ADD_TILE, REMOVE_TILE
	}
	
}
