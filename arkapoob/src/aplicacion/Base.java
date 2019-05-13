package aplicacion;

import math.Vector2D;

public class Base extends GameObject{
	
	public static final int NORMAL = 0,ESPECIAL =1,PEGAJOSA = 2,BIG = 3,SMALL = 4;
	private Player player;
	private Proyectil residente;
	private int moxEnX = 10;
	private EstadoBase estadoBase ;
	private int numEstado;
	private int coliciones;
	
		/**
     	* 
     	* @param posicion - Vector2D con la informacion de la posicion de la base
     	* @param textura - Imagen sobre el tipo de base con la que se juega
     	* @param player - Jugador usuario de la base
     	* @param residente - Si existe bola sobre ella actualmente
     	*/
	public Base(Vector2D posicion,int width,int height,int estado, Player player,BolaNormal residente,int tipoBase){
		super(posicion,width,height,estado);
		this.estadoBase = new BaseNormal(this);
		this.player = player;
		this.residente = residente;
		coliciones = 0;
		this.numEstado = tipoBase;
		asignaEstado(tipoBase);
	}
	
		/**
		 * 
		 * @param posicion - Vector2D con la informacion de la posicion de la base
		 * @param player - Jugador usuario de la base
		 * @param residente - Si existe bola sobre ella actualmente
		 */
	public Base(Vector2D posicion, Player player,BolaNormal residente){
		this (posicion,140,20,0,player,residente,NORMAL);
	}
        
        /**
         * 
         * @param posicion - Vector2D con la informacion de la posicion de la base
         * @param textura - Imagen sobre el tipo de base con la que se juega
         * @param player - Jugador usuario de la base
         */
	public Base(Vector2D posicion, Player player,int tipoBase){
		this(posicion,140,20,0,player,null,tipoBase);
	}
	
	public Base(Vector2D posicion, Player player){
		this(posicion,player,null);
	}
	
	
        /**
         * Actualiza el estado de la base
         */
        @Override
	public void update(){
        estadoBase.update(this);

	}

        
        /**
         * Define la habilidad de la base
         */
	public void colicion(BolaNormal b) {
		estadoBase.colicion(b, this);
	}
        
        /**
         * 
         * @param residente - Define la bola sobre la base
         */
	public void setResidente(Proyectil residente){
		this.residente = residente;
	}
	public Proyectil getResidente() {
		return residente;
	}
        
        /**
         * 
         * @return  Juego sobre el cual esta actuando la base
         */
	public ArkaPOOB getArkaPOOB(){
		return player.getArkaPOOB();
	}
	
	public  Representacion representacion() {
		return estadoBase.representacion(this);
	}
	public int getWidth() {
		return estadoBase.getWidth(super.getWidth());
	}
	public int getHeight() {
		return estadoBase.getHeight(super.getHeight());
	}
	
	public String getNombre() {
	    return estadoBase.getNombre();
	}

	public int getMoxEnX(){
		return moxEnX;
	}

	public void asignaEstado(int estado) {
		switch (estado) {
		case NORMAL:
			estadoBase = new BaseNormal(this);
			break;
		case ESPECIAL:
			estadoBase = new BaseEspecial(this);
			break;
		case PEGAJOSA:
			estadoBase = new BasePegajosa(this);
			break;
		case SMALL:
			estadoBase = new BaseSmall(this);
			break;
		case BIG:
			estadoBase = new BaseBig(this);
			break;
		}
		
	}
	public void movIzq() {
		estadoBase.movIzq(this);
	}
	public void movDer() {
		estadoBase.movDer(this);
	}
	public void fire() {
		estadoBase.fire(this);
	}
	
	public void pegajosa() {
		coliciones = 0;
		estadoBase.pegajosa(this);
	}
	public void normal() {
		coliciones = 0;
		estadoBase.normal(this);
	}
	public void especial() {
		coliciones = 0;
		estadoBase.especial(this);
	}
	public void small() {
		coliciones = 0;
		estadoBase.small(this);
	}
	public void big() {
		coliciones = 0;
		estadoBase.big(this);
	}
	public void setEstado(EstadoBase estado) {
		estadoBase = estado;
	}
	public int getColiciones() {
		return coliciones;
	}
	public void setColiciones(int numColiciones) {
		coliciones = numColiciones;
	}
	public void restaura() {
		asignaEstado(numEstado);
	}
}