package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Speed extends GameObject {
	
	private BufferedImage speed_image;

	public Speed(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		speedY = 3;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		speed_image = ss.grabImage(2,3,16,16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	public void tick() {
		// TODO Auto-generated method stub
		x += speedX;
		y += speedY;
		
	}

	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(speed_image,(int)x,(int)y,null);
		
		
	}

}