package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import input.*;
import graficos.*;

public class Base extends GameObject{
	
	public static final int NORMAL = 1;
	private Player player;
	private Proyectil residente;
	private int moxEnX = 10;
	
	/**
         * 
         * @param posicion - Vector2D con la informacion de la posicion de la base
         * @param textura - Imagen sobre el tipo de base con la que se juega
         * @param player - Jugador usuario de la base
         * @param residente - Si existe bola sobre ella actualmente
         */
	public Base(Vector2D posicion,BufferedImage textura, Player player,BolaNormal residente){
		super(posicion,textura);
		this.player = player;
		this.residente = residente;
	}
        
        /**
         * 
         * @param posicion - Vector2D con la informacion de la posicion de la base
         * @param textura - Imagen sobre el tipo de base con la que se juega
         * @param player - Jugador usuario de la base
         */
	public Base(Vector2D posicion,BufferedImage textura, Player player){
		super(posicion,textura);
		this.player = player;
		this.residente = null;
	}
	
        /**
         * Actualiza el estado de la base
         */
        @Override
	public void update(){
		if(Teclado.getLeft()){
			if(getPosicion().getX()-moxEnX >= 0){
				getPosicion().cambioX(-moxEnX);
				if(residente!=null) residente.mov(new Vector2D(-moxEnX,0));
			}
				
		}
		if(Teclado.getRight()){
			if(getPosicion().getX()+getWidth()+moxEnX <= getArkaPOOB().getWidth()){
				getPosicion().cambioX(moxEnX);
				if(residente!=null) residente.mov(new Vector2D(moxEnX,0));
			}
		
		}
		if(Teclado.getSpace()){
			if(residente!=null) {
				residente.diparado();
				residente = null;
			}
		}
	}
        
	/**
	*draw genera la textura sobre graphis en el buffer
        * @param g - Define el grafico sobre el que se genera la textura
	*/
        @Override
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
        
        /**
         * 
         * @param numBase - el numero de la base que juega
         * @param posicion - informacion de la posicion
         * @param player - Jugador usuario de esta
         * @return Base clon segun los datos dirigidos
         */
	public static Base getBase(int numBase,Vector2D posicion,Player player){
		if(numBase == 1) return new Base(posicion,Recursos.plataforma,player);
		return null;
		
	}
        /**
         * Define la habilidad de la base
         */
	public void habilidad(){}
        
        /**
         * 
         * @param residente - Define la bola sobre la base
         */
	public void setResidente(Proyectil residente){
		this.residente = residente;
	}
        
        /**
         * Para la base que cambia de movimiento
         */
	public void movOpuesto(){
		moxEnX*=-1;
	}
        /**
         * 
         * @return  Juego sobre el cual esta actuando la base
         */
	public ArkaPOOB getArkaPOOB(){
		return player.getArkaPOOB();
	}
	
}