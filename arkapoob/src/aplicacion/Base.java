package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class Base extends GameObject implements Serializable{
	
	public static final int NORMAL = 0,ESPECIAL =1,PEGAJOSA = 2,BIG = 3,SMALL = 4, SHOOTER = 5;
	private Player player;
	private Proyectil residente;
	private int moxEnX;
	private EstadoBase estadoBase ;
	private int numEstado;
	private int coliciones;
	
		/**
     	* 
     	* @param posicion - Vector2D con la informacion de la posicion de la base
     * @param width
     * @param height
     * @param estado
     	* @param player - Jugador usuario de la base
     * @param tipoBase
     	* @param residente - Si existe bola sobre ella actualmente
     	*/
	public Base(Vector2D posicion,int width,int height,int estado, Player player,BolaNormal residente,int tipoBase){
		super(posicion,width,height,estado);
                this.moxEnX = 10;
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
        this.moxEnX = 10;
	}
        
        /**
         * 
         * @param posicion - Vector2D con la informacion de la posicion de la base
         * @param player - Jugador usuario de la base
         * @param tipoBase
         */
	public Base(Vector2D posicion, Player player,int tipoBase){
		this(posicion,140,20,0,player,null,tipoBase);
        this.moxEnX = 10;
	}
	
	public Base(Vector2D posicion, Player player){
		this(posicion,player,null);
        this.moxEnX = 10;
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
         * @param b
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
	/**
         * Representacion del estado de la base
         * @return 
         */
        @Override
	public  Representacion representacion() {
		return estadoBase.representacion(this);
	}
        /**
         * Ancho de la base
         * @return 
         */
        @Override
	public int getWidth() {
		return estadoBase.getWidth(super.getWidth());
	}
        /**
         * Alto de la base
         * @return 
         */
        @Override
	public int getHeight() {
		return estadoBase.getHeight(super.getHeight());
	}
	/**
         * Nombre de la base
         * @return 
         */
        @Override
	public String getNombre() {
	    return estadoBase.getNombre();
	}
        /**
         * Movimiento en X
         * @return 
         */
	public int getMoxEnX(){
		return moxEnX;
	}
        /**
         * Asigna un estado a la base
         * @param estado 
         */
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
		case SHOOTER:
			estadoBase = new BaseShooter(this);
			break;                
		}
		
	}
        /**
         * mueve a la izquierda
         */
	public void movIzq() {
		estadoBase.movIzq(this);
	}
        /**
         * mueve a la derecha
         */
	public void movDer() {
		estadoBase.movDer(this);
	}
        /**
         * Dispara la bola
         */
	public void fire() {
		estadoBase.fire(this);
	}
	/**
         * vuelve pegajosa la base
         */
	public void pegajosa() {
		coliciones = 0;
		estadoBase.pegajosa(this);
	}
        /**
         * Vuelve normal la base
         */
	public void normal() {
		coliciones = 0;
		estadoBase.normal(this);
	}
        /**
         * vuelve especial la base
         */
	public void especial() {
		coliciones = 0;
		estadoBase.especial(this);
	}
        /**
         * vuelve pequeña la base
         */
	public void small() {
		coliciones = 0;
		estadoBase.small(this);
	}
        /**
         * vuelve grande la base
         */
	public void big() {
		coliciones = 0;
		estadoBase.big(this);
	}
        /**
         * permite a la base disparar
         */
	public void shooter(){
		coliciones = 0;
		estadoBase.shooter(this);
	}
        /**
         * Define el estado de la base
         * @param estado 
         */
	public void setEstado(EstadoBase estado) {
		estadoBase = estado;
	}
        /**
         * Define la cantidad de coliciones que ha recibido
         * @return 
         */
	public int getColiciones() {
		return coliciones;
	}
        /**
         * Cambia el numero de coliciones que ha recibido
         * @param numColiciones 
         */
	public void setColiciones(int numColiciones) {
		coliciones = numColiciones;
	}
        /**
         * restaura es estado de la base
         */
	public void restaura() {
		asignaEstado(numEstado);
	}
    public Player getPlayer(){
        return player;
    }
    
    public boolean solapadas(Base base) {
    	double centroDiferencia = Math.abs(getCentro().getX()- base.getCentro().getX());
    	int largo = getWidth()/2 + base.getWidth()/2;
    	if(centroDiferencia<=largo) return true; 
    	else return false;
    }
    public void setX(double x) {
    	double diferencia = getPosicion().getX()-x;
    	getPosicion().setX(x);
    	if(residente!=null) 
    		residente.getPosicion().cambioX(-diferencia);
    	
    }
    public void cambioX(double delta) {
    	getPosicion().cambioX(delta);
    	if(residente!=null)
    	
    		residente.getPosicion().cambioX(delta);
    }
    public boolean inverso() {
    	return estadoBase.inverso();
    }
}