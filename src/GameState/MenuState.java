package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState{
	
	private GameStateManager gsm;
	private Background bg;
	private int currentChoice = 0;
	private int hover;
	
	private String gameName = "Project Fighter";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	private int[] x;
	private int[] y;
	private int[] dx;
	private int[] dy;
	
	
	
	private String[] options = {
			"Start",
			"Settings",
			"Quit"
	};
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try{
			
			bg = new Background("/background/menubg.jpg", 1);
			
			titleColor = Color.blue;
			titleFont = new Font("Century Gothic", Font.PLAIN, 72);
			font = new Font("Arial", Font.PLAIN, 46);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		init();
		
		
	}

	public void init() {
		x = new int[options.length];
		y = new int[options.length];
		dx = new int[options.length];
		dy = new int[options.length];
	}
	public void update() {
		bg.update();
	}
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		int width;
		int height;
		
		
		
		//draw tile
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(gameName, (GamePanel.WIDTH / 8), (int) (( GamePanel.HEIGHT / 5)));
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
		    
			FontMetrics fm = g.getFontMetrics();
		    Rectangle2D rect = fm.getStringBounds(options[i], g);
			
			width =  GamePanel.WIDTH / 8 ;
			height = GamePanel.HEIGHT / 4 + i * 50;
			
			x[i] = width;
			y[i] = height;
			dx[i] = width + (int) rect.getWidth();
			dy[i] = height - (int) rect.getHeight();
			
			
			if(currentChoice == i){
				g.setColor(Color.cyan);
			}
			else
				g.setColor(Color.blue);
			g.drawString(options[i], width , height);
		
		}
		

		
		
	}
	
	public void select(){
		if(currentChoice == 0){
		gsm.setState(gsm.TESTLEVEL);
		}
		else if(currentChoice == 1){
			gsm.setState(gsm.SETTINGSTATE);
		}
		else{
			System.exit(0);
		}
						
	}


	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_W){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
			
		}
		if(k == KeyEvent.VK_S){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	public void mousePressed(Point pos) {
		for(int i = 0; i < options.length; i++){
			System.out.println(x[i] + " " + y[i] + " " + dx[i] + " " + dy[i]);
		if(pos.getX() > x[i] && pos.getX() < dx[i] && pos.getY() < y[i] && pos.getY() > dy[i]){
			System.out.println(currentChoice + " I=" + i);
			currentChoice = i;
			System.out.println(currentChoice);
			select();
		}
		}
	}
	public void mouseReleased(Point pos) {
	}

	@Override
	public void mouseMoved(Point pos) {
		System.out.println("asd");
		for(int i = 0; i < options.length; i++){
			System.out.println(x[i] + " " + y[i] + " " + dx[i] + " " + dy[i]);
		if(pos.getX() > x[i] && pos.getX() < dx[i] && pos.getY() < y[i] && pos.getY() > dy[i]){
			hover = i;
			select();
		}
		}
	}

}
