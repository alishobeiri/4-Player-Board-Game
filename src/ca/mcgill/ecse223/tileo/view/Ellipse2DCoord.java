package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.mcgill.ecse223.tileo.model.Player;

public class Ellipse2DCoord {
	Ellipse2D circle;
	int coordX;
	int coordY;
	Color color;
	Player player;

	public Ellipse2DCoord(int x, int y) {
		coordX = x;
		coordY = y;
	}

	public void setColor(Color c) {
		color = c;
	}

	public int getX() {
		return coordX;
	}

	public int getY() {
		return coordY;
	}
}
