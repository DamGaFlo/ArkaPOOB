package aplicacion;

import java.io.Serializable;
import math.Vector2D;

public class BloqueIndestructible extends Bloque implements Serializable{
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param estado
     * @param arkaPOOB - Juego sobre el cual se posiciona
     * @param resistencia - Resistencia del bloque a los golpes
     * @param puntaje
     */
    public BloqueIndestructible(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia, int puntaje){
            super(posicion,width,height,estado,arkaPOOB,resistencia,puntaje);
    }
    /**
     * 
     * @param posicion - Vector de informacion sobre la posicion
     * @param width
     * @param height
     * @param arkaPOOB - Juego sobre el cual se posiciona
     * @param estado
     */
    public BloqueIndestructible(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
            super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE_DURO,0);


    }
    public BloqueIndestructible(Vector2D posicion,ArkaPOOB arkaPOOB){
            this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
    }
    /**
     * 
     * @param P
     * @return nulo, pues no gana puntos en el golpe
     */
    @Override
    public int colicion(Proyectil P){
            //   :v
            return PUNTAJE;
    }
    @Override
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    @Override
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
	

}