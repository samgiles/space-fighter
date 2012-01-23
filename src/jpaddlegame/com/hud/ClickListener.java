package jpaddlegame.com.hud;

import java.awt.event.MouseEvent;

public interface ClickListener {
	void onMouseDown(MouseEvent e);
	void onMouseUp(MouseEvent e);
	void onMouseOver(MouseEvent e);
	void onMouseOut(MouseEvent e);
	void onClick(MouseEvent e);
}
