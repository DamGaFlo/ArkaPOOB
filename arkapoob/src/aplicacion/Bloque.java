package aplicacion;


import gameObjects.GameObject;
import math.Vector2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class Bloque extends GameObject{
	
	private int resistencia;
	public static final int PUNTOS_BLOQUE = 100,PUNTOS_BLOQUE_SORP = 300,PUNTOS_BLOQUE_DURO = 200;
	public static final int RES_BLOQUE = 1,RES_BLOQUE_DURO=2;
	private ArkaPOOB arkaPOOB;
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public Bloque(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,textura);
		this.resistencia = resistencia;
		this.arkaPOOB = arkaPOOB;
		
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public Bloque(Vector2D posicion,BufferedImage textura,ArkaPOOB arkaPOOB){
		super(posicion,textura);
		this.resistencia = RES_BLOQUE;
		this.arkaPOOB = arkaPOOB;
	}
        /**
         * Actualiza el estado del bloque
         */
        @Override
	public void update(){
		//System.out.println(resistencia);
		if(resistencia<=0){
			destroy();
		}
	}
	/**
	*draw genera la textura sobre graphis en el buffer
        * @param g - grafico sobre el cual se actua
	*/
        @Override
	public void draw(Graphics g){
		g.drawImage(getTextura(),(int)getPosicion().getX(),(int) getPosicion().getY(),null);
	}
        
        /**
         * 
         * @param delta - cantidad que se le cambia de resistenca
         * @return - puntos obtenidos por el golpe
         */
	public int disminuirRes(int delta){
		resistencia -=delta;
		if(resistencia<=0){
			return PUNTOS_BLOQUE;
		}
		return 0;
	}
        
        /**
         * 
         * @return Resistencia del bloque
         */
	public int getResistencia(){
		return resistencia;
	}
        /**
         * 
         * @return Juego sobre el cual se actua
         */
	public ArkaPOOB getArkaPOOB(){
		return arkaPOOB;
	}
        /**
         * Destruye el bloque
         */
	public void destroy(){
		getArkaPOOB().getBloques().remove(this);
		
		
	}
	
}