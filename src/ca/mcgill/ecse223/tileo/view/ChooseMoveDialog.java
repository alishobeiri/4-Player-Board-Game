package ca.mcgill.ecse223.tileo.view;

import javax.swing.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.view.BoardPanel.Mode;
import ca.mcgill.ecse223.tileo.view.BoardPanel.TileType;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;

public class ChooseMoveDialog{
	private int choice = 0;
	public int show(){
		JLabel label = new JLabel("Choose a value");
		JButton one = new JButton("1");
		JButton two = new JButton("2");
		JButton three = new JButton("3");
		JButton four = new JButton("4");
		JButton five = new JButton("5");
		JButton six = new JButton("6");
		one.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 1;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		two.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 2;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		three.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 3;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		four.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 4;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		five.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 5;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		six.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choice = 6;
				JButton button = (JButton)e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
			}
		});
		JPanel buttons = new JPanel();
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(four);
        buttons.add(five);
        buttons.add(six);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.add(label, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.setTitle("Choose Move");
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
		return choice;
	}
}