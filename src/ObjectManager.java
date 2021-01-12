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
	int score2 = 0;

	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	Divider div = new Divider(250,0, 18, 800);
	Random random = new Random();
    Random rand = new Random();
	ObjectManager(battleShip RocketX, battleShip RocketX2) {
		ship = RocketX;
		ship2 = RocketX2;
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
void addPowerup() {
	
}
	void addAlien() {
		// System.out.println("Alien: "+bombs.size());
		bombs.add(new Bomb(random.nextInt(BattleBoats.WIDTH - 50), 0, 50, 50));
		powerups.add(new Powerup(rand.nextInt(BattleBoats.WIDTH - 10), 0, 50, 50));
		// System.out.println("Alien: "+bombs.size());
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
 void speak(String words) {
		
		if (System.getProperty("os.name").contains("Windows")) {
			String cmd = "PowerShell -Command \"Add-Type -AssemblyName System.Speech; (New-Object System.Speech.Synthesis.SpeechSynthesizer).Speak('"
					+ words + "');\"";
			try {
				Runtime.getRuntime().exec(cmd).waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec("say " + words).waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void checkCollision() {
		for (int i = 0; i < powerups.size(); i++) {
			Powerup p = powerups.get(i);
			if (ship.collisionBox.intersects(p.collisionBox)) {
				speak("Trash talk power up engaged if laughter is the best medicine your face must be curing the world");

			}
			if (ship2.collisionBox.intersects(p.collisionBox)) {
				speak("Annoying player 2 power up engaged imagine being bad at this game hardy har har cant relate loser");
                   break;
			}
		}

		
			if (ship.collisionBox.intersects(div.collisionBox)) {
				score -= 1;
				ship.y=700;
				ship.x=300;
			     if(score<0) {
			    	 
			    	 score=0;
			     }
			}
			if (ship2.collisionBox.intersects(div.collisionBox)) {
				score2 -= 1;
				ship2.y=700;
				ship2.x=100;
			     if(score2<0) {
			    	 
			    	 score2=0;
			     }

			}
		

		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			if (ship.collisionBox.intersects(a.collisionBox)) {
				a.isActive = false;
				ship.isActive = false;

			}
		}
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			if (ship2.collisionBox.intersects(a.collisionBox)) {
				a.isActive = false;
				ship2.isActive = false;

			}

			for (int i2 = 0; i2 < Projectiles.size(); i2++) {
				Projectile p = Projectiles.get(i2);
				if (p.collisionBox.intersects(a.collisionBox) ) {
					p.isActive = false;
					a.isActive = false;
					score2+=1;
				}
				if(p.x >250 && p.collisionBox.intersects(a.collisionBox)) {
					score+=1;
					
				}

			}

		}

	}

	void purgeObjects() {
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
		addAlien();

	}
}
