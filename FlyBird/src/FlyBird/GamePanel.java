package FlyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	public static final long serialVersionUID = 1L;

	Bird bird = new Bird();

	ArrayList<Pipe> pipes = new ArrayList<>();
	ArrayList<Coin> coinsList = new ArrayList<>();

	Timer timer;

	int score = 0;
	int highScore = 0; // En yuksek skor degiskeni
	int totalCoins = 0; // Toplanan altin miktari

	boolean gameOver = false;
	boolean started = false;

	int pipeTimer = 0;

	public GamePanel() {

		addKeyListener(this);
		setFocusable(true);
		requestFocus();

		Pipe initialPipe = new Pipe();
		pipes.add(initialPipe);
		createCoinForPipe(initialPipe);

		timer = new Timer();

		timer.schedule(new TimerTask() {

			public void run() {

				if (started && !gameOver) {

					bird.update();

					for (Pipe p : pipes) {
						p.update();
					}

					for (Coin c : coinsList) {
						c.update();
					}

					pipeTimer++;

					if (pipeTimer > 100) {

						Pipe newPipe = new Pipe();
						pipes.add(newPipe);
						createCoinForPipe(newPipe);

						pipeTimer = 0;

					}

					// Skor Kontrolu
					for (Pipe p : pipes) {

						if (!p.passed && p.getX() < bird.getX()) {

							score++;
							p.passed = true;

						}

					}

					// Altin Toplama Kontrolu
					for (Coin c : coinsList) {

						if (!c.collected && bird.getX() + bird.getWidth() > c.getX()
								&& bird.getX() < c.getX() + c.getSize() && bird.getY() + bird.getHeight() > c.getY()
								&& bird.getY() < c.getY() + c.getSize()) {

							c.collected = true;
							totalCoins++;

						}

					}

					checkCollision();

				}

				repaint();

			}

		}, 0, 20);

	}

	// Her boru olustugunda ortasina bir Altin ekler
	private void createCoinForPipe(Pipe p) {
		int gapCenterY = (p.getTopHeight() + p.getBottomY()) / 2;
		int coinX = p.getX() + p.getWidth() / 2 - 11;
		coinsList.add(new Coin(coinX, gapCenterY - 11));
	}

	public void paint(Graphics g) {

		super.paint(g);

		// arka plan
		g.setColor(new java.awt.Color(135, 206, 235));
		g.fillRect(0, 0, getWidth(), getHeight());

		// kuş
		bird.draw(g);

		// borular
		for (Pipe p : pipes) {
			p.draw(g);
		}

		// altınlar
		for (Coin c : coinsList) {
			c.draw(g);
		}

		// zemin
		g.setColor(new java.awt.Color(200, 170, 80));
		g.fillRect(0, 600, getWidth(), 40);

		g.setColor(java.awt.Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));

		if (!started) {

			g.drawString("Flappy Bird", 120, 230);
			g.drawString("SPACE ile basla", 100, 270);
			g.drawString("En Yüksek Skor: " + highScore, 90, 320);

		} else {

			// Sol Üst Bilgiler
			g.setColor(Color.BLACK);
			g.drawString("Skor: " + score, 20, 40);
			g.drawString("En Yüksek Skor: " + highScore, 20, 70);

			// Altın Sayısı
			g.setColor(new Color(184, 134, 11));
			g.drawString("Altın: " + totalCoins + " 🪙", 20, 100);

		}

		if (gameOver) {

			g.setColor(Color.BLACK);
			g.drawString("Game Over", 120, 230);
			g.drawString("SPACE ile yeniden basla", 70, 270);

			g.drawString("Skorunuz: " + score, 110, 320);
			g.drawString("En Yüksek Skor: " + highScore, 90, 360);

			g.setColor(new Color(184, 134, 11));
			g.drawString("Toplanan Altın: " + totalCoins + " 🪙", 80, 400);

		}

	}

	public void checkCollision() {

		for (Pipe p : pipes) {

			if (bird.getX() + bird.getWidth() > p.getX() && bird.getX() < p.getX() + p.getWidth()) {

				if (bird.getY() < p.getTopHeight() || bird.getY() + bird.getHeight() > p.getBottomY()) {

					gameOver = true;

				}

			}

		}

		if (bird.getY() < 0 || bird.getY() + bird.getHeight() > 600) {

			gameOver = true;

		}

		// Oyun bittiginde En Yuksek Skoru guncelle
		if (gameOver) {

			if (score > highScore) {
				highScore = score;
			}

		}

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			if (!started) {

				started = true;

			} else if (gameOver) {

				bird = new Bird();

				pipes.clear();
				coinsList.clear();

				Pipe firstPipe = new Pipe();
				pipes.add(firstPipe);
				createCoinForPipe(firstPipe);

				score = 0;
				pipeTimer = 0;

				gameOver = false;

			} else {

				bird.jump();

			}

		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}