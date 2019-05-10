package aplicacion;

import math.Vector2D;

public abstract class GameObject{
	private Vector2D posicion;
	private int width;
	private int height;
	private int estado;
	
	public GameObject(Vector2D posicion,int width,int height,int estado){
		this.width = width;
		this.height = height;
		this.estado = estado;
		this.posicion = posicion;
	}
	
	
	public abstract void update();
	public  abstract Representacion representacion() ;
	
	public void setPosicion(Vector2D posicion){
		this.posicion = posicion;
	}
	public Vector2D getCentro(){
		return new Vector2D(posicion.getX()+width/2,posicion.getY()+height/2);
	}
	public Vector2D getPosicion(){
		return posicion;
	}
	public abstract String getNombre() ;

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public int getEstado() {
		return estado;
	}

}