package jpaddlegame.com.game;

import javax.vecmath.Vector2d;

public class Character extends DynamicEntity {
	
	public Character(Map map) {
		super(map);
		this.setImageId(1);
		this.setPosition(new Vector2d(Camera.getCamera().toWorldRectangle().getCenterX(), Camera.getCamera().toWorldRectangle().getCenterY()));
	}
	
	public void fire() {
		this.map.addTemporaryMapEntity(new Projectile(this));
	}
	
}
