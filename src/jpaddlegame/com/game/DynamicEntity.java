package jpaddlegame.com.game;

import javax.vecmath.Vector2d;

public class DynamicEntity extends Entity {

	private Vector2d velocity;
	
	public DynamicEntity(Map map) {
		super(map);
		velocity = new Vector2d(0, 0);
	}

	public void moveForward(int speed){
		// Use polar coordinates to get a vector ahead of the rotation.
		double x = (Math.cos(this.rotation - 1.53)) * speed;
		double y = (Math.sin(this.rotation - 1.53)) * speed;
		
		this.velocity.add(new Vector2d(x, y));
	}
	
	public void moveBackward(int speed) {
		// Use polar coordinates to get a vector ahead of the rotation.
				double x = (Math.cos(this.rotation - 1.53)) * speed;
				double y = (Math.sin(this.rotation - 1.53)) * speed;
				
				this.velocity.add(new Vector2d(-x, -y));
	}

	public void rotate(double rotation){
		this.rotation += rotation;
	}
	
	public Vector2d getVelocity() {
		return velocity;
	}

	@Override
	public void update() {
		// Integrate the velocity
		this.position.add(velocity);
		this.velocity.scale(0.9);
	}

}
