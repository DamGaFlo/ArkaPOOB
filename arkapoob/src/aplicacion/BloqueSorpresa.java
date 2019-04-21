package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.Recursos;

public class BloqueSorpresa extends Bloque{
	
	private Sorpresa sorpresa;
	
	public BloqueSorpresa(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Sorpresa sorpresa){
		super(posicion,textura,arkaPOOB);
		this.sorpresa = sorpresa;
	}

	public BloqueSorpresa(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura,arkaPOOB);
		sorpresa = Sorpresa.randomSorpresa(getCentro(),this);
		
		
	}
	public int disminuirRes(int delta){
		super.disminuirRes(delta);
		if(getResistencia()<=0){
			habilidad();
			return PUNTOS_BLOQUE_SORP;
		}
		return 0;
	}
	
	public void habilidad(){
		sorpresa.setVisible(true);
		getArkaPOOB().getSorpresas().add(sorpresa);
	}
	
	

}