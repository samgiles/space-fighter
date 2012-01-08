package jpaddlegame.com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import javax.vecmath.Vector2d;


public class Entity implements Serializable, Drawable, Spatial, Updateable{

	private Vector2d position;
	private Vector2d size;
	
	private transient Image entityImage;
	
	private int imageId;
	
	public Entity(){
		setImageId(0);
	}
	
	public Entity(int imageId){
		setImageId(imageId);
		setPosition(new Vector2d(0, 0));
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
		entityImage = ContentStore.getStore().getResource(imageId);
		
		this.size = new Vector2d(entityImage.getWidth(null), entityImage.getHeight(null));
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public void setSize(Vector2d size) {
		this.size = size;
	}

	@Override
	public void paint(Graphics g) {
		Camera cam = Camera.getCamera(); // TODO: Integrate these two lines into some kind of batching system as they are repeated in every draw from a world object.
		Vector2d screenPosition = cam.convertToScreenCoordinates(position);
		
		g.drawImage(entityImage, (int)screenPosition.getX(), (int)screenPosition.getY(), null);
		
	}
	

	@Override
	public Vector2d getCenter() {
		
		double x = position.getX() + (size.getX() / 2);
		double y = position.getY() + (size.getY() / 2);
		
		return new Vector2d(x, y);
	}

	@Override
	public Vector2d getMax() {
		double x = position.getX() + (size.getX());
		double y = position.getY() + (size.getY());
		
		return new Vector2d(x, y);
	}

	@Override
	public Vector2d getPosition() {
		return position;
	}

	@Override
	public Vector2d getSize() {
		
		return size;
	}

	@Override
	public Rectangle toRectangle() {
		return new Rectangle((int)getSize().getX(), (int)getSize().getY(), (int)getPosition().getX(), (int)getPosition().getY());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
