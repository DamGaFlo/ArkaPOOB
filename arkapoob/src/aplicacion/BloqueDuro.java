package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueDuro extends Bloque{
	
	public BloqueDuro(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura,arkaPOOB,resistencia);
	}

	public BloqueDuro(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB,RES_BLOQUE_DURO);
		
		
	}
	public int disminuirRes(int delta){
		super.disminuirRes(delta);
		if(getResistencia()<=0){
			return PUNTOS_BLOQUE_DURO;
		}
		return 0;
	}
	

}