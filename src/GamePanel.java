 import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final int MENU = 0;
	final int GAME = 1;
	int i = 0;
	final int END = 2;

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int currentState = MENU;
	battleShip ship = new battleShip(250, 700, 50, 50);
	Timer frameDraw;
	Timer alienSpawn;
	Timer Transition;

	public int getscore() {
		return this.getscore();
	}

	ObjectManager objectmanager = new ObjectManager(ship);
	Font titleFont1;
	Font titleFont2, titleFont = new Font("Arial", Font.PLAIN, 12), titleFont21 = new Font("Arial", Font.PLAIN, 35);

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
		if (ship.isActive == false) {
			currentState = END;
		}

	}

	void updateEndState() {
		alienSpawn.stop();

	}

	void startGame() {
		alienSpawn = new Timer(1000, objectmanager);
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
		String stage = "Easy";
		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		objectmanager.draw(g);
if(objectmanager.getscore()>10) {
	stage="Medium";
}
if(objectmanager.getscore()>20) {
	stage="";
	stage="Hard";
}
		g.drawString("Score:" + objectmanager.getscore(), 50, 30);
		g.drawString("Stage: " + stage, 50, 70);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont21);
		g.setColor(Color.YELLOW);

		g.drawString("Game Over", 110, 110);
		g.setFont(titleFont);
		g.drawString("You killed   " + objectmanager.getscore(), 185, 185);
		g.drawString("enemies", 270, 185);
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
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.VK_UP && currentState == GAME) {

			ship.up = true;

		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN && currentState == GAME) {

			ship.down = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE&& currentState == MENU) {

			JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. The game will become harder over time.");
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME) {

			ship.left = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME) {

			ship.right = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME) {
			objectmanager.addProjectile(ship.getProjectile());

		}
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == MENU) {
				currentState = GAME;
				startGame();

			} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER && currentState == GAME) {
				currentState = END;

			} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER && currentState == END) {
				currentState = MENU;
				ship.isActive = true;
				ship = new battleShip(250, 700, 50, 50);
				objectmanager = new ObjectManager(ship);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN && currentState == GAME) {

			ship.down = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT && currentState == GAME) {

			ship.left = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT && currentState == GAME) {

			ship.right = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP && currentState == GAME) {
			ship.up = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
