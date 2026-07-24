package FlyBird;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bird {
	Image image;

	int x;
	int y;

	int width = 60;
	int height = 60;

	int velocity = 0;

	public Bird() {

		x = 100;
		y = 300;

		image = new ImageIcon("images/kedi.jpeg").getImage();

	}

	public void update() {

		velocity += 1;

		y += velocity;

	}

	public void jump() {

		velocity = -10;

	}

	public void draw(Graphics g) {

		g.drawImage(image, x, y, width, height, null);

	}

	public int getX() {

		return x;

	}

	public int getY() {

		return y;

	}

	public int getWidth() {

		return width;

	}

	public int getHeight() {

		return height;

	}

}