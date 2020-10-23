import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 10;
		if (needImage) {
		    loadImage ("rocket.png");
		}
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
		if (y + height >40) {
			y -= speed;
			
		}

		
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}

}
