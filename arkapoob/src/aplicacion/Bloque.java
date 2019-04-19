package aplicacion;


import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class Bloque extends GameObject{
	
	private int resistencia;
	
	public Bloque(Vector2D posicion,BufferedImage textura){
		super(posicion,textura);
		resistencia = 1;
	}
	public void update(){
		
	}
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public void disminuirRes(int delta){
		resistencia-=delta;
	}
public int getResistencia(){
	return resistencia;
}
	
	
}