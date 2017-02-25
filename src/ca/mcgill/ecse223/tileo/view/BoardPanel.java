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
	public ArrayList<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	//***TESTING*** TODO: REMOVE
	TileO tileo = new TileO();
	Game game = new Game(32, tileo);
	NormalTile tile1 = new NormalTile(0, 0, game);
	NormalTile tile2 = new NormalTile(5, 10, game);
	NormalTile tile3 = new NormalTile(13, 3, game);
	
	
	/*public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BoardPanel(), BorderLayout.CENTER);
		frame.setSize(650, 670);
	}*/
	
	public BoardPanel(){
		addMouseListener(new MouseSelectionListener());
		
		//TESTING TODO: REMOVE
			tiles.add(tile1);
			tiles.add(tile2);
			tiles.add(tile3);
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
		
		rectangles.clear();
		
		//This fills the grid with rectangles
		for(int i = 0; i < HORIZONTAL_RECTANGLES; i++){
			for(int j = 0; j < VERTICAL_RECTANGLES; j++){
		
		/*for(Tile tile: game.getTiles()){
				int i = tile.getX();
				int j = tile.getY();*/
			
				Rectangle2D rectangle = new Rectangle2D.Float(GAP*(j+1) + WIDTH*j, GAP*(i+1) + HEIGHT*i, WIDTH, HEIGHT);
				
				rectangles.add(rectangle);
				//tiles.add(tile);
				
				g2d.setColor(Color.WHITE);
				g2d.fill(rectangle);
				g2d.setColor(Color.DARK_GRAY);
				g2d.draw(rectangle);
				
			}
		}
	}
	
	class MouseSelectionListener implements MouseListener{
		public void mouseClicked(MouseEvent ev){
			int x = ev.getX();
			int y = ev.getY();
			for(Rectangle2D rect : rectangles){
				if(rect.contains(x, y)){
					System.out.println("Found me!");
					//TODO ADD HASHMAP (TILES, RECTNAGLES)
					//tiles.get(rect);
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
	
}
