import javax.swing.JFrame;

public class BattleBoats{
	JFrame frame;
	final static int WIDTH = 500;
	final static int HEIGHT = 800;
	GamePanel gp;
	public static void main(String[] args) {
		BattleBoats league = new BattleBoats();
BattleBoats.setup();


	}

	public BattleBoats() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}

	void setup() {
		frame.add(gamePanel);
frame.setSize(WIDTH, HEIGHT);
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.addKeyListener(gamePanel);


	}
}
