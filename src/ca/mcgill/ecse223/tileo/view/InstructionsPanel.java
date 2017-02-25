package ca.mcgill.ecse223.tileo.view;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class InstructionsPanel extends JPanel {
	
	//***TESTING***
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(220, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new InstructionsPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
	//Components
	String text = "Instructions";
	JTextArea instructions = new JTextArea(text, 20, 14);
	
	public void initComponents(){
		instructions.setBackground(Color.LIGHT_GRAY);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		
		instructions.setFont(new Font("Futura", Font.PLAIN, 23));
		instructions.setEditable(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void doDrawing(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, 220, 300, 30, 30);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(rect);
	}
	
}
