package Player;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Attack.Fireball;
import Entity.Entity;
import Entity.EntityManager;
import Main.GamePanel;


public class Player {
	
	BufferedImage image;
	
	private double x = 500;
	private double y = 500;
	private Point currentPosition;
	private int FACING;
	private EntityManager em;
	
	
	private boolean left = false;
	private boolean right = false;
	
	public Player(){
		em = new EntityManager();
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/character/player/player.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	
	public void update(){
		em.update();
		
		if(right && x + image.getWidth() < GamePanel.WIDTH){
			x+=10;
			FACING = 1;
		}
		if(left && x > 0){
			x-=10;
			FACING = -1;
		}
			
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_D) {
			right = true;
		}
		if(k == KeyEvent.VK_A){
			left = true;
		}

	}
	public void keyReleased(int k){
		if(k == KeyEvent.VK_D) {
			right = false;
		}
		if(k == KeyEvent.VK_A){
			left = false;
		}
		if(k == KeyEvent.VK_SPACE){
			Entity fireball = new Fireball(x, y, FACING);
			em.addEntity(fireball);
		}
	}
	
	public void draw(Graphics2D g){
		em.draw(g);
		g.drawImage(image, (int)x, (int) y, null);
	}

}
