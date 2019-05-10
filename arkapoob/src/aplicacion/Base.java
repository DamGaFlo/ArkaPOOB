package aplicacion;

import math.Vector2D;
import input.*;

public class Base extends GameObject{
	
	public static final int NORMAL = 1;
	private Player player;
	private Proyectil residente;
	private int moxEnX = 10;
	private String izq;
	private String der;
	private String fire;
	
		/**
     	* 
     	* @param posicion - Vector2D con la informacion de la posicion de la base
     	* @param textura - Imagen sobre el tipo de base con la que se juega
     	* @param player - Jugador usuario de la base
     	* @param residente - Si existe bola sobre ella actualmente
     	*/
	public Base(Vector2D posicion,int width,int height,int estado, Player player,BolaNormal residente,String izq,String der,String fire){
		super(posicion,width,height,estado);
		this.player = player;
		this.residente = residente;
		this.izq = izq;
		this.der = der;
		this.fire = fire;
	}
	
		/**
		 * 
		 * @param posicion - Vector2D con la informacion de la posicion de la base
		 * @param player - Jugador usuario de la base
		 * @param residente - Si existe bola sobre ella actualmente
		 */
	public Base(Vector2D posicion, Player player,BolaNormal residente,String izq,String der,String fire){
		this(posicion,200,20,0,player,residente,izq,der,fire);
	}
        
        /**
         * 
         * @param posicion - Vector2D con la informacion de la posicion de la base
         * @param textura - Imagen sobre el tipo de base con la que se juega
         * @param player - Jugador usuario de la base
         */
	public Base(Vector2D posicion, Player player,String izq,String der,String fire){
		this(posicion,player,null,izq,der,fire);
	}
	
	
        /**
         * Actualiza el estado de la base
         */
        @Override
	public void update(){
		if(player.getArkaPOOB().orden(izq)){
			if(getPosicion().getX()-moxEnX >= 0){
				getPosicion().cambioX(-moxEnX);
				if(residente!=null) residente.mov(new Vector2D(-moxEnX,0));
			}
				
		}
		if(player.getArkaPOOB().orden(der)){
			if(getPosicion().getX()+getWidth()+moxEnX <= getArkaPOOB().getWidth()){
				getPosicion().cambioX(moxEnX);
				if(residente!=null) residente.mov(new Vector2D(moxEnX,0));
			}
		
		}
		if(player.getArkaPOOB().orden(fire)){
			if(residente!=null) {
				residente.diparado();
				residente = null;
			}
		}
	}
        

        
        /**
         * 
         * @param numBase - el numero de la base que juega
         * @param posicion - informacion de la posicion
         * @param player - Jugador usuario de esta
         * @return Base clon segun los datos dirigidos
         */
	public static Base getBase(int numBase,Vector2D posicion,Player player,String izq,String der,String fire){
		if(numBase == 1) return new Base(posicion,player,izq,der,fire);
		return null;
		
	}
        /**
         * Define la habilidad de la base
         */
	public void colicion(BolaNormal b) {}
        
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
	
	public  Representacion representacion() {
		return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
	}
	
	public String getNombre() {
	    return this.getClass().getSimpleName();
	}
	
}