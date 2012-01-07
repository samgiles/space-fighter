package jpaddlegame.com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.vecmath.Tuple2d;
import javax.vecmath.Vector2d;


public class Entity implements Serializable, Drawable, Spatial, Updateable{

	private Vector2d position;
	private Vector2d size;
	
	private transient Image entityImage;
	
	private int imageId;
	
	public Entity(){
		
	}
	
	public Entity(int imageId){
		entityImage = ContentStore.getStore().getResource(imageId);
	}
	
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
		entityImage = ContentStore.getStore().getResource(imageId);
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public void setSize(Vector2d size) {
		this.size = size;
	}

	@Override
	public void paint(Graphics g) {
		
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
