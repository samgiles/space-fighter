package jpaddlegame.com;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JApplet;

/**
 * This is the entry
 * @author Sam
 *
 */
public class Game extends JApplet implements Runnable {

	private Thread mainThread;
	private int i;
	
	private BackBuffer backBuffer;
	
	
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
		// Create the main thread.
		mainThread = new Thread(this);
		// Start the main thread.
		mainThread.start();

		Dimension dim = this.getSize();
		
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
		
		bbG.drawString("i = " + i, 10, 20);
		
		g.drawImage(backBuffer.getBackBuffer(), 0, 0, Color.white, null);
		backBuffer.clear();
	}

}
