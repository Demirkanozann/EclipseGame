package FlyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Coin {

	private int x;
	private int y;
	private int size = 22;
	public boolean collected = false;

	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		x -= 3; // Borularla aynı hızda sola kayar
	}

	public void draw(Graphics g) {
		if (!collected) {
			// Dış Altın Daire
			g.setColor(new Color(255, 215, 0));
			g.fillOval(x, y, size, size);

			// İç Çerçeve
			g.setColor(new Color(184, 134, 11));
			g.drawOval(x, y, size, size);

			// Altın Simgesi ($)
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.drawString("$", x + 7, y + 16);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}
}