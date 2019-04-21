package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueIndestructible extends Bloque{
	
	public BloqueIndestructible(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura,arkaPOOB,resistencia);
	}

	public BloqueIndestructible(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB,RES_BLOQUE_DURO);
		
		
	}
	public int disminuirRes(int delta){
		//   :v
		return 0;
	}
	

}