package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.*;
public class BolaNormal extends Proyectil{
	
	
	public BolaNormal(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
		super(posicion,textura, arkaPOOB, ultimoGolpeador,velocidad);
	}
	
	public BolaNormal(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB,Player ultimoGolpeador){
		super(posicion,textura, arkaPOOB, ultimoGolpeador);
	}

	
	public void update(){
		colicion();
		mov();
		
		
	}
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public void reboteX(){
		setVelocidad(new Vector2D(getVelocidad().getX()*-1,getVelocidad().getY()));
	}
	public void reboteY(){
		setVelocidad(new Vector2D(getVelocidad().getX(),getVelocidad().getY()*-1));
	}
	private void bordes(){
		if(getCentro().getX()+getWidth()/2 >= getMaxWidth()  ||  getCentro().getX()-getWidth()/2<=0) reboteX();
		if(getCentro().getY()+getHeight()/2 >= getMaxHight()  ||  getCentro().getY()-getHeight()/2<=0) reboteY();
	}
	
	
	private void colicion(){
		bordes();
		bloquesColicion();
		// se puede hacer mas general con un arreglo de players
		Vector2D posBase = getArkaPoob().getPlayer().getBase().getCentro(),miCentro = getCentro();
		int baseWidth = getArkaPoob().getPlayer().getBase().getWidth(),baseHeight = getArkaPoob().getPlayer().getBase().getHeight();
		if(Math.abs(posBase.getX()-miCentro.getX()) <= (getWidth()+baseWidth)/2 && Math.abs(posBase.getY()-miCentro.getY()) <= (getHeight()+baseHeight)/2){
			reboteY();
		}
		
		
	}
	private void bloquesColicion(){
		Vector2D miCentro = getCentro();
		ArrayList<Bloque> bloques = getArkaPoob().getBloques();
		for(Bloque b: bloques){
			Vector2D centroB = b.getCentro();
			int bWidth = b.getWidth(),bHeight = b.getHeight();
			if(Math.abs(centroB.getX() - miCentro.getX()) <= (getWidth()+bWidth)/2  && Math.abs(centroB.getY()-miCentro.getY()) <= (getHeight()+bHeight)/2){
				Vector2D posB = b.getPosicion();
				if(miCentro.getX() >= posB.getX() && miCentro.getX() <= posB.getX()+bWidth) reboteY();
				else if(miCentro.getY() >= posB.getY() && miCentro.getY() <= posB.getY()+bHeight) reboteX();
				else{
					reboteX();reboteY();
				}
			}
		}
		
	}
	private void mov(){
		setPosicion(getPosicion().add(getVelocidad()));
	}
}