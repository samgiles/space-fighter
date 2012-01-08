package jpaddlegame.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyProcessor implements KeyListener {

	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean space = false;
	
	
	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}
	
	public boolean isSpace() {
		return space;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			down = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP){
			up = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			space = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP){
			up = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			space = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
