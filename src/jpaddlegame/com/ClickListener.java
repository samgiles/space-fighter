package jpaddlegame.com;

import jpaddlegame.com.game.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.vecmath.Vector2d;

public class ClickListener extends MouseAdapter {
	
		private World world;
		
		public ClickListener(World world) {
			this.world = world;
		}
	
	  public void mousePressed(MouseEvent event) {
	    System.out.println("Mouse pressed at (" +
	                       event.getX() + "," +
	                       event.getY() + ").");
	    
	    
	    if (event.isControlDown()){
	    	Entity e = new Entity(0);
	    	Vector2d pos = Camera.getCamera().convertToWorldCoordinates(new Vector2d(event.getX(), event.getY()));
	    	e.setPosition(pos);
	    	world.getMap().addEntity(e);
	    }
	    
	    if (event.isAltDown()){
	    	world.getMap().save();
	    }
	    
	    if (event.isShiftDown()){
	    	world.getMap().setMapEntities(new LinkedList<Entity>());
	    }
	  }
}