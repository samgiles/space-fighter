package jpaddlegame.com.hud;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import jpaddlegame.com.BatchDrawer;
import jpaddlegame.com.ContentStore;
import jpaddlegame.com.Drawable;

public abstract class Control implements Drawable {
	
	private List<ClickListener> mouseDownListeners;
	private List<ClickListener> mouseOverListeners;
	private List<ClickListener> mouseUpListeners;	
	private List<ClickListener> mouseOutListeners;
	private List<ClickListener> mouseClickListeners;
	
	private Image mouseOverImage;
	
	private Image mouseDownImage;
	
	private Image defaultImage;
	
	private Image currentImage;
	
	private float sizeX;
	
	private float sizeY;
	
	private float positionX;
	
	private float positionY;

	
	public Control(int defaultImageId, int mouseDownImageId, int mouseOverImageId, float posX, float posY) {
		setDefaultImage(ContentStore.getStore().getResource(defaultImageId));
		setMouseDownImage(ContentStore.getStore().getResource(mouseDownImageId));
		setMouseOverImage(ContentStore.getStore().getResource(mouseOverImageId));
		
		setX(posX);
		setY(posY);
		
		sizeX = defaultImage.getWidth(null);
		sizeY = defaultImage.getHeight(null);
		
		currentImage = defaultImage;
		mouseDownListeners = new Vector<ClickListener>();
		mouseOverListeners = new Vector<ClickListener>();
		mouseUpListeners = new Vector<ClickListener>();
		mouseOutListeners = new Vector<ClickListener>();
		mouseClickListeners = new Vector<ClickListener>();
	}
	
	protected void setMouseOverImage(final Image image) {
		mouseOverImage = image;
	}
	
	protected void setMouseDownImage(final Image image) {
		mouseDownImage = image;
	}
	
	protected void setDefaultImage(final Image image) {
		defaultImage = image;
	}
	
	public void setX(float posX) {
		positionX = posX;
	}
	
	public void setY(float posY) {
		positionY = posY;
	}
	
	public float getX() {
		return positionX;
	}
	
	public float getY() {
		return positionY;
	}
	
	public float getSizeX() {
		return sizeX;
	}
	
	public float getSizeY() {
		return sizeY;
	}

	
	public void addClickListener(ClickListener clickListener) {
		this.mouseClickListeners.add(clickListener);
	}
	
	public void notifyClick(MouseEvent e) {
		e.setSource(this);
		currentImage = mouseOverImage;
		for(ClickListener listener : this.mouseClickListeners) {
			listener.onClick(e);
		}
	}
	
	public void notifyMouseOver(MouseEvent e) {
		e.setSource(this);
		currentImage = mouseOverImage;
		for(ClickListener listener : this.mouseOverListeners) {
			listener.onMouseOver(e);
		}
	}
	
	public void notifyMouseDown(MouseEvent e) {
		e.setSource(this);
		currentImage = mouseDownImage;
		for(ClickListener listener : this.mouseDownListeners) {
			listener.onMouseDown(e);
		}
	}
	
	public void notifyMouseUp(MouseEvent e) {
		e.setSource(this);
		currentImage = mouseOverImage;
		for(ClickListener listener : this.mouseUpListeners) {
			listener.onMouseUp(e);
		}
	}
	
	public void notifyMouseOut(MouseEvent e) {
		e.setSource(this);
		currentImage = defaultImage;
		for (ClickListener listener : this.mouseOutListeners) {
			listener.onMouseOut(e);
		}
	}
	
	@Override
	public void paint(BatchDrawer g) {
		g.drawImage(currentImage, (int)positionX, (int)positionY, 0, 1000);
	}
}
