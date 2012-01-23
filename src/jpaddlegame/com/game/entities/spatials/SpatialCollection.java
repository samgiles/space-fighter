package jpaddlegame.com.game.entities.spatials;

import java.util.List;

public interface SpatialCollection extends List<Spatial>{
	SpatialCollection collidesWith(Spatial spatial);
}
