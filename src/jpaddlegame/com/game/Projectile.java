package jpaddlegame.com.game;

import java.util.Date;

import javax.vecmath.Vector2d;

public class Projectile extends DynamicEntity implements TemporaryEntity {
	private Character owner;

	
	private boolean alive = true;
	
	public Projectile( Character owner) {
		super(owner.map);
		this.setImageId(3);
		this.rotation = owner.rotation;
		this.position = new Vector2d(owner.position.x, owner.position.y);
		this.moveForward(40 + (int)owner.getVelocity().length());
	}
	
	public void update(){
		if (this.getVelocity().length() < 5){
			alive = false;
		}
		super.update();
	}

	@Override
	public boolean isDead() {
		return !alive;
	}
}
