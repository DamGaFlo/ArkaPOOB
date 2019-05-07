package aplicacion;

import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.*;
import input.*;
public class BolaNormal extends Proyectil{
	
	private boolean destruida = false;
	
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene la pelota
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param ultimoGolpeador - Reconoce el jugador que golpeo por ultima vez
         * @param velocidad - Velocidad con la cual viaja la pelota
         */
	public BolaNormal(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad){
		super(posicion,textura, arkaPOOB, ultimoGolpeador,velocidad,Proyectil.DAMAGE_NORMAL);
		
	}
	
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene la pelota
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param ultimoGolpeador - Reconoce el jugador que golpeo por ultima vez
         */
	public BolaNormal(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB,Player ultimoGolpeador){
		super(posicion,textura, arkaPOOB, ultimoGolpeador,Proyectil.DAMAGE_NORMAL);
	}
	
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene la pelota
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param ultimoGolpeador - Reconoce el jugador que golpeo por ultima vez
         * @param velocidad - Velocidad con la cual viaja la pelota
         * @param damage - daño que genera la bola al golpear
         */
	public BolaNormal(Vector2D posicion,BufferedImage textura, ArkaPOOB arkaPOOB,Player ultimoGolpeador, Vector2D velocidad,int damage){
		super(posicion,textura, arkaPOOB, ultimoGolpeador,velocidad,damage);
	}

	
	/**
	*update actualiza la logica del funcionamiento de la bola
	*/
        @Override
	public void update(){
		
		if(getEnAire()){
			colicion();
			corrigeAngle();
			mov();
		}
		
	}
	/**
	*draw genera la textura sobre graphis en el buffer
        * @param g
	*/
        @Override
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
        
        /**
         * Define direccion del rebote en X
         */
	public void reboteX(){
		setVelocidad(new Vector2D(getVelocidad().getX()*-1,getVelocidad().getY()));
	}
        /**
         * Define direccion del rebote en Y
         */
	public void reboteY(){
		setVelocidad(new Vector2D(getVelocidad().getX(),getVelocidad().getY()*-1));
	}
        /**
         * Reconoce los bordes por los cuales viaja la bola para tomar una decision segun su lugar
         */
	private void bordes(){
		if(getCentro().getX()+getWidth()/2 >= getMaxWidth()  ||  getCentro().getX()-getWidth()/2<=0) reboteX();
		if(getCentro().getY()-getHeight()/2<=0) reboteY();
		if(getCentro().getY()+getHeight()/2 >= getMaxHight()){
			destroy();
		}
	}
	
	/**
         * Responde a un impacto de la bola en el juego
         */
	private void colicion(){
		bordes();
		if(!destruida){
			bloquesColicion();
			colicionPlayers();
		}
		
	}
        /**
         * Responde a un impacto con el jugador
         */
	private void colicionPlayers(){
		ArrayList<Player> players = getArkaPoob().getPlayers();
		Vector2D miCentro = getCentro();
		for(Player p: players){
			Vector2D posBase = p.getBase().getCentro();
			int baseWidth = p.getBase().getWidth(),baseHeight = p.getBase().getHeight();
			if(Math.abs(posBase.getX()-miCentro.getX()) <= (getWidth()+baseWidth)/2 && Math.abs(posBase.getY()-miCentro.getY()) <= (getHeight()+baseHeight)/2){
				if(!p.enEspera()){
					p.tiempoDeEspera();
					reboteY();
				}
				break;
			}
		}
	}
        
        /**
         * Responde a un impacto con un bloque
         */
	private void bloquesColicion(){
		Vector2D miCentro = getCentro();
		ArrayList<Bloque> bloques = getArkaPoob().getBloques();
		for(Bloque b: bloques){
			Vector2D centroB = b.getCentro();
			int bWidth = b.getWidth(),bHeight = b.getHeight();
			if(Math.abs(centroB.getX() - miCentro.getX()) <= (getWidth()+bWidth)/2  && Math.abs(centroB.getY()-miCentro.getY()) <= (getHeight()+bHeight)/2){
				b.disminuirRes(getDamage());
				Vector2D posB = b.getPosicion();
				if(miCentro.getX() >= posB.getX() && miCentro.getX() <= posB.getX()+bWidth) reboteY();
				else if(miCentro.getY() >= posB.getY() && miCentro.getY() <= posB.getY()+bHeight) reboteX();
				else{
					reboteX();reboteY();
					setVelocidad(getVelocidad().addAngle(((Math.random()*2)-1)*Math.PI/4));
					
				}
			}
		}
		
	}
        
        /**
         * cambia el movimiento de la bola
         */
        @Override
	public void mov(){
		setPosicion(getPosicion().add(getVelocidad()));
	}
        /**
         * Cambia el movimiento a partir de un vector
         * @param movimiento - Vector de movimiento
         */
        @Override
	public void mov(Vector2D movimiento){
		setPosicion(getPosicion().add(movimiento));
	}
	public void destroy(){
		getArkaPoob().getBolasEnJuego().remove(this);
		destruida = true;
	}
        /**
         * Mueve la bola sobre la base del jugador
         */
        @Override
	public void ajustarSobreBase(){
		setPosicion(new Vector2D(getPosicion().getX()-getWidth()/2,getPosicion().getY()-getHeight()-5));
	}
        /**
         * Corrige el angulo de direccion de la bola
         */
	private void corrigeAngle(){
		double angle = Math.toDegrees(getVelocidad().getAngle());
		if((angle>85 && angle<95) || (angle>175 && angle<185) || (angle>265 && angle<275) || (angle>=0 && angle<5) || (angle>355 && angle<=360)) setVelocidad(getVelocidad().addAngle(Math.PI/4));
	}
	
}