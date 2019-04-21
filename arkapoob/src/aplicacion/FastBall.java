package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.*;

public class FastBall extends Sorpresa{
	public FastBall(Vector2D posicion,BufferedImage textura,Bloque bloque){
		super(posicion,textura,bloque);

	}
	public void efecto(Base base){
		for(Proyectil b : getArkaPoob().getBolasEnJuego()){
			b.setVelocidad(b.getVelocidad().escalar(2));
		}
	}
	
	
}