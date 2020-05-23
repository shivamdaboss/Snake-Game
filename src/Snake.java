import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
	public ArrayList<BodyTile> body;
	public ArrayList<Pin> pins;
	
	Snake(int len, int x, int y, int width, int height){
		body = new ArrayList<BodyTile>();
		for(int i = 0; i < len; i++) {
			body.add(new BodyTile(x - i*width, y, width, height, 5));
		}
		pins = new ArrayList<Pin>();
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		for(int i = 0; i < body.size(); i++) {
			body.get(i).draw(g);
		}
	}
	
	public void move() {

		dirCheck();
		for(int i = 0; i < body.size(); i++) {
			for(int j = 0; j<pins.size(); j++) {
				if(body.get(i).getX() == pins.get(j).getX() && body.get(i).getY() == pins.get(j).getY()) {
					body.get(i).setDir(pins.get(j).getDir());
					pins.get(j).incrementHits();
					if(pins.get(j).getHits() == body.size()) {
						pins.remove(j);
					}
					break;
				}
			}
			body.get(i).move();
			if(i == 0) {
				//collisionCheck();
			}
		}
		collisionCheck();	
	}
	
	public void dirCheck() {
		if(body.get(0).getDir() == BodyTile.RIGHT) {
			if(Main.keyList[KeyEvent.VK_UP]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.UP));
			}
			else if(Main.keyList[KeyEvent.VK_DOWN]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.DOWN));
			}
		}
		else if(body.get(0).getDir() == BodyTile.UP) {
			if(Main.keyList[KeyEvent.VK_LEFT]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.LEFT));
			}
			else if(Main.keyList[KeyEvent.VK_RIGHT]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.RIGHT));
			}
		}
		else if(body.get(0).getDir() == BodyTile.LEFT) {
			if(Main.keyList[KeyEvent.VK_UP]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.UP));
			}
			else if(Main.keyList[KeyEvent.VK_DOWN]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.DOWN));
			}
		}
		else if(body.get(0).getDir() == BodyTile.DOWN) {
			if(Main.keyList[KeyEvent.VK_LEFT]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.LEFT));
			}
			else if(Main.keyList[KeyEvent.VK_RIGHT]) {
				pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.RIGHT));
			}
		}
		else {
			if(Main.keyList[KeyEvent.VK_UP] || Main.keyList[KeyEvent.VK_RIGHT] || Main.keyList[KeyEvent.VK_DOWN]) {
				for(int i = 0; i < body.size(); i++) {
					body.get(i).setDir(BodyTile.RIGHT);
				}
				if(Main.keyList[KeyEvent.VK_UP]) {
					pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.UP));
				}
				else if(Main.keyList[KeyEvent.VK_DOWN]) {
					pins.add(new Pin(body.get(0).getX(), body.get(0).getY(), BodyTile.DOWN));
				}
			}
		}
	}
	
	public void collisionCheck() {
		//check the walls
		if(body.get(0).getX() < 0 || body.get(0).getX() + body.get(0).getWidth() >= Main.FRAME_WIDTH || body.get(0).getY() < 0 || body.get(0).getY() + body.get(0).getHeight() >= Main.FRAME_HEIGHT) {
			Main.gameOver = true;
		}
		//checking if the head is in the same spot as anything
		for(int i = 1; i < body.size(); i++) {
			if(body.get(i).getX() == body.get(0).getX() && body.get(i).getY() == body.get(0).getY()) {
				Main.gameOver = true;
			}
		}
		//apples
		if(body.get(0).getX() == Main.a.getX() && body.get(0).getY() == Main.a.getY()) {
			int last = body.size() - 1;
			if(body.get(last).getDir() == BodyTile.UP) {
				body.add(new BodyTile(body.get(last).getX(), body.get(last).getY() + body.get(last).getHeight(), body.get(last).getWidth(), body.get(last).getHeight(), BodyTile.UP));
			}
			else if(body.get(body.size() - 1).getDir() == BodyTile.LEFT) {
				body.add(new BodyTile(body.get(last).getX() + body.get(last).getWidth(), body.get(last).getY(), body.get(last).getWidth(), body.get(last).getHeight(), BodyTile.LEFT));
			}
			else if(body.get(body.size() - 1).getDir() == BodyTile.RIGHT) {
				body.add(new BodyTile(body.get(last).getX() - body.get(last).getWidth(), body.get(last).getY(), body.get(last).getWidth(), body.get(last).getHeight(), BodyTile.RIGHT));
			}
			else {
				body.add(new BodyTile(body.get(last).getX(), body.get(last).getY() - body.get(last).getHeight(), body.get(last).getWidth(), body.get(last).getHeight(), BodyTile.DOWN));
			}
			Apple.eaten = true;
		}
		
	}
}
