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
	  rocket.draw(g);
	  for (int i = 0; i < aliens.size(); i++) {
		  Alien a = aliens.get(i);
		  a.draw(g);
		  //use if neededdraw(g);
	  }
}
void purgeObjects() {
	for (int i = 0; i < Projectiles.size(); i++) {
		Projectile p = Projectiles.get(i);
		if(p.isActive==false) {
			Projectiles.remove(i);
		}
		}
	for (int i1 = 0; i1 < aliens.size(); i1++) {
		Alien a = aliens.get(i1);
		if(a.isActive==false) {
			aliens.remove(i1);
		}
	}
		
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
