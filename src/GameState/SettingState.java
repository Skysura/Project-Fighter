package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import Main.GamePanel;
import TileMap.Background;

public class SettingState extends GameState{
	
	private GameStateManager gsm;
	private Background bg;
	private int currentChoice = 0;
	private boolean showResolutionMenu = false;
	
	private int[] x;
	private int[] y;
	private int[] dx;
	private int[] dy;
	
	private String gameName = "Settings";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	private String[] options = {
			"Resolution",
			"Back"
	};
	
	private String[] resolution = {
		"1280:960",
		"640:480"
	};

	public SettingState(GameStateManager gsm) {
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
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(gameName, (GamePanel.WIDTH / 8), (int) (( GamePanel.HEIGHT / 5)));
		
		if(showResolutionMenu){
			drawResolution(g);
		}
		else
		drawMenu(g);

	}
	
	public void drawMenu(Graphics2D g){
		
		int width;
		int height;
		
		//draw menu
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			
			width =  GamePanel.WIDTH / 8 ;
			height = GamePanel.HEIGHT / 4 + i * 50;
			if(currentChoice == i){
				g.setColor(Color.cyan);
			}
			else
				g.setColor(Color.blue);
			g.drawString(options[i], width , height);
		}
		
	}
	
	public void drawResolution(Graphics2D g){
		int width;
		int height;
		
		//draw menu
		g.setFont(font);
		for(int i = 0; i < resolution.length; i++){
			
			width =  GamePanel.WIDTH / 8 ;
			height = GamePanel.HEIGHT / 4 + i * 50;
			if(currentChoice == i){
				g.setColor(Color.cyan);
			}
			else
				g.setColor(Color.blue);
			g.drawString(resolution[i], width , height);
		}
	}
	
	public void select(){
		if(showResolutionMenu){
			if(currentChoice == 0){
				showResolutionMenu = false;
				gsm.setResolution(1280, 960);
				gsm.setChanged(true);
			}
			if(currentChoice == 1){
				showResolutionMenu = false;
				gsm.setResolution(1280/2, 960/2);
				gsm.setChanged(true);
			}
		}
		
		else{
			if(currentChoice == 0){
				showResolutionMenu = true;
			}
			else{
				gsm.setState(gsm.MENUSTATE);
			}
		}			
	}


	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		
		if(showResolutionMenu){
			if(k == KeyEvent.VK_W){
				currentChoice--;
				if(currentChoice == -1){
					currentChoice = resolution.length - 1;
				}
				
			}
			if(k == KeyEvent.VK_S){
				currentChoice++;
				if(currentChoice == resolution.length){
					currentChoice = 0;
				}
			}
		}
		else{
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
	public void mouseReleased(Point pos) {}

	@Override
	public void mouseMoved(Point pos) {
		// TODO Auto-generated method stub
		
	}

}
