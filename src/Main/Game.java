package Main;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	public static void main(String[] args){
		JFrame window = new JFrame ("Project Fighter");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true); 
		
		
		
	}

}
