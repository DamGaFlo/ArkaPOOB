package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import input.*;
import graficos.*;

public class Base extends GameObject{
	
	public static final int NORMAL = 1;
	
	private ArkaPOOB arkaPOOB;
	public static final int CAMBIO_X = 10;
	
	
	public Base(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB){
		super(posicion,textura);
		this.arkaPOOB = arkaPOOB;
	}
	
	public void update(){
		if(Teclado.getLeft()){
			if(getPosicion().getX()-CAMBIO_X >= 0)
				getPosicion().cambioX(-CAMBIO_X);
		}
		if(Teclado.getRight()){
			if(getPosicion().getX()+getWidth()+CAMBIO_X <= arkaPOOB.getWidth())
			getPosicion().cambioX(CAMBIO_X);
		}
	}
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public static Base getBase(int numBase,Vector2D posicion,ArkaPOOB arkaPOOB){
		if(numBase == 1) return new Base(posicion,Recursos.plataforma,arkaPOOB);
		return null;
		
	}
	
}