package jpaddlegame.com.game;

import jpaddlegame.com.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.vecmath.Vector2d;

/**
 * This camera simply takes a section of the World to draw and converts the world coordinates to Screen coordinates.
 * @author Sam
 *
 */
public class Camera implements Drawable{
	
	int sizeX;
	int sizeY;
	
	Vector2d worldPosition;
	
	private Component viewport;
	
	private static Camera cameraInstance;
	
	/**
	 * The world this camera is looking at.
	 */
	public static Camera getCamera(){
		if (cameraInstance == null){
			cameraInstance = new Camera(100, 100); // TODO Set the size properly.
		}
		
		return cameraInstance;
	}
	
	private Camera(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		worldPosition = new Vector2d(100, 100); // TODO Set this properly.
	}
	
	/**
	 * Set the Camera to a component
	 * @param game
	 */
	public void setViewport(Component viewport){
		this.viewport = viewport;
		
		// Sync the camera size to the viewport size.
		this.sizeX = viewport.getX();
		this.sizeY = viewport.getY();
	}
	
	public Vector2d convertToScreenCoordinates(Vector2d worldPosition) {
		
		if (viewport == null){
			throw new NullPointerException("Viewport not set, set viewport with Camera.setViewport(Component viewport).");
		}
		
		double screenPositionX = worldPosition.getX() - this.worldPosition.getX();
		double screenPositionY = worldPosition.getY() - this.worldPosition.getY();
		
		return new Vector2d(screenPositionX, screenPositionY);
	}
	
	public void move(Vector2d moveamount) {
		this.worldPosition.add(moveamount);
	}
	
	/**
	 * Get the rectangular projection of this Camera.
	 * @return
	 */
	public Rectangle toRectangle() {
		return new Rectangle((int)worldPosition.getX(), (int)worldPosition.getY(), sizeX, sizeY);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
