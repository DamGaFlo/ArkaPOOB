package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import graficos.*;
import java.util.*;

public abstract class Sorpresa extends GameObject{
	public static final int NUM_SORPRESAS = 2;
	
	public static final int SLOW_BALL = 1;
	public static final int FAST_BALL = 1;
	
	public static final Vector2D velocidad = new Vector2D(0,6);
	private boolean visible = false;
	private Bloque bloque;
	private boolean destruida = false;
	
	public Sorpresa(Vector2D posicion,BufferedImage textura,Bloque bloque){
		super(posicion,textura);
		this.bloque = bloque;
	}
	
	public abstract void efecto(Base base);
	
	public void caer(){
		setPosicion(getPosicion().add(velocidad));
	}
	public void setVisible(boolean b){
		visible = b;
	}
	public void draw(Graphics g){
		if(visible) g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
	public void update(){
		if(visible){
			caer();
			colicion();
		}
	}
	public ArkaPOOB getArkaPoob(){
		return bloque.getArkaPOOB();
	}
	
	public static Sorpresa randomSorpresa(Vector2D posicion,Bloque bloque){
		Random random = new Random();
		return getSorpresa(random.nextInt(NUM_SORPRESAS)+1,posicion,bloque);
	} 
	public static Sorpresa getSorpresa(int numPower,Vector2D posicion,Bloque bloque){
		Sorpresa envio;
		switch(numPower){
			case 1:
				envio = new FastBall(posicion,Recursos.fastBall,bloque);
				break;
			case 2:
				envio = new SlowBall(posicion,Recursos.slowBall,bloque);
				break;
			default:
				envio = null;
		}
		return envio;
	}
	public void bordes(){
		if(getCentro().getY()+getHeight()/2 >= getMaxHight()){
			destroy();
		}
	}
	public void colicion(){
		bordes();
		if(!destruida){
			colicionPlayers();
		}
		
	}
	public void colicionPlayers(){
		ArrayList<Player> players = getArkaPoob().getPlayers();
		Vector2D miCentro = getCentro();
		for(Player p: players){
			Vector2D posBase = p.getBase().getCentro();
			int baseWidth = p.getBase().getWidth(),baseHeight = p.getBase().getHeight();
			if(Math.abs(posBase.getX()-miCentro.getX()) <= (getWidth()+baseWidth)/2 && Math.abs(posBase.getY()-miCentro.getY()) <= (getHeight()+baseHeight)/2){
				efecto(p.getBase());
				destroy();
				break;
				
			}
		}
	}
	public void destroy(){
		getArkaPoob().getSorpresas().remove(this);
		destruida = true;
	}
	public int getMaxWidth(){
		return getArkaPoob().getWidth();
	}
	public int getMaxHight(){
		return getArkaPoob().getHight();
	}
	
}