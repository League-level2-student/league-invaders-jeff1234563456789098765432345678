import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	battleShip ship;
	battleShip ship2;
	Object[] List = {};
	Object Projectile;
	Object Alien;
	int score = 0;
	
	ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	ArrayList<Divider> Divider = new ArrayList<Divider>();
	Random random = new Random();

	ObjectManager(battleShip RocketX) {
		ship = RocketX;
		ship2 = RocketX;
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
	{}

	void addAlien() {
		//System.out.println("Alien: "+bombs.size());
		bombs.add(new Bomb(random.nextInt(BattleBoats.WIDTH-50), 0, 50, 50));
		//System.out.println("Alien: "+bombs.size());
	}

	void draw(Graphics g) {
		ship2.draw(g);
		ship.draw(g);
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);
			
			a.draw(g);

		}
		
		
		for (int i = 0; i < Projectiles.size(); i++) {
			Projectile p = Projectiles.get(i);
			p.draw(g);
		}

	}

	void checkCollision() {
		for (int i = 0; i < bombs.size(); i++) {
			Bomb a = bombs.get(i);

			if (ship.collisionBox.intersects(a.collisionBox)) {
				a.isActive = false;
				ship.isActive = false;
				
			}
			if (ship2.collisionBox.intersects(a.collisionBox)) {
				a.isActive = false;
				ship2.isActive = false;
				
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
		if(score>10) {
			for (int i = 0; i < bombs.size(); i++) {

				Bomb a = bombs.get(i);
				a.speed=8;
				
		}
		}else if(score>12) {
			for (int i = 0; i < bombs.size(); i++) {
				
				Bomb a = bombs.get(i);
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
