package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueDuro extends Bloque{
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public BloqueDuro(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura,arkaPOOB,resistencia);
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public BloqueDuro(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB,RES_BLOQUE_DURO);
		
		
	}
        
        /**
         * 
         * @param delta - cantidad que se le cambia de resistenca
         * @return - puntos obtenidos por el golpe
         */
        @Override
	public int disminuirRes(int delta){
		super.disminuirRes(delta);
		if(getResistencia()<=0){
			return PUNTOS_BLOQUE_DURO;
		}
		return 0;
	}
	

}