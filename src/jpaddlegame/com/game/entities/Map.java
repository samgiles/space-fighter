package jpaddlegame.com.game.entities;

import java.awt.Graphics;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jpaddlegame.com.BatchDrawer;
import jpaddlegame.com.Drawable;
import jpaddlegame.com.Updateable;
import jpaddlegame.com.game.StarField;
import jpaddlegame.com.game.TemporaryEntity;
import jpaddlegame.com.game.entities.spatials.LinearSpatialCollection;
import jpaddlegame.com.game.entities.spatials.Spatial;
import jpaddlegame.com.game.entities.spatials.SpatialCollection;

public class Map implements java.io.Serializable, Drawable, Updateable{

	private SpatialCollection mapEntities;
	
	private StarField starField;
	
	/**
	 * Temporary map entities that are not supposed to be serialized to the map file.
	 */
	private transient SpatialCollection temporaryEntities;
	
	int sizeX = 3000;
	int sizeY = 3000;
	
	int mapId = 0;
	
	public SpatialCollection getMapEntities() {
		return mapEntities;
	}

	public void setMapEntities(SpatialCollection mapEntities) {
		this.mapEntities = mapEntities;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public Map() {
		mapEntities = new LinearSpatialCollection();
		temporaryEntities = new LinearSpatialCollection();
		mapId = 0;
		starField = new StarField(0);
		
	}
	
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
	
	public void removeEntity(Entity entity){
		mapEntities.remove(entity);
	}
	
	public void update(){
		
		
		
		
		// Iterate over all persistable entities and update them.
		for (Iterator<Spatial> it = mapEntities.iterator(); it.hasNext();){
			Spatial e = it.next();
			
			e.update();
			
			/*SpatialCollection collisions = mapEntities.collidesWith(e);
			
			if (collisions.size() > 0){
				for (Iterator<Spatial> it2 = collisions.iterator(); it2.hasNext();) {
					Spatial collision = it2.next();
					
				}
			}*/
		}
		
		// Iterate over all temporary entities and update them.
		for (Iterator<Spatial> it = temporaryEntities.iterator(); it.hasNext();){
			Spatial e = it.next();
			if (e!= null){
				e.update();
			
			
				SpatialCollection collisions = mapEntities.collidesWith(e);
				if (collisions.size() > 0){
					for (Iterator<Spatial> it2 = collisions.iterator(); it2.hasNext();) {
						Spatial collision = it2.next();
						if (e instanceof Projectile){
							Projectile p = (Projectile)e;
							if (p.getOwner() == collision){
								continue;
							} else {
								if (collision instanceof Character){
									if (collision instanceof Enemy && p.getOwner() instanceof Enemy){
										continue;
									}
									((Character)collision).takeDamage(p.getOwner(), p.getPower());
									p.kill();
								}
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < temporaryEntities.size(); i++){
			if (temporaryEntities.get(i) instanceof TemporaryEntity){
				if ( ((TemporaryEntity)temporaryEntities.get(i)).isDead()){
					temporaryEntities.remove(temporaryEntities.get(i));
				}
			}
		}
	}
	
	@Override
	public void paint(BatchDrawer g) {
		// Iterate over all persistable entities and draw them.
		for(Iterator<Spatial> it = mapEntities.iterator(); it.hasNext();){
			Spatial e = it.next();
			
			// TODO FUTURE Move this into an extended Iterator and Spatial Collection.  // Only draw entity if in view.
			e.paint(g);
		}
		
		for (Iterator<Spatial> it = temporaryEntities.iterator(); it.hasNext();){
			Spatial e = it.next();
			
			// TODO FUTURE Move this into an extended Iterator and Spatial Collection.  // Only draw entity if in view.
			e.paint(g);
		}
		
		starField.paint(g);
	}
	
	public void save() {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(mapId + ".xml"));
			encoder.writeObject(this);
			encoder.close();
		} catch (Exception e) {
			
		}
	}
	
	public static Map load(int mapId) {
		XMLDecoder decoder = null;
		Map map = null;
		try {
			 decoder = new XMLDecoder(new FileInputStream(mapId + ".xml"));
			 map = (Map)decoder.readObject();
		} catch (Exception e) {
		} finally {
			if (decoder != null){
				decoder.close();
			}
		}
		return map;
	}
}