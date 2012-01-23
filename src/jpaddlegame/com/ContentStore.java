package jpaddlegame.com;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class ContentStore {
	
	/**
	 * Stores image resources by Id.
	 */
	private Dictionary<Integer, Image> dict;
	
	private static ContentStore contentStoreInstance;
	
	public static ContentStore getStore() {
		
		if (contentStoreInstance == null) {
			contentStoreInstance = new ContentStore();
		}
		
		return contentStoreInstance;
	}
	
	private ContentStore(){
		dict = new Hashtable<Integer, Image>();
	}
	
	public Image getResource(int id){
		
		Image resource = dict.get(id);
		
		if (resource == null){
			// Create the resource and place it into the Hashset.
			BufferedImage img = null;
			
			try {
				img = ImageIO.read(new File(id+".png"));
			} catch (IOException e){
				e.printStackTrace();
			}
			
			resource = img;
			dict.put(id, resource);
		}
		
		return resource;
	}
}
