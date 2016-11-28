import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {

	Graphics g;

	BufferedImage dbImage;

	int pplCount = 0;

	int lightRadius = 40;

	boolean running = true;

	Color background;

	ArrayList<Color> lightColors = new ArrayList<Color>();

	ArrayList<Person> people = new ArrayList<Person>();

	Cake cake;

	Player player;
	
	public Main() {

		setSize(640, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		pplCount = (int) (Math.random() * 14) + 7;

		for (int i = 0; i < pplCount; i++) {
			people.add(new Person((int) (Math.random() * 140) + 10, 120));
		}

		background = new Color((int) (Math.random() * 55), (int) (Math.random() * 55), (int) (Math.random() * 55));

		lightColors.add(new Color(254, 232, 84, 150));
		lightColors.add(new Color(253, 191, 71, 100));
		lightColors.add(new Color(248, 148, 52, 50));

		cake = new Cake(80, 120);

		player = new Player(80, 120);
		
		while (running) {
			update();
			try {
				Thread.sleep(33);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String args[]) {
		new Main();
	}

	public void update() {
		this.addKeyListener(this);
		repaint();
		player.update(this );
	}

	public void paint(Graphics dbg) {
		dbImage = (BufferedImage) createImage(160, 144);
		g = dbImage.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, 160, 144);

		g.setColor(new Color((int) (Math.random() * 100) + 155, (int) (Math.random() * 100) + 155,
				(int) (Math.random() * 55) + 200));
		g.drawString("Happy Birthday", 32, 30);

		g.setColor(background);
		g.fillRect(0, 120, 160, 24);

		for (int i = 0; i < people.size(); i++) {
			people.get(i).draw(g);
		}

		for (int i = 0; i < lightColors.size(); i++) {
			lightRadius = (int) Math.pow(4, i + 1) + 4;
			g.setColor(lightColors.get(i));
			g.fillOval(cake.x - lightRadius, cake.y - cake.height - lightRadius, lightRadius * 2, lightRadius * 2);
		}
		cake.draw(g);

		dbg.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		player.keyTyped(e);
	}
}

class Person {
	public int x, y;
	public int width = 10;
	public int height = 10;

	public Color color;

	public Person(int x, int y) {
		this.x = x;
		this.y = y;
		color = new Color((int) (Math.random() * 200), (int) (Math.random() * 200), (int) (Math.random() * 200));
		width *= (Math.random() / 2 + .5);
		height *= (Math.random() / 2 + .5);
	}

	public Person(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x - width / 2, y - height, width, height);
	}
}

class Cake {
	public int x, y;
	public int width = 20;
	public int height = 10;

	public Color color;
	public Color inside;

	ArrayList<Color> cakeColors = new ArrayList<Color>();

	public Cake(int x, int y) {
		this.x = x;
		this.y = y;
		width *= (Math.random() / 2 + .2);
		height *= (Math.random() / 2 + .2);
		cakeColors.add(new Color(64, 55, 45));
		cakeColors.add(new Color(255, 240, 183));
		cakeColors.add(new Color(255, 199, 183));
		cakeColors.add(new Color(232, 155, 183));
		cakeColors.add(new Color(143, 106, 158));
		color = cakeColors.get((int) (Math.random() * cakeColors.size()));
		inside = cakeColors.get((int) (Math.random() * cakeColors.size()));
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x - width / 2, y - height, width, height);
		g.setColor(inside);
		g.fillRect(x - width / 2, y - height / 2, width, height / 4);
	}
}

class Player {
	public int x, y, dx = 0, dy = 0;
	public int width = 10;
	public int height = 10;

	public Color color;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		color = new Color((int) (Math.random() * 200), (int) (Math.random() * 200), (int) (Math.random() * 200));
		width *= (Math.random() / 2 + .5);
		height *= (Math.random() / 2 + .5);
	}

	public Player(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void update(Main m){
		
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x - width / 2, y - height, width, height);
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}
