package jpaddlegame.com.game;

import java.util.Date;

import javax.vecmath.Vector2d;

public class Character extends DynamicEntity {
	
	double health = 100;
	
	int killCount = 0;
	
	int firePowerRemaining = 100;
	
	long lastUpdate = 0;
	
	public Character(Map map) {
		super(map);
		this.setImageId(1);
		this.setPosition(new Vector2d(Camera.getCamera().toWorldRectangle().getCenterX(), Camera.getCamera().toWorldRectangle().getCenterY()));
	}
	
	public void fire() {
		if (firePowerRemaining > 0){
			this.map.addTemporaryMapEntity(new Projectile(this, 3, 2.0));
			firePowerRemaining -= 1;
		}
	}
	
	public double getHealth(){
		return health;
	}
	
	public int getKillCount() {
		return killCount;
	}
	
	public void addHealth(double value) {
		health += value;
	}
	
	public void killedCharacter(Character who){
		killCount++;
	}
	
	public int getFirePowerRemaining() {
		return firePowerRemaining;
	}
	
	
	public void takeDamage(Character from, double amount){
		
		health -= amount;
		
		if (health < 0){
			// Died
			from.killedCharacter(this);
			this.health = 100;
			this.firePowerRemaining = 100;
			this.setPosition(new Vector2d(0, 0));
			killCount--;
		}
	}
	

	public void update() {
		
		
		long thisUpdate = (long)(new Date().getTime());
		
		if ((thisUpdate - lastUpdate) > 500){
			if (firePowerRemaining < 100){
				firePowerRemaining++;
			}
			lastUpdate = thisUpdate;
		}
		
		super.update();
	}
}
