package jpaddlegame.com.game;

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
	
	Vector2d position;
	
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
		
		position = new Vector2d(0, 0);
	}
	
	/**
	 * Get the rectangular projection of this Camera.
	 * @return
	 */
	public Rectangle toRectangle() {
		return new Rectangle((int)position.getX(), (int)position.getY(), sizeX, sizeY);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
