package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class DeckSetUpPage extends JFrame{
	
	//Value Fields
	private int[] values = new int[5];
	boolean done;
	DesignPage designPage;
	
	//Components
	JLabel rollDieCard = new JLabel("Roll Die Action Card");
	JLabel connectTilesCard = new JLabel("Connect Tiles Action Card");
	JLabel removeConnectionCard = new JLabel("Remove Connection Action Card");
	JLabel teleportCard = new JLabel("Teleport Action Card");
	JLabel loseTurnCard = new JLabel("Lose Turn Action Card");
	Integer[] nums = new Integer[32];
	JComboBox rollDieNum;
	JComboBox connectTilesNum;
	JComboBox removeConnectionNum;
	JComboBox teleportNum;
	JComboBox loseTurnNum;
	JLabel title = new JLabel("Deck Settings");
	JLabel description1 = new JLabel("  Select the number of Action Cards of each kind you want to have");
	JLabel description2 = new JLabel("  in the deck. There must be exactly 32 cards in the deck.");
	JButton cancel = new JButton("Cancel");
	JButton save = new JButton("Save");
	CardsPanel panel = new CardsPanel();
	
	public DeckSetUpPage(DesignPage aDesignPage){
		initComponents();
		designPage = aDesignPage;
	}
	
	public void initComponents(){
		//Frame settings
		setSize(600, 380);
		setResizable(false);
		
		for(int i = 0; i < 32; i++){
			nums[i] = i+1;
		}
		
		//Button Listener
		save.addActionListener(new SaveListener());
		cancel.addActionListener(new CancelListener());
		
		//Font settings
		title.setFont(new Font("Futura", Font.PLAIN, 32));
		description1.setFont(new Font("San Francisco", Font.PLAIN, 15));
		description2.setFont(new Font("San Francisco", Font.PLAIN, 15));
		Font labelFont = new Font("San Francisco", Font.PLAIN, 16);
		rollDieCard.setFont(labelFont);
		connectTilesCard.setFont(labelFont);
		removeConnectionCard.setFont(labelFont);
		teleportCard.setFont(labelFont);
		loseTurnCard.setFont(labelFont);
		
		//Initialize combo boxes
		rollDieNum = new JComboBox(nums);
		connectTilesNum = new JComboBox(nums);
		removeConnectionNum = new JComboBox(nums);
		teleportNum = new JComboBox(nums);
		loseTurnNum = new JComboBox(nums);
		
		//Panel layout
		GroupLayout panelLayout = new GroupLayout(panel);
		panel.setLayout(panelLayout);
		panelLayout.setAutoCreateGaps(true);
		panelLayout.setAutoCreateContainerGaps(true);
		
		//Panel components positioning
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup()
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(rollDieCard, 490, 490, 490)
						.addComponent(rollDieNum, 70, 70, 70))
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(connectTilesCard, 490, 490, 490)
						.addComponent(connectTilesNum, 70, 70, 70))
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(removeConnectionCard, 490, 490, 490)
						.addComponent(removeConnectionNum, 70, 70, 70))
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(teleportCard, 490, 490, 490)
						.addComponent(teleportNum, 70, 70, 70))
				.addGroup(panelLayout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(loseTurnCard, 490, 490, 490)
						.addComponent(loseTurnNum, 70, 70, 70)));
		
		panelLayout.setVerticalGroup(panelLayout.createSequentialGroup()
				.addGap(15, 15, 15)
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(rollDieCard)
						.addComponent(rollDieNum))
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(connectTilesCard)
						.addComponent(connectTilesNum))
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(removeConnectionCard)
						.addComponent(removeConnectionNum))
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(teleportCard)
						.addComponent(teleportNum))
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(loseTurnCard)
						.addComponent(loseTurnNum)));
		
		//Change layout to group layout
		GroupLayout layout = new GroupLayout(getContentPane());
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//Component positioning
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGap(200, 200, 200)
								.addComponent(title))
						.addComponent(description1)
						.addComponent(description2)
				)
				.addComponent(panel, 600, 600, 600)
				.addGroup(layout.createSequentialGroup()
						.addGap(345, 345, 345)
						.addComponent(save, 120, 120, 120)
						.addComponent(cancel, 120, 120, 120))
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(title)
						.addGap(10, 10, 10)
						.addComponent(description1)
						.addComponent(description2)
						.addGap(15, 15, 15)
				)
				.addComponent(panel, 200, 200, 200)
				.addGroup(layout.createParallelGroup()
						.addComponent(save)
						.addComponent(cancel)));
		
	}
	
	class CardsPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			doDrawing(g);
		}
		
		public void doDrawing(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			RoundRectangle2D rect = new RoundRectangle2D.Float(5, 5, 580, 190, 10, 10);
			g2d.setColor(new Color(226, 226, 226));
			g2d.fill(rect);
			g2d.setColor(new Color(210, 210, 210));
			g2d.draw(rect);
		}
	}
	
	public int[] getValues(){
		return values;
	}
	
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			rollDieNum.setSelectedItem(values[0]);
			connectTilesNum.setSelectedItem(values[1]);
			removeConnectionNum.setSelectedItem(values[2]);
			teleportNum.setSelectedItem(values[3]);
			loseTurnNum.setSelectedItem(values[4]);
			dispose();
		}
	}
	
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			values[0] = (int) rollDieNum.getSelectedItem();
			values[1] = (int) connectTilesNum.getSelectedItem();
			values[2] = (int) removeConnectionNum.getSelectedItem();
			values[3] = (int) teleportNum.getSelectedItem();
			values[4] = (int) loseTurnNum.getSelectedItem();
			
			designPage.setCardNumbers(values);
			
			dispose();
		}
	}

}
