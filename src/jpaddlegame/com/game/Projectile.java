package jpaddlegame.com.game;

import java.util.Date;

import javax.vecmath.Vector2d;

public class Projectile extends DynamicEntity implements TemporaryEntity {
	private Character owner;
	private double power;
	
	public Character getOwner() {
		return owner;
	}

	private boolean alive = true;
	
	public Projectile(Character owner, int image, double power) {
		super(owner.map);
		this.setImageId(image);
		this.rotation = owner.rotation;
		this.position = new Vector2d(owner.position.x, owner.position.y);
		this.moveForward(50 + (int)owner.getVelocity().length());
		this.owner = owner;
		this.power = power;
	}
	
	public void kill() {
		alive = false;
	}
	
	public double getPower() {
		return this.power;
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
