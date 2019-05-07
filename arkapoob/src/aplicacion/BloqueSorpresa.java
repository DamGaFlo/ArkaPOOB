package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueSorpresa extends Bloque{
	
	private Sorpresa sorpresa;
	
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param sorpresa - Tipo de sorpresa que contiene el bloque
         */
	public BloqueSorpresa(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Sorpresa sorpresa){
		super(posicion,textura,arkaPOOB);
		this.sorpresa = sorpresa;
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public BloqueSorpresa(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB);
		sorpresa = Sorpresa.randomSorpresa(getCentro(),this);
		
		
	}
        
        /**
         * 
         * @param delta - cambio en la resistencia del bloque
         * @return puntos obtenidos
         */
        @Override
	public int disminuirRes(int delta){
		super.disminuirRes(delta);
		if(getResistencia()<=0){
			habilidad();
			return PUNTOS_BLOQUE_SORP;
		}
		return 0;
	}
	/**
         * Define la habilidad que tiene el bloque
         */
	public void habilidad(){
		sorpresa.setVisible(true);
		getArkaPOOB().getSorpresas().add(sorpresa);
	}
	
	

}