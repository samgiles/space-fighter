/**
 * 
 */
package jpaddlegame.com.game;

import jpaddlegame.com.BatchDrawer;
import jpaddlegame.com.Drawable;
import jpaddlegame.com.Updateable;
import jpaddlegame.com.game.entities.Character;
import jpaddlegame.com.game.entities.Enemy;
import jpaddlegame.com.game.entities.Map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Samuel Giles
 *
 */
public class World implements Drawable, Updateable{
	
	private Map map;
	
	private Character character;
	
	private List<Enemy> e;
	
	int lastTotalWhenEnemyAdded = 0;
	
	public World(Component worldWiewPort) {
		Camera cam = Camera.getCamera(); // HACK:  This seemes hacky.  Sets up the singleton of the camera, and supplies it with a viewport.
		cam.setViewport(worldWiewPort);
		
		
		
		map = new Map();
		character = new Character(map);
		e = new LinkedList<Enemy>();
		Enemy enemy = new Enemy(map);
		enemy.setTarget(character);
		e.add(enemy);
		
		map.addEntity(character);
		map.addEntity(enemy);
		cam.setCenterOn(character);
	}
	
	public Map getMap(){
		return map;
	}

	@Override
	public void paint(BatchDrawer g) {
		
		g.drawString("Health: ", 20, 20, Color.white, -1);
		if (character.getHealth() > 70){
			g.drawFillRect((int)character.getHealth(), 10, 80, 11, Color.green, -1);
		} else if (character.getHealth() > 30){
			g.drawFillRect((int)character.getHealth(), 10, 80, 11, Color.orange, -1);
		} else {
			g.drawFillRect((int)character.getHealth(), 10, 80, 11, Color.red, -1);
		}
		
		if (character.getFirePowerRemaining() > 70){
			g.drawFillRect(character.getFirePowerRemaining(), 10, 80, 25, Color.green, -1);
		} else if (character.getFirePowerRemaining() > 30){
			g.drawFillRect(character.getFirePowerRemaining(), 10, 80, 25, Color.orange, -1);
		} else {
			g.drawFillRect(character.getFirePowerRemaining(), 10, 80, 25, Color.red, -1);
		}
		
		g.drawString("Weapons: ", 20, 35, Color.white, -1);
		g.drawString("Kills: " + character.getKillCount(), 20, 50, Color.white, -1);
		g.drawString("Enemy: " + totalEnemyKillCount(), 20, 65, Color.white, -1);
		map.paint(g);
	}
	
	public Character getCharacter(){
		return character;
	}

	private int totalEnemyKillCount(){
		int tot = 0;
		for(Iterator<Enemy> it = e.iterator(); it.hasNext();){
			Character c = it.next();
			tot += c.getKillCount();
		}
		return tot;
	}
	
	@Override
	public void update() {
		int tot = totalEnemyKillCount();
		
		if (lastTotalWhenEnemyAdded > tot && (tot % 2) == 0 && tot < 0){
			Enemy newEnemy = new Enemy(getMap());
			newEnemy.setTarget(character);
			e.add(newEnemy);
			getMap().addEntity(newEnemy);
			lastTotalWhenEnemyAdded = tot;
		}
		
		if (tot > 0 && (tot % 2) == 0) {
			if (e.size() > 1){
				map.removeEntity(e.remove(0));
			}
			lastTotalWhenEnemyAdded = 0;
		}
		
		map.update();
	}
	
	
}
