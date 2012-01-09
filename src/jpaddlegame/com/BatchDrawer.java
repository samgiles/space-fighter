package jpaddlegame.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.awt.geom.*;

public class BatchDrawer {

	private Graphics2D graphicsObject;
	
	private PriorityQueue<BatchDrawerFunction> queue; 
	
	private interface BatchDrawerFunction {
		int getDrawOrder();
		
		void draw();
	}
	
	public BatchDrawer(Graphics graphicsObject) {
		this.graphicsObject = (Graphics2D)graphicsObject;
		this.graphicsObject.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		this.graphicsObject.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		this.graphicsObject.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		this.graphicsObject.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		queue = new PriorityQueue<BatchDrawerFunction>(10, new Comparator<BatchDrawerFunction>(){
			@Override
			public int compare(BatchDrawerFunction arg0,
					BatchDrawerFunction arg1) {
				return arg0.getDrawOrder() - arg1.getDrawOrder();
			}});
	}
	
	public void drawFillRect(final int width, final int height, final int x, final int y, final Color color, final int zIndex){
		queue.add(new BatchDrawerFunction() {

			int _x= x;
			int _y = y;
			
			int _zIndex = zIndex;
			
			Color _color = color;
			
			@Override
			public int getDrawOrder() {
				return _zIndex;
			}

			@Override
			public void draw() {
				Color color = graphicsObject.getColor();
				graphicsObject.setColor(_color);
				graphicsObject.fillRect(_x, _y, width, height);
				graphicsObject.setColor(color);
			}});
	}
	
	public void drawImage(final Image image, final int x, final int y, final double rotation, final int zIndex){
		
		queue.add(new BatchDrawerFunction() {
			
			int _x = x;
			int _y = y;
			
			double _rotation = rotation;
			
			int _zIndex = zIndex;
			
			@Override
			public int getDrawOrder() {
				return _zIndex;
			}

			@Override
			public void draw() {
				// TODO Auto-generated method stub
				AffineTransform transform = new AffineTransform();
				
				transform.translate(x, y);
				transform.rotate(_rotation, image.getWidth(null) / 2, image.getHeight(null) / 2);
				
				graphicsObject.drawImage(image, transform, null);
			}});
	}
	
	public void drawString(final String string, final int x, final int y, final Color color, final int zIndex) {
		
		queue.add(new BatchDrawerFunction() {

			int _x= x;
			int _y = y;
			
			int _zIndex = zIndex;
			
			Color _color = color;
			
			@Override
			public int getDrawOrder() {
				return _zIndex;
			}

			@Override
			public void draw() {
				Color color = graphicsObject.getColor();
				graphicsObject.setColor(_color);
				graphicsObject.drawString(string, _x, _y);
				graphicsObject.setColor(color);
			}});
	}

	public void drawPoint(final Color color, final int x, final int y, final int zIndex){
		queue.add(new BatchDrawerFunction() {

			int _x= x;
			int _y = y;
			
			int _zIndex = zIndex;
			
			Color _color = color;
			
			@Override
			public int getDrawOrder() {
				return _zIndex;
			}

			@Override
			public void draw() {
				Color color = graphicsObject.getColor();
				graphicsObject.setColor(_color);
				graphicsObject.drawLine(_x, _y, _x, _y);
				graphicsObject.setColor(color);
			}});
	}
	
	public void draw(){
		for (Iterator<BatchDrawerFunction> it = queue.iterator(); it.hasNext();) {
			BatchDrawerFunction drawer = it.next();
			drawer.draw();
		}
	}
}
