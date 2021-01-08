import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Divider extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Divider(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		super(xOBJ, yOBJ, widthOBJ, heightOBJ);
	}
		void update() {
			super.update();
			  
		}
		
		
		void draw(Graphics g) {
			 
		        
		        	g.setColor(Color.BLACK);
		        	g.fillRect(x,y,width,height);
		        
		        	
		        }
		}

