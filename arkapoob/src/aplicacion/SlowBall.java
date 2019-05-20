package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class SlowBall extends Sorpresa implements Serializable{
    /**
     * 
     * @param posicion - posicion de la sorpresa
     * @param width
     * @param height
     * @param bloque  - bloque que contiene la sorpresa
     * @param estado
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
    @Override
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    	
    @Override
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
	
}