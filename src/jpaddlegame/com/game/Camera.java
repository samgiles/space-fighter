package jpaddlegame.com.game;

import jpaddlegame.com.*;
import jpaddlegame.com.game.entities.spatials.Spatial;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.vecmath.Vector2d;

/**
 * This camera simply takes a section of the World to draw and converts the world coordinates to Screen coordinates.
 * @author Sam
 *
 */
public class Camera {

	Vector2d worldPosition;
	
	private Component viewport;
	
	private ComponentListener sizeListener;
	
	private static Camera cameraInstance;
	
	private Spatial objectCentered = null;
	
	/**
	 * The world this camera is looking at.
	 */
	public static Camera getCamera(){
		if (cameraInstance == null){
			cameraInstance = new Camera(); // TODO Set the size properly.
		}
		
		return cameraInstance;
	}
	
	private Camera(){
		worldPosition = new Vector2d(0, 0); // TODO Set this properly.
	}
	
	/**
	 * Set the Camera to a component
	 * @param game
	 */
	public void setViewport(Component viewport){
		this.viewport = viewport;
	}
	
	public void setCenterOn(Spatial object){
		objectCentered = object;
	}
	
	public Vector2d convertToWorldCoordinates(Vector2d screenPosition) {
		
		if (viewport == null){
			throw new NullPointerException("Viewport not set, set viewport with Camera.setViewport(Component viewport).");
		}
		
		double screenPositionX = worldPosition.getX() + screenPosition.getX();
		double screenPositionY = worldPosition.getY() + screenPosition.getY();
		
		return new Vector2d(screenPositionX, screenPositionY);
	}
	
	public Vector2d convertToScreenCoordinates(Vector2d worldPosition) {
		
		if (viewport == null){
			throw new NullPointerException("Viewport not set, set viewport with Camera.setViewport(Component viewport).");
		}
		
		double screenPositionX = worldPosition.getX() - this.worldPosition.getX();
		double screenPositionY = worldPosition.getY() - this.worldPosition.getY();
		
		return new Vector2d(screenPositionX, screenPositionY);
	}
	
	public Vector2d getWorldPosition() {
		return worldPosition;
	}
	
	public void move(Vector2d moveamount) {
		this.worldPosition.add(moveamount);
	}
	
	/**
	 * Get the rectangular projection of this Camera.
	 * @return
	 */
	public Rectangle toWorldRectangle() {
		return new Rectangle((int)worldPosition.getX(), (int)worldPosition.getY(), viewport.getWidth(), viewport.getHeight());
	}

	public void update() {
		if (objectCentered != null){
			Rectangle rect = this.toWorldRectangle();
			double x = rect.getCenterX();
			double y = rect.getCenterY();
		
			Vector2d centerOn = this.objectCentered.getCenter();
		
			centerOn.sub(new Vector2d(x, y));
		
			
				this.worldPosition.add(centerOn);
		}
	}
}
