package game.main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Coin extends GameObject {
	
	private BufferedImage coin_image;

	public Coin(int x, int y, ID id) {
		super(x, y, id);
		
		speedY = 3;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		coin_image = ss.grabImage(1,2,16,16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += speedX;
		y += speedY;
		
	}

	public void render(Graphics g) {
		g.drawImage(coin_image,(int)x,(int)y,null);
		
		//g.setColor(Color.yellow);
		//g.fillRect(x, y, 16, 16);
	}
}
