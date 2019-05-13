package aplicacion;

import math.Vector2D;

public class AgrandadorBase extends Sorpresa{
	public AgrandadorBase(Vector2D posicion,int width,int height,int estado,Bloque bloque){
		super(posicion,width,height,estado,bloque);

	}
		/**
		 * 
		 * @param posicion
		 * @param bloque
		 */
	public AgrandadorBase(Vector2D posicion,Bloque bloque) {
		this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,bloque);
	}
        /**
         * Define el efecto que tiene la sorpresa sobre la bola
         * @param base - base sobre la cual actua la bola
         */
        @Override
	public void efecto(Base base){
		base.big();
	}
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    	
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }

}
