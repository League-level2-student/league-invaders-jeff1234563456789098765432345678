import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	Object[] List = {};
	Object Projectile;
	Object Alien;
	int score = 0;
	
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Rocket> aliens = new ArrayList<Rocket>();
	Random random = new Random();

	ObjectManager(Rocketship RocketX) {
		rocket = RocketX;
	}

	void addProjectile(Projectile projectile) {
		Projectiles.add(projectile);

	}
	public int getscore() {
        return this.score;
    }
	{}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH-50), 0, 50, 50));

	}

	void draw(Graphics g) {
		
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			a.draw(g);

		}
		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile p = Projectiles.get(i);
			p.draw(g);
		}

	}

	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);

			if (rocket.collisionBox.intersects(a.collisionBox)) {
				a.isActive = false;
				rocket.isActive = false;
				
			}

			for (int i2 = 0; i2 < Projectiles.size(); i2++) {
				Projectile p = Projectiles.get(i2);
				if (p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
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
		for (int i1 = 0; i1 < aliens.size(); i1++) {
			Alien a = aliens.get(i1);
			if (a.isActive == false) {
				aliens.remove(i1);
			}
		}

	}

	void update() {
		rocket.update();
		if(score>10) {
			for (int i = 0; i < aliens.size(); i++) {

				Alien a = aliens.get(i);
				a.speed=8;
				
		}
		}else if(score>12) {
			for (int i = 0; i < aliens.size(); i++) {
				
				Alien a = aliens.get(i);
				a.speed=0;
				a.speed=15;
				
		}
		}
		for (int i = 0; i < Projectiles.size(); i++) {
			
			Projectile proj = Projectiles.get(i);
			proj.update();
			if (proj.y < 0) {
				proj.isActive = false;
			}
		}
		for (int i = 0; i < aliens.size(); i++) {

			Alien a = aliens.get(i);
			a.update();
			if (a.y > LeagueInvaders.HEIGHT) {
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
