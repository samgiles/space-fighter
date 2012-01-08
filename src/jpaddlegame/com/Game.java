package jpaddlegame.com;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.vecmath.Vector2d;

import org.w3c.dom.events.MouseEvent;

import jpaddlegame.com.game.*;
/**
 * This is the entry
 * @author Sam
 *
 */
public class Game extends JApplet implements Runnable, KeyEventDispatcher, MouseListener {

	private Thread mainThread;
	private int i;
	
	private BackBuffer backBuffer;
	
	private World world;
	
	

	/**
	 * 
	 * @throws HeadlessException
	 */
	public Game() throws HeadlessException {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
		
	}
	
	/**
	 * Start the game loop.
	 */
	public void start() {
		world = new World(this);
		this.addMouseListener(new ClickListener(world));
		// Create the main thread.
		mainThread = new Thread(this);
		// Start the main thread.
		mainThread.start();
		
		backBuffer = new BackBuffer(this);
		
		i = 0;
	}

	/** 
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			i++;
			
			repaint();
			
			try {
				mainThread.sleep(33); 
			} catch(InterruptedException e){
				// Swallow the interrupted exception.
			}
		}
	}
	
	public void paint(Graphics g){
		
		Graphics bbG = backBuffer.getGraphics();
		
		bbG.drawString("Camera World Position = " + Camera.getCamera().getWorldPosition().toString(), 10, 10);
		
		
		world.getMap().paint(bbG);
		g.drawImage(backBuffer.getBackBuffer(), 0, 0, Color.white, null);
		backBuffer.clear();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent arg0) {
		// TODO Auto-generated method stub
				if (KeyEvent.VK_LEFT == arg0.getKeyCode()){
					Camera.getCamera().move(new Vector2d(-5, 0));
					return true;
				}
				
				if (KeyEvent.VK_RIGHT == arg0.getKeyCode()){
					Camera.getCamera().move(new Vector2d(5, 0));
					return true;
				}
				
				if (KeyEvent.VK_UP == arg0.getKeyCode()){
					Camera.getCamera().move(new Vector2d(0, -5));
					return true;
				}
				
				if (KeyEvent.VK_DOWN == arg0.getKeyCode()){
					Camera.getCamera().move(new Vector2d(0, 5));
					return true;
				}
				return false;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		if (arg0.isShiftDown()){
			world.getMap().addEntity(new Entity(0));
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
