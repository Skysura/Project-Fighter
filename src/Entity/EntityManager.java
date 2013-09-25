package Entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Main.GamePanel;

public class EntityManager implements Entity{
	
	private Set world = Collections.synchronizedSet(new HashSet());
	private ArrayList list;
	
	public void update() {
		Iterator it = getEntities().iterator();
		
		while(it.hasNext()){
			((Entity)it.next()).update();
		}
	}

	public void draw(Graphics2D g) {
		Iterator it = getEntities().iterator();
		
		while(it.hasNext()){

			((Entity)it.next()).draw(g);
		}
	}

	public void init() {
		
	}
	
	public void addEntity(Entity entity){
		synchronized (world) {
			world.add(entity);
			
		}
	}
	
	public Collection getEntities(){
		HashSet currentWorld = new HashSet();
			synchronized (world) {
				Iterator it = world.iterator();
				while(it.hasNext())
					currentWorld.add(it.next());
			}
		return currentWorld;	
	}
	
	public void removeEntity(Entity entity){
		synchronized (world) {
			world.remove(entity);
		}
	}
	
	

}
