import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Tile{
	
	public static boolean eaten = false;
	public Color c3 = new Color(229, 147, 39);

	Apple(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		if(eaten) {
			x = (int)(Math.random() * Main.grid.length) * Main.TILE_WIDTH;
			y = (int)(Math.random() * Main.grid[0].length) * Main.TILE_HEIGHT;
			Main.score++;
			if(Main.score%5 == 0) {
				Main.fps++;
			}
			eaten = false;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(c3);
		g.fillRect(x, y, width, height);
	}
	
	

}
