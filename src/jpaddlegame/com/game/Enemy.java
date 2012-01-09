package jpaddlegame.com.game;

import java.awt.Color;

import javax.vecmath.Vector2d;

import jpaddlegame.com.BatchDrawer;

public class Enemy extends Character {

	public Enemy(Map map) {
		super(map);
	}

	public void paint(BatchDrawer g){
		
		Vector2d pos = Camera.getCamera().convertToScreenCoordinates(this.position);
		
		g.drawFillRect((int)this.getHealth() / 10, 3, (int)pos.x - 2,  (int)pos.y - 2, Color.green, 1);
		
		super.paint(g);
	}
	
	public void update() {
		
	}
	
}
