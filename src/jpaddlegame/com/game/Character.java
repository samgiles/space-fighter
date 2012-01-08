package jpaddlegame.com.game;

import javax.vecmath.Vector2d;

public class Character extends DynamicEntity {
	
	public Character() {
		super();
		this.setImageId(1);
		this.setPosition(new Vector2d(Camera.getCamera().toWorldRectangle().getCenterX(), Camera.getCamera().toWorldRectangle().getCenterY()));
	}
	
	
	
}
