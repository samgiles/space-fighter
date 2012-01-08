/**
 * 
 */
package jpaddlegame.com.game;

import java.awt.Component;
import java.awt.Graphics;

/**
 * @author Samuel Giles
 *
 */
public class World implements Drawable{
	
	private Map map;
	
	
	public World(Component worldWiewPort) {
		Camera cam = Camera.getCamera(); // HACK:  This seemes hacky.  Sets up the singleton of the camera, and supplies it with a viewport.
		cam.setViewport(worldWiewPort);
		
		map = Map.load(0);

	}
	
	public Map getMap(){
		return map;
	}

	@Override
	public void paint(Graphics g) {
		map.paint(g);
	}
	
	
}
