package jpaddlegame.com.game;

import java.awt.Rectangle;

import javax.vecmath.Vector2d;

/**
 * Represents an object that takes up space in the World.
 * @author Samuel Giles
 */
public interface Spatial {
	
	/**
	 * Gets the central point of this Spatial.
	 * @return
	 */
	Vector2d getCenter();
	
	/**
	 * Gets the bottom right point of this spatial.
	 * @return
	 */
	Vector2d getMax();
	
	/**
	 * Gets the top-left point of this spatial.
	 * @return
	 */
	Vector2d getPosition();
	
	/**
	 * Gets the total size of this spatial.
	 * @return returns Vector2d representing size, X being width, Y being height
	 */
	Vector2d getSize();
	
	/**
	 * Gets the rectangle that this Spatial represents.
	 * @return Rectangle The rectangle representation of this Spatial.
	 */
	Rectangle toRectangle();
	
}
