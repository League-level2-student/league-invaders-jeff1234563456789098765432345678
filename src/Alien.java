import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Alien extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Alien(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 1;
		if (needImage) {
		    loadImage ("bullet.png");
		}
	}
	void update() {
		y+=speed;
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
	void draw(Graphics g) {
		  g.setColor(Color.YELLOW);
	        g.fillRect(x, y, width, height);
	        if (gotImage) {
	        	g.drawImage(image, x, y, width, height, null);
	        } else {
	        	g.setColor(Color.BLUE);
	        	g.fillRect(x, y, width, height);
	        }
	}

}
