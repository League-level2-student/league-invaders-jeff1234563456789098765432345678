import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 10;

	}

	public void right() {
		if (speed +x < 500 - width ) {
			x += speed;
		}

	}

	public void down() {
		
		if (speed + height+y < 800) {
			y += speed;
			System.out.println(width);
		}

		
	}

	public void left() {
		
		if (x >0) {
			x -= speed; 
		}


	}

	public void up() {
		y -= speed;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

}
