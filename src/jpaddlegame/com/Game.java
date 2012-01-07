package jpaddlegame.com;

import java.applet.Applet;
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
		g.drawString("i = " + i, 10, 20);
	}

}
