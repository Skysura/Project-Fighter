package GameState;

import java.awt.Point;

public abstract class GameState {

		public abstract void init();
		public abstract void update();
		public abstract void draw(java.awt.Graphics2D g);
		public abstract void keyPressed(int k);
		public abstract void keyReleased(int k);
		public abstract void mousePressed(Point pos);
		public abstract void mouseReleased(Point pos);
		public abstract void mouseMoved(Point pos);
		
}
