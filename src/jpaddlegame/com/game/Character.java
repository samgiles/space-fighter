package jpaddlegame.com.game;

import javax.vecmath.Vector2d;

public class Character extends DynamicEntity {
	
	double health = 100;
	
	int killCount = 0;
	
	public Character(Map map) {
		super(map);
		this.setImageId(1);
		this.setPosition(new Vector2d(Camera.getCamera().toWorldRectangle().getCenterX(), Camera.getCamera().toWorldRectangle().getCenterY()));
	}
	
	public void fire() {
		this.map.addTemporaryMapEntity(new Projectile(this));
	}
	
	public double getHealth(){
		return health;
	}
	
	public void addHealth(double value) {
		health += value;
	}
	
	public void killedCharacter(Character who){
		killCount++;
	}
	
	public void takeDamage(Character from){
		
		health -= 0.2;
		
		if (health < 0){
			from.killedCharacter(this);
		}
	}
	
}
