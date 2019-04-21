package aplicacion;

import java.util.*;
import math.Vector2D;
import java.awt.Graphics;
import gameObjects.*;
public class Player{
	private int puntos;
	private int vidas;
	private Base base;
	private Chronometer time;
	private ArkaPOOB arkaPOOB;
	public Player(int vidas,int plataforma,Vector2D posicion,ArkaPOOB arkaPOOB){
		this.vidas = vidas;
		this.arkaPOOB = arkaPOOB;
		time = new Chronometer();
		base = Base.getBase(plataforma,posicion,this);
	}
	/**
	*update actualiza la logica del funcionamiento de la bola
	*/
	public void update(){
		base.update();
		time.update();
	}
	/**
	*draw genera la textura sobre graphis en el buffer en este caso de la base del jugador
	*/
	public void draw(Graphics g){
		base.draw(g);
	}
	public Base getBase(){
		return base;
	}
	public void addScore(int puntosObtenidos){
		puntos+=puntosObtenidos;
	}
	public void tiempoDeEspera(){
		time.run(300);
	}
	public boolean enEspera(){
		return time.isRunning();
	}
	public void cambioVida(int delta){
		vidas+=delta;
	}
	public ArkaPOOB getArkaPOOB(){
		return arkaPOOB;
	}
}