package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

import math.Vector2D;

public class ArkaPOOB2P extends ArkaPOOB implements Serializable{
	
	/**
         * Prepara dos jugadores
         */
	protected void preparaPlayer() {
		players.add(new Player(3,Base.BIG,new Vector2D(10,HEIGHT-150),this,"izq1","der1","fire1"));
		players.add(new PlayerMimo(3,Base.PEGAJOSA,new Vector2D(WIDTH-10,HEIGHT-150),this,"izq2","der2","fire2"));
	} 
	/**
         * Reinicia el juego de otra manera
         */
	protected void reInicio() {
		if(players.size()==1){
			super.reInicio();
		}
		else if(players.size()==2) {
			players.get(0).getBase().setPosicion(new Vector2D(10,HEIGHT-players.get(0).getBase().getHeight()-50));
			players.get(1).getBase().setPosicion(new Vector2D(WIDTH - players.get(1).getBase().getWidth()-10,HEIGHT-players.get(1).getBase().getHeight()-50));
			for(Player p:players) p.getBase().restaura(); 
			generaBolas();
			sorpresas.clear();
		}
	}
        /**
         * Genera las bolas necesarias
         */
	protected void generaBolas() {
		if(players.size()==1) {
			super.generaBolas();
		}
		else if(players.size()==2) {
			double magnitudVel = 6.5;
			bolasEnJuego.add(new BolaNormal(new Vector2D(WIDTH/2,HEIGHT/2),this,null,Vector2D.getVector(Math.random()*(MAX_ANGLE_INIT-MIN_ANGLE_INIT)+(MIN_ANGLE_INIT),magnitudVel)));
		}
	}
        /**
         * actualiza el estado del juego
         */
	public void update() {
		if(!getPause()) {
			updateOrdenesPlayer2();
			super.update();
			ajustaBases();
		}
	}
	/**
	 * ajusta las plataformas si estan solapadas
	 */
	private void ajustaBases() {
		if(players.size()>1) {
			Base base1,base2;
			if(players.get(0).getBase().getPosicion().getX()>players.get(1).getBase().getPosicion().getX()) {
				base1 = players.get(1).getBase();
				base2 = players.get(0).getBase();	
			}
			else {
				base1 = players.get(0).getBase();
				base2 = players.get(1).getBase();
			}
			if(base1.solapadas(base2)) {
				if(base1.getPosicion().getX()+base1.getWidth()+base2.getWidth()+20>WIDTH) {
					base1.setX(WIDTH-base1.getWidth());
					base2.setX(WIDTH-base1.getWidth()-base2.getWidth()-20);
				}
				else {
					base2.setX(base1.getPosicion().getX());
					base1.cambioX(base2.getWidth()+20);
				}
			}
			
		}
	}
	
}
