import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Alien extends GameObject {

	Alien(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 1;
	}
	void update() {
		y+=speed;
	}
	void draw(Graphics g) {
		  g.setColor(Color.YELLOW);
	        g.fillRect(x, y, width, height);
	}

}
