import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.management.timer.Timer;

public class ObjectManager implements ActionListener {
	battleShip ship;
	battleShip ship2;
	Object[] List = {};
	Object Alien;
	int score = 0;
	int b = 0;

	int score2 = 0;
	Thread speakShip1 = new Thread();
	Thread speakShip2 = new Thread();
	Random vrand = new Random();
//int num = 0;
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	Divider div = new Divider(250, 0, 18, 800);
	Random random = new Random();
	Random rand = new Random();
	int num = 0;
	boolean canShip1Speak = true;
	boolean canShip2Speak = true;
	int pi = 0;
	int R = 0;
	int L = 0;
	Random rad = new Random();

	ObjectManager(battleShip RocketX, battleShip RocketX2) {
		ship = RocketX;
		ship2 = RocketX2;
		ship.setBoundries(BattleBoats.WIDTH-ship.width, div.x+div.width);
		ship2.setBoundries(div.x-ship.width, 0);
	}

	public ObjectManager(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		// TODO Auto-generated constructor stub
	}

	void addProjectile(Projectile projectile) {
		Projectiles.add(projectile);

	}

	public int getscore() {
		return this.score;
	}

	public int getscore2() {
		return this.score2;
	}

	{
	}

	void addBomb() {
		int i2 = vrand.nextInt(11);
		bombs.add(new Bomb(random.nextInt(BattleBoats.WIDTH - 70), 0, 50, 50));
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			if (a.x > 270) {
				// R
				R += 1;

			}
			if (a.x < 270) {
				// L
				L += 1;

			}
			if (R > L) {
				L += 1;
				new Bomb(random.nextInt(BattleBoats.WIDTH - 270), 0, 50, 50);
			}
			if (L > R) {
				R += 1;
				new Bomb(270 + random.nextInt(BattleBoats.WIDTH), 0, 50, 50);

			}
			// System.out.println("R: " + R + " " + "L: " + L);
		}

		int i = vrand.nextInt(10);
		// change the IF statment value for different percentage drop for powerup
		if (i >= 9) {
			powerups.add(new Powerup(rand.nextInt(BattleBoats.WIDTH - 70), 0, 50, 50));
		}
	}

	void draw(Graphics g) {
		ship2.draw(g);
		ship.draw(g);
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);

			a.draw(g);

		}
		for (int i = 0; i < powerups.size(); i++) {
			Powerup p = powerups.get(i);

			p.draw(g);

		}

		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile p = Projectiles.get(i);
			p.draw(g);
		}

	}

	void checkCollision() {
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			if (a.collisionBox.intersects(div.collisionBox)) {
				a.isActive = false;

				new Bomb(random.nextInt(BattleBoats.WIDTH - 270), 0, 50, 50);
				new Bomb(270 + random.nextInt(BattleBoats.WIDTH), 0, 50, 50);

			}
		}
		for (int i = 0; i < powerups.size(); i++) {
			Powerup p = powerups.get(i);

			if (!speakShip1.isAlive() && !speakShip2.isAlive()) {
				for (int i4 = 0; i4 < Projectiles.size(); i4++) {
					Projectile projectiles = Projectiles.get(i4);

					if ((p.collisionBox.intersects(projectiles.collisionBox) && p.x > 250)
							|| ship2.collisionBox.intersects(p.collisionBox)) {
						score += 1;
						if (ship2.collisionBox.intersects(p.collisionBox)) {
							p.isActive = false;
						}
						if (p.collisionBox.intersects(projectiles.collisionBox) && p.x > 250) {
							p.isActive = false;
						}

						// System.out.println("Right side");
						int i1 = vrand.nextInt(4);
						if (i1 == 1 && canShip1Speak) {

							i += 1;

							speakShip1 = new Thread(new Speak(

									"Love power up engaged       I love player 1 I think player 2 is not good, your going to win player 1"));
							speakShip1.start();

						}
						if (i1 == 2 && canShip1Speak) {

							i += 1;

							speakShip1 = new Thread(new Speak(
									"Annoying power up engaged   Player 1 is the coolest player 1 is the coolest player 1 is the coolest player 1 is the coolest player 1 is going to lose player 2 is going to lose"));
							speakShip1.start();
							canShip1Speak = false;

						}
						if (i1 == 3 && canShip1Speak) {

							i += 1;

							speakShip1 = new Thread(new Speak(
									"Useless power up engaged     Did you know that the Hawaiian alphabet has 12 letters."));
							speakShip1.start();

						}
						if (!speakShip1.isAlive()) {
							canShip1Speak = true;
						}
						if (speakShip1.isAlive() && speakShip2.isAlive()) {
							canShip1Speak = false;
							canShip2Speak = false;

						}

						if (!speakShip1.isAlive()) {
							canShip1Speak = true;
						}
						if (speakShip1.isAlive() && speakShip2.isAlive()) {
							canShip1Speak = false;
							canShip2Speak = false;

						}
						if (div.collisionBox.intersects(p.collisionBox)) {

							powerups.add(new Powerup(rand.nextInt(BattleBoats.WIDTH - 10), 0, 50, 50));

						}
					}
				}
			}
			for (int i5 = 0; i5 < Projectiles.size(); i5++) {
				Projectile projectiles = Projectiles.get(i5);

				if (!speakShip1.isAlive() && !speakShip2.isAlive()) {

					if ((p.collisionBox.intersects(projectiles.collisionBox) && p.x < 250)
							|| ship.collisionBox.intersects(p.collisionBox)) {
						score2 += 1;
						if (p.collisionBox.intersects(projectiles.collisionBox) && p.x < 250) {
							p.isActive = false;
						}
						if (ship.collisionBox.intersects(p.collisionBox)) {
							p.isActive = false;
						}

						int i1 = vrand.nextInt(4);
						if (i1 == 1 && canShip2Speak) {

							speakShip2 = new Thread(new Speak("Quick fire power up engaged"));
							speakShip2.start();
							for (int i4 = 0; i4 < Projectiles.size(); i4++) {
								Projectile proj = Projectiles.get(i4);
								proj.speed = 20;

							}

						}
						if (i1 == 2 && canShip2Speak) {

							speakShip2 = new Thread(
									new Speak("Tips power up engaged      If you hold down space you can rapid fire"));
							speakShip2.start();

						}
						if (i1 == 3 && canShip2Speak) {

							speakShip2 = new Thread(new Speak(
									"Useless power up engaged     Did you know that the voyager space craft will leave the solar system in thirty eight thousand years"));
							speakShip2.start();

						}
					}

					if (!speakShip2.isAlive()) {
						canShip2Speak = true;
					}
				}
			}
		}

		if (ship.collisionBox.intersects(div.collisionBox)) {
			if (ship.speed + ship.x < 500 - ship.width) {
				ship.x += ship.speed;
			}

			if (score < 0) {

				score = 0;
			}
		}

		for (int i = 0; i < powerups.size(); i++) {
			Powerup pow = powerups.get(i);
			if (pow.collisionBox.intersects(div.collisionBox)) {
				pow.isActive = true;

			}
		}
		if (ship2.collisionBox.intersects(div.collisionBox)) {
			if (ship2.speed + ship2.x >ship2.width) {
				ship2.x -= ship2.speed;
			}
			if (score2 < 0) {

				score2 = 0;
			}

		}

		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			if (ship.collisionBox.intersects(a.collisionBox)) {

				a.isActive = false;
				ship.isActive = false;
				if (num == 1) {
					ship.isActive = true;
				}
			}
		}
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);

			if (ship2.collisionBox.intersects(a.collisionBox)) {

				a.isActive = false;
				ship2.isActive = false;
				if (num == 1) {
					ship2.isActive = true;
				}

			}

			for (int i2 = 0; i2 < Projectiles.size(); i2++) {
				Projectile p = Projectiles.get(i2);
				if (p.collisionBox.intersects(a.collisionBox) && p.x < 250) {
					p.isActive = false;
					a.isActive = false;
					score2 += 1;
					// player 2
				}
				if (p.x > 250 && p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
					score += 1;
					// player 1

				}

			}

		}

	}

	void purgeObjects() {
		for (int i = 0; i < powerups.size(); i++) {
			Powerup pow = powerups.get(i);
			if (pow.isActive == false) {
				powerups.remove(i);

			}
		}
		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile p = Projectiles.get(i);
			if (p.isActive == false) {
				Projectiles.remove(i);
			}
		}
		for (int i1 = 0; i1 < bombs.size(); i1++) {
			Bomb a = bombs.get(i1);
			if (a.isActive == false) {
				bombs.remove(i1);

			}

		}
	}

	void update() {
		ship.update();
		ship2.update();
		for (int i = 0; i < powerups.size(); i++) {
			Powerup pow = powerups.get(i);
			pow.update();

		}

		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile proj = Projectiles.get(i);
			proj.update();

		}
		for (int i = 0; i < Projectiles.size(); i++) {

			Projectile proj = Projectiles.get(i);
			proj.update();
			if (proj.y < 0) {
				proj.isActive = false;
			}

		}

		for (int i = 0; i < bombs.size(); i++) {

			Bomb a = bombs.get(i);
			a.update();
			if (a.y > BattleBoats.HEIGHT) {
				a.isActive = false;

			}

		}
		checkCollision();
		purgeObjects();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addBomb();

	}
}
