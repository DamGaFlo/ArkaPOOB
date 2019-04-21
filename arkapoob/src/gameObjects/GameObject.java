package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;

public abstract class GameObject{
	private BufferedImage textura;
	private Vector2D posicion;
	
	public GameObject(Vector2D posicion,BufferedImage textura){
		this.textura = textura;
		this.posicion = posicion;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public void setPosicion(Vector2D posicion){
		this.posicion = posicion;
	}
	public Vector2D getCentro(){
		return new Vector2D(posicion.getX()+textura.getWidth()/2,posicion.getY()+textura.getHeight()/2);
	}
	public Vector2D getPosicion(){
		return posicion;
	}
	public BufferedImage getTextura(){
		return textura;
	}
	public int getWidth(){
		return textura.getWidth();
	}
	public int getHeight(){
		return textura.getHeight();
	}

}