package aplicacion;

import math.Vector2D;

public class BloqueDuro extends Bloque{
	/**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         * @param resistencia - Resistencia del bloque a los golpes
         */
	public BloqueDuro(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB,int resistencia){
		super(posicion,width,height,estado,arkaPOOB,resistencia);
	}
        /**
         * 
         * @param posicion - Vector de informacion sobre la posicion
         * @param textura - Imagen que contiene el bloque
         * @param arkaPOOB - Juego sobre el cual se posiciona
         */
	public BloqueDuro(Vector2D posicion,int width,int height,int estado,ArkaPOOB arkaPOOB){
		super(posicion,width,height,estado,arkaPOOB,RES_BLOQUE_DURO);
	}
	public BloqueDuro(Vector2D posicion,ArkaPOOB arkaPOOB){
		this(posicion,WIDTH_DEFAULT,HEIGHT_DEFAULT,0,arkaPOOB);
	}
        /**
         * 
         * @param delta - cantidad que se le cambia de resistenca
         * @return - puntos obtenidos por el golpe
         */
        @Override
	public int colicion(Proyectil p){
		super.colicion(p);
		if(getResistencia()<=0){
			return PUNTOS_BLOQUE_DURO;
		}
		return 0;
	}
    public  Representacion representacion() {
    	return new Representacion(getNombre(),(int)getPosicion().getX(),(int)getPosicion().getY(),getEstado());
    }
    public String getNombre() {
    	return this.getClass().getSimpleName();
    }
	

}