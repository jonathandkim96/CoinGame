package game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SideMine extends GameObject {
	
	private BufferedImage side_image;

	public SideMine(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		speedY = 3;
		speedX = 3;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		side_image = ss.grabImage(1,4,32,32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	public void tick() {

		x += speedX;
		y += speedY;
		
	}

	public void render(Graphics g) {

		g.drawImage(side_image,(int)x,(int)y,null);
		
		//g.setColor(Color.cyan);
		//g.fillRect(x, y, 24, 24);
	}

}