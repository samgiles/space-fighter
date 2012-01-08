package jpaddlegame.com.game;

import java.util.List;

public interface SpatialCollection extends List<Spatial>{
	SpatialCollection collidesWith(Spatial spatial);
}
