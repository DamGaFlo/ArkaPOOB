package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import input.*;
import graficos.*;

public class Base extends GameObject{
	
	public static final int NORMAL = 1;
	private Player player;
	private Proyectil residente;
	private int moxEnX = 10;
	
	
	public Base(Vector2D posicion,BufferedImage textura, Player player,BolaNormal residente){
		super(posicion,textura);
		this.player = player;
		this.residente = residente;
	}
	public Base(Vector2D posicion,BufferedImage textura, Player player){
		super(posicion,textura);
		this.player = player;
		this.residente = null;
	}
	
	public void update(){
		if(Teclado.getLeft()){
			if(getPosicion().getX()-moxEnX >= 0){
				getPosicion().cambioX(-moxEnX);
				if(residente!=null) residente.mov(new Vector2D(-moxEnX,0));
			}
				
		}
		if(Teclado.getRight()){
			if(getPosicion().getX()+getWidth()+moxEnX <= getArkaPOOB().getWidth()){
				getPosicion().cambioX(moxEnX);
				if(residente!=null) residente.mov(new Vector2D(moxEnX,0));
			}
		
		}
		if(Teclado.getSpace()){
			if(residente!=null) {
				residente.diparado();
				residente = null;
			}
		}
	}
	/**
	*draw genera la textura sobre graphis en el buffer
	*/
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public static Base getBase(int numBase,Vector2D posicion,Player player){
		if(numBase == 1) return new Base(posicion,Recursos.plataforma,player);
		return null;
		
	}
	public void habilidad(){}
	public void setResidente(Proyectil residente){
		this.residente = residente;
	}
	public void movOpuesto(){
		moxEnX*=-1;
	}
	public ArkaPOOB getArkaPOOB(){
		return player.getArkaPOOB();
	}
	
}