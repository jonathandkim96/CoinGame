package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.main.Game.STATE;

public class KeyInput extends KeyAdapter{

	public Handler handler;
	private boolean[] keyDown = new boolean[2];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for (int i = 0 ; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				//player movement
				
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setX(tempObject.getX() - handler.spd);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setX(tempObject.getX() + handler.spd);
					keyDown[1] = true;
				}
			}
		}
		if(key == KeyEvent.VK_P) {
			
			if(game.gameState == STATE.Game) {
				if(Game.paused) {
					Game.paused = false;
				}
				else {
					Game.paused = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0 ; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				if(key == KeyEvent.VK_LEFT) {
					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_RIGHT) {
					keyDown[1] = false;
				}
				if(!keyDown[0] && !keyDown[1]) {
					tempObject.setSpeedX(0);
				}
			}
		}
	}
	
}
