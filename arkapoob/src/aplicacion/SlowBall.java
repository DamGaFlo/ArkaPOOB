package aplicacion;

import math.Vector2D;

public class SlowBall extends Sorpresa{
    /**
     * 
     * @param posicion - posicion de la sorpresa
     * @param textura - imagen que posee la sorpresa
     * @param bloque  - bloque que contiene la sorpresa
     */
	public SlowBall(Vector2D posicion,int width,int height,int estado,Bloque bloque){
		super(posicion,width,height,estado,bloque);

	}
	
		/**
		 * 
		 * @param posicion
		 * @param bloque
		 */
	public SlowBall(Vector2D posicion,Bloque bloque) {
		this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,bloque);
	}
        /**
         * Efecto que tiene la sospresa sobre la base o juego
         * @param base - define la posicion en la cual actua la sorpresa
         */
        @Override
	public void efecto(Base base){
		for(Proyectil b : getArkaPoob().getBolasEnJuego()){
			b.setVelocidad(b.getVelocidad().escalar(0.5));
		}
	}
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    	
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
	
}