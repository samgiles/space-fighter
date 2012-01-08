package jpaddlegame.com;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.*;
/**
 * A simple BackBuffer that is used to buffer the current frame before drawing to the main window.
 * @author Samuel Giles
 */
public class BackBuffer {
	
	/**
	 * The Image the backbuffer is drawn to.
	 */
	private Image offscreenImage;
	
	/**
	 * The graphics object that belongs to the backbuffer.
	 */
	Graphics offscreenGraphics;
	
	/**
	 * The width of the back buffer.
	 */
	int width;
	
	/**
	 * The height of the back buffer.
	 */
	int height;
	
	/**
	 * The parent awt component.
	 */
	Component owner;
	
	/**
	 * The property listener that listens for a change in size of the component.  This allows the backbuffer to handle its own resizing automatically, allowing this 
	 * class to become more pluggable.
	 */
	ComponentListener sizeChangeListener = null;
	
	/**
	 * Constructs a BackBuffer given an owner component.
	 * @param Component owner The owner of this BackBuffer.
	 */
	public BackBuffer(Component owner) {
		this.owner = owner;
		setUpBackImage();
	}
	
	/**
	 * Set up the BackBuffer image with the current objects state.
	 */
	private void setUpBackImage() {
		offscreenImage = owner.createImage(owner.getWidth(), owner.getHeight());
		offscreenGraphics = offscreenImage.getGraphics();
		Color color = offscreenGraphics.getColor();
		offscreenGraphics.setColor(Color.black);
		offscreenGraphics.fillRect(0, 0, owner.getWidth(), owner.getHeight());
		offscreenGraphics.setColor(color);
	}
	
	/**
	 * Clear the BackBuffer.
	 */
	public void clear() {
		setUpBackImage();
	}
	
	
	/**
	 * Get the graphics object for the BackBuffer.
	 * @return Graphics The BackBuffer's Graphics object.
	 */
	public Graphics getGraphics() {
		return offscreenGraphics;
	}
	
	/**
	 * Get the BackBuffer image.
	 * @return Image The BackBuffer image.
	 */
	public Image getBackBuffer() {
		return offscreenImage;
	}

}
