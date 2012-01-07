package jpaddlegame.com;

import java.applet.Applet;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JApplet;

public class BackBuffer {
	private Image offscreenImage;
	Graphics offscreenGraphics;
	
	int width;
	int height;
	
	Applet owner;
	
	PropertyChangeListener sizeChangeListener = null;
	
	public BackBuffer(int width, int height, Applet owner) {
		this.width = width;
		this.height = height;
		this.owner = owner;
		
		setUpBackImage();
		
		owner.addPropertyChangeListener("Size", getSizeListener());

	}
	
	
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
	
	private void setUpBackImage() {
		offscreenImage = owner.createImage(width, height);
		offscreenGraphics = offscreenImage.getGraphics();
	}
	
	
	public void clear() {
		setUpBackImage();
	}
	
	private void setSize(Dimension dimension) {
		this.width = dimension.height;
		this.height = dimension.width;
		setUpBackImage();
	}
	
	public Graphics getGraphics() {
		return offscreenGraphics;
	}
	
	public Image getBackBuffer() {
		return offscreenImage;
	}

}
