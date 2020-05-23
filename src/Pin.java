
public class Pin {
	
	private int x, y, dir, hits;
	
	Pin(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDir() {
		return dir;
	}
	
	public void incrementHits() {
		hits++;
	}
	
	public int getHits() {
		return hits;
	}
}
