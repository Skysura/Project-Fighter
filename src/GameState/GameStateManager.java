package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class GameStateManager{
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int SETTINGSTATE = 1;
	public static final int TESTLEVEL = 2;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new SettingState(this));
		gameStates.add(new TestLevel(this));
	}
	
	public void setState(int state){
		currentState = state;
	}
	
	public void init() {
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
		
	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
		
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
		
	}

	public void mousePressed(int x, int y) {
		Point pos = new Point(x, y);
		gameStates.get(currentState).mousePressed(pos);
		
	}

	public void mouseReleased(int x, int y) {
		Point pos = new Point(x, y);
		gameStates.get(currentState).mouseReleased(pos);
		
	}

}
