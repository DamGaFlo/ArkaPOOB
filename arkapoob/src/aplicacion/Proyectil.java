package aplicacion;

import math.Vector2D;

public abstract class Proyectil extends GameObject{
	
	public  static final int DAMAGE_NORMAL = 1;
	private int damage;
	private ArkaPOOB arkaPOOB; 
	private Player ultimoGolpeador;
	private Vector2D velocidad;
	private boolean enAire =false;
	
        /**
         * 
         * @param posicion - Vector en el cual se encuentra el proyectil
         * @param textura - Imagen que posee la bola
         * @param arkaPOOB - Juego sobre el cual actua 
         * @param ultimoGolpeador - ultimo jugador que dio un golpe en la bola
         * @param damage - daño que genera la bola
         */
	public Proyectil(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,Player ultimoGolpeador,int damage){
		super(posicion,width,height,estado);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		velocidad = new Vector2D(4,-4);
		this.damage = damage;
		if(ultimoGolpeador == null) enAire = true;
	}
        /**
         * 
         * @param posicion - Vector en el cual se encuentra el proyectil
         * @param textura - Imagen que posee la bola
         * @param arkaPOOB - Juego sobre el cual actua 
         * @param ultimoGolpeador - ultimo jugador que dio un golpe en la bola
         * @param damage - daño que genera la bola
         * @param velocidad - velocidad de desplazamiento de la bola 
         */
	public Proyectil(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,Player ultimoGolpeador,Vector2D velocidad,int damage){
		super(posicion,width,height,estado);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		this.velocidad = velocidad;
		this.damage = damage;
		if(ultimoGolpeador == null) enAire = true;
	}
	/**
         * 
         * @return Velocidad de la bola
         */
	public Vector2D getVelocidad(){
		return velocidad;
	}
        
        /**
         * Cambia el valor de la velocidad y angulo del proyectil
         * @param v - Vector de velocidad que contiene el proyectil
         */
	public void setVelocidad(Vector2D v){
		velocidad = v;
		
	}
        /**
         * 
         * @return Juego sobre el cual se actua
         */
	public ArkaPOOB getArkaPoob(){
		return arkaPOOB;
	}
        
        /**
         * 
         * @return Ancho maximo del juego 
         */
	public int getMaxWidth(){
		return arkaPOOB.getWidth();
	}
        /**
         * 
         * @return Largo maximo del juego
         */
	public int getMaxHight(){
		return arkaPOOB.getHight();
	}
        /**
         * 
         * @return damage que genera la bola
         */
	public int getDamage(){
		return damage;
	}
        
	public abstract void ajustarSobreBase();
	//sobreescribir 
        
        /**
         * 
         * @return Jugador que realizo el ultimo golpe
         */
	public Player getUltimoGolpe(){
		return ultimoGolpeador;
	}
	public abstract void mov();
	public abstract void mov(Vector2D vel);
        
        /**
         * Define si la bola ha sido lanzada o no
         */
	public void diparado(){
		enAire = true;
	}
        /**
         * Define si a bola ha sido atrapada
         */
	public void atrapado(){
		enAire = false;
	}
        /**
         * 
         * @return Estado actual de la bola
         */
	public boolean getEnAire(){
		return enAire;
	}
	
	
	
	
}