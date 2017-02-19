package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DeckPanel extends JPanel {
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new DeckPanel(), BorderLayout.CENTER);
		frame.setSize(220, 300);
	}
	
	//Components
	
	//***TESTING VARIABLES***
	String title = "Roll Die Action Card";
	String descriptionLine1 = "You get another turn!";
	String descriptionLine2 = "Roll the die and move";
	String descriptionLine3 = "your player to a new";
	String descriptionLine4 = "tile.";
	
	//Constructor
	public DeckPanel(){
		//this.setBackground(Color.BLUE);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void doDrawing(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, 220, 300, 30, 30);
		g2d.fill(rect);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Gill Sans",Font.PLAIN , 22));
		g2d.drawString(title, 15 ,50);
		g2d.setFont(new Font("Gill Sans",Font.PLAIN , 20));
		g2d.drawString(descriptionLine1, 20 ,120);
		g2d.drawString(descriptionLine2, 20 ,150);
		g2d.drawString(descriptionLine3, 20 ,180);
		g2d.drawString(descriptionLine4, 20 ,210);
	}
	
}
