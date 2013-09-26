package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.text.html.parser.Entity;

import Attack.Fireball;
import Entity.EntityManager;
import Player.Player;
import TileMap.Background;

public class TestLevel extends GameState{

	private GameStateManager gsm;
	private Background bg;
	private Player player;
	
	public TestLevel(GameStateManager gsm){
		this.gsm = gsm;
		player = new Player();
		
		
		try{
			
			bg = new Background("/background/stage1.jpg", 1);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {}
	public void update() {
		bg.update();
		player.update();
	}
	public void draw(Graphics2D g) {
		bg.draw(g);
		player.draw(g);
	}
	public void keyPressed(int k) {
		player.keyPressed(k);
	}
	public void keyReleased(int k) {
		player.keyReleased(k);
	}
	public void mousePressed(Point pos) {}
	public void mouseReleased(Point pos) {}

	@Override
	public void mouseMoved(Point pos) {
		// TODO Auto-generated method stub
		
	}

}
