package aplicacion;


import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class Bloque extends GameObject{
	
	private int resistencia;
	public static final int PUNTOS_BLOQUE = 100,PUNTOS_BLOQUE_SORP = 300,PUNTOS_BLOQUE_DURO = 200;
	public static final int RES_BLOQUE = 1,RES_BLOQUE_DURO=2;
	private ArkaPOOB arkaPOOB;
	
	public Bloque(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura);
		this.resistencia = resistencia;
		this.arkaPOOB = arkaPOOB;
		
	}
	public Bloque(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura);
		this.resistencia = RES_BLOQUE;
		this.arkaPOOB = arkaPOOB;
	}
	public void update(){
		//System.out.println(resistencia);
		if(resistencia<=0){
			destroy();
		}
	}
	/**
	*draw genera la textura sobre graphis en el buffer
	*/
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public int disminuirRes(int delta){
		resistencia -=delta;
		if(resistencia<=0){
			return PUNTOS_BLOQUE;
		}
		return 0;
	}
	public int getResistencia(){
		return resistencia;
	}
	public ArkaPOOB getArkaPOOB(){
		return arkaPOOB;
	}

	public void destroy(){
		getArkaPOOB().getBloques().remove(this);
		
		
	}
	
}