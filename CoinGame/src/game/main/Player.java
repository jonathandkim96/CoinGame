package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	public void tick() {
		x += speedX;
		y += speedY;
		
		x = Game.clamp(x,  0,  Game.WIDTH - 50);
		
		collision();
	}
	
	private void collision() {
		for(int i = 0 ; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Mine) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					//mine collision
					handler.object.remove(i);
					HUD.HEALTH -= 34;
				}
			}
			if (tempObject.getID() == ID.Coin) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					//coin collision
					handler.object.remove(i);
					HUD.score += 1;
					
				}
			}
			if (tempObject.getID() == ID.Speed_Powerup) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.object.remove(i);
					handler.spd += 5;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
}
