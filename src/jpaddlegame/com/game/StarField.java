package jpaddlegame.com.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.vecmath.Vector2d;

import jpaddlegame.com.BatchDrawer;

public class StarField implements Drawable {

	private double speed;
	
	private List<Vector2d> worldPoints;	
	
	public StarField (double speed) {
		this.speed = speed;
		// Initialise with some random stars!
		worldPoints = new ArrayList<Vector2d>();
		
		for (int i = 0; i < 100000; i++){
			Random rand = new Random();
			int x = (rand.nextInt() % 10000);
			int y = (rand.nextInt() % 10000);
			worldPoints.add(new Vector2d(x, y));
		}
	}
	
	
	
	@Override
	public void paint(BatchDrawer g) {
		
		for (Iterator<Vector2d> it = worldPoints.iterator(); it.hasNext();){
			Vector2d point  = it.next();
			
			if (Camera.getCamera().toWorldRectangle().contains(point.getX(), point.getY())){
				
				point = Camera.getCamera().convertToScreenCoordinates(point);
				g.drawPoint(Color.white, (int)point.getX(), (int)point.getY(), -1);
			}
		}
	}
}
