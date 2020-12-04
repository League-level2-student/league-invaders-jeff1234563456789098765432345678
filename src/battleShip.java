import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class battleShip extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	battleShip(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
		speed = 8;
		if (needImage) {
		    loadImage ("Rocket2.png");
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
	public Projectile getProjectile() {
		//System.out.println(x+": "+y);
        return new Projectile(x+width/2, y, 10, 10);
        
}

	void draw(Graphics g) {
		
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
	void update() {
		super.update();
		if(up) {
			up();
		}
		if(down) {
down();
		} 
	if(left) {
		left();
	}
	if(right) {
		right();
	}
	
	}

}
