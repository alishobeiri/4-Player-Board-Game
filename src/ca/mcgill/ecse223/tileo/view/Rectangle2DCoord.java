package ca.mcgill.ecse223.tileo.view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Rectangle2DCoord {
	Rectangle2D coordRectangle;
	int coordX;
	int coordY;
	Color color;

	public Rectangle2DCoord(Rectangle2D aRectangle, int x, int y) {
		coordRectangle = aRectangle;
		coordX = x;
		coordY = y;
		color = Color.WHITE;
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
