import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
Rocketship rocket;
Object[] List= {};
Object Projectile;
Object alien;
ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
Random random = new Random();
ObjectManager(Rocketship RocketX){
	rocket = RocketX;
}
void addProjectile(Projectile projectile) {
Projectiles.add(projectile);
}
void addAlien() {
	aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	
}
void draw(Graphics g) {
	  
}

void update() {
	for (int i = 0; i < aliens.size(); i++) {

		Alien a = aliens.get(i);
		a.update();
		if(a.x>LeagueInvaders.HEIGHT) {
			a.isActive=false;
		}
	}
	
}
}
