import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	battleShip rocket;
	battleShip rocket2;
	Object[] List = {};
	Object Projectile;
	Object Alien;
	int score = 0;
	
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	ArrayList<Rocket> rockets2 = new ArrayList<Rocket>();
	Random random = new Random();

	ObjectManager(battleShip RocketX) {
		rocket = RocketX;
		rocket2 = RocketX;
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
		rocket2.draw(g);
		rocket.draw(g);
		for (int i = 0; i < rockets.size(); i++) {
			Rocket a = rockets.get(i);
			a.draw(g);

		}
		for (int i = 0; i < rockets2.size(); i++) {
			Rocket a = rockets2.get(i);
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
		for (int i = 0; i < rockets2.size(); i++) {
			Rocket a2 = rockets2.get(i);

			if (a2.collisionBox.intersects(a2.collisionBox)) {
				a2.isActive = false;
				rocket2.isActive = false;
				
			}
			for (int i2 = 0; i2 < Projectiles.size(); i2++) {
				Projectile p = Projectiles.get(i2);
				if (p.collisionBox.intersects(a2.collisionBox)) {
					p.isActive = false;
					a2.isActive = false;
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
			for (int i2 = 0; i2 < rockets2.size(); i2++) {
				Rocket b = rockets2.get(i2);
				if (b.isActive == false) {
					rockets2.remove(i2);
				}
		}
		}

	}

	void update() {
		rocket.update();
		rocket2.update();
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
			for (int i2 = 0; i < rockets2.size(); i2++) {

				Rocket r = rockets2.get(i2);
				r.update();
				if (r.y > BattleBoats.HEIGHT) {
					r.isActive = false;

				}
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
