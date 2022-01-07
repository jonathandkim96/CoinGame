package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	
	private static final long serialVersionUID = 852753996046178928L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Settings,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	public Game() {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Coin Guzzler", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		spawner = new Spawn(handler,hud);
		
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2 - 32,405,ID.Player, handler));
			handler.addObject(new Coin(WIDTH/2 - 32,0,ID.Coin));
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
	
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					hud.setLevel(1);
					spawner.resetInc();
					gameState = STATE.End;
					handler.clearAll();
				}
				if(HUD.score == 100) {
					HUD.HEALTH = 100;
				}
			}
		}
			
		else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
			handler.tick();
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			g.setColor(Color.white);
			
			g.drawString("PAUSED",280,100);
		}
		if(gameState == STATE.Game) {
			hud.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Settings || gameState == STATE.End) {
			menu.render(g);
		}
	
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}

	public static void main(String args[]) {
		new Game();
	}
}
