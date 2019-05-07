package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.*;

public class SlowBall extends Sorpresa{
    /**
     * 
     * @param posicion - posicion de la sorpresa
     * @param textura - imagen que posee la sorpresa
     * @param bloque  - bloque que contiene la sorpresa
     */
	public SlowBall(Vector2D posicion,BufferedImage textura,Bloque bloque){
		super(posicion,textura,bloque);

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
	
	
}