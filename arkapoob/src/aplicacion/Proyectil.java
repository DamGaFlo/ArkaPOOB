package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Proyectil extends GameObject{
	
	public  static final int DAMAGE_NORMAL = 1;
	private int damage;
	private ArkaPOOB arkaPOOB; 
	private Player ultimoGolpeador;
	private Vector2D velocidad;
	
	public Proyectil(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Player ultimoGolpeador,int damage){
		super(posicion,textura);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		velocidad = new Vector2D(4,-4);
		this.damage = damage;
	}
	public Proyectil(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,Player ultimoGolpeador,Vector2D velocidad,int damage){
		super(posicion,textura);
		this.ultimoGolpeador = ultimoGolpeador;
		this.arkaPOOB = arkaPOOB;
		this.velocidad = velocidad;
		this.damage = damage;
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
	public int getDamage(){
		return damage;
	}
	//sobreescribir 
	
	
	
}