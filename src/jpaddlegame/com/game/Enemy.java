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
		
		if (this.getHealth() > 70){
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.green, 1);
		} else if (this.getHealth() > 30){
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.orange, 1);
		} else {
			g.drawFillRect((int)this.getHealth() / 5, 3, (int)pos.x + 2,  (int)pos.y - 5, Color.red, 1);
		}
		super.paint(g);
	}
	
	public void update() {
		
	}
	
}
