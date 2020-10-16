import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	Projectile(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
	speed=10;
	}
	void update() {
		y-=speed;
	}
	void draw(Graphics g) {
		  g.setColor(Color.RED);
	        g.fillRect(x, y, width, height);
	}
}
