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
	
	public static int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000, TILE_HEIGHT = 10, TILE_WIDTH = 10;
	public static JFrame frame = new JFrame("Snake");
	public static Canvas canvas = new Canvas();
	public static BufferStrategy bs;
	public static Graphics g;
	public static Tile[][] grid;
	public static Snake s;
	public static Apple a;
	public static boolean gameOver;
	public static boolean[] keyList = new boolean[256];
	public static int fps = 30;
	
	Main(){
		init();
		long cur = System.currentTimeMillis(); 
		long prev = cur;
		long fpsmilli = 1000/fps;
		while(!gameOver) {
			cur = System.currentTimeMillis();
			if(cur - prev > fpsmilli) {
				refresh();
				draw();
				prev = cur;
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Main app = new Main();
	}
	
	public void init() {
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT + 30);
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
		grid = new Tile[100][100];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Tile(i*TILE_WIDTH, j*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
			}
		}
		
		s = new Snake(20, grid[50][50].getX(), grid[50][50].getY(), grid[50][50].getWidth(), grid[50][50].getHeight());
		gameOver = false;
		
		a = new Apple(60 * TILE_WIDTH, 50*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}
	
	
	public void draw() {
		if(canvas.getBufferStrategy() == null)
			canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		//drawing begins here
		g.setColor(Color.red);
		g.fillRect(0, 0, FRAME_WIDTH + 40, FRAME_HEIGHT + 40);
		g.setColor(Color.black);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		s.draw(g);
		a.draw(g);
		/*for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				g.fillRect((int)grid[i][j].getX(), (int)grid[i][j].getY(), (int)(grid[i][j].getWidth()), (int)(grid[i][j].getHeight()));
			}
		}*/
		//drawing ends here
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	
	public void refresh(){
		/*
		if(gameOver) {
			if(keyList[KeyEvent.VK_SPACE]) {
				replay = true;
				keyList[KeyEvent.VK_SPACE] = false;
			}
		}
		
		gameCheck();
		*/
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
}



