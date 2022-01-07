package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.main.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//play
			if (mouseOver(mx, my, 220, 170, 200, 64 )) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32,405,ID.Player, handler));
				handler.addObject(new Coin(Game.WIDTH/2 - 32,0,ID.Coin));
			}
			//settings
			if (mouseOver(mx, my, 220, 250, 200, 64)) {
				game.gameState = STATE.Settings;
			}
			
			//quit
			if (mouseOver(mx, my, 220, 330, 200, 64)) {
				System.exit(1);
			}
		}
		

		//back button in settings
		if(game.gameState == STATE.Settings) {
			if (mouseOver(mx,my,1,3,45,25)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		//buttons in end screen
			//Back to menu
		if(game.gameState == STATE.End) {
			if (mouseOver(mx,my,155,238,150,30)) {
				hud.setScore(0);
				game.gameState = STATE.Menu;
				return;
			}
		}
			//Try again
		if(game.gameState == STATE.End) {
			handler.clearAll();
			if (mouseOver(mx,my,325,238,150,30)) {
				game.gameState = STATE.Game;
				handler.clearAll();
				hud.setScore(0);
				
				handler.addObject(new Player(Game.WIDTH/2 - 32,405,ID.Player, handler));
				handler.addObject(new Coin(Game.WIDTH/2 - 32,0,ID.Coin));
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial",1,50);
			
			g.setColor(Color.gray);
			g.fillRect(0, 0, 700, 500);
			
			g.setFont(fnt);
			g.setColor(Color.yellow);
			g.drawString("Coin", 170, 100);
			g.setColor(Color.white);
			g.drawString("Guzzler", 290, 100);
			fnt = new Font("arial",1,15);
			g.setFont(fnt);
			g.drawString("Don't stop coining!", 255, 135);
			
			g.setColor(Color.white);
			fnt = new Font("arial",1,30);
			g.setFont(fnt);
			g.drawString("PLAY",280,215);
			g.drawRect(220, 170, 200, 64);
			
			g.drawString("SETTINGS",248,295);
			g.drawRect(220, 250, 200, 64);
			
			g.drawString("QUIT",280,375);
			g.drawRect(220, 330, 200, 64);
		}
		else if(game.gameState == STATE.Settings) {
			Font fnt = new Font("arial",1,50);
			g.fillRect(0, 0, 700, 500);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("How to Play", 170, 100);
			fnt = new Font("arial",1,20);
			g.setFont(fnt);
			g.drawString("Use the Left and Right arrow keys to move.", 110, 150);
			g.drawString("Collect the coins and dodge the mines!", 130, 230);
			g.drawString("Press 'P' while in game to pause", 160, 310);
			
			fnt = new Font("arial",1,15);
			g.setFont(fnt);
			g.drawString("BACK", 3, 20);
			g.drawRect(1,3,45,25);
			
		}
		else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial",1,50);
			
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 160, 100);
			fnt = new Font("arial",1,20);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("You collected "+hud.getScore()+" coins!", 210, 150);
			
			g.setColor(Color.white);
			g.drawString("MENU", 200, 260);
			g.drawRect(155,238,150,30);
			
			g.drawString("REPLAY", 362, 260);
			g.drawRect(325,238,150,30);
			
		}

		
		
		
		
		
	}
	
	public void tick() {
		
	}

}
