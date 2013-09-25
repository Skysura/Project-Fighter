package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState{
	
	private GameStateManager gsm;
	private Background bg;
	private int currentChoice = 0;
	
	private String gameName = "Project Fighter";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	
	
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
		
		
	}

	public void init() {}
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
			
			width =  GamePanel.WIDTH / 8 ;
			height = GamePanel.HEIGHT / 4 + i * 50;
			if(currentChoice == i){
				g.setColor(Color.cyan);
			}
			else
				g.setColor(Color.blue);
			g.drawString(options[i], width , GamePanel.HEIGHT / 4 + i * 50);
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
		System.out.println(pos);
	}
	public void mouseReleased(Point pos) {
		System.out.println(pos);
	}

}
