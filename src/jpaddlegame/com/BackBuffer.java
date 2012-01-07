package jpaddlegame.com;

import java.awt.*;
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
	PropertyChangeListener sizeChangeListener = null;
	
	/**
	 * Constructs a BackBuffer given an owner component.
	 * @param Component owner The owner of this BackBuffer.
	 */
	public BackBuffer(Component owner) {
		this.owner = owner;
		this.width = owner.getWidth();
		this.height = owner.getHeight();
		
		setUpBackImage();
		
		owner.addPropertyChangeListener("Size", getSizeListener());

	}
	
	/**
	 * Gets the PropertyChangeListener that is used to listen to the size property of the component.
	 * @return
	 */
	private PropertyChangeListener getSizeListener() {
		
		// If the listener hasn't been set up yet then set it up.
		if (sizeChangeListener == null) {
			sizeChangeListener =  new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					if (arg0.getNewValue() instanceof Dimension){
						Dimension newDimension = (Dimension)arg0.getNewValue();
					
						BackBuffer.this.setSize(newDimension);
					}
				}
			};
			
		}
		
		return sizeChangeListener;
		
	}
	
	/**
	 * Set up the BackBuffer image with the current objects state.
	 */
	private void setUpBackImage() {
		offscreenImage = owner.createImage(width, height);
		offscreenGraphics = offscreenImage.getGraphics();
	}
	
	/**
	 * Clear the BackBuffer.
	 */
	public void clear() {
		setUpBackImage();
	}
	
	/**
	 * Set the size of the BackBuffer.
	 * @param Dimension dimension The dimension to set the size from.
	 */
	private void setSize(Dimension dimension) {
		this.width = dimension.height;
		this.height = dimension.width;
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
