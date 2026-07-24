package FlyBird;

import java.awt.Graphics;
import java.util.Random;

public class Pipe {

	int x;

	int topHeight;
	int bottomY;

	int width = 60;

	int gap = 180;

	boolean passed = false;

	public Pipe() {

		x = 400;

		Random random = new Random();

		topHeight = random.nextInt(200) + 50;

		bottomY = topHeight + gap;

	}

	public void update() {

		x -= 3;

	}

	public void draw(Graphics g) {

		g.setColor(new java.awt.Color(0, 200, 0));

		g.fillRect(x, 0, width, topHeight);

		g.fillRect(x, bottomY, width, 640);

	}

	public int getX() {

		return x;

	}

	public int getWidth() {

		return width;

	}

	public int getTopHeight() {

		return topHeight;

	}

	public int getBottomY() {

		return bottomY;

	}

}