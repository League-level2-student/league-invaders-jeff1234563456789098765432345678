import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final int MENU = 0;
	final int GAME = 1;
	int i = 0;
	final int END = 2;
	Thread speakShip1 = new Thread();
	Thread speakShip2 = new Thread();
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int currentState = MENU;
	battleShip ship = new battleShip(300, 700, 50, 50);
	battleShip ship2 = new battleShip(125, 700, 50, 50);
	Divider div = new Divider(250, 0, 18, 800);
	Timer frameDraw;
	Timer alienSpawn;
	Timer alienSpawn2;
	Timer Transition;
	Thread speak1 = new Thread();
	Thread speak2 = new Thread();
	Thread speak3 = new Thread();
	Thread speak4 = new Thread();
	Thread speak5 = new Thread();
	Thread speak6 = new Thread();

	public int getscore() {
		return this.getscore();
	}

	ObjectManager objectmanager = new ObjectManager(ship, ship2);

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
		if (ship2.isActive == false) {
			currentState = END;
		}
		if (objectmanager.getscore2() >= 10) {
			currentState = END;
			// g.drawString("Player 2 got to 10 first!", 125, 110);
		}
		if (objectmanager.getscore() >= 10) {
			currentState = END;
			// g.drawString("Player 1 got to 10 first!", 125, 110);
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
		g.setColor(Color.green);
		g.fillRect(0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT);
		g.setFont(titleFont21);
		// g.drawImage(image, 0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT, null);
		g.setColor(Color.green);
		g.setColor(Color.BLACK);
		g.drawString("BATTLE BOATS", 110, 50);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to start", 185, 185);

		g.drawString("Press SPACE for instructions", 170, 235);
		g.drawString("Press 'C' for creative mode", 170, 300);

	}

	void drawGameState(Graphics g) {

		g.drawImage(image, 0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT, null);
		objectmanager.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("PLAYER 2", 50, 30);
		g.drawString("PLAYER 1", 400, 30);
		g.drawString("Score:" + objectmanager.getscore2(), 50, 70);
		g.drawString("Score: " + objectmanager.getscore(), 400, 70);
		div.draw(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, BattleBoats.WIDTH, BattleBoats.HEIGHT);
		g.setFont(titleFont21);
		g.setColor(Color.YELLOW);
		if (objectmanager.getscore2() < objectmanager.getscore() && objectmanager.getscore2() < 10
				&& objectmanager.getscore() < 10) {
			g.drawString("Player 1 won!", 125, 110);
		}
		if (objectmanager.getscore2() > objectmanager.getscore() && objectmanager.getscore2() < 10
				&& objectmanager.getscore() < 10) {
			g.drawString("Player 2 won!", 150, 110);

		}
		if (objectmanager.getscore2() == objectmanager.getscore() && objectmanager.getscore2() < 10
				&& objectmanager.getscore() < 10) {
			g.drawString("You tied!", 150, 110);
		}
		if (objectmanager.getscore2() >= 10) {

			g.drawString("Player 2 got to 10 first!", 100, 110);
		}
		if (objectmanager.getscore() >= 10) {

			g.drawString("Player 1 got to 10 first!", 100, 110);
		}

		g.setFont(titleFont);
		g.drawString("Player 1 killed  " + objectmanager.getscore(), 165, 185);
		g.drawString("enemies", 270, 185);
		g.drawString("Player 2 killed  " + objectmanager.getscore2(), 165, 220);
		g.drawString("enemies", 265, 220);
		g.drawString("Press ENTER to restart", 170, 285);
		g.drawString("Press 'i' to hear a inspirational quote", 145, 700);

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
			loadImage("waterv2.png");
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
					"There are two players. Player 1 has arrow key controls with space to fire.\n Player 2 has WASD controls with Shift to fire. First player to 10 wins!  \n If you die before that, who ever has the most kills wins. ");
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
		if (arg0.getKeyCode() == KeyEvent.VK_C && currentState == MENU) {
			objectmanager.num += 1;
			ship2.speed = 15;
			ship.speed = 15;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_I && currentState == END) {


			Random rand = new Random();
			int i = rand.nextInt(7);
			if (i == 1) {
				speakShip1 = new Thread(new Speak(
						" Start each day with a positive thought and continue to perserve through the pain "));
				speak1.start();
				
			}
			if (i == 2) {
				speak2 = new Thread(new Speak(
						" Frustration is only in the mind "));
				speak2.start();
			}
			if (i == 3) {
				speak3 = new Thread(new Speak(
						" Never give up and keep going "));
				speak3.start();
			}
			if (i == 4) {
				speak4 = new Thread(new Speak(
						" Wonder is the beginning of wisdom "));
				speak4.start();
			}
			if (i == 5) {
				speak5 = new Thread(new Speak(
						" One day you will be able to code this game that you are playing "));
				speak5.start();
			}
			if(i==6){
				speak6 = new Thread(new Speak(
						"Keep pushing your self because no one else will"));
				speak6.start();
			}
			
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
				ship = new battleShip(300, 700, 50, 50);
				ship2 = new battleShip(125, 700, 50, 50);
				objectmanager = new ObjectManager(ship, ship2);

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
			objectmanager.addProjectile(ship2.getProjectile());

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
