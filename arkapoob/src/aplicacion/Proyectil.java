package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Proyectil extends GameObject{
	
	private static int damage = 1;
	private ArkaPOOB arkaPOOB; 
	private Player ultimoGolpeador;
	private Vector2D velocidad;
	
	public Proyectil(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Player ultimoGolpeador){
		super(posicion,textura);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		velocidad = new Vector2D(4,-4);
	}
	public Proyectil(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Player ultimoGolpeador,Vector2D velocidad){
		super(posicion,textura);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		this.velocidad = velocidad;
	}
	
	public Vector2D getVelocidad(){
		return velocidad;
	}
	public void setVelocidad(Vector2D v){
		velocidad = v;
		
	}
	public ArkaPOOB getArkaPoob(){
		return arkaPOOB;
	}
	public int getMaxWidth(){
		return arkaPOOB.getWidth();
	}
	public int getMaxHight(){
		return arkaPOOB.getHight();
	}
	//sobreescribir 
	
	
	
}