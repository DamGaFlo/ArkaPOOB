package aplicacion;

import java.util.ArrayList;

import math.Vector2D;

public class ArkaPOOB2P extends ArkaPOOB {
	
	
	protected void preparaPlayer() {
		players.add(new Player(3,1,new Vector2D(10,HEIGHT-150),this,"izq1","der1","fire1"));
		players.add(new Player(3,1,new Vector2D(WIDTH-10,HEIGHT-150),this,"izq2","der2","fire2"));
	}
	
	protected void reInicio() {
		if(players.size()==1){
			super.reInicio();
		}
		else if(players.size()==2) {
			players.get(0).getBase().setPosicion(new Vector2D(10,HEIGHT-players.get(0).getBase().getHeight()-50));
			players.get(1).getBase().setPosicion(new Vector2D(WIDTH - players.get(1).getBase().getWidth()-10,HEIGHT-players.get(1).getBase().getHeight()-50));
			generaBolas();
			sorpresas.clear();
		}
	}
	protected void generaBolas() {
		if(players.size()==1) {
			super.generaBolas();
		}
		else if(players.size()==2) {
			double magnitudVel = 6.5;
			bolasEnJuego.add(new BolaNormal(new Vector2D(WIDTH/2,HEIGHT/2),this,null,Vector2D.getVector(Math.random()*(MAX_ANGLE_INIT-MIN_ANGLE_INIT)+(MIN_ANGLE_INIT),magnitudVel)));
		}
	}
	public void update() {
		if(!getPause()) {
			updateOrdenesPlayer2();
			super.update();
		}
	}
	
}
