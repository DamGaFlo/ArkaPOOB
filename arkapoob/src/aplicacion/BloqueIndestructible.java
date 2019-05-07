package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueIndestructible extends Bloque{
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public BloqueIndestructible(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura,arkaPOOB,resistencia);
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public BloqueIndestructible(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB,RES_BLOQUE_DURO);
		
		
	}
        /**
         * 
         * @param delta - cantidad de cambio sobre el bloque
         * @return nulo, pues no gana puntos en el golpe
         */
        @Override
	public int disminuirRes(int delta){
		//   :v
		return 0;
	}
	

}