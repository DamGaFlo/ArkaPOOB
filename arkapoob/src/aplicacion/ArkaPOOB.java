package aplicacion;

import java.awt.Graphics;
import math.Vector2D;
import java.awt.image.BufferedImage;
import graficos.Recursos;
import java.util.*;
import input.*;

public class ArkaPOOB{
	private final int MIN_ANGLE_INIT = 225,MAX_ANGLE_INIT = 315;
	private int numBloques;
	private ArrayList<Proyectil> bolasEnJuego;
	private ArrayList<Player> players;
	private ArrayList<Bloque> bloques;
	private boolean pause = false;
	private int lvl =1;
	private ArrayList<Player> datosJugadores;
	private ArrayList<Sorpresa> sorpresas;
	
	private static int WIDTH,HIGHT;
	
	/**
         * Constructor
         * @param width - Ancho del juego
         * @param hight - Alto del Juego
         */
	public ArkaPOOB(int width,int hight){
		WIDTH = width;
		HIGHT = hight;
		
		bolasEnJuego = new ArrayList<Proyectil>();
		players = new ArrayList<Player>();
		bloques = new ArrayList<Bloque>();
		sorpresas = new ArrayList<Sorpresa>();
		inicio();
		
	}

        /**
         * Actualiza el juego segun cada movimiento
         */
	public void update(){
		if(bolasEnJuego.isEmpty()){
			reInicio();
		}
		ArrayList<Proyectil> bolas = new ArrayList<Proyectil>(bolasEnJuego);
		for(Proyectil p: bolas) p.update();
		for(Player p:players) p.update();
		ArrayList<Bloque> iterador =  new ArrayList<Bloque>(bloques);
		for(Bloque b: iterador) b.update();
		if(!sorpresas.isEmpty()){
			ArrayList<Sorpresa> iteraSorpresas = new ArrayList<Sorpresa>(sorpresas);
			for(Sorpresa s:iteraSorpresas) s.update();
		}
	}
		
	/**
         * dibuja el juego y cada uno de sus elementos
         * @param g - Graficos del juego 
         */
	public void draw(Graphics g){
		for(Proyectil p:bolasEnJuego) p.draw(g);
		for(Player p:players) p.draw(g);
		for(Bloque b: bloques) b.draw(g);
		for(Sorpresa s: sorpresas) s.draw(g);
	}
	
        /**
         * 
         * @return Ancho del juego
         */
	public int getWidth(){
		return WIDTH;
	}
        
        /**
         * 
         * @return Alto del juego
         */
	public int getHight(){
		return HIGHT;
	}
        
        /**
         * 
         * @return lista de los jugadores
         */
	public ArrayList<Player> getPlayers(){
		return players;
	}
        
        /**
         * 
         * @return Lista de Bloques
         */
	public ArrayList<Bloque> getBloques(){
		return bloques;
	}
        
        /**
         * 
         * @return Lista de bolas en la partida
         */
	public ArrayList<Proyectil> getBolasEnJuego(){
		return bolasEnJuego;
	}
        
        /**
         * Da inicio al juego construyendo los primeros mapas
         */
	private void inicio(){
		players.add(new Player(3,1,new Vector2D(WIDTH/2,HIGHT-60),this));
		datosJugadores = new ArrayList<Player>(players);
		reInicio();
		
		bloques.add(new Bloque(new Vector2D(100,100),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(205,100),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(310,100),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(415,100),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(520,100),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(100,160),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(205,160),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(310,160),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(415,160),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(520,160),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(100,220),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(205,220),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(310,220),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(415,220),Recursos.bloqueRojo,this));
		bloques.add(new Bloque(new Vector2D(520,220),Recursos.bloqueRojo,this));
	}
        
        /**
         * Reinicia el juego por cada vez que se pierde
         */
	private void reInicio(){
		if(!players.isEmpty()){
			players.get(0).getBase().setPosicion(new Vector2D(WIDTH/2-players.get(0).getBase().getWidth()/2,HIGHT-players.get(0).getBase().getHeight()-40));
		}
		generaBolas();
	}
        
        /**
         * genera las bolas necesarias para el juego
         */
	private void generaBolas(){
		double magnitudVel = 6.5;
		Player jugador = players.get((int)(Math.random()*(players.size()-1)));
		Base base = jugador.getBase();
		bolasEnJuego.add(new BolaNormal(new Vector2D(base.getCentro().getX(),base.getPosicion().getY()),Recursos.bola,this,jugador,Vector2D.getVector(Math.random()*(MIN_ANGLE_INIT-MIN_ANGLE_INIT)+(MIN_ANGLE_INIT),magnitudVel)));
		bolasEnJuego.get(bolasEnJuego.size()-1).ajustarSobreBase();
		base.setResidente(bolasEnJuego.get(bolasEnJuego.size()-1));
	}
        
        /**
         * Define el estado de pausa del juego
         * @param o - Define si el juego esta en pausa
         */
	private void setPause(boolean o){
		pause = o;
	}
        
        /**
         * aumenta un nivel por juego
         */
	public void nextLvl(){
		lvl+=1;
	}
        
        /**
         * 
         * @return  Lista de informacion sobre los jugadores
         */
	public ArrayList<Player> getJugadores(){
		return datosJugadores;
	}
        
        /**
         * 
         * @return Lista de informacion sobre las sorpresas en el juego
         */
	public ArrayList<Sorpresa> getSorpresas(){
		return sorpresas;
	}
        
        /**
         * 
         * @return Nivel actual del juego
         */
	public int getLvl(){
		return lvl;
	}
}
/*
		bloques.add(new BloqueSorpresa(new Vector2D(100,100),Recursos.bloqueSorpresa,this));
		bloques.add(new BloqueSorpresa(new Vector2D(205,100),Recursos.bloqueSorpresa,this));
		bloques.add(new BloqueDuro(new Vector2D(310,100),Recursos.bloqueDuro,this));
		bloques.add(new BloqueSorpresa(new Vector2D(415,100),Recursos.bloqueSorpresa,this));
		bloques.add(new Bloque(new Vector2D(520,100),Recursos.bloqueRojo,this));
		bloques.add(new BloqueDuro(new Vector2D(100,160),Recursos.bloqueDuro,this));
		bloques.add(new BloqueSorpresa(new Vector2D(205,160),Recursos.bloqueSorpresa,this));
		bloques.add(new BloqueDuro(new Vector2D(310,160),Recursos.bloqueDuro,this));
		bloques.add(new BloqueIndestructible(new Vector2D(415,160),Recursos.bloqueIndestructible,this));
		bloques.add(new Bloque(new Vector2D(520,160),Recursos.bloqueRojo,this));
		bloques.add(new BloqueSorpresa(new Vector2D(100,220),Recursos.bloqueSorpresa,this));
		bloques.add(new BloqueIndestructible(new Vector2D(205,220),Recursos.bloqueIndestructible,this));
		bloques.add(new BloqueDuro(new Vector2D(310,220),Recursos.bloqueDuro,this));
		bloques.add(new BloqueSorpresa(new Vector2D(415,220),Recursos.bloqueSorpresa,this));
		bloques.add(new BloqueIndestructible(new Vector2D(520,220),Recursos.bloqueIndestructible,this));
		*/
//javac -d bin src/presentacion/*.java src/aplicacion/*.java src/math/*.java src/gameObjects/*.java src/graficos/*.java src/input/*.java
//java -cp bin presentacion.PantallaInicio