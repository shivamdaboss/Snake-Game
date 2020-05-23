import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Tile{
	
	public static boolean eaten = false;

	Apple(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		if(eaten) {
			x = (int)(Math.random() * 100) * Main.TILE_WIDTH;
			y = (int)(Math.random() * 100) * Main.TILE_HEIGHT;
			eaten = false;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
	
	

}
