import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame holder;
	final int WIDTH = 500;
	final int HEIGHT = 800;
	GamePanel gp;
	public static void main(String[] args) {
		LeagueInvaders league = new LeagueInvaders();
league.setup();
	}

	public LeagueInvaders() {
		holder = new JFrame();
		gp = new GamePanel();
	}

	void setup() {
		holder.add(gp);
holder.setSize(WIDTH, HEIGHT);
holder.setVisible(true);
holder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
