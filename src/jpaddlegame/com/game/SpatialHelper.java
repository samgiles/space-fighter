package jpaddlegame.com.game;

import java.awt.Rectangle;

public class SpatialHelper {

	/**
	 * Gets whether this spatial is intersecting a rectangle at all.
	 * @param spatial
	 * @param section
	 * @return
	 */
	public static boolean isIntersecting(Spatial spatial, Rectangle section){
		
		Rectangle spatialRect = spatial.toRectangle();
		
		return spatialRect.intersects(section);
		
	}
}