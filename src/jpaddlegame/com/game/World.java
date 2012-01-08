/**
 * 
 */
package jpaddlegame.com.game;

import jpaddlegame.com.BatchDrawer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author Samuel Giles
 *
 */
public class World implements Drawable, Updateable{
	
	private Map map;
	
	private Character character;
	
	public World(Component worldWiewPort) {
		Camera cam = Camera.getCamera(); // HACK:  This seemes hacky.  Sets up the singleton of the camera, and supplies it with a viewport.
		cam.setViewport(worldWiewPort);
		
		map = new Map();
		character = new Character(map);
		map.addEntity(character);
		cam.setCenterOn(character);
	}
	
	public Map getMap(){
		return map;
	}

	@Override
	public void paint(BatchDrawer g) {
		
		g.drawString("Health: ", 20, 20, Color.white, -1);
		if (character.getHealth() > 70){
			g.drawFillRect((int)character.getHealth(), 10, 65, 11, Color.green, -1);
		} else if (character.getHealth() > 30){
			g.drawFillRect((int)character.getHealth(), 10, 65, 11, Color.orange, -1);
		} else {
			g.drawFillRect((int)character.getHealth(), 10, 65, 11, Color.red, -1);
		}
		map.paint(g);
	}
	
	public Character getCharacter(){
		return character;
	}

	@Override
	public void update() {
		map.update();
	}
	
	
}
