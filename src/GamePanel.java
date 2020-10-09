import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Rocketship ship = new Rocketship(250,700,50,50);
	Timer frameDraw;
	Font titleFont1;
	Font titleFont2,
	 titleFont = new Font("Arial", Font.PLAIN, 12),
	 titleFont21 = new Font("Arial", Font.PLAIN, 35);
	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont21);
		g.setColor(Color.YELLOW);
		
		g.drawString("LEAGUE INVADERS", 110, 50);
		g.setFont(titleFont);
		g.drawString("Press ENTER to start", 185, 185);
		
		g.drawString("Press SPACE for instructions", 170, 235);
		
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		ship.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont21);
		g.setColor(Color.YELLOW);
		
		g.drawString("Game Over", 110, 110);
		g.setFont(titleFont);
		g.drawString("You killed enemies", 185, 185);
		
		g.drawString("Press ENTER to restart", 170, 235);
	}
	public GamePanel() {
		   frameDraw = new Timer(1000/60,this);
		    frameDraw.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==KeyEvent.VK_UP && currentState==GAME) {
		
		    System.out.println("UP");
		    ship.up();
		}
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN && currentState==GAME) {
			
		    System.out.println("DOWN");
		    ship.down();
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT && currentState==GAME) {
			
		    System.out.println("LEFT");
		    ship.left();
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT && currentState==GAME) {
			
		    System.out.println("RIGHT");
		    ship.right();
		}
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		    
		}   
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
