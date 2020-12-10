import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	battleShip rocket;
	Object[] List = {};
	Object Projectile;
	Object Alien;
	int score = 0;
	
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	Random random = new Random();

	ObjectManager(battleShip RocketX) {
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
		rockets.add(new Rocket(random.nextInt(BattleBoats.WIDTH-50), 0, 50, 50));

	}

	void draw(Graphics g) {
		
		rocket.draw(g);
		for (int i = 0; i < rockets.size(); i++) {
			Rocket a = rockets.get(i);
			a.draw(g);

		}
		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile p = Projectiles.get(i);
			p.draw(g);
		}

	}

	void checkCollision() {
		for (int i = 0; i < rockets.size(); i++) {
			Rocket a = rockets.get(i);

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
		for (int i1 = 0; i1 < rockets.size(); i1++) {
			Rocket a = rockets.get(i1);
			if (a.isActive == false) {
				rockets.remove(i1);
			}
		}

	}

	void update() {
		rocket.update();
		if(score>10) {
			for (int i = 0; i < rockets.size(); i++) {

				Rocket a = rockets.get(i);
				a.speed=8;
				
		}
		}else if(score>12) {
			for (int i = 0; i < rockets.size(); i++) {
				
				Rocket a = rockets.get(i);
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
		for (int i = 0; i < rockets.size(); i++) {

			Rocket a = rockets.get(i);
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
