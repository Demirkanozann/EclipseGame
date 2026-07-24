package FlyBird;

import javax.swing.JFrame;

public class FlyBird {

	public static FlyBird flyBird;

	public FlyBird() {

		JFrame window = new JFrame();

		GamePanel panel = new GamePanel();

		window.add(panel);

		window.setTitle("Flappy Bird");
		window.setSize(360, 640);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);

	}

	public static void main(String[] args) {

		flyBird = new FlyBird();

	}

}