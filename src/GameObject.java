import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	Rectangle collisionBox;

	GameObject(int xOBJ, int yOBJ, int widthOBJ, int heightOBJ) {
		x = xOBJ;
		y = yOBJ;
		width = widthOBJ;
		height = heightOBJ;
		collisionBox = new Rectangle(x, y, width, height);

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);

	}

}
