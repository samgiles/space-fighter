package jpaddlegame.com.game;

import java.awt.Color;

import javax.vecmath.Vector2d;

import jpaddlegame.com.BatchDrawer;

public class Enemy extends Character {

	DynamicEntity target;
	
	
	public Enemy(Map map) {
		super(map);
		this.setImageId(0);
		this.health = 90;
	}

	public void setTarget(DynamicEntity character) {
		this.target = character;
	}
	
	public void paint(BatchDrawer g){
		
		Vector2d pos = Camera.getCamera().convertToScreenCoordinates(this.getPosition());
		
		if (this.getHealth() > 50){
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.green, 1);
		} else if (this.getHealth() > 20){
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.orange, 1);
		} else {
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.red, 1);
		}
		
		
		super.paint(g);
	}
	
	public void update() {
		
		if (this.getHealth() < 0){
			return;
		}
		
		if (target == null){
			super.update();
			return;
		}
		
		Vector2d target = new Vector2d(this.target.getPosition().getX(), this.target.getPosition().getY());
		
		target.sub(this.getCenter());
		
		double angle = Math.atan2(target.getY(), target.getX() );
		
	
		if (Math.random() > 0.7){
			this.rotation = angle + 1.57;
		}
		
			if (target.length() > 100){
				this.moveForward(2);
			} else {
			this.moveBackward(2);
			}
		
			double rand = Math.random();
			
			if (firePowerRemaining < 10){
				if (rand > 0.9){
					this.fire();
				}
			} else {
				if (rand > 0.6){
					this.fire();
				}
			}
		
			
		
		super.update();
		
	}
	
	public void fire(){
		if (firePowerRemaining > 0){
			this.map.addTemporaryMapEntity(new Projectile(this, 4, 1.0));
			firePowerRemaining -= 2;
		}
	}
	
}
