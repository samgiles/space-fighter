package jpaddlegame.com.game;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

public class Map implements java.io.Serializable, Drawable{

	private List<Entity> mapEntities;
	
	
	
	@Override
	public void paint(Graphics g) {
		// Iterate over all entities and draw them.
		for(Iterator<Entity> it = mapEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			e.paint(g);
		}
	}
}