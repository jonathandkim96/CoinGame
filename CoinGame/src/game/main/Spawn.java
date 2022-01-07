package game.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	private int coinKeep = 0;
	private int inc = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public void resetInc() {
		this.inc = 0;
	}
	
	public void tick() {
		scoreKeep++;
		coinKeep++;
		inc++;
		
		if(coinKeep >= 80) {
			coinKeep = 0;
			handler.addObject(new Coin(r.nextInt(Game.WIDTH), 0, ID.Coin));
		}
		
		if(hud.score >= 3) {
			if (inc < 1000) {
				if(scoreKeep >= 64) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
			}
			if (inc > 1000 & inc < 3000) {
				if(scoreKeep >= 48) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
				//if(inc == 1250 || inc == 1750) {
					//handler.addObject(new Speed(r.nextInt(Game.WIDTH), 0, ID.Speed_Powerup));
				//}
			}
			if (inc > 3000 & inc < 8000) {
				if(scoreKeep >= 48) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
				if(scoreKeep == 24) {
					handler.addObject(new FastMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
				
			}
			if (inc > 8000 & inc < 12000) {
				if(scoreKeep >= 36) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
				if(scoreKeep == 24) {
					handler.addObject(new FastMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
			}
			if (inc > 12000 && inc < 18000) {
				if(scoreKeep == 15) {
					handler.addObject(new FastMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
				if(scoreKeep == 19) {
					handler.addObject(new SideMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
				if(scoreKeep >= 20) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
		
			}
			if (inc > 18000) {
				if(scoreKeep >= 20) {
					scoreKeep = 0;
					hud.setLevel(hud.getLevel() +1);
					handler.addObject(new Mine(r.nextInt(Game.WIDTH), 0, ID.Mine, handler));
				}
				if(scoreKeep == 15) {
					handler.addObject(new FastMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
				if(scoreKeep == 19) {
					handler.addObject(new SideMine(r.nextInt(Game.WIDTH),0,ID.Mine,handler));
				}
			}
		}
	}
}