package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Mine extends GameObject {
	
	private BufferedImage mine_image;

	public Mine(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		speedY = 3;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		mine_image = ss.grabImage(2,2,16,16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {

		x += speedX;
		y += speedY;
		
	}
	
	public void render(Graphics g) {

		g.drawImage(mine_image,(int)x,(int)y,null);

	}
}