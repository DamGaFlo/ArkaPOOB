package aplicacion;

import math.Vector2D;

public class BloqueSorpresa extends Bloque{
	
	private Sorpresa sorpresa;
	
    	/**
    	 * 
    	 * @param posicion
    	 * @param width
    	 * @param height
    	 * @param estado
    	 * @param arkaPOOB
    	 * @param sorpresa
    	 */
	public BloqueSorpresa(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,Sorpresa sorpresa){
		super(posicion,width,height,estado,arkaPOOB);
		this.sorpresa = sorpresa;
	}
		/**
		 * 
		 * @param posicion
		 * @param width
		 * @param height
		 * @param estado
		 * @param arkaPOOB
		 */
	public BloqueSorpresa(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
		super(posicion,width,height,estado,arkaPOOB);
		sorpresa = Sorpresa.randomSorpresa(getCentro(),this);
	}
		/**
		 * 
		 * @param posicion
		 * @param arkaPOOB
		 */
	public BloqueSorpresa(Vector2D posicion,ArkaPOOB arkaPOOB){
		this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
	}
        
        /**
         * 
         * @param delta - cambio en la resistencia del bloque
         * @return puntos obtenidos
         */
        @Override
	public int colicion(Proyectil p){
		super.colicion(p);
		if(getResistencia()<=0){
			habilidad(p);
			return PUNTOS_BLOQUE_SORP;
		}
		return 0;
	}
	/**
         * Define la habilidad que tiene el bloque
         */
	public void habilidad(Proyectil p){
		getArkaPOOB().getSorpresas().add(sorpresa);
	}
	public  Representacion representacion() {
		return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
	}
	public String getNombre() {
    	return this.getClass().getSimpleName();
    }
}