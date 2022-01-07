package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastMine extends GameObject {
	
	private BufferedImage fast_image;

	public FastMine(int x, int y, ID id, Handler handler) {
		super(x, y, id);
	
		speedY = 6;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		fast_image = ss.grabImage(1,3,32,32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {

		x += speedX;
		y += speedY;
		
	}

	public void render(Graphics g) {
		
		g.drawImage(fast_image,(int)x,(int)y,null);

	}
}