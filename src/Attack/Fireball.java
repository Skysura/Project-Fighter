package Attack;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Entity.EntityManager;
import Main.GamePanel;

public class Fireball extends EntityManager implements Entity{
	
	private Point startingPosition;
	private int FACING;
	private double SPEED;
	private double x;
	private double y;
	
	private BufferedImage image;
	
	public Fireball(double x, double y, int FACING){
		this.x = x;
		this.y = y;
		this.FACING = FACING;
		init();
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/spell/fireball.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void init() {
		SPEED = 20;
	}
	//test
	int i = 0;
	
	public void update() {
		x += SPEED * FACING;
		//test
		i++;
		if(i == 10)
			removeEntity(this);
			
		
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int) y, null);
	}

}
