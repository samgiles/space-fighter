package jpaddlegame.com;

import jpaddlegame.com.game.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.vecmath.Vector2d;

public class ClickListener extends MouseAdapter implements MouseMotionListener {
	
		private World world;
		
		public ClickListener(World world) {
			this.world = world;
		}
	
	  public void mousePressed(MouseEvent event) {
	    
	  }
	  
	  public void mouseMoved(MouseEvent event){
			
	  }
}