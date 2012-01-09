package jpaddlegame.com.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinearSpatialCollection implements SpatialCollection {

	
	List<Spatial> spatials = new ArrayList<Spatial>();
	
	public LinearSpatialCollection() {
		
	}
	
	@Override
	public boolean add(Spatial e) {
		return spatials.add(e);
	}

	@Override
	public void add(int index, Spatial element) {
		spatials.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Spatial> c) {
		return spatials.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Spatial> c) {
		return spatials.addAll(index, c);
	}

	@Override
	public void clear() {
		spatials.clear();
	}

	@Override
	public boolean contains(Object o) {
		return spatials.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return spatials.containsAll(c);
	}

	@Override
	public Spatial get(int index) {
		return spatials.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return spatials.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return spatials.isEmpty();
	}

	@Override
	public Iterator<Spatial> iterator() {
		return spatials.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return spatials.lastIndexOf(o);
	}

	@Override
	public ListIterator<Spatial> listIterator() {
		return spatials.listIterator();
	}

	@Override
	public ListIterator<Spatial> listIterator(int index) {
		return spatials.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return spatials.remove(o);
	}

	@Override
	public Spatial remove(int index) {
		return spatials.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return spatials.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return spatials.retainAll(c);
	}

	@Override
	public Spatial set(int index, Spatial element) {
		return spatials.set(index, element);
	}

	@Override
	public int size() {
		return spatials.size();
	}

	@Override
	public List<Spatial> subList(int fromIndex, int toIndex) {
		return spatials.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return spatials.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return spatials.toArray(a);
	}




	@Override
	public SpatialCollection collidesWith(Spatial spatial) {
		
		SpatialCollection spatials = new LinearSpatialCollection();
		
		// Inefficient
		for (Iterator<Spatial> it = this.iterator(); it.hasNext();){
			
			Spatial s = it.next();
			
			if (s == spatial){
				// Collision with self
				continue;
			}
			
			Rectangle t = s.toRectangle();
			Rectangle o = spatial.toRectangle();
			
			
			if (s.toRectangle().intersects(spatial.toRectangle())){
				spatials.add(s);
			}
		}
		
		return spatials;
		
	}
	
	
}
