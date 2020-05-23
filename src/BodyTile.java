import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class BodyTile extends Tile{
	
	public static int LEFT = 1, UP = 2, RIGHT = 3, DOWN = 4;
	private int dir;

	BodyTile(int x, int y, int width, int height, int dir) {
		super(x, y, width, height);
		this.dir = dir;
	}
	
	public int getDir() {
		return dir;
	}
	
	public void setDir(int x) {
		dir = x;
	}
	
	public void move() {
		if(dir == UP) {
			y -= Main.TILE_HEIGHT;
		}
		else if(dir == RIGHT) {
			x += Main.TILE_WIDTH;
		}
		else if(dir == LEFT) {
			x -= Main.TILE_WIDTH;
		}
		else if(dir == DOWN) {
			y += Main.TILE_HEIGHT;
		}
		
		
		/*if(Main.keyList[KeyEvent.VK_UP]) {
			y -= Main.TILE_HEIGHT;
		}
		else if(Main.keyList[KeyEvent.VK_RIGHT]) {
			x += Main.TILE_WIDTH;
		}
		else if(Main.keyList[KeyEvent.VK_LEFT]) {
			x -= Main.TILE_WIDTH;
		}
		else if(Main.keyList[KeyEvent.VK_DOWN]) {
			y += Main.TILE_HEIGHT;
		}*/
	}

	public void draw(Graphics g) {
		g.fillRect(x, y , width, height);
	}

}
