import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bomb extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Bomb(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 6;
		//previous speed=7
		if (needImage) {
		    loadImage ("Bomb2.0.png");
		}
	}
	void update() {
		y+=speed;
		  super.update();
		  //System.out.println(x + ": "+ y+": "+ width+":"+ height);
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
	        if (gotImage) {
	        	g.drawImage(image, x, y, width, height, null);
	        } else {
	        	g.setColor(Color.BLUE);
	        	g.fillRect(x, y, width, height);
	        }
	}

}
