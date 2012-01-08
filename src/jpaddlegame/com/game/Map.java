package jpaddlegame.com.game;

import java.awt.Graphics;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Map implements java.io.Serializable, Drawable, Updateable{

	private List<Entity> mapEntities;
	
	/**
	 * Temporary map entities that are not supposed to be serialized to the map file.
	 */
	private transient List<Entity> temporaryEntities;
	
	int sizeX = 3000;
	int sizeY = 3000;
	
	int mapId = 0;
	
	public List<Entity> getMapEntities() {
		return mapEntities;
	}

	public void setMapEntities(List<Entity> mapEntities) {
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
		mapEntities = new LinkedList<Entity>();
		temporaryEntities = new LinkedList<Entity>();
		mapId = 0;
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
			e.paint(g);
		}
		
		for (Iterator<Entity> it = temporaryEntities.iterator(); it.hasNext();){
			Entity e = it.next();
			
			// TODO FUTURE Move this into an extended Iterator and Spatial Collection.  // Only draw entity if in view.
			e.paint(g);
		}
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