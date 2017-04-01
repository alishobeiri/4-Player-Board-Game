package ca.mcgill.ecse223.tileo.view;

import java.awt.geom.Rectangle2D;

import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Tile;

public class Connector2D {
	Tile tileOne;
	Tile tileTwo;
	Rectangle2D c;
	Connection connect;

	public Connector2D(Tile first, Tile second, Rectangle2D rect, Connection d) {
		tileOne = first;
		tileTwo = second;
		c = rect;
		connect = d;
	}
}
