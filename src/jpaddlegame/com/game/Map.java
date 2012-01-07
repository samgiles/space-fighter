package jpaddlegame.com.game;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

public class Map implements java.io.Serializable, Drawable, Updateable{

	private List<Entity> mapEntities;
	
	/**
	 * Temporary map entities that are not supposed to be serialized to the map file.
	 */
	private transient List<Entity> temporaryEntities;
	
	int sizeX = 500;
	int sizeY = 500;
	
	
	/**
	 * Adds an entity to the map.
	 * @param entity An entity.
	 */
	public void addEntity(Entity entity){
		if (mapEntities.contains(entity)){
			return;
		}
		
		mapEntities.add(entity);
	}
	
	public void addTemporaryMapEntity(Entity entity){
		if (temporaryEntities.contains(entity)){
			return;
		}
		
		temporaryEntities.add(entity);
	}
	
	public void update(){
		// Iterate over all persistable entities and update them.
		for (Iterator<Entity> it = mapEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			e.update();
		}
		
		// Iterate over all temporary entities and update them.
		for (Iterator<Entity> it = temporaryEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			e.update();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// Iterate over all persistable entities and draw them.
		for(Iterator<Entity> it = mapEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			
			// TODO FUTURE Move this into an extended Iterator and Spatial Collection.  // Only draw entity if in view.
			if (SpatialHelper.isIntersecting(e, Camera.getCamera().toRectangle())){
				e.paint(g);
			}
		}
		
		for (Iterator<Entity> it = temporaryEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			
			// TODO FUTURE Move this into an extended Iterator and Spatial Collection.  // Only draw entity if in view.
			if (SpatialHelper.isIntersecting(e, Camera.getCamera().toRectangle())){
				e.paint(g);
			}
		}
	}
}