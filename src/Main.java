import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Date;

import javax.swing.JFrame;

public class Main implements KeyListener {
	
	public static int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000, TILE_HEIGHT = 20, TILE_WIDTH = 20;
	public static JFrame frame = new JFrame("Snake");
	public static Canvas canvas = new Canvas();
	public static BufferStrategy bs;
	public static Graphics g;
	public static Tile[][] grid;
	public static Snake s;
	public static Apple a;
	public static boolean gameOver, replay = false;
	public static boolean[] keyList = new boolean[256];
	public static int fps = 30, score;
	public static Color c1 = new Color(42, 83, 220), c2 = new Color(60, 42, 220);
	
	Main(){
		init();
		long cur = System.currentTimeMillis(); 
		long prev = cur;
		long fpsmilli = 1000/fps;
		while(true) {
			gameInit();
			while(!gameOver || !replay) {
				cur = System.currentTimeMillis();
				if(cur - prev > fpsmilli) {
					refresh();
					draw();
					prev = cur;
				}
			}

		}
		
	}
	
	public static void main(String[] args) {
		Main app = new Main();
	}
	
	public void init() {
		frame.setSize(FRAME_WIDTH+1, FRAME_HEIGHT + 38);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setResizable(true);
		canvas.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		canvas.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		canvas.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.add(canvas);
		frame.addKeyListener(this);
		frame.setVisible(true);
		System.out.println(frame.getContentPane().getSize());
	}
	
	public void gameInit() {
		gameOver = false;
		replay = false;
		grid = new Tile[FRAME_WIDTH/TILE_WIDTH][FRAME_HEIGHT/TILE_HEIGHT];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile(i*TILE_WIDTH, j*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
			}
		}
		
		s = new Snake(5, grid[grid.length/2][grid.length/2].getX(), grid[grid.length/2][grid.length/2].getY(), grid[grid.length/2][grid.length/2].getWidth(), grid[grid.length/2][grid.length/2].getHeight());
		gameOver = false;
		
		a = new Apple((grid.length/2 + 10) * TILE_WIDTH, (grid.length/2)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		fps = 30;
		score = 0;
	}
	
	
	public void draw() {
		if(canvas.getBufferStrategy() == null)
			canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		//drawing begins here
		g.setColor(Color.black);
		g.fillRect(0, 0, FRAME_WIDTH+10, FRAME_HEIGHT+10);
		if(gameOver) {
			g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			g.setColor(Color.black);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 55));
			drawStringCenter(g, score+"",470, 475,60,50);
			//g.drawString(score+"", 350, 470);
			g.setColor(new Color(76, 192, 235));
			g.fillRect(400, 600, 215, 30);
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 13));
			drawStringCenter(g, "HIT SPACE TO REPLAY!", 400, 600, 215, 30);
		}
		else {
			
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j< grid[i].length; j++) {
					if(i%2 == 0) {
						if(j%2 == 0) {
							g.setColor(c1);
						}
						else {
							g.setColor(c2);
						}
					}
					else {
						if(j%2 == 0) {
							g.setColor(c2);
						}
						else {
							g.setColor(c1);
						}
					}
					g.fillRect(grid[i][j].getX(), grid[i][j].getY(), grid[i][j].getWidth(), grid[i][j].getHeight());
				}
			}
			s.draw(g);
			a.draw(g);
		}
			//drawing ends here
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	
	public void refresh(){
		if(gameOver) {
			if(keyList[KeyEvent.VK_SPACE]) {
				replay = true;
				keyList[KeyEvent.VK_SPACE] = false;
			}
		}

		s.move();
		a.update();
		if(keyList[KeyEvent.VK_UP]) {
			keyList[KeyEvent.VK_UP] = false;
		}
		else if(keyList[KeyEvent.VK_RIGHT]) {
			keyList[KeyEvent.VK_RIGHT] = false;
		}
		else if(keyList[KeyEvent.VK_LEFT]) {
			keyList[KeyEvent.VK_LEFT] = false;
		}
		else if(keyList[KeyEvent.VK_DOWN]) {
			keyList[KeyEvent.VK_DOWN] = false;
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!keyList[e.getKeyCode()])
			keyList[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawStringCenter(Graphics g, String text, int x, int y, int width, int height) {
	    Font font = g.getFont();
		// Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x_coord = x + (width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y_coord = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x_coord, y_coord);
	}
}



