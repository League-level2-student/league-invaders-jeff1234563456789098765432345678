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
	battleShip ship2 = new battleShip(250, 700, 50, 50);
	Timer frameDraw;
	Timer alienSpawn;
	Timer alienSpawn2;
	Timer Transition;

	public int getscore() {
		return this.getscore();
	}

	ObjectManager objectmanager = new ObjectManager(ship);
	ObjectManager objectmanager2 = new ObjectManager(ship2);
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
		objectmanager2.update();
		if (ship.isActive == false) {
			currentState = END;
		}
		if (ship2.isActive == false) {
			currentState = END;
		}
		

	}

	void updateEndState() {
		alienSpawn.stop();

	}

	void startGame() {
		alienSpawn = new Timer(1000, objectmanager);
		alienSpawn.start();
		alienSpawn2 = new Timer(1000, objectmanager2);
		alienSpawn2.start();
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT);
		g.setFont(titleFont21);
		//g.drawImage(image, 0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT, null);
		 g.setColor(Color.green);
		g.setColor(Color.BLACK);
		g.drawString("BATTLE BOATS", 110, 50);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to start", 185, 185);

		g.drawString("Press SPACE for instructions", 170, 235);

	}

	void drawGameState(Graphics g) {
		String stage = "Easy";
		g.drawImage(image, 0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT, null);
		objectmanager.draw(g);
		objectmanager2.draw(g);
		if (objectmanager.getscore() > 10) {
			stage = "Medium";
		}
		if (objectmanager.getscore() > 20) {
			stage = "";
			stage = "Hard";
		}
		g.drawString("Score:" + objectmanager.getscore(), 50, 30);
		g.drawString("Stage: " + stage, 50, 70);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT);
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
			loadImage("WATER.PNG");
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
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE && currentState == MENU) {

			JOptionPane.showMessageDialog(null,
					"Use arrow keys to move. Press SPACE to fire. The game will become harder over time.");
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
		// Secondary controls begins here
		if (arg0.getKeyCode() == KeyEvent.VK_W && currentState == GAME) {

			ship2.up = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A && currentState == GAME) {

			ship2.left = true;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S && currentState == GAME) {
			ship2.down = true;

		}
		if (arg0.getKeyCode() == KeyEvent.VK_D && currentState == GAME) {

			ship2.right = true;
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
				ship2.isActive = true;
				ship = new battleShip(250, 700, 50, 50);
				ship2 = new battleShip(250, 700, 50, 50);
				objectmanager = new ObjectManager(ship);
				objectmanager2 = new ObjectManager(ship2);
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
		// Secondary controls Begins here
		if (arg0.getKeyCode() == KeyEvent.VK_S && currentState == GAME) {

			ship2.down = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A && currentState == GAME) {

			ship2.left = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D && currentState == GAME) {

			ship2.right = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_W && currentState == GAME) {
			ship2.up = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SHIFT && currentState == GAME) {
			objectmanager2.addProjectile(ship2.getProjectile());

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
