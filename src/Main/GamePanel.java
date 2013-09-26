package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Entity.EntityManager;
import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	//dimensions
	public static int WIDTH = 1280;
	public static int HEIGHT = 960;
	public static final int SCALE = 1;
	
	//game thread
	private Thread thread;
	private boolean running;
	private int FPS = 500;
	private long targetTime = 1000 / FPS;
	
	//image
	private BufferedImage image;
	public Graphics2D g;
	
	//gamestatemanager
	private GameStateManager gsm;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			thread.start();
		}
	}
	
	public void initialize(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		gsm = new GameStateManager();
	}
	
	long nextSecond = System.currentTimeMillis() + 1000;
	int frameInLastSecond = 0;
	int framesInCurrentSecond = 0;

	public void run() {
		initialize();
		
		long start;
		long elapsed;
		long wait;
		


		
		//Game loop
		while(running){
			
			start = System.nanoTime();
			elapsed=System.nanoTime() - start;

		    long currentTime = System.currentTimeMillis();
		    if (currentTime > nextSecond) {
		        nextSecond += 1000;
		        frameInLastSecond = framesInCurrentSecond;
		        framesInCurrentSecond = 0;
		    }
		    framesInCurrentSecond++;
			
			update();
			draw();
			drawToScreen();
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update(){
		gsm.update();
	}
	public void draw(){
		gsm.draw(g);
		g.drawString(frameInLastSecond + " fps", 10, 50);
		
	}
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {

		System.out.println(arg0.getX() + " " + arg0.getY());
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent pos) {
		gsm.mousePressed(pos.getX(), pos.getY());

		System.out.println("md");
	}
	public void mouseReleased(MouseEvent pos) {
		gsm.mouseReleased(pos.getX(), pos.getY());
	}

	public void mouseDragged(MouseEvent pos) {
		gsm.mouseMoved(pos.getX(), pos.getY());
		System.out.println("md");
	}

	public void mouseMoved(MouseEvent pos) {
		gsm.mouseMoved(pos.getX(), pos.getY());

		System.out.println("mm");
	}

}
