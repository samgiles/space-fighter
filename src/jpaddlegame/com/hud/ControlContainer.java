/**
 * 
 */
package jpaddlegame.com.hud;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import jpaddlegame.com.BatchDrawer;
import jpaddlegame.com.Drawable;

/**
 * @author Sam
 *
 */
public class ControlContainer implements Drawable{

	
	public List<Control> controls;
	
	/**
	 * 
	 */
	public ControlContainer(Component window) {
		
		controls = new LinkedList<Control>();
		
		window.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				for(Control c : controls) {
					System.out.println(arg0.getX() + " : " + c.getX());
					if (isPointInControl(arg0.getPoint(), c)) {
						c.notifyMouseOver(arg0);
					} else {
						c.notifyMouseOut(arg0);
					}
				}
			}});
		
		// TODO Auto-generated constructor stub
		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(Control c : controls) {
					if (isPointInControl(arg0.getPoint(), c)) {
						c.notifyClick(arg0);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				for(Control c : controls) {
					if (isPointInControl(arg0.getPoint(), c)) {
						c.notifyMouseOut(arg0);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				for(Control c : controls) {
					if (isPointInControl(arg0.getPoint(), c)) {
						c.notifyMouseDown(arg0);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				for(Control c : controls) {
					if (isPointInControl(arg0.getPoint(), c)) {
						c.notifyMouseUp(arg0);
					}
				}
			}
			
		});
	}
	
	public static boolean isPointInControl(Point point, Control control) {

		System.out.println(point.y + " : " + control.getY());
		if (point.x < control.getX()) {
			return false;
		}
		
		if (point.x > (control.getX() + control.getSizeX())) {
			return false;
		}
		
		if (point.y < (control.getY())) {
			return false;
		}

		if (point.y > (control.getY() + control.getSizeY())){
			return false;
		}
		
		return true;
	}
	
	public void addControl(Control control) {
		this.controls.add(control);
	}

	@Override
	public void paint(BatchDrawer g) {
		for (Drawable c : controls) {
			c.paint(g);
		}
	}

}
