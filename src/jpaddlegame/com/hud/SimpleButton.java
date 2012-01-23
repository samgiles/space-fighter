/**
 * 
 */
package jpaddlegame.com.hud;

import java.awt.Color;

import jpaddlegame.com.BatchDrawer;

/**
 * @author Sam
 *
 */
public class SimpleButton extends Control {

	
	private String text;
	
	/**
	 * @param defaultImageId
	 * @param mouseDownImageId
	 * @param mouseOverImageId
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 */
	public SimpleButton(String text, int x, int y) {
		super(6, 7, 5, x, y);
		
		this.text = text;
		// TODO Auto-generated constructor stub
	}
	
	public void paint(BatchDrawer g) {
		super.paint(g);
		g.drawString(text, (int)this.getX() + 30, (int)this.getY() + 20, Color.white, 1001);
	}

}
