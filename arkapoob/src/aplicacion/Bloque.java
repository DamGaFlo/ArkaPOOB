package aplicacion;


import math.Vector2D;


public class Bloque extends GameObject{
	
	private int resistencia;
	public static final int PUNTOS_BLOQUE = 100,PUNTOS_BLOQUE_SORP = 300,PUNTOS_BLOQUE_DURO = 200;
	public static final int RES_BLOQUE = 1,RES_BLOQUE_DURO=2;
	public static final int WIDTH_DEFAULT = 100, HEIGHT_DEFAULT = 50;
	private ArkaPOOB arkaPOOB;
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public Bloque(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,width,height,estado);
		this.resistencia = resistencia;
		this.arkaPOOB = arkaPOOB;
		
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public Bloque(Vector2D posicion,ArkaPOOB arkaPOOB){
		super(posicion,WIDTH_DEFAULT,50,0);
		this.resistencia = RES_BLOQUE;
		this.arkaPOOB = arkaPOOB;
	}
	/**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param textura - Imagen que contiene el bloque
     * @param arkaPOOB - Juego sobre el cual se posiciona
     */
	public Bloque(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
		super(posicion,width,height,estado);
		this.resistencia = RES_BLOQUE;
		this.arkaPOOB = arkaPOOB;
	}
        /**
         * Actualiza el estado del bloque
         */
        @Override
	public void update(){
		//System.out.println(resistencia);
		if(resistencia<=0){
			destroy();
		}
	}

        
        /**
         * 
         * @param delta - cantidad que se le cambia de resistenca
         * @return - puntos obtenidos por el golpe
         */
	public int colicion(Proyectil p){
		resistencia -=p.getDamage();
		if(resistencia<=0){
			return PUNTOS_BLOQUE;
		}
		return 0;
	}
        
        /**
         * 
         * @return Resistencia del bloque
         */
	public int getResistencia(){
		return resistencia;
	}
        /**
         * 
         * @return Juego sobre el cual se actua
         */
	public ArkaPOOB getArkaPOOB(){
		return arkaPOOB;
	}
        /**
         * Destruye el bloque
         */
	public void destroy(){
		getArkaPOOB().getBloques().remove(this);
			
	}
	public  Representacion representacion() {
		return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
	}
	public String getNombre() {
	    return this.getClass().getSimpleName();
	}
	
}