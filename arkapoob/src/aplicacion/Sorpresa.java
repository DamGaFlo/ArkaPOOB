package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.*;
import java.util.*;

public abstract class Sorpresa extends GameObject{
	public static final int NUM_SORPRESAS = 2;
	
	public static final int SLOW_BALL = 1;
	public static final int FAST_BALL = 1;
	
	public static final Vector2D velocidad = new Vector2D(0,6);
	private boolean visible = false;
	private Bloque bloque;
	private boolean destruida = false;
	
        /**
         * 
         * @param posicion - posicion de la sorpresa
         * @param textura - imagen que define el tipo de sorpresa
         * @param bloque - Bloque que contiene la sorpresa
         */
	public Sorpresa(Vector2D posicion,BufferedImage textura,Bloque bloque){
		super(posicion,textura);
		this.bloque = bloque;
	}
	
        
	public abstract void efecto(Base base);
	
        /**
         * Genera el movimiento de la sorpresa al caer
         */
	public void caer(){
		setPosicion(getPosicion().add(velocidad));
	}
        
        /**
         * Modifica la visibilidad de la sorpresa
         * @param b - define si es visible o no
         */
	public void setVisible(boolean b){
		visible = b;
	}
        /**
         * Dibuja la sorpresa en una posicion respectiva
         * @param g 
         */
        @Override
	public void draw(Graphics g){
		if(visible) g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
        /**
         * Actualiza el comportamiento de la sorpresa
         */
        @Override
	public void update(){
		if(visible){
			caer();
			colicion();
		}
	}
        /**
         * 
         * @return Juego sobre el cual actua
         */
	public ArkaPOOB getArkaPoob(){
		return bloque.getArkaPOOB();
	}
	/**
         * 
         * @param posicion - Posicion de la sospresa
         * @param bloque - bloque que la contiene
         * @return tipo de sorpresa aleatoria
         */
	public static Sorpresa randomSorpresa(Vector2D posicion,Bloque bloque){
		Random random = new Random();
		return getSorpresa(random.nextInt(NUM_SORPRESAS)+1,posicion,bloque);
	} 
        /**
         * 
         * @param numPower - poder que posee la sorpresa
         * @param posicion - posicion en la cual estara ubicada
         * @param bloque - bloque que la contiene
         * @return Sorpresa con el tipo de poder
         */
	public static Sorpresa getSorpresa(int numPower,Vector2D posicion,Bloque bloque){
		Sorpresa envio;
		switch(numPower){
			case 1:
				envio = new FastBall(posicion,Recursos.fastBall,bloque);
				break;
			case 2:
				envio = new SlowBall(posicion,Recursos.slowBall,bloque);
				break;
			default:
				envio = null;
		}
		return envio;
	}
        
        /**
         * Define el comportamiento de la sospresa al salir del borde de juego
         */
	public void bordes(){
		if(getCentro().getY()+getHeight()/2 >= getMaxHight()){
			destroy();
		}
	}
        
        /**
         * Define el comportamiento al empezar a caer
         */
	public void colicion(){
		bordes();
		if(!destruida){
			colicionPlayers();
		}
		
	}
        /**
         * define el comportamiento al ser atrapada por un jugador
         */
	public void colicionPlayers(){
		ArrayList<Player> players = getArkaPoob().getPlayers();
		Vector2D miCentro = getCentro();
		for(Player p: players){
			Vector2D posBase = p.getBase().getCentro();
			int baseWidth = p.getBase().getWidth(),baseHeight = p.getBase().getHeight();
			if(Math.abs(posBase.getX()-miCentro.getX()) <= (getWidth()+baseWidth)/2 && Math.abs(posBase.getY()-miCentro.getY()) <= (getHeight()+baseHeight)/2){
				efecto(p.getBase());
				destroy();
				break;
				
			}
		}
	}
        
        /**
         * Destruye la sorpresa
         */
	public void destroy(){
		getArkaPoob().getSorpresas().remove(this);
		destruida = true;
	}
        /**
         * 
         * @return Ancho maximo del juego
         */
	public int getMaxWidth(){
		return getArkaPoob().getWidth();
	}
        /**
         * Alto maximo de juego
         * @return 
         */
	public int getMaxHight(){
		return getArkaPoob().getHight();
	}
	
}