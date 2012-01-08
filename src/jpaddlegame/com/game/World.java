/**
 * 
 */
package jpaddlegame.com.game;

import jpaddlegame.com.BatchDrawer;
import java.awt.Component;
import java.awt.Graphics;

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
		character = new Character();
		map.addEntity(character);
		cam.setCenterOn(character);
	}
	
	public Map getMap(){
		return map;
	}

	@Override
	public void paint(BatchDrawer g) {
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
