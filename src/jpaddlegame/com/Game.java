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
import java.util.Date;

import javax.swing.JApplet;
import javax.vecmath.Vector2d;

import org.w3c.dom.events.MouseEvent;

import jpaddlegame.com.game.*;
/**
 * This is the entry
 * @author Sam
 *
 */
public class Game extends JApplet implements Runnable{

	private Thread mainThread;
	private int i;
	
	private BackBuffer backBuffer;
	
	private World world;
	
	/**
	 * 
	 * @throws HeadlessException
	 */
	public Game() throws HeadlessException {
		
	}
	
	/**
	 * Start the game loop.
	 */
	public void start() {
		world = new World(this);
		ClickListener listener = new ClickListener(world);
		this.addKeyListener(KeyBoardState.getProcessor());
		this.setFocusable(true);
		requestFocus();
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
		long lastDraw = 0; 
		while (true) {
			long now = (long)(new Date().getTime());
			if((now - lastDraw) > 30){
				handleKeys();
				world.update();
				Camera.getCamera().update();
				repaint();
				lastDraw = now;
			}
		}
	}
	
	public void paint(Graphics g){
		
		Graphics bbG = backBuffer.getGraphics();
		BatchDrawer drawer = new BatchDrawer(bbG);
		
		
		world.paint(drawer);
		
		drawer.draw();
		g.drawImage(backBuffer.getBackBuffer(), 0, 0, Color.black, null);
		backBuffer.clear();
	}

	
	private void handleKeys() {
		
		if (KeyBoardState.getProcessor().isSpace()){
			world.getCharacter().fire();
		}
		
		if (KeyBoardState.getProcessor().isLeft()){
			world.getCharacter().rotate(-0.2);
		}
		
		if (KeyBoardState.getProcessor().isRight()){
			world.getCharacter().rotate(0.2);
		}
		
		if (KeyBoardState.getProcessor().isUp()){
			world.getCharacter().moveForward(2);
		}
		
		if (KeyBoardState.getProcessor().isDown()){
			world.getCharacter().moveBackward(1);
		}
		
		
	}

}
