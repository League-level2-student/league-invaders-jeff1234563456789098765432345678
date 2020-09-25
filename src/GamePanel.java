import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
//I'm on step 6 for drawing and updating f
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(10, 10, 100, 100);
	}

	void updateMenuState() {
	}

	void updateGameState() {
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
	}

	void drawGameState(Graphics g) {
	}

	void drawEndState(Graphics g) {
	}
}
