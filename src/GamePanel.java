import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Timer;
public class GamePanel extends JPanel implements ActionListener,KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Object ObjectManager=null;
	int currentState = MENU;
	Rocketship ship = new Rocketship(250,700,50,50);
	Timer frameDraw;
	Timer alienSpawn;
	 ObjectManager objectmanager = new ObjectManager(ship);
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
		objectmanager.update();
	}

	void updateEndState() {
	}
void startGame() {
	  alienSpawn = new Timer(1000 , objectmanager);
	    alienSpawn.start();
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
	g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		objectmanager.draw(g);
		
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
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
		
	
	public GamePanel() {
		   frameDraw = new Timer(1000/60,this);
		    frameDraw.start();
		    if (needImage) {
		        loadImage ("space.png");
		    }
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
//IM ON ADDING THE STARTGAME(); TO MENU TO GAME ill know if I get a bird by this time gl future me ;)
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
