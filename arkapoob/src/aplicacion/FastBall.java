package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.*;

public class FastBall extends Sorpresa{
    /**
     * 
     * @param posicion - posicion de la sorpresa 
     * @param textura - imagen del tipo de sorpresa
     * @param bloque - bloque sobre el cual se encuentra
     */
	public FastBall(Vector2D posicion,BufferedImage textura,Bloque bloque){
		super(posicion,textura,bloque);

	}
        /**
         * Define el efecto que tiene la sorpresa sobre la bola
         * @param base - base sobre la cual actua la bola
         */
        @Override
	public void efecto(Base base){
		for(Proyectil b : getArkaPoob().getBolasEnJuego()){
			b.setVelocidad(b.getVelocidad().escalar(2));
		}
	}
	
	
}